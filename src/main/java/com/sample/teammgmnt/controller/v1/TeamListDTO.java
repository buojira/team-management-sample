package com.sample.teammgmnt.controller.v1;

public class TeamListDTO {
  private String id;
  private String name;
  private String teamLeadId;
  private String teamMemberIds;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getTeamLeadId() {
    return teamLeadId;
  }

  public void setTeamLeadId(String teamLeadId) {
    this.teamLeadId = teamLeadId;
  }

  public String getTeamMemberIds() {
    return teamMemberIds;
  }

  public void setTeamMemberIds(String teamMemberIds) {
    this.teamMemberIds = teamMemberIds;
  }
}
