package com.fontys.s3.buyahouse.business.impl;

import com.fontys.s3.buyahouse.domain.GetUserDetailsResponse;
import com.fontys.s3.buyahouse.domain.LoggedUserDetails;
import com.fontys.s3.buyahouse.persistance.RoleRepository;
import com.fontys.s3.buyahouse.persistance.UserRepository;
import com.fontys.s3.buyahouse.persistance.entity.RoleEnum;
import com.fontys.s3.buyahouse.persistance.entity.UserRoleEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetUserDetailsImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @InjectMocks
    private GetUserDetailsUseCaseImpl getUserDetailsUseCase;

    @Test
    void getUserDetailsShouldReturnUserDetails(){
        UserRoleEntity role = roleRepository.findByRole(RoleEnum.USER);

        LoggedUserDetails userDetails = LoggedUserDetails.builder()
                .userId(1L)
                .username("username")
                .phoneNumber("123456789")
                .firstName("John")
                .lastName("Doe")
                .userRole(role)
                .build();

        when(userRepository.getDetails(1L))
                .thenReturn(userDetails);

        GetUserDetailsResponse actualResult = getUserDetailsUseCase.getLoggedUserDetails(1L);

        LoggedUserDetails user = LoggedUserDetails.builder()
                .userId(1L)
                .username("username")
                .phoneNumber("123456789")
                .firstName("John")
                .lastName("Doe")
                .userRole(role)
                .build();

        GetUserDetailsResponse expectedResult = GetUserDetailsResponse.builder()
                .userDetails(user)
                .build();

        assertEquals(expectedResult, actualResult);

        verify(userRepository).getDetails(1L);
    }
}
