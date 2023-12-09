package com.fontys.s3.buyahouse.persistance;

import com.fontys.s3.buyahouse.domain.PropertyByUserId;
import com.fontys.s3.buyahouse.domain.PropertyDetails;
import com.fontys.s3.buyahouse.domain.PropertyOverview;
import com.fontys.s3.buyahouse.persistance.entity.PropertyEntity;
import com.fontys.s3.buyahouse.persistance.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface PropertyRepository extends JpaRepository<PropertyEntity, Long> {
    boolean existsById(Long propertyId);

    @Query("select new com.fontys.s3.buyahouse.domain.PropertyDetails(p.id, p.name, p.description, p.area, p.rooms, p.price, p.city, p.parkingIncluded, p.furnished, p.maxOccupancy, p.outsideArea, p.propertyTypeId, p.address, p.constructionYear, p.user, p.photoLink) from PropertyEntity p where p.id = ?1")
    PropertyDetails getDetails(Long propertyId);

    @Query("select new com.fontys.s3.buyahouse.domain.PropertyOverview(p.id, p.name, p.description, p.price, p.user, p.photoLink) from PropertyEntity as p")
    List<PropertyOverview> getOverview();

    @Query("select p.user.userId from PropertyEntity as p where p.id =?1")
    Long getUserIdByPropertyId(Long propertyId);

    @Query("select new com.fontys.s3.buyahouse.domain.PropertyOverview(p.id, p.name, p.description, p.price, p.user, p.photoLink) from PropertyEntity as p where p.name like %?1% ")
    List<PropertyOverview> searchProperties(String search);

    @Query("select new com.fontys.s3.buyahouse.domain.PropertyByUserId(p.user.userId, u.username, count(p)) from PropertyEntity as p JOIN p.user as u group by p.user.userId")
    List<PropertyByUserId> getPropertiesByUserId();

    @Query("select new com.fontys.s3.buyahouse.domain.PropertyOverview(p.id, p.name, p.description, p.price, p.user, p.photoLink) from PropertyEntity as p where p.user.userId = ?1")
    List<PropertyOverview> getPropertyOverviewByUser(Long userId);

    void deleteByUser (UserEntity user);
}