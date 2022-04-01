package com.revature.dao;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.revature.dto.ReimbursementDto;
import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementStatus;
import com.revature.model.ReimbursementType;
import com.revature.model.User;
import com.revature.utility.ConnectionUtility;


import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDao {

/*    public List<Reimbursement> getReimbursementForMgr() throws SQLException {
        try(Connection connection= ConnectionUtility.getConnection()){
            List<Reimbursement> reimbursementList= new ArrayList<>();
            String sql="select tr.reimb_reimbid, tr.reimb_amount, tr.reimb_description, tr.reimb_submitteddate, " +
                    "tr.reimb_author as AuthorID, tu2.user_name as Author,tr.reimb_resolver as ResolverId,tu.user_name as Resolver, " +
                    "tr.reimb_resolveddate, tr.reimb_reimb_typeid, rt.reimb_type,tr.reimb_reimb_statusid,trs.reimb_status, tr.reimb_receipt " +
                    "from public.tbl_reimbursement tr inner join tbl_reimbursement_type rt on tr.reimb_reimb_typeid = rt.reimb_typeid " +
                    "inner join tbl_reimbursement_status trs on tr.reimb_reimb_statusid = trs.reimb_statusid " +
                    "left outer join tbl_user tu on tr.reimb_resolver = tu.user_id " +
                    "left outer join tbl_user tu2 on tr.reimb_author = tu2.user_id";
            PreparedStatement preparedStatement= connection.prepareStatement(sql);
            ResultSet rs= preparedStatement.executeQuery();
            while(rs.next())
            {
                Reimbursement reimbursement= new Reimbursement();
                reimbursement.setReImbID(rs.getInt("reimb_reimbid"));
                reimbursement.setReAmount(rs.getDouble("reimb_amount"));
                reimbursement.setReDescription(rs.getString("reimb_description"));
                reimbursement.setReSubmittedDate(rs.getTimestamp("reimb_submitteddate"));
                reimbursement.setReAuthorID(rs.getInt("AuthorID"));
                reimbursement.setReAuthor(rs.getString("Author"));
                reimbursement.setReResolverID(rs.getInt("ResolverId"));
                reimbursement.setReResolver(rs.getString("Resolver"));
                reimbursement.setReResolvedDate(rs.getTimestamp("reimb_resolveddate"));
                reimbursement.setReTypeId(rs.getInt("reimb_reimb_typeid"));
                reimbursement.setReType(rs.getString("reimb_type"));
                reimbursement.setReTypeId(rs.getInt("reimb_reimb_statusid"));
                reimbursement.setReType(rs.getString("reimb_status"));
*//*                InputStream rImage=rs.getBinaryStream("reimb_receipt");
                reimbursement.setReReceipt(rImage);*//*
                //reimbursement.setReReceipt(rs.getBinaryStream("reimb_receipt"));
                reimbursementList.add(reimbursement);
            }
            return reimbursementList;
        }
    }*/

    public List<ReimbursementDto> getReimbursementForMgr() throws SQLException {
        try(Connection connection= ConnectionUtility.getConnection()){
            List<ReimbursementDto> reimbursementList= new ArrayList<>();
            String sql="select tr.reimb_reimbid, tr.reimb_amount, tr.reimb_description, tr.reimb_submitteddate, " +
                    "tr.reimb_author as AuthorID, tu2.user_name as Author,tr.reimb_resolver as ResolverId,tu.user_name as Resolver, " +
                    "tr.reimb_resolveddate, tr.reimb_reimb_typeid, rt.reimb_type,tr.reimb_reimb_statusid,trs.reimb_status, tr.reimb_receipt " +
                    "from public.tbl_reimbursement tr inner join tbl_reimbursement_type rt on tr.reimb_reimb_typeid = rt.reimb_typeid " +
                    "inner join tbl_reimbursement_status trs on tr.reimb_reimb_statusid = trs.reimb_statusid " +
                    "left outer join tbl_user tu on tr.reimb_resolver = tu.user_id " +
                    "left outer join tbl_user tu2 on tr.reimb_author = tu2.user_id";
            PreparedStatement preparedStatement= connection.prepareStatement(sql);
            ResultSet rs= preparedStatement.executeQuery();
            while(rs.next())
            {
                ReimbursementDto reimbursement= new ReimbursementDto();
                reimbursement.setReImbID(rs.getInt("reimb_reimbid"));
                reimbursement.setReAmount(rs.getDouble("reimb_amount"));
                reimbursement.setReDescription(rs.getString("reimb_description"));
                reimbursement.setReAuthorID(rs.getInt("AuthorID"));
                reimbursement.setReAuthor(rs.getString("Author"));
                reimbursement.setReResolver(rs.getString("Resolver"));
                reimbursement.setReType(rs.getString("reimb_type"));
                reimbursement.setReStatus(rs.getString("reimb_status"));
                String submittedDate = new Date(rs.getTimestamp("reimb_submitteddate").getTime()).toString();
                reimbursement.setSubmittedDate(submittedDate);
                Timestamp resolveDate = rs.getTimestamp("reimb_resolveddate");
                String resolveDateString;
                if (resolveDate != null) {
                    resolveDateString = new Date(rs.getTimestamp("reimb_submitteddate").getTime()).toString();
                    reimbursement.setSubmittedDate(resolveDateString);
                } else {
                    resolveDateString = null;
                }
                reimbursement.setSubmittedDate(resolveDateString);
                reimbursementList.add(reimbursement);
            }
            return reimbursementList;
        }
    }

    /*public List<Reimbursement> getReimbursementForEmp(int EmpID) throws SQLException {
        try(Connection connection= ConnectionUtility.getConnection()){
            List<Reimbursement> reimbursementList= new ArrayList<>();
            String sql="select tr.reimb_reimbid, tr.reimb_amount, tr.reimb_description, tr.reimb_submitteddate, " +
                    "tr.reimb_author as AuthorID, tu2.user_name as Author,tr.reimb_resolver as ResolverId,tu.user_name as Resolver, " +
                    "tr.reimb_resolveddate, tr.reimb_reimb_typeid, rt.reimb_type,tr.reimb_reimb_statusid,trs.reimb_status, tr.reimb_receipt " +
                    "from public.tbl_reimbursement tr inner join tbl_reimbursement_type rt on tr.reimb_reimb_typeid = rt.reimb_typeid " +
                    "inner join tbl_reimbursement_status trs on tr.reimb_reimb_statusid = trs.reimb_statusid " +
                    "left outer join tbl_user tu on tr.reimb_resolver = tu.user_id " +
                    "left outer join tbl_user tu2 on tr.reimb_author = tu2.user_id where tr.reimb_author =?";
            PreparedStatement preparedStatement= connection.prepareStatement(sql);
            preparedStatement.setInt(1, EmpID);
            ResultSet rs= preparedStatement.executeQuery();
            while(rs.next())
            {
                Reimbursement reimbursement= new Reimbursement();
                reimbursement.setReImbID(rs.getInt("reimb_reimbid"));
                reimbursement.setReAmount(rs.getDouble("reimb_amount"));
                reimbursement.setReDescription(rs.getString("reimb_description"));
                reimbursement.setReSubmittedDate(rs.getTimestamp("reimb_submitteddate"));
                reimbursement.setReAuthorID(rs.getInt("AuthorID"));
                reimbursement.setReAuthor(rs.getString("Author"));
                reimbursement.setReResolverID(rs.getInt("ResolverId"));
                reimbursement.setReResolver(rs.getString("Resolver"));
                reimbursement.setReResolvedDate(rs.getTimestamp("reimb_resolveddate"));
                reimbursement.setReTypeId(rs.getInt("reimb_reimb_typeid"));
                reimbursement.setReType(rs.getString("reimb_type"));
                reimbursement.setReTypeId(rs.getInt("reimb_reimb_statusid"));
                reimbursement.setReType(rs.getString("reimb_status"));
                InputStream rImage=rs.getBinaryStream("reimb_receipt");
                reimbursement.setReReceipt(rImage);
                reimbursementList.add(reimbursement);
            }
            return reimbursementList;
        }
    }*/

    public List<ReimbursementDto> getReimbursementForEmp(int EmpID,int statusID, int intSelectedType) throws SQLException {
        try(Connection connection= ConnectionUtility.getConnection()){
            List<ReimbursementDto> reimbursementList= new ArrayList<>();
            PreparedStatement preparedStatement;
            if(statusID != -1 && intSelectedType != -1) {
                String sql="select tr.reimb_reimbid, tr.reimb_amount, tr.reimb_description, tr.reimb_submitteddate, " +
                        "tr.reimb_author as AuthorID, tu2.user_name as Author,tr.reimb_resolver as ResolverId,tu.user_name as Resolver, " +
                        "tr.reimb_resolveddate, tr.reimb_reimb_typeid, rt.reimb_type,tr.reimb_reimb_statusid,trs.reimb_status, tr.reimb_receipt " +
                        "from public.tbl_reimbursement tr inner join tbl_reimbursement_type rt on tr.reimb_reimb_typeid = rt.reimb_typeid " +
                        "inner join tbl_reimbursement_status trs on tr.reimb_reimb_statusid = trs.reimb_statusid " +
                        "left outer join tbl_user tu on tr.reimb_resolver = tu.user_id " +
                        "left outer join tbl_user tu2 on tr.reimb_author = tu2.user_id where tr.reimb_author =? and tr.reimb_reimb_typeid=? and tr.reimb_reimb_statusid=? order by tr.reimb_reimbid desc";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, EmpID);
                preparedStatement.setInt(2, intSelectedType);
                preparedStatement.setInt(3, statusID);
            } else if (statusID != -1){
                String sql="select tr.reimb_reimbid, tr.reimb_amount, tr.reimb_description, tr.reimb_submitteddate, " +
                        "tr.reimb_author as AuthorID, tu2.user_name as Author,tr.reimb_resolver as ResolverId,tu.user_name as Resolver, " +
                        "tr.reimb_resolveddate, tr.reimb_reimb_typeid, rt.reimb_type,tr.reimb_reimb_statusid,trs.reimb_status, tr.reimb_receipt " +
                        "from public.tbl_reimbursement tr inner join tbl_reimbursement_type rt on tr.reimb_reimb_typeid = rt.reimb_typeid " +
                        "inner join tbl_reimbursement_status trs on tr.reimb_reimb_statusid = trs.reimb_statusid " +
                        "left outer join tbl_user tu on tr.reimb_resolver = tu.user_id " +
                        "left outer join tbl_user tu2 on tr.reimb_author = tu2.user_id where tr.reimb_author =? and tr.reimb_reimb_statusid=? order by tr.reimb_reimbid desc";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, EmpID);
                preparedStatement.setInt(2, statusID);
            } else if(intSelectedType != -1)
            {
                String sql="select tr.reimb_reimbid, tr.reimb_amount, tr.reimb_description, tr.reimb_submitteddate, " +
                        "tr.reimb_author as AuthorID, tu2.user_name as Author,tr.reimb_resolver as ResolverId,tu.user_name as Resolver, " +
                        "tr.reimb_resolveddate, tr.reimb_reimb_typeid, rt.reimb_type,tr.reimb_reimb_statusid,trs.reimb_status, tr.reimb_receipt " +
                        "from public.tbl_reimbursement tr inner join tbl_reimbursement_type rt on tr.reimb_reimb_typeid = rt.reimb_typeid " +
                        "inner join tbl_reimbursement_status trs on tr.reimb_reimb_statusid = trs.reimb_statusid " +
                        "left outer join tbl_user tu on tr.reimb_resolver = tu.user_id " +
                        "left outer join tbl_user tu2 on tr.reimb_author = tu2.user_id where tr.reimb_author =? and tr.reimb_reimb_typeid=? order by tr.reimb_reimbid desc";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, EmpID);
                preparedStatement.setInt(2, intSelectedType);

            }
            else{
                String sql="select tr.reimb_reimbid, tr.reimb_amount, tr.reimb_description, tr.reimb_submitteddate, " +
                        "tr.reimb_author as AuthorID, tu2.user_name as Author,tr.reimb_resolver as ResolverId,tu.user_name as Resolver, " +
                        "tr.reimb_resolveddate, tr.reimb_reimb_typeid, rt.reimb_type,tr.reimb_reimb_statusid,trs.reimb_status, tr.reimb_receipt " +
                        "from public.tbl_reimbursement tr inner join tbl_reimbursement_type rt on tr.reimb_reimb_typeid = rt.reimb_typeid " +
                        "inner join tbl_reimbursement_status trs on tr.reimb_reimb_statusid = trs.reimb_statusid " +
                        "left outer join tbl_user tu on tr.reimb_resolver = tu.user_id " +
                        "left outer join tbl_user tu2 on tr.reimb_author = tu2.user_id where tr.reimb_author =? order by tr.reimb_reimbid desc";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, EmpID);
            }

            ResultSet rs= preparedStatement.executeQuery();
            while(rs.next())
            {
                ReimbursementDto reimbursement= new ReimbursementDto();
                reimbursement.setReImbID(rs.getInt("reimb_reimbid"));
                reimbursement.setReAmount(rs.getDouble("reimb_amount"));
                reimbursement.setReDescription(rs.getString("reimb_description"));
                reimbursement.setReAuthorID(rs.getInt("AuthorID"));
                reimbursement.setReAuthor(rs.getString("Author"));
                reimbursement.setReResolver(rs.getString("Resolver"));
                reimbursement.setReType(rs.getString("reimb_type"));
                reimbursement.setReStatus(rs.getString("reimb_status"));
                String submittedDate = new Date(rs.getTimestamp("reimb_submitteddate").getTime()).toString();
                reimbursement.setSubmittedDate(submittedDate);
                Timestamp resolveDate = rs.getTimestamp("reimb_resolveddate");
                String resolveDateString;
                if (resolveDate != null) {
                    resolveDateString = new Date(rs.getTimestamp("reimb_submitteddate").getTime()).toString();
                    reimbursement.setSubmittedDate(resolveDateString);
                } else {
                    resolveDateString = null;
                }
                reimbursementList.add(reimbursement);
            }
            return reimbursementList;
        }
    }


  /*  public Reimbursement addReimbursementForEmp(Reimbursement reimbursement) throws SQLException {
        try(Connection connection= ConnectionUtility.getConnection()){
            connection.setAutoCommit(false);
            Reimbursement reimbursement1 = new Reimbursement();
            String sql="insert into tbl_Reimbursement " +
                    "(reimb_Amount,reimb_SubmittedDate,reimb_Description,reimb_receipt,reimb_Author,reimb_Reimb_StatusID,reimb_reimb_TypeID) " +
                    "values(?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement= connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setDouble(1, reimbursement.getReAmount());
            preparedStatement.setTimestamp(2, reimbursement.getReSubmittedDate());
            preparedStatement.setString(3, reimbursement.getReDescription());
            preparedStatement.setBinaryStream(4, reimbursement.getReReceipt());
            preparedStatement.setInt(5, reimbursement.getReAuthorID());
            preparedStatement.setInt(6, reimbursement.getReStatusId());
            preparedStatement.setInt(7, reimbursement.getReTypeId());
            preparedStatement.executeUpdate();
            ResultSet rs= preparedStatement.getGeneratedKeys();
            if (rs.next())
            {

                reimbursement1.setReImbID(rs.getInt("reimb_reimbid"));
                reimbursement1.setReAmount(reimbursement.getReAmount());
                reimbursement1.setReDescription(reimbursement.getReDescription());
                reimbursement1.setReSubmittedDate(reimbursement.getReSubmittedDate());
                reimbursement1.setReAuthorID(reimbursement.getReAuthorID());
                reimbursement1.setReAuthor(reimbursement.getReAuthor());
                reimbursement1.setReResolverID(reimbursement.getReResolverID());
                reimbursement1.setReResolver(reimbursement.getReResolver());
                reimbursement1.setReResolvedDate(reimbursement.getReResolvedDate());
                reimbursement1.setReTypeId(reimbursement.getReTypeId());
                reimbursement1.setReType(reimbursement.getReType());
                reimbursement1.setReStatusId(reimbursement.getReStatusId());
                reimbursement1.setReStatus(reimbursement.getReStatus());
                InputStream rImage=rs.getBinaryStream("reimb_receipt");

*//*                ObjectMapper mapper= new ObjectMapper();
                JsonNode jsonNode= mapper.readTree(rImage);
                reimbursement1.setReReceipt(jsonNode);
                InputStream rImage=rs.getBinaryStream("reimb_receipt");*//*

                //reimbursement1.setReReceipt((InputStream)reimbursement.getReReceipt());
                connection.commit();
            }
            return reimbursement1;
        }
    }*/


    public ReimbursementDto addReimbursementForEmp(Reimbursement reimbursement) throws SQLException {
        try(Connection connection= ConnectionUtility.getConnection()){
            connection.setAutoCommit(false);
            ReimbursementDto reimbursement1;
            String sql="insert into tbl_Reimbursement " +
                    "(reimb_Amount,reimb_SubmittedDate,reimb_Description,reimb_receipt,reimb_Author,reimb_Reimb_StatusID,reimb_reimb_TypeID) " +
                    "values(?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement= connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setDouble(1, reimbursement.getReAmount());
            preparedStatement.setTimestamp(2, reimbursement.getReSubmittedDate());
            preparedStatement.setString(3, reimbursement.getReDescription());
            preparedStatement.setBinaryStream(4, reimbursement.getReReceipt());
            preparedStatement.setInt(5, reimbursement.getReAuthorID());
            preparedStatement.setInt(6, reimbursement.getReStatusId());
            preparedStatement.setInt(7, reimbursement.getReTypeId());
            preparedStatement.executeUpdate();
            ResultSet rs= preparedStatement.getGeneratedKeys();
            if (rs.next())
            {

                int reimbID=rs.getInt("reimb_reimbid");
                connection.commit();
                return reimbursement1=getReimbursementByReimbursementIDForDTO(reimbID);
            }
        }
        return null;
    }

    public InputStream getRI_Image(int rID) throws SQLException {
        try(Connection connection= ConnectionUtility.getConnection()){
            String sql="select reimb_receipt from tbl_reimbursement where reimb_reimbid=?";
            PreparedStatement preparedStatement= connection.prepareStatement(sql);
            preparedStatement.setInt(1,rID);
            ResultSet rs= preparedStatement.executeQuery();
            if(rs.next())
            {
                InputStream rImage=rs.getBinaryStream("reimb_receipt");
                return rImage;
            }
        }
        return null;
    }

    public Reimbursement updateReimbursementStatus(int rId,int resolverUserID,int statusID, Timestamp resolvedDate) throws SQLException {
        try(Connection connection= ConnectionUtility.getConnection()){
            connection.setAutoCommit(false);
            String sql="update tbl_reimbursement set reimb_resolveddate=?, reimb_reimb_statusid=?, reimb_resolver=? where reimb_reimbid=?";
            PreparedStatement preparedStatement= connection.prepareStatement(sql);
            preparedStatement.setTimestamp(1,resolvedDate);
            preparedStatement.setInt(2,statusID);
            preparedStatement.setInt(3,resolverUserID);
            preparedStatement.setInt(4,rId);
            int noOfRowsExecuted= preparedStatement.executeUpdate();
            if(noOfRowsExecuted > 0)
            {
                connection.commit();
                return getReimbursementByReimbursementID(rId);
            }
        }
        return null;
    }
    public Reimbursement getReimbursementByReimbursementID(int reimbursementID) throws SQLException {
        Reimbursement reimbursement= new Reimbursement();
        try(Connection connection= ConnectionUtility.getConnection()){
            List<Reimbursement> reimbursementList= new ArrayList<>();
            String sql="select tr.reimb_reimbid, tr.reimb_amount, tr.reimb_description, tr.reimb_submitteddate, " +
                    "tr.reimb_author as AuthorID, tu2.user_name as Author,tr.reimb_resolver as ResolverId,tu.user_name as Resolver, " +
                    "tr.reimb_resolveddate, tr.reimb_reimb_typeid, rt.reimb_type,tr.reimb_reimb_statusid,trs.reimb_status, tr.reimb_receipt " +
                    "from public.tbl_reimbursement tr " +
                    "inner join tbl_reimbursement_type rt on tr.reimb_reimb_typeid = rt.reimb_typeid  " +
                    "inner join tbl_reimbursement_status trs on tr.reimb_reimb_statusid = trs.reimb_statusid  " +
                    "left outer join tbl_user tu on tr.reimb_resolver = tu.user_id " +
                    "left outer join tbl_user tu2 on tr.reimb_author = tu2.user_id  " +
                    "where tr.reimb_reimbid =?";
            PreparedStatement preparedStatement= connection.prepareStatement(sql);
            preparedStatement.setInt(1, reimbursementID);
            ResultSet rs= preparedStatement.executeQuery();
            while(rs.next())
            {

                reimbursement.setReImbID(rs.getInt("reimb_reimbid"));
                reimbursement.setReAmount(rs.getDouble("reimb_amount"));
                reimbursement.setReDescription(rs.getString("reimb_description"));
                reimbursement.setReSubmittedDate(rs.getTimestamp("reimb_submitteddate"));
                reimbursement.setReAuthorID(rs.getInt("AuthorID"));
                reimbursement.setReAuthor(rs.getString("Author"));
                reimbursement.setReResolverID(rs.getInt("ResolverId"));
                reimbursement.setReResolver(rs.getString("Resolver"));
                reimbursement.setReResolvedDate(rs.getTimestamp("reimb_resolveddate"));
                reimbursement.setReTypeId(rs.getInt("reimb_reimb_typeid"));
                reimbursement.setReType(rs.getString("reimb_type"));
                reimbursement.setReStatusId(rs.getInt("reimb_reimb_statusid"));
                reimbursement.setReStatus(rs.getString("reimb_status"));
                reimbursementList.add(reimbursement);
            }
            return reimbursement;
        }
    }


    public ReimbursementDto getReimbursementByReimbursementIDForDTO(int reimbursementID) throws SQLException {
        ReimbursementDto reimbursement= new ReimbursementDto();
        try(Connection connection= ConnectionUtility.getConnection()){
            String sql="select tr.reimb_reimbid, tr.reimb_amount, tr.reimb_description, tr.reimb_submitteddate, " +
                    "tr.reimb_author as AuthorID, tu2.user_name as Author,tr.reimb_resolver as ResolverId,tu.user_name as Resolver, " +
                    "tr.reimb_resolveddate, tr.reimb_reimb_typeid, rt.reimb_type,tr.reimb_reimb_statusid,trs.reimb_status, tr.reimb_receipt " +
                    "from public.tbl_reimbursement tr " +
                    "inner join tbl_reimbursement_type rt on tr.reimb_reimb_typeid = rt.reimb_typeid  " +
                    "inner join tbl_reimbursement_status trs on tr.reimb_reimb_statusid = trs.reimb_statusid  " +
                    "left outer join tbl_user tu on tr.reimb_resolver = tu.user_id " +
                    "left outer join tbl_user tu2 on tr.reimb_author = tu2.user_id  " +
                    "where tr.reimb_reimbid =?";
            PreparedStatement preparedStatement= connection.prepareStatement(sql);
            preparedStatement.setInt(1, reimbursementID);
            ResultSet rs= preparedStatement.executeQuery();
            while(rs.next())
            {
                reimbursement.setReImbID(rs.getInt("reimb_reimbid"));
                reimbursement.setReAmount(rs.getDouble("reimb_amount"));
                reimbursement.setReDescription(rs.getString("reimb_description"));
                String submittedDate = new Date(rs.getTimestamp("reimb_submitteddate").getTime()).toString();
                reimbursement.setSubmittedDate(submittedDate);
                reimbursement.setReAuthorID(rs.getInt("AuthorID"));
                reimbursement.setReAuthor(rs.getString("Author"));
                reimbursement.setReResolver(rs.getString("Resolver"));
                Timestamp resolveDate = rs.getTimestamp("reimb_resolveddate");
                String resolveDateString;
                if (resolveDate != null) {
                    resolveDateString = new Date(resolveDate.getTime()).toString();
                    reimbursement.setResolvedDate(resolveDateString);
                }
                reimbursement.setReType(rs.getString("reimb_type"));
                reimbursement.setReStatus(rs.getString("reimb_status"));
            }
            return reimbursement;
        }
    }

    public List<ReimbursementStatus> getReimbursementStatuses() throws SQLException {
        List<ReimbursementStatus> reimbursementStatusList= new ArrayList<>();
        try(Connection connection= ConnectionUtility.getConnection())
        {
            String sql = "select reimb_statusid, reimb_status from tbl_reimbursement_status";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs= preparedStatement.executeQuery();
            while (rs.next())
            {
                ReimbursementStatus reimbursementStatus= new ReimbursementStatus();
                reimbursementStatus.setStatusId(rs.getInt("reimb_statusid"));
                reimbursementStatus.setStatus(rs.getString("reimb_status"));
                reimbursementStatusList.add(reimbursementStatus);
            }
            return reimbursementStatusList;
        }
    }



    public List<ReimbursementType> getReimbursementTypes() throws SQLException {
        List<ReimbursementType> reimbursementTypeList= new ArrayList<>();
        try(Connection connection= ConnectionUtility.getConnection())
        {
            String sql = "select reimb_typeid,reimb_type from tbl_reimbursement_type";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs= preparedStatement.executeQuery();
            while (rs.next())
            {
                ReimbursementType reimbursementType= new ReimbursementType();
                reimbursementType.setTypeId(rs.getInt("reimb_typeid"));
                reimbursementType.setReimbursementType(rs.getString("reimb_type"));
                reimbursementTypeList.add(reimbursementType);
            }
            return reimbursementTypeList;
        }
    }

    public User addUser(User user) throws SQLException {
        User user1= new User();
        try(Connection connection= ConnectionUtility.getConnection()){
            String sql = "insert into tbl_user(user_name,user_password,user_firstname,user_lastname,user_email,user_userrollid) " +
                    "values(?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,user.getUserName());
            preparedStatement.setString(2,user.getUserPassword());
            preparedStatement.setString(3,user.getUserFirstName());
            preparedStatement.setString(4,user.getUserLastName());
            preparedStatement.setString(5,user.getUserEmail());
            preparedStatement.setInt(6,Integer.parseInt(user.getUserRole()));
            preparedStatement.executeUpdate();
            ResultSet rs= preparedStatement.getGeneratedKeys();

            if(rs.next())
            {
                user1.setUserID(rs.getInt("user_id"));
                user1.setUserName(user.getUserName());
                user1.setUserFirstName(user.getUserFirstName());
                user1.setUserLastName(user.getUserLastName());
                user1.setUserPassword(user.getUserPassword());
                user1.setUserEmail(user.getUserEmail());
                user1.setUserRole(user.getUserRole());
                return user1;
            }
        }

        return null;

    }

    public List<ReimbursementDto> getReimbursementForMgrByStatus(int statusID, int intSelectedType) throws SQLException {
        try(Connection connection= ConnectionUtility.getConnection()){
            List<ReimbursementDto> reimbursementList= new ArrayList<>();
            String sql;
            PreparedStatement preparedStatement;
            if(statusID != -1 && intSelectedType != -1) {
                sql = "select tr.reimb_reimbid, tr.reimb_amount, tr.reimb_description, tr.reimb_submitteddate, " +
                        "tr.reimb_author as AuthorID, tu2.user_name as Author,tr.reimb_resolver as ResolverId,tu.user_name as Resolver, " +
                        "tr.reimb_resolveddate, tr.reimb_reimb_typeid, rt.reimb_type,tr.reimb_reimb_statusid,trs.reimb_status, tr.reimb_receipt " +
                        "from public.tbl_reimbursement tr inner join tbl_reimbursement_type rt on tr.reimb_reimb_typeid = rt.reimb_typeid " +
                        "inner join tbl_reimbursement_status trs on tr.reimb_reimb_statusid = trs.reimb_statusid " +
                        "left outer join tbl_user tu on tr.reimb_resolver = tu.user_id " +
                        "left outer join tbl_user tu2 on tr.reimb_author = tu2.user_id where tr.reimb_reimb_statusid =? and tr.reimb_reimb_typeid =? order by tr.reimb_reimbid desc";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, statusID);
                preparedStatement.setInt(2, intSelectedType);
            }
            else if (intSelectedType != -1){
                sql = "select tr.reimb_reimbid, tr.reimb_amount, tr.reimb_description, tr.reimb_submitteddate, " +
                        "tr.reimb_author as AuthorID, tu2.user_name as Author,tr.reimb_resolver as ResolverId,tu.user_name as Resolver, " +
                        "tr.reimb_resolveddate, tr.reimb_reimb_typeid, rt.reimb_type,tr.reimb_reimb_statusid,trs.reimb_status, tr.reimb_receipt " +
                        "from public.tbl_reimbursement tr inner join tbl_reimbursement_type rt on tr.reimb_reimb_typeid = rt.reimb_typeid " +
                        "inner join tbl_reimbursement_status trs on tr.reimb_reimb_statusid = trs.reimb_statusid " +
                        "left outer join tbl_user tu on tr.reimb_resolver = tu.user_id " +
                        "left outer join tbl_user tu2 on tr.reimb_author = tu2.user_id where tr.reimb_reimb_typeid =? order by tr.reimb_reimbid desc";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, intSelectedType);
            } else if (statusID != -1){
                sql = "select tr.reimb_reimbid, tr.reimb_amount, tr.reimb_description, tr.reimb_submitteddate, " +
                        "tr.reimb_author as AuthorID, tu2.user_name as Author,tr.reimb_resolver as ResolverId,tu.user_name as Resolver, " +
                        "tr.reimb_resolveddate, tr.reimb_reimb_typeid, rt.reimb_type,tr.reimb_reimb_statusid,trs.reimb_status, tr.reimb_receipt " +
                        "from public.tbl_reimbursement tr inner join tbl_reimbursement_type rt on tr.reimb_reimb_typeid = rt.reimb_typeid " +
                        "inner join tbl_reimbursement_status trs on tr.reimb_reimb_statusid = trs.reimb_statusid " +
                        "left outer join tbl_user tu on tr.reimb_resolver = tu.user_id " +
                        "left outer join tbl_user tu2 on tr.reimb_author = tu2.user_id where tr.reimb_reimb_statusid =? order by tr.reimb_reimbid desc";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, statusID);
            } else
            {
                sql = "select tr.reimb_reimbid, tr.reimb_amount, tr.reimb_description, tr.reimb_submitteddate, " +
                        "tr.reimb_author as AuthorID, tu2.user_name as Author,tr.reimb_resolver as ResolverId,tu.user_name as Resolver, " +
                        "tr.reimb_resolveddate, tr.reimb_reimb_typeid, rt.reimb_type,tr.reimb_reimb_statusid,trs.reimb_status, tr.reimb_receipt " +
                        "from public.tbl_reimbursement tr inner join tbl_reimbursement_type rt on tr.reimb_reimb_typeid = rt.reimb_typeid " +
                        "inner join tbl_reimbursement_status trs on tr.reimb_reimb_statusid = trs.reimb_statusid " +
                        "left outer join tbl_user tu on tr.reimb_resolver = tu.user_id " +
                        "left outer join tbl_user tu2 on tr.reimb_author = tu2.user_id order by tr.reimb_reimbid desc";
                preparedStatement = connection.prepareStatement(sql);
           }

            ResultSet rs= preparedStatement.executeQuery();
            while(rs.next())
            {
                ReimbursementDto reimbursement= new ReimbursementDto();
                reimbursement.setReImbID(rs.getInt("reimb_reimbid"));
                reimbursement.setReAmount(rs.getDouble("reimb_amount"));
                reimbursement.setReDescription(rs.getString("reimb_description"));
                reimbursement.setReAuthorID(rs.getInt("AuthorID"));
                reimbursement.setReAuthor(rs.getString("Author"));
                reimbursement.setReResolver(rs.getString("Resolver"));
                reimbursement.setReType(rs.getString("reimb_type"));
                reimbursement.setReStatus(rs.getString("reimb_status"));
                String submittedDate = new Date(rs.getTimestamp("reimb_submitteddate").getTime()).toString();
                reimbursement.setSubmittedDate(submittedDate);
                Timestamp resolveDate = rs.getTimestamp("reimb_resolveddate");
                String resolveDateString;
                if (resolveDate != null) {
                    resolveDateString = new Date(rs.getTimestamp("reimb_submitteddate").getTime()).toString();
                    reimbursement.setSubmittedDate(resolveDateString);
                } else {
                    resolveDateString = null;
                }
                reimbursement.setSubmittedDate(resolveDateString);
                reimbursementList.add(reimbursement);
            }
            return reimbursementList;
        }
    }



}
