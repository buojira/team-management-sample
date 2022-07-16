package com.sample.teammgmnt.controller.v1.dto;

public class MembershipResponseDTO {
  private String user;
  private String team;
  private String role;

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getTeam() {
    return team;
  }

  public void setTeam(String team) {
    this.team = team;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }
}
