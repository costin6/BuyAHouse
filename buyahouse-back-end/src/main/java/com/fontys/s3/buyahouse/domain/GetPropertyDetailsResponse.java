package com.fontys.s3.buyahouse.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetPropertyDetailsResponse {
    private PropertyDetails propertyDetails;
}
