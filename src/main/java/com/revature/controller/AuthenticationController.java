package com.revature.controller;

import com.revature.dto.UserLoginInfo;
import com.revature.model.User;
import com.revature.service.JWTService;
import com.revature.service.UserService;
import io.javalin.Javalin;
import io.javalin.http.Handler;

import javax.security.auth.login.FailedLoginException;
import javax.servlet.http.HttpSession;

public class AuthenticationController implements Controller {

     private UserService userService;
     private JWTService jwtService;
     public AuthenticationController(){

         userService= new UserService();
         jwtService= new JWTService();
     }
    private Handler loginHandler=(ctx)->{
        UserLoginInfo userLoginInfo=ctx.bodyAsClass(UserLoginInfo.class);
        User user=userService.getLoggedInDetails(userLoginInfo.getUserName());
        if(user == null || !(this.jwtService.encryptString(userLoginInfo.getPassword()).equals(user.getUserPassword())))
        {
            throw new FailedLoginException("Invalid username and/or password was provided");
        }

        String jwt= this.jwtService.createJWT(user);
        ctx.header("Access-Control-Expose-Headers", "*");
        ctx.header("Token", jwt);

        /* This code for HttPSession
        HttpSession session= ctx.req.getSession();
        session.setAttribute("currentUser",user);
        */
        ctx.json(user);

    };
    @Override
    public void mapEndPoints(Javalin app) {
        app.post("/login/",loginHandler);
    }
}
