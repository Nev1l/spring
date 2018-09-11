package by.gomel.vkapachou.controller;

import by.gomel.vkapachou.containers.TemporarySessionContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import by.gomel.vkapachou.websocket.CustomConfigurator;

/**
 * Created by Администратор on 09.04.2017.
 */

@Controller
public class RunnerController {
    private static final String VIEW_INDEX = "index";
    @Autowired
    private ApplicationContext springContext;

    @RequestMapping(value = "/index.do", method = RequestMethod.GET)
    public String welcome(ModelMap model) {
        System.out.println("IDEA:http://localhost:8080/index.do");
        System.out.println("Eclipse:http://localhost:8080/spring-webapp/index.do");
        CustomConfigurator config = springContext.getBean(CustomConfigurator.class);
        System.out.println(springContext.getBean(TemporarySessionContainer.class));
        System.out.println(config.getSpringContext().hashCode());
        System.out.println(springContext.hashCode());

        //tasklist /svc | find "java.exe"
        //netstat -ano | find "PID (process ID)"
        return VIEW_INDEX;
    }


}