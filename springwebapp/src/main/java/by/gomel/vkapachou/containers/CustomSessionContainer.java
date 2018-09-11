package by.gomel.vkapachou.containers;

import by.gomel.vkapachou.websocket.SessionContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Viktar_Kapachou on 7/7/2017.
 */
@Component
public class CustomSessionContainer implements SessionContainerHandler{
    private static CustomSessionContainer ourInstance = new CustomSessionContainer();

    @Autowired
    private SessionContainer sessionContainer;// = new SessionContainer();
    //TODO: make checking for opening sessions
    @Autowired
    private TemporarySessionContainer tempSessionContainer;// = new TemporarySessionContainer();

    public static CustomSessionContainer getInstance() {
        return ourInstance;
    }

    public CustomSessionContainer() {
    }

    @Override
    public SessionContainer getSessionContainer() {
        return sessionContainer;
    }

    @Override
    public TemporarySessionContainer getTemporarySessionContainer() {
        return tempSessionContainer;
    }
}
