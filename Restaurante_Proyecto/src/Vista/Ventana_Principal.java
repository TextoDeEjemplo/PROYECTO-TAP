/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import restaurante_proyecto.Launcher;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 *
 * @author RodrigoLP
 */
public class Ventana_Principal extends JFrame
{
    Color Fondo = new Color(65, 65, 65  );
    Color Bordes = new Color(65, 65, 65);
    Color TopPane = new Color(2, 142, 131);
    Color fonttp = new Color(255,255,255);
    Color Fondo2 = new Color(65, 65, 65);
    Color PanelListaM = new Color(65, 65, 65);
    Color botonesPrin = new Color(53, 175, 220 );
    Color panelPlatillos = new Color(65, 65, 65 );
    
    Color btnMeseros = new Color(0, 160, 193 );
    Color btnCaja = new Color(2, 142, 131);
    Color btnBarra = new Color(0, 160, 193 );
    Color btnCocina = new Color(0, 160, 193 );
    
    JButton meseros;
    JButton cocina;
    JButton caja;
    JButton barra;
    
    JButton administrador;
    
    JButton salir;

    //CAMBIOS PARA MOVER SIN BORDES
    private JFrame control;
    int pX,pY;
    JLabel labelTitulo;

    //Para intentar lanzar
    private Launcher launcher;

    public Ventana_Principal()
    {
        super("RESTAURANTE");
        setSize(650,480);
        setDefaultCloseOperation(EXIT_ON_CLOSE);    
        setLayout(new BorderLayout());
        add(panelSuperior(), BorderLayout.NORTH);
        add(botonesPrincipales(), BorderLayout.CENTER);
        add(panelInferior(), BorderLayout.SOUTH);
        setBackground(Fondo);


        //CAMBIOS PARA MOVERSE SIN BORDES

        control=this;
        setUndecorated(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    
    public JPanel panelSuperior()
    {
        JPanel content= new JPanel();
        content.setLayout(new BorderLayout());
        content.setBackground(Fondo);
        
        JPanel superior = new JPanel();
        superior.setBackground(new Color(25,25,25));

        //CAMBIOS PARA MOVERE SIN BORDES
        superior.addMouseListener(new MouseAdapter() {
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

        superior.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent me) {

                control.setLocation(control.getLocation().x + me.getX() - pX,
                        control.getLocation().y + me.getY() - pY);
            }
        });
        
        labelTitulo=new JLabel("PRINCIPAL");
        labelTitulo.setFont(new Font("Agency FB", 1,25));
        labelTitulo.setForeground(Color.WHITE);
        superior.add(labelTitulo);
        content.add(superior , BorderLayout.NORTH);
        content.add(Box.createRigidArea(new Dimension(100, 90)), BorderLayout.CENTER);



        return content;
    }
    
    public JPanel botonesPrincipales()
    {
        JPanel content = new JPanel();
        content.setLayout(new FlowLayout());
        content.setBackground(Fondo);
        
        meseros = new JButton("MESEROS");
        meseros.setPreferredSize(new Dimension(130,130));
        meseros.setBackground(btnMeseros);
        meseros.setForeground(Color.white);
        meseros.setFocusPainted(false);

        meseros.setActionCommand("meseros");
        
        cocina = new JButton("COCINA");
        cocina.setPreferredSize(new Dimension(130,130));
        cocina.setBackground(btnCocina);
        cocina.setForeground(Color.white);
        cocina.setFocusPainted(false);

        cocina.setActionCommand("cocina");
        
        barra = new JButton("BARRA");
        barra.setPreferredSize(new Dimension(130,130));
        barra.setBackground(btnBarra);
        barra.setForeground(Color.white);
        barra.setFocusPainted(false);

        barra.setActionCommand("barra");
        
        caja = new JButton("CAJA");
        caja.setPreferredSize(new Dimension(130,130));
        caja.setBackground(btnCaja);
        caja.setForeground(Color.white);
        caja.setFocusPainted(false);

        caja.setActionCommand("caja");
        
        //content.add(Box.createRigidArea(new Dimension(20, 20)));
        content.add(meseros);
        content.add(cocina);
        content.add(barra);
        content.add(caja); 
        //content.add(Box.createRigidArea(new Dimension(20, 20)));

        controlAbrir controlAbrir=new controlAbrir();
        meseros.addActionListener(controlAbrir);
        cocina.addActionListener(controlAbrir);
        barra.addActionListener(controlAbrir);
        caja.addActionListener(controlAbrir);

        return content;
    }
    private  class controlAbrir implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()){
                case "meseros":
                    launcher.lanzarMeseros();
                    break;
                case "cocina":
                    launcher.lanzarCocina();
                    break;
                case "barra":
                    launcher.lanzarBarra();
                    break;
                case "caja":
                    launcher.lanzarCaja();
            }
        }
    }
    public void conectarLauncher(Launcher l)
    {
        launcher=l;
    }
    
    public JPanel panelInferior()
    {
        JPanel content= new JPanel();
        content.setLayout(new BorderLayout());
        content.setBackground(Fondo);
        
        JPanel botones= new JPanel();
        botones.setBackground(Fondo);
        //botones.setBorder(new LineBorder(Fondo,5));
        botones.setLayout(new GridLayout(1,3));
        
        administrador = new JButton("Administrador");
        administrador.setBackground(botonesPrin);
        administrador.setForeground(Color.white);
        administrador.setFocusPainted(false);
        
        botones.add(Box.createRigidArea(new Dimension(100, 30)));
        botones.add(administrador);
        botones.add(Box.createRigidArea(new Dimension(100, 30)));
        
        
        content.add(botones, BorderLayout.NORTH);
        content.add(Box.createRigidArea(new Dimension(100, 100)), BorderLayout.CENTER);
        
        JPanel botonSalir = new JPanel();
        botonSalir.setLayout(new BorderLayout());
        botonSalir.setBackground(Fondo);
        botonSalir.setBorder(new LineBorder(Fondo,5));
        salir = new JButton("Salir");
        salir.add(Box.createRigidArea(new Dimension(80, 20)));
        salir.setBackground(Color.RED);
        salir.setForeground(Color.white);
        salir.setFocusPainted(false);
        
        botonSalir.add(Box.createRigidArea(new Dimension(150, 0)), BorderLayout.WEST);
        botonSalir.add(Box.createRigidArea(new Dimension(150, 0)), BorderLayout.CENTER);      
        botonSalir.add(salir, BorderLayout.EAST);
        content.add(botonSalir, BorderLayout.SOUTH);    
        return content;
    }
   
    
    
    public static void main (String args[])
    {
        new Ventana_Principal();
    }
}
