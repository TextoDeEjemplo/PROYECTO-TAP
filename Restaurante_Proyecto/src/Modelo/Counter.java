package Modelo;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

public class Counter extends Thread{
    private int dia;
    private int hora;
    private int minuto;
    private Calendar calendar;
    private int semana;
    private String[] strDays = new String[]{
            "Domingo",
            "Lunes",
            "Martes",
            "Miercoles",
            "Jueves",
            "Viernes",
            "Sabado"};

    boolean stopped=false;
    private Vector<JLabel> labels;

    public Counter()
    {
        dia=1;
        hora=0;
        calendar=Calendar.getInstance();
        semana=calendar.get(Calendar.DAY_OF_WEEK);
        labels=new Vector<>();
    }
    public String toString()
    {
        return "Día "+dia+" : "+strDays[semana%7-1]+" Hora: "+(hora<10?"0":"")+hora+":"+(minuto<10?"0":"")+minuto;
    }
    public void aumentar()
    {
        if(minuto<59)
            minuto++;
        else if(hora<23) {
            hora++;
            minuto=0;
        }
        else{
            dia++;
            hora=0;
            minuto=0;
            semana++;
        }
    }
    public void addLabel(JLabel label)
    {
        labels.add(label);
    }
    public void detener()
    {
        stopped=true;
    }

    @Override
    public void run() {
        while (!stopped)
        {
            try{
                Thread.sleep(1000);
                aumentar();
                for(JLabel label:labels)
                    label.setText(this.toString());
            }catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
