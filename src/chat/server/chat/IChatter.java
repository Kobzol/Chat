/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.server.chat;

/**
 * Represents a chatter that chats in a chatting room with other chatters.
 */
public interface IChatter {
    /**
     * Returns the chatter's name.
     * @return chatter's name
     */
    String getName();
}
