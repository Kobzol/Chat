/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.server.controller;

import chat.server.chat.ChattingRoom;
import chat.server.view.ServerView;

/**
 *
 * @author Jakub
 */
public class ServerController {
    private ServerView view;
    private ChattingRoom chattingRoom;
    
    public ServerController() {
        this.chattingRoom = new ChattingRoom();
    }
    
    public void setView(ServerView view) {
        this.view = view;
    }
    
    public ChattingRoom getChattingRoom() {
        return this.chattingRoom;
    }
}
