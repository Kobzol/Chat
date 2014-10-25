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
    private final boolean success;
    private final T payload;
    
    public NetMessage(T payload) {
        this(payload, true);
    }
    
    public NetMessage(boolean success) {
        this(null, success);
    }
    
    public NetMessage(T payload, boolean success) {
        this.payload = payload;
        this.success = success;
    }
    
    public boolean isSuccessful() {
        return this.success;
    }
    
    public T getPayload() {
        return this.payload;
    }
}
