/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ClientGUI.java
 *
 * Created on Dec 11, 2011, 12:53:25 AM
 */
package com.techjar.minichat.gui;

import com.techjar.minichat.Client;
import com.techjar.minichat.gui.util.*;
import com.techjar.minichat.gui.util.SortedListModel.SortOrder;
import com.techjar.network.packet.Packet;
import com.techjar.network.packet.Packet255Disconnect;
import com.techjar.network.packet.Packet5NameChange;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.text.html.*;

/**
 *
 * @author Jarsky
 */
public class GUIClient extends javax.swing.JFrame {
    public static final String lineSep = System.getProperty("line.separator");
    public final Client client;
    private DefaultListModel userList;
    private SortedListModel userListSorted;
    private List<String> chatSendLog;
    private int chatSendLogIndex = -1;
    private String lastServer = "";
    
    
    /** Creates new form ClientGUI */
    public GUIClient(Client client) {
        super();
        this.client = client;
        
        preInitComponents();
        initComponents();
        postInitComponents();
        
        // Set the icons
        List<Image> icons = new ArrayList<Image>();
        icons.add(new ImageIcon("resources/img/icon/16.png").getImage());
        icons.add(new ImageIcon("resources/img/icon/32.png").getImage());
        icons.add(new ImageIcon("resources/img/icon/128.png").getImage());
        this.setIconImages(icons);

        // Position and display the form
        java.awt.Dimension dim = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((dim.width - getSize().width) / 2, (dim.height - getSize().height) / 2);
        setVisible(true);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chatInputBox = new javax.swing.JTextField();
        chatSendButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        chatUserList = new javax.swing.JList();
        jScrollPane1 = new javax.swing.JScrollPane();
        chatOutputBox = new javax.swing.JTextPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuConnect = new javax.swing.JMenuItem();
        menuDisconnect = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MiniChat");

        chatInputBox.setEnabled(false);
        chatInputBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chatInputBoxActionPerformed(evt);
            }
        });
        chatInputBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                chatInputBoxKeyPressed(evt);
            }
        });

        chatSendButton.setText("Send");
        chatSendButton.setEnabled(false);
        chatSendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chatSendButtonActionPerformed(evt);
            }
        });

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        chatUserList.setModel(userListSorted);
        chatUserList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        chatUserList.setAutoscrolls(false);
        jScrollPane2.setViewportView(chatUserList);

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setAutoscrolls(true);

        chatOutputBox.setContentType("text/html");
        chatOutputBox.setEditable(false);
        chatOutputBox.setText("<html>\r\n  <head>\r\n\r\n  </head>\r\n  <body>\r\n\n  </body>\r\n</html>\r\n");
        chatOutputBox.setMaximumSize(new java.awt.Dimension(482, 2147483647));
        jScrollPane1.setViewportView(chatOutputBox);

        jMenu1.setText("File");

        menuConnect.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        menuConnect.setText("Connect");
        menuConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuConnectActionPerformed(evt);
            }
        });
        jMenu1.add(menuConnect);

        menuDisconnect.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.ALT_MASK));
        menuDisconnect.setText("Disconnect");
        menuDisconnect.setEnabled(false);
        menuDisconnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuDisconnectActionPerformed(evt);
            }
        });
        jMenu1.add(menuDisconnect);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem1.setText("Username");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(chatInputBox, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chatSendButton))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chatInputBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chatSendButton))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void chatInputBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_chatInputBoxKeyPressed
    switch (evt.getKeyCode()) {
        case KeyEvent.VK_UP:
            if (chatSendLogIndex < chatSendLog.size() - 1) {
                chatInputBox.setText(chatSendLog.get(++chatSendLogIndex));
            }
            else java.awt.Toolkit.getDefaultToolkit().beep();
            break;
        case KeyEvent.VK_DOWN:
            if (chatSendLogIndex > -1) {
                if (--chatSendLogIndex > -1) chatInputBox.setText(chatSendLog.get(chatSendLogIndex));
                else chatInputBox.setText("");
            }
            else java.awt.Toolkit.getDefaultToolkit().beep();
            break;
    }
}//GEN-LAST:event_chatInputBoxKeyPressed

private void chatSendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chatSendButtonActionPerformed
    sendChatMessage();
    chatInputBox.requestFocus();
}//GEN-LAST:event_chatSendButtonActionPerformed

private void menuDisconnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuDisconnectActionPerformed
    if (JOptionPane.showConfirmDialog(this, "Are you sure you want to disconnect?", "Disconnect", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
        disconnect("User disconnected!", true);
    }
}//GEN-LAST:event_menuDisconnectActionPerformed

private void chatInputBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chatInputBoxActionPerformed
    sendChatMessage();
}//GEN-LAST:event_chatInputBoxActionPerformed

private void menuConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuConnectActionPerformed
    String ip = null; int port = 4120;
    String input = (String)JOptionPane.showInputDialog(this, "Input the IP of the server:", "Connect", JOptionPane.PLAIN_MESSAGE, null, null, lastServer);
    if (input == null) return;
    lastServer = input;
    
    try {
        if (input.indexOf(":") != -1) {
            if (input.indexOf("[") != -1 && input.indexOf("]") != -1) {
                ip = input.substring(1, input.indexOf("]"));
                port = Integer.parseInt(input.substring(input.indexOf("]") + 2));
            }
            else if (input.indexOf(":") == input.lastIndexOf(":")) {
                ip = input.substring(0, input.indexOf(":"));
                port = Integer.parseInt(input.substring(input.indexOf(":") + 1));
            }
            else ip = input;
        }
        else ip = input;

        connect(ip, port);
    }
    catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, e, "Error", JOptionPane.ERROR_MESSAGE);
    }
}//GEN-LAST:event_menuConnectActionPerformed

private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
    String input = (String)JOptionPane.showInputDialog(this, "Input your new username:", "Change Username", JOptionPane.PLAIN_MESSAGE, null, null, client.username);
    if (input == null) return;
    input = input.trim();
    if (input.toLowerCase().equals(client.username.toLowerCase())) return;
    
    if (input.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Your username cannot be blank.", "Invalid Username", JOptionPane.WARNING_MESSAGE);
        return;
    }
    if (input.length() > 32) {
        JOptionPane.showMessageDialog(this, "Your username cannot be longer than 32 characters.", "Invalid Username", JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    if (client.netManager != null && client.netManager.isRunning()) {
        client.netManager.queuePacket(new Packet5NameChange(input, false));
    }
    else {
        client.username = input;
        JOptionPane.showMessageDialog(this, "Your username has been changed.", "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}//GEN-LAST:event_jMenuItem1ActionPerformed

    private void preInitComponents() {
        userList = new DefaultListModel();
        userListSorted = new SortedListModel(userList, SortOrder.ASCENDING);
        chatSendLog = new ArrayList<String>();
    }

    private void postInitComponents() {
        try {
            ((HTMLDocument)chatOutputBox.getStyledDocument()).setBase(new File("resources/").toURI().toURL());
            chatInputBox.setDocument(new LimitDocument(1000, true));
            chatInputBox.requestFocus();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void sendChatMessage() {
        if (!chatInputBox.getText().trim().isEmpty()) {
            String message = chatInputBox.getText().trim().replaceAll("\\s{2,}", " ");
            client.sendChatMessage(message);
            addToChatSendLog(message);
            chatInputBox.setText("");
            chatSendLogIndex = -1;
        }
    }

    public void addChatMessage(String message) {
        final String message2 = parseMessage(message);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    ((HTMLEditorKit)chatOutputBox.getEditorKit()).insertHTML((HTMLDocument)chatOutputBox.getStyledDocument(), chatOutputBox.getStyledDocument().getLength(), "<div>" + message2 + "</div>", 0, 0, null);
                    chatOutputBox.setCaretPosition(chatOutputBox.getDocument().getLength());
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private String parseMessage(String message) {
        String parsed = message;
        Matcher match = Pattern.compile("\\((\\w*)\\)").matcher(message);
        while (match.find()) {
            String emote = message.substring(match.start() + 1, match.end() - 1).toLowerCase();
            if (new File("resources/img/emoticons/" + emote + ".png").exists())
                parsed = parsed.replaceFirst("\\(" + emote + "\\)", "<img src='img/emoticons/" + emote + ".png'>");
            if (new File("resources/img/emoticons/" + emote + ".gif").exists())
                parsed = parsed.replaceFirst("\\(" + emote + "\\)", "<img src='img/emoticons/" + emote + ".gif'>");
        }
        return parsed;
    }

    private void addToChatSendLog(String message) {
        if (chatSendLog.size() < 1 || !chatSendLog.get(0).equals(message)) {
            if (chatSendLog.size() >= 100)
                chatSendLog.remove(chatSendLog.size() - 1);
            chatSendLog.add(0, message);
        }
    }
    
    public void connect(String ip, int port) throws IOException, UnknownHostException {
        client.connect(ip, port);
        menuConnect.setEnabled(false);
        menuDisconnect.setEnabled(true);
    }
    
    public void disconnect(String reason, Object... info) {
        try {
            Packet.writePacket(client.netManager.getOutputStream(), new Packet255Disconnect(reason));
        }
        catch (IOException ex) { }
        client.netManager.shutdown(reason, info);
        toggleState(false);
    }
    
    public void toggleState(boolean state) {
        chatInputBox.setEnabled(state);
        chatSendButton.setEnabled(state);
        menuConnect.setEnabled(!state);
        menuDisconnect.setEnabled(state);
        if (!state) userList.clear();
    }
    
    public void addUser(final String name) {
        if (!userList.contains(name)) {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    userList.addElement(name);
                }
            });
        }
    }
    
    public void removeUser(final String name) {
        if (userList.contains(name)) {
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    userList.removeElement(name);
                }
            });
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField chatInputBox;
    private javax.swing.JTextPane chatOutputBox;
    private javax.swing.JButton chatSendButton;
    private javax.swing.JList chatUserList;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenuItem menuConnect;
    private javax.swing.JMenuItem menuDisconnect;
    // End of variables declaration//GEN-END:variables
}
