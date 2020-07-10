/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingeragent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import static pingeragent.PingerAgent.mainform;

/**
 *
 * @author denis
 */
public class SocketAgent {
    
    
    ObjectOutputStream oos;
    ObjectInputStream ois;
    Settings set = new Settings();
    
    
    public void runSocket (){
        String server = set.getServer();
        Integer port = Integer.parseInt(set.getPort());
        
        try (Socket socket = new Socket(server, port);
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream()))
        {
            mainform.setText("Socket up");
            this.oos = oos;
            this.ois = ois;
            //System.out.println("Socket сервера создан");
            while (true){
                String ip = (String)ois.readObject(); // wait enter ip
                //System.out.println("Получено от сервера " + ip);
                Process p = Runtime.getRuntime().exec("ping " + ip + " -n 10");
                BufferedReader inputStream = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String values = "";
                /*for (int i = 0; i<11 ; i++){
                    values = inputStream.readLine();
                    oos.writeObject(values);
                    oos.flush();
                }*/
                
                while ((values = inputStream.readLine()) != null) {// reading output stream of the command
                    oos.writeObject(values);
                    
                }
                oos.writeObject("END");
                oos.flush();
                inputStream.close();
            }
        } catch (IOException ex) {
            mainform.setText("Not link server");
            //System.out.println("Not link server");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ex1) {}
            this.runSocket();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SocketAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}