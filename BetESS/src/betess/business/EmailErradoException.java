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
class EmailErradoException extends Exception {
    
    public EmailErradoException(){
        super();
    }
    
    public EmailErradoException(String m){
        super(m);
    }
}
