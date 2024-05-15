package com.tunemerge.tunemerge.Controller;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.tunemerge.tunemerge.Service.OAuthUtil;

import jakarta.servlet.http.HttpServletResponse;


@Controller

public class LoginController {

    @Autowired
    OAuthUtil oauthUtil;

    @GetMapping("/home")
    public ModelAndView welcome() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("homepage.html");
        return modelAndView;
}


    @GetMapping("/login")
    public RedirectView login() {
        String authURL = oauthUtil.getAuthURL();
        return new RedirectView(authURL);
    }

    @GetMapping("/chooseSource")
    public ModelAndView chooseSource() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("source.html");
        return modelAndView;
    }

    
    /**
     * Retrieves the access token using the provided authorization code.
     *
     * @param code The authorization code obtained from the OAuth provider.
     * @return A ResponseEntity containing the access token as a String.
     */
    
    @GetMapping("/tune_merge")
    public ResponseEntity<String> getAccessToken(@RequestParam String code) {
        String accessToken = oauthUtil.getAccessToken(code);
        return new ResponseEntity<>(accessToken, HttpStatus.OK);
    }
    

    

    
}