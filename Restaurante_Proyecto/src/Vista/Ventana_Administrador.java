/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;
import javax.swing.*;
import Controlador.Controlador_Restaurante;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import static java.awt.FlowLayout.LEADING;
import static java.awt.FlowLayout.LEFT;
import static java.awt.FlowLayout.RIGHT;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
/**
 *
 * @author RodrigoLP
 */
public class Ventana_Administrador extends JFrame
{     
    ////////COLORES
    //Tema Oscuro
    /*
    Color Fondo = new Color(65, 65, 65 );
    Color Fondo2 = new Color(65, 65, 65 );
    Color TopPane = new Color(21, 86, 131 );
    Color botonesPrin = new Color(82, 136, 138 );
    Color botonesBD = new Color(45, 117, 170  );
    Color Caceptar = new Color(98, 147, 81);
    Color Ccancelar = new Color(164, 50, 50);
    Color colorSelect = new Color(215, 124, 0 );
    Color salmon = new Color(240, 203, 176 );   
    */
    
    Color Fondo = new Color(255, 255, 255 );
    Color Fondo2 = new Color(255, 255, 255 );
    Color TopPane = new Color(2, 60, 72);
    Color botonesPrin = new Color(22, 148, 117 );
    Color botonesBD = new Color(62, 0, 66);
    Color Caceptar = new Color(98, 147, 81);
    Color Ccancelar = new Color(164, 50, 50);
    Color colorSelect = new Color(215, 124, 0 );
    Color salmon = new Color(240, 203, 176 );   
    Color separadores = new Color(30, 33, 43);
    
    Font BDFuente = new Font("EngraversGothic BT", 4, 16);
   
    
/////////////////////
    
    JButton salirTopA = new JButton("");  
    
    JPanel botonesPrincipales;
    JPanel productos = panelAdminProductos();
    JPanel usuarios = panelAdminUsuarios();
    JPanel admProd;
    JPanel admUsu;
    
    JPanel formProd = panelAdminProductosReg();
    JPanel formUs = panelAdminUsuariosReg();
    JPanel admProdReg;
    JPanel admUsReg;
    
    JPanel formBebidasAlcoholicas = panelAdminBebidasAlcoholicas();
    JPanel admBebidasAlcoholicas;
    
    JButton adminProductos; 
        JButton adminPlatillos;
        JButton adminBebidas;
        JComboBox adminBebidasMenu;
        JButton adminPostres;   
        JLabel bdnombre;
        JTextField nombre;
        JTextArea descripcion;
        JTextField  precio;
        JLabel mostrarIMG;
        JTextField url;
        JButton selectImg;
        JButton aceptar;
        JButton cancelar;
        
        JLabel bdnombreBA;
        JTextField nombreba;
        JTextArea descripcionba;
        JTextField  precioba;
        JLabel mostrarIMGba;
        JTextField urlba;
        JButton selectImgba;
        JButton aceptarba;
        JButton cancelarba;
        
        JComboBox tiposAlcohol;
    
    JButton adminUsuarios; 
        JButton adminCajero;
        JButton adminBarman;
        JButton adminCocinero;
        JButton adminMeseros;
        
        JLabel bdnombre_us;
        JTextField nombre_us;
        JTextField apellidos_us;
        JTextField fecha_n_us;
        JTextField  fecha_in_us;
        JTextField fecha_sal_us;
        JTextField nameusuario_us;
        JPasswordField contraseña_us;
        JButton aceptar_us;
        JButton cancelar_us;
        
        
    JButton NMesasBP;  
    
    
    JButton agregar;
    JButton eliminar;
    JButton actualizar;
    JButton buscar;
    JTextField bus;
    JTable tablaprin;
      
    
    PreparedStatement ps;
    ResultSet res;

    //CAMBIOS PARA MOVER SIN BORDES
    private JFrame control;
    int pX,pY;
    JLabel labelTitulo;
    
    
    public String nombrebd="";
    public Ventana_Administrador()
    {
        super("ADMINISTRADORES");
        setSize(920,650);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        add(principal());

        //CAMBIOS PARA MOVERSE SIN BORDES

        control=this;
        setUndecorated(true);
        setLocationRelativeTo(null);
        setVisible(true);
        
    }
    
    public JPanel principal()
    {
        JPanel principal = new JPanel();
        principal.setLayout(new BorderLayout());
        principal.add(top(), BorderLayout.NORTH);
        principal.add(center(), BorderLayout.CENTER);
        return principal;
    }
    public JPanel top()
    {
        JPanel top = new JPanel();
        top.setLayout(new BorderLayout());
        top.setBackground(TopPane);
        JLabel panelC = new JLabel("Panel de Control");
        panelC.setForeground(Color.white);
        panelC.setBackground(TopPane);
        Font fuente = new Font("Agency FB", 1, 25);
        panelC.setFont(fuente);
        
        salirTopA = new JButton("");       
        salirTopA.setIcon(redimensionarImagen(new ImageIcon(getClass().getResource("/Iconos/apagar_OFF.png"))));
        salirTopA.setRolloverIcon(redimensionarImagen(new ImageIcon(getClass().getResource("/Iconos/apagar_ON.png"))));
        salirTopA.setRolloverSelectedIcon(redimensionarImagen(new ImageIcon(getClass().getResource("/Iconos/apagar_ON.png"))));
        salirTopA.setSelectedIcon(redimensionarImagen(new ImageIcon(getClass().getResource("/Iconos/apagar_ON.png"))));
        salirTopA.setBorderPainted(false);
        salirTopA.setContentAreaFilled(false);
        salirTopA.setBorder(new EmptyBorder(0,0,0,0));
        JPanel botones = new JPanel();
        botones.setBackground(TopPane);
        botones.add(salirTopA);
        
        
        Component rigidArea = Box. createRigidArea(new Dimension(40, 0));
        top.add(rigidArea, BorderLayout.WEST);
        top.add(panelC, BorderLayout.CENTER);
        top.add(botones, BorderLayout.EAST);

        //CAMBIOS PARA MOVER SIN BORDES
        top.addMouseListener(new MouseAdapter() {
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

        top.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent me) {

                control.setLocation(control.getLocation().x + me.getX() - pX,
                        control.getLocation().y + me.getY() - pY);
            }
        });

        return top;
    }
    
    private Icon redimensionarImagen(ImageIcon imagen) {
        Image entrada = imagen.getImage();
        Image salida = entrada.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        return new ImageIcon(salida);
    } 
    
    public JPanel center()
    {
        JPanel prin = new JPanel();
        prin.setLayout(new BorderLayout());
      
        prin.add(panelSuperior(), BorderLayout.NORTH);
        prin.add(editorBD(),BorderLayout.CENTER);
        JButton salirr = new JButton("Salir");
        //prin.add(salirr,BorderLayout.SOUTH);
        return prin;
    }
    public JPanel panelSuperior()
    {
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        
        JPanel separa = new JPanel();
        separa.setBackground(separadores);
        
        
        content.add(contenido(), BorderLayout.CENTER);
        content.add(separa, BorderLayout.SOUTH);
        return content;
    }

    
    public JPanel contenido()
    {
        JPanel content = new JPanel();
        content.setBorder(new LineBorder(Fondo, 10));
        content.setBackground(Fondo);
        content.setLayout(new GridLayout(1,3));
        
        JPanel botonesP = new JPanel();
        botonesP.add(botonesPrincipales());
        botonesP.setBackground(Fondo);
        
        //botonesP.setBorder(new LineBorder(Color.BLUE, WIDTH));
        
        JPanel botonesS = new JPanel();
        botonesS.add(panelBotonesSecundarios());
        botonesS.setBackground(Fondo);
        //content.setBorder(new LineBorder(Color.BLUE, WIDTH));
        
        
        JPanel formularios = new JPanel(); 
        formularios.add(panelFormularios());        
        formularios.setBackground(Fondo);
        //content3.setBorder(new LineBorder(Color.BLUE, WIDTH));
        
        content.add(botonesP);
        content.add(botonesS);
        content.add(formularios);
        
        return content;
    }
    
    public JPanel botonesPrincipales()
    {
        JPanel content = new JPanel();
        content.setBackground(Fondo);
        content.setLayout(new GridLayout(4,1,10,10));
        //content.add(Box.createRigidArea(new Dimension(10, 10)));
        adminProductos = new JButton("Administrar Productos");
        adminProductos.setPreferredSize(new Dimension(220,50));
        adminProductos.setBackground(botonesPrin);
        adminProductos.setForeground(Color.white);
        adminProductos.setFocusPainted(false);
        adminUsuarios = new JButton("Administrar Usuarios");  
        adminUsuarios.setBackground(botonesPrin);
        adminUsuarios.setForeground(Color.white);
        adminUsuarios.setFocusPainted(false);
        content.add(Box.createRigidArea(new Dimension(10, 10)));
        content.add(adminProductos);
        content.add(adminUsuarios); 
        return content;
    }
    
    public JPanel panelBotonesSecundarios()
    {
        JPanel content = new JPanel();
        content.setLayout(new GridBagLayout());
        admProd = new JPanel();
        admProd.setBackground(Fondo);
        admUsu = new JPanel();
        admUsu.setBackground(Fondo);
        
        admProd.add(productos);
        admUsu.add(usuarios);
        
        GridBagConstraints config = new GridBagConstraints();
        config.gridx=0;
        config.gridy=0;
        config.gridwidth=2;
        config.gridheight=1;
        config.weightx=1.0;
        config.weighty=1.0;
        config.anchor=GridBagConstraints.CENTER;
        config.fill= GridBagConstraints.BOTH; 
        content.add(admUsu, config); 
        content.add(admProd, config);   
        admProd.setVisible(false);
        admUsu.setVisible(false);
        
        
        return content;
    }
    
    public JPanel panelFormularios()
    {
        JPanel content = new JPanel();        
        content.setLayout(new GridBagLayout());
        
        admProdReg = new JPanel();
        admProdReg.setBackground(Fondo);
        
        admUsReg = new JPanel();
        admUsReg.setBackground(Fondo);
        
        admBebidasAlcoholicas = new JPanel();
        admBebidasAlcoholicas.setBackground(Fondo);
        
        admProdReg.add(formProd);
        admUsReg.add(formUs);
        admBebidasAlcoholicas.add(formBebidasAlcoholicas);
        
        GridBagConstraints config = new GridBagConstraints();
        config.gridx=0;
        config.gridy=0;
        config.gridwidth=2;
        config.gridheight=1;
        config.weightx=1.0;
        config.weighty=1.0;
        config.anchor=GridBagConstraints.CENTER;
        config.fill= GridBagConstraints.BOTH; 
        content.add(admUsReg, config); 
        content.add(admProdReg, config);   
        content.add(admBebidasAlcoholicas, config);
        admProdReg.setVisible(false);
        admUsReg.setVisible(false);     
        admBebidasAlcoholicas.setVisible(false);  
        return content;
    }
    
    public JPanel panelAdminProductos()
    {
        JPanel content = new JPanel();
        content.setLayout(new GridLayout(5,1,20,20));   
        content.setBackground(Fondo);
        adminPlatillos = new JButton("PLATILLOS");
        adminPlatillos.setBackground(botonesPrin);
        adminPlatillos.setForeground(Color.white);
        adminPlatillos.setFocusPainted(false);
        //adminBebidas = new JButton("BEBIDAS");
        //adminBebidas.setBackground(botonesPrin);
        //adminBebidas.setForeground(Color.white);
        //adminBebidas.setFocusPainted(false);
        
        adminBebidasMenu = new JComboBox();
		adminBebidasMenu.addItem("BEBIDAS");
		adminBebidasMenu.addItem("ALCOHOLICAS");
		adminBebidasMenu.addItem("FRIAS");
                adminBebidasMenu.addItem("CALIENTES");
                adminBebidasMenu.setBackground(botonesPrin);
                adminBebidasMenu.setForeground(Color.white);
                adminBebidasMenu.setFocusCycleRoot(false);
        
        adminPostres = new JButton("POSTRES");
        adminPostres.setBackground(botonesPrin);
        adminPostres.setForeground(Color.white);
        adminPostres.setFocusPainted(false);
        NMesasBP = new JButton("Numero de Mesas");
        NMesasBP.setBackground(new Color(111, 24, 5 ));
        NMesasBP.setForeground(Color.white);
        NMesasBP.setFocusPainted(false);
        
        content.add(Box.createRigidArea(new Dimension(10, 10)));
        content.add(adminPlatillos);
        //content.add(adminBebidas);
        content.add(adminBebidasMenu);
        content.add(adminPostres);
        content.add(NMesasBP);
        return content;
    }   
    
    public JPanel panelAdminProductosReg()
    {
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        
        JPanel reg = new JPanel();
        reg.setLayout(new GridBagLayout());
        
      
        
        JPanel bot = new JPanel();
        
        JPanel separador = new JPanel();
        separador.setBorder(new LineBorder(Fondo, 10));
        separador.setBackground(Color.WHITE);                    
        
        JLabel titulo = new JLabel("Agregar o Actualizar en: ");  
        titulo.setForeground(Color.BLACK);
        titulo.setAlignmentX(LEADING);
        GridBagConstraints gbctitulo = new GridBagConstraints();
        gbctitulo.gridx = 0;
        gbctitulo.gridy = 0;
        gbctitulo.gridwidth = 2;
        gbctitulo.fill = GridBagConstraints.HORIZONTAL;
        reg.add(titulo, gbctitulo);
        
        bdnombre = new JLabel();  
        bdnombre.setFont(BDFuente);
        GridBagConstraints gbcbdname = new GridBagConstraints();
        bdnombre.setPreferredSize(new Dimension(100,20));
        gbcbdname.gridx = 2;
        gbcbdname.gridy = 0;
        gbcbdname.insets = new Insets(3,0,3,3);
        reg.add(bdnombre, gbcbdname);
        
        JLabel nombrelb = new JLabel("Nombre: ");
        nombrelb.setForeground(Color.BLACK);
        nombrelb.setPreferredSize(new Dimension(80,25));
        nombrelb.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        GridBagConstraints gbcnombrelb = new GridBagConstraints();
        gbcnombrelb.gridx = 0;
        gbcnombrelb.gridy = 1;
        reg.add(nombrelb, gbcnombrelb);
        
        nombre = new JTextField();
        nombre.setPreferredSize(new Dimension(150,25));
        GridBagConstraints gbcnombre = new GridBagConstraints();
        gbcnombre.gridx = 1;
        gbcnombre.gridy = 1;
        gbcnombre.gridwidth = 2;
        gbcnombre.fill = GridBagConstraints.HORIZONTAL;
        gbcnombre.insets = new Insets(3,0,3,3);
        reg.add(nombre, gbcnombre);        
        
        JLabel descripcionlb = new JLabel("Descripcion: ");
        descripcionlb.setForeground(Color.BLACK);
        descripcionlb.setPreferredSize(new Dimension(80,25));
        descripcionlb.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        GridBagConstraints gbcdescripcionlb = new GridBagConstraints();
        gbcdescripcionlb.gridx = 0;
        gbcdescripcionlb.gridy = 2;
        reg.add(descripcionlb, gbcdescripcionlb);
        
        descripcion = new JTextArea(150,65);
        JScrollPane scroll = new JScrollPane(descripcion);       
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setPreferredSize(new Dimension(150,65));
        GridBagConstraints gbcdescripcion = new GridBagConstraints();
        gbcdescripcion.gridx = 1;
        gbcdescripcion.gridy = 2;
        gbcdescripcion.gridwidth = 2;
        gbcdescripcion.fill = GridBagConstraints.HORIZONTAL;
        gbcdescripcion.insets = new Insets(3,0,3,3);
        reg.add(scroll, gbcdescripcion);      
        
        JLabel preciolb = new JLabel("Precio: ");
        preciolb.setForeground(Color.BLACK);
        preciolb.setPreferredSize(new Dimension(80,25));
        preciolb.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        GridBagConstraints gbcpreciolb = new GridBagConstraints();
        gbcpreciolb.gridx = 0;
        gbcpreciolb.gridy = 3;
        reg.add(preciolb, gbcpreciolb); 
        
        precio = new JTextField();
        precio.setPreferredSize(new Dimension(150,25));
        GridBagConstraints gbcprecio = new GridBagConstraints();
        gbcprecio.gridx = 1;
        gbcprecio.gridy = 3;
        gbcprecio.gridwidth = 2;
        gbcprecio.fill = GridBagConstraints.HORIZONTAL;
        gbcprecio.insets = new Insets(3,0,3,3);
        reg.add(precio, gbcprecio); 
        
        JLabel urllb = new JLabel("Imagen: ");
        urllb.setForeground(Color.BLACK);
        urllb.setPreferredSize(new Dimension(80,25));
        GridBagConstraints gbcurllb = new GridBagConstraints();
        gbcurllb.gridx = 0;
        gbcurllb.gridy = 4;
        reg.add(urllb, gbcurllb); 
             
        
        mostrarIMG = new JLabel();
        mostrarIMG.setPreferredSize(new Dimension(80,60));
        mostrarIMG.setBorder(new LineBorder(Color.BLACK));
        GridBagConstraints gbcmostrarIMG = new GridBagConstraints();
        gbcmostrarIMG.gridx = 1;
        gbcmostrarIMG.gridy = 4;
        gbcmostrarIMG.insets = new Insets(3,0,3,3);
        reg.add(mostrarIMG, gbcmostrarIMG); 
              
        selectImg = new JButton();
        String texto= "Seleccinar IMG";
        selectImg.setText("<html>"+texto.replaceAll("\\n", "<br>")+"</html>");
        selectImg.setPreferredSize(new Dimension(100,40));
        selectImg.setBackground(colorSelect);
        selectImg.setForeground(Color.BLACK);
        selectImg.setFocusPainted(false);
        GridBagConstraints gbcselectImg = new GridBagConstraints();
        gbcselectImg.gridx = 2;
        gbcselectImg.gridy = 4;
        gbcselectImg.insets = new Insets(3,3,3,3);
        reg.add(selectImg, gbcselectImg); 
        
        url = new JTextField();
        url.setPreferredSize(new Dimension(70,25));
        GridBagConstraints gbcurl = new GridBagConstraints();
        gbcurl.gridx = 1;
        gbcurl.gridy = 5;
        gbcurl.gridwidth = 2;
        gbcurl.fill = GridBagConstraints.HORIZONTAL;
        gbcurl.insets = new Insets(3,0,3,3);
        reg.add(url, gbcurl); 
              
        bot.setLayout(new GridLayout(1,2,10,10));
        aceptar = new JButton("Aceptar");
        aceptar.setBackground(Caceptar);
        aceptar.setForeground(Color.white);
        aceptar.setFocusPainted(false);
        
        cancelar = new JButton("Cancelar");
        cancelar.setBackground(Ccancelar);
        cancelar.setForeground(Color.white);
        cancelar.setFocusPainted(false);
        
        bot.add(aceptar);
        bot.add(cancelar);
        
        content.add(reg, BorderLayout.CENTER);
        content.add(bot, BorderLayout.SOUTH);
        
        reg.setBackground(Fondo);
        bot.setBackground(Fondo);
        
        return content;
    }
    
    
    
    public JPanel panelAdminBebidasAlcoholicas()
    {
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        
        JPanel reg = new JPanel();
        reg.setLayout(new GridBagLayout());
        
        JPanel bot = new JPanel();
        
        JLabel titulo = new JLabel("Agregar o Actualizar en: ");  
        titulo.setForeground(Color.BLACK);
        titulo.setAlignmentX(LEADING);
        GridBagConstraints gbctitulo = new GridBagConstraints();
        gbctitulo.gridx = 0;
        gbctitulo.gridy = 0;
        gbctitulo.gridwidth = 2;
        gbctitulo.fill = GridBagConstraints.HORIZONTAL;
        reg.add(titulo, gbctitulo);
        
        bdnombreBA = new JLabel();  
        bdnombreBA.setFont(BDFuente);
        GridBagConstraints gbcbdname = new GridBagConstraints();
        bdnombreBA.setPreferredSize(new Dimension(100,20));
        gbcbdname.gridx = 2;
        gbcbdname.gridy = 0;
        gbcbdname.insets = new Insets(3,0,3,3);
        reg.add(bdnombreBA, gbcbdname);
        
        JLabel nombrelb = new JLabel("Nombre: ");
        nombrelb.setForeground(Color.BLACK);
        nombrelb.setPreferredSize(new Dimension(80,25));
        nombrelb.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        GridBagConstraints gbcnombrelb = new GridBagConstraints();
        gbcnombrelb.gridx = 0;
        gbcnombrelb.gridy = 1;
        reg.add(nombrelb, gbcnombrelb);
        
        nombreba = new JTextField();
        nombreba.setPreferredSize(new Dimension(150,25));
        GridBagConstraints gbcnombre = new GridBagConstraints();
        gbcnombre.gridx = 1;
        gbcnombre.gridy = 1;
        gbcnombre.gridwidth = 2;
        gbcnombre.fill = GridBagConstraints.HORIZONTAL;
        gbcnombre.insets = new Insets(3,0,3,3);
        reg.add(nombreba, gbcnombre);        
        
        JLabel descripcionlb = new JLabel("Descripcion: ");
        descripcionlb.setForeground(Color.BLACK);
        descripcionlb.setPreferredSize(new Dimension(80,25));
        descripcionlb.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        GridBagConstraints gbcdescripcionlb = new GridBagConstraints();
        gbcdescripcionlb.gridx = 0;
        gbcdescripcionlb.gridy = 2;
        reg.add(descripcionlb, gbcdescripcionlb);
        
        descripcionba = new JTextArea(150,65);
        JScrollPane scroll = new JScrollPane(descripcionba);       
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setPreferredSize(new Dimension(150,65));
        GridBagConstraints gbcdescripcion = new GridBagConstraints();
        gbcdescripcion.gridx = 1;
        gbcdescripcion.gridy = 2;
        gbcdescripcion.gridwidth = 2;
        gbcdescripcion.fill = GridBagConstraints.HORIZONTAL;
        gbcdescripcion.insets = new Insets(3,0,3,3);
        reg.add(scroll, gbcdescripcion);      
        
        JLabel preciolb = new JLabel("Precio: ");
        preciolb.setForeground(Color.BLACK);
        preciolb.setPreferredSize(new Dimension(80,25));
        preciolb.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        GridBagConstraints gbcpreciolb = new GridBagConstraints();
        gbcpreciolb.gridx = 0;
        gbcpreciolb.gridy = 3;
        reg.add(preciolb, gbcpreciolb); 
        
        precioba = new JTextField();
        precioba.setPreferredSize(new Dimension(150,25));
        GridBagConstraints gbcprecio = new GridBagConstraints();
        gbcprecio.gridx = 1;
        gbcprecio.gridy = 3;
        gbcprecio.gridwidth = 2;
        gbcprecio.fill = GridBagConstraints.HORIZONTAL;
        gbcprecio.insets = new Insets(3,0,3,3);
        reg.add(precioba, gbcprecio);
        
        JLabel tipoAlcohol = new JLabel("Tipo de Alcohol:");
        tipoAlcohol.setForeground(Color.BLACK);
        tipoAlcohol.setPreferredSize(new Dimension(80,25));
        tipoAlcohol.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        GridBagConstraints gbctipoAlcohol = new GridBagConstraints();
        gbctipoAlcohol.gridx = 0;
        gbctipoAlcohol.gridy = 4;
        reg.add(tipoAlcohol, gbctipoAlcohol);        
        
        tiposAlcohol = new JComboBox();
            tiposAlcohol.addItem("Whisky");
            tiposAlcohol.addItem("Vodka");
            tiposAlcohol.addItem("Ron");
            tiposAlcohol.addItem("Tequila");
            tiposAlcohol.addItem("Cervezas");
            tiposAlcohol.addItem("Ginebra");
        tiposAlcohol.setPreferredSize(new Dimension(150,25));
        GridBagConstraints gbctiposAlcohol = new GridBagConstraints();
        gbctiposAlcohol.gridx = 1;
        gbctiposAlcohol.gridy = 4;
        gbctiposAlcohol.gridwidth = 2;
        gbctiposAlcohol.fill = GridBagConstraints.HORIZONTAL;
        gbctiposAlcohol.insets = new Insets(3,0,3,3);
        reg.add(tiposAlcohol, gbctiposAlcohol);
        
        JLabel urllb = new JLabel("Imagen: ");
        urllb.setForeground(Color.BLACK);
        urllb.setPreferredSize(new Dimension(80,25));
        GridBagConstraints gbcurllb = new GridBagConstraints();
        gbcurllb.gridx = 0;
        gbcurllb.gridy = 5;
        reg.add(urllb, gbcurllb); 
             
        
        mostrarIMGba = new JLabel();
        mostrarIMGba.setPreferredSize(new Dimension(80,60));
        mostrarIMGba.setBorder(new LineBorder(Color.BLACK));
        GridBagConstraints gbcmostrarIMG = new GridBagConstraints();
        gbcmostrarIMG.gridx = 1;
        gbcmostrarIMG.gridy = 5;
        gbcmostrarIMG.insets = new Insets(3,0,3,3);
        reg.add(mostrarIMGba, gbcmostrarIMG); 
              
        selectImgba = new JButton();
        String texto= "Seleccinar IMG";
        selectImgba.setText("<html>"+texto.replaceAll("\\n", "<br>")+"</html>");
        selectImgba.setPreferredSize(new Dimension(100,40));
        selectImgba.setBackground(colorSelect);
        selectImgba.setForeground(Color.BLACK);
        selectImgba.setFocusPainted(false);
        GridBagConstraints gbcselectImg = new GridBagConstraints();
        gbcselectImg.gridx = 2;
        gbcselectImg.gridy = 5;
        gbcselectImg.insets = new Insets(3,3,3,3);
        reg.add(selectImgba, gbcselectImg); 
        
        urlba = new JTextField();
        urlba.setPreferredSize(new Dimension(70,25));
        GridBagConstraints gbcurl = new GridBagConstraints();
        gbcurl.gridx = 1;
        gbcurl.gridy = 6;
        gbcurl.gridwidth = 2;
        gbcurl.fill = GridBagConstraints.HORIZONTAL;
        gbcurl.insets = new Insets(3,0,3,3);
        reg.add(urlba, gbcurl); 
              
        bot.setLayout(new GridLayout(1,2,10,10));
        aceptarba = new JButton("Aceptar");
        aceptarba.setBackground(Caceptar);
        aceptarba.setForeground(Color.white);
        aceptarba.setFocusPainted(false);
        
        cancelarba = new JButton("Cancelar");
        cancelarba.setBackground(Ccancelar);
        cancelarba.setForeground(Color.white);
        cancelarba.setFocusPainted(false);
        
        bot.add(aceptarba);
        bot.add(cancelarba);
        
        content.add(reg, BorderLayout.CENTER);
        content.add(bot, BorderLayout.SOUTH);
        
        reg.setBackground(Fondo);
        bot.setBackground(Fondo);
        
        return content;
    }
    
    
    public JPanel panelAdminUsuarios()
    {
        JPanel content = new JPanel();
        content.setLayout(new GridLayout(5,1,20,20));   
        content.setBackground(Fondo);
        adminCajero = new JButton("CAJERO");
        adminCajero.setBackground(botonesPrin);
        adminCajero.setForeground(Color.white);
        adminCajero.setFocusPainted(false);
        adminBarman = new JButton("BARMAN");
        adminBarman.setBackground(botonesPrin);
        adminBarman.setForeground(Color.white);
        adminBarman.setFocusPainted(false);
        adminCocinero = new JButton("COCINERO");
        adminCocinero.setBackground(botonesPrin);
        adminCocinero.setForeground(Color.white);
        adminCocinero.setFocusPainted(false);
        adminMeseros = new JButton("MESERO");
        adminMeseros.setBackground(botonesPrin);
        adminMeseros.setForeground(Color.white);
        adminMeseros.setFocusPainted(false);
        content.add(Box.createRigidArea(new Dimension(10, 10)));
        content.add(adminCajero);
        //content.add(adminBarman);
        content.add(adminCocinero);
        content.add(adminMeseros);
        return content;
    }
    
    public JPanel panelAdminUsuariosReg()
    {
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        
        JPanel reg = new JPanel();
        reg.setLayout(new GridBagLayout());
        
        JPanel bot = new JPanel();
        
        JLabel titulo = new JLabel("Agregar o Actualizar en: ");  
        titulo.setForeground(Color.BLACK);
        GridBagConstraints gbctitulo = new GridBagConstraints();
        gbctitulo.gridx = 0;
        gbctitulo.gridy = 0;
        gbctitulo.gridwidth = 2;
        gbctitulo.fill = GridBagConstraints.HORIZONTAL;
        reg.add(titulo, gbctitulo);
        
        bdnombre_us = new JLabel();  
        bdnombre_us.setFont(BDFuente);
        GridBagConstraints gbcbdname = new GridBagConstraints();
        bdnombre_us.setPreferredSize(new Dimension(100,20));
        //bdnombre_us.setEditable(false);
        gbcbdname.gridx = 2;
        gbcbdname.gridy = 0;
        gbcbdname.insets = new Insets(5,0,5,5);
        reg.add(bdnombre_us, gbcbdname);
        
        JLabel nombrelb = new JLabel("Nombre(s): ");
        nombrelb.setForeground(Color.BLACK);
        nombrelb.setPreferredSize(new Dimension(120,20));
        GridBagConstraints gbcnombrelb = new GridBagConstraints();
        gbcnombrelb.gridx = 0;
        gbcnombrelb.gridy = 1;
        reg.add(nombrelb, gbcnombrelb);
        
        nombre_us = new JTextField();
        nombre_us.setPreferredSize(new Dimension(150,25));
        GridBagConstraints gbcnombre_us = new GridBagConstraints();
        gbcnombre_us.gridx = 1;
        gbcnombre_us.gridy = 1;
        gbcnombre_us.gridwidth = 2;
        gbcnombre_us.fill = GridBagConstraints.HORIZONTAL;
        gbcnombre_us.insets = new Insets(5,0,5,5);
        reg.add(nombre_us, gbcnombre_us);        
        
        JLabel apellidoslbl = new JLabel("Apellidos: ");
        apellidoslbl.setForeground(Color.BLACK);
        apellidoslbl.setPreferredSize(new Dimension(120,20));
        GridBagConstraints gbcapellidoslbl = new GridBagConstraints();
        gbcapellidoslbl.gridx = 0;
        gbcapellidoslbl.gridy = 2;
        reg.add(apellidoslbl, gbcapellidoslbl);
        
        apellidos_us = new JTextField();  
        apellidos_us.setPreferredSize(new Dimension(150,25));
        GridBagConstraints gbcapellidos_us = new GridBagConstraints();
        gbcapellidos_us.gridx = 1;
        gbcapellidos_us.gridy = 2;
        gbcapellidos_us.gridwidth = 2;
        gbcapellidos_us.fill = GridBagConstraints.HORIZONTAL;
        gbcapellidos_us.insets = new Insets(5,0,5,5);
        reg.add(apellidos_us, gbcapellidos_us);      
        
        JLabel f_nac = new JLabel("Fecha de Nac.: ");
        f_nac.setForeground(Color.BLACK);
        f_nac.setPreferredSize(new Dimension(120,20));
        GridBagConstraints gbcf_nac = new GridBagConstraints();
        gbcf_nac.gridx = 0;
        gbcf_nac.gridy = 3;
        reg.add(f_nac, gbcf_nac); 
        
        fecha_n_us = new JTextField();
        fecha_n_us.setPreferredSize(new Dimension(150,25));
        GridBagConstraints gbcfecha_n_us = new GridBagConstraints();
        gbcfecha_n_us.gridx = 1;
        gbcfecha_n_us.gridy = 3;
        gbcfecha_n_us.gridwidth = 2;
        gbcfecha_n_us.fill = GridBagConstraints.HORIZONTAL;
        gbcfecha_n_us.insets = new Insets(5,0,5,5);
        reg.add(fecha_n_us, gbcfecha_n_us); 
        
        JLabel f_in = new JLabel("Fecha de Ingreso: ");
        f_in.setForeground(Color.BLACK);
        f_in.setPreferredSize(new Dimension(120,20));
        GridBagConstraints gbcf_f_in = new GridBagConstraints();
        gbcf_f_in.gridx = 0;
        gbcf_f_in.gridy = 4;
        reg.add(f_in, gbcf_f_in); 
        
        fecha_in_us = new JTextField();
        fecha_in_us.setPreferredSize(new Dimension(150,25));
        GridBagConstraints gbcfecha_in_us = new GridBagConstraints();
        gbcfecha_in_us.gridx = 1;
        gbcfecha_in_us.gridy = 4;
        gbcfecha_in_us.gridwidth = 2;
        gbcfecha_in_us.fill = GridBagConstraints.HORIZONTAL;
        gbcfecha_in_us.insets = new Insets(5,0,5,5);
        reg.add(fecha_in_us, gbcfecha_in_us); 
        
        
        JLabel nUsuario = new JLabel("Nombre de Usuario: ");
        nUsuario.setForeground(Color.BLACK);
        nUsuario.setPreferredSize(new Dimension(120,20));
        GridBagConstraints gbcnUsuario = new GridBagConstraints();
        gbcnUsuario.gridx = 0;
        gbcnUsuario.gridy = 5;
        reg.add(nUsuario, gbcnUsuario); 
        
        nameusuario_us = new JTextField();
        nameusuario_us.setPreferredSize(new Dimension(150,25));
        GridBagConstraints gbcnameusuario_us = new GridBagConstraints();
        gbcnameusuario_us.gridx = 1;
        gbcnameusuario_us.gridy = 5;
        gbcnameusuario_us.gridwidth = 2;
        gbcnameusuario_us.fill = GridBagConstraints.HORIZONTAL;
        gbcnameusuario_us.insets = new Insets(5,0,5,5);
        reg.add(nameusuario_us, gbcnameusuario_us); 
        
        JLabel contraseña_uslb = new JLabel("Contraseña: ");
        contraseña_uslb.setForeground(Color.BLACK);
        contraseña_uslb.setPreferredSize(new Dimension(120,20));
        GridBagConstraints gbccontraseña_uslb = new GridBagConstraints();
        gbccontraseña_uslb.gridx = 0;
        gbccontraseña_uslb.gridy = 6;
        reg.add(contraseña_uslb, gbccontraseña_uslb); 
        
        contraseña_us = new JPasswordField();
        contraseña_us.setPreferredSize(new Dimension(150,25));
        GridBagConstraints gbccontraseña_us = new GridBagConstraints();
        gbccontraseña_us.gridx = 1;
        gbccontraseña_us.gridy = 6;
        gbccontraseña_us.gridwidth = 2;
        gbccontraseña_us.fill = GridBagConstraints.HORIZONTAL;
        gbccontraseña_us.insets = new Insets(5,0,5,5);
        reg.add(contraseña_us, gbccontraseña_us); 

        
        bot.setLayout(new GridLayout(1,2,10,10));
        aceptar_us = new JButton("Aceptar");
        aceptar_us.setBackground(Caceptar);
        aceptar_us.setForeground(Color.white);
        aceptar_us.setFocusPainted(false);
        
        cancelar_us = new JButton("Cancelar");
        cancelar_us.setBackground(Ccancelar);
        cancelar_us.setForeground(Color.white);
        cancelar_us.setFocusPainted(false);
        bot.add(aceptar_us);
        bot.add(cancelar_us);
        
        content.add(reg, BorderLayout.CENTER);
        content.add(bot, BorderLayout.SOUTH);
        
        reg.setBackground(Fondo);
        bot.setBackground(Fondo);
        
        return content;
    }
    
    public JPanel panelAdminMeseros()
    {
        JPanel content = new JPanel();
        content.setLayout(new GridBagLayout());            
        return content;
    }
    
    
    
    public JPanel editorBD()
    {
        JPanel content = new JPanel();
        content.setBorder(new LineBorder(Fondo, 3));
        content.setLayout(new BorderLayout());
        content.setBackground(Fondo);
               
        JPanel bd = new JPanel();
        bd.setBackground(Fondo);
        JScrollPane bd2  = new JScrollPane();
        bd2.setBackground(Fondo);
        bd.setBorder(new LineBorder(Fondo, WIDTH));
        bd.setLayout(new GridLayout());
        tablaprin = new JTable();
        tablaprin.setFocusable(false);
        tablaprin.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tablaprin.setRowHeight(16);
        tablaprin.setBackground(Color.WHITE);
        tablaprin.setFont(new Font("Century Gothic", Font.LAYOUT_RIGHT_TO_LEFT, 11));
        tablaprin.setSelectionBackground(new Color(153, 222, 202 ) );
        tablaprin.setSelectionForeground(Color.WHITE);
        tablaprin.getTableHeader().setReorderingAllowed(false);
        tablaprin.setRowMargin(0);
        tablaprin.setShowVerticalLines(false);
        tablaprin.getTableHeader().setFont(new Font("Century Gothic", Font.BOLD, 11));
        tablaprin.getTableHeader().setOpaque(false);
        tablaprin.getTableHeader().setBackground(TopPane);
        tablaprin.getTableHeader().setForeground(Color.WHITE);
        bd2.setViewportView(tablaprin);   
        bd2.getViewport().setBackground(Fondo);
        bd2.setBorder(new MatteBorder(0,0,0,0,Fondo));
        bd2.setBackground(Color.WHITE);
        bd.add(bd2);
        
        content.add(botonesBDProd(), BorderLayout.NORTH);
        content.add(bd, BorderLayout.CENTER);
        
        return content;
    }
     
    public JPanel botonesBDProd()
    {             
        JPanel botones = new JPanel();
        botones.setLayout(new GridLayout(1,8,1,1));
        botones.setBorder(new LineBorder(Fondo2, WIDTH));
        botones.setBackground(Fondo2);
        agregar = new JButton("Agregar Nuevo");
        agregar.setBackground(botonesBD);
        agregar.setForeground(Color.white);
        agregar.setFocusPainted(false);
        actualizar = new JButton("Actualizar Reg");
        actualizar.setBackground(botonesBD);
        actualizar.setForeground(Color.white);
        actualizar.setFocusPainted(false);
        eliminar = new JButton("Eliminar Reg");
        eliminar.setBackground(botonesBD);
        eliminar.setForeground(Color.white);
        eliminar.setFocusPainted(false);
        JLabel buscarlb = new JLabel("Buscar (ID o Nombre):");
        buscarlb.setHorizontalAlignment(LEFT);
        buscarlb.setForeground(Color.white);
        buscar = new JButton("Buscar Reg");        
        buscar.setBackground(botonesBD);
        buscar.setForeground(Color.white);
        buscar.setFocusPainted(false);
        bus = new JTextField();
        
        
        botones.add(agregar);
        botones.add(actualizar);
        botones.add(eliminar);       
        botones.add(Box.createRigidArea(new Dimension(10, 0)));
        botones.add(buscarlb);
        botones.add(bus);
        botones.add(buscar);
        
        
        return botones;
    } 
    
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///SETS Y GETS    
    public JTable getTabla()
    {
        return tablaprin;
    }
    
    public JComboBox getBebidasMenu()
    {
        return adminBebidasMenu;
    }
    
    public void setModeloTabla(TableModel tm)
    {
        tablaprin.setModel(tm);
    }
    //FORMULARIO DE PRODUCTOS
    public void setNombreBD(String name)
    {
        bdnombre.setText(name);
    }
    public void setNombreP(String nomb)
    {
        nombre.setText(nomb);
    }
    
    public void setDescripcionP(String des)
    {
        descripcion.setText(des);
    }
    
    public void setPrecio(String pre)
    {
        precio.setText(pre);
    }
    
    public void setURLP(String ur)
    {
        url.setText(ur);
    }
    
    public JLabel getNombreBD()
    {
        return bdnombre;
    }
    
    public JTextField getNombreT()
    {
        return nombre;
    }
    
    public JTextArea getDescripcionT()
    {
        return descripcion;
    }
    
    public JTextField getPrecioT()
    {
        return precio;
    }
    
    public JTextField getURLT()
    {
        return url;
    }
    
    public JLabel getLabelIMG()
    {
        return mostrarIMG;
    }
    
    public JButton getBtnIMG()
    {
        return selectImg;
    }
    
    
    
    //////ALCH
    public void setNombreBDBA(String name)
    {
        bdnombreBA.setText(name);
    }
    public void setNombrePBA(String nomb)
    {
        nombreba.setText(nomb);
    }
    
    public void setDescripcionPBA(String des)
    {
        descripcion.setText(des);
    }
    
    public void setPrecioBA(String pre)
    {
        precioba.setText(pre);
    }
    
    public void setURLPBA(String ur)
    {
        urlba.setText(ur);
    }
    
    public JLabel getNombreBDBA()
    {
        return bdnombreBA;
    }  
    
    public JTextField getNombreTBA()
    {
        return nombreba;
    }
    
    public JTextArea getDescripcionTBA()
    {
        return descripcionba;
    }
    
    public JTextField getPrecioTBA()
    {
        return precioba;
    }
    
    public JComboBox getTipoAlcohol()
    {
        return tiposAlcohol;
    }
    
    public JTextField getURLTBA()
    {
        return urlba;
    }
    
    public JLabel getLabelIMGBA()
    {
        return mostrarIMGba;
    }
    
    public JButton getBtnIMGBA()
    {
        return selectImgba;
    }
    ///////////////////////////////
    
    
    
    
    
    public void setNomreT(String nt)
    {
        this.nombrebd=nt;
    }
    
    public String getNomreT()
    {
        return nombrebd;
    }
    
    public JButton getBtnAceptar()
    {
        return aceptar;
    }
    public JButton getBtnCancelar()
    {
        return cancelar;
    }
    
    public JButton getBtnAceptarBA()
    {
        return aceptarba;
    }
    public JButton getBtnCancelarBA()
    {
        return cancelarba;
    }
    
    
    
    
    public JTextField getBuscarTxt()
    {
        return bus;
    }
    
    public JPanel getPanelAP()
    {
        return admProd;
    }
    
    public JPanel getPanelAU()
    {
        return admUsu;
    }
    
    public JPanel getPanelAPR()
    {
        return admProdReg;
    }
    
    public JPanel getPanelAUR()
    {
        return admUsReg;
    }
    
    public JPanel getPaneBebidasAlcoholicas()
    {
        return admBebidasAlcoholicas;
    }

    //FORMULARIO DE PRODUCTOS
    public void setNombreBDU(String name)
    {
        bdnombre_us.setText(name);
    }
    
    public JLabel getNombreBD_U()
    {
        return bdnombre_us;
    }
    
    public JTextField getNombreU()
    {
        return nombre_us;
    }
    
    public JTextField getApelidoU()
    {
        return apellidos_us;
    }
    
    public JTextField getFechaNaU()
    {
        return fecha_n_us;
    }
    
    public JTextField getFechaInU()
    {
        return fecha_in_us;
    }
    
    public JTextField getNameUsuario()
    {
        return nameusuario_us;
    }
    
    public JPasswordField getContraseñaU()
    {
        return contraseña_us;
    }
    
    public JButton getBtnAceptarU()
    {
        return aceptar_us;
    }
    
    public JButton getBtnCancelarU()
    {
        return cancelar_us;
    }
    
    ////////////////PANEL BD
    public JButton getBtnAgregar()
    {
        return agregar;
    }
    
    public JButton getBtnActualizar()
    {
        return actualizar;
    }
    
    public JButton getBtnEliminar()
    {
        return eliminar;
    }
    
    public JTextField getTxtBuscar()
    {
        return bus;
    }
    public JButton getBtnBuscar()
    {
        return buscar;
    }
    
    
    
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
    public void conectarControlador(Controlador_Restaurante controlador)
    {
        
        salirTopA.addActionListener(controlador);
        salirTopA.setActionCommand("SALIRTOPA");

        ////PANEL ADMIN
        adminProductos.addActionListener(controlador);
        adminProductos.setActionCommand("PRODUCTOS");
        
        adminUsuarios.addActionListener(controlador);
        adminUsuarios.setActionCommand("USUARIOS");
        
        ////PANEL ADMINPROD
        adminPlatillos.addActionListener(controlador);
        adminPlatillos.setActionCommand("PLATILLOST");
        
        adminBebidasMenu.addActionListener(controlador);
        adminBebidasMenu.setActionCommand("BEBIDASME");      
        
        adminPostres.addActionListener(controlador);
        adminPostres.setActionCommand("POSTREST");
        
        ////PANEL BASE DE DATOS
        agregar.addActionListener(controlador);
        agregar.setActionCommand("AGREGAR");
        
        actualizar.addActionListener(controlador);
        actualizar.setActionCommand("ACTUALIZAR");
        
        eliminar.addActionListener(controlador);
        eliminar.setActionCommand("ELIMINAR");
        
        buscar.addActionListener(controlador);
        buscar.setActionCommand("BUSCAR");
        
        ////PANEL ADMINUSUA
        adminCajero.addActionListener(controlador);
        adminCajero.setActionCommand("CAJEROS");
        
        adminBarman.addActionListener(controlador);
        adminBarman.setActionCommand("BARMANS");
        
        adminCocinero.addActionListener(controlador);
        adminCocinero.setActionCommand("COCINEROS");
        
        adminMeseros.addActionListener(controlador);
        adminMeseros.setActionCommand("MESEROS");
        
        /////PANEL REGISTRO_PROD
        selectImg.addActionListener(controlador);
        selectImg.setActionCommand("SELECTIMG");
        
        selectImgba.addActionListener(controlador);
        selectImgba.setActionCommand("SELECTIMGBA");
        
        aceptar.addActionListener(controlador);
        aceptar.setActionCommand("ACEPTAR");
        
        cancelar.addActionListener(controlador);
        cancelar.setActionCommand("CANCELAR");
        
        /////PANEL REGISTRO_USUA
        aceptar_us.addActionListener(controlador);
        aceptar_us.setActionCommand("ACEPTARU");
        
        cancelar_us.addActionListener(controlador);
        cancelar_us.setActionCommand("CANCELARU");
        
        
        aceptarba.addActionListener(controlador);
        aceptarba.setActionCommand("ACEPTARBA");
        
        cancelarba.addActionListener(controlador);
        cancelarba.setActionCommand("CANCELARBA");
    }
       
    public static void main(String args[])
    {
        new Ventana_Administrador();
    }
 
}
