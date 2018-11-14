/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package betess.business;

import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author tiagoalves
 */
public class BetESS {

    private Map<String,Apostador> apostadores;
    private Map<Integer,Evento> eventos;
    private Map<String,LinkedList<Aposta>> apostas;
    private int user;

    public BetESS(Map<String, Apostador> apostadores, Map<Integer, Evento> eventos, Map<String, LinkedList<Aposta>> apostas, int user) {
        this.apostadores = apostadores;
        this.eventos = eventos;
        this.apostas = apostas;
        this.user = user;
    }

    public Map<String, Apostador> getApostadores() {
        Map<String,Apostador> aps = new TreeMap<>();
        this.apostadores.entrySet().forEach((m) -> {
            aps.put(m.getKey(), m.getValue());
        });
        return aps;
    }

    public void setApostadores(Map<String, Apostador> aps) {
        aps.entrySet().forEach((m) -> {
            this.apostadores.put(m.getKey(), m.getValue());
        });
    }

    public Map<Integer, Evento> getEventos() {
        Map<Integer,Evento> evs = new TreeMap<>();
        this.eventos.entrySet().forEach((m) -> {
            evs.put(m.getKey(), m.getValue());
        });
        return evs;
    }

    public void setEventos(Map<Integer, Evento> eventos) {
        this.eventos = eventos;
    }

    public Map<String, LinkedList<Aposta>> getApostas() {
        LinkedList<Aposta> lista = new LinkedList<>();
        Map<String,LinkedList<Aposta>> aps = new TreeMap<>();
        for(Map.Entry<String,LinkedList<Aposta>> m: this.apostas.entrySet()){
            for(Aposta a: m.getValue())
                lista.add(a);
            aps.put(m.getKey(), lista);
            lista.clear();
        }
        return aps;
    }

    public void setApostas(Map<String, LinkedList<Aposta>> apostas) {
        LinkedList<Aposta> lista = new LinkedList<>();
        for(Map.Entry<String,LinkedList<Aposta>> m: apostas.entrySet()){
            for(Aposta a: m.getValue())
                lista.add(a);
            this.apostas.put(m.getKey(), lista);
            lista.clear();
        }
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
