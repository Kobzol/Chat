/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.klient.chat;

import chat.klient.net.IClientConnector;
import chat.server.net.NetMessage;
import chat.server.net.NetMessage.NetMessageType;

/**
 * Chatter that send messages using the internet.
 */
public class NetClientChatter implements IClientChatter {
    private final String name;
    private final IClientConnector client;
    
    public NetClientChatter(String name, IClientConnector client) {
        this.name = name;
        this.client = client;
        
        this.sendName(this.name);
    }
    
    private void sendName(String name) {
        NetMessage<String> msg = new NetMessage<>(NetMessageType.SET_NAME, name);
        this.client.write(msg);
    }
    
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void sendMessage(String message) {
        NetMessage<String> msg = new NetMessage<>(NetMessageType.SEND_CHAT_MESSAGE, message);
        this.client.write(msg);
    }
}
