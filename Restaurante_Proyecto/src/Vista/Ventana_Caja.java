/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.Controlador_Restaurante;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class Ventana_Caja extends JFrame {
    private JLabel labelTotal;
    private JButton btnDetalle,btnEliminar,btnCobrar;
    private JTable list;
    
    Color Fondo = new Color(65, 65, 65  );
    Color Bordes = new Color(65, 65, 65);
    Color TopPane = new Color(2, 142, 131);
    Color fonttp = new Color(255,255,255);
    Color Fondo2 = new Color(65, 65, 65);
    Color PanelListaM = new Color(65, 65, 65);
    Color botonesColor = new Color(255, 232, 140);
    Color panelPlatillos = new Color(65, 65, 65 );
    
    DefaultTableModel cobro;

    //CAMBIOS PARA MOVER SIN BORDES
    private JFrame control;
    int pX,pY;
    JLabel labelTitulo;
    
    public Ventana_Caja()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(crearPanelCobrar());
        setSize(new Dimension(1000,550));

        //CAMBIOS PARA MOVERSE SIN BORDES

        control=this;
        setUndecorated(true);
        setLocationRelativeTo(null);
        setVisible(true);
        
        setModelTabla();
        
    }
    private JPanel crearPanelCobrar()
    {
        
    
        JPanel panelPrincipal=new JPanel();
        panelPrincipal.setLayout(new BorderLayout());

        JPanel panelTitulo=new JPanel();
        //panelTitulo.setMaximumSize(new Dimension(2000,300));
        panelTitulo.setBackground(TopPane);
        JLabel labelTitulo=new JLabel("Caja");
        labelTitulo.setFont(new Font("Agency FB", 1,25));
        labelTitulo.setForeground(Color.WHITE);
        panelTitulo.add(labelTitulo);



        JPanel panelLista=new JPanel();
        panelLista.setBorder(new MatteBorder(10,10,10,10,Fondo));
        panelLista.setLayout(new BoxLayout(panelLista,BoxLayout.Y_AXIS));

        JLabel labelPedidos=new JLabel("Pedidos por cobrar");
        labelPedidos.setFont(new Font("Century Gothic",Font.BOLD,20));
        //labelPedidos.setBackground(new Color(81,90,90));
        labelPedidos.setForeground(Color.WHITE);

        //CAMBIOS PARA MOVERE SIN BORDES
        panelTitulo.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                // Get x,y and store them
                pX = me.getX();
                pY = me.getY();

            }

            public void mouseDragged(MouseEvent me) {

                control.setLocation(control.getLocation().x + me.getX() - pX,
                        control.getLocation().y + me.getY() - pY);
            }
        });

        panelTitulo.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent me) {

                control.setLocation(control.getLocation().x + me.getX() - pX,
                        control.getLocation().y + me.getY() - pY);
            }
        });


        list=new JTable();
        JScrollPane scrollPane=new JScrollPane(list);
        panelLista.add(labelPedidos);
        panelLista.add(scrollPane);
        panelLista.setBackground(Fondo);

        JPanel panelControl=new JPanel();
        panelControl.setLayout(new BorderLayout());
        panelControl.setBackground(Fondo);
        panelControl.setMaximumSize(new Dimension(500,1500));

        JPanel subPanel1=new JPanel();
        subPanel1.setLayout(new GridLayout(2,1));
        subPanel1.setBackground(Fondo);

        JLabel labelTituloTotal=new JLabel("Total:");
        labelTituloTotal.setFont(new Font("Arial",Font.BOLD,60));
        labelTituloTotal.setForeground(Color.WHITE);


        labelTotal=new JLabel("$00.00");
        labelTotal.setFont(new Font("Arial",Font.BOLD,60));
        labelTotal.setForeground(new Color(244,208,63));

        subPanel1.add(labelTituloTotal);
        subPanel1.add(labelTotal);


        JPanel subPanel2=new JPanel();
        //subPanel1.setLayout(new GridLayout(1,3,5,5));
        subPanel2.setBorder(new LineBorder(Fondo, 10));  
        subPanel2.setBackground(Fondo);
        subPanel2.setLayout(new GridLayout(3,1,10,10));
        subPanel2.setPreferredSize(new Dimension(110,200));

        btnDetalle=new JButton("VER DETALLE");
        btnDetalle.setBackground(new Color(52,152,219));
        btnDetalle.setForeground(Color.WHITE);
        btnDetalle.setFocusPainted(false);
        btnDetalle.setPreferredSize(new Dimension(30,100));

        btnEliminar=new JButton("ELIMINAR");
        btnEliminar.setBackground(new Color(231,76,60));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFocusPainted(false);
        btnEliminar.setPreferredSize(new Dimension(30,100));
        

        btnCobrar=new JButton("COBRAR");
        btnCobrar.setBackground(new Color(46,204,113));
        btnCobrar.setForeground(Color.WHITE);
        btnCobrar.setFocusPainted(false);
        btnCobrar.setPreferredSize(new Dimension(30,100));

        subPanel2.add(btnDetalle);
        subPanel2.add(btnEliminar);
        subPanel2.add(btnCobrar);
        //subPanel2.add(new JSeparator());

        JSeparator separator=new JSeparator(JSeparator.HORIZONTAL);
        separator.setMaximumSize(new Dimension(100,100));

        panelControl.add(Box.createRigidArea(new Dimension(100, 100)) ,BorderLayout.NORTH);
        panelControl.add(subPanel1, BorderLayout.CENTER);
        //panelControl.add(separator, BorderLayout.CENTER);
        panelControl.add(subPanel2, BorderLayout.SOUTH);

        JPanel panelCentral=new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral,BoxLayout.X_AXIS));
        panelCentral.add(panelLista);
        panelCentral.add(panelControl);

        panelPrincipal.add(panelTitulo, BorderLayout.NORTH);
        panelPrincipal.add(panelCentral, BorderLayout.CENTER);



        return panelPrincipal;
    }

    
    public JTable obtenerTablaCaja()
    {
        return list;
    }
    
    public void setModelTabla()
    {
                       
        cobro = new DefaultTableModel() 
                {
                    @Override
                    public boolean isCellEditable(int filas, int columnas) 
                    {
                        return false;
                    }
                };
        cobro.addColumn("NUM MESERO");
        cobro.addColumn("NUM MESA");
        cobro.addColumn("FECHA");
        cobro.addColumn("HORA");
        cobro.addColumn("TOTAL");
        list.setModel(cobro);
              
        list.setIntercellSpacing(new java.awt.Dimension(0, 0));
        list.setRowHeight(30);
        list.setBackground(Color.WHITE);
        list.setFont(new Font("Century Gothic", Font.LAYOUT_RIGHT_TO_LEFT, 18));
        list.setSelectionBackground(new Color(3, 140, 111) );
        list.setSelectionForeground(Color.WHITE);
        list.getTableHeader().setReorderingAllowed(false);
        list.setRowMargin(0);
        list.setShowVerticalLines(false);
        list.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
        list.getTableHeader().setOpaque(false);
        list.getTableHeader().setBackground(new Color(0,0,0));
        list.getTableHeader().setForeground(Color.WHITE);
    }
    
    public void añadirCobracion(String datos[])
    {
        cobro.addRow(datos);
    }
    public JLabel getLabelTotal()
    {
        return labelTotal;
    }
    public void conectarControlador(Controlador_Restaurante controlador)
    {
        ////PANEL MESEROS     
        btnCobrar.addActionListener(controlador);
        btnCobrar.setActionCommand("COBRARPED");
        
        btnEliminar.addActionListener(controlador);
        btnEliminar.setActionCommand("ELIMINARPED");
        
        btnDetalle.addActionListener(controlador);
        btnDetalle.setActionCommand("VERDETALLES");
        
        list.addMouseListener(controlador);
        //sólo se permite pulsar una fila a la vez.
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        
    }
    
    
    
    public static void main(String [] agrs)
    {
       new Ventana_Caja();
    }
}