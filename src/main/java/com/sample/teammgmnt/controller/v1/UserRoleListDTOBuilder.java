package com.sample.teammgmnt.controller.v1;

public final class UserRoleListDTOBuilder {
  private String user;
  private String team;
  private String role;

  private UserRoleListDTOBuilder() {
  }

  public static UserRoleListDTOBuilder of() {
    return new UserRoleListDTOBuilder();
  }

  public UserRoleListDTOBuilder user(String user) {
    this.user = user;
    return this;
  }

  public UserRoleListDTOBuilder team(String team) {
    this.team = team;
    return this;
  }

  public UserRoleListDTOBuilder role(String role) {
    this.role = role;
    return this;
  }

  public UserRoleListDTO build() {
    UserRoleListDTO userRoleListDTO = new UserRoleListDTO();
    userRoleListDTO.setUser(user);
    userRoleListDTO.setTeam(team);
    userRoleListDTO.setRole(role);
    return userRoleListDTO;
  }
}
