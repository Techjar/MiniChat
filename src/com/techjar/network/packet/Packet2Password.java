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
public class Packet2Password extends Packet {
    public String password;
    
    
    public Packet2Password() {
    }
    
    public Packet2Password(String password) {
        this.password = password;
    }
    
    @Override
    public void readData(DataInputStream stream) throws IOException {
        password = readString(stream, 128);
    }

    @Override
    public void writeData(DataOutputStream stream) throws IOException {
        writeString(stream, password);
    }

    @Override
    public void process(NetHandler handler) {
        handler.handlePassword(this);
    }

    @Override
    public int getSize() {
        return 2 + (password.length() * 2);
    }
    
}
