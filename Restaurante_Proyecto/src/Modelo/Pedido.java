/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Herramientas.LightScrollPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 *
 * @author RodrigoLP
 */
public class Pedido extends JPanel
{
    JTable list;
    Color Fondo = new Color(255, 255, 255 );
    Color Fondo2 = new Color(255, 255, 255 );
    Color FondoOs = new Color(45, 45, 45);
    Color TopPane = new Color(255, 187, 60);
    
    Font fuenteTop = new Font("Agency FB", 1, 25);
    
    public Pedido(int mesa, int orden, TableModel modelotabla)
    {
        setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.RAISED)));
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
      
        JPanel panelLista=new JPanel();
        panelLista.setBorder(new LineBorder(Color.BLACK, 1));
        panelLista.setBackground(TopPane);
        panelLista.setLayout(new BoxLayout(panelLista,BoxLayout.Y_AXIS));
        JLabel labelPedidos=new JLabel("MESA: " + mesa + "  ORDEN: "+ orden);;
        labelPedidos.setFont(fuenteTop);
        labelPedidos.setForeground(Color.BLACK);


        //JList list = new JList<>();
        list = new JTable();
        list.setIntercellSpacing(new java.awt.Dimension(0, 0));
        list.setRowHeight(20);
        list.setBackground(Color.WHITE);
        list.setFont(new Font("Century Gothic", Font.LAYOUT_RIGHT_TO_LEFT, 16));
        list.setSelectionBackground(new Color(255, 174, 2 ) );
        list.setSelectionForeground(Color.WHITE);
        list.getTableHeader().setReorderingAllowed(false);
        list.setRowMargin(0);
        list.setShowVerticalLines(false);
        list.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
        list.getTableHeader().setOpaque(false);
        list.getTableHeader().setBackground(new Color(0,0,0));
        list.getTableHeader().setForeground(Color.WHITE);
        
        
        
        //list.setPreferredSize(new Dimension(50,50));
        //JScrollPane scrollPane=new JScrollPane(list);
        LightScrollPane scrollPane= new LightScrollPane();
        scrollPane.setViewportView(list);
        scrollPane.setPreferredSize(new Dimension(100,180));
        scrollPane.setBackground(Color.WHITE);
        //scrollPane.getViewport().setBackground(Color.white);
        //scrollPane.setBorder(new LineBorder(Color.BLACK, 3));
        list.setModel(modelotabla);                         
        
        
        JPanel top = new JPanel();
        top.setBackground(FondoOs);
        top.setLayout(new GridBagLayout());
        JLabel nomb = new JLabel("Nombre");
        nomb.setForeground(Fondo);
        nomb.setFont(new Font("Segoe UI", Font.BOLD, 16));
        JLabel canti = new JLabel("Cantidad");
        canti.setForeground(Fondo);
        canti.setFont(new Font("Segoe UI", Font.BOLD, 16));
        
        Component rigidArea = Box. createRigidArea(new Dimension(250, 0));
        
        top.add(nomb);
        top.add(rigidArea);
        top.add(canti);
        
        panelLista.add(top);
        panelLista.add(scrollPane);  
        
        JPanel panelContent = new JPanel();
        panelContent.setLayout(new BorderLayout());
        panelContent.setBackground(TopPane);
        
        
        panelContent.add(labelPedidos, BorderLayout.NORTH);
        panelContent.add(panelLista, BorderLayout.CENTER);
        
        
        
        add(panelContent);
       
   }
    
    public JTable getTabla()
    {
        return list;
    }
}
