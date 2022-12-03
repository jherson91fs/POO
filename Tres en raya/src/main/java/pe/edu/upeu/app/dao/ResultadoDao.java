/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.upeu.app.dao;


import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import pe.edu.upeu.app.coon.ConnS;
import pe.edu.upeu.app.modelo.ResultadoTO;

/**
 *
 * @author INTEL
 */
public class ResultadoDao implements ResultadoDaoI{
    ConnS instance = ConnS.getInstance();
    Connection conexion = instance.getConnection();
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public List ListarResultados() {
       List<ResultadoTO> lista =new ArrayList();
       String sql = "select * from resultados";
        try {
            ps=conexion.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {
                ResultadoTO to = new ResultadoTO();
                to.setId_Resultado(rs.getInt("id_resultado"));
                to.setNombre_partida(rs.getString("Nombre partido"));
                to.setNombre_jugador1(rs.getString("Nombre de jugador 1"));
                to.setNombre_jugador2(rs.getString("Nombre de jugador 2"));
                to.setGanador(rs.getString("Nombre ganador"));
                to.setPunto(rs.getInt("Puntuacion"));
                to.setEstado(rs.getString("Estado"));
            }
        } catch (Exception e) {
        }
        return lista;
    }

    @Override
    public int crearResultado(ResultadoTO re) {
        int exec = 0;
        int i=0;
        String sql="insert into resultados(Nombre_jugador1, Nombre_jugador2)" + " values(?,?)";
        //,Ganador, Punto, Estado
        try {
            ps = conexion.prepareCall(sql);
            ps.setString(++i, re.getNombre_partida());
            ps.setString(++i, re.getNombre_jugador1());
            ps.setString(++i, re.getNombre_jugador2());
            ps.setString(++i, re.getGanador());
            ps.setInt(++i, re.getPunto());
            ps.setString(++i, re.getEstado());
            exec = ps.executeUpdate();
        } catch (Exception e) {
        }
        return exec;
    }
    
}
