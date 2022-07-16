package com.sample.teammgmnt.controller.v1;

import com.sample.teammgmnt.business.team.TeamEntity;
import com.sample.teammgmnt.business.user.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TeamModelMapper {
  private final ModelMapper modelMapper;

  public TeamModelMapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public TeamListDTO toTeamDTO(TeamEntity entity) {
    return modelMapper.map(entity, TeamListDTO.class);
  }

  public UserListDTO toUserDTO(UserEntity entity) {
    return modelMapper.map(entity, UserListDTO.class);
  }
}
