/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebabd;

import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author oquintansocampo
 */
public class PruebaBD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BaseDatos bd = new BaseDatos();
        bd.conexion();
        int op;
        do {
            op = Integer.parseInt(JOptionPane.showInputDialog("BaseDatos Colegio\n1--->Select\n2--->Insert\n3--->Delete\n4--->Create\n5--->Exit"));
            switch (op) {
                case 1:
                    bd.select();
                    break;
                case 2:
                    bd.insertar();
                    break;
                case 3:
                    bd.borrar();
                    break;
                case 4:
                    bd.crearTab();
                    break;
                case 5:
                    try {
                        bd.con.close();
                    } catch (SQLException ex) {
                        System.out.println("ERROR " + ex);
                    }
                    System.exit(0);
            }
        } while (op != 5);
    }
}
