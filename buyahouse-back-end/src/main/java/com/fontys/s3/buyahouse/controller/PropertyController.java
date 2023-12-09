package com.fontys.s3.buyahouse.controller;

import com.fontys.s3.buyahouse.business.PropertyService;
import com.fontys.s3.buyahouse.configuration.security.isauthenticated.IsAuthenticated;
import com.fontys.s3.buyahouse.domain.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;


@RestController
@RequestMapping("/properties")
@AllArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000"})
public class PropertyController {
    private final PropertyService propertyService;

    @IsAuthenticated
    @RolesAllowed({"ROLE_USER"})
    @PostMapping()
    public ResponseEntity<CreatePropertyResponse> createProperty(@RequestBody @Valid PropertyServiceRequest request){
        CreatePropertyResponse response = propertyService.createProperty(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<GetPropertyOverviewResponse> getPropertyOverview(){
        GetPropertyOverviewResponse response = propertyService.getPropertyOverview();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{propertyId}")
    public ResponseEntity<GetPropertyDetailsResponse> getPropertyDetails(@PathVariable("propertyId")Long propertyId){
        GetPropertyDetailsResponse response = propertyService.getPropertyDetails(propertyId);
        return ResponseEntity.ok(response);
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_USER","ROLE_ADMIN"})
    @PutMapping("{propertyId}")
    public ResponseEntity<Void> updateProperty(@PathVariable("propertyId") Long id,
                                               @RequestBody @Valid UpdatePropertyRequest request){
        request.setPropertyId(id);
        propertyService.updateProperty(request);
        return ResponseEntity.noContent().build();
    }

    @IsAuthenticated
    @RolesAllowed({"ROLE_USER","ROLE_ADMIN"})
    @DeleteMapping("{propertyId}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long propertyId){
        propertyService.deleteProperty(propertyId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/{searchQuery}")
    public ResponseEntity<GetPropertyOverviewResponse> getSearchedPropertiesOverview(@PathVariable("searchQuery")String searchQuery){
        GetPropertyOverviewResponse response = propertyService.searchProperties(searchQuery);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/statistics")
    public ResponseEntity<GetPropertiesByUserIdResponse> getPropertiesByUserId(){
        GetPropertiesByUserIdResponse response = propertyService.getPropertiesByUserId();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/myproperties/{userId}")
    public ResponseEntity<GetPropertyOverviewResponse> getPropertyOverviewByUser(@PathVariable("userId")Long userId){
        GetPropertyOverviewResponse response = propertyService.getPropertyOverviewByUser(userId);
        return ResponseEntity.ok(response);
    }
}
