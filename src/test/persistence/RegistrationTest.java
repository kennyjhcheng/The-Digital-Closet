package persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationTest {
    Registration testRegistration;

    @BeforeEach
    void runBefore() {
        testRegistration = new Registration("testusername", "testpassword");
    }

    @Test
    void testGetUsername() {
        String theUser = testRegistration.getUsername();

        assertEquals(theUser, "testusername");
    }

    @Test
    void testSetUsername() {
        testRegistration.setUsername("user2");

        assertEquals(testRegistration.getUsername(), "user2");
    }

    @Test
    void testGetPassword() {
        assertEquals(testRegistration.getPassword(), "testpassword");
    }

    @Test
    void testSetPassword() {
        testRegistration.setPassword("pass2");

        assertEquals(testRegistration.getPassword(), "pass2");
    }

}
