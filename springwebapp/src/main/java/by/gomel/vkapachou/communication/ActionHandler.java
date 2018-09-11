package by.gomel.vkapachou.communication;

import by.gomel.vkapachou.containers.CustomSessionContainer;
import by.gomel.vkapachou.containers.SessionContainerHandler;

/**
 * Created by Администратор on 15.04.2017.
 */
public interface ActionHandler {
    //TODO: add all session containers here and provide methods for working wit it
    CustomSessionContainer getCustomSessionContainer();
    void handle(RequestJSON request) throws Exception;
}
