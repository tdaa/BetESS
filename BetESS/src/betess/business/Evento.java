/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package betess.business;

/**
 *
 * @author tiagoalves
 */
public class Evento {
    
    private int idEvento;
    private String equipaUm;
    private String equipaDois;
    private double oddUm;
    private double oddDois;
    private double oddX;
    private String estado;
    private String resultado;

    public Evento(int idEvento, String equipaUm, String equipaDois, double oddUm, double oddDois, double oddX, String estado, String resultado) {
        this.idEvento = idEvento;
        this.equipaUm = equipaUm;
        this.equipaDois = equipaDois;
        this.oddUm = oddUm;
        this.oddDois = oddDois;
        this.oddX = oddX;
        this.estado = estado;
        this.resultado = resultado;
    }
    
    public Evento(Evento e){
        this.idEvento = e.idEvento;
        this.equipaUm = e.equipaUm;
        this.equipaDois = e.equipaDois;
        this.oddUm = e.oddUm;
        this.oddDois = e.oddDois;
        this.oddX = e.oddX;
        this.estado = e.estado;
        this.resultado = e.resultado;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public String getEquipaUm() {
        return equipaUm;
    }

    public void setEquipaUm(String equipaUm) {
        this.equipaUm = equipaUm;
    }

    public String getEquipaDois() {
        return equipaDois;
    }

    public void setEquipaDois(String equipaDois) {
        this.equipaDois = equipaDois;
    }

    public double getOddUm() {
        return oddUm;
    }

    public void setOddUm(double oddUm) {
        this.oddUm = oddUm;
    }

    public double getOddDois() {
        return oddDois;
    }

    public void setOddDois(double oddDois) {
        this.oddDois = oddDois;
    }

    public double getOddX() {
        return oddX;
    }

    public void setOddX(double oddX) {
        this.oddX = oddX;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    
   
    
}