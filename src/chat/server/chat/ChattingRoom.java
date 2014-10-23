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
    public static interface ChattingRoomChangedListener {
        void onMessageReceived(ChatMessage message);
        void onChattersChanged();
    }  
    public static ChattingRoomChangedListener EmptyChattingRoomChangedListener = new ChattingRoomChangedListener() {
        @Override
        public void onMessageReceived(ChatMessage message) {
        }

        @Override
        public void onChattersChanged() {
        }      
    };
    
    private final List<IChatter> chatters;
    private final List<ChatMessage> messages;
    
    private final List<ChattingRoomChangedListener> changeListeners;
    
    public ChattingRoom() {
        this.chatters = new ArrayList<>();
        this.messages = new ArrayList<>();
        
        this.changeListeners = new ArrayList<>();
    }
    
    private void notifyRoomChanged() {
        for (ChattingRoomChangedListener listener : this.changeListeners)
        {
            listener.onChattersChanged();
        }
    }
    private void notifyMessageReceived(ChatMessage message) {
        for (ChattingRoomChangedListener listener : this.changeListeners)
        {
            listener.onMessageReceived(message);
        }
    }
    
    public void addChangeListener(ChattingRoomChangedListener listener) {
        this.changeListeners.add(listener);
    }
    
    public void addChatter(IChatter chatter) {
        this.chatters.add(chatter);
        this.notifyRoomChanged();
    }
    
    public void removeChatter(IChatter chatter) {
        this.chatters.remove(chatter);
        this.notifyRoomChanged();
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
            this.notifyMessageReceived(message);
        }
    }
    
}
