/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.techjar.minichat;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.*;

/**
 *
 * @author Techjar
 */
public class LogOutputStream extends ByteArrayOutputStream {
    private Logger logger;
    private Level level;
    private String lineSeparator;
    
    
    public LogOutputStream(Logger logger, Level level) {
        super();
        this.logger = logger;
        this.level = level;
        this.lineSeparator = System.getProperty("line.separator"); 
    }
    
    @Override
    public void flush() throws IOException { 
        String record;
        synchronized(this) {
            super.flush();
            record = this.toString();
            super.reset();

            if (record.length() == 0 || record.equals(lineSeparator))
                return;

            logger.logp(level, "", "", record);
        }
    }
}
