/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.Controlador_Restaurante;
import Vista.Ventana_Administrador;
import Vista.Ventana_Meseros;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import restaurante_proyecto.Restaurante_Proyecto;
/**
 *
 * @author RodrigoLP
 */
public class Modelo 
{
    PreparedStatement ps;
    ResultSet res;
    
    Restaurante_Proyecto cc= new Restaurante_Proyecto();
    Connection cn= cc.conexion();
    
    private Ventana_Meseros vista_meseros;
    private Ventana_Administrador vista_administrador;
    private Controlador_Restaurante controlador;
    
    
        
    public Modelo()
    {
        
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
public void metodo()
{               
    TableModel TablaDatos = obtenerTDatosPlatillos("");
    for (int i = 0; i < TablaDatos.getRowCount(); i++) 
    {
        System.out.println("ID: "+TablaDatos.getValueAt(i, 0));
        System.out.println("NOMBRE: "+TablaDatos.getValueAt(i, 1));
        System.out.println("DESCRIPCION: "+TablaDatos.getValueAt(i, 2));
        System.out.println("PRECIO: "+TablaDatos.getValueAt(i, 3));
        System.out.println("URL: "+TablaDatos.getValueAt(i, 4));
        System.out.println("///////////////////////////////////////");
    }                
}
    
        
//////OBTENER TABLA DE DATOS PRODUCTOS//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
    
    public TableModel obtenerTDatosPlatillos(String valor) 
    {        
        DefaultTableModel TBPlatillos = new DefaultTableModel()
        {
            @Override
            public boolean isCellEditable(int filas, int columnas)
            {
                return false;
            }
        };
        TBPlatillos.addColumn("ID_PLATILLO");
        TBPlatillos.addColumn("NOMBRE");
        TBPlatillos.addColumn("DESCRIPCION");
        TBPlatillos.addColumn("PRECIO");
        TBPlatillos.addColumn("URL");
        String sql = "";
        if (valor.equals("")) 
        {
            sql = "SELECT * FROM platillos";
        } 
        else 
        {
            sql = "SELECT * FROM platillos WHERE id_platillos='"+valor+"' OR nombre_platillos='" +valor+" '";
        }

        String[] DATOSL = new String[5];
        try 
        {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                DATOSL[0] = rs.getString(1);
                DATOSL[1] = rs.getString(2);
                DATOSL[2] = rs.getString(3);
                DATOSL[3] = rs.getString(4);
                DATOSL[4] = rs.getString(5);
                TBPlatillos.addRow(DATOSL);
            }
            return TBPlatillos;
            

        } 
        catch (SQLException ex) 
        {
            //Logger.getLogger(Restaurante_Proyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public TableModel obtenerTDatosBebidasA(String valor) 
    {

        DefaultTableModel TBBebidasA = new DefaultTableModel()
        {
            @Override
            public boolean isCellEditable(int filas, int columnas)
            {
                return false;
            }
        };
        
        TBBebidasA.addColumn("ID_BEBIDA");
        TBBebidasA.addColumn("NOMBRE");
        TBBebidasA.addColumn("DESCRIPCION");
        TBBebidasA.addColumn("PRECIO");
        TBBebidasA.addColumn("TIPO");
        TBBebidasA.addColumn("URL");
        String sql = "";
        if (valor.equals("")) 
        {
            sql = "SELECT * FROM bebidas_alcoholicas";
        } 
        else 
        {
            sql = "SELECT * FROM bebidas_alcoholicas WHERE id_bebidas_alcoholicas='"+valor+"' OR nombre_bebidas_alcoholicas='" +valor+" '";
        }

        String[] DATOSL = new String[6];
        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
            {
                DATOSL[0] = rs.getString(1);
                DATOSL[1] = rs.getString(2);
                DATOSL[2] = rs.getString(3);
                DATOSL[3] = rs.getString(4);
                DATOSL[4] = rs.getString(5);
                DATOSL[5] = rs.getString(6);
                TBBebidasA.addRow(DATOSL);
            }
            return TBBebidasA;
        } 
        catch (SQLException ex) 
        {
            //Logger.getLogger(Restaurante_Proyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public TableModel obtenerTDatosBebidasF(String valor) 
    {

        DefaultTableModel TBBebidasF = new DefaultTableModel()
        {
            @Override
            public boolean isCellEditable(int filas, int columnas)
            {
                return false;
            }
        };        
        TBBebidasF.addColumn("ID_B_FRIA");
        TBBebidasF.addColumn("NOMBRE");
        TBBebidasF.addColumn("DESCRIPCION");
        TBBebidasF.addColumn("PRECIO");
        TBBebidasF.addColumn("URL");
        String sql = "";
        if (valor.equals("")) 
        {
            sql = "SELECT * FROM bebidas_frias";
        } 
        else 
        {
            sql = "SELECT * FROM bebidas_frias WHERE id_bebidas_frias='"+valor+"' OR nombre_bebidas_frias='" +valor+" '";
        }

        String[] DATOSL = new String[5];
        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
            {
                DATOSL[0] = rs.getString(1);
                DATOSL[1] = rs.getString(2);
                DATOSL[2] = rs.getString(3);
                DATOSL[3] = rs.getString(4);
                DATOSL[4] = rs.getString(5);
                TBBebidasF.addRow(DATOSL);
            }
            return TBBebidasF;
        } 
        catch (SQLException ex) 
        {
            //Logger.getLogger(Restaurante_Proyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public TableModel obtenerTDatosBebidasC(String valor) 
    {
        DefaultTableModel TBBebidasC = new DefaultTableModel()
        {
            @Override
            public boolean isCellEditable(int filas, int columnas)
            {
                return false;
            }
        };        
        TBBebidasC.addColumn("ID_B_CALIENTE");
        TBBebidasC.addColumn("NOMBRE");
        TBBebidasC.addColumn("DESCRIPCION");
        TBBebidasC.addColumn("PRECIO");
        TBBebidasC.addColumn("URL");
        String sql = "";
        if (valor.equals("")) 
        {
            sql = "SELECT * FROM bebidas_calientes";
        } 
        else 
        {
            sql = "SELECT * FROM bebidas_calientes WHERE id_bebidas_calientes='"+valor+"' OR nombre_bebidas_calientes='" +valor+" '";
        }

        String[] DATOSL = new String[5];
        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
            {
                DATOSL[0] = rs.getString(1);
                DATOSL[1] = rs.getString(2);
                DATOSL[2] = rs.getString(3);
                DATOSL[3] = rs.getString(4);
                DATOSL[4] = rs.getString(5);
                TBBebidasC.addRow(DATOSL);
            }
            return TBBebidasC;
        } 
        catch (SQLException ex) 
        {
            //Logger.getLogger(Restaurante_Proyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public TableModel obtenerTDatosPostres(String valor) 
    {        
        DefaultTableModel TBPostres = new DefaultTableModel()
        {
            @Override
            public boolean isCellEditable(int filas, int columnas)
            {
                return false;
            }
        };
        TBPostres.addColumn("ID_POSTRE");
        TBPostres.addColumn("NOMBRE");
        TBPostres.addColumn("DESCRIPCION");
        TBPostres.addColumn("PRECIO");
        TBPostres.addColumn("URL");
        String sql = "";
        if (valor.equals("")) 
        {
            sql = "SELECT * FROM postres";
        } 
        else 
        {
            sql = "SELECT * FROM postres WHERE id_postres='"+valor+"' OR nombre_postres='" +valor+" '";
        }

        String[] DATOSL = new String[5];
        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) 
            {
                DATOSL[0] = rs.getString(1);
                DATOSL[1] = rs.getString(2);
                DATOSL[2] = rs.getString(3);
                DATOSL[3] = rs.getString(4);
                DATOSL[4] = rs.getString(5);
                TBPostres.addRow(DATOSL);
            }
            return TBPostres;

        } 
        catch (SQLException ex) 
        {
            //Logger.getLogger(Restaurante_Proyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }
    
//////OBTENER TABLA DE DATOS USUARIOS//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
    
    
    public TableModel obtenerTDatosCajeros(String valor) 
    {

        DefaultTableModel TBCajeros = new DefaultTableModel()
        {
            @Override
            public boolean isCellEditable(int filas, int columnas)
            {
                return false;
            }
        };
        TBCajeros.addColumn("ID_CAJERO");
        TBCajeros.addColumn("NOMBRE");
        TBCajeros.addColumn("APELLIDOS");
        TBCajeros.addColumn("FECHA NAC");
        TBCajeros.addColumn("FECHA IN");
        TBCajeros.addColumn("USUARIO");
        TBCajeros.addColumn("CONTRASEÑA");
        String sql = "";
        if (valor.equals("")) 
        {
            sql = "SELECT * FROM cajeros";
        } 
        else 
        {
            sql = "SELECT * FROM cajeros WHERE id_cajeros='"+valor+"' OR nombre_cajeros='" +valor+" '";
        }

        String[] DATOSL = new String[7];
        try 
        {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                DATOSL[0] = rs.getString(1);
                DATOSL[1] = rs.getString(2);
                DATOSL[2] = rs.getString(3);
                DATOSL[3] = rs.getString(4);
                DATOSL[4] = rs.getString(5);
                DATOSL[5] = rs.getString(6);
                DATOSL[6] = rs.getString(7);
                TBCajeros.addRow(DATOSL);
            }
            return TBCajeros;
        } 
        catch (SQLException ex) 
        {
            //Logger.getLogger(Restaurante_Proyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public TableModel obtenerTDatosBarmans(String valor) 
    {
        DefaultTableModel TBBarmans = new DefaultTableModel()
        {
            @Override
            public boolean isCellEditable(int filas, int columnas)
            {
                return false;
            }
        };
        TBBarmans.addColumn("ID_BARMAN");
        TBBarmans.addColumn("NOMBRE");
        TBBarmans.addColumn("APELLIDOS");
        TBBarmans.addColumn("FECHA NAC");
        TBBarmans.addColumn("FECHA IN");
        TBBarmans.addColumn("USUARIO");
        TBBarmans.addColumn("CONTRASEÑA");
        String sql = "";
        if (valor.equals("")) 
        {
            sql = "SELECT * FROM barmans";
        } 
        else 
        {
            sql = "SELECT * FROM barmans WHERE id_barmans='"+valor+"' OR nombre_barmans='" +valor+" '";
        }

        String[] DATOSL = new String[7];
        try 
        {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                DATOSL[0] = rs.getString(1);
                DATOSL[1] = rs.getString(2);
                DATOSL[2] = rs.getString(3);
                DATOSL[3] = rs.getString(4);
                DATOSL[4] = rs.getString(5);
                DATOSL[5] = rs.getString(6);
                DATOSL[6] = rs.getString(7);
                TBBarmans.addRow(DATOSL);
            }
            return TBBarmans;
        } 
        catch (SQLException ex) 
        {
            //Logger.getLogger(Restaurante_Proyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public TableModel obtenerTDatosCocineros(String valor) 
    {
        DefaultTableModel TBCocineros = new DefaultTableModel()
        {
            @Override
            public boolean isCellEditable(int filas, int columnas)
            {
                return false;
            }
        };
        TBCocineros.addColumn("ID_COCINERO");
        TBCocineros.addColumn("NOMBRE");
        TBCocineros.addColumn("APELLIDOS");
        TBCocineros.addColumn("FECHA NAC");
        TBCocineros.addColumn("FECHA IN");
        TBCocineros.addColumn("USUARIO");
        TBCocineros.addColumn("CONTRASEÑA");
        String sql = "";
        if (valor.equals("")) 
        {
            sql = "SELECT * FROM cocineros";
        } 
        else 
        {
            sql = "SELECT * FROM cocineros WHERE id_cocineros='"+valor+"' OR nombre_cocineros='" +valor+" '";
        }

        String[] DATOSL = new String[7];
        try 
        {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                DATOSL[0] = rs.getString(1);
                DATOSL[1] = rs.getString(2);
                DATOSL[2] = rs.getString(3);
                DATOSL[3] = rs.getString(4);
                DATOSL[4] = rs.getString(5);
                DATOSL[5] = rs.getString(6);
                DATOSL[6] = rs.getString(7);
                TBCocineros.addRow(DATOSL);
            }
            return TBCocineros;
        } 
        catch (SQLException ex) 
        {
            //Logger.getLogger(Restaurante_Proyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public TableModel obtenerTDatosMeseros(String valor) 
    {
        DefaultTableModel TBMeseros = new DefaultTableModel()
        {
            @Override
            public boolean isCellEditable(int filas, int columnas)
            {
                return false;
            }
        };
        TBMeseros.addColumn("ID_MESERO");
        TBMeseros.addColumn("NOMBRE");
        TBMeseros.addColumn("APELLIDOS");
        TBMeseros.addColumn("FECHA NAC");
        TBMeseros.addColumn("FECHA IN");
        TBMeseros.addColumn("USUARIO");
        TBMeseros.addColumn("CONTRASEÑA");
        String sql = "";
        if (valor.equals("")) 
        {
            sql = "SELECT * FROM meseros";
        } 
        else 
        {
            sql = "SELECT * FROM meseros WHERE id_meseros='"+valor+"' OR nombre_meseros='" +valor+" '";
        }

        String[] DATOSL = new String[7];
        try 
        {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                DATOSL[0] = rs.getString(1);
                DATOSL[1] = rs.getString(2);
                DATOSL[2] = rs.getString(3);
                DATOSL[3] = rs.getString(4);
                DATOSL[4] = rs.getString(5);
                DATOSL[5] = rs.getString(6);
                DATOSL[6] = rs.getString(7);
                TBMeseros.addRow(DATOSL);
            }
            return TBMeseros;

        } 
        catch (SQLException ex) 
        {
            //Logger.getLogger(Restaurante_Proyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    

//////////METODOS////////////////////////////////////////
    
    public String obtenerIDMesero(String valor) 
    {       
        String obtenido="";
        String sql="";
        try 
        {
            sql = "SELECT * FROM meseros WHERE id_meseros='"+valor+"' OR nombre_meseros='" +valor+" '";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) 
            {
                obtenido = rs.getString(1);
            }
            return obtenido;

        } 
        catch (SQLException ex) 
        {
            //Logger.getLogger(Restaurante_Proyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public String obtenerUsuarioMesero(String valor) 
    {       
        String obtenido="";
        String sql="";
        try 
        {
            sql = "SELECT * FROM meseros WHERE id_meseros='"+valor+"' OR nombre_meseros='" +valor+" '";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) 
            {
                obtenido = rs.getString(6);
            }
            return obtenido;

        } 
        catch (SQLException ex) 
        {
            //Logger.getLogger(Restaurante_Proyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public String obtenerPassMesero(String valor) 
    {       
        String obtenido="";
        String sql="";
        try 
        {
            sql = "SELECT * FROM meseros WHERE id_meseros='"+valor+"' OR nombre_meseros='" +valor+" '";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) 
            {
                obtenido = rs.getString(7);
            }
            return obtenido;

        } 
        catch (SQLException ex) 
        {
            //Logger.getLogger(Restaurante_Proyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    
    public void insertIntoProductos(String datos[]) 
    {
        try 
        {
            PreparedStatement pst = cn.prepareStatement("INSERT INTO " + datos[4] + " (nombre_" + datos[5] + ",descripcion_" + datos[5] + ",precio_" + datos[5] + ",url_imagen_" + datos[5] + ") VALUES (?,?,?,?)");
            pst.setString(1, datos[0]);
            pst.setString(2, datos[1]);
            pst.setString(3, datos[2]);
            pst.setString(4, datos[3]);
            pst.executeUpdate();
            
        } 
        catch (Exception ex) 
        {
            System.out.print(ex.getMessage());
        }
    }
    
    public void insertIntoProductosBebidas(String datos[]) 
    {
        try 
        {
            PreparedStatement pst = cn.prepareStatement("INSERT INTO " + datos[4] + " (nombre_bebidas_" + datos[5] + ",descripcion_bebidas_" + datos[5] + ",precio_bebidas_" + datos[5] + ",url_imagen_bebidas_" + datos[5] + ") VALUES (?,?,?,?)");            
            pst.setString(1, datos[0]);
            pst.setString(2, datos[1]);
            pst.setString(3, datos[2]);
            pst.setString(4, datos[3]);
            pst.executeUpdate();
            
        } 
        catch (Exception ex) 
        {
            System.out.print(ex.getMessage());
        }
    }
    
    public void insertIntoProductosBebidasA(String datos[]) 
    {
        try 
        {
            PreparedStatement pst = cn.prepareStatement("INSERT INTO " + datos[5] + " (nombre_bebidas_" + datos[6] + ",descripcion_bebidas_" + datos[6] + ",precio_bebidas_" + datos[6] + ",tipo_bebidas_" + datos[6] + ",url_imagen_bebidas_" + datos[6] + ") VALUES (?,?,?,?,?)");
            pst.setString(1, datos[0]);
            pst.setString(2, datos[1]);
            pst.setString(3, datos[2]);
            pst.setString(4, datos[3]);
            pst.setString(5, datos[4]);
            pst.executeUpdate();
            
        } 
        catch (Exception ex) 
        {
            System.out.print(ex.getMessage());
        }
    }
    
    public void insetIntoUsuarios(String[] datos)
    {
        
        try 
        {
            PreparedStatement pst = cn.prepareStatement("INSERT INTO " + datos[6] + " (nombre_" + datos[7] + ",apellidos_" + datos[7] + ",fecha_nac_" + datos[7] + ",fecha_in_" + datos[7] + ",nombre_usuario_" + datos[7] + ",contraseña_" + datos[7] + ") VALUES (?,?,?,?,?,?)");

            pst.setString(1, datos[0]);
            pst.setString(2, datos[1]);
            pst.setString(3, datos[2]);
            pst.setString(4, datos[3]);
            pst.setString(5, datos[4]);
            pst.setString(6, datos[5]);
            pst.executeUpdate();
        } 
        catch (Exception ex) 
        {
            System.out.print(ex.getMessage());
        }
    }
    
    public void updateProductos(String[] datos)
    {
        try {
            PreparedStatement pst = cn.prepareStatement("UPDATE " + datos[4] + " SET nombre_" + datos[5] + "=?, descripcion_" + datos[5] + "=? ,precio_" + datos[5] + "=?, url_imagen_" + datos[5] + "=? WHERE id_" + datos[4] + "='" + datos[6] + "'");
            pst.setString(1, datos[0]);
            pst.setString(2, datos[1]);
            pst.setString(3, datos[2]);
            pst.setString(4, datos[3]);
            pst.executeUpdate();
            
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
    }
    public void updateProductosBebidas(String[] datos)
    {
        try {
            PreparedStatement pst = cn.prepareStatement("UPDATE " + datos[4] + " SET nombre_bebidas_" + datos[5] + "=?, descripcion_bebidas_" + datos[5] + "=?, precio_bebidas_" + datos[5] + "=? ,url_imagen_bebidas_" + datos[5] + "=? WHERE id_bebidas_" + datos[4] + "='" + datos[6] + "'");
            pst.setString(1, datos[0]);
            pst.setString(2, datos[1]);
            pst.setString(3, datos[2]);
            pst.setString(4, datos[3]);
            pst.executeUpdate();
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
    }

    public void updateProductosBebidasA(String[] datos)
    {
        try {
            PreparedStatement pst = cn.prepareStatement("UPDATE " + datos[5] + " SET nombre_bebidas_" + datos[6] + "=?, descripcion_bebidas_" + datos[6] + "=?, precio_bebidas_" + datos[6] + "=?, tipo_bebidas_" + datos[6] + "=?, url_imagen_bebidas_" + datos[6] + "=?  WHERE id_bebidas_" + datos[6] + "='" + datos[7] + "'");
            pst.setString(1, datos[0]);
            pst.setString(2, datos[1]);
            pst.setString(3, datos[2]);
            pst.setString(4, datos[3]);
            pst.setString(5, datos[4]);
            pst.executeUpdate();
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
    }
    
    public void updateUsuarios(String[] datos)
    {
        try {
            PreparedStatement pst = cn.prepareStatement("UPDATE " + datos[6]+ " SET nombre_" + datos[7] + "=?, apellidos_" + datos[7] + "=?, fecha_nac_" + datos[7] + "=?, fecha_in_" + datos[7] + "=?, nombre_usuario_" + datos[7] + "=?, contraseña_" + datos[7] + "=? WHERE id_" + datos[6] + "='" + datos[8] + "'");
            pst.setString(1, datos[0]);
            pst.setString(2, datos[1]);
            pst.setString(3, datos[2]);
            pst.setString(4, datos[3]);
            pst.setString(5, datos[4]);
            pst.setString(6, datos[5]);
            pst.executeUpdate();
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
    }
    
    public void  deleteProdUsua(String datos[])
    {
        try 
        {
            PreparedStatement pst = cn.prepareStatement("DELETE FROM " + datos[0] + " WHERE  id_" + datos[1] + "='" + datos[2] + "'");
            pst.executeUpdate();

        } 
        catch (Exception ex) 
        {

        }
    }
    
     public void  deleteProdBebidas(String datos[])
    {
        try 
        {
            PreparedStatement pst = cn.prepareStatement("DELETE FROM " + datos[0] + " WHERE  id_bebidas_" + datos[1] + "='" + datos[2] + "'");
            pst.executeUpdate();

        } 
        catch (Exception ex) 
        {

        }
    }
     
    public int obtenerNMesas() 
    {               
        String sql = "";
        sql = "SELECT * FROM mesas WHERE id_mesas='"+1+"'";
        int nMesas =0;
        String[] DATOSL = new String[2];
        try 
        {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) 
            {
                nMesas = rs.getInt(2);
                System.out.println(""+nMesas);
            }
            return nMesas;           
        } 
        catch (SQLException ex) 
        {
            //Logger.getLogger(Restaurante_Proyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
   
    public int getNumMeseros() 
    {
        ResultSet res;
        int nRegistros=0;
        try 
        {
            Statement st = cn.createStatement();                     
            res = st.executeQuery("SELECT COUNT(*) AS id_meseros from meseros;");
            if (res.next()) 
            {
                nRegistros = Integer.parseInt(res.getString("id_meseros"));
            } else {
                nRegistros = 0;
            }
        } catch (SQLException ex) {
            //Logger.getLogger(Restaurante_Proyecto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nRegistros;
    }
    
}
