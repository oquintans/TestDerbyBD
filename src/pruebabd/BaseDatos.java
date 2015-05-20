/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebabd;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author oquintansocampo
 */
public class BaseDatos {

    Connection con = null;
    Statement s = null;
    ResultSet rs = null;

    public Connection conexion() {
        try {
            //Cargar Driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            //Conectar al derby
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/colegio");
            //Confirmacion
            System.out.println("Conexion establecida");

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("ERROR de conexion: " + e);
        }

        return con;
    }

    public void insertar() {
        try {
            //Declarar consulta
            s = con.createStatement();
            String cons = JOptionPane.showInputDialog("Cod");
            String cons2 = JOptionPane.showInputDialog("nombre");
            String cons3 = JOptionPane.showInputDialog("nota");
            //Ejecutar consulta
            s.executeUpdate("INSERT INTO APP.ALUMNOS values (" + "'" + cons + "'" + ',' + cons3 + ',' + "'" + cons2 + "'" + ')');
            //Confirmacion
            System.out.println("Inserción realizada");

        } catch (SQLIntegrityConstraintViolationException ex) {
            System.out.println("ERROR " + ex);
            //En caso de que repitamos la primary key volvera a lanzar el metodo
            insertar();

        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void select() {
        try {
            //Declaracion y ejecucion de consulta
            s = con.createStatement();
            String nTab = JOptionPane.showInputDialog("nombre tabla");
            rs = s.executeQuery("select * from " + nTab);
            //Recibimos el resultado y utilizamos metodos para imprimirlo
            ResultSetMetaData rsmd = rs.getMetaData();
            int nColumnas = rsmd.getColumnCount();
            //Esto es para que imprima bonito
            while (rs.next()) {
                for (int i = 1; i <= nColumnas; i++) {
                    if (i > 1) {
                        System.out.print(",  ");
                    }
                    String colValue = rs.getString(i);
                    System.out.print(rsmd.getColumnName(i) + " " + colValue);
                }
                System.out.println("");
            }

        } catch (SQLException ex) {
            System.out.println("ERROR " + ex);
            //En caso de excepcion relanza el metodo
            select();
        }
    }

    public void borrar() {
        try {
            //Declarar consulta y ejecutar
            s = con.createStatement();
            String cons = JOptionPane.showInputDialog("Cod");
            s.executeUpdate("delete from ALUMNOS where cod='" + cons + "'");
            //Confirmacion
            System.out.println("Borrado realizado");

        } catch (SQLException ex) {
            Logger.getLogger(BaseDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void crearTab() {
        try {
            //Declaracion
            s = con.createStatement();
            String nTab = JOptionPane.showInputDialog("nombre tabla");
            //Ejecucion
            s.executeUpdate("create table " + nTab + " (cod varchar(5) primary key,nombre varchar(10))");
            //Confirmacion
            System.out.println("Tabla Creada");
        } catch (SQLException ex) {
            System.out.println("ERROR " + ex);
        }
    }
}
