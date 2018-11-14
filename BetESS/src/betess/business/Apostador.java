/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package betess.business;

import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author tiagoalves
 */
public class Apostador {
    
    private String email;
    private String nome;
    private String password;
    private double essCoins;
    private Map<Integer,Aposta> apostas;

    public Apostador(String email, String nome, String password, double essCoins, Map<Integer, Aposta> aps) {
        this.email = email;
        this.nome = nome;
        this.password = password;
        this.essCoins = essCoins;
        this.setApostas(aps);
    }
    
    public Apostador(Apostador a){
        this.email = a.email;
        this.nome = a.nome;
        this.password = a.password;
        this.essCoins = a.essCoins;
        this.apostas = a.getApostas();
    }

    public Apostador() {
        this.apostas = new TreeMap<>();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getEssCoins() {
        return essCoins;
    }

    public void setEssCoins(double essCoins) {
        this.essCoins = essCoins;
    }

    public Map<Integer, Aposta> getApostas() {
        Map<Integer,Aposta> aps = new TreeMap<>();
        this.apostas.entrySet().forEach((m) -> {
            aps.put(m.getKey(), m.getValue());
        });
        return aps;
    }

    public void setApostas(Map<Integer, Aposta> aps) {
        aps.entrySet().forEach((m) -> {
            this.apostas.put(m.getKey(), m.getValue());
        });
    }

    @Override
    public String toString() {
        return "Apostador{" + "email=" + email + ", nome=" + nome + ", password=" + password + ", essCoins=" + essCoins + ", apostas=" + apostas + '}';
    }
    
    
    
    
}
