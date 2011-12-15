/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.techjar.network;

/**
 *
 * @author Techjar
 */
public class NetworkReaderThread extends Thread {
    private NetworkManager netManager;
    
    
    public NetworkReaderThread(String name, NetworkManager netManager) {
        super(name);
        this.netManager = netManager;
    }
    
    @Override
    public void run() {
        NetworkManager.numReadThreads++;
        while (netManager.isRunning()) {
            while (netManager.readPacket()) ;
            try { sleep(2L); }
            catch(InterruptedException ex) { }
        }
        NetworkManager.numReadThreads--;
    }
}
