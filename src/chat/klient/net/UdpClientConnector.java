/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.klient.net;

import chat.server.net.NetMessage;
import java.io.IOException;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Client that can sends and receives messages using UDP (multicast).
 */
public class UdpClientConnector implements IClientConnector {
    private final List<ClientEventListener> eventListeners;
    private final MulticastSocket socket;
    
    private final String address;
    private final int port;
    
    private Thread inputListener;
    
    public UdpClientConnector(String address, int port) throws IOException {
        this.eventListeners = new ArrayList<>();
        this.socket = new MulticastSocket(port);
        socket.joinGroup(InetAddress.getByName(address));
        
        this.address = address;
        this.port = port;
        
        this.startListening();
    }
            
    @Override
    public boolean write(Serializable serializable) {
        NetMessage msg = (NetMessage) serializable;
        
        byte[] buffer = (msg.getPayload().toString()).getBytes();
        DatagramPacket packet = null;
        
        try
        {
            packet = new DatagramPacket(buffer, buffer.length, InetAddress.getByName(this.address), this.port);
        }
        catch (UnknownHostException ex)
        {
            ex.printStackTrace();
            return false;
        }
        
        try
        {
            this.socket.send(packet);
            
            return true;
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
            
            return false;
        }
    }

    @Override
    public boolean isConnected() {
        return this.socket.isConnected();
    }

    @Override
    public void addClientEventListener(ClientEventListener listener) {
        this.eventListeners.add(listener);
    }
    
    private void startListening() {
        this.inputListener = new Thread(new Runnable() {
            @Override
            public void run() {
                try
                {    
                    while (!inputListener.isInterrupted())
                    {
                        byte[] buffer = new byte[256];
                        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                        socket.receive(packet);
                        
                        receiveMessage(new String(packet.getData(), Charset.defaultCharset()));
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    cleanup();
                }
            }
        });
        
        this.inputListener.start();
    }
    private void receiveMessage(Serializable object) {
        for (ClientEventListener listener : this.eventListeners)
        {
            listener.onMessageReceived(object);
        }
    }
    
     private void notifyClientDisconnected() {
        for (ClientEventListener listener : this.eventListeners)
        {
            listener.onClientDisconnected();
        }
    }
    
    /**
     * Cleans up resources.
     */
    private void cleanup() {
        try
        {
            if (this.inputListener.isAlive())
            {
                this.inputListener.interrupt();
            }
            
            this.socket.leaveGroup(this.socket.getInetAddress());
            
            if (!this.socket.isClosed())
            {
                this.socket.close();
            }
        }
        catch (Exception e)
        {
            
        }
        finally
        {
            this.notifyClientDisconnected();
        }
    }
}
