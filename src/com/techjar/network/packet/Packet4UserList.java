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
public class Packet4UserList extends Packet {
    public String name;
    public boolean add;
    
    
    public Packet4UserList() {
    }
    
    public Packet4UserList(String name, boolean add) {
        this.name = name;
        this.add = add;
    }
    
    @Override
    public void readData(DataInputStream stream) throws IOException {
        name = readString(stream, 32);
        add = stream.readBoolean();
    }

    @Override
    public void writeData(DataOutputStream stream) throws IOException {
        writeString(stream, name);
        stream.writeBoolean(add);
    }

    @Override
    public void process(NetHandler handler) {
        handler.handleUserList(this);
    }

    @Override
    public int getSize() {
        return 3 + (name.length() * 2);
    }
    
}
