/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.server.chat;

import chat.server.net.IClient;
import chat.server.net.IClient.ClientEventListener;
import chat.server.net.MessageInterpreter;
import chat.server.net.NetMessage;
import chat.server.net.NetMessage.NetMessageType;
import java.io.Serializable;

/**
 *
 * @author Jakub
 */
public class NetChatter implements IChatter {
    private final IClient client;
    private final ChattingRoom chattingRoom;
    private final MessageInterpreter interpreter;
    
    private String name = "";
    
    public NetChatter(IClient client, ChattingRoom chattingRoom) {
        this.client = client;
        this.chattingRoom = chattingRoom;
        this.interpreter = new MessageInterpreter();
        
        this.setEvents();
    }
    
    private void setEvents() {
        final IChatter instance = this;
        
        this.client.addClientEventListener(new ClientEventListener() {
            @Override
            public void onMessageReceived(Serializable serializable) {
                handleMessage(interpreter.interpret(serializable));
            }

            @Override
            public void onClientDisconnected() {
                chattingRoom.removeChatter(instance);
            }
        });
    }

    @Override
    public String getName() {
        return this.name;
    }
    
    private void setNameAndRegister(String name) {
        this.name = name;
        this.chattingRoom.addChatter(this);
    }
    
    private void sendChatMessage(String message) {
        ChatMessage msg = new ChatMessage(this, message);
        this.chattingRoom.receiveMessage(msg);
    }
    
    private void handleMessage(NetMessage message) {
        if (message.getType() == NetMessageType.SET_NAME)
        {
            this.setNameAndRegister((String) message.getPayload());
        }
        else if (message.getType() == NetMessageType.SEND_CHAT_MESSAGE)
        {
            this.sendChatMessage((String) message.getPayload());
        }
    }
    
    @Override
    public String toString() {
        return this.getName();
    }
}
