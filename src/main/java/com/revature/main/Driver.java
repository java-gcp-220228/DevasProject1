package com.revature.main;

import com.revature.controller.*;
import com.revature.dao.UserDao;
import com.revature.model.User;
import com.revature.service.ReimbursementService;
import com.revature.utility.ConnectionUtility;
import io.javalin.Javalin;

import java.sql.SQLException;

public class Driver {
        public static void main(String[] args) throws SQLException {

        Javalin app= Javalin.create((config)->{config.enableCorsForAllOrigins();
        //config.enableCorsForOrigin("http://localhost:8081/");
        });
        map(app,new AuthenticationController(), new ReimbursementController(), new ExceptionController());
        app.start(8081);

    }

    public static void map(Javalin app, Controller... controllers)
    {
        for (Controller controller: controllers) {
            controller.mapEndPoints(app);

        }
    }
}
