package com.fontys.s3.buyahouse.business.impl;

import com.fontys.s3.buyahouse.business.exceptions.InvalidUserException;
import com.fontys.s3.buyahouse.domain.UpdateUserDetailsRequest;
import com.fontys.s3.buyahouse.persistance.UserRepository;
import com.fontys.s3.buyahouse.persistance.entity.RoleEnum;
import com.fontys.s3.buyahouse.persistance.entity.UserEntity;
import com.fontys.s3.buyahouse.persistance.entity.UserRoleEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateUserDetailsUseCaseImplTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UpdateUserDetailsUseCaseImpl updateUserDetailsUseCase;

    @Test
    void updateShouldUpdateUserDetails(){
        UserRoleEntity role = UserRoleEntity.builder()
                        .id(1L)
                        .role(RoleEnum.USER)
                        .build();

        UserEntity user = UserEntity.builder()
                .userId(1L)
                .username("username")
                .password("password")
                .phoneNumber("123456789")
                .firstName("John")
                .lastName("Doe")
                .userRole(role)
                .build();

        when(userRepository.findByUserId(1L))
                .thenReturn(user);

        when(userRepository.existsById(1L))
                .thenReturn(true);

        UpdateUserDetailsRequest request = UpdateUserDetailsRequest.builder()
                .userId(1L)
                .username("username")
                .phoneNumber("123456789")
                .firstName("John")
                .lastName("Doe")
                .build();

        updateUserDetailsUseCase.updateUserDetails(request);

        verify(userRepository).findByUserId(1L);
        verify(userRepository).existsById(1L);
    }

    @Test
    void updateShouldThrowInvalidUserExceptionIfUserIsNotFound(){
        when(userRepository.findByUserId(1L))
                .thenReturn(null);

        UpdateUserDetailsRequest request = UpdateUserDetailsRequest.builder()
                .userId(1L)
                .username("username")
                .phoneNumber("123456789")
                .firstName("John")
                .lastName("Doe")
                .build();

        assertThrows(InvalidUserException.class, () -> updateUserDetailsUseCase.updateUserDetails(request));

        verify(userRepository).findByUserId(1L);
    }

    @Test
    void updateShouldThrowInvalidUserExceptionIfUserDoesntExist(){
        UserRoleEntity role = UserRoleEntity.builder()
                .id(1L)
                .role(RoleEnum.USER)
                .build();

        UserEntity user = UserEntity.builder()
                .userId(1L)
                .username("username")
                .password("password")
                .phoneNumber("123456789")
                .firstName("John")
                .lastName("Doe")
                .userRole(role)
                .build();

        when(userRepository.findByUserId(1L))
                .thenReturn(user);

        when(userRepository.existsById(1L))
                .thenReturn(false);

        UpdateUserDetailsRequest request = UpdateUserDetailsRequest.builder()
                .userId(1L)
                .username("username")
                .phoneNumber("123456789")
                .firstName("John")
                .lastName("Doe")
                .build();

        assertThrows(InvalidUserException.class, () -> updateUserDetailsUseCase.updateUserDetails(request));

        verify(userRepository).findByUserId(1L);
        verify(userRepository).existsById(1L);
    }
}
