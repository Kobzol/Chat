/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.server.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * TCP/IP server implementatino.
 */
public class TcpServer implements IServer {
    private enum ServerState {
        Created,
        Running,
        Stopped
    }
    
    private final List<IClient> connectedClients;
    
    private final ServerSocket server;
    private final SocketAddress serverAddress;
    private volatile ServerState state;
    private Thread serverRunner;
    
    private final List<ServerConnectionListener> connectionListeners;
    
    public TcpServer(int port) throws IOException {
        this.serverAddress = new InetSocketAddress(port);
        this.server = new ServerSocket();
        this.state = ServerState.Created;
        
        this.connectedClients = new ArrayList<>();
        this.connectionListeners = new ArrayList<>();
    }
    
    private void receiveClient(TcpClient client) {
        this.connectedClients.add(client);
        
        for (ServerConnectionListener listener : this.connectionListeners)
        {
            listener.onClientConnected(client);
        }
    }
    
    public List<IClient> getClients() {
        return this.connectedClients;
    }
    
    @Override
    public void startListening() {
        if (this.state == ServerState.Running) return;
        
        try {
            this.server.bind(this.serverAddress);
            this.state = ServerState.Running;
        }
        catch (IOException ex) {
            System.err.println(ex);
        }
        
        this.serverRunner = new Thread(new Runnable() {
            @Override
            public void run() {
                while (state == ServerState.Running && !serverRunner.isInterrupted())
                {
                    try
                    {
                        Socket client = server.accept();
                        TcpClient tcpClient = new TcpClient(client);
                        
                        receiveClient(tcpClient);
                        
                    }
                    catch (IOException ex)
                    {
                        ex.printStackTrace();
                    }
                }
            }
        });
        
        this.serverRunner.start();
        
    }

    @Override
    public void stopListening() {
        try
        {
            this.state = ServerState.Stopped;
            this.serverRunner.interrupt();
            this.server.close();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void setServerConnectionListener(ServerConnectionListener listener) {
        this.connectionListeners.add(listener);
    }
}
