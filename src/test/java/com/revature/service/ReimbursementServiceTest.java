package com.revature.service;


import com.revature.dao.ReimbursementDao;
import com.revature.dao.UserDao;
import com.revature.dto.ReimbursementDto;
import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementStatus;
import com.revature.model.ReimbursementType;
import com.revature.model.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.security.auth.login.FailedLoginException;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)  // ExtendWith is used in Jupiter (JUnit 5) to add additional functionality
// for running and executing tests. It allows us in particular to make use of the @Mock and @InjectMocks annotations
public class ReimbursementServiceTest {

    // Mock the DAO
    @Mock
    private ReimbursementDao reimbursementDao;

    // Automatically instantiate UserService with the Mocks injected
    @InjectMocks
    private ReimbursementService systemUnderTest;


    private AutoCloseable closeable;
    @BeforeAll
    public void set_up() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterAll
    public void close_mocks() throws Exception {
        closeable.close();
    }

    @Test
    public void getReimbursementForMgr() throws SQLException {

        List<ReimbursementDto> fakeReimbursementDtoList = new ArrayList<>();
        ReimbursementDto reimbursementDto = new ReimbursementDto(1,100,"Food Expense",1,"Lokesh","Sarvesh","Pending","Other","","","");
        fakeReimbursementDtoList.add(reimbursementDto);
        for(int i= 1; i< 4; i++)
        {
            ReimbursementDto  reimbursementDto1 = new ReimbursementDto();
            reimbursementDto1.setReImbID(i+1);
            reimbursementDto1.setReAmount(reimbursementDto.getReAmount()+1);
            reimbursementDto1.setReDescription(reimbursementDto.getReDescription()+1);
            reimbursementDto1.setReAuthorID(reimbursementDto.getReAuthorID()+1);
            reimbursementDto1.setReAuthor(reimbursementDto.getReAuthor()+1);
            reimbursementDto1.setReResolver(reimbursementDto.getReResolver()+1);
            reimbursementDto1.setReStatus(reimbursementDto.getReStatus()+1);
            reimbursementDto1.setReType(reimbursementDto.getReType()+1);
            reimbursementDto1.setReceiptURL(reimbursementDto.getReceiptURL()+1);
            reimbursementDto1.setSubmittedDate(reimbursementDto.getSubmittedDate()+1);
            reimbursementDto1.setResolvedDate(reimbursementDto.getResolvedDate()+1);
            fakeReimbursementDtoList.add(reimbursementDto1);
        }
        when(reimbursementDao.getReimbursementForMgr()).thenReturn(fakeReimbursementDtoList);
        List<ReimbursementDto> actualClientDetailsList= systemUnderTest.getReimbursementForMgr();
        Assertions.assertEquals(fakeReimbursementDtoList,actualClientDetailsList);
    }

    @Test
    public void getReimbursementForMgrByStatusTest() throws SQLException {
        List<ReimbursementDto> fakeReimbursementDtoList = new ArrayList<>();
        ReimbursementDto reimbursementDto = new ReimbursementDto(1,100,"Food Expense",1,"Lokesh","Sarvesh","Pending","Other","","","");
        fakeReimbursementDtoList.add(reimbursementDto);
        for(int i= 1; i< 4; i++)
        {
            ReimbursementDto  reimbursementDto1 = new ReimbursementDto();
            reimbursementDto1.setReImbID(i+1);
            reimbursementDto1.setReAmount(reimbursementDto.getReAmount()+1);
            reimbursementDto1.setReDescription(reimbursementDto.getReDescription()+1);
            reimbursementDto1.setReAuthorID(reimbursementDto.getReAuthorID()+1);
            reimbursementDto1.setReAuthor(reimbursementDto.getReAuthor()+1);
            reimbursementDto1.setReResolver(reimbursementDto.getReResolver()+1);
            reimbursementDto1.setReStatus(reimbursementDto.getReStatus()+1);
            reimbursementDto1.setReType(reimbursementDto.getReType()+1);
            reimbursementDto1.setReceiptURL(reimbursementDto.getReceiptURL()+1);
            reimbursementDto1.setSubmittedDate(reimbursementDto.getSubmittedDate()+1);
            reimbursementDto1.setResolvedDate(reimbursementDto.getResolvedDate()+1);
            fakeReimbursementDtoList.add(reimbursementDto1);
        }
        when(reimbursementDao.getReimbursementForMgrByStatus(eq(-1),eq(-1))).thenReturn(fakeReimbursementDtoList);
        //Act
        List<ReimbursementDto> actualClientDetailsList=systemUnderTest.getReimbursementForMgrByStatus("-1","-1");

        //Assertion
        Assertions.assertEquals(fakeReimbursementDtoList,actualClientDetailsList);
    }

    @Test
    public void getReimbursementForEmpTest() throws SQLException {
        List<ReimbursementDto> fakeReimbursementDtoList = new ArrayList<>();
        ReimbursementDto reimbursementDto = new ReimbursementDto(1,100,"Food Expense",1,"Lokesh","Sarvesh","Pending","Other","","","");
        fakeReimbursementDtoList.add(reimbursementDto);
        for(int i= 1; i< 4; i++)
        {
            ReimbursementDto  reimbursementDto1 = new ReimbursementDto();
            reimbursementDto1.setReImbID(i+1);
            reimbursementDto1.setReAmount(reimbursementDto.getReAmount()+1);
            reimbursementDto1.setReDescription(reimbursementDto.getReDescription()+1);
            reimbursementDto1.setReAuthorID(reimbursementDto.getReAuthorID()+1);
            reimbursementDto1.setReAuthor(reimbursementDto.getReAuthor()+1);
            reimbursementDto1.setReResolver(reimbursementDto.getReResolver()+1);
            reimbursementDto1.setReStatus(reimbursementDto.getReStatus()+1);
            reimbursementDto1.setReType(reimbursementDto.getReType()+1);
            reimbursementDto1.setReceiptURL(reimbursementDto.getReceiptURL()+1);
            reimbursementDto1.setSubmittedDate(reimbursementDto.getSubmittedDate()+1);
            reimbursementDto1.setResolvedDate(reimbursementDto.getResolvedDate()+1);
            fakeReimbursementDtoList.add(reimbursementDto1);
        }
        when(reimbursementDao.getReimbursementForEmp(eq(1),eq(-1),eq(-1))).thenReturn(fakeReimbursementDtoList);
        //Act
        List<ReimbursementDto> actualClientDetailsList=systemUnderTest.getReimbursementForEmp("1","-1","-1");

        //Assertion
        Assertions.assertEquals(fakeReimbursementDtoList,actualClientDetailsList);
    }
    @Test
    public void updateReimbursementStatusTest() throws SQLException {
        Timestamp resolvedDate = Timestamp.valueOf(LocalDateTime.now());
        Reimbursement reimbursement = new Reimbursement();
        reimbursement.setReImbID(1);
        reimbursement.setReAmount(100);
        reimbursement.setReDescription("test123");
        reimbursement.setReSubmittedDate(resolvedDate);
        reimbursement.setReAuthorID(1);
        reimbursement.setReAuthor("Lokesh777");
        reimbursement.setReResolverID(2);
        reimbursement.setReResolver("Sarvesh777");
        reimbursement.setReResolvedDate(resolvedDate);
        reimbursement.setReTypeId(1);
        reimbursement.setReType("Food");
        reimbursement.setReStatusId(2);
        reimbursement.setReStatus("Approved");
        reimbursement.setReceiptURL("c:/test");
        when(reimbursementDao.updateReimbursementStatus(eq(1), eq(2), eq(1), eq(resolvedDate))).thenReturn(reimbursement);
        //Act
        Reimbursement reimbursementActual=systemUnderTest.updateReimbursementStatus(1, 2, 1, resolvedDate);
        //Assertions
        Assertions.assertEquals(reimbursement, reimbursement);


    }

    @Test
    public void getReimbursementStatusTest() throws SQLException {
        //Arrange
        List<ReimbursementStatus> reimbursementStatusListExpected= new ArrayList<>();
        reimbursementStatusListExpected.add(new ReimbursementStatus(1,"Pending"));
        reimbursementStatusListExpected.add(new ReimbursementStatus(2,"Approved"));
        reimbursementStatusListExpected.add(new ReimbursementStatus(3,"Denied"));
        when(reimbursementDao.getReimbursementStatuses()).thenReturn(reimbursementStatusListExpected);
        //Act
        List<ReimbursementStatus> reimbursementStatusListActual= systemUnderTest.getReimbursementStatus();
        //Assert
        Assertions.assertEquals(reimbursementStatusListExpected,reimbursementStatusListActual);


    }

    @Test
    public void getReimbursementTypesTest() throws SQLException {
        //Arrange
        List<ReimbursementType>  reimbursementTypeListExpected = new ArrayList<>();
        reimbursementTypeListExpected.add(new ReimbursementType(1,"Food"));
        reimbursementTypeListExpected.add(new ReimbursementType(2,"Travel"));
        reimbursementTypeListExpected.add(new ReimbursementType(3,"Other"));
        when(reimbursementDao.getReimbursementTypes()).thenReturn(reimbursementTypeListExpected);
        //Act
        List<ReimbursementType> reimbursementStatusListActual= systemUnderTest.getReimbursementTypes();
        //Assert
        Assertions.assertEquals(reimbursementTypeListExpected,reimbursementStatusListActual);

    }



}
