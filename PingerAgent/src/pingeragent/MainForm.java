/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pingeragent;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 *
 * @author denis
 */
public class MainForm extends JFrame {
    
    JMenuBar jmemubar;
    JMenu jmenuSet;
    JMenuItem jmiServer;
    JMenuItem jmiPort;
    JTextArea jtextarea;
    
    public MainForm () {
        super("Agent");
        
        setSize(400, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        
        setTrayIcon();
        
        jmemubar = new JMenuBar();
        add(jmemubar, BorderLayout.NORTH);
        jmenuSet = new JMenu("Settings");
        jmemubar.add(jmenuSet);
        jmiServer = new JMenuItem("Set server");
        jmenuSet.add(jmiServer);
        jmiServer.addActionListener(e -> {
            settingsJdialog("server");
                });
        jmiPort = new JMenuItem("Set port");
        jmenuSet.add(jmiPort);
        jmiPort.addActionListener(e -> {
            settingsJdialog("port");
                });
        
        jtextarea = new JTextArea();
        add(jtextarea, BorderLayout.CENTER);
        
        JPanel jpanel = new JPanel();
        add(jpanel, BorderLayout.SOUTH);
        JButton serverUp = new JButton("Server link");
        jpanel.add(serverUp);
        serverUp.addActionListener(e -> {
            //socketagent.runSocket();
            });
    }
    
    private void setTrayIcon (){
        if (!SystemTray.isSupported()){
            return;
        }
        
        PopupMenu trayMenu = new PopupMenu();
        MenuItem itemExit = new MenuItem("Exit");
        itemExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        trayMenu.add(itemExit);
        TrayIcon iconTray;
        try {
            iconTray = new TrayIcon(ImageIO.read(new File("agent.jpeg")), "agent pinger", trayMenu);
            iconTray.setImageAutoSize(true);
            SystemTray tray = SystemTray.getSystemTray();
            tray.add(iconTray);
        } catch (IOException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AWTException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    protected void setText (String line){
        jtextarea.append(line + "\n");
    }
    
    private void settingsJdialog (String field){
        
        JDialog dialog = new JDialog(this, "value");
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        
        JPanel scrolpane = new JPanel();
        
        JPanel jp = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField serverURL = new JTextField(15);
        JLabel label = new JLabel("server");
        label.setLabelFor(serverURL);
        jp.add(label);
        jp.add(serverURL);
        scrolpane.add(jp);
        
        jp = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField portserver = new JTextField(15);
        JLabel label2 = new JLabel("port");
        label2.setLabelFor(portserver);
        jp.add(label2);
        jp.add(portserver);
        scrolpane.add(jp);
        
        jp = new JPanel();
        dialog.add(jp, BorderLayout.SOUTH);
        JButton ok = new JButton("OK");
        jp.add(ok);
        ok.addActionListener(e -> {
            Settings set = new Settings();
            set.setField(serverURL.getText(), portserver.getText());
            
            //System.out.println(set.getPort());
            dialog.setVisible(false);
        });
        dialog.add(scrolpane);
    }
}
