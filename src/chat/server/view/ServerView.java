/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.server.view;

import chat.server.chat.ChatMessage;
import chat.server.chat.ChattingRoom;
import chat.server.chat.IChatter;
import chat.server.controller.ServerController;
import chat.server.gui.MainWindow;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.DefaultListModel;

/**
 *
 * @author Jakub
 */
public class ServerView {
    private final MainWindow window;
    private final ServerController controller;
    
    private final ChattingRoom chattingRoom;
    
    private final DefaultListModel chattersModel = new DefaultListModel();
    
    public ServerView(ServerController controller) {
        this.window = new MainWindow();
        this.controller = controller;
        this.controller.setView(this);
        
        this.chattingRoom = this.controller.getChattingRoom();
        
        this.setEvents();
    }
    
    /**
     * Shows the main window.
     */
    public void show() {
        this.window.setVisible(true);
    }
    
    /**
     * Sets the UI events.
     */
    private void setEvents() {
        this.chattingRoom.addChangeListener(new ChattingRoom.ChattingRoomChangedListener() {
            @Override
            public void onMessageReceived(ChatMessage message) {
                appendMessage(message);
            }

            @Override
            public void onChattersChanged() {
                refreshChattersList();
            }
        });       
        this.window.getChattersList().setModel(this.chattersModel);
        
        this.window.getStartButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String protocol = window.getProtocolComboBox().getSelectedItem().toString();
                
                window.getPortField().setText(controller.startServer(protocol) + "");
            }
        });
    }
    
    /**
     * Refreshes the list of connected chatters.
     */
    private void refreshChattersList() {
        chattersModel.clear();
                
        for (IChatter chatter : controller.getChattingRoom().getChatters())
        {
            chattersModel.addElement(chatter);
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
        
        window.getChatMessagesArea().append(formattedMessage);
    }
}
