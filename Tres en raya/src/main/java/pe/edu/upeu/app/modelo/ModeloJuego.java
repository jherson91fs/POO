/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.upeu.app.modelo;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import pe.edu.upeu.app.dao.ResultadoDao;
import pe.edu.upeu.app.dao.ResultadoDaoI;
import pe.edu.upeu.app.gui.MainJuego;

public class ModeloJuego {
    
    MainJuego Gp;
    ResultadoDaoI resul;
    DefaultTableModel modelo;
    private String turno;
    private boolean end;
    private boolean empate;
    public JLabel Xj1;
    public JLabel Oj2;
    private String[][] tablero;
    private int cantiJugadas;
    private int victoriasJ1;
    private int victoriasJ2;

    public ModeloJuego() {

        turno = "X";
        end = false;
        empate = false;
        tablero = new String[3][3];
        //cantiJugada = 0;
        cantiJugadas = 0;
        victoriasJ1 = 0;
        victoriasJ2 = 0;
    }

    public void marcarBtn(int i, int j, JButton[][] MatrizButton, javax.swing.JButton bt) {
        if (!end) {
            if (tablero[i][j] == null) {
                tablero[i][j] = turno;
                MatrizButton[i][j].setText(turno);
                cantiJugadas++;
                verificarEstado();
                if (!end) {
                    if (turno.equals("X")) {
                        turno = "O";
                        bt.setForeground(Color.red);
                        bt.setFont(new Font("Ink Free", Font.BOLD, 56));
                    } else {
                        turno = "X";
                        bt.setForeground(Color.blue);
                        bt.setFont(new Font("Ink Free", Font.BOLD, 56));
                    }
                } else {
                    terminarJuego();
                }
            }
        }
    }

    private void verificarEstado() {
        verificarFilas();
        if (!end) {
            verificarColumnas();
            if (!end) {
                verificarDiagonalP();
                if (!end) {
                    verificarDiagonalS();
                    if (cantiJugadas == 9) {
                        empate = true;
                        end = true;
                    }
                }
            }
        }
    }

    private void verificarFilas() {
        for (int i = 0; i < 3 && !end; i++) {
            boolean win = true;
            for (int j = 0; j < 3 && win; j++) {
                if (tablero[i][j] == null || !tablero[i][j].equals(turno)) {
                    win = false;
                }
            }
            if (win) {
                end = true;
            }
        }
    }

    private void verificarColumnas() {
        for (int j = 0; j < 3 && !end; j++) {
            boolean win = true;
            for (int i = 0; i < 3 && win; i++) {
                if (tablero[i][j] == null || !tablero[i][j].equals(turno)) {
                    win = false;
                }
            }
            if (win) {
                end = true;
            }
        }
    }

    private void verificarDiagonalP() {

        boolean win = true;
        for (int i = 0; i < 3 && win; i++) {
            if (tablero[i][i] == null || !tablero[i][i].equals(turno)) {
                win = false;
            }
        }
        if (win) {
            end = true;
        }
    }

    private void verificarDiagonalS() {

        boolean win = true;
        int j = 2;
        for (int i = 0; i < 3 && win; i++, j--) {
            if (tablero[i][j] == null || !tablero[i][j].equals(turno)) {
                win = false;
            }
        }
        if (win) {
            end = true;
        }

    }

    private void terminarJuego() {

        if (empate) {
            victoriasJ1 = 0;
            victoriasJ2 = 0;
            JOptionPane.showMessageDialog(null, "Empate ningún ganador");
            Gp.Guadardatos();
        } else {
            if (turno.equals("X")) {
                victoriasJ1++;
                Xj1.setText(String.valueOf(victoriasJ1));
                JOptionPane.showMessageDialog(null, "Victoria jugador 1");
               Gp.Guadardatos();
            } else {
                victoriasJ2++;
                Oj2.setText(String.valueOf(victoriasJ2));
                JOptionPane.showMessageDialog(null, "Victoria jugador 2");
                Gp.Guadardatos();
            }
        }
    }

    public void setPlayer(JLabel j1, JLabel j2) {
        Xj1 = j1;
        Oj2 = j2;
    }

    public void resetJP(JButton[][] MatrizButton) {
        turno = "X";
        end = false;
        empate = false;
        cantiJugadas = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = null;
                MatrizButton[i][j].setText("");
                
            }
        }
    }
}
