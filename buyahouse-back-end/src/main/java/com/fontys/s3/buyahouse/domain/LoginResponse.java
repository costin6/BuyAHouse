package com.fontys.s3.buyahouse.domain;

import com.fontys.s3.buyahouse.persistance.entity.UserRoleEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private Long userId;
    private String username;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private UserRoleEntity userRole;
    private String accessToken;
}
