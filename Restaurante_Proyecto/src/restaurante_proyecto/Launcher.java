package restaurante_proyecto;

import Controlador.Controlador_Restaurante;
import Modelo.Modelo;
import Vista.*;

public class Launcher {
    private Ventana_Administrador administrador;
    private Hilos controlHilos;
    private Ventana_Caja caja;
    private Ventana_Cocina cocina;
    private Ventana_Meseros mesero;
    private Ventana_Principal principal;
    private Modelo modelo;
    Controlador_Restaurante controlar_meseros;

    public Launcher()
    {
        principal=new Ventana_Principal();
        principal.conectarLauncher(this);
    }

    public  static void main (String [] args)
    {
        new Launcher();
    }
    public void lanzarCocina()
    {
        System.out.println("Cocina");
        cocina=new Ventana_Cocina();
    }
    public void lanzarBarra()
    {
        System.out.println("Barra");

    }
    public void lanzarMeseros()
    {
        System.out.println("Meseros");
        mesero=new Ventana_Meseros();
    }
    public void lanzarCaja()
    {
        System.out.println("Caja");
        caja=new Ventana_Caja();
    }
}
