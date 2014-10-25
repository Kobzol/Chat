/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.klient.controller;

import chat.klient.chat.IClientChatter;
import chat.klient.chat.NetClientChatter;
import chat.klient.net.IClientConnector;
import chat.klient.net.TcpClientConnector;
import chat.server.chat.ChattingRoom;
import javax.swing.JFrame;

/**
 * Relays messages between the view and model.
 */
public class ClientController {
    private JFrame view;
    
    private final ChattingRoom chattingRoom;
    private IClientChatter chatter;
    
    public ClientController() {
        this.chattingRoom = new ChattingRoom();
    }
    
    public ChattingRoom getChattingRoom() {
        return this.chattingRoom;
    }
    
    public void setView(JFrame view) {
        this.view = view;
    }
    
    public boolean connectToServer(String clientName, String address, int port, String protocol) {
        IClientConnector client = null;
        
        if (protocol.equals("TCP/IP"))
        {
            try
            {
                client = new TcpClientConnector(address, port);
            }
            catch (Exception ex)
            {
                System.err.println(ex);
                
                return false;
            }
        }
        else if (protocol.equals("UDP"))
        {
            
        }
        else return false;
        
        this.chatter = new NetClientChatter(clientName, client);
        
        return true;
    }
    
    public boolean sendMessage(String message) {
        this.chatter.sendMessage(message);
        
        return true;
    }
}
