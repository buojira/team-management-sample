package com.sample.teammgmnt.team;

public final class TeamEntityBuilder {
  private String id;
  private String name;
  private String teamLeadId;
  private String teamMemberIds;

  private TeamEntityBuilder() {
  }

  public static TeamEntityBuilder of() {
    return new TeamEntityBuilder();
  }

  public TeamEntityBuilder id(String id) {
    this.id = id;
    return this;
  }

  public TeamEntityBuilder name(String name) {
    this.name = name;
    return this;
  }

  public TeamEntityBuilder teamLeadId(String teamLeadId) {
    this.teamLeadId = teamLeadId;
    return this;
  }

  public TeamEntityBuilder teamMemberIds(String teamMemberIds) {
    this.teamMemberIds = teamMemberIds;
    return this;
  }

  public TeamEntity build() {
    TeamEntity teamEntity = new TeamEntity();
    teamEntity.setId(id);
    teamEntity.setName(name);
    teamEntity.setTeamLeadId(teamLeadId);
    teamEntity.setTeamMemberIds(teamMemberIds);
    return teamEntity;
  }
}
