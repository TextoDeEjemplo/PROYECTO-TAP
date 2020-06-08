/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurante_proyecto;
 
import Modelo.Modelo;
import Vista.Ventana_Meseros;
import Vista.Ventana_Administrador;
import Controlador.Controlador_Restaurante;
import Vista.Ventana_Caja;
import Vista.Ventana_Cocina;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author RodrigoLP
 */
public class Restaurante_Proyecto 
{

    public static final String URL = "jdbc:mysql://localhost:3306/proyecto_restaurante";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "LOADING55";
    Connection conectar = null;
        

    public Connection conexion() {
        Connection con = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(URL, USERNAME, PASSWORD);
            //JOptionPane.showMessageDialog(null, "Conexion Exitosa");
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }
    public static void main(String[] args) 
    {
        
        Ventana_Meseros vista_meseros = new Ventana_Meseros();
        Ventana_Administrador vista_admin = new Ventana_Administrador();
        Ventana_Cocina vista_cocina = new Ventana_Cocina();
        Ventana_Caja vista_caja = new Ventana_Caja();
        Modelo modelo = new Modelo();
        Controlador_Restaurante controlar_meseros = new Controlador_Restaurante(vista_meseros, vista_admin, vista_cocina, vista_caja, modelo);
        vista_meseros.conectarControlador(controlar_meseros);
        vista_admin.conectarControlador(controlar_meseros);    
        vista_caja.conectarControlador(controlar_meseros); 
    }

}
