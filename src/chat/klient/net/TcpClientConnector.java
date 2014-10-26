/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.klient.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Client that can connect to a remote server using TCP/IP.
 */
public class TcpClientConnector implements IClientConnector {
    private final List<ClientEventListener> eventListeners;
    private final Socket socket;
    
    private Thread inputListener;
    
    public TcpClientConnector(String address, int port) throws IOException {
        this.socket = new Socket(address, port);
        this.eventListeners = new ArrayList<>();
        
        this.startListening();
    }

    @Override
    public synchronized boolean write(Serializable serializable) {
        try
        {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(serializable);
            oos.flush();
            
            return true;
        }
        catch (IOException ex)
        {
            System.err.println(ex);
            
            this.cleanup();
            
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
                        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                        
                        Serializable object = (Serializable) ois.readObject();
                        receiveMessage(object);
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
            
            if (!this.socket.isInputShutdown())
            {
                this.socket.getInputStream().close();
            }
            if (!this.socket.isOutputShutdown())
            {
                this.socket.getOutputStream().close();
            }
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
