package com.fontys.s3.buyahouse.business.impl;

import com.fontys.s3.buyahouse.business.exceptions.InvalidPropertyException;
import com.fontys.s3.buyahouse.business.exceptions.UnauthorizedDataAccessException;
import com.fontys.s3.buyahouse.domain.*;
import com.fontys.s3.buyahouse.persistance.PropertyRepository;
import com.fontys.s3.buyahouse.persistance.RoleRepository;
import com.fontys.s3.buyahouse.persistance.UserRepository;
import com.fontys.s3.buyahouse.persistance.entity.PropertyEntity;
import com.fontys.s3.buyahouse.persistance.entity.RoleEnum;
import com.fontys.s3.buyahouse.persistance.entity.UserEntity;
import com.fontys.s3.buyahouse.persistance.entity.UserRoleEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PropertyServiceImplTest {
    @Mock
    private PropertyRepository propertyRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private AccessToken accessToken;
    @InjectMocks
    private PropertyServiceImpl propertyService;

    @Test
    void createPropertyShouldCreateANewProperty() {

        UserRoleEntity userRole = UserRoleEntity.builder().id(1L).role(RoleEnum.USER).build();

        when(roleRepository.findByRole(RoleEnum.USER)).thenReturn(userRole);

        UserRoleEntity role = roleRepository.findByRole(RoleEnum.USER);

        UserEntity savedUser = UserEntity.builder()
                .userId(1L)
                .username("username")
                .password("password")
                .phoneNumber("1234567890")
                .firstName("John")
                .lastName("Doe")
                .userRole(role)
                .build();

        PropertyEntity property = PropertyEntity.builder()
                .name("name")
                .description("description")
                .area(200)
                .rooms(2)
                .price(20000.00)
                .city("city")
                .parkingIncluded(true)
                .furnished(true)
                .maxOccupancy(3)
                .outsideArea(true)
                .propertyTypeId(1)
                .address("address")
                .constructionYear(2012)
                .user(savedUser)
                .photoLink("link")
                .build();

        when(propertyRepository.save(property)).thenReturn(PropertyEntity.builder().id(1L).area(200).rooms(2).price(20000.00).maxOccupancy(3).propertyTypeId(1).constructionYear(2012).user(savedUser).build());

        PropertyServiceRequest request = PropertyServiceRequest.builder()
                .propertyId(1L)
                .name("name")
                .description("description")
                .area(200)
                .rooms(2)
                .price(20000.00)
                .city("city")
                .parkingIncluded(true)
                .furnished(true)
                .maxOccupancy(3)
                .outsideArea(true)
                .propertyTypeId(1)
                .address("address")
                .constructionYear(2012)
                .user(savedUser)
                .photoLink("link")
                .build();

        CreatePropertyResponse actual = propertyService.createProperty(request);
        CreatePropertyResponse expected = CreatePropertyResponse.builder().propertyId(1L).build();

        assertEquals(expected, actual);

        verify(roleRepository).findByRole(RoleEnum.USER);
        verify(propertyRepository).save(property);
    }

    @Test
    void getPropertiesOverviewShouldReturnAllPropertiesOverview() {

        PropertyOverview propertyOverview = PropertyOverview.builder()
                .id(1L)
                .name("name")
                .description("description")
                .price(20000.00)
                .photoLink("link")
                .build();

        when(propertyRepository.getOverview())
                .thenReturn(List.of(propertyOverview));

        GetPropertyOverviewResponse actualResult = propertyService.getPropertyOverview();

        PropertyOverview property = PropertyOverview.builder()
                .id(1L)
                .name("name")
                .description("description")
                .price(20000.00)
                .photoLink("link")
                .build();

        GetPropertyOverviewResponse expectedResult = GetPropertyOverviewResponse.builder()
                .properties(List.of(property))
                .build();

        assertEquals(expectedResult, actualResult);

        verify(propertyRepository).getOverview();
    }

    @Test
    void getPropertyDetailsShouldReturnPropertyDetails() {

        PropertyDetails propertyDetails = PropertyDetails.builder()
                .id(1L)
                .name("name")
                .description("description")
                .area(200)
                .rooms(2)
                .price(20000.00)
                .city("city")
                .parkingIncluded(true)
                .furnished(true)
                .maxOccupancy(3)
                .outsideArea(true)
                .propertyTypeId(1)
                .address("address")
                .constructionYear(2012)
                .photoLink("link")
                .build();

        when(propertyRepository.getDetails(1L))
                .thenReturn(propertyDetails);

        GetPropertyDetailsResponse actualResult = propertyService.getPropertyDetails(1L);

        PropertyDetails property = PropertyDetails.builder()
                .id(1L)
                .name("name")
                .description("description")
                .area(200)
                .rooms(2)
                .price(20000.00)
                .city("city")
                .parkingIncluded(true)
                .furnished(true)
                .maxOccupancy(3)
                .outsideArea(true)
                .propertyTypeId(1)
                .address("address")
                .constructionYear(2012)
                .photoLink("link")
                .build();

        GetPropertyDetailsResponse expectedResult = GetPropertyDetailsResponse.builder()
                .propertyDetails(property)
                .build();

        assertEquals(expectedResult, actualResult);

        verify(propertyRepository).getDetails(1L);
    }

    @Test
    void updatePropertyShouldUpdateThePropertyIfTheUserIsLoggedIn() {

        UserRoleEntity userRole = UserRoleEntity.builder().id(1L).role(RoleEnum.USER).build();

        when(roleRepository.findByRole(RoleEnum.USER)).thenReturn(userRole);

        UserRoleEntity role = roleRepository.findByRole(RoleEnum.USER);

        when(accessToken.getRoles()).thenReturn(List.of(role.toString()));

        UserEntity savedUser = UserEntity.builder()
                .userId(1L)
                .username("username")
                .password("password")
                .phoneNumber("1234567890")
                .firstName("John")
                .lastName("Doe")
                .userRole(role)
                .build();

        PropertyEntity property = PropertyEntity.builder()
                .id(1L)
                .name("name")
                .description("description")
                .area(200)
                .rooms(2)
                .price(20000.00)
                .city("city")
                .parkingIncluded(true)
                .furnished(true)
                .maxOccupancy(3)
                .outsideArea(true)
                .propertyTypeId(1)
                .address("address")
                .constructionYear(2012)
                .photoLink("link")
                .user(savedUser)
                .build();

        when(propertyRepository.findById(1L))
                .thenReturn(Optional.ofNullable(property));

        UpdatePropertyRequest request = UpdatePropertyRequest.builder()
                .propertyId(1L)
                .name("name")
                .description("description")
                .area(200)
                .rooms(2)
                .price(20000.00)
                .city("city")
                .parkingIncluded(true)
                .furnished(true)
                .maxOccupancy(3)
                .outsideArea(true)
                .propertyTypeId(1)
                .address("address")
                .constructionYear(2012)
                .photoLink("link")
                .build();

        when(propertyRepository.getUserIdByPropertyId(1L))
                .thenReturn(1L);

        when(accessToken.getUserId()).thenReturn(1L);

        propertyService.updateProperty(request);

        verify(roleRepository).findByRole(RoleEnum.USER);
        verify(propertyRepository).findById(1L);
        verify(propertyRepository).getUserIdByPropertyId(1L);
    }

    @Test
    void updatePropertyShouldUpdateThePropertyIfTheUserIsAdminAndUserIdMatches() {

        UserRoleEntity userRole = UserRoleEntity.builder().id(1L).role(RoleEnum.ADMIN).build();

        when(roleRepository.findByRole(RoleEnum.ADMIN)).thenReturn(userRole);

        UserRoleEntity role = roleRepository.findByRole(RoleEnum.ADMIN);

        when(accessToken.getRoles()).thenReturn(List.of(role.toString()));

        UserEntity savedUser = UserEntity.builder()
                .userId(1L)
                .username("username")
                .password("password")
                .phoneNumber("1234567890")
                .firstName("John")
                .lastName("Doe")
                .userRole(role)
                .build();

        PropertyEntity property = PropertyEntity.builder()
                .id(1L)
                .name("name")
                .description("description")
                .area(200)
                .rooms(2)
                .price(20000.00)
                .city("city")
                .parkingIncluded(true)
                .furnished(true)
                .maxOccupancy(3)
                .outsideArea(true)
                .propertyTypeId(1)
                .address("address")
                .constructionYear(2012)
                .photoLink("link")
                .user(savedUser)
                .build();

        when(propertyRepository.findById(1L))
                .thenReturn(Optional.ofNullable(property));

        UpdatePropertyRequest request = UpdatePropertyRequest.builder()
                .propertyId(1L)
                .name("name")
                .description("description")
                .area(200)
                .rooms(2)
                .price(20000.00)
                .city("city")
                .parkingIncluded(true)
                .furnished(true)
                .maxOccupancy(3)
                .outsideArea(true)
                .propertyTypeId(1)
                .address("address")
                .constructionYear(2012)
                .photoLink("link")
                .build();

        when(propertyRepository.getUserIdByPropertyId(1L))
                .thenReturn(1L);

        when(accessToken.getUserId()).thenReturn(1L);

        propertyService.updateProperty(request);

        verify(roleRepository).findByRole(RoleEnum.ADMIN);
        verify(propertyRepository).findById(1L);
        verify(propertyRepository).getUserIdByPropertyId(1L);
    }

    @Test
    void updatePropertyShouldThrowInvalidPropertyExceptionIfTheUserIsAdmin() {

        UserRoleEntity userRole = UserRoleEntity.builder().id(1L).role(RoleEnum.ADMIN).build();

        when(roleRepository.findByRole(RoleEnum.ADMIN)).thenReturn(userRole);

        UserRoleEntity role = roleRepository.findByRole(RoleEnum.ADMIN);

        when(accessToken.getRoles()).thenReturn(List.of(role.toString()));

        when(propertyRepository.findById(1L))
                .thenReturn(Optional.empty());

        UpdatePropertyRequest request = UpdatePropertyRequest.builder()
                .propertyId(1L)
                .name("name")
                .description("description")
                .area(200)
                .rooms(2)
                .price(20000.00)
                .city("city")
                .parkingIncluded(true)
                .furnished(true)
                .maxOccupancy(3)
                .outsideArea(true)
                .propertyTypeId(1)
                .address("address")
                .constructionYear(2012)
                .photoLink("link")
                .build();

        when(propertyRepository.getUserIdByPropertyId(1L))
                .thenReturn(1L);

        when(accessToken.getUserId()).thenReturn(1L);

        assertThrows(InvalidPropertyException.class, () -> propertyService.updateProperty(request));

        verify(roleRepository).findByRole(RoleEnum.ADMIN);
        verify(propertyRepository).findById(1L);
        verify(propertyRepository).getUserIdByPropertyId(1L);
    }

    @Test
    void updatePropertyShouldUpdateThrowUnauthorizedUserException() {

        UserRoleEntity userRole = UserRoleEntity.builder().id(1L).role(RoleEnum.USER).build();

        when(roleRepository.findByRole(RoleEnum.USER)).thenReturn(userRole);

        UserRoleEntity role = roleRepository.findByRole(RoleEnum.USER);

        when(accessToken.getRoles()).thenReturn(List.of(role.toString()));

        UpdatePropertyRequest request = UpdatePropertyRequest.builder()
                .propertyId(1L)
                .name("name")
                .description("description")
                .area(200)
                .rooms(2)
                .price(20000.00)
                .city("city")
                .parkingIncluded(true)
                .furnished(true)
                .maxOccupancy(3)
                .outsideArea(true)
                .propertyTypeId(1)
                .address("address")
                .constructionYear(2012)
                .photoLink("link")
                .build();

        when(propertyRepository.getUserIdByPropertyId(1L))
                .thenReturn(1L);

        when(accessToken.getUserId()).thenReturn(2L);

        assertThrows(UnauthorizedDataAccessException.class, () -> propertyService.updateProperty(request));

        verify(roleRepository).findByRole(RoleEnum.USER);
        verify(propertyRepository).getUserIdByPropertyId(1L);
    }

    @Test
    void updatePropertyShouldUpdateThrowInvalidPropertyException() {

        UserRoleEntity userRole = UserRoleEntity.builder().id(1L).role(RoleEnum.USER).build();

        when(roleRepository.findByRole(RoleEnum.USER)).thenReturn(userRole);

        UserRoleEntity role = roleRepository.findByRole(RoleEnum.USER);

        when(accessToken.getRoles()).thenReturn(List.of(role.toString()));

        when(propertyRepository.findById(1L))
                .thenReturn(Optional.empty());

        UpdatePropertyRequest request = UpdatePropertyRequest.builder()
                .propertyId(1L)
                .name("name")
                .description("description")
                .area(200)
                .rooms(2)
                .price(20000.00)
                .city("city")
                .parkingIncluded(true)
                .furnished(true)
                .maxOccupancy(3)
                .outsideArea(true)
                .propertyTypeId(1)
                .address("address")
                .constructionYear(2012)
                .photoLink("link")
                .build();

        when(propertyRepository.getUserIdByPropertyId(1L))
                .thenReturn(1L);

        when(accessToken.getUserId()).thenReturn(1L);

        assertThrows(InvalidPropertyException.class, () -> propertyService.updateProperty(request));

        verify(roleRepository).findByRole(RoleEnum.USER);
        verify(propertyRepository).findById(1L);
        verify(propertyRepository).getUserIdByPropertyId(1L);
    }

    @Test
    void deletePropertyShouldDeleteTheProperty() {
        UserRoleEntity userRole = UserRoleEntity.builder().id(1L).role(RoleEnum.USER).build();

        when(roleRepository.findByRole(RoleEnum.USER)).thenReturn(userRole);

        UserRoleEntity role = roleRepository.findByRole(RoleEnum.USER);

        UserEntity savedUser = UserEntity.builder()
                .userId(1L)
                .username("username")
                .password("password")
                .phoneNumber("1234567890")
                .firstName("John")
                .lastName("Doe")
                .userRole(role)
                .build();

        PropertyDetails property = PropertyDetails.builder()
                .id(1L)
                .name("name")
                .description("description")
                .area(200)
                .rooms(2)
                .price(20000.00)
                .city("city")
                .parkingIncluded(true)
                .furnished(true)
                .maxOccupancy(3)
                .outsideArea(true)
                .propertyTypeId(1)
                .address("address")
                .constructionYear(2012)
                .photoLink("link")
                .user(savedUser)
                .build();

        when(propertyRepository.getDetails(1L))
                .thenReturn(property);

        LoggedUserDetails userDetails = LoggedUserDetails.builder()
                .userId(1L)
                .username("username")
                .phoneNumber("1234567890")
                .firstName("John")
                .lastName("Doe")
                .userRole(role)
                .build();

        when(userRepository.getDetails(1L))
                .thenReturn(userDetails);

        propertyService.deleteProperty(1L);

        verify(roleRepository).findByRole(RoleEnum.USER);
        verify(propertyRepository).getDetails(1L);
        verify(propertyRepository).deleteById(1L);
    }

    @Test
    void deletePropertyShouldThrowUnauthorizedAccessException() {
        UserRoleEntity userRole = UserRoleEntity.builder().id(1L).role(RoleEnum.USER).build();

        when(roleRepository.findByRole(RoleEnum.USER)).thenReturn(userRole);

        UserRoleEntity role = roleRepository.findByRole(RoleEnum.USER);

        UserEntity savedUser = UserEntity.builder()
                .userId(1L)
                .username("username")
                .password("password")
                .phoneNumber("1234567890")
                .firstName("John")
                .lastName("Doe")
                .userRole(role)
                .build();

        PropertyDetails property = PropertyDetails.builder()
                .id(1L)
                .name("name")
                .description("description")
                .area(200)
                .rooms(2)
                .price(20000.00)
                .city("city")
                .parkingIncluded(true)
                .furnished(true)
                .maxOccupancy(3)
                .outsideArea(true)
                .propertyTypeId(1)
                .address("address")
                .constructionYear(2012)
                .photoLink("link")
                .user(savedUser)
                .build();

        when(propertyRepository.getDetails(1L))
                .thenReturn(property);

        LoggedUserDetails userDetails = LoggedUserDetails.builder()
                .userId(2L)
                .username("username")
                .phoneNumber("1234567890")
                .firstName("John")
                .lastName("Doe")
                .userRole(role)
                .build();

        when(userRepository.getDetails(1L))
                .thenReturn(userDetails);

        assertThrows(UnauthorizedDataAccessException.class, () -> propertyService.deleteProperty(1L));

        verify(roleRepository).findByRole(RoleEnum.USER);
        verify(propertyRepository).getDetails(1L);
        verify(userRepository).getDetails(1L);
    }

    @Test
    void getUserIdByPropertyIdShouldReturnUserId() {

        UserRoleEntity userRole = UserRoleEntity.builder().id(1L).role(RoleEnum.USER).build();

        when(roleRepository.findByRole(RoleEnum.USER)).thenReturn(userRole);

        UserRoleEntity role = roleRepository.findByRole(RoleEnum.USER);

        UserEntity savedUser = UserEntity.builder()
                .userId(1L)
                .username("username")
                .password("password")
                .phoneNumber("1234567890")
                .firstName("John")
                .lastName("Doe")
                .userRole(role)
                .build();

        PropertyEntity propertyDetails = PropertyEntity.builder()
                .id(1L)
                .name("name")
                .description("description")
                .area(200)
                .rooms(2)
                .price(20000.00)
                .city("city")
                .parkingIncluded(true)
                .furnished(true)
                .maxOccupancy(3)
                .outsideArea(true)
                .propertyTypeId(1)
                .address("address")
                .constructionYear(2012)
                .photoLink("link")
                .user(savedUser)
                .build();

        when(propertyRepository.getUserIdByPropertyId(1L))
                .thenReturn(savedUser.getUserId());

        Long actual = propertyService.getUserIdByPropertyId(1L);

        Long expected = propertyDetails.getId();
        assertEquals(expected, actual);

        verify(roleRepository).findByRole(RoleEnum.USER);
        verify(propertyRepository).getUserIdByPropertyId(1L);
    }

    @Test
    void searchPropertiesShouldReturnProperties() {
        UserRoleEntity userRole = UserRoleEntity.builder().id(1L).role(RoleEnum.USER).build();

        when(roleRepository.findByRole(RoleEnum.USER)).thenReturn(userRole);

        UserRoleEntity role = roleRepository.findByRole(RoleEnum.USER);

        UserEntity savedUser = UserEntity.builder()
                .userId(1L)
                .username("username")
                .password("password")
                .phoneNumber("1234567890")
                .firstName("John")
                .lastName("Doe")
                .userRole(role)
                .build();

        PropertyOverview property = PropertyOverview.builder()
                .id(1L)
                .name("name")
                .description("description")
                .price(20000.00)
                .photoLink("link")
                .user(savedUser)
                .build();

        when(propertyRepository.searchProperties("name"))
                .thenReturn(List.of(property));

        GetPropertyOverviewResponse actual = propertyService.searchProperties("name");

        GetPropertyOverviewResponse expected = GetPropertyOverviewResponse.builder()
                .properties(List.of(property))
                .build();

        assertEquals(expected, actual);

        verify(roleRepository).findByRole(RoleEnum.USER);
        verify(propertyRepository).searchProperties("name");
    }

    @Test
    void getPropertiesByUserIdShouldReturnAListOfProperties() {
        PropertyByUserId property = PropertyByUserId.builder()
                .userId(1L)
                .username("username")
                .countProperties(2L)
                .build();

        when(propertyRepository.getPropertiesByUserId())
                .thenReturn(List.of(property));

        GetPropertiesByUserIdResponse actual = propertyService.getPropertiesByUserId();

        GetPropertiesByUserIdResponse expected = GetPropertiesByUserIdResponse.builder()
                .propertyByUserIds(List.of(property))
                .build();

        assertEquals(expected, actual);

        verify(propertyRepository).getPropertiesByUserId();
    }

    @Test
    void getPropertyOverviewByUserShouldReturnAListOfProperties() {
        UserRoleEntity userRole = UserRoleEntity.builder().id(1L).role(RoleEnum.USER).build();

        when(roleRepository.findByRole(RoleEnum.USER)).thenReturn(userRole);

        UserRoleEntity role = roleRepository.findByRole(RoleEnum.USER);

        UserEntity savedUser = UserEntity.builder()
                .userId(1L)
                .username("username")
                .password("password")
                .phoneNumber("1234567890")
                .firstName("John")
                .lastName("Doe")
                .userRole(role)
                .build();

        PropertyOverview property = PropertyOverview.builder()
                .id(1L)
                .name("name")
                .description("description")
                .price(20000.00)
                .user(savedUser)
                .photoLink("link")
                .build();

        when(propertyRepository.getPropertyOverviewByUser(1L))
                .thenReturn(List.of(property));

        GetPropertyOverviewResponse actual = propertyService.getPropertyOverviewByUser(1L);

        GetPropertyOverviewResponse expected = GetPropertyOverviewResponse.builder()
                .properties(List.of(property))
                .build();

        assertEquals(expected, actual);

        verify(propertyRepository).getPropertyOverviewByUser(1L);
        verify(roleRepository).findByRole(RoleEnum.USER);
    }
}