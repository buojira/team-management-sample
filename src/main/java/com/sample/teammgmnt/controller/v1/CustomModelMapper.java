package com.sample.teammgmnt.controller.v1;

import com.sample.teammgmnt.business.role.RoleEntity;
import com.sample.teammgmnt.business.team.TeamEntity;
import com.sample.teammgmnt.business.membership.MembershipEntity;
import com.sample.teammgmnt.business.user.UserEntity;
import com.sample.teammgmnt.controller.v1.dto.MembershipResponseDTO;
import com.sample.teammgmnt.controller.v1.dto.MembershipResponseDTOBuilder;
import com.sample.teammgmnt.controller.v1.dto.RoleResponseDTO;
import com.sample.teammgmnt.controller.v1.dto.TeamResponseDTO;
import com.sample.teammgmnt.controller.v1.dto.UserResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomModelMapper {
  private final ModelMapper modelMapper;

  public CustomModelMapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public TeamResponseDTO toTeamDTO(TeamEntity entity) {
    return modelMapper.map(entity, TeamResponseDTO.class);
  }

  public UserResponseDTO toUserDTO(UserEntity entity) {
    return modelMapper.map(entity, UserResponseDTO.class);
  }

  public MembershipResponseDTO toMemberShipDTO(MembershipEntity row) {
    return MembershipResponseDTOBuilder.of()
            .team(row.getTeamId())
            .user(row.getUserId())
            .role(row.getRoleId())
            .build();
  }

  public RoleResponseDTO toRoleDTO(RoleEntity entity) {
    return modelMapper.map(entity, RoleResponseDTO.class);
  }
}
