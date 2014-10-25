/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.server.main;

import chat.server.controller.ServerController;
import chat.server.net.IClient;
import chat.server.net.IServer.ServerConnectionListener;
import chat.server.net.TcpServer;
import chat.server.view.ServerView;
import java.io.IOException;

/**
 *
 * @author Jakub
 */
public class ChatApplication {
    public static void main(String[] args) throws IOException {
        ServerController controller = new ServerController();
        ServerView serverView = new ServerView(controller);
        serverView.show();
        
        TcpServer ts = new TcpServer(1338);
        ts.setServerConnectionListener(new ServerConnectionListener() {
            @Override
            public void onClientConnected(IClient client) {
                client.write("ahoj");
            }
        });
        
        ts.startListening();
    }
}
