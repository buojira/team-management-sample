package com.sample.teammgmnt.business.teamrole;

import com.sample.teammgmnt.business.IDGenerator;
import com.sun.istack.NotNull;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeamRoleService {
  private static final Logger LOGGER = LoggerFactory.getLogger(TeamRoleService.class);

  public static final String DEFAULT_ROLE = "1245a4bf-adfe-415c-941b-1739af070301";
  private final TeamRoleRepository teamRoleRepository;

  public TeamRoleService(TeamRoleRepository teamRoleRepository) {
    this.teamRoleRepository = teamRoleRepository;
  }

  public String save(@NotNull String teamID, @NotNull String userID, String roleID) {
    TeamRoleEntity entity = castEntity(teamID, userID, verifyRoleID(roleID));
    if (!exists(entity)) {
      entity.setId(IDGenerator.generate());
      teamRoleRepository.save(entity);
      return "New role assigned to team member";
    }
    return "Role was already assigned to team member. Nothing was done";
  }

  public String delete(@NotNull String teamID, @NotNull String userID, @NotNull String roleID) {
    Optional<String> optId = findID(castEntity(teamID, userID, roleID));
    if (optId.isPresent()) {
      teamRoleRepository.deleteById(optId.get());
      return "Role unassigned from team member";
    }
    return "Role was not assigned to team member. Nothing was done";
  }

  private Optional<String> findID(TeamRoleEntity entity) {
    Optional<TeamRoleEntity> one = teamRoleRepository.findOne(getFullScanExample(entity));
    return one.isPresent() ?
            Optional.of(one.get().getId()) :
            Optional.empty();
  }

  private boolean exists(TeamRoleEntity entity) {
    return teamRoleRepository.exists(getFullScanExample(entity));
  }

  private Example<TeamRoleEntity> getFullScanExample(TeamRoleEntity entity) {
    return Example.of(entity, fullScanExample());
  }

  private ExampleMatcher fullScanExample() {
    return ExampleMatcher.matching()
            .withIgnorePaths("id")
            .withStringMatcher(ExampleMatcher.StringMatcher.valueOf("userId"))
            .withStringMatcher(ExampleMatcher.StringMatcher.valueOf("teamID"))
            .withStringMatcher(ExampleMatcher.StringMatcher.valueOf("roleID"));
  }

  private TeamRoleEntity castEntity(String teamID, String userID, String roleID) {
    return TeamRoleEntityBuilder.of()
            .teamId(teamID)
            .userId(userID)
            .roleId(roleID)
            .build();
  }

  private String verifyRoleID(String roleID) {
    return Strings.isNotBlank(roleID) ? roleID : DEFAULT_ROLE;
  }
}
