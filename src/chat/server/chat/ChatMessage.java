/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.server.chat;

import java.util.Calendar;

/**
 * Represents a chat message sent by an instance of IChatter.
 */
public class ChatMessage {
    private final IChatter owner;
    private final String message;
    private final Calendar creationTime;
    
    public ChatMessage(IChatter owner, String message) {
        this.owner = owner;
        this.message = message;
        this.creationTime = Calendar.getInstance();
    }
    
    public IChatter getOwner() {
        return this.owner;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public Calendar getCreationTime() {
        return this.creationTime;
    }
}
