package com.revature.dao;

import com.revature.model.User;
import com.revature.utility.ConnectionUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    public User getLoginUserDetails(String userName) throws SQLException {
        try(Connection connection= ConnectionUtility.getConnection()){
            //String sql="select user_id,user_name,user_firstname,user_lastname,user_email,user_role,tu.user_password from tbl_user tu inner join tbl_userrole tur on tu.user_userrollid = tur.user_roleid where tu.user_name=? and tu.user_password=?";
            String sql="select user_id,user_name,user_firstname,user_lastname,user_email,user_role,tu.user_password from tbl_user tu inner join tbl_userrole tur on tu.user_userrollid = tur.user_roleid where tu.user_name=?";
            PreparedStatement preparedStatement= connection.prepareStatement(sql);
            preparedStatement.setString(1,userName);
            //preparedStatement.setString(2, password);
            ResultSet rs= preparedStatement.executeQuery();
            if(rs.next())
            {
                return new User(rs.getInt("user_id"),rs.getString("user_name"),rs.getString("user_firstname"),rs.getString("user_lastname"),rs.getString("user_email"),rs.getString("user_role"),rs.getString("user_password"));

            }


        }
        return null;
    }

}
