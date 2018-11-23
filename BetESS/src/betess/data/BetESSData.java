/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package betess.data;

import betess.business.BetESS;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author tiagoalves
 */
public class BetESSData implements Serializable{

    public BetESSData() {}
    
    /**
     *
     * @param fileName
     * @param system
     */
    public void writeData(String fileName, BetESS system){
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(system);
            oos.flush();
            oos.close();
            System.out.println("Guardado!");
	} catch (Exception e) {
            System.out.println(e.getMessage());
	}
    }
    
    /**
     *
     * @param fileName
     * @param b
     * @return
     */
    public BetESS readData(String fileName, BetESS b){
        BetESS betEss = b;
        try {
            File f = new File(fileName);
            if (f.exists()){
                FileInputStream fis = new FileInputStream(fileName);
                ObjectInputStream ois = new ObjectInputStream(fis);
                betEss = (BetESS) ois.readObject();
                ois.close();
                System.out.println("Successfully loaded state.");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return betEss;
    }
    
}
