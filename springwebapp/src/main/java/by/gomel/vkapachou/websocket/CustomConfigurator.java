package by.gomel.vkapachou.websocket;

import javax.websocket.server.ServerEndpointConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Администратор on 09.04.2017.
 */

@Configuration
public class CustomConfigurator  extends ServerEndpointConfig.Configurator{
    @Autowired
    private static ApplicationContext springContext;

    public static ApplicationContext getSpringContext() {
        return springContext;
    }

    public void setSpringContext(ApplicationContext springContext) {
        this.springContext = springContext;
    }

}
