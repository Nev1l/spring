package by.gomel.vkapachou.websocket;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import by.gomel.vkapachou.communication.ActionManager;
import by.gomel.vkapachou.communication.RequestJSON;
import by.gomel.vkapachou.oauth.OAuth2Parameters;

/**
 * Created by Viktar_Kapachou on 04.04.2017.
 */
//https://stackoverflow.com/questions/29306854/serverendpoint-and-autowired

@ServerEndpoint(value = "/action", configurator = CustomConfigurator.class)
public class WebSocketServer implements ISocketServer {
    private ActionManager actionManager = new ActionManager();

    @OnOpen
    public void onOpen(Session session) {
        System.out.println(session.getId() + " has opened a connection");
        try {
            //TODO: create enum as parameter for session container
            // session.getUserProperties().put("token", token);
            actionManager.getTemporarySessionContainer().add(session);
            session.getBasicRemote().sendText("Connected!");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println(message);
        RequestJSON requestJSON = RequestJSON.create(message);
        try {
            System.out.println("Message from " + session.getId() + ": " + message);
            actionManager.handle(requestJSON);
            String token = "test";
            session.getBasicRemote().sendText("Message!");
            //TODO: create enum as parameter for session container
            actionManager.getTemporarySessionContainer().remove(session);
            actionManager.getSessionContainer().addSession(token, session);
            OAuth2Parameters.OAuth2ParametersBuilder otest= new  OAuth2Parameters.OAuth2ParametersBuilder();
            otest.handle("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(Session session) {
        actionManager.getSessionContainer().removeSession(session);
        System.out.println(session.getId() + " has closed a connection");
        try {
            session.getBasicRemote().sendText("Closed!");
            session.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}



