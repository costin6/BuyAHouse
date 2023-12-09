package com.fontys.s3.buyahouse.business.impl;

import com.fontys.s3.buyahouse.business.SignUpUseCase;
import com.fontys.s3.buyahouse.domain.SignUpRequest;
import com.fontys.s3.buyahouse.domain.SignUpResponse;
import com.fontys.s3.buyahouse.persistance.RoleRepository;
import com.fontys.s3.buyahouse.persistance.UserRepository;
import com.fontys.s3.buyahouse.persistance.entity.RoleEnum;
import com.fontys.s3.buyahouse.persistance.entity.UserEntity;
import com.fontys.s3.buyahouse.persistance.entity.UserRoleEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SignUpUseCaseImpl implements SignUpUseCase {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public SignUpResponse registerUser(SignUpRequest signUpRequest){
        UserEntity savedUser = saveNewUser(signUpRequest);
        return SignUpResponse.builder()
                .id(savedUser.getUserId())
                .build();
    }

    private UserEntity saveNewUser(SignUpRequest signUpRequest){
        UserRoleEntity role = roleRepository.findByRole(RoleEnum.USER);
        signUpRequest.setUserRole(role);
        String encodedPassword = passwordEncoder.encode(signUpRequest.getPassword());
        UserEntity newUser = UserEntity.builder()
                .userId(signUpRequest.getId())
                .username(signUpRequest.getUsername())
                .password(encodedPassword)
                .phoneNumber(signUpRequest.getPhoneNumber())
                .firstName(signUpRequest.getFirstName())
                .lastName(signUpRequest.getLastName())
                .userRole(signUpRequest.getUserRole())
                .build();
        return userRepository.save(newUser);
    }
}
