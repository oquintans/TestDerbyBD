/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebabd;

import java.sql.SQLException;

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
       // bd.select();
        //bd.borrar();
        bd.select();
        //bd.crearTab();
        
        try {
            bd.con.close();
        } catch (SQLException ex) {
            System.out.println("ERROR " + ex);
        }

    }
}
