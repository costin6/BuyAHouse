package com.fontys.s3.buyahouse.business;

import com.fontys.s3.buyahouse.domain.AccessToken;

public interface AccessTokenEncoder {
    String encode(AccessToken accessToken);
}
