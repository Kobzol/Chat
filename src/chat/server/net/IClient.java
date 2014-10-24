/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.server.net;

/**
 * Wrapper class around a socket.
 */
public interface IClient {
    public static interface MessageReceivedListener {
        void onMessageReceived(String message);
    }
    
    void write(byte[] buffer, int length);
    void addMessageReceivedListener(MessageReceivedListener listener);
}
