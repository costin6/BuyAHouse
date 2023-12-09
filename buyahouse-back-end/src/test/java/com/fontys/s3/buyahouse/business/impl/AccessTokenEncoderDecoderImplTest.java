package com.fontys.s3.buyahouse.business.impl;

import com.fontys.s3.buyahouse.business.exceptions.InvalidAccessTokenException;
import com.fontys.s3.buyahouse.business.exceptions.InvalidUserException;
import com.fontys.s3.buyahouse.domain.AccessToken;
import com.fontys.s3.buyahouse.persistance.entity.RoleEnum;
import com.fontys.s3.buyahouse.persistance.entity.UserEntity;
import com.fontys.s3.buyahouse.persistance.entity.UserRoleEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AccessTokenEncoderDecoderImplTest {

    private final AccessTokenEncoderDecoderImpl accessTokenEncoderDecoder = new AccessTokenEncoderDecoderImpl("E91E158E4C6656F68B1B5D1C316766DE98D2AD6EF3BFB44F78E9CFCDF5");

    @Test
    void encode_shouldReturnEncodedAccessToken() {
        UserRoleEntity userRole = UserRoleEntity.builder().id(1L).role(RoleEnum.USER).build();

        UserEntity user = UserEntity.builder()
                .userId(1L)
                .username("username")
                .password("password")
                .phoneNumber("123456789")
                .firstName("John")
                .lastName("Doe")
                .userRole(userRole)
                .build();

        AccessToken accessToken = AccessToken.builder()
                .subject(user.getUsername())
                .roles(List.of(user.getUserRole().getRole().toString()))
                .userId(user.getUserId())
                .build();

        String actualToken = accessTokenEncoderDecoder.encode(accessToken);

        assertNotNull(actualToken);
    }
    @Test
    void encode_shouldThrowInvalidUserError() {
        UserRoleEntity userRole = UserRoleEntity.builder().id(1L).role(RoleEnum.USER).build();

        UserEntity user = UserEntity.builder()
                .userId(null)
                .username("username")
                .password("password")
                .phoneNumber("123456789")
                .firstName("John")
                .lastName("Doe")
                .userRole(userRole)
                .build();

        AccessToken accessToken = AccessToken.builder()
                .subject(user.getUsername())
                .roles(List.of(user.getUserRole().getRole().toString()))
                .userId(user.getUserId())
                .build();

        assertThrows(InvalidUserException.class, () -> accessTokenEncoderDecoder.encode(accessToken));
    }

    @Test
    void decode_shouldReturnValidAccessToken() {

        String accessTokenEncoded = getToken();

        AccessToken actualAccessToken = accessTokenEncoderDecoder.decode(accessTokenEncoded);

        assertEquals("username", actualAccessToken.getSubject());
    }

    @Test
    void decode_shouldThrowInvalidAccessTokenException() {

        String accessTokenEncoded = "SOMETHING INVALID";

        assertThrows(InvalidAccessTokenException.class, () -> accessTokenEncoderDecoder.decode(accessTokenEncoded));

    }

    private String getToken() {
        UserRoleEntity userRole = UserRoleEntity.builder().id(1L).role(RoleEnum.USER).build();

        UserEntity user = UserEntity.builder()
                .userId(1L)
                .username("username")
                .password("password")
                .phoneNumber("123456789")
                .firstName("John")
                .lastName("Doe")
                .userRole(userRole)
                .build();

        AccessToken accessToken = AccessToken.builder()
                .subject(user.getUsername())
                .roles(List.of(user.getUserRole().getRole().toString()))
                .userId(user.getUserId())
                .build();

        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put("role", userRole);
        claimsMap.put("userId", user.getUserId());


        Instant now = Instant.now();
        byte[] keyBytes = Decoders.BASE64.decode("E91E158E4C6656F68B1B5D1C316766DE98D2AD6EF3BFB44F78E9CFCDF5");
        Key key = Keys.hmacShaKeyFor(keyBytes);

        return Jwts.builder()
                .setSubject(accessToken.getSubject())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(1000, ChronoUnit.SECONDS)))
                .addClaims(claimsMap)
                .signWith(key)
                .compact();
    }

}