/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.techjar.network.packet;

import com.techjar.network.handler.NetHandler;
import java.util.*;
import java.io.*;

/**
 *
 * @author Techjar
 */
public abstract class Packet {
    private static Map<Integer, Class> idToClass = new HashMap<Integer, Class>();
    private static Map<Class, Integer> classToId = new HashMap<Class, Integer>();
    private static Set<Integer> clientPackets = new HashSet<Integer>();
    private static Set<Integer> serverPackets = new HashSet<Integer>();
    
    public Packet() {
    }
    
    public static void addMapping(Class class1, int id, boolean client, boolean server) {
        if (idToClass.containsKey(id)) throw new IllegalArgumentException(new StringBuilder("Duplicate packet id: ").append(id).toString());
        if (classToId.containsKey(class1)) throw new IllegalArgumentException(new StringBuilder("Duplicate packet class: ").append(class1.getName()).toString());
        idToClass.put(id, class1);
        classToId.put(class1, id);
        if (client) clientPackets.add(id);
        if (server) serverPackets.add(id);
    }
    
    public static Packet createPacket(int id) {
        try {
            Class class1 = idToClass.get(id);
            if (class1 == null) return null;
            return (Packet)class1.newInstance();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static Packet readPacket(DataInputStream stream, boolean server) throws IOException {
        Packet packet = null;
        try {
            int id = stream.readUnsignedByte();
            if ((!server && !clientPackets.contains(id)) || (server && !serverPackets.contains(id)))
                throw new IOException(new StringBuilder("Unknown packet ID: ").append(id).toString());
            packet = createPacket(id);
            if (packet == null) throw new IOException(new StringBuilder("Unknown packet ID: ").append(id).toString());
            packet.readData(stream);
        }
        catch (EOFException ex) {
            System.out.println("Reached end of stream!");
            return null;
        }
        return packet;
    }
    
    public static void writePacket(DataOutputStream stream, Packet packet) throws IOException {
        stream.write(packet.getId());
        packet.writeData(stream);
    }
    
    public static String readString(DataInputStream stream, int maxLength) throws IOException {
        short length = stream.readShort();
        if (length > maxLength) throw new IOException(new StringBuilder("Received string length longer than maximum allowed! (").append(length).append(" > ").append(maxLength).append(")").toString());
        if (length < 0) throw new IOException("Received string length is less than zero! Weird string!");
        StringBuilder sb = new StringBuilder();
        for (short i = 0; i < length; i++) sb.append(stream.readChar());
        return sb.toString();
    }
    
    public static void writeString(DataOutputStream stream, String str) throws IOException {
        if (str.length() > Short.MAX_VALUE) throw new IOException("String too large!");
        stream.writeShort(str.length());
        stream.writeChars(str);
    }
    
    public final int getId() {
        return classToId.get(getClass());
    }
    
    public abstract void readData(DataInputStream stream) throws IOException;
    public abstract void writeData(DataOutputStream stream) throws IOException;
    public abstract void process(NetHandler handler);
    public abstract int getSize();
    
    static {
        // ID <-> Class Mapping
        addMapping(Packet0KeepAlive.class, 0, true, true);
        addMapping(Packet1Login.class, 1, true, true);
        addMapping(Packet2Password.class, 2, false, true);
        addMapping(Packet3Chat.class, 3, true, true);
        addMapping(Packet4UserList.class, 4, true, false);
    }
}
