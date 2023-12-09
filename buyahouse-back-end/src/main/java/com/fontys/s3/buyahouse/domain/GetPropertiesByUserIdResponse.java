package com.fontys.s3.buyahouse.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetPropertiesByUserIdResponse {
    private List<PropertyByUserId> propertyByUserIds;
}
