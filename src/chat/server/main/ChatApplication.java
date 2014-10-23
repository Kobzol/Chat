/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.server.main;

import chat.server.controller.ServerController;
import chat.server.view.ServerView;

/**
 *
 * @author Jakub
 */
public class ChatApplication {
    public static void main(String[] args) {
        ServerController controller = new ServerController();
        ServerView serverView = new ServerView(controller);
        serverView.show();
    }
}
