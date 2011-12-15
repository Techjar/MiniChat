/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.techjar.minichat;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.*;

/**
 *
 * @author Techjar
 */
public class LogHandler extends Handler {
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss a");
    public PrintWriter pw;
    
    
    public LogHandler(String file) {
        super();
        try {
            //FileOutputStream fos = new FileOutputStream(file, true);
            this.pw = new PrintWriter(new FileOutputStream(file, true), true);
            setFormatter(new LogFormatter());
        }
        catch (Throwable e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
    
    @Override
    public void publish(LogRecord record) {
        String line = getFormatter().format(record);
        pw.println(line);
        Main.sysOut.println(line);
    }
    
    @Override
    public void flush() {
        pw.flush();
    }
    
    @Override
    public void close() {
        pw.close();
    }
    
    public class LogFormatter extends Formatter {
        public LogFormatter() {
            super();
        }
        
        @Override
        public String format(LogRecord record) {
            StringBuilder sb = new StringBuilder();
            sb.append('[').append(dateFormat.format(Calendar.getInstance().getTime())).append(']');
            sb.append(' ').append(String.format(record.getMessage(), record.getParameters()));
            return sb.toString();
        }
    }
}
