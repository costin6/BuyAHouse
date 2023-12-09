package com.fontys.s3.buyahouse.business;

import com.fontys.s3.buyahouse.domain.LoginRequest;
import com.fontys.s3.buyahouse.domain.LoginResponse;

public interface LoginUseCase {
    LoginResponse login(LoginRequest loginRequest);
}
