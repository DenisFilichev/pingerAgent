/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingeragent;

/**
 *
 * @author denis
 */
public class PingerAgent {

    public static MainForm mainform = new MainForm();
    public static PingerAgent agentmain = new PingerAgent();
    public static SocketAgent socketagent;
    
    public static void main(String[] args) {
        socketagent = new SocketAgent();
        socketagent.runSocket();
    }
    
}
