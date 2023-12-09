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
public class PropertyDetails {
    private Long id;
    private String name;
    private String description;
    private Integer area;
    private Integer rooms;
    private Double price;
    private String city;
    private Boolean parkingIncluded;
    private Boolean furnished;
    private Integer maxOccupancy;
    private Boolean outsideArea;
    private Integer propertyTypeId;
    private String address;
    private Integer constructionYear;
    private UserEntity user;
    private String photoLink;
}
