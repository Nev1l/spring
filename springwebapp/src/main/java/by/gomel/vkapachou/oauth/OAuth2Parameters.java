package by.gomel.vkapachou.oauth;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONException;
import org.json.JSONObject;

import by.gomel.vkapachou.exception.OAuthException;

/**
 * Created by Администратор on 30.04.2017.
 */
public class OAuth2Parameters {
    //http://stackoverflow.com/questions/10835365/authenticate-programmatically-to-google-with-oauth2
    //http://stackoverflow.com/questions/14393811/how-to-use-google-oauth-java-client
    //http://stackoverflow.com/questions/10800028/google-plus-java-api-to-get-access-token
    //https://ru.stackoverflow.com/questions/509701/%D0%90%D0%B2%D1%82%D0%BE%D1%80%D0%B8%D0%B7%D0%B0%D1%86%D0%B8%D1%8F-%D0%B2-google-%D1%81-%D0%BF%D0%BE%D0%BC%D0%BE%D1%89%D1%8C%D1%8E-oauth-2-%D0%B1%D0%B5%D0%B7-%D0%B8%D1%81%D0%BF%D0%BE%D0%BB%D1%8C%D0%B7%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D1%8F-%D0%B1%D1%80%D0%B0%D1%83%D0%B7%D0%B5%D1%80%D0%B0
    //http://stackoverflow.com/questions/40574892/how-to-send-post-request-with-x-www-form-urlencoded-body
    public String responseType;
    public String clientId;
    public String clientSecret;
    public String redirectUri;
    public String scope;
    public String accessType;
    public String approvalPrompt;
    public String tokenType;
    //https://console.developers.google.com/apis/credentials?highlightClient=203684160636-im0vg4h4vlgcu0ng2tn62i8fd74e56ks.apps.googleusercontent.com&project=inductive-arena-166213
    public static final String testClientId= "203684160636-im0vg4h4vlgcu0ng2tn62i8fd74e56ks.apps.googleusercontent.com";
    public static final String testClientSecret = "DqiYr6ptTcuI00gUKrfUhI3j";

    public static class OAuth2ParametersBuilder {
        private static final String OAuthScopeKey = "scope";
        private static final String OAuth2ClientId = "client_id";
        private static final String OAuth2RedirectUri = "redirect_uri";
        private static final String OAuth2AccessType = "access_type";
        private static final String OAuth2ResponseType = "response_type";
        private static final String OAuth2ApprovalPrompt = "approval_prompt";
        private static final String OAuth2ClientSecret = "client_secret";
        private static final String OAuth2RefreshToken = "refresh_token";
        private static final String OAuth2GrantType = "grant_type";

        //"https://accounts.google.com/o/oauth2/token"
        //authUrl is https://accounts.google.com/o/oauth2/auth
        private static final String GOOGLE_API_HOST = "https://www.googleapis.com/";

        private String buildAuthorizationUrl(String authUrl, OAuth2Parameters parameters) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(authUrl);
            stringBuilder.append(String.format("?%s=%s", OAuth2ResponseType, parameters.responseType));
            stringBuilder.append(String.format("&%s=%s", OAuth2ClientId, parameters.clientId));
            stringBuilder.append(String.format("&%s=%s", OAuthScopeKey, parameters.scope));
            stringBuilder.append(String.format("&%s=%s", OAuth2AccessType, parameters.accessType));
            stringBuilder.append(String.format("&%s=%s", OAuth2ApprovalPrompt, parameters.approvalPrompt));
            stringBuilder.append(String.format("&%s=%s", OAuth2RedirectUri, parameters.redirectUri));
            return stringBuilder.toString();
        }

        private String buildAccessTokenUrl(String url, OAuth2Parameters parameters) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(url+"oauth2/v3/token?");
            stringBuilder.append(String.format("%s=%s", OAuth2ClientId, parameters.clientId));
            stringBuilder.append(String.format("&%s=%s", OAuth2ClientSecret, parameters.clientSecret));
            stringBuilder.append(String.format("&%s=%s", OAuth2RefreshToken, "_____________________"));
            stringBuilder.append(String.format("&%s=%s", OAuth2GrantType, OAuth2RefreshToken));
            return stringBuilder.toString();
        }

        private String getAccessToken(String url, OAuth2Parameters parameters) throws Exception {
            JSONObject object = handle(buildAccessTokenUrl(url,parameters));
            return object.getString("access_token");
        }

        public JSONObject handle(String url) throws IOException {
            HttpClient httpclient = HttpClients.createDefault();
            HttpPost post = new HttpPost(url);
            post.addHeader("Content-Type", "application/x-www-form-urlencoded");
            HttpResponse response = httpclient.execute(post);
            try {
                HttpEntity entity = response.getEntity();
                int code = response.getStatusLine().getStatusCode();
                InputStream body = entity.getContent();
                boolean result = false;
                if (code < 400) {
                    if (body != null) {
                        try {
                            return new JSONObject(new String(IOUtils.toByteArray(body)));
                        } catch (JSONException e) {
                            throw new OAuthException(e.getMessage());
                        }
                    }
                } else {
                    throw new OAuthException(OAuthException.RESPONSE_CODE_ERROR_MESSAGE + code);
                }
            } finally {
                post.releaseConnection();
            }
            throw new OAuthException("Response has NULL value");
        }
        /*
        HttpPost post = new HttpPost("https://accounts.google.com/o/oauth2/token");
        List <NameValuePair> nvps = new ArrayList <NameValuePair>();
        nvps.add(new BasicNameValuePair("code", code));
        nvps.add(new BasicNameValuePair("client_id", client_id));
        nvps.add(new BasicNameValuePair("client_secret", client_secret));
        nvps.add(new BasicNameValuePair("redirect_uri", redirect_uri));
        nvps.add(new BasicNameValuePair("grant_type", grant_type));

        post.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
        * */
    }
    //private static Credential authorize() throws Exception {
    //GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
    //      httpTransport, JSON_FACTORY, clientSecrets, SCOPES).setAccessType("offline").setApprovalPrompt("force").build();
    // authorize
    //return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("my@email.com");
    //}

    //new GoogleAuthTokenFactory.OAuth2Token(new GoogleCredential().setAccessToken(accessToken)))


}
