package Vista;

import Modelo.Counter;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.*;

public class Hilos extends JFrame {
    private Counter counter;
    private JButton btnTop; //El de arriba
    private JButton btnMid; //El de medio
    private JButton btnBot; //El de abajo
    private JButton btnExit; //El cancelar
    private JLabel label;
    private JLabel info;

    private JFrame control;
    int pX,pY;

    private Color[] colores=new Color[]{new Color(31, 97, 141),new Color(214, 137, 16),new Color(20, 143, 119)};
    private int count=0;

    public Hilos() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 600);
        setLayout(new GridLayout(6, 1));
        setUndecorated(true);

        btnTop = new JButton("AGREGAR CLIENTE");
        btnMid = new JButton("TERMINAR SIMULACIÓN");
        btnBot = new JButton("OTRA OPCIÓN");

        estilo(btnTop);
        estilo(btnMid);
        estilo(btnBot);

        JPanel separator = new JPanel(new FlowLayout(FlowLayout.LEADING));
        separator.setBackground(new Color(46, 64, 83));

        label=new JLabel("CONTROL DE SIMULACIÓN");
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(Color.WHITE);

        separator.add(label);

        counter=new Counter();

        JPanel count=new JPanel();
        count.setBackground(new Color(46, 64, 83));
        info=new JLabel(counter.toString());
        info.setFont(new Font("Arial", Font.BOLD, 20));
        info.setForeground(Color.WHITE);
        count.add(info);

        add(separator);
        add(count);
        add(btnTop);
        add(btnMid);
        add(btnBot);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(46, 64, 83));


        btnExit = new JButton("CANCELAR");
        btnExit.setFont(new Font("Arial", Font.BOLD, 14));
        btnExit.setForeground(Color.WHITE);
        btnExit.setBackground(new Color(146, 43, 33));
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        panel.add(btnExit);
        add(panel);

        btnTop.setSelected(false);

        control=this;
        //
        /*
        label.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                System.exit(0);
            }
        });

         */
        separator.addMouseListener(new MouseAdapter() {
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

        separator.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent me) {

                control.setLocation(control.getLocation().x + me.getX() - pX,
                        control.getLocation().y + me.getY() - pY);
            }
        });


        //setUndecorated(true);
        setLocationRelativeTo(null);
        //setVisible(true);
        counter.addLabel(info);
        counter.run();
    }
    public void estilo(JButton button)
    {
        button.setFont(new Font("Arial",Font.BOLD,20));
        button.setForeground(Color.WHITE);
        button.setBackground(colores[count]);
        count++;
        button.setBorder(new MatteBorder(0,40,20,40,new Color(46, 64, 83)));
    }
    public void conectarControlador(ActionListener l)
    {
        btnTop.addActionListener(l);
        btnMid.addActionListener(l);
        btnBot.addActionListener(l);
    }

    public JButton getBtnTop() {
        return btnTop;
    }

    public JButton getBtnMid() {
        return btnMid;
    }

    public JButton getBtnBot() {
        return btnBot;
    }

    public static void main(String [] args)
    {
        new Hilos();
    }
}
