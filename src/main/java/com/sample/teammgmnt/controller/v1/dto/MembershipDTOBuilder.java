package com.sample.teammgmnt.controller.v1.dto;

public final class MembershipDTOBuilder {
  private String user;
  private String team;
  private String role;

  private MembershipDTOBuilder() {
  }

  public static MembershipDTOBuilder of() {
    return new MembershipDTOBuilder();
  }

  public MembershipDTOBuilder user(String user) {
    this.user = user;
    return this;
  }

  public MembershipDTOBuilder team(String team) {
    this.team = team;
    return this;
  }

  public MembershipDTOBuilder role(String role) {
    this.role = role;
    return this;
  }

  public MembershipDTO build() {
    MembershipDTO membershipDTO = new MembershipDTO();
    membershipDTO.setUser(user);
    membershipDTO.setTeam(team);
    membershipDTO.setRole(role);
    return membershipDTO;
  }
}
