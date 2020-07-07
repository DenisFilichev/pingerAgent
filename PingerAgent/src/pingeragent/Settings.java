package pingeragent;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author denis
 */
public class Settings {
    Properties properties = new Properties();
    FileInputStream fis;
    FileOutputStream fos;
    
    private void inputstream (){
        try {
            fis = new FileInputStream("set.properties");
            properties.load(fis);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void outputstream (){
        try {
            fos = new FileOutputStream("set.properties");
            //properties.load(fos);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getServer() {
        inputstream();
        return properties.getProperty("server");
    }

    public void setField(String server, String port) {
        try {
            fos = new FileOutputStream("set.properties");
            properties.setProperty("server", server);
            properties.setProperty("port", port);
            properties.store(fos, null);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        }
        //85.21.168.185
    }

    public String getPort() {
        inputstream();
        return properties.getProperty("port");
    }
}
