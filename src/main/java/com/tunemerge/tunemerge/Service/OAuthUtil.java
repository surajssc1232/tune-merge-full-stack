/**
    * Generates the authorization URL for Spotify OAuth.
    * 
    * @return The authorization URL.
    */
    package com.tunemerge.tunemerge.Service;


    import com.tunemerge.tunemerge.Entity.AccessToken;
import com.tunemerge.tunemerge.Repository.AccesstokenRepositry;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
    import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
    import org.springframework.http.ResponseEntity;
    import org.springframework.stereotype.Service;
    import org.springframework.util.LinkedMultiValueMap;
    import org.springframework.util.MultiValueMap;
    import org.springframework.web.client.RestTemplate;

/**
     * Utility class for handling OAuth authentication with Spotify.
     */
    @Data
    @Service
    public  class OAuthUtil {
        private static final String CLIENT_ID = "cf215981c8e548e2b2a70b1e3e244001";
        private static final String CLIENT_SECRET= "559466c34a6343beb827e9d94e7bedc5";
        private static final String REDIRECT_URI = "http://localhost:8080/login/callback";
        
        @Autowired
        AccesstokenRepositry AccesstokenRepo;

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
       

        //this method will fetch the access token and store it in the database
        public AccessToken getAccessToken(String code) {
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

            AccessToken token= response.getBody();
            AccesstokenRepo.save(token);

            return token;
        }
    }