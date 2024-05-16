package com.tunemerge.tunemerge.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;
/**
 * This class represents a service for interacting with the Spotify API.
 */
@Service
public class SpotifyService {
    @Autowired
    OAuthUtil oAuthUtil;
    private static final String SPOTIFY_API_URL = "https://api.spotify.com/v1/me/playlists";


    public String getUserPlaylists(String accessToken) {
        
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(SPOTIFY_API_URL, HttpMethod.GET, entity, String.class);
        if(response.getStatusCode()==HttpStatus.OK){
            return response.getBody();
        }
        else{
            response.getStatusCode();
            return "Error fetching playlists.";
        }
    }

    //a function to get the user profile of the authorized user
        public String getUserProfile(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange("https://api.spotify.com/v1/me", HttpMethod.GET, entity, String.class);
        if(response.getStatusCode()==HttpStatus.OK){
            return response.getBody();
        }
        else{
            response.getStatusCode();
            return "Error fetching user profile. suraj";
        }
    }
}
