/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.techjar.minichat;

import java.io.File;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Techjar
 */
public class Main {
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
    public static final Logger logger = Logger.getLogger("MiniChat");
    public static final Logger errorLogger = Logger.getLogger("MiniChat_Error");
    public static final PrintStream sysOut = System.out;
    public static final PrintStream sysErr = System.err;
    
    
    public static void main(String[] args) {
        try {
            String folder = (args.length >= 1 ? "server/" : "client/");
            
            new File("logs/" + folder + "errors/").mkdirs();
            logger.setUseParentHandlers(false);
            errorLogger.setUseParentHandlers(false);
            logger.addHandler(new LogHandler("logs/" + folder + dateFormat.format(Calendar.getInstance().getTime()) + ".txt"));
            errorLogger.addHandler(new LogHandler("logs/" + folder + "errors/" + dateFormat.format(Calendar.getInstance().getTime()) + ".txt"));
            System.setOut(new PrintStream(new LogOutputStream(logger, Level.INFO), true));
            System.setErr(new PrintStream(new LogOutputStream(errorLogger, Level.SEVERE), true));
            
            if (args.length >= 1) {
                Server server = new Server(Integer.parseInt(args[0]));
                server.start();
            }
            else {
                //<editor-fold defaultstate="collapsed" desc="Set the Nimbus look and feel">
                /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
                 * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
                 */
                try {
                    for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                        if ("CDE/Motif".equals(info.getName())) {
                            javax.swing.UIManager.setLookAndFeel(info.getClassName());
                            break;
                        }
                    }
                } catch (ClassNotFoundException ex) {
                    java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                    java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
                //</editor-fold>
                
                Client client = new Client();
                client.loadLoginGUI();
            }
        }
        catch (Throwable ex) {
            ex.printStackTrace();
            System.exit(0);
        }
    }
}
