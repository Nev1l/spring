package by.gomel.vkapachou.communication;

import by.gomel.vkapachou.containers.CustomSessionContainer;
import by.gomel.vkapachou.containers.SessionContainerHandler;
import by.gomel.vkapachou.containers.TemporarySessionContainer;
import by.gomel.vkapachou.websocket.CustomConfigurator;
import by.gomel.vkapachou.websocket.SessionContainer;
import com.google.gson.JsonObject;
import org.springframework.context.annotation.Bean;

/**
 * Created by Администратор on 15.04.2017.
 */
public class LoginActionHandler implements ActionHandler, SessionContainerHandler {

    public void handle(RequestJSON request) {
        JsonObject value = request.getValue();
        String accessToken = value.get("accessToken").getAsString();
        String tokenType = value.get("tokenType").getAsString();
        //use google oauth
        //get
        //return ResonseJSON()
    }

    @Bean
    public CustomSessionContainer getCustomSessionContainer(){
        return CustomConfigurator.getSpringContext().getBean(CustomSessionContainer.class);
    }

    @Override
    public SessionContainer getSessionContainer() {
        return getCustomSessionContainer().getSessionContainer();
    }

    @Override
    public TemporarySessionContainer getTemporarySessionContainer() {
        return getCustomSessionContainer().getTemporarySessionContainer();
    }
/*
    ==========================================
### login req

    {
        "action": "login",
            "value": {
        "accessToken": "acc1",
                "tokenType": "tt1"
    }
    }
    ==========================================
### login res
    {
        "action": "login",
            "result": {
        "name": "Angelina Jolie",
                "email": "angelina@jolie.com",
                "avatar": "http://hochu.ua/thumbnails/articles/cropr_640x490/59663_0.jpg"
    }
    }
    ==========================================
*/
}
