/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

/**
 *
 * @author RodrigoLP
 */

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import Modelo.Pedido;


public class Ventana_Cocina extends JFrame {
    
    JLabel labelOrdenes, labelTitulo;
    JPanel panelOR, panelTabla, panelTitulo, panelPrincipal;
    JScrollPane scroll ;
    JPanel panelOR2;
    JPanel panelscroll;
    boolean panelvis1 = true;
    
    Color Fondo = new Color(255, 255, 255 );
    Color Fondo2 = new Color(225, 146, 40);
    Color TopPane = new Color(203, 92, 15);
    
    Font fuenteTop = new Font("Agency FB", 1, 40);
    
    public Ventana_Cocina()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //this.setExtendedState(MAXIMIZED_BOTH);
        setSize(1100,630);

        panelPrincipal=new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal,BoxLayout.Y_AXIS));
        add(panelPrincipal);
          
        panelTitulo=new JPanel();
        panelTitulo.setMaximumSize(new Dimension(4000,300));
        panelTitulo.setBackground(TopPane);
        
        labelTitulo=new JLabel("COCINA");
        labelTitulo.setFont(new Font("Arial",Font.BOLD,30));
        labelTitulo.setForeground(Color.WHITE);
        panelTitulo.add(labelTitulo);
        panelPrincipal.add(panelTitulo);

        panelTabla=new JPanel();
        panelTabla.setBackground(Fondo2);
        //panelTabla.setBorder(new MatteBorder(10,15,15,15,Color.BLUE));
        panelTabla.setLayout(new BoxLayout(panelTabla,BoxLayout.Y_AXIS));

        labelOrdenes=new JLabel("ORDENES");
        labelOrdenes.setFont(new Font("Arial",Font.BOLD,14));
        labelOrdenes.setForeground(Color.BLACK);
        panelTabla.add(labelOrdenes);
        
        
        panelOR = new JPanel();//panel de las ordenes
        panelOR.setLayout(new GridLayout(20,1,110,20));
        //panelOR.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED)));
        panelOR.setBackground(Fondo);
        
        panelOR2 = new JPanel();//panel de las ordenes
        panelOR2.setLayout(new GridLayout(20,1,110,20));
        //panelOR2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED)));
        panelOR2.setBackground(Fondo);
        
        panelscroll =new JPanel();
        panelscroll.setLayout(new BoxLayout(panelscroll, BoxLayout.X_AXIS));
        panelscroll.add(panelOR);
        panelscroll.add(panelOR2);
        
        scroll = new JScrollPane();
        scroll.setViewportView(panelscroll);
        panelTabla.add(scroll);

        panelPrincipal.add(panelTabla);                    
        setVisible(true);
    }
    
     
     
        
     public void nuevaorden(int mesa, int orden, TableModel pedido)
     {
         
         //sie es verdadero lo agrega al primer panel
        if(panelvis1 == true)
        {
            Pedido create = new Pedido(mesa, orden, pedido);
            panelOR.add(create);
            TableColumnModel columnModel = create.getTabla().getColumnModel();
            columnModel.getColumn(0).setPreferredWidth(350);    
            columnModel.getColumn(1).setPreferredWidth(30);            
            
            panelvis1 = false;
        }
        //lo agrega al segundo panel 
        else
        {
            Pedido create = new Pedido(mesa, orden, pedido);
            panelOR2.add(create);
            TableColumnModel columnModel = create.getTabla().getColumnModel();
            columnModel.getColumn(0).setPreferredWidth(350);    
            columnModel.getColumn(1).setPreferredWidth(30);             
            
            panelvis1 = true;
        }
         //panelOR.add(ordenes(mesa, orden));
         validate();
     }
     

    public static void main(String [] agrs)
    {
       Ventana_Cocina lk = new Ventana_Cocina();       
       
       //lk.nuevaorden(2, 1);
       //lk.nuevaorden(3, 1);
       //lk.nuevaorden(1, 4);
       //lk.nuevaorden(2, 1);
       //lk.nuevaorden(3, 1);
    }
}