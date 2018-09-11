package by.gomel.vkapachou.communication;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import by.gomel.vkapachou.containers.CustomSessionContainer;
import by.gomel.vkapachou.containers.SessionContainerHandler;
import by.gomel.vkapachou.containers.TemporarySessionContainer;
import by.gomel.vkapachou.exception.UndefinedActionHandlerException;
import by.gomel.vkapachou.websocket.CustomConfigurator;
import by.gomel.vkapachou.websocket.SessionContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by Администратор on 15.04.2017.
 */
@Component
public class ActionManager implements ActionHandler, SessionContainerHandler {
    //TODO: create responseJSON field
    private Map<String, ActionHandler> actionList = new ConcurrentHashMap<String, ActionHandler>();

    public ActionManager() {
        actionList.put("login", new LoginActionHandler());
    }

    private ActionHandler define(String actionName) throws Exception{
        if(!actionList.containsKey(actionName)){
            throw new UndefinedActionHandlerException(actionName);
        }
        return actionList.get(actionName);
    }

    public void handle(RequestJSON request) throws Exception {
        ActionHandler actionHandler = define(request.getAction());
        actionHandler.handle(request);
    }

    @Bean
    public CustomSessionContainer getCustomSessionContainer(){
        return new CustomSessionContainer();//CustomConfigurator.getSpringContext().getBean(CustomSessionContainer.class);
    }

    @Override
    public SessionContainer getSessionContainer() {
        return getCustomSessionContainer().getSessionContainer();
    }

    @Override
    public TemporarySessionContainer getTemporarySessionContainer() {
        return getCustomSessionContainer().getTemporarySessionContainer();
    }

}
