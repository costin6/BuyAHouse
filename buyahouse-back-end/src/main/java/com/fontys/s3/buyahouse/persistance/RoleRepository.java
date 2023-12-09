package com.fontys.s3.buyahouse.persistance;

import com.fontys.s3.buyahouse.persistance.entity.RoleEnum;
import com.fontys.s3.buyahouse.persistance.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<UserRoleEntity, Long> {
    UserRoleEntity findByRole(RoleEnum roleEnum);
}
