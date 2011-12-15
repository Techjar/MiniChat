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
public class Packet1Login extends Packet {
    public int version;
    public String name;
    public boolean password;
    
    
    public Packet1Login() {
    }
    
    public Packet1Login(int version, String name, boolean password) {
        this.version = version;
        this.name = name;
        this.password = password;
    }
    
    @Override
    public void readData(DataInputStream stream) throws IOException {
        version = stream.readInt();
        name = readString(stream, 32);
        password = stream.readBoolean();
    }

    @Override
    public void writeData(DataOutputStream stream) throws IOException {
        stream.writeInt(version);
        writeString(stream, name);
        stream.writeBoolean(password);
    }

    @Override
    public void process(NetHandler handler) {
        handler.handleLogin(this);
    }

    @Override
    public int getSize() {
        return 7 + (name.length() * 2);
    }
}
