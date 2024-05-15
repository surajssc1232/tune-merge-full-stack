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

    /**
     * Retrieves the user's playlists from Spotify using the provided access token.
     *
     * @param accessToken the access token for authenticating the request
     * @return a string representation of the user's playlists
     */


    public String getUserPlaylists(String accessToken) {
        /**
         * A synchronous client to perform HTTP requests, exposing a simple, template method API over underlying HTTP client libraries such as the JDK {@link HttpURLConnection}, Apache HttpComponents, and others.
         * This class is designed on the same principles as the {@link org.springframework.web.client.RestTemplate}, providing a higher-level, more convenient API for making HTTP requests.
         * It simplifies communication with HTTP servers and enforces RESTful principles.
         */
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
}