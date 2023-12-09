package com.fontys.s3.buyahouse.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropertyByUserId {
    private Long userId;
    private String username;
    private Long countProperties;
}
