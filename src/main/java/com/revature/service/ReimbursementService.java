package com.revature.service;

import com.revature.Exception.InvalidImageException;
import com.revature.Exception.UnableToCreateUserException;
import com.revature.dao.ReimbursementDao;
import com.revature.dao.UserDao;
import com.revature.dto.ReimbursementDto;
import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementStatus;
import com.revature.model.ReimbursementType;
import com.revature.model.User;
import org.apache.tika.Tika;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class ReimbursementService {
    private ReimbursementDao reimbursementDao;

    public ReimbursementService() {
        this.reimbursementDao =  new ReimbursementDao();
    }
    public ReimbursementService(ReimbursementDao mockReimbursementDao) {
        reimbursementDao = mockReimbursementDao;
    }

    public List<ReimbursementDto> getReimbursementForMgr() throws SQLException {
        return reimbursementDao.getReimbursementForMgr();
    }

    public List<ReimbursementDto> getReimbursementForMgrByStatus(String statusID, String selectedType) throws SQLException {
        try{
            int intStatusID= Integer.parseInt(statusID);
            int intSelectedType= Integer.parseInt(selectedType);
        return reimbursementDao.getReimbursementForMgrByStatus(intStatusID,intSelectedType);
        }
        catch ( Exception e)
        {

        }
        return null;
    }

/*    public List<Reimbursement> getReimbursementForEmp(int empId) throws SQLException {
        return reimbursementDao.getReimbursementForEmp(empId);
    }*/

    public List<ReimbursementDto> getReimbursementForEmp(String empId,String statusID, String selectedType ) throws SQLException {
        try{
            int intEmpID= Integer.parseInt(empId);
            int intStatusId = Integer.parseInt(statusID);
            int intSelectedType= Integer.parseInt(selectedType);

            return reimbursementDao.getReimbursementForEmp(intEmpID,intStatusId,intSelectedType);
        }
        catch(Exception e)
        {

        }
        return null;

    }
    public ReimbursementDto addReimbursementForEmp(Reimbursement reimbursement) throws SQLException, IOException, InvalidImageException {
        Timestamp submitted = Timestamp.valueOf(LocalDateTime.now());
        reimbursement.setReSubmittedDate(submitted);
        Tika tika = new Tika();
        String mimeType = tika.detect(reimbursement.getReReceipt());
        if (!mimeType.equals("image/jpeg") && !mimeType.equals("image/png") && !mimeType.equals("image/gif")) {
            throw new InvalidImageException("Image must be a JPEG, PNG, or GIF");
        }
        return reimbursementDao.addReimbursementForEmp(reimbursement);
    }
/*    public InputStream getReimbursementImgForID(int rId) throws SQLException {
        return reimbursementDao.getRI_Image(rId);
    }*/
    public InputStream getReimbursementImgForID(String stringrId) throws SQLException {
        try
        {
            int rId=Integer.parseInt(stringrId);
            return reimbursementDao.getRI_Image(rId);
        }
        catch (NumberFormatException e)
        {
            throw new IllegalArgumentException("Enter the proper reimbursement id");
        }

    }

    public Reimbursement updateReimbursementStatus(int rId,int userID,int statusID, Timestamp resolvedDate) throws SQLException {
        return reimbursementDao.updateReimbursementStatus(rId,userID,statusID,resolvedDate);
    }

    public List<ReimbursementStatus> getReimbursementStatus() throws SQLException {
        return reimbursementDao.getReimbursementStatuses();
    }

    public List<ReimbursementType> getReimbursementTypes() throws SQLException {
        return reimbursementDao.getReimbursementTypes();
    }

    public User addUser(User user) throws SQLException, UnableToCreateUserException {
        User user1= reimbursementDao.addUser(user);
        if(user1==null){
            throw new UnableToCreateUserException("Unable to create the user, please proivide the valid input.");
        }
        return user1;

    }


}
