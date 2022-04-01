package com.revature.service;

import com.revature.dao.ReimbursementDao;
import com.revature.dao.UserDao;
import com.revature.model.User;
import com.revature.utility.ConnectionUtility;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import javax.security.auth.login.FailedLoginException;
import java.sql.SQLException;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)  // ExtendWith is used in Jupiter (JUnit 5) to add additional functionality
public class UserServiceTest {

    // Mock the DAO
    @Mock
    private UserDao mockDao;

    @Mock
    private ReimbursementDao reimbursementDao;

    // Automatically instantiate UserService with the Mocks injected
    @InjectMocks
    private UserService systemUnderTest;
    @InjectMocks
    private ReimbursementService reimbursementService;

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
    public void unitTestTheUserLogin() throws SQLException, FailedLoginException {
        when(mockDao.getLoginUserDetails(eq("test"))).thenReturn(
                new User(1,"test","firstname","lastName","abc@abc.com","Employee","password123"));
        //actual
        User userActual= systemUnderTest.getLoggedInDetails("test");
        //expected
        User userExpected= new User(1,"test","firstname","lastName","abc@abc.com","Employee","password123");
        Assertions.assertEquals(userExpected,userActual);

    }


    @Test
    public void testConnectionToDatabase() throws SQLException {
        ConnectionUtility.getConnection();
    }


}
