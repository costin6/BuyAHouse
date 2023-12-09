package com.fontys.s3.buyahouse.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePropertyRequest {
    private Long propertyId;
    @NotBlank
    private String name;
    private String description;
    @NotNull
    private Integer area;
    @NotNull
    private Integer rooms;
    @NotNull
    private Double price;
    @NotNull
    private String city;
    private Boolean parkingIncluded;
    private Boolean furnished;
    private Integer maxOccupancy;
    private Boolean outsideArea;
    @NotNull
    private Integer propertyTypeId;
    @NotBlank
    private String address;
    private Integer constructionYear;
    private String photoLink;
}
