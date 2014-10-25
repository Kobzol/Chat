/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.server.net;

import java.util.List;

/**
 * Represents a server that listens for incoming connections
 * and returns instances of connected IClients.
 */
public interface IServer {
    public static interface ServerConnectionListener {
        void onClientConnected(IClient client);
    }
    
    void startListening();
    void stopListening();
    void setServerConnectionListener(ServerConnectionListener listener);
    List<IClient> getClients();
}
