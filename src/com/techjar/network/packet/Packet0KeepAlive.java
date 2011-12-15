/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.techjar.network.packet;

import com.techjar.network.handler.NetHandler;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 *
 * @author Techjar
 */
public class Packet0KeepAlive extends Packet {
    public Packet0KeepAlive() {
    }
    
    @Override
    public void readData(DataInputStream stream) throws IOException {
    }

    @Override
    public void writeData(DataOutputStream stream) throws IOException {
    }

    @Override
    public void process(NetHandler handler) {
        handler.handleKeepAlive(this);
    }

    @Override
    public int getSize() {
        return 0;
    }
}
