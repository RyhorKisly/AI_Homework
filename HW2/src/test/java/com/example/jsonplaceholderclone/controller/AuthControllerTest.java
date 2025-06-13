package com.example.jsonplaceholderclone.controller;

import com.example.jsonplaceholderclone.config.TestSecurityConfig;
import com.example.jsonplaceholderclone.dto.LoginRequest;
import com.example.jsonplaceholderclone.dto.RegisterRequest;
import com.example.jsonplaceholderclone.model.User;
import com.example.jsonplaceholderclone.security.JwtTokenProvider;
import com.example.jsonplaceholderclone.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
@Import(TestSecurityConfig.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JwtTokenProvider tokenProvider;

    private User testUser;
    private LoginRequest loginRequest;
    private RegisterRequest registerRequest;
    private Authentication authentication;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setId(1L);
        testUser.setUsername("testuser");
        testUser.setEmail("test@example.com");
        testUser.setPasswordHash("password123");
        testUser.setName("Test User");
        testUser.setPhone("1234567890");
        testUser.setWebsite("www.test.com");

        loginRequest = new LoginRequest();
        loginRequest.setUsername("testuser");
        loginRequest.setPassword("password123");

        registerRequest = new RegisterRequest();
        registerRequest.setUsername("newuser");
        registerRequest.setEmail("newuser@example.com");
        registerRequest.setPassword("password123");

        authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(tokenProvider.generateToken(authentication)).thenReturn("test.jwt.token");
    }

    @Test
    void login_WithValidCredentials_ShouldReturnToken() throws Exception {
        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("test.jwt.token"));

        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(tokenProvider).generateToken(authentication);
    }

    @Test
    void register_WithValidData_ShouldCreateUser() throws Exception {
        when(userService.existsByUsername(anyString())).thenReturn(false);
        when(userService.existsByEmail(anyString())).thenReturn(false);
        when(userService.save(any(User.class))).thenReturn(testUser);

        mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(status().isOk())
                .andExpect(content().string("User registered successfully!"));

        verify(userService).existsByUsername(registerRequest.getUsername());
        verify(userService).existsByEmail(registerRequest.getEmail());
        verify(userService).save(any(User.class));
    }

    @Test
    void register_WithExistingUsername_ShouldReturnBadRequest() throws Exception {
        when(userService.existsByUsername(anyString())).thenReturn(true);

        mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Username is already taken!"));

        verify(userService).existsByUsername(registerRequest.getUsername());
        verify(userService, never()).save(any(User.class));
    }

    @Test
    void register_WithExistingEmail_ShouldReturnBadRequest() throws Exception {
        when(userService.existsByUsername(anyString())).thenReturn(false);
        when(userService.existsByEmail(anyString())).thenReturn(true);

        mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Email is already in use!"));

        verify(userService).existsByUsername(registerRequest.getUsername());
        verify(userService).existsByEmail(registerRequest.getEmail());
        verify(userService, never()).save(any(User.class));
    }
} 