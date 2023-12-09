package com.fontys.s3.buyahouse.business;

import com.fontys.s3.buyahouse.domain.AccessToken;

public interface AccessTokenDecoder {
    AccessToken decode(String accessTokenEncoded);
}
