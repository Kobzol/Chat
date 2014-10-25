/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.server.net;

import java.io.Serializable;

/**
 * Interprets internet messages.
 */
public class MessageInterpreter {
    public NetMessage interpret(Serializable serializable) {
        return (NetMessage) serializable;
    }
}
