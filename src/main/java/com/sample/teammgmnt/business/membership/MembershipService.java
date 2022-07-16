package com.sample.teammgmnt.business.membership;

import com.sample.teammgmnt.business.ServiceHelper;
import com.sample.teammgmnt.business.role.RoleEntity;
import com.sample.teammgmnt.business.role.RoleRepository;
import com.sample.teammgmnt.business.team.TeamService;
import com.sample.teammgmnt.business.user.UserService;
import com.sun.istack.NotNull;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MembershipService {
  private static final Logger LOGGER = LoggerFactory.getLogger(MembershipService.class);

  public static final String DEFAULT_ROLE = "1245a4bf-adfe-415c-941b-1739af070301";
  private final MembershipRepository membershipRepository;
  private final RoleRepository roleRepository;
  private final ServiceHelper serviceHelper;
  private final UserService userService;
  private final TeamService teamService;

  public MembershipService(MembershipRepository membershipRepository, RoleRepository roleRepository, UserService userService, TeamService teamService) {
    this.membershipRepository = membershipRepository;
    this.roleRepository = roleRepository;
    this.userService = userService;
    this.teamService = teamService;
    serviceHelper = new ServiceHelper();
  }

  public List<MembershipEntity> listAll() {
    return membershipRepository.findAll();
  }

  public String save(@NotNull String teamID, @NotNull String userID, String roleID) {
    MembershipEntity entity = castEntity(teamID, userID, verifyRoleID(roleID));
    if (!exists(entity)) {
      entity.setId(serviceHelper.generateUUID());
      membershipRepository.save(entity);
      return "New role assigned to team member";
    }
    return "Role was already assigned to team member. Nothing was done";
  }

  public String delete(@NotNull String teamID, @NotNull String userID, @NotNull String roleID) {
    Optional<String> optId = findID(castEntity(teamID, userID, roleID));
    if (optId.isPresent()) {
      membershipRepository.deleteById(optId.get());
      return "Role unassigned from team member";
    }
    return "Role was not assigned to team member. Nothing was done";
  }

  private Optional<String> findID(MembershipEntity entity) {
    Optional<MembershipEntity> one = membershipRepository.findOne(getFullScanMembershipExample(entity));
    return one.isPresent() ?
            Optional.of(one.get().getId()) :
            Optional.empty();
  }

  private boolean exists(MembershipEntity entity) {
    return membershipRepository.exists(getFullScanMembershipExample(entity));
  }

  private Example<MembershipEntity> getFullScanMembershipExample(MembershipEntity entity) {
    return Example.of(entity, serviceHelper.getFullScanExample());
  }

  private MembershipEntity castEntity(String teamID, String userID, String roleID) {
    return MembershipEntityBuilder.of()
            .teamId(teamID)
            .userId(userID)
            .roleId(roleID)
            .build();
  }

  private String verifyRoleID(String roleID) {
    return Strings.isNotBlank(roleID) ? roleID : DEFAULT_ROLE;
  }

  public List<RoleEntity> findRoles(String teamID, String userID) {
    List<RoleEntity> result = new ArrayList<>();
    MembershipEntity filter = MembershipEntityBuilder.of()
            .teamId(teamID)
            .userId(userID)
            .build();
    List<MembershipEntity> rows = findAll(filter);
    rows.forEach(row -> result.add(roleRepository.getById(row.getRoleId())));
    return result;
  }

  public List<MembershipEntity> findMemberships(String roleID) {
    MembershipEntity filter = MembershipEntityBuilder.of()
            .roleId(roleID)
            .build();
    List<MembershipEntity> result = new ArrayList<>();
    findAll(filter).forEach(row -> result.add(assembleDescribedMembership(row)));
    return result;
  }

  private MembershipEntity assembleDescribedMembership(MembershipEntity row) {
    return MembershipEntityBuilder.of()
            .teamId(getTeamName(row.getTeamId()))
            .userId(getUserName(row.getUserId()))
            .build();
  }

  private String getUserName(String userId) {
    return userService.findById(userId).getDisplayName();
  }

  private String getTeamName(String teamId) {
    return teamService.get(teamId).getName();
  }

  private List<MembershipEntity> findAll(MembershipEntity filter) {
    return membershipRepository.findAll(Example.of(filter, serviceHelper.getFullScanExample()));
  }
}
