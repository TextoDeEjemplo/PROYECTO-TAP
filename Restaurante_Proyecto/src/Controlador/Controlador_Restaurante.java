/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Mesa;
import rsscalelabel.RSScaleLabel;
import Vista.Ventana_Meseros;
import Vista.Ventana_Administrador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import restaurante_proyecto.Restaurante_Proyecto;
import Modelo.Modelo;
import Vista.Ventana_Caja;
import Vista.Ventana_Cocina;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Calendar;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 *
 * @author RodrigoLP
 */
public class Controlador_Restaurante implements ActionListener , MouseListener
                                                        
{
    
    
    String idProd;
    String idUsua;
    String nameBD;
    String completado;
    String nameBDU;
    String completadoU;
    Boolean platillosFlag, bebidasFlag, postresFlag, cajerosFlag, barmansFlag, cocinerosFlag, meserosFlag, bebidasAFlag, bebidasFFlag, bebidasCFlag;
    Boolean banderaAgregarP, banderaActualizarP, banderaAgregarU, banderaActualizarU, banderaAgregarBA, banderaActualizarBA, banderaAgregarBF, banderaActualizarBF, banderaAgregarBC, banderaActualizarBC;
    Boolean banderaProductos, banderaUsuarios;
    
    
    /////COLORES
    
    Color mesaLibre = new Color(38, 192, 128);
    Color mesaOcupada = new Color(207, 44, 75);
    Color mesaAtendiendo= new Color(108, 183, 240);
    Color bordeMesa = new Color(255, 255, 255 );
    
    int numeroMesero=0;
    
    int[][] meserosControlador;
    String num_mesa;
    
    String [] id_Platillos;
    String variable="";
    
    /////////TABLA PEDIDO////////////
    int contadorDeOrdenes=0;
    DefaultTableModel TBPedido;
    
    ////////GENERAR BOTONES PLATILLOS/////////////////////////
    int contgBP;
    
    TableModel TablaBPlatillos;
    JButton[] botonesPlatillos;
    boolean banderaBP = true;
    boolean banderaGenerarP=false;
    
    ////////GENERAR BOTONES BEBIDAS ALCOHOLICAS/////////////////////////
    int contgBBA;
    
    TableModel TablaBBebidasAlcoholicas;
    JButton[] botonesBebidasAlcoholicas;
    boolean banderaBBA = true;
    boolean banderaGenerarBA=false;
    
     ////////GENERAR BOTONES BEBIDAS FRIAS/////////////////////////
    int contgBBF;
    
    TableModel TablaBBebidasFrias;
    JButton[] botonesBebidasFrias;
    boolean banderaBBF = true;
    boolean banderaGenerarBF=false;
    
     ////////GENERAR BOTONES BEBIDAS CALIENTES/////////////////////////
    int contgBBC;
    
    TableModel TablaBBebidasCalientes;
    JButton[] botonesBebidasCalientes;
    boolean banderaBBC = true;
    boolean banderaGenerarBC=false;
    
     ////////GENERAR BOTONES POSTRES/////////////////////////
    int contgBPO;
    
    TableModel TablaBPostres;
    JButton[] botonesPostres;
    boolean banderaBPo = true;
    boolean banderaGenerarPo=false;
    
    //////////////////////////////////////////////////////////
    
    /////GENERAR BOTONES MESAS/////////////////////////////////
    int contgBM;
    JButton[] botonesMesas;
    Mesa[] objetoMesas;
    boolean banderaBM = true;
    int nMesas;
    
    boolean btnRojos = false;
    boolean btnAzules = false;
    
    boolean banderaGenerar;

    
    //////////////////////////////////////////////////////////
    
    private Ventana_Meseros vista_meseros;
    private Ventana_Administrador vista_administrador;
    private Ventana_Cocina vista_cocina;
    private Ventana_Caja vista_caja;
    private Modelo modelo;
   
    Restaurante_Proyecto cc= new Restaurante_Proyecto();
    Connection cn= cc.conexion();
    
    public Controlador_Restaurante(Ventana_Meseros ventana_meseros, Ventana_Administrador ventana_administrador, Ventana_Cocina vista_cocina, Ventana_Caja vista_caja, Modelo model) 
    {        
        meserosControlador = new int[modelo.getNumMeseros()][2];//nmesa, nmesero
        //nmeseros
        //1,2
        //num_mesa = meserosControlmador[numMesero][0]
        
        this.vista_meseros = ventana_meseros;
        this.vista_administrador = ventana_administrador;
        this.vista_cocina = vista_cocina;
        this.vista_caja = vista_caja; 
        this.modelo = model;
        
        vista_administrador.getNombreT().setEditable(false);
        vista_administrador.getDescripcionT().setEditable(false);
        vista_administrador.getPrecioT().setEditable(false);
        vista_administrador.getURLT().setEditable(false);
        vista_administrador.getBtnAceptar().setEnabled(false);
        vista_administrador.getBtnCancelar().setEnabled(false); 
        vista_administrador.getBtnIMG().setEnabled(false); 
        vista_administrador.getLabelIMG().setIcon(null); 
        banderaAgregarP = false;
        
        vista_administrador.getNombreU().setEditable(false);
        vista_administrador.getApelidoU().setEditable(false);
        vista_administrador.getFechaNaU().setEditable(false);
        vista_administrador.getFechaInU().setEditable(false);
        vista_administrador.getNameUsuario().setEditable(false);
        vista_administrador.getContraseñaU().setEditable(false);

        vista_administrador.getBtnAceptarU().setEnabled(false);
        vista_administrador.getBtnCancelarU().setEnabled(false);
        banderaAgregarU = false;
        
        vista_administrador.getBtnAgregar().setEnabled(false);
        vista_administrador.getBtnActualizar().setEnabled(false);
        vista_administrador.getBtnEliminar().setEnabled(false);
        vista_administrador.getTxtBuscar().setEnabled(false);
        vista_administrador.getBtnBuscar().setEnabled(false);
                       
        vista_administrador.getNombreTBA().setText("");
        vista_administrador.getDescripcionTBA().setText("");
        vista_administrador.getPrecioTBA().setText("");
        vista_administrador.getURLTBA().setText("");
            
        vista_administrador.getNombreTBA().setEditable(false);
        vista_administrador.getDescripcionTBA().setEditable(false);
        vista_administrador.getPrecioTBA().setEditable(false);
        vista_administrador.getTipoAlcohol().setEditable(false);
        vista_administrador.getTipoAlcohol().setEnabled(false);
        vista_administrador.getURLTBA().setEditable(false);
        vista_administrador.getBtnAceptarBA().setEnabled(false);
        vista_administrador.getBtnCancelarBA().setEnabled(false);
        vista_administrador.getBtnIMGBA().setEnabled(false);
        vista_administrador.getLabelIMGBA().setIcon(null);

        
        ///////VENTANA MESEROS
        vista_meseros.getBtnMenuPlatillos().setEnabled(false);
        vista_meseros.getComboBoxMenuBebidas().setEnabled(false);
        vista_meseros.getBtnMenuPostres().setEnabled(false);
        vista_meseros.getBtnMenuBuscar().setEnabled(false);
        vista_meseros.getButtonEliminar().setEnabled(false);
        vista_meseros.getButtonEnviar().setEnabled(false);
        
        
                
                generarBotonesPlatillos();         
                generarBotonesBebidasAlcohilicas();
                generarBotonesBebidasFrias();
                generarBotonesBebidasCalientes();
                generarBotonesPostres();
                nMesas = modelo.obtenerNMesas();
        
                
        System.out.println(modelo.getNumMeseros());
        generarListaMeseros();
        
        String seleccionado = vista_meseros.getNMeseroIn().getSelectedItem().toString();           
        vista_meseros.getMostrarNomUsuario().setText(modelo.obtenerUsuarioMesero(seleccionado));
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        String comando = e.getActionCommand();


//////////////////////VENTANA MESEROS//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////     
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //INICIO MESEROS   
        if (comando=="SALIRTOP")
        {
            System.exit(0);            
                    
        }
        else if (comando=="INICIAR")
        {
            String seleccionado = vista_meseros.getNMeseroIn().getSelectedItem().toString();
            String pass = modelo.obtenerPassMesero(seleccionado);
            System.out.println("Usuario: "+seleccionado);
            System.out.println("Contrasena: "+pass);
            
            if (vista_meseros.getContraseñaM().getText().equals(pass)) 
            {
                vista_meseros.getPanelMeseros().setVisible(false);
                vista_meseros.getPanelMesas().setVisible(true);
                vista_meseros.validate();
                TBPedido = new DefaultTableModel() 
                {
                    @Override
                    public boolean isCellEditable(int filas, int columnas) 
                    {
                        return false;
                    }
                };
                TBPedido.addColumn("ID");
                TBPedido.addColumn("Nombre");
                TBPedido.addColumn("Precio");
                TBPedido.addColumn("Cant");
                TBPedido.addColumn("Total");
                vista_meseros.getTablaPedido().setModel(TBPedido);
                TableColumnModel columnModel = vista_meseros.getTablaPedido().getColumnModel();
                columnModel.getColumn(0).setPreferredWidth(15);
                columnModel.getColumn(1).setPreferredWidth(100);
                columnModel.getColumn(2).setPreferredWidth(100);
                columnModel.getColumn(3).setPreferredWidth(20);
                columnModel.getColumn(3).setPreferredWidth(20);
                
                //meserosControlador[Integer.parseInt(vista_meseros.getNMeseroIn().getSelectedItem().toString())][1];
                numeroMesero = Integer.parseInt(vista_meseros.getNMeseroIn().getSelectedItem().toString());
                if (banderaGenerar == false) 
                {
                    generarListaBtnMesas();
                    banderaGenerar = true;
                }

                if (banderaGenerarP == false) 
                {
                    imagenesBotonesPlatillos();
                    banderaGenerarP = true;
                }

                if (banderaGenerarBA == false) 
                {
                    imagenesBotonesBebidasAlcohilicas();
                    banderaGenerarBA = true;
                }

                if (banderaGenerarBF == false) 
                {
                    imagenesBotonesBebidasFrias();
                    banderaGenerarBF = true;
                }

                if (banderaGenerarBC == false) {
                    imagenesBotonesBebidasCalientes();
                    banderaGenerarBC = true;
                }

                if (banderaGenerarPo == false) 
                {
                    imagenesBotonesPostres();
                    banderaGenerarPo = true;
                }
                repintarMesas();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Contraseña Incorrecta");
            }
                 
        }
        else if(comando=="NUMEROMESERO")
        {    
            String seleccionado = vista_meseros.getNMeseroIn().getSelectedItem().toString();           
            vista_meseros.getMostrarNomUsuario().setText(modelo.obtenerUsuarioMesero(seleccionado));
        }
    //PANEL MESAS         
        else if(comando=="ENVIARCONFIRMAR")
        {            
            String[] botones2 = {"Enviar", "Cancelar",};
            String[] botones = {"Agregar otra", "Salir",};
            int variable2 = JOptionPane.showOptionDialog(null, "¿Estas seguro de enviar tu pedido a la cocina?", "Confirmacion", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, botones2, botones2[0]);            
            if (variable2 == 0) 
            {
                /////ENVIAR
                TableModel pedido;
                pedido=pedidoCocina(obtenerDatosTablaPedido());
                objetoMesas[Integer.parseInt(num_mesa)-1].setTotal(Double.parseDouble(vista_meseros.getTotalLabel().getText()));
                
                vista_cocina.nuevaorden(Integer.parseInt(num_mesa), contadorDeOrdenes+1, pedido);
                for(int i=0;i < nMesas; i++)
                {
                    botonesMesas[i].setEnabled(true);
                }
                
                int variable = JOptionPane.showOptionDialog(null, "¿Deseas agregar otra mesa?", "Confirmacion", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, botones, botones[0]);                        
                if(variable==0)
                {
                    //AgregarOtra
                    TBPedido.setRowCount(0);
                    
                }
                else if(variable==1)
                {
                    ////SALIR
                    vista_meseros.getPanelMeseros().setVisible(true);
                    vista_meseros.getPanelMesas().setVisible(false);
                    vista_meseros.getContraseñaM().setText("");
                  
                    vista_meseros.validate();
                }
                vista_meseros.getButtonSalirTb().setEnabled(true);
                vista_meseros.getTotalLabel().setText("$");
                
                

            } 
            else if (variable2 == 1) 
            {
                //CANCELAR
                
            }
            
            
            setPeidoaMesa(); 
            num_mesa = "";            
            vista_meseros.getBtnMenuPlatillos().setEnabled(false);
            vista_meseros.getComboBoxMenuBebidas().setEnabled(false);
            vista_meseros.getBtnMenuPostres().setEnabled(false);
            vista_meseros.getBtnMenuBuscar().setEnabled(false);
            
            vista_meseros.getPanelComida().setVisible(false);
            vista_meseros.getPanelBebidasAlcoholicas().setVisible(false);
            vista_meseros.getPanelBebidasFrias().setVisible(false);
            vista_meseros.getPanelBebidasCalientes().setVisible(false);
            vista_meseros.getPanelPostres().setVisible(false);
            vista_meseros.getPanelBuscado().setVisible(false);
            vista_meseros.getTotalLabel().setText("$");
            
            
            
                          
        }
        else if(comando=="ELIMINARFP")
        {
            TBPedido.removeRow(vista_meseros.getTablaPedido().getSelectedRow());
        }
        else if(comando=="SALIRBTN")
        {
            vista_meseros.getContraseñaM().setText("");
            num_mesa = "";            
            vista_meseros.getBtnMenuPlatillos().setEnabled(false);
            vista_meseros.getComboBoxMenuBebidas().setEnabled(false);
            vista_meseros.getBtnMenuPostres().setEnabled(false);
            vista_meseros.getBtnMenuBuscar().setEnabled(false);
            
            vista_meseros.getPanelComida().setVisible(false);
            vista_meseros.getPanelBebidasAlcoholicas().setVisible(false);
            vista_meseros.getPanelBebidasFrias().setVisible(false);
            vista_meseros.getPanelBebidasCalientes().setVisible(false);
            vista_meseros.getPanelPostres().setVisible(false);
            vista_meseros.getPanelBuscado().setVisible(false);
            
            vista_meseros.getPanelMeseros().setVisible(true);
            vista_meseros.getPanelMesas().setVisible(false);     
            String seleccionado = vista_meseros.getNMeseroIn().getSelectedItem().toString();           
            vista_meseros.getMostrarNomUsuario().setText(modelo.obtenerUsuarioMesero(seleccionado));
            
            vista_meseros.validate(); 
        }
        
        
    //PANEL MENU
        else if(comando=="COMIDA")
        {
            vista_meseros.getPanelComida().setVisible(true);
            vista_meseros.getPanelBebidasAlcoholicas().setVisible(false);
            vista_meseros.getPanelBebidasFrias().setVisible(false);
            vista_meseros.getPanelBebidasCalientes().setVisible(false);
            vista_meseros.getPanelPostres().setVisible(false);
            vista_meseros.getPanelBuscado().setVisible(false);
                                   
            vista_meseros.validate();                       

        } 
        else if(comando=="BEBIDASMENU")
        {      
            vista_administrador.getTabla().setVisible(false);

            if(vista_meseros.getComboBoxMenuBebidas().getSelectedItem().toString()=="BEBIDAS")
            {
                vista_meseros.getPanelComida().setVisible(false);
                vista_meseros.getPanelBebidasAlcoholicas().setVisible(false);
                vista_meseros.getPanelBebidasFrias().setVisible(false);
                vista_meseros.getPanelBebidasCalientes().setVisible(false);
                vista_meseros.getPanelPostres().setVisible(false);
                vista_meseros.getPanelBuscado().setVisible(false);
                                   
                vista_meseros.validate();   
            }           
            if(vista_meseros.getComboBoxMenuBebidas().getSelectedItem().toString()=="ALCOHOLICAS")
            {
                vista_meseros.getPanelComida().setVisible(false);
                vista_meseros.getPanelBebidasAlcoholicas().setVisible(true);
                vista_meseros.getPanelBebidasFrias().setVisible(false);
                vista_meseros.getPanelBebidasCalientes().setVisible(false);
                vista_meseros.getPanelPostres().setVisible(false);
                vista_meseros.getPanelBuscado().setVisible(false);
                                   
                vista_meseros.validate();   
            }            
            if(vista_meseros.getComboBoxMenuBebidas().getSelectedItem().toString()=="FRIAS")
            {
                vista_meseros.getPanelComida().setVisible(false);
                vista_meseros.getPanelBebidasAlcoholicas().setVisible(false);
                vista_meseros.getPanelBebidasFrias().setVisible(true);
                vista_meseros.getPanelBebidasCalientes().setVisible(false);
                vista_meseros.getPanelPostres().setVisible(false);
                vista_meseros.getPanelBuscado().setVisible(false);
                                   
                vista_meseros.validate();   
            }
            if(vista_meseros.getComboBoxMenuBebidas().getSelectedItem().toString()=="CALIENTES")
            {
                vista_meseros.getPanelComida().setVisible(false);
                vista_meseros.getPanelBebidasAlcoholicas().setVisible(false);
                vista_meseros.getPanelBebidasFrias().setVisible(false);
                vista_meseros.getPanelBebidasCalientes().setVisible(true);
                vista_meseros.getPanelPostres().setVisible(false);
                vista_meseros.getPanelBuscado().setVisible(false);
                                   
                vista_meseros.validate();   
            }
        
        }
///////////////////////////////////////////////////        
        
        
//////////////////////////////////////////        
        else if(comando=="POSTRES")
        {
            vista_meseros.getPanelComida().setVisible(false);
            vista_meseros.getPanelBebidasAlcoholicas().setVisible(false);
            vista_meseros.getPanelBebidasFrias().setVisible(false);
            vista_meseros.getPanelBebidasCalientes().setVisible(false);
            vista_meseros.getPanelPostres().setVisible(true);
            vista_meseros.getPanelBuscado().setVisible(false);
            vista_meseros.validate();

        }
        
        else if(comando=="BUSCARPLAT")
        {
            vista_meseros.getPanelComida().setVisible(false);
            vista_meseros.getPanelBebidasAlcoholicas().setVisible(false);
            vista_meseros.getPanelBebidasFrias().setVisible(false);
            vista_meseros.getPanelBebidasCalientes().setVisible(false);
            vista_meseros.getPanelPostres().setVisible(false);
            vista_meseros.getPanelBuscado().setVisible(true);
            
            vista_meseros.validate();

        }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////     
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////            

//////////////////////VENTANA ADMINISTRADOR//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////     
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////     

        else if (comando=="SALIRTOPA")
        {
            System.exit(0);            
                    
        }
        else if(comando=="PRODUCTOS")
        {
            banderaProductos = true;
            banderaUsuarios = false;
            bebidasAFlag = false;
            bebidasFFlag = false;
            bebidasCFlag = false;
            vista_administrador.getPanelAP().setVisible(true);
            vista_administrador.getPanelAU().setVisible(false);

            vista_administrador.getPanelAPR().setVisible(true);
            vista_administrador.getPanelAUR().setVisible(false);

            vista_administrador.getNombreT().setEditable(false);
            vista_administrador.getDescripcionT().setEditable(false);
            vista_administrador.getPrecioT().setEditable(false);
            vista_administrador.getURLT().setEditable(false);
            vista_administrador.getBtnAceptar().setEnabled(false);
            vista_administrador.getBtnCancelar().setEnabled(false);
            vista_administrador.getBtnIMG().setEnabled(false); 
            vista_administrador.getLabelIMG().setIcon(null);

            vista_administrador.getNombreU().setEditable(false);
            vista_administrador.getApelidoU().setEditable(false);
            vista_administrador.getFechaNaU().setEditable(false);
            vista_administrador.getFechaInU().setEditable(false);
            vista_administrador.getNameUsuario().setEditable(false);
            vista_administrador.getContraseñaU().setEditable(false);

            vista_administrador.getBtnAceptarU().setEnabled(false);
            vista_administrador.getBtnCancelarU().setEnabled(false);

            vista_administrador.getBtnAgregar().setEnabled(false);
            vista_administrador.getBtnActualizar().setEnabled(false);
            vista_administrador.getBtnEliminar().setEnabled(false);
            vista_administrador.getTxtBuscar().setEnabled(false);
            vista_administrador.getBtnBuscar().setEnabled(false);

            vista_administrador.getNombreTBA().setEditable(false);
            vista_administrador.getDescripcionTBA().setEditable(false);
            vista_administrador.getPrecioTBA().setEditable(false);
            vista_administrador.getTipoAlcohol().setEnabled(false);
            vista_administrador.getURLTBA().setEditable(false);
            vista_administrador.getBtnAceptarBA().setEnabled(false);
            vista_administrador.getBtnCancelarBA().setEnabled(false);
            vista_administrador.getBtnIMGBA().setEnabled(false);
            vista_administrador.getLabelIMGBA().setIcon(null);

            vista_administrador.getTabla().setVisible(false);               
        }

        else if(comando=="USUARIOS")
        {
            banderaUsuarios = true;
            banderaProductos = false;
            bebidasAFlag = false;
            bebidasFFlag = false;
            bebidasCFlag = false;
            vista_administrador.getPanelAP().setVisible(false);
            vista_administrador.getPanelAU().setVisible(true);

            vista_administrador.getPanelAPR().setVisible(false);
            vista_administrador.getPanelAUR().setVisible(true);


            vista_administrador.getNombreT().setEditable(false);
            vista_administrador.getDescripcionT().setEditable(false);
            vista_administrador.getPrecioT().setEditable(false);
            vista_administrador.getURLT().setEditable(false);
            vista_administrador.getBtnAceptar().setEnabled(false);
            vista_administrador.getBtnCancelar().setEnabled(false);
            vista_administrador.getBtnIMG().setEnabled(false); 
            vista_administrador.getLabelIMG().setIcon(null);

            vista_administrador.getNombreU().setEditable(false);
            vista_administrador.getApelidoU().setEditable(false);
            vista_administrador.getFechaNaU().setEditable(false);
            vista_administrador.getFechaInU().setEditable(false);
            vista_administrador.getNameUsuario().setEditable(false);
            vista_administrador.getContraseñaU().setEditable(false);

            vista_administrador.getBtnAceptarU().setEnabled(false);
            vista_administrador.getBtnCancelarU().setEnabled(false);

            vista_administrador.getBtnAgregar().setEnabled(false);
            vista_administrador.getBtnActualizar().setEnabled(false);
            vista_administrador.getBtnEliminar().setEnabled(false);
            vista_administrador.getTxtBuscar().setEnabled(false);
            vista_administrador.getBtnBuscar().setEnabled(false);

            vista_administrador.getTabla().setVisible(false);


        }

   ////////////ADMIN PRODUCTOS//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////     

        else if(comando=="PLATILLOST")
        {               
            mostrarDatosPlatillos("");
            platillosFlag = true;
            bebidasAFlag = false;
            bebidasFFlag = false;
            bebidasCFlag = false;
            postresFlag = false;
            banderaProductos = true; 

            vista_administrador.getBtnAgregar().setEnabled(true);
            vista_administrador.getBtnActualizar().setEnabled(true);
            vista_administrador.getBtnEliminar().setEnabled(true);
            vista_administrador.getTxtBuscar().setEnabled(true);
            vista_administrador.getBtnBuscar().setEnabled(true);

            vista_administrador.getTabla().setVisible(true);

            vista_administrador.getPaneBebidasAlcoholicas().setVisible(false);
            vista_administrador.getPanelAPR().setVisible(true);
            vista_administrador.getPanelAUR().setVisible(false);

            vista_administrador.getNombreT().setText("");
            vista_administrador.getDescripcionT().setText("");
            vista_administrador.getPrecioT().setText("");
            vista_administrador.getURLT().setText("");

        }

        else if(comando=="BEBIDASME")
        {      
            vista_administrador.getTabla().setVisible(false);

            if(vista_administrador.getBebidasMenu().getSelectedItem().toString()=="BEBIDAS")
            {
                vista_administrador.getPaneBebidasAlcoholicas().setVisible(false);
                vista_administrador.getPanelAPR().setVisible(false);
                vista_administrador.getPanelAUR().setVisible(false);
                vista_administrador.getTabla().setVisible(false);
                banderaUsuarios = false;
                banderaProductos = false;                   
                bebidasAFlag = false;
                bebidasFFlag = false;
                bebidasCFlag = false;
                platillosFlag = false;
                bebidasAFlag = false;
            }
            if(vista_administrador.getBebidasMenu().getSelectedItem().toString()=="ALCOHOLICAS")
            {
                vista_administrador.getPaneBebidasAlcoholicas().setVisible(true);
                vista_administrador.getPanelAPR().setVisible(false);
                vista_administrador.getPanelAUR().setVisible(false);
                banderaUsuarios = false;
                banderaProductos = false;
                bebidasAFlag = true;
                bebidasFFlag = false;
                bebidasCFlag = false;
                platillosFlag = false;
                postresFlag = false;
                mostrarDatosBebidasA("");

            }
            else if(vista_administrador.getBebidasMenu().getSelectedItem().toString()=="FRIAS")
            {
                vista_administrador.getPaneBebidasAlcoholicas().setVisible(false);
                vista_administrador.getPanelAPR().setVisible(true);
                vista_administrador.getPanelAUR().setVisible(false);
                banderaUsuarios = false;
                banderaProductos = false;
                bebidasAFlag = false;
                bebidasFFlag = true;
                bebidasCFlag = false;
                platillosFlag = false;
                postresFlag = false;
                mostrarDatosBebidasF("");
            }
            else if(vista_administrador.getBebidasMenu().getSelectedItem().toString()=="CALIENTES")
            {
                vista_administrador.getPaneBebidasAlcoholicas().setVisible(false);
                vista_administrador.getPanelAPR().setVisible(true);
                vista_administrador.getPanelAUR().setVisible(false);
                banderaUsuarios = false;
                banderaProductos = false;
                bebidasAFlag = false;
                bebidasFFlag = false;
                bebidasCFlag = true;
                platillosFlag = false;
                postresFlag = false;
                mostrarDatosBebidasC("");
            }
            //mostrarDatosBebidas("");


            vista_administrador.getBtnAgregar().setEnabled(true);
            vista_administrador.getBtnActualizar().setEnabled(true);
            vista_administrador.getBtnEliminar().setEnabled(true);
            vista_administrador.getTxtBuscar().setEnabled(true);
            vista_administrador.getBtnBuscar().setEnabled(true);

            vista_administrador.getTabla().setVisible(true);

        }

        else if(comando=="POSTREST") 
        {
            mostrarDatosPostres("");    
            platillosFlag = false;
            bebidasAFlag = false;
            bebidasFFlag = false;
            bebidasCFlag = false;
            postresFlag = true;
            banderaProductos = true;  


            vista_administrador.getBtnAgregar().setEnabled(true);
            vista_administrador.getBtnActualizar().setEnabled(true);
            vista_administrador.getBtnEliminar().setEnabled(true);
            vista_administrador.getTxtBuscar().setEnabled(true);
            vista_administrador.getBtnBuscar().setEnabled(true);

            vista_administrador.getTabla().setVisible(true);

            vista_administrador.getPaneBebidasAlcoholicas().setVisible(false);
            vista_administrador.getPanelAPR().setVisible(true);
            vista_administrador.getPanelAUR().setVisible(false);

            vista_administrador.getNombreT().setText("");
            vista_administrador.getDescripcionT().setText("");
            vista_administrador.getPrecioT().setText("");
            vista_administrador.getURLT().setText("");

        }

   ////////////ADMIN USUARIOS//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////     

        else if(comando=="CAJEROS") 
        {               
            mostrarDatosCajeros("");
            cajerosFlag = true;
            barmansFlag = false;
            cocinerosFlag = false;
            meserosFlag = false;
            banderaUsuarios = true;

            vista_administrador.getBtnAgregar().setEnabled(true);
            vista_administrador.getBtnActualizar().setEnabled(true);
            vista_administrador.getBtnEliminar().setEnabled(true);
            vista_administrador.getTxtBuscar().setEnabled(true);
            vista_administrador.getBtnBuscar().setEnabled(true);

            vista_administrador.getTabla().setVisible(true);

        }

        else if(comando=="BARMANS") 
        {
            mostrarDatosBarmans("");
            cajerosFlag = false;
            barmansFlag = true;
            cocinerosFlag = false;
            meserosFlag = false;
            banderaUsuarios = true;

            vista_administrador.getBtnAgregar().setEnabled(true);
            vista_administrador.getBtnActualizar().setEnabled(true);
            vista_administrador.getBtnEliminar().setEnabled(true);
            vista_administrador.getTxtBuscar().setEnabled(true);
            vista_administrador.getBtnBuscar().setEnabled(true);

            vista_administrador.getTabla().setVisible(true);

        }

        else if(comando== "COCINEROS")
        {
            mostrarDatosCocineros("");
            cajerosFlag = false;
            barmansFlag = false;
            cocinerosFlag = true;
            meserosFlag = false;
            banderaUsuarios = true;

            vista_administrador.getBtnAgregar().setEnabled(true);
            vista_administrador.getBtnActualizar().setEnabled(true);
            vista_administrador.getBtnEliminar().setEnabled(true);
            vista_administrador.getTxtBuscar().setEnabled(true);
            vista_administrador.getBtnBuscar().setEnabled(true);

            vista_administrador.getTabla().setVisible(true);

        }       

        else if(comando=="MESEROS") 
        {
            mostrarDatosMeseros("");
            cajerosFlag = false;
            barmansFlag = false;
            cocinerosFlag = false;
            meserosFlag = true;
            banderaUsuarios = true;

            vista_administrador.getBtnAgregar().setEnabled(true);
            vista_administrador.getBtnActualizar().setEnabled(true);
            vista_administrador.getBtnEliminar().setEnabled(true);
            vista_administrador.getTxtBuscar().setEnabled(true);
            vista_administrador.getBtnBuscar().setEnabled(true);

            vista_administrador.getTabla().setVisible(true);

        }    

/////////////////////////////////////  PANEL REGISTRO PROD
        else if(comando=="SELECTIMG")
        {
            JFileChooser fc = new JFileChooser();
            fc.setDialogTitle("Buscar Imagen");
            //fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            if(fc.showOpenDialog(vista_administrador)==JFileChooser.APPROVE_OPTION)
            {
                ImageIcon fot = new ImageIcon(fc.getSelectedFile().getAbsolutePath());
                Icon icono = new ImageIcon(fot.getImage().getScaledInstance(vista_administrador.getLabelIMG().getWidth(), vista_administrador.getLabelIMG().getHeight(), Image.SCALE_DEFAULT));
                vista_administrador.getLabelIMG().setIcon(icono);
                vista_administrador.repaint();

                vista_administrador.getURLT().setText(fc.getSelectedFile().getAbsolutePath());
            }

        }

        else if(comando== "SELECTIMGBA")
        {
            JFileChooser fc = new JFileChooser();
            fc.setDialogTitle("Buscar Imagen");
            //fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            if(fc.showOpenDialog(vista_administrador)==JFileChooser.APPROVE_OPTION)
            {
                ImageIcon fot = new ImageIcon(fc.getSelectedFile().getAbsolutePath());
                Icon icono = new ImageIcon(fot.getImage().getScaledInstance(vista_administrador.getLabelIMGBA().getWidth(), vista_administrador.getLabelIMGBA().getHeight(), Image.SCALE_DEFAULT));
                vista_administrador.getLabelIMGBA().setIcon(icono);
                vista_administrador.repaint();

                vista_administrador.getURLTBA().setText(fc.getSelectedFile().getAbsolutePath());
            }

        }
   ////////////PANEL FORMULARIO PRODUCTOS//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////     

        else if(comando=="ACEPTAR")
        {                

            if (banderaAgregarP) 
            {
                if (JOptionPane.showConfirmDialog(null, "Estas seguro de agregar este Registro?", "Confirmar Registro", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) 
                {
                    String datos[] = new String[6];
                    datos[0]=vista_administrador.getNombreT().getText();
                    datos[1]=vista_administrador.getDescripcionT().getText();
                    datos[2]=vista_administrador.getPrecioT().getText();
                    datos[3]=vista_administrador.getURLT().getText();
                    datos[4] = nameBD;
                    datos[5] = completado;
                    modelo.insertIntoProductos(datos);
                    
                    
                    vista_administrador.getNombreT().setText("");
                    vista_administrador.getDescripcionT().setText("");
                    vista_administrador.getPrecioT().setText("");
                    vista_administrador.getURLT().setText("");

                    vista_administrador.getNombreT().setEditable(false);
                    vista_administrador.getDescripcionT().setEditable(false);
                    vista_administrador.getPrecioT().setEditable(false);
                    vista_administrador.getURLT().setEditable(false);
                    vista_administrador.getBtnAceptar().setEnabled(false);
                    vista_administrador.getBtnCancelar().setEnabled(false);
                    vista_administrador.getBtnIMG().setEnabled(false); 
                    vista_administrador.getLabelIMG().setIcon(null);
                    banderaAgregarP = false;
                    banderaActualizarP = false;

                    if (platillosFlag) 
                    {
                        mostrarDatosPlatillos("");
                    } else if (postresFlag) 
                    {
                        mostrarDatosPostres("");
                    }
                }

            }

            if (banderaAgregarBF) 
            {
                if (JOptionPane.showConfirmDialog(null, "Estas seguro de agregar este Registro?", "Confirmar Registro", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) 
                {
                    String datos[] = new String[6];
                    datos[0]=vista_administrador.getNombreT().getText();
                    datos[1]=vista_administrador.getDescripcionT().getText();
                    datos[2]=vista_administrador.getPrecioT().getText();
                    datos[3]=vista_administrador.getURLT().getText();
                    datos[4] = nameBD;
                    datos[5] = completado;
                    modelo.insertIntoProductosBebidas(datos);
                    
                    vista_administrador.getNombreT().setText("");
                    vista_administrador.getDescripcionT().setText("");
                    vista_administrador.getPrecioT().setText("");
                    vista_administrador.getURLT().setText("");

                    vista_administrador.getNombreT().setEditable(false);
                    vista_administrador.getDescripcionT().setEditable(false);
                    vista_administrador.getPrecioT().setEditable(false);
                    vista_administrador.getURLT().setEditable(false);
                    vista_administrador.getBtnAceptar().setEnabled(false);
                    vista_administrador.getBtnCancelar().setEnabled(false);
                    vista_administrador.getBtnIMG().setEnabled(false); 
                    vista_administrador.getLabelIMG().setIcon(null);
                    banderaAgregarP = false;
                    banderaActualizarP = false;
                    bebidasFFlag = false;
                    bebidasCFlag = false;

                    mostrarDatosBebidasF("");

                }

            }

            if (banderaAgregarBC)
            {
                if (JOptionPane.showConfirmDialog(null, "Estas seguro de agregar este Registro?", "Confirmar Registro", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) 
                {
                    String datos[] = new String[6];
                    datos[0]=vista_administrador.getNombreT().getText();
                    datos[1]=vista_administrador.getDescripcionT().getText();
                    datos[2]=vista_administrador.getPrecioT().getText();
                    datos[3]=vista_administrador.getURLT().getText();
                    datos[4] = nameBD;
                    datos[5] = completado;
                    modelo.insertIntoProductosBebidas(datos);
                    
                    vista_administrador.getNombreT().setText("");
                    vista_administrador.getDescripcionT().setText("");
                    vista_administrador.getPrecioT().setText("");
                    vista_administrador.getURLT().setText("");

                    vista_administrador.getNombreT().setEditable(false);
                    vista_administrador.getDescripcionT().setEditable(false);
                    vista_administrador.getPrecioT().setEditable(false);
                    vista_administrador.getURLT().setEditable(false);
                    vista_administrador.getBtnAceptar().setEnabled(false);
                    vista_administrador.getBtnCancelar().setEnabled(false);
                    vista_administrador.getBtnIMG().setEnabled(false); 
                    vista_administrador.getLabelIMG().setIcon(null);
                    banderaAgregarP = false;
                    banderaActualizarP = false;

                    mostrarDatosBebidasC("");

                }

            }               
            /////////////////////////////////////
            else if (banderaActualizarP) 
            {
                if (JOptionPane.showConfirmDialog(null, "Estas seguro de Actualizar este Registro?", "Confirmar Registro", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) 
                {
                    String datos[] = new String[7];
                    datos[0]=vista_administrador.getNombreT().getText();
                    datos[1]=vista_administrador.getDescripcionT().getText();
                    datos[2]=vista_administrador.getPrecioT().getText();
                    datos[3]=vista_administrador.getURLT().getText();
                    datos[4] = nameBD;
                    datos[5] = completado;
                    datos[6] = idProd;
                    modelo.updateProductos(datos);
                    
                    if (platillosFlag) 
                    {
                        mostrarDatosPlatillos("");
                    }else if (postresFlag) 
                    {
                        mostrarDatosPostres("");
                    }

                    vista_administrador.getNombreT().setText("");
                    vista_administrador.getDescripcionT().setText("");
                    vista_administrador.getPrecioT().setText("");
                    vista_administrador.getURLT().setText("");

                    vista_administrador.getNombreT().setEditable(false);
                    vista_administrador.getDescripcionT().setEditable(false);
                    vista_administrador.getPrecioT().setEditable(false);
                    vista_administrador.getURLT().setEditable(false);
                    vista_administrador.getBtnAceptar().setEnabled(false);
                    vista_administrador.getBtnCancelar().setEnabled(false);
                    vista_administrador.getBtnIMG().setEnabled(false); 
                    vista_administrador.getLabelIMG().setIcon(null);
                    banderaAgregarP = false;
                    banderaActualizarP = false;
                    banderaAgregarBA = false;
                    banderaActualizarBA = false;
                    banderaAgregarBF = false;
                    banderaActualizarBF = false;
                    banderaActualizarBC = false;
                    banderaAgregarBC = false;
                }
            }

            else if (banderaActualizarBF) 
            {
                if (JOptionPane.showConfirmDialog(null, "Estas seguro de Actualizar este Registro?", "Confirmar Registro", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) 
                {
                    
                    String datos[] = new String[7];
                    datos[0]=vista_administrador.getNombreT().getText();
                    datos[1]=vista_administrador.getDescripcionT().getText();
                    datos[2]=vista_administrador.getPrecioT().getText();
                    datos[3]=vista_administrador.getURLT().getText();
                    datos[4] = nameBD;
                    datos[5] = completado;
                    datos[6] = idProd;
                    
                    modelo.updateProductosBebidas(datos);
                    mostrarDatosBebidasF("");


                    vista_administrador.getNombreT().setText("");
                    vista_administrador.getDescripcionT().setText("");
                    vista_administrador.getPrecioT().setText("");
                    vista_administrador.getURLT().setText("");

                    vista_administrador.getNombreT().setEditable(false);
                    vista_administrador.getDescripcionT().setEditable(false);
                    vista_administrador.getPrecioT().setEditable(false);
                    vista_administrador.getURLT().setEditable(false);
                    vista_administrador.getBtnAceptar().setEnabled(false);
                    vista_administrador.getBtnCancelar().setEnabled(false);
                    vista_administrador.getBtnIMG().setEnabled(false); 
                    vista_administrador.getLabelIMG().setIcon(null);
                    banderaAgregarP = false;
                    banderaActualizarP = false;
                    banderaAgregarBA = false;
                    banderaActualizarBA = false;
                    banderaAgregarBF = false;
                    banderaActualizarBF = false;
                    banderaActualizarBC = false;
                    banderaAgregarBC = false;
                }
            }

            else if (banderaActualizarBC) 
            {
                if (JOptionPane.showConfirmDialog(null, "Estas seguro de Actualizar este Registro?", "Confirmar Registro", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) 
                {
                    String datos[] = new String[7];
                    datos[0]=vista_administrador.getNombreT().getText();
                    datos[1]=vista_administrador.getDescripcionT().getText();
                    datos[2]=vista_administrador.getPrecioT().getText();
                    datos[3]=vista_administrador.getURLT().getText();
                    datos[4] = nameBD;
                    datos[5] = completado;
                    datos[6] = idProd;
                    
                    modelo.updateProductosBebidas(datos);
                    mostrarDatosBebidasC("");

                    vista_administrador.getNombreT().setText("");
                    vista_administrador.getDescripcionT().setText("");
                    vista_administrador.getPrecioT().setText("");
                    vista_administrador.getURLT().setText("");

                    vista_administrador.getNombreT().setEditable(false);
                    vista_administrador.getDescripcionT().setEditable(false);
                    vista_administrador.getPrecioT().setEditable(false);
                    vista_administrador.getURLT().setEditable(false);
                    vista_administrador.getBtnAceptar().setEnabled(false);
                    vista_administrador.getBtnCancelar().setEnabled(false);
                    vista_administrador.getBtnIMG().setEnabled(false); 
                    vista_administrador.getLabelIMG().setIcon(null);
                    banderaAgregarP = false;
                    banderaActualizarP = false;
                    banderaAgregarBA = false;
                    banderaActualizarBA = false;
                    banderaAgregarBF = false;
                    banderaActualizarBF = false;
                    banderaActualizarBC = false;
                    banderaAgregarBC = false;
                }
            }      

        }

        else if(comando== "CANCELAR")
        {
            vista_administrador.getNombreT().setText("");
            vista_administrador.getDescripcionT().setText("");
            vista_administrador.getPrecioT().setText("");
            vista_administrador.getURLT().setText("");

            vista_administrador.getNombreT().setEditable(false);
            vista_administrador.getDescripcionT().setEditable(false);
            vista_administrador.getPrecioT().setEditable(false);
            vista_administrador.getURLT().setEditable(false);
            vista_administrador.getBtnAceptar().setEnabled(false);  
            vista_administrador.getBtnCancelar().setEnabled(false); 
            vista_administrador.getBtnIMG().setEnabled(false); 
            vista_administrador.getLabelIMG().setIcon(null);

        }

   ////////////PANEL FORMULARIO BEBIDAS ALCOHOLICAS//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////     
        else if(comando== "ACEPTARBA")
        {

             if (banderaAgregarBA) 
            {
                if (JOptionPane.showConfirmDialog(null, "Estas seguro de agregar este Registro?", "Confirmar Registro", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) 
                {
                    String datos[] = new String[8];
                   
                    datos[0] = vista_administrador.getNombreTBA().getText();
                    datos[1] = vista_administrador.getDescripcionTBA().getText();
                    datos[2] = vista_administrador.getPrecioTBA().getText();
                    datos[3] = vista_administrador.getTipoAlcohol().getSelectedItem().toString();
                    datos[4] = vista_administrador.getURLTBA().getText();                       
                    datos[5] = nameBD;
                    datos[6] = completado;
                    datos[7] = idProd;
                    
                    modelo.insertIntoProductosBebidasA(datos);
                    
                    vista_administrador.getNombreTBA().setText("");
                    vista_administrador.getDescripcionTBA().setText("");
                    vista_administrador.getPrecioTBA().setText("");
                    vista_administrador.getURLTBA().setText("");

                    vista_administrador.getNombreTBA().setEditable(false);
                    vista_administrador.getDescripcionTBA().setEditable(false);
                    vista_administrador.getPrecioTBA().setEditable(false);
                    vista_administrador.getTipoAlcohol().setEnabled(false);
                    vista_administrador.getURLTBA().setEditable(false);
                    vista_administrador.getBtnAceptarBA().setEnabled(false);
                    vista_administrador.getBtnCancelarBA().setEnabled(false);
                    vista_administrador.getBtnIMGBA().setEnabled(false); 
                    vista_administrador.getLabelIMGBA().setIcon(null);
                    banderaAgregarP = false;
                    banderaActualizarP = false;
                    mostrarDatosBebidasA("");

                }

            }
            else if (banderaActualizarBA) 
            {
                if (JOptionPane.showConfirmDialog(null, "Estas seguro de Actualizar este Registro?", "Confirmar Registro", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) 
                {
                    String datos[] = new String[8];
                   
                    datos[0] = vista_administrador.getNombreTBA().getText();
                    datos[1] = vista_administrador.getDescripcionTBA().getText();
                    datos[2] = vista_administrador.getPrecioTBA().getText();
                    datos[3] = vista_administrador.getTipoAlcohol().getSelectedItem().toString();
                    datos[4] = vista_administrador.getURLTBA().getText();                       
                    datos[5] = nameBD;
                    datos[6] = completado;
                    datos[7] = idProd;
                    
                    modelo.updateProductosBebidasA(datos);
                    mostrarDatosBebidasA("");

                    vista_administrador.getNombreTBA().setText("");
                    vista_administrador.getDescripcionTBA().setText("");
                    vista_administrador.getPrecioTBA().setText("");
                    vista_administrador.getURLTBA().setText("");

                    vista_administrador.getNombreTBA().setEditable(false);
                    vista_administrador.getDescripcionTBA().setEditable(false);
                    vista_administrador.getPrecioTBA().setEditable(false);
                    vista_administrador.getTipoAlcohol().setEnabled(false);
                    vista_administrador.getURLTBA().setEditable(false);
                    vista_administrador.getBtnAceptarBA().setEnabled(false);
                    vista_administrador.getBtnCancelarBA().setEnabled(false);
                    vista_administrador.getBtnIMGBA().setEnabled(false); 
                    vista_administrador.getLabelIMGBA().setIcon(null);
                    banderaAgregarP = false;
                    banderaActualizarP = false;
                    mostrarDatosBebidasA("");
                }
            }


        }

        else if(comando== "CANCELARBA")
        {
            vista_administrador.getNombreTBA().setText("");
            vista_administrador.getDescripcionTBA().setText("");
            vista_administrador.getPrecioTBA().setText("");
            vista_administrador.getURLTBA().setText("");

            vista_administrador.getNombreTBA().setEditable(false);
            vista_administrador.getDescripcionTBA().setEditable(false);
            vista_administrador.getPrecioTBA().setEditable(false);
            vista_administrador.getTipoAlcohol().setEnabled(false);
            vista_administrador.getURLTBA().setEditable(false);
            vista_administrador.getBtnAceptarBA().setEnabled(false);  
            vista_administrador.getBtnCancelarBA().setEnabled(false); 
            vista_administrador.getBtnIMGBA().setEnabled(false); 
            vista_administrador.getLabelIMGBA().setIcon(null);

        }
////////////PANEL FORMULARIO USUARIOS//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
        else if(comando== "ACEPTARU") 
        {                              
            if (banderaAgregarU) 
            {
                if (JOptionPane.showConfirmDialog(null, "Estas seguro de Agregar este Registro?", "Confirmar Registro", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) 
                {
                    
                    String datos[] = new String[9];
                   
                    datos[0] = vista_administrador.getNombreU().getText();
                    datos[1] = vista_administrador.getApelidoU().getText();
                    datos[2] = vista_administrador.getFechaNaU().getText();
                    datos[3] = vista_administrador.getFechaInU().getText();
                    datos[4] = vista_administrador.getNameUsuario().getText();
                    datos[5] = vista_administrador.getContraseñaU().getText();
                    
                    datos[6] = nameBDU;
                    datos[7] = completadoU;
                    datos[8] = idUsua;
                    
                    modelo.insetIntoUsuarios(datos);
                    
                    vista_administrador.getNombreU().setText("");
                    vista_administrador.getApelidoU().setText("");
                    vista_administrador.getFechaNaU().setText("");
                    vista_administrador.getFechaInU().setText("");
                    vista_administrador.getNameUsuario().setText("");
                    vista_administrador.getContraseñaU().setText("");

                    vista_administrador.getNombreU().setEditable(false);
                    vista_administrador.getApelidoU().setEditable(false);
                    vista_administrador.getFechaNaU().setEditable(false);
                    vista_administrador.getFechaInU().setEditable(false);
                    vista_administrador.getNameUsuario().setEditable(false);
                    vista_administrador.getContraseñaU().setEditable(false);
                    vista_administrador.getBtnAceptarU().setEnabled(false);  
                    vista_administrador.getBtnCancelarU().setEnabled(false); 
                    banderaAgregarU = false;
                    banderaActualizarU = false;

                    if (cajerosFlag) 
                    {
                        mostrarDatosCajeros("");
                    } 
                    else if (barmansFlag) 
                    {
                        mostrarDatosBarmans("");
                    } 
                    else if (cocinerosFlag) {
                        mostrarDatosCocineros("");
                    } 
                    else if (meserosFlag) 
                    {
                        mostrarDatosMeseros("");
                    }
                }

            } 
            else if (banderaActualizarU) 
            {
                if (JOptionPane.showConfirmDialog(null, "Estas seguro de Actualizar este Registro?", "Confirmar Registro", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) 
                {
                    String datos[] = new String[9];
                   
                    datos[0] = vista_administrador.getNombreU().getText();
                    datos[1] = vista_administrador.getApelidoU().getText();
                    datos[2] = vista_administrador.getFechaNaU().getText();
                    datos[3] = vista_administrador.getFechaInU().getText();
                    datos[4] = vista_administrador.getNameUsuario().getText();
                    datos[5] = vista_administrador.getContraseñaU().getText();
                    
                    datos[6] = nameBDU;
                    datos[7] = completadoU;
                    datos[8] = idUsua;
                    
                    modelo.updateUsuarios(datos);

                    
                    vista_administrador.getNombreU().setText("");
                    vista_administrador.getApelidoU().setText("");
                    vista_administrador.getFechaNaU().setText("");
                    vista_administrador.getFechaInU().setText("");
                    vista_administrador.getNameUsuario().setText("");
                    vista_administrador.getContraseñaU().setText("");

                    vista_administrador.getNombreU().setEditable(false);
                    vista_administrador.getApelidoU().setEditable(false);
                    vista_administrador.getFechaNaU().setEditable(false);
                    vista_administrador.getFechaInU().setEditable(false);
                    vista_administrador.getNameUsuario().setEditable(false);
                    vista_administrador.getContraseñaU().setEditable(false);
                    vista_administrador.getBtnAceptarU().setEnabled(false);  
                    vista_administrador.getBtnCancelarU().setEnabled(false); 
                    banderaAgregarU = false;
                    banderaActualizarU = false;
                    
                    if (cajerosFlag) 
                    {
                        mostrarDatosCajeros("");
                    } 
                    else if (barmansFlag) 
                    {
                        mostrarDatosBarmans("");
                    } 
                    else if (cocinerosFlag) {
                        mostrarDatosCocineros("");
                    } 
                    else if (meserosFlag) 
                    {
                        mostrarDatosMeseros("");
                    }


                }

            }

        }

        else if(comando=="CANCELARU")
        {
            vista_administrador.getNombreU().setText("");
            vista_administrador.getApelidoU().setText("");
            vista_administrador.getFechaNaU().setText("");
            vista_administrador.getFechaInU().setText("");
            vista_administrador.getNameUsuario().setText("");
            vista_administrador.getContraseñaU().setText("");

            vista_administrador.getNombreU().setEditable(false);
            vista_administrador.getApelidoU().setEditable(false);
            vista_administrador.getFechaNaU().setEditable(false);
            vista_administrador.getFechaInU().setEditable(false);
            vista_administrador.getNameUsuario().setEditable(false);
            vista_administrador.getContraseñaU().setEditable(false);

            vista_administrador.getBtnAceptarU().setEnabled(false);
            vista_administrador.getBtnCancelarU().setEnabled(false); 

        }



////////////BOTONES BD//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        else if(comando=="AGREGAR")
        {
            if (banderaProductos) 
            {
                vista_administrador.getNombreT().setEditable(true);
                vista_administrador.getDescripcionT().setEditable(true);
                vista_administrador.getPrecioT().setEditable(true);
                vista_administrador.getURLT().setEditable(true);
                vista_administrador.getBtnIMG().setEnabled(true); 
                vista_administrador.getLabelIMG().setIcon(null);

                vista_administrador.getBtnAceptar().setEnabled(true);
                vista_administrador.getBtnCancelar().setEnabled(true);

                banderaActualizarBA = false;
                banderaAgregarBA = false;

                banderaActualizarU = false;
                banderaAgregarU = false;

                banderaActualizarP = false;
                banderaAgregarP = true;

                banderaActualizarBF = false;
                banderaAgregarBF = false;

                banderaActualizarBC = false;
                banderaAgregarBC = false;
            } 

            else if (bebidasAFlag) 
            {
                vista_administrador.getNombreTBA().setEditable(true);
                vista_administrador.getDescripcionTBA().setEditable(true);
                vista_administrador.getPrecioTBA().setEditable(true);
                vista_administrador.getTipoAlcohol().setEnabled(true);
                vista_administrador.getURLTBA().setEditable(true);
                vista_administrador.getBtnIMGBA().setEnabled(true); 
                vista_administrador.getLabelIMGBA().setIcon(null);

                vista_administrador.getBtnAceptarBA().setEnabled(true);
                vista_administrador.getBtnCancelarBA().setEnabled(true);

                banderaActualizarBA = false;
                banderaAgregarBA = true;

                banderaActualizarU = false;
                banderaAgregarU = false;

                banderaActualizarP = false;
                banderaAgregarP = false;

                banderaActualizarBF = false;
                banderaAgregarBF = false;

                banderaActualizarBC = false;
                banderaAgregarBC = false;
            }    
            else if (bebidasFFlag) 
            {
                vista_administrador.getNombreT().setEditable(true);
                vista_administrador.getDescripcionT().setEditable(true);
                vista_administrador.getPrecioT().setEditable(true);
                vista_administrador.getURLT().setEditable(true);
                vista_administrador.getBtnIMG().setEnabled(true); 
                vista_administrador.getLabelIMG().setIcon(null);

                vista_administrador.getBtnAceptar().setEnabled(true);
                vista_administrador.getBtnCancelar().setEnabled(true);

                banderaActualizarBA = false;
                banderaAgregarBA = false;

                banderaActualizarU = false;
                banderaAgregarU = false;

                banderaActualizarP = false;
                banderaAgregarP = false;

                banderaActualizarBF = false;
                banderaAgregarBF = true;

                banderaActualizarBC = false;
                banderaAgregarBC = false;
            }   
            else if (bebidasCFlag) 
            {
                vista_administrador.getNombreT().setEditable(true);
                vista_administrador.getDescripcionT().setEditable(true);
                vista_administrador.getPrecioT().setEditable(true);
                vista_administrador.getURLT().setEditable(true);
                vista_administrador.getBtnIMG().setEnabled(true); 
                vista_administrador.getLabelIMG().setIcon(null);

                vista_administrador.getBtnAceptar().setEnabled(true);
                vista_administrador.getBtnCancelar().setEnabled(true);

                banderaActualizarBA = false;
                banderaAgregarBA = false;

                banderaActualizarU = false;
                banderaAgregarU = false;

                banderaActualizarP = false;
                banderaAgregarP = false;

                banderaActualizarBF = false;
                banderaAgregarBF = false;

                banderaActualizarBC = false;
                banderaAgregarBC = true;
            }   
            else if (banderaUsuarios) 
            {
                vista_administrador.getNombreU().setEditable(true);
                vista_administrador.getApelidoU().setEditable(true);
                vista_administrador.getFechaNaU().setEditable(true);
                vista_administrador.getFechaInU().setEditable(true);
                vista_administrador.getNameUsuario().setEditable(true);
                vista_administrador.getContraseñaU().setEditable(true);

                vista_administrador.getBtnAceptarU().setEnabled(true);
                vista_administrador.getBtnCancelarU().setEnabled(true);

                banderaActualizarBA = false;
                banderaAgregarBA = false;

                banderaActualizarU = false;
                banderaAgregarU = true;

                banderaActualizarP = false;
                banderaAgregarP = false;

                banderaActualizarBF = false;
                banderaAgregarBF = false;

                banderaActualizarBC = false;
                banderaAgregarBC = false;


            }

        }

       else if(comando== "ACTUALIZAR")
        {
            if (banderaProductos) 
            {
                banderaActualizarBA = false;
                banderaAgregarBA = false;

                banderaActualizarU = false;
                banderaAgregarU = false;

                banderaActualizarP = true;
                banderaAgregarP = false;

                banderaActualizarBF = false;
                banderaAgregarBF = false;

                banderaActualizarBC = false;
                banderaAgregarBC = false;

                JTable tabla = vista_administrador.getTabla();

                vista_administrador.getNombreT().setEditable(true);
                vista_administrador.getDescripcionT().setEditable(true);
                vista_administrador.getPrecioT().setEditable(true);
                vista_administrador.getURLT().setEditable(true);
                vista_administrador.getBtnAceptar().setEnabled(true);
                vista_administrador.getBtnCancelar().setEnabled(true);
                vista_administrador.getBtnIMG().setEnabled(true); 


                int fila = tabla.getSelectedRow();
                if (fila >= 0) 
                {
                    idProd = tabla.getValueAt(fila, 0).toString();
                    vista_administrador.getNombreT().setText(tabla.getValueAt(fila, 1).toString());
                    vista_administrador.getDescripcionT().setText(tabla.getValueAt(fila, 2).toString());
                    vista_administrador.getPrecioT().setText(tabla.getValueAt(fila, 3).toString());
                    vista_administrador.getURLT().setText(tabla.getValueAt(fila, 4).toString());
                    setImagen();
                }
                else 
                {
                    JOptionPane.showMessageDialog(null, "No Selecciono Fila");
                }
            } 

            else if (bebidasAFlag) 
            {
                banderaActualizarBA = true;
                banderaAgregarBA = false;

                banderaActualizarU = false;
                banderaAgregarU = false;

                banderaActualizarP = false;
                banderaAgregarP = false;

                banderaActualizarBF = false;
                banderaAgregarBF = false;

                banderaActualizarBC = false;
                banderaAgregarBC = false;

                JTable tabla = vista_administrador.getTabla();

                vista_administrador.getNombreTBA().setEditable(true);
                vista_administrador.getDescripcionTBA().setEditable(true);
                vista_administrador.getPrecioTBA().setEditable(true);
                vista_administrador.getTipoAlcohol().setEnabled(true);
                vista_administrador.getURLTBA().setEditable(true);
                vista_administrador.getBtnAceptarBA().setEnabled(true);
                vista_administrador.getBtnCancelarBA().setEnabled(true);
                vista_administrador.getBtnIMGBA().setEnabled(true); 


                int fila = tabla.getSelectedRow();
                if (fila >= 0) 
                {
                    String tipo;

                    idProd = tabla.getValueAt(fila, 0).toString();
                    vista_administrador.getNombreTBA().setText(tabla.getValueAt(fila, 1).toString());
                    vista_administrador.getDescripcionTBA().setText(tabla.getValueAt(fila, 2).toString());
                    vista_administrador.getPrecioTBA().setText(tabla.getValueAt(fila, 3).toString());
                    tipo = tabla.getValueAt(fila, 4).toString();
                    vista_administrador.getTipoAlcohol().setName(tipo);                       
                    vista_administrador.getURLTBA().setText(tabla.getValueAt(fila, 5).toString());

                    setImagenBA();
                }
                else 
                {
                    JOptionPane.showMessageDialog(null, "No Selecciono Fila");
                }
            }               
            else if (bebidasFFlag) 
            {
                banderaActualizarBA = false;
                banderaAgregarBA = false;

                banderaActualizarU = false;
                banderaAgregarU = false;

                banderaActualizarP = false;
                banderaAgregarP = false;

                banderaActualizarBF = true;
                banderaAgregarBF = false;

                banderaActualizarBC = false;
                banderaAgregarBC = false;

                JTable tabla = vista_administrador.getTabla();

                vista_administrador.getNombreT().setEditable(true);
                vista_administrador.getDescripcionT().setEditable(true);
                vista_administrador.getPrecioT().setEditable(true);
                vista_administrador.getURLT().setEditable(true);
                vista_administrador.getBtnAceptar().setEnabled(true);
                vista_administrador.getBtnCancelar().setEnabled(true);
                vista_administrador.getBtnIMG().setEnabled(true); 


                int fila = tabla.getSelectedRow();
                if (fila >= 0) 
                {
                    String tipo;

                    idProd = tabla.getValueAt(fila, 0).toString();
                    vista_administrador.getNombreT().setText(tabla.getValueAt(fila, 1).toString());
                    vista_administrador.getDescripcionT().setText(tabla.getValueAt(fila, 2).toString());
                    vista_administrador.getPrecioT().setText(tabla.getValueAt(fila, 3).toString());                      
                    vista_administrador.getURLT().setText(tabla.getValueAt(fila, 4).toString());

                    setImagen();
                }
                else 
                {
                    JOptionPane.showMessageDialog(null, "No Selecciono Fila");
                }
            }

            else if (bebidasCFlag) 
            {
                banderaActualizarBA = false;
                banderaAgregarBA = false;

                banderaActualizarU = false;
                banderaAgregarU = false;

                banderaActualizarP = false;
                banderaAgregarP = false;

                banderaActualizarBF = false;
                banderaAgregarBF = false;

                banderaActualizarBC = true;
                banderaAgregarBC = false;

                JTable tabla = vista_administrador.getTabla();

                vista_administrador.getNombreT().setEditable(true);
                vista_administrador.getDescripcionT().setEditable(true);
                vista_administrador.getPrecioT().setEditable(true);
                vista_administrador.getURLT().setEditable(true);
                vista_administrador.getBtnAceptar().setEnabled(true);
                vista_administrador.getBtnCancelar().setEnabled(true);
                vista_administrador.getBtnIMG().setEnabled(true);

                int fila = tabla.getSelectedRow();
                if (fila >= 0) 
                {
                    idProd = tabla.getValueAt(fila, 0).toString();
                    vista_administrador.getNombreT().setText(tabla.getValueAt(fila, 1).toString());
                    vista_administrador.getDescripcionT().setText(tabla.getValueAt(fila, 2).toString());
                    vista_administrador.getPrecioT().setText(tabla.getValueAt(fila, 3).toString());                      
                    vista_administrador.getURLT().setText(tabla.getValueAt(fila, 4).toString());
                    setImagen();
                }
                else 
                {
                    JOptionPane.showMessageDialog(null, "No Selecciono Fila");
                }
            }

            else if (banderaUsuarios) 
            {
                banderaActualizarBA = false;
                banderaAgregarBA = false;

                banderaActualizarU = true;
                banderaAgregarU = false;

                banderaActualizarP = false;
                banderaAgregarP = false;

                banderaActualizarBF = false;
                banderaAgregarBF = false;

                banderaActualizarBC = false;
                banderaAgregarBC = false;

                JTable tabla = vista_administrador.getTabla();

                vista_administrador.getNombreU().setEditable(true);
                vista_administrador.getApelidoU().setEditable(true);
                vista_administrador.getFechaNaU().setEditable(true);
                vista_administrador.getFechaInU().setEditable(true);
                vista_administrador.getNameUsuario().setEditable(true);
                vista_administrador.getContraseñaU().setEditable(true);
                vista_administrador.getBtnAceptarU().setEnabled(true);
                vista_administrador.getBtnCancelarU().setEnabled(true);


                int fila = tabla.getSelectedRow();
                if (fila >= 0) 
                {
                    idUsua = tabla.getValueAt(fila, 0).toString();
                    vista_administrador.getNombreU().setText(tabla.getValueAt(fila, 1).toString());
                    vista_administrador.getApelidoU().setText(tabla.getValueAt(fila, 2).toString());
                    vista_administrador.getFechaNaU().setText(tabla.getValueAt(fila, 3).toString());
                    vista_administrador.getFechaInU().setText(tabla.getValueAt(fila, 4).toString());
                    vista_administrador.getNameUsuario().setText(tabla.getValueAt(fila, 5).toString());
                    vista_administrador.getContraseñaU().setText(tabla.getValueAt(fila, 6).toString());
                }
                else 
                {
                    JOptionPane.showMessageDialog(null, "No Selecciono Fila");
                }
            } 

        }

        else if(comando=="ELIMINAR")
        {
            if (banderaProductos) 
            {
                int fila = vista_administrador.getTabla().getSelectedRow();
                String cod = "";
                cod = vista_administrador.getTabla().getValueAt(fila, 0).toString();
                
                String datos[] = new String[3];
                datos[0] = nameBD;
                datos[1] = completado;
                datos[2] = cod;
                modelo.deleteProdUsua(datos);
                
                if (platillosFlag) 
                {
                    mostrarDatosPlatillos("");
                } 
                else if (postresFlag) 
                {
                    mostrarDatosPostres("");
                }

            } 
            else if (bebidasAFlag) 
            {
                int fila = vista_administrador.getTabla().getSelectedRow();
                String cod = "";
                cod = vista_administrador.getTabla().getValueAt(fila, 0).toString();
                
                String datos[] = new String[3];
                datos[0] = nameBD;
                datos[1] = completado;
                datos[2] = cod;
                
                modelo.deleteProdBebidas(datos);
                mostrarDatosBebidasA("");
            }
            else if (bebidasFFlag) 
            {
                int fila = vista_administrador.getTabla().getSelectedRow();
                String cod = "";
                cod = vista_administrador.getTabla().getValueAt(fila, 0).toString();
                
                String datos[] = new String[3];
                datos[0] = nameBD;
                datos[1] = completado;
                datos[2] = cod;
                
                modelo.deleteProdBebidas(datos);
                mostrarDatosBebidasF("");
            }   
            else if (bebidasCFlag) 
            {
                int fila = vista_administrador.getTabla().getSelectedRow();
                String cod = "";
                cod = vista_administrador.getTabla().getValueAt(fila, 0).toString();
                
                String datos[] = new String[3];
                datos[0] = nameBD;
                datos[1] = completado;
                datos[2] = cod;
                
                modelo.deleteProdBebidas(datos);
                mostrarDatosBebidasC("");
            }   
            else if (banderaUsuarios) 
            {
                int fila = vista_administrador.getTabla().getSelectedRow();
                String cod = "";
                cod = vista_administrador.getTabla().getValueAt(fila, 0).toString();
                
                String datos[] = new String[3];
                datos[0] = nameBDU;
                datos[1] = completadoU;
                datos[2] = cod;
                
                modelo.deleteProdBebidas(datos);
                if (cajerosFlag) 
                {
                    mostrarDatosCajeros("");
                } 
                else if (barmansFlag) 
                {
                    mostrarDatosBarmans("");
                } 
                else if (cocinerosFlag) 
                {
                    mostrarDatosCocineros("");
                }
                else if (meserosFlag) 
                {
                    mostrarDatosMeseros("");
                }

            }

        }

        else if(comando=="BUSCAR")
        {
            if (banderaProductos) 
            {
                String valor;
                valor = vista_administrador.getBuscarTxt().getText();
                if (platillosFlag) 
                {
                    mostrarDatosPlatillos(valor);
                } 
                else if (postresFlag) 
                {
                    mostrarDatosPostres(valor);
                }
            } 

            else if (bebidasAFlag) 
            {
                String valor;
                valor = vista_administrador.getBuscarTxt().getText();
                mostrarDatosBebidasA(valor);
            }
            else if (bebidasFFlag) 
            {
                String valor;
                valor = vista_administrador.getBuscarTxt().getText();
                mostrarDatosBebidasF(valor);
            }
            else if (bebidasCFlag) 
            {
                String valor;
                valor = vista_administrador.getBuscarTxt().getText();
                mostrarDatosBebidasC(valor);
            }

            else if (banderaUsuarios) 
            {
                String valor;
                valor = vista_administrador.getBuscarTxt().getText();
                if (cajerosFlag) 
                {
                    mostrarDatosCajeros(valor);
                } 
                else if (barmansFlag) 
                {
                    mostrarDatosBarmans(valor);
                } 
                else if (cocinerosFlag) 
                {
                    mostrarDatosCocineros(valor);
                }
                else if (meserosFlag) 
                {
                    mostrarDatosMeseros(valor);
                }
            }
        }
        
              
    }
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////     
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////     
    
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////METODOS
    
    
    
    //Platillos menu
    public void generarBotonesPlatillos() 
    {
        TablaBPlatillos = modelo.obtenerTDatosPlatillos("");
        botonesPlatillos = new JButton[TablaBPlatillos.getRowCount()];
        
        for (contgBP = 0; contgBP < TablaBPlatillos.getRowCount(); contgBP++) 
        {
            //botonesPlatillos[contgBP] = new JButton(String.valueOf(TablaBPlatillos.getValueAt(contgBP, 1)));
            botonesPlatillos[contgBP] = new JButton("NOMBRE");
            botonesPlatillos[contgBP].setFocusPainted(true);
            //botonesPlatillos[contgBP].setBorder(new LineBorder(Color.black));
            botonesPlatillos[contgBP].setContentAreaFilled(false);
            botonesPlatillos[contgBP].addActionListener(new ActionListener()                    
            {
                String id2= (String.valueOf(TablaBPlatillos.getValueAt(contgBP, 0)));
                @Override
                public void actionPerformed(ActionEvent e) 
                {                                      
                    actionBotonesPlatillos(id2);
                }
            });
            if(banderaBP)
            {
                vista_meseros.getPanelCPlatillos().add(botonesPlatillos[contgBP]);
            }                      
        }
        banderaBP=false;
    }  
    
    public void imagenesBotonesPlatillos()
    {
        for (int i = 0; i < TablaBPlatillos.getRowCount(); i++) 
        {
            ImageIcon fot = new ImageIcon(String.valueOf(TablaBPlatillos.getValueAt(i, 4)));
            Icon icono = new ImageIcon(fot.getImage().getScaledInstance(botonesPlatillos[i].getWidth(), botonesPlatillos[i].getHeight(), Image.SCALE_SMOOTH));
            botonesPlatillos[i].setIcon(icono);            
            vista_meseros.repaint();
        }
    }
    
    public void actionBotonesPlatillos(String id) 
    {
        
        for (int i = 0; i < TablaBPlatillos.getRowCount(); i++) 
        {
            if (id == String.valueOf(TablaBPlatillos.getValueAt(i, 0))) 
            {
                String cant = JOptionPane.showInputDialog(String.valueOf(TablaBPlatillos.getValueAt(i, 1))+"\nDescripcion: "+String.valueOf(TablaBPlatillos.getValueAt(i, 2))+String.valueOf(TablaBPlatillos.getValueAt(i, 3))+"\nPrecio: $"+String.valueOf(TablaBPlatillos.getValueAt(i, 3))+"\nCantidad: ");
                if((cant != null) && (cant.length() > 0) && isNumeric(cant))
                {
                    //String cant = JOptionPane.showInputDialog(null, "" + String.valueOf(TablaBPlatillos.getValueAt(i, 1)) + "\nDescripcion: " + String.valueOf(TablaBPlatillos.getValueAt(i, 2)) + "\nCantidad: ");
                    String datos[] = new String[5];
                    datos[0] = String.valueOf(TablaBPlatillos.getValueAt(i, 0));
                    datos[1] = String.valueOf(TablaBPlatillos.getValueAt(i, 1));
                    datos[2] = String.valueOf(TablaBPlatillos.getValueAt(i, 3));
                    datos[3] = cant;
                    double total = (Double.parseDouble(cant))*Double.parseDouble(datos[2]);
                    datos[4] = String.valueOf(total);
                    
                    TBPedido.addRow(datos);                                      
                }
                else if((cant != null)  &&  !isNumeric(cant))
                {
                    JOptionPane.showMessageDialog(null, "¡Solo pueden ser Numeros enteros!");
                }
                
            }            
        }
        String pedido[][] = obtenerDatosTablaPedido();
        double totalFin=0;
        for(int i = 0;i<pedido.length;i++)
        {
            totalFin = totalFin+Double.parseDouble(pedido[i][4]);
        }
        vista_meseros.getTotalLabel().setText(""+totalFin);
        
    }
    
    //Bebidas Alcoholicas menu
    public void generarBotonesBebidasAlcohilicas() 
    {
        TablaBBebidasAlcoholicas = modelo.obtenerTDatosBebidasA("");
        botonesBebidasAlcoholicas = new JButton[TablaBBebidasAlcoholicas.getRowCount()];
        
        for (contgBBA = 0; contgBBA < TablaBBebidasAlcoholicas.getRowCount(); contgBBA++) 
        {
            //botonesBebidasAlcoholicas[contgBP] = new JButton(String.valueOf(TablaBPlatillos.getValueAt(contgBP, 1)));
            botonesBebidasAlcoholicas[contgBBA] = new JButton("ALCOHOL");
            botonesBebidasAlcoholicas[contgBBA].setFocusPainted(true);
            //botonesBebidasAlcoholicas[contgBP].setBorder(new LineBorder(Color.black));
            botonesBebidasAlcoholicas[contgBBA].setContentAreaFilled(false);
            botonesBebidasAlcoholicas[contgBBA].addActionListener(new ActionListener()                    
            {
                String id2= (String.valueOf(TablaBBebidasAlcoholicas.getValueAt(contgBBA, 0)));
                @Override
                public void actionPerformed(ActionEvent e) 
                {                                      
                    actionBotonesBebidasAlcohilicas(id2);
                }
            });
            if(banderaBBA)
            {
                vista_meseros.getPanelCBebidasAlcoholicas().add(botonesBebidasAlcoholicas[contgBBA]);
            }                      
        }
        banderaBBA=false;
    }  
    
    public void imagenesBotonesBebidasAlcohilicas()
    {
        for (int i = 0; i < TablaBBebidasAlcoholicas.getRowCount(); i++) 
        {
            ImageIcon fot = new ImageIcon(String.valueOf(TablaBBebidasAlcoholicas.getValueAt(i, 4)));
            Icon icono = new ImageIcon(fot.getImage().getScaledInstance(botonesBebidasAlcoholicas[i].getWidth(), botonesBebidasAlcoholicas[i].getHeight(), Image.SCALE_SMOOTH));
            botonesBebidasAlcoholicas[i].setIcon(icono);            
            vista_meseros.repaint();
        }
    }
    
    public void actionBotonesBebidasAlcohilicas(String id) 
    {
        
        for (int i = 0; i < TablaBBebidasAlcoholicas.getRowCount(); i++) 
        {
            if (id == String.valueOf(TablaBBebidasAlcoholicas.getValueAt(i, 0))) 
            {
                String cant = JOptionPane.showInputDialog(String.valueOf(TablaBBebidasAlcoholicas.getValueAt(i, 1))+"\nDescripcion: "+String.valueOf(TablaBBebidasAlcoholicas.getValueAt(i, 2))+String.valueOf(TablaBBebidasAlcoholicas.getValueAt(i, 3))+"\nPrecio: $"+String.valueOf(TablaBBebidasAlcoholicas.getValueAt(i, 3))+"\nCantidad: ");
                if((cant != null) && (cant.length() > 0) && isNumeric(cant))
                {
                    //String cant = JOptionPane.showInputDialog(null, "" + String.valueOf(TablaBPlatillos.getValueAt(i, 1)) + "\nDescripcion: " + String.valueOf(TablaBPlatillos.getValueAt(i, 2)) + "\nCantidad: ");
                    String datos[] = new String[5];
                    datos[0] = String.valueOf(TablaBBebidasAlcoholicas.getValueAt(i, 0));
                    datos[1] = String.valueOf(TablaBBebidasAlcoholicas.getValueAt(i, 1));
                    datos[2] = String.valueOf(TablaBBebidasAlcoholicas.getValueAt(i, 3));
                    datos[3] = cant;
                    double total = (Double.parseDouble(cant))*Double.parseDouble(datos[2]);
                    datos[4] = String.valueOf(total);
                    
                    TBPedido.addRow(datos);                                      
                }
                else if((cant != null)  &&  !isNumeric(cant))
                {
                    JOptionPane.showMessageDialog(null, "¡Solo pueden ser Numeros enteros!");
                }
                
            }            
        }
        String pedido[][] = obtenerDatosTablaPedido();
        double totalFin=0;
        for(int i = 0;i<pedido.length;i++)
        {
            totalFin = totalFin+Double.parseDouble(pedido[i][4]);
        }
        vista_meseros.getTotalLabel().setText(""+totalFin);
        
    }
    
    //Bebidas Frias menu
    public void generarBotonesBebidasFrias() 
    {
        TablaBBebidasFrias = modelo.obtenerTDatosBebidasF("");
        botonesBebidasFrias = new JButton[TablaBBebidasFrias.getRowCount()];
        
        for (contgBBF = 0; contgBBF < TablaBBebidasFrias.getRowCount(); contgBBF++) 
        {
            //botonesPlatillos[contgBP] = new JButton(String.valueOf(TablaBPlatillos.getValueAt(contgBP, 1)));
            botonesBebidasFrias[contgBBF] = new JButton("FRIAS");
            botonesBebidasFrias[contgBBF].setFocusPainted(true);
            //botonesPlatillos[contgBP].setBorder(new LineBorder(Color.black));
            botonesBebidasFrias[contgBBF].setContentAreaFilled(false);
            botonesBebidasFrias[contgBBF].addActionListener(new ActionListener()                    
            {
                String id2= (String.valueOf(TablaBBebidasFrias.getValueAt(contgBBF, 0)));
                @Override
                public void actionPerformed(ActionEvent e) 
                {                                      
                    actionBotonesBebidasFrias(id2);
                }
            });
            if(banderaBBF)
            {
                vista_meseros.getPanelCBebidasFrias().add(botonesBebidasFrias[contgBBF]);
            }                      
        }
        banderaBBF=false;
    }  
    
    public void imagenesBotonesBebidasFrias()
    {
        for (int i = 0; i < TablaBBebidasFrias.getRowCount(); i++) 
        {
            ImageIcon fot = new ImageIcon(String.valueOf(TablaBBebidasFrias.getValueAt(i, 4)));
            Icon icono = new ImageIcon(fot.getImage().getScaledInstance(botonesBebidasFrias[i].getWidth(), botonesBebidasFrias[i].getHeight(), Image.SCALE_SMOOTH));
            botonesBebidasFrias[i].setIcon(icono);            
            vista_meseros.repaint();
        }
    }
    
    public void actionBotonesBebidasFrias(String id) 
    {
        
        for (int i = 0; i < TablaBBebidasFrias.getRowCount(); i++) 
        {
            if (id == String.valueOf(TablaBBebidasFrias.getValueAt(i, 0))) 
            {
                String cant = JOptionPane.showInputDialog(String.valueOf(TablaBBebidasFrias.getValueAt(i, 1))+"\nDescripcion: "+String.valueOf(TablaBBebidasFrias.getValueAt(i, 2))+"\nPrecio: $"+String.valueOf(TablaBBebidasFrias.getValueAt(i, 3))+"\nCantidad: ");
                if((cant != null) && (cant.length() > 0) && isNumeric(cant))
                {
                    //String cant = JOptionPane.showInputDialog(null, "" + String.valueOf(TablaBPlatillos.getValueAt(i, 1)) + "\nDescripcion: " + String.valueOf(TablaBPlatillos.getValueAt(i, 2)) + "\nCantidad: ");
                    String datos[] = new String[5];
                    datos[0] = String.valueOf(TablaBBebidasFrias.getValueAt(i, 0));
                    datos[1] = String.valueOf(TablaBBebidasFrias.getValueAt(i, 1));
                    datos[2] = String.valueOf(TablaBBebidasFrias.getValueAt(i, 3));
                    datos[3] = cant;
                    double total = (Double.parseDouble(cant))*Double.parseDouble(datos[2]);
                    datos[4] = String.valueOf(total);
                    
                    TBPedido.addRow(datos);                                      
                }
                else if((cant != null)  &&  !isNumeric(cant))
                {
                    JOptionPane.showMessageDialog(null, "¡Solo pueden ser Numeros enteros!");
                }
                
            }            
        }
        String pedido[][] = obtenerDatosTablaPedido();
        double totalFin=0;
        for(int i = 0;i<pedido.length;i++)
        {
            totalFin = totalFin+Double.parseDouble(pedido[i][4]);
        }
        vista_meseros.getTotalLabel().setText(""+totalFin);
        
    }
    
    //Bebidas Calientes menu
    public void generarBotonesBebidasCalientes() 
    {
        TablaBBebidasCalientes = modelo.obtenerTDatosBebidasC("");
        botonesBebidasCalientes = new JButton[TablaBBebidasCalientes.getRowCount()];
        
        for (contgBBC = 0; contgBBC < TablaBBebidasCalientes.getRowCount(); contgBBC++) 
        {
            //botonesPlatillos[contgBP] = new JButton(String.valueOf(TablaBPlatillos.getValueAt(contgBP, 1)));
            botonesBebidasCalientes[contgBBC] = new JButton("CALIENTES");
            botonesBebidasCalientes[contgBBC].setFocusPainted(true);
            //botonesPlatillos[contgBP].setBorder(new LineBorder(Color.black));
            botonesBebidasCalientes[contgBBC].setContentAreaFilled(false);
            botonesBebidasCalientes[contgBBC].addActionListener(new ActionListener()                    
            {
                String id2= (String.valueOf(TablaBBebidasCalientes.getValueAt(contgBBC, 0)));
                @Override
                public void actionPerformed(ActionEvent e) 
                {                                      
                    actionBotonesBebidasCalientes(id2);
                }
            });
            if(banderaBBC)
            {
                vista_meseros.getPanelCCalientes().add(botonesBebidasCalientes[contgBBC]);
            }                      
        }
        banderaBBC=false;
    }  
    
    public void imagenesBotonesBebidasCalientes()
    {
        for (int i = 0; i < TablaBBebidasCalientes.getRowCount(); i++) 
        {
            ImageIcon fot = new ImageIcon(String.valueOf(TablaBBebidasCalientes.getValueAt(i, 4)));
            Icon icono = new ImageIcon(fot.getImage().getScaledInstance(botonesBebidasCalientes[i].getWidth(), botonesBebidasCalientes[i].getHeight(), Image.SCALE_SMOOTH));
            botonesBebidasCalientes[i].setIcon(icono);            
            vista_meseros.repaint();
        }
    }
    
    public void actionBotonesBebidasCalientes(String id) 
    {
        
        for (int i = 0; i < TablaBBebidasCalientes.getRowCount(); i++) 
        {
            if (id == String.valueOf(TablaBBebidasCalientes.getValueAt(i, 0))) 
            {
                String cant = JOptionPane.showInputDialog(String.valueOf(TablaBBebidasCalientes.getValueAt(i, 1))+"\nDescripcion: "+String.valueOf(TablaBBebidasCalientes.getValueAt(i, 2))+"\nPrecio: $"+String.valueOf(TablaBBebidasCalientes.getValueAt(i, 3))+"\nCantidad: ");
                if((cant != null) && (cant.length() > 0) && isNumeric(cant))
                {
                    //String cant = JOptionPane.showInputDialog(null, "" + String.valueOf(TablaBPlatillos.getValueAt(i, 1)) + "\nDescripcion: " + String.valueOf(TablaBPlatillos.getValueAt(i, 2)) + "\nCantidad: ");
                    String datos[] = new String[5];
                    datos[0] = String.valueOf(TablaBBebidasCalientes.getValueAt(i, 0));
                    datos[1] = String.valueOf(TablaBBebidasCalientes.getValueAt(i, 1));
                    datos[2] = String.valueOf(TablaBBebidasCalientes.getValueAt(i, 3));
                    datos[3] = cant;
                    double total = (Double.parseDouble(cant))*Double.parseDouble(datos[2]);
                    datos[4] = String.valueOf(total);
                    
                    TBPedido.addRow(datos);                                      
                }
                else if((cant != null)  &&  !isNumeric(cant))
                {
                    JOptionPane.showMessageDialog(null, "¡Solo pueden ser Numeros enteros!");
                }
                
            }            
        }
        String pedido[][] = obtenerDatosTablaPedido();
        double totalFin=0;
        for(int i = 0;i<pedido.length;i++)
        {
            totalFin = totalFin+Double.parseDouble(pedido[i][4]);
        }
        vista_meseros.getTotalLabel().setText(""+totalFin);
        
    }
    
    //Postres menu
    public void generarBotonesPostres() 
    {
        TablaBPostres = modelo.obtenerTDatosPostres("");
        botonesPostres = new JButton[TablaBPostres.getRowCount()];
        
        for (contgBPO = 0; contgBPO < TablaBPostres.getRowCount(); contgBPO++) 
        {
            //botonesPlatillos[contgBP] = new JButton(String.valueOf(TablaBPlatillos.getValueAt(contgBP, 1)));
            botonesPostres[contgBPO] = new JButton("POSTRES");
            botonesPostres[contgBPO].setFocusPainted(true);
            //botonesPlatillos[contgBP].setBorder(new LineBorder(Color.black));
            botonesPostres[contgBPO].setContentAreaFilled(false);
            botonesPostres[contgBPO].addActionListener(new ActionListener()                    
            {
                String id2= (String.valueOf(TablaBPostres.getValueAt(contgBPO, 0)));
                @Override
                public void actionPerformed(ActionEvent e) 
                {                                      
                    actionBotonesPostres(id2);
                }
            });
            if(banderaBPo)
            {
                vista_meseros.getPanelCPostres().add(botonesPostres[contgBPO]);
            }                      
        }
        banderaBPo=false;
    }  
    
    public void imagenesBotonesPostres()
    {
        for (int i = 0; i < TablaBPostres.getRowCount(); i++) 
        {
            ImageIcon fot = new ImageIcon(String.valueOf(TablaBPostres.getValueAt(i, 4)));
            Icon icono = new ImageIcon(fot.getImage().getScaledInstance(botonesPostres[i].getWidth(), botonesPostres[i].getHeight(), Image.SCALE_SMOOTH));
            botonesPostres[i].setIcon(icono);            
            vista_meseros.repaint();
        }
    }
    
    public void actionBotonesPostres(String id) 
    {
        
        for (int i = 0; i < TablaBPostres.getRowCount(); i++) 
        {
            if (id == String.valueOf(TablaBPostres.getValueAt(i, 0))) 
            {
                String cant = JOptionPane.showInputDialog(String.valueOf(TablaBPostres.getValueAt(i, 1))+"\nDescripcion: "+String.valueOf(TablaBPostres.getValueAt(i, 2))+"\nPrecio: $"+String.valueOf(TablaBPostres.getValueAt(i, 3))+"\nCantidad: ");
                if((cant != null) && (cant.length() > 0) && isNumeric(cant))
                {
                    //String cant = JOptionPane.showInputDialog(null, "" + String.valueOf(TablaBPlatillos.getValueAt(i, 1)) + "\nDescripcion: " + String.valueOf(TablaBPlatillos.getValueAt(i, 2)) + "\nCantidad: ");
                    String datos[] = new String[5];
                    datos[0] = String.valueOf(TablaBPostres.getValueAt(i, 0));
                    datos[1] = String.valueOf(TablaBPostres.getValueAt(i, 1));
                    datos[2] = String.valueOf(TablaBPostres.getValueAt(i, 3));
                    datos[3] = cant;
                    double total = (Double.parseDouble(cant))*Double.parseDouble(datos[2]);
                    datos[4] = String.valueOf(total);
                    
                    TBPedido.addRow(datos);                                      
                }
                else if((cant != null)  &&  !isNumeric(cant))
                {
                    JOptionPane.showMessageDialog(null, "¡Solo pueden ser Numeros enteros!");
                }
                
            }                          
        }
        
        String pedido[][] = obtenerDatosTablaPedido();
        double totalFin=0;
        for(int i = 0;i<pedido.length;i++)
        {
            totalFin = totalFin+Double.parseDouble(pedido[i][4]);
        }
        vista_meseros.getTotalLabel().setText(""+totalFin);
        
    }
    
    
    //NUMERODEMESERO//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void generarListaMeseros() 
    {
        int numMeseros;
        numMeseros = modelo.getNumMeseros();
        for(int contador = 0; contador<numMeseros;contador++)
        {
            
            vista_meseros.getNMeseroIn().addItem((contador+1));            
        }                        
    }  
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    //Mesas Lista
    
    public void generarListaBtnMesas() 
    {  
        botonesMesas = new JButton[nMesas];
        objetoMesas = new Mesa[nMesas];     
        int nMbotonesMesases;
        for (contgBM = 0; contgBM < nMesas; contgBM++) 
        {
            
            botonesMesas[contgBM] = new JButton(""+(contgBM+1)); 
            botonesMesas[contgBM] .setBackground(mesaLibre);
            botonesMesas[contgBM] .setForeground(Color.white);
            botonesMesas[contgBM] .setFocusPainted(false);
            //botonesMesas[contgBM] .setBorder(new LineBorder(bordeMesa, 1));
            botonesMesas[contgBM].addActionListener(new ActionListener()                    
            {
                String id=""+(contgBM+1);
                @Override
                public void actionPerformed(ActionEvent e) 
                {                                      
                    actionBotonesMesas(id);
                }
            });
            if(banderaBM)
            {
                vista_meseros.getPanelMesasLista().add(botonesMesas[contgBM]);
            }
            
            Mesa mesa = new Mesa(""+(contgBM+1), false, null, numeroMesero);            
            objetoMesas[contgBM] = mesa;
        }
        banderaBM=false;
    }  
    
    public void actionBotonesMesas(String id) 
    { 
        num_mesa = id;
        for(int cont = 0; cont<nMesas; cont++)
        {           
            if(id.equals(objetoMesas[cont].getNMesa()))
            {
                if(objetoMesas[cont].getEstado() == true && numeroMesero == objetoMesas[cont].getNMeseros())
                {
                    String [] botones = { "Cobrar", "Agregar pedido", "Cancelar",}; 
                    int variable = JOptionPane.showOptionDialog (null, "¡No cobres si no haz revisado tu Pedido!", "Modificar o Cobrar", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, botones, botones[0]);
                    if(variable == 0)
                    {
                        ///COBRAR
                        
                        String[] pedidoObtenido = cobrarMesa();                        
                        vista_caja.añadirCobracion(pedidoObtenido);
                        
                        botonesMesas[cont].setBackground(mesaLibre);
                        objetoMesas[cont].setEstado(false);
                        vista_meseros.getBtnMenuPlatillos().setEnabled(false);
                        vista_meseros.getComboBoxMenuBebidas().setEnabled(false);
                        vista_meseros.getBtnMenuPostres().setEnabled(false);
                        vista_meseros.getBtnMenuBuscar().setEnabled(false);
                        
                        vista_meseros.getPanelComida().setVisible(false);
                        vista_meseros.getPanelBebidasAlcoholicas().setVisible(false);
                        vista_meseros.getPanelBebidasFrias().setVisible(false);
                        vista_meseros.getPanelBebidasCalientes().setVisible(false);
                        vista_meseros.getPanelPostres().setVisible(false);
                        vista_meseros.getPanelBuscado().setVisible(false);
                                                                                                                                             
                        vista_meseros.validate(); 
                    }
                    else if(variable == 1)
                    {
                        for(int i=0;i < nMesas; i++)
                        {
                            botonesMesas[i].setEnabled(false);
                        }
                        vista_meseros.getButtonEnviar().setEnabled(true);
                        vista_meseros.getButtonEliminar().setEnabled(true);
                        vista_meseros.validate();      
                        
                    }
                    else if(variable == 2)
                    {                       
                        vista_meseros.validate();      
                        
                    }
                    
                }
                else if(objetoMesas[cont].getEstado() == true && objetoMesas[cont].getNMeseros() != numeroMesero )
                {                    
                    JOptionPane.showMessageDialog(null, "Esta mesa esta ocupada");
                }
                else if(objetoMesas[cont].getEstado() == false && numeroMesero == objetoMesas[cont].getNMeseros())
                {
                    String nPersonas = JOptionPane.showInputDialog("Numero de Comensales");
                    if((nPersonas != null) && (nPersonas.length() > 0) && isNumeric(nPersonas))
                    {
                        objetoMesas[cont].setEstado(true);
                        objetoMesas[cont].setNMeseros(numeroMesero);
                        objetoMesas[cont].setNPersonas(nPersonas);
                        botonesMesas[cont].setBackground(mesaAtendiendo);
                        
                        vista_meseros.getBtnMenuPlatillos().setEnabled(true);
                        vista_meseros.getComboBoxMenuBebidas().setEnabled(true);
                        vista_meseros.getBtnMenuPostres().setEnabled(true);
                        vista_meseros.getBtnMenuBuscar().setEnabled(true);
                        
                        vista_meseros.getPanelComida().setVisible(true);
                        vista_meseros.getPanelBebidasAlcoholicas().setVisible(false);
                        vista_meseros.getPanelBebidasFrias().setVisible(false);
                        vista_meseros.getPanelBebidasCalientes().setVisible(false);
                        vista_meseros.getPanelPostres().setVisible(false);
                        vista_meseros.getPanelBuscado().setVisible(false);
                         
                        for(int i=0;i < nMesas; i++)
                        {
                            botonesMesas[i].setEnabled(false);
                        }
                        vista_meseros.getButtonEnviar().setEnabled(true);
                        vista_meseros.getButtonEliminar().setEnabled(true);
                        vista_meseros.getButtonSalirTb().setEnabled(false);
                        objetoMesas[Integer.parseInt(num_mesa)-1].setNPersonas(nPersonas);
                        
                        vista_meseros.validate();
                        
                        
                    }  
                    else if((nPersonas != null)  &&  !isNumeric(nPersonas))
                    {
                        JOptionPane.showMessageDialog(null, "¡Solo pueden ser Numeros enteros!");
                    }
                }
                else if(objetoMesas[cont].getEstado() == false)
                {
                    String nPersonas = JOptionPane.showInputDialog("Numero de Comensales");
                    if((nPersonas != null) && (nPersonas.length() > 0) && isNumeric(nPersonas))
                    {
                        objetoMesas[cont].setEstado(true);
                        objetoMesas[cont].setNMeseros(numeroMesero);
                        objetoMesas[cont].setNPersonas(nPersonas);
                        botonesMesas[cont].setBackground(mesaAtendiendo);
                        
                        vista_meseros.getBtnMenuPlatillos().setEnabled(true);
                        vista_meseros.getComboBoxMenuBebidas().setEnabled(true);
                        vista_meseros.getBtnMenuPostres().setEnabled(true);
                        vista_meseros.getBtnMenuBuscar().setEnabled(true);
                        
                        vista_meseros.getPanelComida().setVisible(true);
                        vista_meseros.getPanelBebidasAlcoholicas().setVisible(false);
                        vista_meseros.getPanelBebidasFrias().setVisible(false);
                        vista_meseros.getPanelBebidasCalientes().setVisible(false);
                        vista_meseros.getPanelPostres().setVisible(false);
                        vista_meseros.getPanelBuscado().setVisible(false);
                        for(int i=0;i < nMesas; i++)
                        {
                            botonesMesas[i].setEnabled(false);
                        }
                        vista_meseros.getButtonEnviar().setEnabled(true);
                        vista_meseros.getButtonEliminar().setEnabled(true);
                        vista_meseros.getButtonSalirTb().setEnabled(false);
                        vista_meseros.validate(); 
                    }
                    else if((nPersonas != null)  &&  !isNumeric(nPersonas))
                    {
                        JOptionPane.showMessageDialog(null, "¡Solo pueden ser Numeros enteros!");
                    }
                                               
                }
               
                
            }            
        }                          
    }
    
    public void repintarMesas()
    {       
        for(int cont=0;cont<nMesas;cont++)
        {
            if(objetoMesas[cont].getNMeseros()==numeroMesero && objetoMesas[cont].getEstado()==true)
            {
                botonesMesas[cont].setBackground(mesaAtendiendo);
            }
            else if(objetoMesas[cont].getNMeseros()!= numeroMesero && objetoMesas[cont].getEstado()==true)
            {
                botonesMesas[cont].setBackground(mesaOcupada);
            }
            
        }
    }
    
    private static boolean isNumeric(String cadena)
    {
	try {
		Integer.parseInt(cadena);
		return true;
	} catch (NumberFormatException nfe){
		return false;
	}
    }
    
    
    //OBTENER DATOS DE TABLA PEDIDO
    
    public TableModel pedidoCocina(String[][] arrayPedido)
    {
        DefaultTableModel pedido;
        
        
        pedido = new DefaultTableModel() 
                {
                    @Override
                    public boolean isCellEditable(int filas, int columnas) 
                    {
                        return false;
                    }
                };
        pedido.addColumn("Platillo");
        pedido.addColumn("Cantidad");
        
        for(int i = 0; i<arrayPedido.length; i++)
        {
            String[] fila = new String[2];
            fila[0]=arrayPedido[i][1];
            fila[1]=arrayPedido[i][3];
            pedido.addRow(fila);
        }
        /*TableColumnModel columnModel = vista_meseros.getTablaPedido().getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(15);
        */
        
        return pedido;
    }
    
    
    public String[] cobrarMesa()
    {
        
        TableModel pedido = getPeidoaMesa();
        String[][] pedidoParacobrar;
        pedidoParacobrar=obtenerDatosTablaPedido();
        
        String numMesaAtendida = objetoMesas[Integer.parseInt(num_mesa)-1].getNMesa();
        String numMeseroA = Integer.toString(objetoMesas[Integer.parseInt(num_mesa)-1].getNMeseros());
        String totalPedido =Double.toString(objetoMesas[Integer.parseInt(num_mesa)-1].getTotal());
        
        Calendar c = Calendar.getInstance();
        String dia = Integer.toString(c.get(Calendar.DATE));
        String mes = Integer.toString(c.get(Calendar.MONTH));
        String anio = Integer.toString(c.get(Calendar.YEAR));
        
        int hora, minutos, segundos;
        hora =c.get(Calendar.HOUR_OF_DAY);
        minutos = c.get(Calendar.MINUTE);
        
        String fecha =dia+"/"+mes+"/"+anio;
        String horaAc = hora+":"+minutos;
                               
        
        String[] fila = new String[5];
        fila[0] =  numMeseroA;
        fila[1] =  numMesaAtendida;
        fila[2] =  fecha;
        fila[3] =  horaAc;
        fila[4] =  totalPedido;     
            
        return fila;
    }
    
    
    public String[][] obtenerDatosTablaPedido()
    {
        String[][] pedido = new String[TBPedido.getRowCount()][5];
        for (int i = 0; i < TBPedido.getRowCount(); i++) 
        {
            pedido[i][0] = (String) TBPedido.getValueAt(i, 0);
            pedido[i][1] = (String) TBPedido.getValueAt(i, 1);
            pedido[i][2] = (String) TBPedido.getValueAt(i, 2);
            pedido[i][3] = (String) TBPedido.getValueAt(i, 3);     
            pedido[i][4] = (String) TBPedido.getValueAt(i, 4);     
        }     
        return pedido;
    }
    
    public void setPeidoaMesa()
    {
        objetoMesas[Integer.parseInt(num_mesa)-1].setDatosPedidoMesa(obtenerDatosTablaPedido());
    }
    
    public TableModel getPeidoaMesa()
    {
        TableModel pedido;
        pedido=vista_meseros.getTablaPedido().getModel();                       
        return pedido;
    }   
    public void limpiarTBPedidos()
    {
        for (int cont = TBPedido.getRowCount()-1;cont>0;cont--)
        {
            TBPedido.removeRow(cont);
        }
    }
    
    public double obtenerTotalMesa()
    {
        double total = 0;
        for (int i = 0; i < TBPedido.getRowCount(); i++) 
        {
            total = total + ((double) TBPedido.getValueAt(i, 3));
        }
        return total;
    }
    
    //////PRODUCTOS//////////////    
    public void mostrarDatosPlatillos(String valor) 
    {
        vista_administrador.setNombreBD("Platillos");
        nameBD = "platillos";
        completado = "platillos";
       
        vista_administrador.setModeloTabla(modelo.obtenerTDatosPlatillos(valor));
    }
       
   
    public void mostrarDatosBebidasA(String valor) 
    {
        vista_administrador.setNombreBDBA("B. Alcoholicas");
        nameBD = "bebidas_alcoholicas";
        completado = "alcoholicas";

        vista_administrador.setModeloTabla(modelo.obtenerTDatosBebidasA(valor));
    }
    
    public void mostrarDatosBebidasF(String valor) 
    {
        vista_administrador.setNombreBD("B. Frias");
        nameBD = "bebidas_frias";
        completado = "frias";

        vista_administrador.setModeloTabla(modelo.obtenerTDatosBebidasF(valor));
    }
    
    public void mostrarDatosBebidasC(String valor) 
    {
        vista_administrador.setNombreBD("B. Calientes");
        nameBD = "bebidas_calientes";
        completado = "calientes";

        vista_administrador.setModeloTabla(modelo.obtenerTDatosBebidasC(valor));
    }
    
    
    
    public void mostrarDatosPostres(String valor) 
    {
        vista_administrador.setNombreBD("Postres");
        nameBD = "postres";
        completado = "postres";
        
        vista_administrador.setModeloTabla(modelo.obtenerTDatosPostres(valor));
    }
    
    //////USUARIOS//////////////    
    
    public void mostrarDatosCajeros(String valor) 
    {
        vista_administrador.setNombreBDU("Cajeros");
        nameBDU = "cajeros";
        completadoU = "cajeros";

        vista_administrador.setModeloTabla(modelo.obtenerTDatosCajeros(valor));
    }
    
    public void mostrarDatosBarmans(String valor) 
    {
        vista_administrador.setNombreBDU("Barmans");
        nameBDU = "barmans";
        completadoU = "barmans";

        vista_administrador.setModeloTabla(modelo.obtenerTDatosBarmans(valor));
    }
    
    public void mostrarDatosCocineros(String valor) 
    {
        vista_administrador.setNombreBDU("Cocineros");
        nameBDU = "cocineros";
        completadoU = "cocineros";

        vista_administrador.setModeloTabla(modelo.obtenerTDatosCocineros(valor));
    }
    
    public void mostrarDatosMeseros(String valor) 
    {
        vista_administrador.setNombreBDU("Meseros");
        nameBDU = "meseros";
        completadoU = "meseros";

        vista_administrador.setModeloTabla(modelo.obtenerTDatosMeseros(valor));
    }
    
    public void setImagen()
    {
        ImageIcon fot = new ImageIcon(vista_administrador.getURLT().getText());
                    Icon icono = new ImageIcon(fot.getImage().getScaledInstance(vista_administrador.getLabelIMG().getWidth(), vista_administrador.getLabelIMG().getHeight(), Image.SCALE_DEFAULT));
                    vista_administrador.getLabelIMG().setIcon(icono);
                    vista_administrador.repaint();
    }

    public void setImagenBA()
    {
        ImageIcon fot = new ImageIcon(vista_administrador.getURLTBA().getText());
                    Icon icono = new ImageIcon(fot.getImage().getScaledInstance(vista_administrador.getLabelIMGBA().getWidth(), vista_administrador.getLabelIMGBA().getHeight(), Image.SCALE_DEFAULT));
                    vista_administrador.getLabelIMGBA().setIcon(icono);
                    vista_administrador.repaint();
    }
    
    public String getNameBD()
    {
        return nameBD;
    }
    
    public String getNameBDU()
    {
        return nameBDU;
    }
    public String getCompletado()
    {
        return completado;
    }
    public String getCompletadoU()
    {
        return completadoU;
    }
    
    public Ventana_Administrador getVAdm0inistrador()
    {
        return vista_administrador;
    }
    
    public String getIDProd()
    {
        return idProd;
    }
    
    public String getIDUsua()
    {
        return idUsua;
    }

    @Override
    public void mouseClicked(MouseEvent me) 
    {
        int filaPulsada = this.vista_caja.obtenerTablaCaja().getSelectedRow();
        if(filaPulsada>=0)
        {
            //Se recoge el id de la fila marcada
            String total= vista_caja.obtenerTablaCaja().getValueAt(filaPulsada, 4).toString();
            vista_caja.getLabelTotal().setText("$"+total);
            
        }
        
        
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
