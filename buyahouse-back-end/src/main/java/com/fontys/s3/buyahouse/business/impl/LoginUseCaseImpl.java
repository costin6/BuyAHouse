package com.fontys.s3.buyahouse.business.impl;

import com.fontys.s3.buyahouse.business.AccessTokenEncoder;
import com.fontys.s3.buyahouse.business.LoginUseCase;
import com.fontys.s3.buyahouse.business.exceptions.InvalidCredentialsException;
import com.fontys.s3.buyahouse.domain.AccessToken;
import com.fontys.s3.buyahouse.domain.LoginRequest;
import com.fontys.s3.buyahouse.domain.LoginResponse;
import com.fontys.s3.buyahouse.persistance.UserRepository;
import com.fontys.s3.buyahouse.persistance.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;


@Service
@RequiredArgsConstructor
public class LoginUseCaseImpl implements LoginUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccessTokenEncoder accessTokenEncoder;
    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        UserEntity user = userRepository.findByUsername(loginRequest.getUsername());
        if(user == null){
            throw new InvalidCredentialsException();
        }

        if(!matchesPassword(loginRequest.getPassword(), user.getPassword())){
            throw new InvalidCredentialsException();
        }

        String accessToken = generateAccessToken(user);
        return LoginResponse.builder().userId(user.getUserId()).username(user.getUsername()).phoneNumber(user.getPhoneNumber()).firstName(user.getFirstName()).lastName(user.getLastName()).userRole(user.getUserRole()).accessToken(accessToken).build();
    }

    private boolean matchesPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    private String generateAccessToken(UserEntity user) {
        return accessTokenEncoder.encode(
                AccessToken.builder()
                        .subject(user.getUsername())
                        .roles(List.of(user.getUserRole().getRole().toString()))
                        .userId(user.getUserId())
                        .build());
    }
}
