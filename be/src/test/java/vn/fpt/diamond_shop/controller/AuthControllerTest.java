package vn.fpt.diamond_shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import vn.fpt.diamond_shop.dto.RegisterRequest;
import vn.fpt.diamond_shop.entity.User;
import vn.fpt.diamond_shop.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Author: Vo Nguyen Minh Nhat
 * This class is used to test the AuthController.
 * Testers should not edit this class.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Captor
    private ArgumentCaptor<User> userCaptor;

    /**
     * Set up the test environment before each test.
     */
    @BeforeEach
    public void setUp() {
        when(userRepository.existsByEmail("existing@test.com")).thenReturn(true);
    }

    /**
     * Test the register method with an email that is already in use.
     */
    @Test
    public void testRegisterEmailAlreadyInUse() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("existing@test.com");
        registerRequest.setName("Test User");
        registerRequest.setPassword("password");
        registerRequest.setProvider("LOCAL");

        mockMvc.perform(post("/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(registerRequest)))
                .andExpect(status().isBadRequest());

        // Verify that userRepository.save() was never called
        verify(userRepository, never()).save(any(User.class));
    }

    /**
     * Test the register method with valid input.
     */
    @Test
    public void testRegisterSuccess() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("test@test.com");
        registerRequest.setName("Test User");
        registerRequest.setPassword("password");
        registerRequest.setProvider("LOCAL");

        mockMvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(registerRequest)))
                .andExpect(status().isOk());

        // Capture the User object passed to userRepository.save()
        verify(userRepository).save(userCaptor.capture());

        // Assert that the fields of the captured User object match the fields of the RegisterRequest
        User savedUser = userCaptor.getValue();
        assertEquals(registerRequest.getEmail(), savedUser.getEmail());
        assertEquals(registerRequest.getName(), savedUser.getName());
        assertEquals(registerRequest.getProvider(), savedUser.getProvider().toString());
        assertEquals(passwordEncoder.encode(registerRequest.getPassword()), savedUser.getPassword());
    }

    /**
     * Convert an object to a JSON string.
     *
     * @param obj the object to convert
     * @return the JSON string representation of the object
     */
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}