/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.klient.controller;

import chat.klient.chat.IClientChatter;
import chat.klient.chat.NetClientChatter;
import chat.klient.gui.MainWindow;
import chat.klient.net.IClientConnector;
import chat.klient.net.TcpClientConnector;
import chat.klient.net.UdpClientConnector;
import chat.server.chat.ChattingRoom;
import chat.server.net.IClient.ClientEventListener;
import java.io.Serializable;
import javax.swing.JOptionPane;

/**
 * Relays messages between the view and model.
 */
public class ClientController {
    private MainWindow view;
    
    private final ChattingRoom chattingRoom;
    private IClientChatter chatter;
    
    public ClientController() {
        this.chattingRoom = new ChattingRoom();
    }
    
    public ChattingRoom getChattingRoom() {
        return this.chattingRoom;
    }
    
    public void setView(MainWindow view) {
        this.view = view;
    }
    
    public boolean connectToServer(String clientName, String address, int port, String protocol) {
        IClientConnector client = null;
        
        try
        {
            if (protocol.equals("TCP/IP"))
            {
                client = new TcpClientConnector(address, port);
            }
            else if (protocol.equals("UDP"))
            {
                client = new UdpClientConnector(address, port);
            }
            else return false;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
        
        this.chatter = new NetClientChatter(clientName, client);
        
        client.addClientEventListener(new ClientEventListener() {
            @Override
            public void onMessageReceived(Serializable serializable) {
                view.appendMessage(serializable.toString());
            }

            @Override
            public void onClientDisconnected() {
                JOptionPane.showMessageDialog(view, "Byl jsi odpojen od chatovacího serveru.");
            }
        });
        
        return true;
    }
    
    public boolean sendMessage(String message) {
        this.chatter.sendMessage(message);
        
        return true;
    }
}
