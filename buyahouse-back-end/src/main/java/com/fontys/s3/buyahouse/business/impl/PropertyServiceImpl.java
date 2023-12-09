package com.fontys.s3.buyahouse.business.impl;

import com.fontys.s3.buyahouse.business.PropertyService;
import com.fontys.s3.buyahouse.business.exceptions.UnauthorizedDataAccessException;
import com.fontys.s3.buyahouse.domain.*;
import com.fontys.s3.buyahouse.persistance.PropertyRepository;
import com.fontys.s3.buyahouse.persistance.UserRepository;
import com.fontys.s3.buyahouse.persistance.entity.PropertyEntity;
import com.fontys.s3.buyahouse.business.exceptions.InvalidPropertyException;
import com.fontys.s3.buyahouse.persistance.entity.RoleEnum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PropertyServiceImpl implements PropertyService {
    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;
    private final AccessToken accessToken;

    @Override
    public CreatePropertyResponse createProperty(PropertyServiceRequest request) {
        PropertyEntity savedProperty = saveNewProperty(request);
        return CreatePropertyResponse.builder()
                .propertyId(savedProperty.getId())
                .build();
    }

    private PropertyEntity saveNewProperty(PropertyServiceRequest request) {
        PropertyEntity newProperty = PropertyEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .area(request.getArea())
                .rooms(request.getRooms())
                .price(request.getPrice())
                .city(request.getCity())
                .parkingIncluded(request.getParkingIncluded())
                .furnished(request.getFurnished())
                .maxOccupancy(request.getMaxOccupancy())
                .outsideArea(request.getOutsideArea())
                .propertyTypeId(request.getPropertyTypeId())
                .address(request.getAddress())
                .constructionYear(request.getConstructionYear())
                .user(request.getUser())
                .photoLink(request.getPhotoLink())
                .build();
        return propertyRepository.save(newProperty);
    }

    @Override
    public GetPropertyDetailsResponse getPropertyDetails(Long propertyId) {
        PropertyDetails propertyDetails = propertyRepository.getDetails(propertyId);
        return GetPropertyDetailsResponse.builder()
                .propertyDetails(propertyDetails)
                .build();
    }

    @Override
    public GetPropertyOverviewResponse getPropertyOverview() {
        List<PropertyOverview> properties = propertyRepository.getOverview();
        return GetPropertyOverviewResponse.builder()
                .properties(properties)
                .build();
    }

    @Override
    public void updateProperty(UpdatePropertyRequest request) {
        if (accessToken.getRoles().contains("ADMIN") || Objects.equals(propertyRepository.getUserIdByPropertyId(request.getPropertyId()), accessToken.getUserId())) {
            Optional<PropertyEntity> property = propertyRepository.findById(request.getPropertyId());
            if (property.isPresent()) {
                PropertyEntity updatedProperty = property.get();
                updateFields(request, updatedProperty);
            }
            else {
                throw new InvalidPropertyException();
            }
        }
        else {
            throw new UnauthorizedDataAccessException("USER_ID_NOT_FROM_LOGGED_USER");
        }
    }

    private void updateFields(UpdatePropertyRequest request, PropertyEntity property) {
        property.setName(request.getName());
        property.setDescription(request.getDescription());
        property.setArea(request.getArea());
        property.setRooms(request.getRooms());
        property.setPrice(request.getPrice());
        property.setCity(request.getCity());
        property.setParkingIncluded(request.getParkingIncluded());
        property.setFurnished(request.getFurnished());
        property.setMaxOccupancy(request.getMaxOccupancy());
        property.setOutsideArea(request.getOutsideArea());
        property.setPropertyTypeId(request.getPropertyTypeId());
        property.setAddress(request.getAddress());
        property.setConstructionYear(request.getConstructionYear());
        property.setPhotoLink(request.getPhotoLink());
        propertyRepository.save(property);
    }

    @Override
    public void deleteProperty(Long propertyId) {
        PropertyDetails property = propertyRepository.getDetails(propertyId);
        LoggedUserDetails user = userRepository.getDetails(property.getUser().getUserId());
        if (user.getUserRole().getRole() == RoleEnum.ADMIN || Objects.equals(user.getUserId(), property.getUser().getUserId())) {
            propertyRepository.deleteById(propertyId);
        }
        else{
            throw new UnauthorizedDataAccessException("USER_ID_NOT_FROM_LOGGED_USER");
        }
    }

    @Override
    public Long getUserIdByPropertyId(Long propertyId) {
        return propertyRepository.getUserIdByPropertyId(propertyId);
    }

    @Override
    public GetPropertyOverviewResponse searchProperties(String search) {
        List<PropertyOverview> searchResult = propertyRepository.searchProperties(search);
        return GetPropertyOverviewResponse.builder()
                .properties(searchResult)
                .build();
    }

    @Override
    public GetPropertiesByUserIdResponse getPropertiesByUserId() {
        List<PropertyByUserId> properties = propertyRepository.getPropertiesByUserId();
        return GetPropertiesByUserIdResponse.builder()
                .propertyByUserIds(properties)
                .build();
    }

    @Override
    public GetPropertyOverviewResponse getPropertyOverviewByUser(Long userId) {
        List<PropertyOverview> properties = propertyRepository.getPropertyOverviewByUser(userId);
        return GetPropertyOverviewResponse.builder()
                .properties(properties)
                .build();
    }
}
