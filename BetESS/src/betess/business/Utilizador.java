/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package betess.business;

import java.io.Serializable;

/**
 *
 * @author tiagoalves
 */
public class Utilizador implements Serializable{
    
    private String email;
    private String password;
    
    public Utilizador() { }

    public Utilizador(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * Getters e Setters.
     * @return 
     */
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
