/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.server.net;

import chat.server.chat.ChattingRoom;
import chat.server.chat.IChatter;
import chat.server.chat.NetChatter;
import chat.server.net.IServer.ServerConnectionListener;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jakub
 */
public class ChatterManager {
    public static interface ChatterChangeListener {
        void onChatterChange();
    }
    
    private final List<ChatterChangeListener> eventListeners;
    
    private final IServer server;
    private final ChattingRoom chattingRoom;
    
    public ChatterManager(IServer server, ChattingRoom chattingRoom) {
        this.server = server;
        this.chattingRoom = chattingRoom;
        
        this.eventListeners = new ArrayList<>();
        
        this.setEvents();
    }
    
    public void addChatterChangeListener(ChatterChangeListener listener) {
        this.eventListeners.add(listener);
    }
    private void notifyChange() {
        for (ChatterChangeListener listener : this.eventListeners)
        {
            listener.onChatterChange();
        }
    }
    
    private void setEvents() {
        this.server.setServerConnectionListener(new ServerConnectionListener() {
            @Override
            public void onClientConnected(IClient client) {
                registerChatter(client);
            }
        });
    }
    
    private void registerChatter(IClient client) {
        IChatter chatter = new NetChatter(client, chattingRoom);
        this.notifyChange();
    }
}
