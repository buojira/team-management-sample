package com.sample.teammgmnt.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleSearchRepository extends JpaRepository<RoleSearchEntity, String> {
}
