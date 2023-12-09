package com.fontys.s3.buyahouse.business.impl;

import com.fontys.s3.buyahouse.domain.SignUpRequest;
import com.fontys.s3.buyahouse.domain.SignUpResponse;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SignUpUseCaseImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private RoleRepository roleRepository;
    @InjectMocks
    private SignUpUseCaseImpl signUpUseCase;

    @Test
    void signUpShouldCreateANewAccount(){
        UserRoleEntity userRole = UserRoleEntity.builder().id(1L).role(RoleEnum.USER).build();

        when(roleRepository.findByRole(RoleEnum.USER)).thenReturn(userRole);

        when(passwordEncoder.encode("password")).thenReturn("password123");


        UserEntity user = UserEntity.builder()
                .username("username")
                .password("password123")
                .phoneNumber("123456789")
                .firstName("John")
                .lastName("Doe")
                .userRole(userRole)
                .build();

        SignUpRequest request = SignUpRequest.builder()
                .username("username")
                .password("password")
                .phoneNumber("123456789")
                .firstName("John")
                .lastName("Doe")
                .build();

        when(userRepository.save(user)).thenReturn(UserEntity.builder().userId(1L).build());


        SignUpResponse actual = signUpUseCase.registerUser(request);
        SignUpResponse expected = SignUpResponse.builder().id(1L).build();

        assertEquals(expected, actual);

        verify(roleRepository).findByRole(RoleEnum.USER);
        verify(passwordEncoder).encode("password");
        verify(userRepository).save(user);
    }
}
