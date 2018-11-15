/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package betess.business;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author tiagoalves
 */
public class Aposta {
    
    private int idAposta;
    private Map<Integer,Evento> eventos;
    private double valor;

    public Aposta(int idAposta, Map<Integer, Evento> eventos, double valor) {
        this.idAposta = idAposta;
        this.setEventos(eventos);
        this.valor = valor;
    }

    public Aposta(int id) {
        this.idAposta = id;
        this.eventos = new HashMap<>();
    }
    
    public Aposta(Aposta a){
        this.idAposta = a.idAposta;
        this.valor = a.valor;
        this.eventos = a.getEventos();
    }

    public int getIdAposta() {
        return idAposta;
    }

    public void setIdAposta(int idAposta) {
        this.idAposta = idAposta;
    }

    public Map<Integer, Evento> getEventos() {
        Map<Integer,Evento> evs = new HashMap<>();
        for(Map.Entry<Integer,Evento> m: this.eventos.entrySet())
            eventos.put(m.getKey(), m.getValue());
        return evs;
    }

    public void setEventos(Map<Integer, Evento> evs) {
        for(Map.Entry<Integer,Evento> m: eventos.entrySet())
            this.eventos.put(m.getKey(), m.getValue());
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Aposta{" + "idAposta=" + idAposta + ", eventos=" + eventos + ", valor=" + valor + '}';
    }
    
    //adicionar evento a aposta
    public void addEventoToAposta(Evento e){
        if(!this.eventos.containsKey(e.getIdEvento()))
            this.eventos.put(e.getIdEvento(), e);
    }
    
    
    
    
}
