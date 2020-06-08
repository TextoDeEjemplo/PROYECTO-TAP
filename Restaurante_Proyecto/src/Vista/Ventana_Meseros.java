package Vista;

import Controlador.Controlador_Restaurante;
import Herramientas.LightScrollPane;
import Modelo.Modelo;
//import com.sun.awt.AWTUtilities;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.ScrollBarUI;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
/**
 *
 * @author RodrigoLP
 */

public class Ventana_Meseros extends JFrame
{
    //Tema Oscuro
    /*Color Fondo = new Color(65, 65, 65  );
    Color Bordes = new Color(65, 65, 65);
    Color TopPane = new Color(2, 142, 131);
    Color fonttp = new Color(255,255,255);
    Color Fondo2 = new Color(65, 65, 65);
    Color PanelListaM = new Color(65, 65, 65);
    Color botonesColor = new Color(255, 232, 140);
    Color panelPlatillos = new Color(65, 65, 65 );
    Color separadores = new Color(30, 33, 43);
    */
    
    Color Fondo = new Color(255, 255, 255 );
    Color Bordes = new Color(255,255,255);
    Color TopPane = new Color(212, 139, 7);
    Color fonttp = new Color(255,255,255);
    Color Fondo2 = new Color(255, 255, 255 );
    Color PanelListaM = new Color(255, 255, 255 );
    Color botonesColor = new Color(255, 182, 0);
    Color panelPlatillos = new Color(255, 255, 255 );
    Color separadores = new Color(30, 33, 43);
   
    
    Color btnEliminar = new Color(216, 60, 91);
    Color btnEnviarConf = new Color(60, 138, 216);
    Color btnSalirBack = new Color(216, 156, 60);

    
    /////////////////////////FUENTES
    
    Font labels = new Font("EngraversGothic BT", 1, 24);
    Font labels20 = new Font("EngraversGothic BT", 1, 18);
    Font labels22 = new Font("EngraversGothic BT", 1, 22);
    Font labels14 = new Font("EngraversGothic BT", 1, 14);
    Font fuente = new Font("Century Gothic", 1, 16);
    
    
    Modelo model;   
    Controlador_Restaurante controlador;
    
    JComboBox numMesero;
       
//PANEL MESEROS
    JButton salirTop;
    
    JPanel meseroscontent;
    JPanel meseros = panelMeseros();
    JButton iniciar;
    JLabel nomusuarioMesero;
    JPasswordField txtcontraseñaM;
    
    //PANEL MESAS
    JPanel paneListasMeasas;
    
    JPanel mesascontent;
    JPanel mesas = panelMesas();    
    JButton comenzar;
    JButton modificar;
    JButton  cobrar;
    JButton eliminar;
    JButton enviarconfirmar;
    
    JTable tablaPedido;
    
    JButton btnSalir;
    
    
    private JButton[] mesitas;
  
    JLabel totalN;
    
    //PANEL MENU
    JButton platillosBtnMenu, bebidasBtnMenu, postresBtnMenu, buscarPlat;
    JComboBox bebidasComboBoxMenu;
    ////////////////////////////////////////////////////
    
    JPanel panelCPlatillos;
    JPanel panelCBebidasAlcoholicas;
    JPanel panelCBebidasFrias;
    JPanel panelCBebidasCalientes;
    JPanel panelCPostres;
    
    
    JPanel panelCBuscado;
    
    ////////////////////////////////////////////////////
    LightScrollPane panelComida = panelComida();
    LightScrollPane panelBebidasAlcoholicas = panelBebidasAlcoholica();
    LightScrollPane panelBebidasFrias = panelBebidasFrias();
    LightScrollPane panelBebidasCalientes = panelBebidasCalientes();
    LightScrollPane panelPostres = panelPostre();
    LightScrollPane panelBuscado = panelBuscado();
    
    
    public Ventana_Meseros ()
    {
        
        super("Meseros");
        setBackground(Fondo);
        setForeground(Fondo);
        setSize(1100,630);
        setUndecorated(true);
            
        setDefaultCloseOperation(EXIT_ON_CLOSE);        
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        add(topPanel(), BorderLayout.NORTH);
        add(panelMeserosMesasContenedor(), BorderLayout.WEST);   
        add(panelMenu(), BorderLayout.CENTER);
        JPanel panel = new JPanel();
        //add(panel, BorderLayout.SOUTH);
        panel.setBackground(TopPane);
        
        //setUndecorated(true);
        panelComida.setVisible(false);
        panelBebidasAlcoholicas.setVisible(false);
        panelBebidasFrias.setVisible(false);
        panelBebidasCalientes.setVisible(false);
        panelPostres.setVisible(false);
        
        setVisible(true);        
    }
    
     public JPanel topPanel()
    {
        JPanel topPanel = new JPanel();
        Shape borde = new RoundRectangle2D.Double(0, 0, topPanel.getBounds().width, topPanel.getBounds().height, 27, 27);
        //AWTUtilities.setComponentMixingCutoutShape(topPanel, borde);
        topPanel.setBackground(TopPane);
        topPanel.setForeground(TopPane);
        topPanel.setLayout(new BorderLayout());
        //topPanel.setBorder(new LineBorder(Bordes, 10));  
        salirTop = new JButton("");       
        salirTop.setIcon(redimensionarImagen(new ImageIcon(getClass().getResource("/Iconos/apagar_OFF.png"))));
        salirTop.setRolloverIcon(redimensionarImagen(new ImageIcon(getClass().getResource("/Iconos/apagar_ON.png"))));
        salirTop.setRolloverSelectedIcon(redimensionarImagen(new ImageIcon(getClass().getResource("/Iconos/apagar_ON.png"))));
        salirTop.setSelectedIcon(redimensionarImagen(new ImageIcon(getClass().getResource("/Iconos/apagar_ON.png"))));
        salirTop.setBorderPainted(false);
        salirTop.setContentAreaFilled(false);
        salirTop.setBorder(new EmptyBorder(0,0,0,0));
        JPanel botones = new JPanel();
        botones.setBackground(TopPane);
        botones.add(salirTop);
        
        JLabel nombre = new JLabel("Krusty Krab");
        nombre.setBorder(new LineBorder(TopPane, 10));
        nombre.setForeground(fonttp);
        Font fuente = new Font("Agency FB", 1, 40);
        nombre.setFont(fuente);
        
      

        topPanel.add(botones, BorderLayout.EAST);
        topPanel.add(nombre, BorderLayout.CENTER);
        
        
        
             
        return topPanel;
    }
     
     private Icon redimensionarImagen(ImageIcon imagen) {
        Image entrada = imagen.getImage();
        Image salida = entrada.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        return new ImageIcon(salida);
    } 
    
    private Icon redimensionarImagenUs(ImageIcon imagen) {
        Image entrada = imagen.getImage();
        Image salida = entrada.getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        return new ImageIcon(salida);
    }  
     
    
     public JPanel panelMeseros()
    {   
        JPanel pestañaM = new JPanel(); 
        pestañaM.setBackground(Fondo);
        Border tituloBorder = BorderFactory.createLineBorder(Bordes, 1, true);
        //pestañaM.setBorder(tituloBorder);
        //pestañaM.setPreferredSize(new Dimension(350,500));
        pestañaM.setLayout(new BorderLayout(8,6));
        JLabel selectMesero = new JLabel("Selecciona Mesero");
        
        Color verdeI2 = new Color(129,198, 139);        
        selectMesero.setFont(fuente);
        selectMesero.setBackground(Color.red);
        
        //pestañaM.add(selectMesero, BorderLayout.NORTH); 
        //pestañaM.add(listaMeseros(), BorderLayout.WEST);         
        //pestañaM.add(new JSeparator(SwingConstants.VERTICAL), BorderLayout.EAST);
        JPanel imgContent = new JPanel();
        imgContent.setBackground(Fondo);
        imgContent.setLayout(new BorderLayout());
        
        
        JLabel userImg = new JLabel();     
        userImg.setIcon(redimensionarImagenUs(new ImageIcon(getClass().getResource("/Imagenes/user_3.png"))));
        //userImg.setBorder(new LineBorder(Color.BLACK, 1));  
         
        JLabel iniciarS = new JLabel("    Inciar Sesion como mesero");
        iniciarS.setForeground(Color.BLACK);
        iniciarS.setFont(labels);
        
        
        JPanel separa = new JPanel();
        separa.setBackground(separadores);
        separa.setBorder(new LineBorder(Fondo, 10));
        imgContent.add(iniciarS, BorderLayout.NORTH);
        imgContent.add(userImg, BorderLayout.CENTER);
        imgContent.add(separa, BorderLayout.SOUTH);
        
        JPanel east = new JPanel();
        east.setBackground(Fondo);
        east.setLayout(new BorderLayout());
        Component rigidArea = Box. createRigidArea(new Dimension(150, 0));
        
        JPanel separa2 = new JPanel();
        separa2.setBackground(TopPane);
        
        east.add(rigidArea,BorderLayout.CENTER); 
        nomusuarioMesero = new JLabel("");
        nomusuarioMesero.setFont(labels20);
        east.add(nomusuarioMesero, BorderLayout.SOUTH);
        //east.add(separa2,BorderLayout.EAST); 
        
        Component rigidArea2 = Box. createRigidArea(new Dimension(130, 0));
        
        imgContent.add(rigidArea2,BorderLayout.WEST); 
        imgContent.add(east,BorderLayout.EAST); 
        pestañaM.add(imgContent, BorderLayout.CENTER);
        
        
        
        pestañaM.add(inicioMesero(), BorderLayout.SOUTH);
        
        
        return pestañaM;
    }
    
    public JPanel inicioMesero()
    {
        JPanel content = new JPanel();
        content.setLayout(new GridLayout(1,2));
        content.setBorder(new LineBorder(Fondo, 10));
        content.add(formMesero());
        content.add(botonMesero());             
        return content;
    }
     
    
    public JPanel botonMesero()
    {
        JPanel content = new JPanel();
        content.setLayout(new GridLayout());
        content.setBorder(new LineBorder(Fondo, 10));
        iniciar = new JButton("Iniciar Sesion");
        iniciar.setForeground(Color.white);
        iniciar.setFocusPainted(false);     
        iniciar.setBackground(new Color(39, 169, 114));     
        content.add(iniciar);    
        
        
        
        return content;
    }
     
    public JPanel formMesero()
    {
        JPanel inicioMesero = new JPanel(); 
        inicioMesero.setBackground(Fondo);
        inicioMesero.setLayout(new GridBagLayout());        
        Font fuente = new Font("Century Gothic", Font.BOLD, 16);          
        JLabel lbliniciosesion = new JLabel("Mesero:");     
        lbliniciosesion.setFont(fuente);
        lbliniciosesion.setForeground(Color.BLACK);
        GridBagConstraints gbclbliniciosesion = new GridBagConstraints();
        gbclbliniciosesion.gridx = 0;
        gbclbliniciosesion.gridy = 0;
        gbclbliniciosesion.insets = new Insets(5,5,10,5);
        inicioMesero.add(lbliniciosesion, gbclbliniciosesion);  
             
        
        numMesero = new JComboBox();    
        GridBagConstraints gbcnumMesero = new GridBagConstraints();
        gbcnumMesero.gridx = 1;
        gbcnumMesero.gridy = 0;
        gbcnumMesero.insets = new Insets(5,5,10,5);
        inicioMesero.add(numMesero, gbcnumMesero);  
        
               
        JLabel lblcontraseña = new JLabel("Contraseña:");  
        lblcontraseña.setFont(fuente);
        lblcontraseña.setForeground(Color.BLACK);
        GridBagConstraints gbclblcontraseña = new GridBagConstraints();
        gbclblcontraseña.gridx = 0;
        gbclblcontraseña.gridy = 1;
        gbclblcontraseña.insets = new Insets(5,5,10,5);
        inicioMesero.add(lblcontraseña, gbclblcontraseña);
        
        
        txtcontraseñaM = new JPasswordField(); 
        txtcontraseñaM.setPreferredSize(new Dimension(100,25));
        GridBagConstraints gbctxtcontraseñaM = new GridBagConstraints();       
        gbctxtcontraseñaM.gridx = 1;
        gbctxtcontraseñaM.gridy = 1;
        gbctxtcontraseñaM.gridwidth = 3;       
        gbctxtcontraseñaM.fill = GridBagConstraints.HORIZONTAL;
        gbctxtcontraseñaM.insets = new Insets(5,5,5,5);
        inicioMesero.add(txtcontraseñaM, gbctxtcontraseñaM);
        
        
        
        return inicioMesero;
    }
    
    public JPanel listaMeseros ()
    {
        JPanel panelista = new JPanel();
        panelista.setBackground(PanelListaM);
        //Border tituloBorder = BorderFactory.createLineBorder(Color.WHITE, 10, true);
        //panelista.setBorder(tituloBorder);
        panelista.setPreferredSize(new Dimension(160,450));
        JList listam = new JList();
        panelista.add(listam);
        return panelista;
    }
      
    
    public JPanel listaMesas()
    {
        JPanel content = new JPanel();
        content.setBackground(Fondo2);
        content.setLayout(new BorderLayout());
        content.setBorder(new LineBorder(Fondo2, 3));
        
        JLabel top = new JLabel("Iniciar Mesa");
        top.setFont(labels20);
        top.setForeground(Color.BLACK);
        JPanel lmesas = new JPanel();
        lmesas.setBackground(Fondo2);
        JScrollPane scp  = new JScrollPane();
        lmesas.setPreferredSize(new Dimension(275,200));
        lmesas.setBorder(new LineBorder(Fondo, WIDTH));
        lmesas.add(panelMesasLista());
        lmesas.setLayout(new GridLayout());
        lmesas.setBorder(new LineBorder(new Color(217, 217, 217), 2));
        JPanel bot = new JPanel();
        bot.setBackground(Fondo2);
        bot.setLayout(new FlowLayout());

        
        
        
        
        content.add(top,BorderLayout.NORTH);
        content.add(bot,BorderLayout.SOUTH);
        bot.add(Box.createRigidArea(new Dimension(10,0)));
        content.add(lmesas, BorderLayout.CENTER);
        
        
        
        return content;
    }    
    public LightScrollPane panelMesasLista()
    {
        //String arreglo [] = {"dsfsfsdf","afasfsasf","asdasfasf","asfasfasf3","safasfasf5","asfasfasf6"};
        
        paneListasMeasas = new JPanel();
        paneListasMeasas.setBackground(Fondo);
        paneListasMeasas.setLayout(new GridLayout(0,4));
        paneListasMeasas.setPreferredSize(new Dimension(240,290)); 
        
        Herramientas.LightScrollPane scpNew = new LightScrollPane();
        scpNew.setViewportView(paneListasMeasas);
        scpNew.setPreferredSize(new Dimension(260,210));
        scpNew.setBackground(Fondo);
        
        return scpNew;
    }
    
    
    
    public JPanel pedidomesa ()
    {
        JPanel content = new JPanel();
        content.setBorder(new LineBorder(Fondo2, 3));
        //content.setLayout(new GridLayout());
        //content.setBackground(Color.BLACK);
        content.setLayout(new BorderLayout()); 
        content.setBackground(Fondo2);
        //content.setBorder(new LineBorder(Color.BLACK,WIDTH));
        
        JLabel top = new JLabel("Pedido de la Mesa");
        top.setForeground(Color.BLACK);
        top.setFont(labels20);
        //top.setLayout(new GridLayout());
        //content.setPreferredSize(new Dimension(500,500));
        JPanel pedido = new JPanel();
        //pedido.setBorder(new LineBorder(Color.BLACK,WIDTH));
        pedido.setPreferredSize(new Dimension(320,190));
        pedido.setLayout(new GridLayout());
        pedido.setBorder(new LineBorder(new Color(217, 217, 217), 2));
        //pedido.setBackground(Color.BLACK);
        
        JScrollPane jcp = new JScrollPane(); 
        //jcp.setBackground(Color.BLACK);
        //jcp.setPreferredSize(new Dimension(375,190));
        
        tablaPedido = new JTable();
        tablaPedido.setFocusable(false);
        tablaPedido.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tablaPedido.setRowHeight(16);
        tablaPedido.setBackground(Color.WHITE);
        tablaPedido.setFont(new Font("Century Gothic", Font.LAYOUT_RIGHT_TO_LEFT, 11));
        tablaPedido.setSelectionBackground(new Color(255, 174, 2 ) );
        tablaPedido.setSelectionForeground(Color.WHITE);
        tablaPedido.getTableHeader().setReorderingAllowed(false);
        tablaPedido.setRowMargin(0);
        tablaPedido.setShowVerticalLines(false);
        tablaPedido.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tablaPedido.getTableHeader().setOpaque(false);
        tablaPedido.getTableHeader().setBackground(new Color(0,0,0));
        tablaPedido.getTableHeader().setForeground(Color.WHITE);
        
        //Herramientas.LightScrollPane jcp = new LightScrollPane();
        jcp.setViewportView(tablaPedido);
        //jcp.setBackground(Color.WHITE);
        jcp.setBorder(new MatteBorder(0,0,0,0,Color.WHITE));
        jcp.getViewport().setBackground(Fondo);
        pedido.add(jcp);
        
        JPanel bots = new JPanel();
        bots.setBackground(Fondo2);
        enviarconfirmar = new JButton("Enviar y Confirmar");
        enviarconfirmar.setPreferredSize(new Dimension(170,25));
        enviarconfirmar.setBackground(btnEnviarConf);
        enviarconfirmar.setForeground(Color.WHITE);
        enviarconfirmar.setFocusPainted(false);

        eliminar = new JButton("Eliminar");
        eliminar.setPreferredSize(new Dimension(150,25));
        eliminar.setBackground(btnEliminar);
        eliminar.setForeground(Color.WHITE);
        eliminar.setFocusPainted(false);
        
        content.add(top,BorderLayout.NORTH);
        content.add(pedido,BorderLayout.CENTER);
        bots.add(eliminar);
        bots.add(enviarconfirmar);
        
        btnSalir = new JButton("Salir");
        bots.add(btnSalir);
        btnSalir.setBackground(btnSalirBack);
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setFocusPainted(false);
        
        JPanel down = new JPanel();
        down.setBackground(Fondo);
        down.setLayout(new BorderLayout());

        JPanel labels = new JPanel();
        labels.setBackground(Fondo);
        JLabel totalP = new JLabel("Total:");
        totalP.setFont(labels14);
        
        totalN = new JLabel("$");
        totalN.setFont(labels14);
        
        labels.add(totalP);
        labels.add(totalN);
        
        down.add(labels, BorderLayout.NORTH); 
        down.add(bots, BorderLayout.CENTER); 
        
        content.add(down, BorderLayout.SOUTH);  
                
        return content;
    }
    
    public JPanel pedidomesaCont()
    {
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        content.add(pedidomesa(), BorderLayout.CENTER);
        
        JPanel separa = new JPanel();
        separa.setBackground(separadores);
        content.add(separa, BorderLayout.NORTH);
        return content;
    }
    public JPanel panelMesas()
    {
        JPanel content = new JPanel();
        content.setBorder(new LineBorder(Fondo2, 5));
        //content.setLayout(new GridLayout(2,1,10,10));
        content.setLayout(new BorderLayout());
        content.add(listaMesas(), BorderLayout.NORTH);
        content.add(pedidomesaCont(),BorderLayout.CENTER);       
        
        return content;
    }
    public JPanel panelMeserosMesasContenedor()
    {
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        
        JPanel separa = new JPanel();
        separa.setBackground(separadores);
        separa.setBorder(new LineBorder(Fondo, 5));
        
        content.add(panelMeserosMesas(),BorderLayout.CENTER);
        content.add(separa, BorderLayout.EAST);
        return content;
    }
    
    public JPanel panelMeserosMesas ()
    {
        JPanel content = new JPanel();
        content.setLayout(new GridLayout());
        content.setBackground(Fondo);
        //content.setBorder(new LineBorder(Fondo, 10));    
        meseroscontent = new JPanel();
        meseroscontent.setBackground(Fondo);
        meseroscontent.setBorder(new LineBorder(Bordes));
        meseroscontent.setLayout(new GridLayout());
        
        mesascontent = new JPanel();
        mesascontent.setBackground(Fondo2);
        mesascontent.setBorder(new LineBorder(Bordes));
        mesascontent.setLayout(new GridLayout());
        
        //content.setPreferredSize(new Dimension(500,500));
        content.setLayout(new GridBagLayout());
        //content.setBorder(new LineBorder(Color.BLACK, WIDTH));       
        meseroscontent.add(meseros);      
        mesascontent.add(mesas);   
        GridBagConstraints config = new GridBagConstraints();
        config.gridx=0;
        config.gridy=0;
        config.gridwidth=2;
        config.gridheight=1;
        config.weightx=1.0;
        config.weighty=1.0;
        config.anchor=GridBagConstraints.CENTER;
        config.fill= GridBagConstraints.BOTH; 
        content.add(meseroscontent, config); 
        content.add(mesascontent, config);  
        mesascontent.setVisible(false);
        return content;
    }
    
    public JPanel panelListasMenu()
    {
        JPanel content = new JPanel();
        content.setBackground(Fondo);
        content.setBorder(new LineBorder(Bordes));
        content.setLayout(new GridBagLayout());
        GridBagConstraints config = new GridBagConstraints();
        config.gridx=0;
        config.gridy=0;
        config.gridwidth=2;
        config.gridheight=1;
        config.weightx=1.0;
        config.weighty=1.0;
        config.anchor=GridBagConstraints.CENTER;
        config.fill= GridBagConstraints.BOTH;
        
        content.add(panelComida, config);
        content.add(panelBebidasAlcoholicas, config);
        content.add(panelBebidasFrias, config);
        content.add(panelBebidasCalientes, config);
        content.add(panelPostres, config);
        content.add(panelBuscado, config);
        
        return content;
    }
    
    public LightScrollPane panelComida()
    {
        panelCPlatillos = new JPanel();
        panelCPlatillos.setBackground(panelPlatillos);
        Herramientas.LightScrollPane scp = new LightScrollPane();
        scp.setViewportView(panelCPlatillos);
        //JScrollPane scp = new JScrollPane(panelCPlatillos);    
        
        panelCPlatillos.setLayout(new GridLayout(0,4,2,2));                 
        return scp;
    }   
    
    public LightScrollPane panelBebidasAlcoholica()
    {
        panelCBebidasAlcoholicas = new JPanel();
        panelCBebidasAlcoholicas.setBackground(panelPlatillos);
        Herramientas.LightScrollPane scp = new LightScrollPane();
        scp.setViewportView(panelCBebidasAlcoholicas);
        panelCBebidasAlcoholicas.setLayout(new GridLayout(0,4,2,2));                 
        return scp;
    }
    
    public LightScrollPane panelBebidasFrias()
    {
        panelCBebidasFrias = new JPanel();
        panelCBebidasFrias.setBackground(panelPlatillos);
        Herramientas.LightScrollPane scp = new LightScrollPane();
        scp.setViewportView(panelCBebidasFrias);
        panelCBebidasFrias.setLayout(new GridLayout(0,4,2,2));                 
        return scp;
    }
    
    public LightScrollPane panelBebidasCalientes()
    {
        panelCBebidasCalientes = new JPanel();
        panelCBebidasCalientes.setBackground(panelPlatillos);
        Herramientas.LightScrollPane scp = new LightScrollPane();
        scp.setViewportView(panelCBebidasCalientes);
        panelCBebidasCalientes.setLayout(new GridLayout(0,4,2,2));                 
        return scp;
    }
    
    public LightScrollPane panelPostre()
    {
        panelCPostres = new JPanel();
        panelCPostres.setBackground(panelPlatillos);
        Herramientas.LightScrollPane scp = new LightScrollPane();
        scp.setViewportView(panelCPostres);
        panelCPostres.setLayout(new GridLayout(0,4,2,2));                 
        return scp;
    }
    
    public LightScrollPane panelBuscado()
    {
        panelCBuscado = new JPanel();
        panelCBuscado.setBackground(panelPlatillos);
        Herramientas.LightScrollPane scp = new LightScrollPane();
        scp.setViewportView(panelCBuscado);
        panelCBuscado.setLayout(new GridLayout(0,4,2,2));                 
        return scp;
    } 
    
    public JPanel panelMenu()
    {
        JPanel principal = new JPanel();
        principal.setLayout(new BorderLayout());
        principal.setBackground(Fondo);
        principal.setBorder(new LineBorder(Bordes, 1));    
        //principal.setBorder(new LineBorder(Color.BLACK));
        JPanel botones = new JPanel();
        botones.setBorder(new LineBorder(Bordes, 2));
        botones.setLayout(new GridLayout(1,4,5,5));
        platillosBtnMenu = new JButton("COMIDA");
        botones.setBackground(Fondo);
        //platillosBtnMenu.setBackground();
        platillosBtnMenu.setForeground(Color.white);
        platillosBtnMenu.setBackground(botonesColor);
        platillosBtnMenu.setFocusPainted(false);
        
        bebidasComboBoxMenu = new JComboBox();
            bebidasComboBoxMenu.addItem("BEBIDAS");
            bebidasComboBoxMenu.addItem("ALCOHOLICAS");
            bebidasComboBoxMenu.addItem("FRIAS");
            bebidasComboBoxMenu.addItem("CALIENTES");
            bebidasComboBoxMenu.setFocusCycleRoot(false);
            bebidasComboBoxMenu.setBackground(botonesColor);
            bebidasComboBoxMenu.setForeground(Color.white);             
        
        postresBtnMenu = new JButton("POSTRES");
        postresBtnMenu.setBackground(botonesColor);
        postresBtnMenu.setForeground(Color.white);
        postresBtnMenu.setFocusPainted(false);
        
        buscarPlat = new JButton("BUSCAR");
        buscarPlat.setBackground(botonesColor);
        buscarPlat.setForeground(Color.white);
        buscarPlat.setFocusPainted(false);
        
        
        botones.add(platillosBtnMenu);
        botones.add(bebidasComboBoxMenu);
        botones.add(postresBtnMenu); 
        botones.add(buscarPlat);
        principal.add(botones, BorderLayout.NORTH);
        JSeparator sep = new JSeparator(SwingConstants.VERTICAL);
        sep.setBorder(new LineBorder(Bordes, 20));
        principal.add(sep, BorderLayout.WEST);
        principal.add(panelListasMenu(), BorderLayout.CENTER);
        
        
        return principal;      
        
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
    //SETS Y GETS
    
    public JPanel getPanelMeseros()
    {
        return meseroscontent;
    }
    
    public JPanel getPanelMesas()
    {
        return mesascontent;
    }
    
    ///////INICIO/////////////////////
    
    public JComboBox getNMeseroIn()
    {
        return numMesero;
    }
    public JLabel getMostrarNomUsuario()
    {
        return nomusuarioMesero;
    }
    public JPasswordField getContraseñaM()
    {
        return txtcontraseñaM;
    }
    
    //////////////////////////////////////
    
    //PANELES CAMBIABLES DEL MENU ///////////////////////////////
    public LightScrollPane getPanelComida()
    {
        return panelComida;
    }
    
    public LightScrollPane getPanelBebidasAlcoholicas()
    {
        return panelBebidasAlcoholicas;
    }
    
    public LightScrollPane getPanelBebidasFrias()
    {
        return panelBebidasFrias;
    }
    
    public LightScrollPane getPanelBebidasCalientes()
    {
        return panelBebidasCalientes;
    }
    
    public LightScrollPane getPanelPostres()
    {
        return panelPostres;
    }
    public LightScrollPane getPanelBuscado()
    {
        return panelBuscado;
    }
    ///////////////////////////////////////////////////////////////
    //PANELES CONTENEDORES DLE MENU///////////////////////////////
    public JPanel getPanelCPlatillos()
    {
        return panelCPlatillos;
    }
    
    public JPanel getPanelCBebidasAlcoholicas()
    {
        return panelCBebidasAlcoholicas;
    }
    
    public JPanel getPanelCBebidasFrias()
    {
        return panelCBebidasFrias;
    }
    
    public JPanel getPanelCCalientes()
    {
        return panelCBebidasCalientes;
    }
    
    public JPanel getPanelCPostres()
    {
        return panelCPostres;
    }
    
    public JButton getBtnMenuPlatillos()
    {
        return platillosBtnMenu;
    }
    
    public JComboBox getComboBoxMenuBebidas()
    {
        return bebidasComboBoxMenu;
    }
    
    public JButton getBtnMenuPostres()
    {
        return postresBtnMenu;
    }
    
    public JButton getBtnMenuBuscar()
    {
        return buscarPlat;
    }
    
    
    /////PANEL MESAS
    public JTable getTablaPedido()
    {
        return tablaPedido;
    }
    
    
    public JPanel getPanelMesasLista()
    {
        return paneListasMeasas;
    }
    
    public JLabel getTotalLabel()
    {
        return totalN;
    }
    
    public JButton getButtonEliminar()
    {
        return eliminar;
    }
    
    public JButton getButtonSalirTb()
    {
        return btnSalir;
    }
    
    public JButton getButtonEnviar()
    {
        return enviarconfirmar;
    }
    ///////////////////////////////////////////////////////////////
    
    
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
   //CONECTAR CONTROLADOR
    
    public void conectarControlador(Controlador_Restaurante controlador)
    {
        ////PANEL MESEROS     
        salirTop.addActionListener(controlador);
        salirTop.setActionCommand("SALIRTOP");
        
        iniciar.addActionListener(controlador);
        iniciar.setActionCommand("INICIAR");
        
        numMesero.addActionListener(controlador);
        numMesero.setActionCommand("NUMEROMESERO");
        
        //PANEL MESAS
        enviarconfirmar.addActionListener(controlador);
        enviarconfirmar.setActionCommand("ENVIARCONFIRMAR");
        
        eliminar.addActionListener(controlador);
        eliminar.setActionCommand("ELIMINARFP");
        
        btnSalir.addActionListener(controlador);
        btnSalir.setActionCommand("SALIRBTN");
        
        //PANEL MENU       
        platillosBtnMenu.addActionListener(controlador);
        platillosBtnMenu.setActionCommand("COMIDA");
               
        bebidasComboBoxMenu.addActionListener(controlador);
        bebidasComboBoxMenu.setActionCommand("BEBIDASMENU");   
        
        postresBtnMenu.addActionListener(controlador);
        postresBtnMenu.setActionCommand("POSTRES");
        
        buscarPlat.addActionListener(controlador);
        buscarPlat.setActionCommand("BUSCARPLAT");
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
    
     public static void main (String args [])
    {
        Ventana_Meseros vm  = new Ventana_Meseros();
    }
}
