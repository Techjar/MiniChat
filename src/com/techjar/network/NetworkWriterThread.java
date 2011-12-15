/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.techjar.network;

/**
 *
 * @author Techjar
 */
public class NetworkWriterThread extends Thread {
    private NetworkManager netManager;
    
    
    public NetworkWriterThread(String name, NetworkManager netManager) {
        super(name);
        this.netManager = netManager;
    }
    
    @Override
    public void run() {
        NetworkManager.numWriteThreads++;
        while (!netManager.isTerminated()) {
            while(netManager.sendPacket()) ;
            try {
                if (netManager.getOutputStream() != null)
                    netManager.getOutputStream().flush();
            }
            catch (Exception ex) {
                netManager.networkError(ex);
            }
            try { sleep(2L); }
            catch(InterruptedException ex) { }
        }
        NetworkManager.numWriteThreads--;
    }
}
