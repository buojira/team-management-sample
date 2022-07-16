package com.sample.teammgmnt.controller.v1.dto;

public final class MembershipResponseDTOBuilder {
  private String user;
  private String team;
  private String role;

  private MembershipResponseDTOBuilder() {
  }

  public static MembershipResponseDTOBuilder of() {
    return new MembershipResponseDTOBuilder();
  }

  public MembershipResponseDTOBuilder user(String user) {
    this.user = user;
    return this;
  }

  public MembershipResponseDTOBuilder team(String team) {
    this.team = team;
    return this;
  }

  public MembershipResponseDTOBuilder role(String role) {
    this.role = role;
    return this;
  }

  public MembershipResponseDTO build() {
    MembershipResponseDTO membershipResponseDTO = new MembershipResponseDTO();
    membershipResponseDTO.setUser(user);
    membershipResponseDTO.setTeam(team);
    membershipResponseDTO.setRole(role);
    return membershipResponseDTO;
  }
}
