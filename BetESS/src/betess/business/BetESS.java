/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package betess.business;

import betess.data.BetESSData;
import com.google.gson.*;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


/**
 *
 * @author tiagoalves
 */
public class BetESS implements Serializable{

    private String user;
    private Map<String, Apostador> apostadores;
    private Map<Integer, Evento> eventos;
    private Map<String, LinkedList<Aposta>> apostas;
    private BetESSData bed;

    public BetESS() {
        this.user = "";
        this.apostadores = new HashMap<>();
        this.eventos = new HashMap<>();
        this.apostas = new HashMap<>();
        this.bed = new BetESSData();
    }

    /**
     * Getters e Setters.
     * @return 
     */
    
    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    
    public Map<String, Apostador> getApostadores() {
        Map<String, Apostador> aps = new HashMap<>();
        
        this.apostadores.entrySet().forEach(m -> {
            aps.put(m.getKey(), m.getValue());
        });
        
        return aps;
    }

    public void setApostadores(Map<String, Apostador> aps) {
        aps.entrySet().forEach(m -> {
            this.apostadores.put(m.getKey(), m.getValue());
        });
    }

    public Map<Integer, Evento> getEventos() {
        Map<Integer, Evento> evs = new HashMap<>();
        
        this.eventos.entrySet().forEach(m -> {
            evs.put(m.getKey(), m.getValue());
        });
        
        return evs;
    }

    public void setEventos(Map<Integer, Evento> eventos) {
        this.eventos = eventos;
    }

    public Map<String, LinkedList<Aposta>> getApostas() {
        Map<String, LinkedList<Aposta>> aps = new HashMap<>();
          
        this.apostas.keySet().forEach(id -> {
            LinkedList<Aposta> lista = new LinkedList<>();
            
            this.apostas.get(id).forEach(a -> {
                lista.addFirst(a);
            });
            
            aps.put(id, lista);
        });
        
        return aps;
    }

    public void setApostas(Map<String, LinkedList<Aposta>> apostas) {      
        apostas.keySet().forEach(id -> {
            LinkedList<Aposta> lista = new LinkedList<>();
            
            apostas.get(id).forEach(a -> {
                lista.addFirst(a);
            });
            
            this.apostas.put(id, lista);
        });
    }
    
    /* Consultar evento por id */
    public Evento getEvento(int id) {
        Evento e = null;
        
        if (this.eventos.containsKey(id))
            e = this.eventos.get(id);
        
        return e;
    }
    
    public Apostador getApostador(String email){
        if(this.apostadores.containsKey(email))
            return this.apostadores.get(email);
        else return null;
    }
    
    /**
     * 
     * 
     * @param email
     * @param nome
     * @param pass
     * @param coins
     * @return 
     * @throws betess.business.RegistoInvalidoException 
     */
    public void registo(String email, String nome, String pass, double coins) 
           throws RegistoInvalidoException
    {   
        if (!this.apostadores.containsKey(email)) {
            Apostador a = new Apostador(email, nome, pass, coins);
            this.apostadores.put(email, a);
        } else throw new RegistoInvalidoException("Utilizador já registado!");
    }
    
    /**
     * 
     * @param email
     * @param pass
     * @return
     * @throws EmailErradoException
     * @throws PassErradaException 
     */
    public int login(String email, String pass) 
           throws EmailErradoException, PassErradaException 
    {
        int estatuto;
        
        if (email.equals("betadmin@betess.pt") && pass.equals("betadmin")) {
            Admin a = new Admin();
            this.setUser(email);
            estatuto = 0;
        } else {
            if (this.apostadores.containsKey(email)) {
                Apostador a = this.apostadores.get(email);
                
                if (pass.equals(a.getPassword())) {
                    this.setUser(email);
                    estatuto = 1;
                } else 
                    throw new PassErradaException("Password incorreta!");
            } else
                throw new EmailErradoException("Email errado!");
        }
        
        return estatuto;
    }
    

    /**
     * Adiciona uma aposta a uma lista de apostas de um determinado apostador.
     * 
     * @param userEmail
     * @param aposta
     */
    public void novaAposta(String userEmail, Aposta aposta){
        this.apostadores.get(userEmail).addAposta(aposta);
    }
    
    
    /* ********************************************* *
     * Conjunto de métodos chamados pelo MenuAdmin.  *
     * ********************************************* */

    /**
     * Cria um novo evento, e adiciona-o a respetiva estrutura de dados.
     * 
     * @param equipaUm
     * @param equipaDois
     * @param oddUm
     * @param oddX
     * @param oddDois
     */  
    public void novoEvento(String equipaUm, String equipaDois, double oddUm, 
                           double oddX, double oddDois)
    {
        Evento evento = new Evento(this.eventos.size()+1, equipaUm, equipaDois, oddUm, oddDois, oddX, "aberto", "-");
        this.eventos.put(evento.getIdEvento(), evento);
    }
    
    /**
     * Altera o estado de um evento de aberto para fechado.
     * 
     * @param idEvento
     */
    public void alteraEstadoEvento(int idEvento) {
        if (this.eventos.containsKey(idEvento))
            this.eventos.get(idEvento).setEstado("fechado");
    }
    
    /**
     * Atualiza o resultado de um evento.
     * 
     * @param idEvento
     * @param resultado
     */
    public void novoResultado(int idEvento, String resultado) {
        if (this.eventos.containsKey(idEvento))
            this.eventos.get(idEvento).setResultado(resultado);
    }
    
    /**
     * Remove um determinado evento da lnista de eventos.
     * 
     * @param idEvento
     */
    public void removeEvento(int idEvento){
        if(this.eventos.containsKey(idEvento))
            this.eventos.remove(idEvento);
    }
    
    /**
     * Importa eventos vindos de um ficheiro JSON.
     */
    public void carregaEventos() {
        JsonParser parser = new JsonParser();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("betess/business/Eventos.json");
        Reader reader = new InputStreamReader(inputStream);
        JsonElement rootElement = parser.parse(reader);
        JsonObject rootObject = rootElement.getAsJsonObject();
        JsonArray evs = rootObject.getAsJsonArray("Eventos");
        
        for (int i = 0; i < evs.size(); i++) {
            JsonObject item = evs.get(i).getAsJsonObject();
            
            int idEvento = item.get("id").getAsInt();
            String equipaUm = item.get("equipaUm").getAsString();
            String equipaDois = item.get("equipaDois").getAsString();
            double oddUm = item.get("oddUm").getAsDouble();
            double oddX = item.get("oddX").getAsDouble();
            double oddDois = item.get("oddDois").getAsDouble();
            
            Evento e = new Evento(idEvento, equipaUm, equipaDois, oddUm, oddDois, oddX, "aberto", "-");
            this.eventos.put(idEvento, e);
        }
    }
    
    /**
     *
     * @return Objeto com estado guardado anteriormente.
     */
    public BetESS startApp(){
        return this.bed.readData("BetData", this);
    }

    /**
     * Guarda o estado de todo o sistema num ficheiro "BetData".
     */
    public void endApp(){
        this.bed.writeData("BetData", this);
    }
    
}
