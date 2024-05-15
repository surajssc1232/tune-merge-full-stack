/**
    * Generates the authorization URL for Spotify OAuth.
    * 
    * @return The authorization URL.
    */
    package com.tunemerge.tunemerge.Service;

    import java.net.URLEncoder;
    import java.nio.charset.StandardCharsets;

    import com.tunemerge.tunemerge.Entity.AccessToken;
    import lombok.Data;
    import org.springframework.http.HttpEntity;
    import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
    import org.springframework.http.ResponseEntity;
    import org.springframework.stereotype.Service;
    import org.springframework.util.LinkedMultiValueMap;
    import org.springframework.util.MultiValueMap;
    import org.springframework.web.client.RestTemplate;
import java.net.URLEncoder;

/**
     * Utility class for handling OAuth authentication with Spotify.
     */
    @Data
    @Service
    public  class OAuthUtil {
        private static final String CLIENT_ID = "cf215981c8e548e2b2a70b1e3e244001";
        private static final String CLIENT_SECRET= "559466c34a6343beb827e9d94e7bedc5";
        private static final String REDIRECT_URI = "http://localhost:8080/tune_merge";
        
        /**
         * Generates the authorization URL for Spotify OAuth authentication.
         */
        public static String getAuthURL (){
            String scope="playlist-read-private";
            String authURL="https://accounts.spotify.com/authorize?"
            +"client_id="+CLIENT_ID
            +"&response_type=code"
            +"&redirect_uri="+REDIRECT_URI
            +"&scope="+scope;

    
            return authURL;
        }
        /**
         * Retrieves the access token from Spotify using the provided authorization code.
         *
         * @param code the authorization code for authenticating the request
         * @return the access token
         */

        public static AccessToken getAccessToken(String code) {
            String tokenURL = "https://accounts.spotify.com/api/token";
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            
            MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
            body.add("grant_type", "authorization_code");
            body.add("code", code);
            body.add("redirect_uri", REDIRECT_URI);
            body.add("client_id", CLIENT_ID);
            body.add("client_secret", CLIENT_SECRET);
            
            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);
            
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<AccessToken> response = restTemplate.postForEntity(tokenURL, request, AccessToken.class);


            return response.getBody().getAccess_token();          
        }
    }