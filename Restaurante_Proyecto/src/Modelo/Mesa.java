/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.TableModel;
/**
 *
 * @author RodrigoLP
 */
public class Mesa
{
    private String numeroMesa="";
    private boolean estado = false;
    private String n_personas="";
    private int n_mesero=0;
    private double total;
    private String[][] pedidoDatos;
    
    public Mesa(String nMesa, boolean Estado, String n_Personas, int nMesero)
    {
       this.numeroMesa = nMesa;
       this.estado = Estado;
       this.n_personas = n_Personas;  
       this.n_mesero = nMesero;
    }
    
    public void setNMesa(String nMesas)
    {
        this.numeroMesa = nMesas;
    }
    public String getNMesa()
    {
        return numeroMesa;
    }
    
    public void setEstado(boolean Estado)
    {
        this.estado = Estado;
    }
    public boolean getEstado()
    {
        return estado;
    }
    
    public void setNPersonas(String nPersonas)
    {
        this.n_personas = nPersonas;
    }
    public String getNPersonas()
    {
        return n_personas;
    }
    
    public void setTotal(double total)
    {
        this.total = total;
    }
    public double getTotal()
    {
        return total;
    }   
    
    public void setNMeseros(int nMeseros)
    {
        this.n_mesero = nMeseros;
    }
    public int getNMeseros()
    {
        return n_mesero;
    }  
    
    public void setDatosPedidoMesa(String[][] datos)
    {
        this.pedidoDatos = datos;
    }
    public String[][] getDatosPedidoMesa()
    {
        return pedidoDatos;
    }
}