package com.fontys.s3.buyahouse.business.impl;

import com.fontys.s3.buyahouse.business.AccessTokenEncoder;
import com.fontys.s3.buyahouse.business.exceptions.InvalidCredentialsException;
import com.fontys.s3.buyahouse.domain.AccessToken;
import com.fontys.s3.buyahouse.domain.LoginRequest;
import com.fontys.s3.buyahouse.domain.LoginResponse;
import com.fontys.s3.buyahouse.persistance.RoleRepository;
import com.fontys.s3.buyahouse.persistance.UserRepository;
import com.fontys.s3.buyahouse.persistance.entity.RoleEnum;
import com.fontys.s3.buyahouse.persistance.entity.UserEntity;
import com.fontys.s3.buyahouse.persistance.entity.UserRoleEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoginUseCaseImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private AccessTokenEncoder accessTokenEncoder;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private RoleRepository roleRepository;
    @InjectMocks
    private LoginUseCaseImpl loginUseCase;


    @Test
    void logInShouldReturnAnAccessToken(){

        UserRoleEntity userRole = UserRoleEntity.builder().id(1L).role(RoleEnum.USER).build();

        when(roleRepository.findByRole(RoleEnum.USER)).thenReturn(userRole);

        UserRoleEntity role = roleRepository.findByRole(RoleEnum.USER);

        String encodedPassword = passwordEncoder.encode("password");

        UserEntity user = UserEntity.builder()
                .userId(1L)
                .username("username")
                .password(encodedPassword)
                .phoneNumber("123456789")
                .firstName("John")
                .lastName("Doe")
                .userRole(role)
                .build();

        when(userRepository.findByUsername("username")).thenReturn(user);

        when(passwordEncoder.matches("password", encodedPassword)).thenReturn(true);

        String accessToken = accessTokenEncoder.encode(
                AccessToken.builder()
                        .subject(user.getUsername())
                        .roles(List.of(user.getUserRole().getRole().toString()))
                        .userId(user.getUserId())
                        .build());

        LoginRequest request = LoginRequest.builder()
                .username("username")
                .password("password")
                .build();

        LoginResponse actual = loginUseCase.login(request);
        LoginResponse expected = LoginResponse.builder().userId(user.getUserId()).username(user.getUsername()).phoneNumber(user.getPhoneNumber()).firstName(user.getFirstName()).lastName(user.getLastName()).userRole(user.getUserRole()).accessToken(accessToken).build();

        assertEquals(expected, actual);

        verify(roleRepository).findByRole(RoleEnum.USER);
        verify(userRepository).findByUsername("username");
        verify(passwordEncoder).matches("password", encodedPassword);
    }

    @Test
    void logInShouldReturnInvalidCredentialsIfTheUserDoesntExist() {

        when(userRepository.findByUsername("username")).thenReturn(null);

        LoginRequest request = LoginRequest.builder()
                .username("username")
                .password("password")
                .build();

        assertThrows(InvalidCredentialsException.class, () -> loginUseCase.login(request));

        verify(userRepository).findByUsername("username");
    }

    @Test
    void logInShouldReturnInvalidCredentialsIfThePasswordIsWrong() {

        UserRoleEntity userRole = UserRoleEntity.builder().id(1L).role(RoleEnum.USER).build();

        when(roleRepository.findByRole(RoleEnum.USER)).thenReturn(userRole);

        UserRoleEntity role = roleRepository.findByRole(RoleEnum.USER);

        String encodedPassword = passwordEncoder.encode("password");

        UserEntity user = UserEntity.builder()
                .userId(1L)
                .username("username")
                .password(encodedPassword)
                .phoneNumber("123456789")
                .firstName("John")
                .lastName("Doe")
                .userRole(role)
                .build();

        when(userRepository.findByUsername("username")).thenReturn(user);

        when(passwordEncoder.matches("password", encodedPassword)).thenReturn(false);

        LoginRequest request = LoginRequest.builder()
                .username("username")
                .password("password")
                .build();

        assertThrows(InvalidCredentialsException.class, () -> loginUseCase.login(request));

        verify(roleRepository).findByRole(RoleEnum.USER);
        verify(userRepository).findByUsername("username");
        verify(passwordEncoder).matches("password", encodedPassword);
    }
}
