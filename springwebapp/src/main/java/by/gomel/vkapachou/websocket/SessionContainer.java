package by.gomel.vkapachou.websocket;

/**
 * Created by Администратор on 15.04.2017.
 */
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionContainer {
    private Map<String,Session> sessionByTokenList = new ConcurrentHashMap<String, Session>();
    private Map<Session,String> tokenBySessionList = new ConcurrentHashMap<Session,String>();

    public void addSession(String token, Session session){
        sessionByTokenList.put(token,session);
        tokenBySessionList.put(session,token);
    }

    public void removeSession(Session session){
        sessionByTokenList.remove(getTokenBySession(session));
        tokenBySessionList.remove(session);
    }

    public Session getSessionByToken(String token) {
        return sessionByTokenList.get(token);
    }

    public String getTokenBySession(Session session) {
        return tokenBySessionList.get(session);
    }
}
