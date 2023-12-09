package com.fontys.s3.buyahouse.persistance;

import com.fontys.s3.buyahouse.domain.LoggedUserDetails;
import com.fontys.s3.buyahouse.persistance.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
    @Query("select new com.fontys.s3.buyahouse.persistance.entity.UserEntity(u.userId, u.username, u.password, u.phoneNumber, u.firstName, u.lastName, u.userRole) from UserEntity u where u.userId = ?1")
    UserEntity findByUserId(Long userId);
    @Query("select new com.fontys.s3.buyahouse.domain.LoggedUserDetails(u.userId, u.username, u.phoneNumber, u.firstName, u.lastName, u.userRole) from UserEntity u where u.userId = ?1")
    LoggedUserDetails getDetails(Long userId);
}
