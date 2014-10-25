/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.klient.gui;

import chat.klient.controller.ClientController;
import chat.server.chat.ChatMessage;
import chat.server.chat.ChattingRoom.ChattingRoomChangedListener;
import chat.server.chat.IChatter;
import java.util.Calendar;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Jakub
 */
public class MainWindow extends javax.swing.JFrame {
    private final ClientController controller;
    private final DefaultListModel chattersModel = new DefaultListModel();
    
    
    /**
     * Creates new form MainWindow
     * @param controller client controller
     */
    public MainWindow(ClientController controller) {
        this.controller = controller;
        this.controller.setView(this);
        
        initComponents();
        
        this.setEvents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        chatMessagesArea = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        chattersList = new javax.swing.JList();
        jLabel2 = new javax.swing.JLabel();
        protocolComboBox = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        portField = new javax.swing.JTextField();
        serverField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        connectButton = new javax.swing.JButton();
        messageArea = new javax.swing.JScrollPane();
        messageField = new javax.swing.JTextArea();
        sendButton = new javax.swing.JButton();
        nameField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat client");

        chatMessagesArea.setEditable(false);
        chatMessagesArea.setColumns(20);
        chatMessagesArea.setRows(5);
        jScrollPane1.setViewportView(chatMessagesArea);

        jLabel1.setText("Zprávy");

        chattersList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(chattersList);

        jLabel2.setText("Chatteři");

        protocolComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "TCP/IP", "UDP" }));

        jLabel3.setText("Protokol");

        jLabel4.setText("Port");

        portField.setText("1340");

        jLabel5.setText("Server IP");

        connectButton.setText("Connect");
        connectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectButtonActionPerformed(evt);
            }
        });

        messageField.setColumns(20);
        messageField.setRows(2);
        messageArea.setViewportView(messageField);

        sendButton.setText("Send");
        sendButton.setEnabled(false);
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        jLabel6.setText("Jméno");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sendButton)
                    .addComponent(messageArea, javax.swing.GroupLayout.PREFERRED_SIZE, 713, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 713, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(serverField, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(portField, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(connectButton))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(protocolComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(protocolComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(portField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(serverField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(connectButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(messageArea, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sendButton)
                .addGap(32, 32, 32))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void setEvents() {
        this.controller.getChattingRoom().addChangeListener(new ChattingRoomChangedListener(){
            @Override
            public void onMessageReceived(ChatMessage message) {
                appendMessage(message);
            }

            @Override
            public void onChattersChanged() {
                refreshChattersList();
            }
        });
        
        this.chattersList.setModel(this.chattersModel);
    }
    
    private void connectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectButtonActionPerformed
        String name = this.nameField.getText();
        String address = this.serverField.getText();
        int port = Integer.parseInt(this.portField.getText());
        String protocol = this.protocolComboBox.getSelectedItem().toString();
        
        if (this.controller.connectToServer(name, address, port, protocol))
        {
            this.connectButton.setEnabled(false);
            this.sendButton.setEnabled(true);
        }
        else JOptionPane.showMessageDialog(this, "Nepovedlo se připojit k chatovacímu serveru.");
    }//GEN-LAST:event_connectButtonActionPerformed

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
        String message = this.messageField.getText();
        
        if (this.controller.sendMessage(message))
        {
            this.messageField.setText("");
        }
        else JOptionPane.showMessageDialog(this, "Nepodařilo se odeslat zprávu.");
    }//GEN-LAST:event_sendButtonActionPerformed

    /**
     * Refreshes the list of connected chatters.
     */
    private void refreshChattersList() {
        this.chattersModel.clear();
                
        for (IChatter chatter : this.controller.getChattingRoom().getChatters())
        {
            this.chattersModel.addElement(chatter);
        }
    }
    /**
     * Appends a chat message to the message text area.
     * @param message chat message
     */
    private void appendMessage(ChatMessage message) {
        String formattedMessage = message.getOwner().getName();
        formattedMessage += ": " + message.getMessage();
        formattedMessage += " [" + message.getCreationTime().get(Calendar.HOUR) + ":" + message.getCreationTime().get(Calendar.MINUTE) + ":" + message.getCreationTime().get(Calendar.SECOND) + "]" + System.lineSeparator();
        
        this.chatMessagesArea.append(formattedMessage);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea chatMessagesArea;
    private javax.swing.JList chattersList;
    private javax.swing.JButton connectButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane messageArea;
    private javax.swing.JTextArea messageField;
    private javax.swing.JTextField nameField;
    private javax.swing.JTextField portField;
    private javax.swing.JComboBox protocolComboBox;
    private javax.swing.JButton sendButton;
    private javax.swing.JTextField serverField;
    // End of variables declaration//GEN-END:variables
}
