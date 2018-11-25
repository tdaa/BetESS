package betess.business;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Manuel Sousa
 * @author Tiago Alves
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
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getEssCoins() {
        return this.essCoins;
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
    
    /**
     * 
     * @return Identificador para uma nova aposta,
     */
    public int getIdNovaAposta() {
        return this.apostas.size() + 1;
    }
    
    /* Adiciona uma nova aposta à sua lista */

    /**
     *
     * @param aposta
     */
    public void addAposta(Aposta aposta) {
        this.apostas.put(this.apostas.size()+1, aposta);
    }
}
