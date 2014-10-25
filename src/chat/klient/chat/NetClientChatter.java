/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.klient.chat;

import chat.klient.net.IClientConnector;

/**
 * Chatter that send messages using the internet.
 */
public class NetClientChatter implements IClientChatter {
    private final String name;
    private final IClientConnector client;
    
    public NetClientChatter(String name, IClientConnector client) {
        this.name = name;
        this.client = client;
    }
    
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void sendMessage(String message) {
        
    }
}
