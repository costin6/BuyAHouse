package com.fontys.s3.buyahouse.business;

import com.fontys.s3.buyahouse.domain.GetUserDetailsResponse;

public interface GetUserDetailsUseCase {
    GetUserDetailsResponse getLoggedUserDetails(Long userId);
}
