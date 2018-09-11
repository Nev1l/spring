package by.gomel.vkapachou.exception;

import java.io.IOException;

/**
 * Created by Администратор on 30.04.2017.
 */
public class OAuthException extends IOException {
    public final static String RESPONSE_CODE_ERROR_MESSAGE = "HTTP code: ";

    public OAuthException(String message) {
        super(message);
    }

}
