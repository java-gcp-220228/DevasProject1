package com.revature.controller;

import com.revature.Exception.InvalidImageException;
import com.revature.Exception.UnableToCreateUserException;
import io.javalin.Javalin;
import io.javalin.http.ExceptionHandler;

import javax.security.auth.login.FailedLoginException;


public class ExceptionController implements Controller {

    private ExceptionHandler<FailedLoginException> failedLogin=(exception, ctx) ->{
        ctx.status(400);
        ctx.json(exception.getMessage());
    };
    private ExceptionHandler<InvalidImageException> invalidImageSelection= (exception, ctx) ->{
        ctx.status(400);
        ctx.json(exception.getMessage());
    };

    private ExceptionHandler<IllegalArgumentException> invalidArgument = (exception, ctx) -> {
        ctx.status(400);
        ctx.json(exception.getMessage());
    };
    private ExceptionHandler<UnableToCreateUserException> unableToCreateUser = (exception, ctx) ->{
        ctx.status(400);
        ctx.json(exception.getMessage());
    };

    @Override
    public void mapEndPoints(Javalin app) {
        app.exception(FailedLoginException.class, failedLogin);
        app.exception(InvalidImageException.class, invalidImageSelection);
        app.exception(IllegalArgumentException.class, invalidArgument);
        app.exception(UnableToCreateUserException.class,unableToCreateUser);
    }
}
