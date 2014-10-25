/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.server.net;

import java.io.Serializable;

/**
 * Represents a net message with payload and state.
 * @param <T> payload type
 */
public class NetMessage<T> implements Serializable {
    public static enum NetMessageType {
        SET_NAME,
        SEND_CHAT_MESSAGE
    }
    
    private final NetMessageType type;
    private final boolean success;
    private final T payload;
    
    public NetMessage(NetMessageType type, T payload) {
        this(type, payload, true);
    }
    
    public NetMessage(NetMessageType type, boolean success) {
        this(type, null, success);
    }
    
    public NetMessage(NetMessageType type, T payload, boolean success) {
        this.type = type;
        this.payload = payload;
        this.success = success;
    }
    
    public NetMessageType getType() {
        return this.type;
    }
    
    public boolean isSuccessful() {
        return this.success;
    }
    
    public T getPayload() {
        return this.payload;
    }
}
