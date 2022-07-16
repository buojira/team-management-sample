package com.sample.teammgmnt.controller.v1;

import com.sample.teammgmnt.business.team.TeamEntity;
import com.sample.teammgmnt.business.membership.MembershipEntity;
import com.sample.teammgmnt.business.user.UserEntity;
import com.sample.teammgmnt.controller.v1.dto.MembershipDTO;
import com.sample.teammgmnt.controller.v1.dto.MembershipDTOBuilder;
import com.sample.teammgmnt.controller.v1.dto.TeamListDTO;
import com.sample.teammgmnt.controller.v1.dto.UserListDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomModelMapper {
  private final ModelMapper modelMapper;

  public CustomModelMapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public TeamListDTO toTeamDTO(TeamEntity entity) {
    return modelMapper.map(entity, TeamListDTO.class);
  }

  public UserListDTO toUserDTO(UserEntity entity) {
    return modelMapper.map(entity, UserListDTO.class);
  }

  public MembershipDTO toMemberShipDTO(MembershipEntity row) {
    return MembershipDTOBuilder.of()
            .team(row.getTeamId())
            .user(row.getUserId())
            .role(row.getRoleId())
            .build();
  }
}
