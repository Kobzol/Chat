/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.server.net;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * TCP/IP implementation of client.
 */
public class TcpClient implements IClient {
    private final List<MessageReceivedListener> messageListeners;
    private final Socket socket;
    
    private Thread inputListener;
    
    public TcpClient(Socket socket) {
        this.socket = socket;
        this.messageListeners = new ArrayList<>();
        
        this.startListening();
    }
    
    private void startListening() {
        this.inputListener = new Thread(new Runnable() {
            @Override
            public void run() {
                try
                {
                    ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                        
                    while (!inputListener.isInterrupted())
                    {
                        Serializable object = (Serializable) ois.readObject();
                        receiveMessage(object);
                    }
                    
                    ois.close();
                }
                catch (Exception e)
                {
                    // client disconnected or error occured
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
        for (MessageReceivedListener listener : this.messageListeners)
        {
            listener.onMessageReceived(object);
        }
    }
    
    @Override
    public boolean write(Serializable serializable) {
        try
        {
            ObjectOutputStream oos = new ObjectOutputStream(this.socket.getOutputStream());
            oos.writeObject(serializable);
            
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return false;
    }

    @Override
    public boolean isConnected() {
        return this.inputListener.isAlive() && this.socket.isConnected();
    }
    
    @Override
    public void addMessageReceivedListener(MessageReceivedListener listener) {
        this.messageListeners.add(listener);
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
    }
}
