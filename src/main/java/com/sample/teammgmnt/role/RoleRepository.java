package com.sample.teammgmnt.role;

import com.sample.teammgmnt.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<UserEntity, String> {
}
