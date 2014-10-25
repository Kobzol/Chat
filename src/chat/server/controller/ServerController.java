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
    private static final int SERVER_PORT = 1340;
    
    private ServerView view;
    
    private final ChattingRoom chattingRoom;
    
    private ChatterManager chatterManager;
    private IServer server;
    
    public ServerController() {
        this.chattingRoom = new ChattingRoom();
    }
    
    public void setView(ServerView view) {
        this.view = view;
    }
    
    public ChattingRoom getChattingRoom() {
        return this.chattingRoom;
    }
    
    /**
     * Start the server and returns the bound port.
     * @return port on which the server listens
     */
    public final int startServer() {       
        try
        {
            this.server = new TcpServer(ServerController.SERVER_PORT);
            this.server.startListening();
            
            this.chatterManager = new ChatterManager(this.server, this.chattingRoom);
            
            return this.server.getPort();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return 0;
        }
    }
}
