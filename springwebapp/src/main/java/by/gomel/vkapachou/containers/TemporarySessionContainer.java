package by.gomel.vkapachou.containers;

import com.google.common.cache.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//import com.google.common.cache.*;

import javax.websocket.Session;

/**
 * Created by Администратор on 27.04.2017.
 */
@Component
public class TemporarySessionContainer {
    public final static int TIMEOUT = 60;
    private ConcurrentMap<String, Session> concurrentMap;

    public TemporarySessionContainer() {
        Cache<String, Session> cache = CacheBuilder.newBuilder()
                .expireAfterWrite(TIMEOUT, TimeUnit.SECONDS)
                .removalListener(createRemovalListener())
                .build();
        concurrentMap = cache.asMap();
    }

    protected RemovalListener createRemovalListener() {
        RemovalListener<String, Session> removalListener = new RemovalListener<String, Session>() {
            public void onRemoval(RemovalNotification<String, Session> removalNotification) {
                //http://www.atetric.com/atetric/javadoc/com.google.guava/guava/18.0/com/google/common/collect/MapMaker.RemovalCause.html
                //if entry wasn't removed by user
                if (removalNotification.getCause() != RemovalCause.EXPLICIT) {
                    Session session = session = removalNotification.getValue();
                    try {
                        session.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        return RemovalListeners.asynchronous(removalListener, Executors.newScheduledThreadPool(1));
    }

    public void add(Session session) {
        //session.getUserProperties().get("javax.websocket.endpoint.remoteAddress").toString()
        concurrentMap.put(session.getId(), session);
    }

    public void remove(Session session) {
        concurrentMap.remove(session.getId());
    }
}
