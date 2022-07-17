package com.sample.teammgmnt.business.role;

import com.sample.teammgmnt.business.ServiceHelper;
import com.sample.teammgmnt.business.membership.MembershipEntity;
import com.sample.teammgmnt.business.membership.MembershipService;
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
  private final MembershipService membershipService;

  @Autowired
  public RoleService(RoleRepository roleRepository, MembershipService membershipService) {
    this.roleRepository = roleRepository;
    this.membershipService = membershipService;
    serviceHelper = new ServiceHelper();
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
    return "Role deleted with success";
  }

  public List<MembershipEntity> getMemberships(String roleID) {
    return membershipService.findMemberships(roleID);
  }

  private RoleEntity castRole(String id, String name) {
    return RoleEntityBuilder.of().id(id).name(name).build();
  }
}
