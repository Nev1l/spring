package by.gomel.vkapachou.containers;

import by.gomel.vkapachou.containers.TemporarySessionContainer;
import by.gomel.vkapachou.websocket.SessionContainer;

/**
 * Created by Viktar_Kapachou on 7/10/2017.
 */
public interface SessionContainerHandler {
    SessionContainer getSessionContainer();
    TemporarySessionContainer getTemporarySessionContainer();
}
