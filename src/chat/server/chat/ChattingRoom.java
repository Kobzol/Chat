/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.server.chat;

import java.util.ArrayList;
import java.util.List;

/**
 * Room in which instances of IChatter chat.
 */
public class ChattingRoom {
    private final List<IChatter> chatters;
    private final List<ChatMessage> messages;
    
    public ChattingRoom() {
        this.chatters = new ArrayList<>();
        this.messages = new ArrayList<>();
    }
    
    public void addChatter(IChatter chatter) {
        this.chatters.add(chatter);
    }
    
    public List<IChatter> getChatters() {
        return this.chatters;
    }
    
    public List<ChatMessage> getMessages() {
        return this.messages;
    }
    
    public void receiveMessage(ChatMessage message) {
        if (this.chatters.contains(message.getOwner()))
        {
            this.messages.add(message);
        }
    }
    
}
