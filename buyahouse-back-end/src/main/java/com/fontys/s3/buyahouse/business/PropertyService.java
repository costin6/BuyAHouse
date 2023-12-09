package com.fontys.s3.buyahouse.business;

import com.fontys.s3.buyahouse.domain.*;

public interface PropertyService {
    CreatePropertyResponse createProperty(PropertyServiceRequest request);
    GetPropertyDetailsResponse getPropertyDetails(Long propertyId);
    GetPropertyOverviewResponse getPropertyOverview();
    void updateProperty (UpdatePropertyRequest request);
    void deleteProperty (Long propertyId);
    Long getUserIdByPropertyId(Long propertyId);
    GetPropertyOverviewResponse searchProperties(String search);
    GetPropertiesByUserIdResponse getPropertiesByUserId();
    GetPropertyOverviewResponse getPropertyOverviewByUser(Long userId);
}
