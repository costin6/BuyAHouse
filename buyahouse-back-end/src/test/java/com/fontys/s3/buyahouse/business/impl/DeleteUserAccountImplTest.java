package com.fontys.s3.buyahouse.business.impl;

import com.fontys.s3.buyahouse.business.exceptions.InvalidUserException;
import com.fontys.s3.buyahouse.persistance.PropertyRepository;
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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeleteUserAccountImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private PropertyRepository propertyRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private DeleteUserAccountUseCaseImpl deleteUserAccountUseCase;
    @Test
    void deleteUserAccountShouldDelete(){

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

        when(userRepository.findByUserId(1L)).thenReturn(user);

        deleteUserAccountUseCase.deleteUserAccount(1L);

        verify(roleRepository).findByRole(RoleEnum.USER);
        verify(userRepository).findByUserId(1L);
    }

    @Test
    void deleteUserAccountShouldThrowInvalidUserException(){
        when(userRepository.findByUserId(1L)).thenReturn(null);

        assertThrows(InvalidUserException.class, () -> deleteUserAccountUseCase.deleteUserAccount(1L));

        verify(userRepository).findByUserId(1L);
    }
}