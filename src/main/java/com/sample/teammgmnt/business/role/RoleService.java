package com.sample.teammgmnt.business.role;

import com.sample.teammgmnt.business.teamrole.TeamRoleEntity;
import com.sample.teammgmnt.controller.v1.dto.MembershipDTO;
import com.sample.teammgmnt.controller.v1.dto.MembershipDTOBuilder;
import com.sample.teammgmnt.business.teamrole.TeamRoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {
  private static final Logger LOGGER = LoggerFactory.getLogger(RoleService.class);

  private final RoleRepository roleRepository;
  private final TeamRoleRepository teamRoleRepository;

  @Autowired
  public RoleService(RoleRepository roleRepository, TeamRoleRepository teamRoleRepository) {
    this.roleRepository = roleRepository;
    this.teamRoleRepository = teamRoleRepository;
  }

  public List<RoleEntity> listAll() {
    return roleRepository.findAll();
  }

  public List<MembershipDTO> findUserRoleList() {

    return teamRoleRepository.findAll()
            .stream()
            .map(userEntity -> toDTO(userEntity))
            .collect(Collectors.toList());
  }

  private MembershipDTO toDTO(TeamRoleEntity entity) {
    return MembershipDTOBuilder.of()
            .user(entity.getUserId())
            .team(entity.getTeamId())
            .user(entity.getUserId())
            .build();
  }

}
