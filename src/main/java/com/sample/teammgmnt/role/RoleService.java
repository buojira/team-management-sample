package com.sample.teammgmnt.role;

import com.sample.teammgmnt.controller.v1.UserRoleListDTO;
import com.sample.teammgmnt.controller.v1.UserRoleListDTOBuilder;
import com.sample.teammgmnt.user.UserEntity;
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

  @Autowired
  public RoleService(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
  }

  public List<UserRoleListDTO> findUserRoleList() {

    return roleRepository.findAll()
            .stream()
            .map(userEntity -> toDTO(userEntity))
            .collect(Collectors.toList());
  }

  private UserRoleListDTO toDTO(UserEntity entity) {
    return UserRoleListDTOBuilder.of()
            .user(entity.getDisplayName())
            .build();
  }

}
