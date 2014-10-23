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
    
    public void show() {
        this.window.setVisible(true);
    }
    
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
    }
    
    private void refreshChattersList() {
        chattersModel.clear();
                
        for (IChatter chatter : controller.getChattingRoom().getChatters())
        {
            chattersModel.addElement(chatter);
        }
    }
    private void appendMessage(ChatMessage message) {
        window.getChatMessagesArea().append("\n" + message.getMessage());
    }
}
