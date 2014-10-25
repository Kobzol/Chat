/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.server.net;

import java.io.Serializable;

/**
 * Wrapper class around a socket.
 */
public interface IClient {
    public static interface ClientEventListener {
        void onMessageReceived(Serializable serializable);
        void onClientDisconnected();
    }
    
    /**
     * Writes a serializable object to the output stream.
     * @param serializable serializable object
     * @return True if the write was successful, otherwise false.
     */
    boolean write(Serializable serializable);
    boolean isConnected();
    void addClientEventListener(ClientEventListener listener);
}
