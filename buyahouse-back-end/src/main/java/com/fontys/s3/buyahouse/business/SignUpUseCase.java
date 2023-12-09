package com.fontys.s3.buyahouse.business;

import com.fontys.s3.buyahouse.domain.SignUpRequest;
import com.fontys.s3.buyahouse.domain.SignUpResponse;

public interface SignUpUseCase {
    SignUpResponse registerUser(SignUpRequest signUpRequest);
}
