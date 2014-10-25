/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.klient.controller;

import chat.server.chat.ChattingRoom;
import javax.swing.JFrame;

/**
 * Relays messages between the view and model.
 */
public class ClientController {
    private JFrame view;
    
    private final ChattingRoom chattingRoom;
    
    public ClientController() {
        this.chattingRoom = new ChattingRoom();
    }
    
    public ChattingRoom getChattingRoom() {
        return this.chattingRoom;
    }
    
    public void setView(JFrame view) {
        this.view = view;
    }
    
    public boolean connectToServer(String address, int port, String protocol) {
        return true;
    }
    
    public boolean sendMessage(String message) {
        return true;
    }
}
