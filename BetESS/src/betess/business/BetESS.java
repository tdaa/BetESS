/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package betess.business;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author tiagoalves
 */
public class BetESS {

    private Map<String,Apostador> apostadores;
    private Map<Integer,Evento> eventos;
    private Map<String,LinkedList<Aposta>> apostas;
    private String user;

    public BetESS() {
        this.apostadores = new HashMap<>();
        this.eventos = new HashMap<>();
        this.apostas = new HashMap<>();
    }

    public Map<String, Apostador> getApostadores() {
        Map<String,Apostador> aps = new HashMap<>();
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
        Map<Integer,Evento> evs = new HashMap<>();
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
        Map<String,LinkedList<Aposta>> aps = new HashMap<>();
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    
    //registo
    public boolean registo(String email, String nome, String pass, double coins){
        if(!this.apostadores.containsKey(email)){
            Apostador a = new Apostador(email, nome, pass, coins);
            this.apostadores.put(email, a);
            return true;
        }
        return false;
        //retorno boolean para saberes se inseriu com sucesso ou não.
    }
    
    //login
    public void login(String email, String pass) throws EmailErradoException, PassErradaException{
         if(email.equals("betAdmin@di.pt") && pass.equals("betAdmin")){
            Admin a = new Admin();
            this.setUser(email);
        }
        else{
            if(this.apostadores.containsKey(email)){
                Apostador a = this.apostadores.get(email);
                if(pass.equals(a.getPassword())){
                    this.setUser(email);
                }
                else throw new PassErradaException("Password incorreta!");
            }
            else throw new EmailErradoException("Email errado!");
        }   
    }
    
    //consultar evento por id
    public Evento getEvento(int id){
        if(this.eventos.containsKey(id)) return this.eventos.get(id);
        return null;
    }
   
    
    
    
    
    
    
}
