package com.sample.teammgmnt.business.teamrole;

public final class TeamRoleEntityBuilder {
  private String id;
  private String userId;
  private String teamId;
  private String roleId;

  private TeamRoleEntityBuilder() {
  }

  public static TeamRoleEntityBuilder of() {
    return new TeamRoleEntityBuilder();
  }

  public TeamRoleEntityBuilder id(String id) {
    this.id = id;
    return this;
  }

  public TeamRoleEntityBuilder userId(String userId) {
    this.userId = userId;
    return this;
  }

  public TeamRoleEntityBuilder teamId(String teamId) {
    this.teamId = teamId;
    return this;
  }

  public TeamRoleEntityBuilder roleId(String roleId) {
    this.roleId = roleId;
    return this;
  }

  public TeamRoleEntity build() {
    TeamRoleEntity teamRoleEntity = new TeamRoleEntity();
    teamRoleEntity.setId(id);
    teamRoleEntity.setUserId(userId);
    teamRoleEntity.setTeamId(teamId);
    teamRoleEntity.setRoleId(roleId);
    return teamRoleEntity;
  }
}
