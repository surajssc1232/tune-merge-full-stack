package com.tunemerge.tunemerge.Controller;


import java.io.IOException;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AcceptAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.tunemerge.tunemerge.Entity.AccessToken;
import com.tunemerge.tunemerge.Repository.AccesstokenRepositry;
import com.tunemerge.tunemerge.Service.OAuthUtil;




@Controller
public class LoginController {

    @Autowired
    OAuthUtil oauthUtil;
    @Autowired
    AccesstokenRepositry accesstokenRepositry;

    @GetMapping("/home")
    public ModelAndView welcome() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("homepage.html");
        return modelAndView;
}

    //this method will be called when the user clicks on the login button
    //it will redirect the user to the spotify authorization page
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

    @PostMapping("/login/callback")
    public AccessToken token(@RequestParam("code") String code) {

        // yeh post request karega spotify ki web api ko udher se access token aayega jo
        // database me jayega sidha

        return oauthUtil.getAccessToken(code);
    }
    

  
    

    

    
}