package com.sample.teammgmnt.business.teamrole;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRoleRepository extends JpaRepository<TeamRoleEntity, String> {
}
