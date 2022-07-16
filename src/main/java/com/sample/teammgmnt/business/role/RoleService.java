package com.sample.teammgmnt.business.role;

import com.sample.teammgmnt.business.ServiceHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
  private static final Logger LOGGER = LoggerFactory.getLogger(RoleService.class);

  private final RoleRepository roleRepository;
  private final ServiceHelper serviceHelper;

  @Autowired
  public RoleService(RoleRepository roleRepository) {
    this.roleRepository = roleRepository;
    serviceHelper = new ServiceHelper();
  }

  public Optional<String> getRoleName(String id){
    Optional<RoleEntity> one = roleRepository.findById(id);
    return one.isPresent() ?
            Optional.of(one.get().getName()) :
            Optional.empty();
  }
  public Optional<String> getRoleID(String name) {
    RoleEntity entity = RoleEntityBuilder.of()
            .name(name)
            .build();
    Example<RoleEntity> example = Example.of(entity, serviceHelper.getFullScanExample());
    Optional<RoleEntity> one = roleRepository.findOne(example);
    return one.isPresent() ?
            Optional.of(one.get().getId()) :
            Optional.empty();
  }

  public List<RoleEntity> listAll() {
    return roleRepository.findAll();
  }

  public String save(String name) {
    if (getRoleID(name).isPresent()) {
      return "Role name already registered. Nothing was done";
    }
    roleRepository.save(castRole(serviceHelper.generateUUID(), name));
    return "New role registered with success";
  }

  public String delete(String name) {
    Optional<String> id = getRoleID(name);
    if (id.isEmpty()) {
      return "Role already deleted. Nothing was done";
    }
    roleRepository.deleteById(id.get());
    return "New role deleted with success";
  }

  private RoleEntity castRole(String id, String name) {
    return RoleEntityBuilder.of().id(id).name(name).build();
  }

}
