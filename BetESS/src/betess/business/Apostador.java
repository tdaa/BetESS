/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package betess.business;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author tiagoalves
 */
public class Apostador extends Utilizador {
    
    private String nome;
    private double essCoins;
    private Map<Integer, Aposta> apostas;

    public Apostador(String email, String nome, String password, double essCoins) {
        super(email, password);
        this.nome = nome;
        this.essCoins = essCoins;
        this.apostas = new HashMap<>();
    }
    
    public Apostador(Apostador a) {
        super(a.getEmail(), a.getPassword());
        this.nome = a.getNome();
        this.essCoins = a.getEssCoins();
        this.apostas = a.getApostas();
    }

    public Apostador() {
        super();
        this.apostas = new HashMap<>();
    }

    /**
     * Getters e Setters
     * @return 
     */
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getEssCoins() {
        return essCoins;
    }

    public void setEssCoins(double essCoins) {
        this.essCoins = essCoins;
    }

    public Map<Integer, Aposta> getApostas() {
        Map<Integer,Aposta> aps = new HashMap<>();
        
        this.apostas.entrySet().forEach(m -> {
            aps.put(m.getKey(), m.getValue());
        });
        
        return aps;
    }

    public void setApostas(Map<Integer, Aposta> aps) {
        aps.entrySet().forEach(m -> {
            this.apostas.put(m.getKey(), m.getValue());
        });
    }

    /**
     * Método toString().
     * Retorna uma representação textual do objeto.
     * @return 
     */
    @Override
    public String toString() {
        return "Apostador{ " + "nome=" + this.nome + 
               ", essCoins=" + this.essCoins + ", apostas=" + this.apostas + " }";
    }
    
    //quando o apostador clica em "submeter aposta" é fechada uma aposta, e quando
    //quiser adicionar um outro evento a uma aposta, esta aposta terá que ser nova.
    //Para verificar se é aposta nova ou não, terá que ser guardado um boolean na frame a dizer
    // que é suposto ser criada uma aposta nova, e que é colocado a true quando clica em
    // "submeter aposta". 
    
    //adicionar evento a aposta
    public void addEventoToAposta(Evento e, boolean novaAposta, int idAposta) {
        if (novaAposta) {
            Aposta a = new Aposta(idAposta);
            a.addEventoToAposta(e);
        } else{
            this.apostas.get(idAposta).addEventoToAposta(e);
        }
    }
    
    // para saber qual o novo id de aposta
    public int getIdNovaAposta() {
        return this.apostas.size() + 1;
    }
}
