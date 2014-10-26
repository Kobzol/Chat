/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.server.net;

import chat.server.net.IClient.ClientEventListener;
import java.io.IOException;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * UDP (multicast) server implementation.
 */
public class UdpServer implements IServer {
    private enum ServerState {
        Created,
        Running,
        Stopped
    }
    
    private final List<IClient> connectedClients;
    
    private final MulticastSocket server;
    private final SocketAddress serverAddress;
    private volatile ServerState state;
    private Thread serverRunner;
    
    private final String address;
    private final int port;
    
    private final List<IServer.ServerConnectionListener> connectionListeners;
    
    public UdpServer(String address, int port) throws IOException {
        this.serverAddress = new InetSocketAddress(port);
        this.server = new MulticastSocket(port);
        this.server.joinGroup(InetAddress.getByName(address));
        this.state = ServerState.Created;
        
        this.address = address;
        this.port = port;
        
        this.connectedClients = new ArrayList<>();
        this.connectionListeners = new ArrayList<>();
    }
    
    private void receiveClient(final TcpClient client) {
        this.connectedClients.add(client);
        
        for (IServer.ServerConnectionListener listener : this.connectionListeners)
        {
            listener.onClientConnected(client);
        }
        
        client.addClientEventListener(new ClientEventListener() {
            @Override
            public void onMessageReceived(Serializable serializable) {
                
            }

            @Override
            public void onClientDisconnected() {
                connectedClients.remove(client);
            }
        });
    }
    
    @Override
    public List<IClient> getClients() {
        return this.connectedClients;
    }
    
    @Override
    public void startListening() {
        if (this.state == ServerState.Running) return;
        
        this.state = ServerState.Running;
        
        this.serverRunner = new Thread(new Runnable() {
            @Override
            public void run() {
                while (state == ServerState.Running && !serverRunner.isInterrupted())
                {
                    try
                    {
                        byte[] buffer = "ahoj".getBytes();
                        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(address), port);
                        server.send(packet);
                        
                        Thread.sleep(2000);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
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
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void setServerConnectionListener(IServer.ServerConnectionListener listener) {
        this.connectionListeners.add(listener);
    }

    @Override
    public int getPort() {
        return this.port;
    }
}
