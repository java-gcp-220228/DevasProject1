package com.revature.controller;

import com.revature.dao.ReimbursementDao;
import com.revature.dto.ReimbursementDto;
import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementStatus;
import com.revature.model.ReimbursementType;
import com.revature.model.User;
import com.revature.service.JWTService;
import com.revature.service.ReimbursementService;
import io.javalin.Javalin;
import io.javalin.http.Handler;
import io.javalin.http.UnauthorizedResponse;
import io.javalin.http.UploadedFile;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.apache.tika.Tika;

import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import java.util.Date;


public class ReimbursementController implements Controller{
    private JWTService jwtService;
    private ReimbursementService reimbursementService;

    public ReimbursementController() {
        this.reimbursementService = new ReimbursementService();
        this.jwtService = JWTService.getInstance();
    }

    private Handler getReimbersement = (ctx) -> {
        try{
            String jwt = ctx.header("Authorization").split(" ")[1];
            Jws<Claims> token = this.jwtService.parseJwt(jwt);
            //List<Reimbursement> reimbursementList = new ArrayList<>();
            List<ReimbursementDto> reimbursementList = new ArrayList<>();
            if (!token.getBody().get("UserRole").toString().toLowerCase().equals("Employee".toLowerCase())) {
                throw new UnauthorizedResponse("You must be a employee to access this endpoint");
            }
            String userIdStr =ctx.pathParam("UserID");
            int userId= Integer.parseInt(userIdStr);
            if(!token.getBody().get("UserID").equals(userId))
            {
                throw new UnauthorizedResponse("You are not authorized to this page now!");
            }
            if((token.getBody().get("UserID").equals(userId)) && (token.getBody().get("UserRole").toString().toLowerCase().equals("Employee".toLowerCase())))
            {
                String reimbStatus=""+ctx.queryParam("ReimbStatus");
                String selectedType=""+ctx.queryParam("Reimbtype");
                if ((reimbStatus != null && !reimbStatus.equals("")) && (selectedType != null && !selectedType.equals("")))
                {
                    reimbursementList = this.reimbursementService.getReimbursementForEmp(userIdStr,reimbStatus,selectedType);
                }
                else {
                    //reimbursementList = this.reimbursementService.getReimbursementForEmp(userId);
                }

            } /*else if ((token.getBody().get("UserID").equals(userId)) && (token.getBody().get("UserRole").toString().toLowerCase().equals("Manager".toLowerCase())))
            {
                reimbursementList = this.reimbursementService.getReimbursementForMgr();
            }*/
            ctx.json(reimbursementList);
        }
        catch(Exception e)
        {
            ctx.json(e.getMessage());
        }

    };

    private Handler getReimbersementForMgr = (ctx) -> {
        try{
            String jwt = ctx.header("Authorization").split(" ")[1];
            Jws<Claims> token = this.jwtService.parseJwt(jwt);
            List<ReimbursementDto> reimbursementList = new ArrayList<>();
            if (!token.getBody().get("UserRole").toString().toLowerCase().equals("Manager".toLowerCase())) {
                throw new UnauthorizedResponse("You must be a Manager to access this endpoint");
            }

            if (token.getBody().get("UserRole").toString().toLowerCase().equals("Manager".toLowerCase()))
            {
                reimbursementList = this.reimbursementService.getReimbursementForMgr();
            }
            ctx.json(reimbursementList);
        }
        catch(Exception e)
        {
            ctx.json(e.getMessage());
        }

    };

    private Handler addReimbursement= (ctx) -> {

        if (ctx.header("Authorization") == null) {
            throw new UnauthorizedResponse("You must be logged in!!!");
        }
        String jwt = ctx.header("Authorization").split(" ")[1];
        Jws<Claims> token = this.jwtService.parseJwt(jwt);
        String userIdStr =ctx.pathParam("UserID");
        int userId= Integer.parseInt(userIdStr);
        if(!token.getBody().get("UserID").equals(userId))
        {
            throw new UnauthorizedResponse("You can add reimbursement for yourself only.");
        }
        // UploadedFile uploadedReceipt = ctx.uploadedFiles().get(0);
        UploadedFile uploadedReceipt= ctx.uploadedFile("image");
        InputStream inputStreamReceipt=uploadedReceipt.getContent();

        Reimbursement reimbursement = new Reimbursement();
        reimbursement.setReAmount(Double.parseDouble(ctx.formParam("amount")));
        reimbursement.setReAuthorID((Integer)token.getBody().get("UserID"));
        reimbursement.setReDescription(ctx.formParam("description"));
        reimbursement.setReTypeId(Integer.parseInt(ctx.formParam("type")));
        reimbursement.setReStatusId(Integer.parseInt(ctx.formParam("status")));
        reimbursement.setReReceipt(inputStreamReceipt);
        Timestamp submittedDate = Timestamp.valueOf(LocalDateTime.now());
        reimbursement.setReSubmittedDate(submittedDate);
        ReimbursementDto newReimbursement = reimbursementService.addReimbursementForEmp(reimbursement);
        ctx.status(201);
        ctx.json(newReimbursement);
    };


    private Handler getAssignmentImage = ctx -> {
        try
        {
            String assignmentId = ctx.pathParam("rID");
            InputStream image = reimbursementService.getReimbursementImgForID(assignmentId);

            Tika tika = new Tika();
            String mimeType = tika.detect(image);

            ctx.header("Content-Type", mimeType); // tell the client what type of image is being sent in the response
            ctx.status(201);
            ctx.result(image);
        }
        catch (Exception e)
        {
            ctx.status(400);
            ctx.json(e.getMessage());
        }

    };

    private Handler updateRimbursementStatus=(ctx) -> {
        try
        {
            if (ctx.header("Authorization") == null) {
                throw new UnauthorizedResponse("You must be logged in!!!");
            }
            String jwt = ctx.header("Authorization").split(" ")[1];
            Jws<Claims> token = this.jwtService.parseJwt(jwt);
            int userId= (Integer)token.getBody().get("UserID");

            String rIdStr= ctx.pathParam("rID");
            int rIdInt=Integer.parseInt(rIdStr);

            String updatedStatus= ctx.queryParam("status");
            int updatedStatusInt=Integer.parseInt(updatedStatus);
            Timestamp resolvedDate = Timestamp.valueOf(LocalDateTime.now());

            Reimbursement reimbursement = reimbursementService.updateReimbursementStatus(rIdInt,userId,updatedStatusInt,resolvedDate);
            ctx.status(201);
            ctx.result(String.valueOf(reimbursement));//need to check it
        }
        catch (Exception e)
        {
            ctx.status(400);
            ctx.json(e.getMessage());
        }
    };

    private Handler getRimbursementStatus=(ctx)->{
        try{
            List<ReimbursementStatus> reimbursementStatusList= new ArrayList<>();
            reimbursementStatusList = reimbursementService.getReimbursementStatus();
            ctx.status(201);
            ctx.json(reimbursementStatusList);
        }
        catch (Exception e){

        }
    };

    private Handler getRimbursementTypes=(ctx)->{
        try{

            List<ReimbursementType> reimbursementTypeList= new ArrayList<>();
            reimbursementTypeList = reimbursementService.getReimbursementTypes();
            ctx.status(201);
            ctx.json(reimbursementTypeList);
        }
        catch (Exception e){

        }
    };


    private Handler addUser= (ctx)->{
        User user= ctx.bodyAsClass(User.class);
        String encryptedPassword=this.jwtService.encryptString(user.getUserPassword());
        user.setUserPassword(encryptedPassword);
        User user1=reimbursementService.addUser(user);
        ctx.status(200);
        ctx.json(user1);


    };

    private Handler getGetReimbersementForMgrByStatus= ctx -> {
        try{
            String jwt = ctx.header("Authorization").split(" ")[1];
            Jws<Claims> token = this.jwtService.parseJwt(jwt);
            List<ReimbursementDto> reimbursementList = new ArrayList<>();
            if (!token.getBody().get("UserRole").toString().toLowerCase().equals("Manager".toLowerCase())) {
                throw new UnauthorizedResponse("You must be a Manager to access this endpoint");
            }
            String reimbStatus=""+ctx.queryParam("ReimbStatus");
            String selectedType=""+ctx.queryParam("Reimbtype");
            if (token.getBody().get("UserRole").toString().toLowerCase().equals("Manager".toLowerCase()))
            {
                if ((reimbStatus != null && !reimbStatus.equals("")) && (selectedType != null && !selectedType.equals("")))
                {
                        reimbursementList = this.reimbursementService.getReimbursementForMgrByStatus(reimbStatus,selectedType);
                }
                else
                {
                    reimbursementList = this.reimbursementService.getReimbursementForMgr();
                }

            }
            ctx.json(reimbursementList);
        }
        catch(Exception e)
        {
            ctx.json(e.getMessage());
        }

    };

    private Handler test=(ctx)->{
        ctx.json("test");
    };

    @Override
    public void mapEndPoints(Javalin app) {
        app.get("/employee/{UserID}/reimbursements",getReimbersement);

/*        app.get("/reimbursements",getReimbersementForMgr);*/
        app.get("/reimbursements",getGetReimbersementForMgrByStatus);
        //add reimbursement
        app.post("/employee/{UserID}/reimbursement",addReimbursement);
        //get reimm image
        app.get("/reimbursement/{rID}/image",getAssignmentImage);
        //update the status
        app.patch("/reimbursement/{rID}",updateRimbursementStatus);

        //get Statues
        app.get("/Statues",getRimbursementStatus);

        //get Statues
        app.get("/Types",getRimbursementTypes);
        //add user
        app.post("/user/add",addUser);

    }
}
