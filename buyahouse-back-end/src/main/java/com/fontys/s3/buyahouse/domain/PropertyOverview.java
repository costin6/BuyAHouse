package com.fontys.s3.buyahouse.domain;

import com.fontys.s3.buyahouse.persistance.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropertyOverview {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private UserEntity user;
    private String photoLink;
}
