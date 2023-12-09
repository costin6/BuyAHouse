package com.fontys.s3.buyahouse.business.impl;

import com.fontys.s3.buyahouse.business.GetUserDetailsUseCase;
import com.fontys.s3.buyahouse.domain.GetUserDetailsResponse;
import com.fontys.s3.buyahouse.domain.LoggedUserDetails;
import com.fontys.s3.buyahouse.persistance.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetUserDetailsUseCaseImpl implements GetUserDetailsUseCase {
    private final UserRepository userRepository;

    @Override
    public GetUserDetailsResponse getLoggedUserDetails(Long userId) {
        LoggedUserDetails userDetails = userRepository.getDetails(userId);
        return GetUserDetailsResponse.builder()
                .userDetails(userDetails)
                .build();
    }
}
