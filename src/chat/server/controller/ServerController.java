/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.server.controller;

import chat.server.chat.ChattingRoom;
import chat.server.net.ChatterManager;
import chat.server.net.IServer;
import chat.server.net.TcpServer;
import chat.server.view.ServerView;

/**
 *
 * @author Jakub
 */
public class ServerController {
    private ServerView view;
    
    private final ChattingRoom chattingRoom;
    
    private ChatterManager chatterManager;
    private IServer server;
    
    public ServerController() {
        this.chattingRoom = new ChattingRoom();
        this.startServer();
    }
    
    public void setView(ServerView view) {
        this.view = view;
    }
    
    public ChattingRoom getChattingRoom() {
        return this.chattingRoom;
    }
    
    public void startServer() {
        int port = 1339;
        
        try
        {
            this.server = new TcpServer(port);
            this.server.startListening();
            
            this.chatterManager = new ChatterManager(this.server, this.chattingRoom);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
