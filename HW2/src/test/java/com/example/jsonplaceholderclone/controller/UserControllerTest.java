package com.example.jsonplaceholderclone.controller;

import com.example.jsonplaceholderclone.config.TestSecurityConfig;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@Import(TestSecurityConfig.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @MockBean
    private JwtTokenProvider tokenProvider;

    private User testUser;
    private Pageable pageable;

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

        pageable = PageRequest.of(0, 10);
    }

    @Test
    @WithMockUser
    void getAllUsers_ShouldReturnUsers() throws Exception {
        Page<User> userPage = new PageImpl<>(Arrays.asList(testUser));
        when(userService.findAll(any(Pageable.class))).thenReturn(userPage);

        mockMvc.perform(get("/api/users")
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(1))
                .andExpect(jsonPath("$.content[0].username").value("testuser"));

        verify(userService).findAll(any(Pageable.class));
    }

    @Test
    @WithMockUser
    void getUserById_WhenUserExists_ShouldReturnUser() throws Exception {
        when(userService.findById(1L)).thenReturn(Optional.of(testUser));

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("testuser"));

        verify(userService).findById(1L);
    }

    @Test
    @WithMockUser
    void getUserById_WhenUserDoesNotExist_ShouldReturnNotFound() throws Exception {
        when(userService.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isNotFound());

        verify(userService).findById(1L);
    }

    @Test
    @WithMockUser
    void createUser_ShouldReturnCreatedUser() throws Exception {
        when(userService.existsByEmail(anyString())).thenReturn(false);
        when(userService.existsByUsername(anyString())).thenReturn(false);
        when(userService.save(any(User.class))).thenReturn(testUser);

        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testUser)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("testuser"));

        verify(userService).existsByEmail(testUser.getEmail());
        verify(userService).existsByUsername(testUser.getUsername());
        verify(userService).save(any(User.class));
    }

    @Test
    @WithMockUser
    void updateUser_WhenUserExists_ShouldReturnUpdatedUser() throws Exception {
        when(userService.findById(1L)).thenReturn(Optional.of(testUser));
        when(userService.save(any(User.class))).thenReturn(testUser);

        mockMvc.perform(put("/api/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("testuser"));

        verify(userService).findById(1L);
        verify(userService).save(any(User.class));
    }

    @Test
    @WithMockUser
    void updateUser_WhenUserDoesNotExist_ShouldReturnNotFound() throws Exception {
        when(userService.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testUser)))
                .andExpect(status().isNotFound());

        verify(userService).findById(1L);
        verify(userService, never()).save(any(User.class));
    }

    @Test
    @WithMockUser
    void deleteUser_WhenUserExists_ShouldReturnNoContent() throws Exception {
        when(userService.findById(1L)).thenReturn(Optional.of(testUser));
        doNothing().when(userService).deleteById(1L);

        mockMvc.perform(delete("/api/users/1"))
                .andExpect(status().isNoContent());

        verify(userService).findById(1L);
        verify(userService).deleteById(1L);
    }

    @Test
    @WithMockUser
    void deleteUser_WhenUserDoesNotExist_ShouldReturnNotFound() throws Exception {
        when(userService.findById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/users/1"))
                .andExpect(status().isNotFound());

        verify(userService).findById(1L);
        verify(userService, never()).deleteById(anyLong());
    }
} 