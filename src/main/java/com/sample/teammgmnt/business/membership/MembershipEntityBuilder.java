package com.sample.teammgmnt.business.membership;

public final class MembershipEntityBuilder {
  private String id;
  private String userId;
  private String teamId;
  private String roleId;

  private MembershipEntityBuilder() {
  }

  public static MembershipEntityBuilder of() {
    return new MembershipEntityBuilder();
  }

  public MembershipEntityBuilder id(String id) {
    this.id = id;
    return this;
  }

  public MembershipEntityBuilder userId(String userId) {
    this.userId = userId;
    return this;
  }

  public MembershipEntityBuilder teamId(String teamId) {
    this.teamId = teamId;
    return this;
  }

  public MembershipEntityBuilder roleId(String roleId) {
    this.roleId = roleId;
    return this;
  }

  public MembershipEntity build() {
    MembershipEntity membershipEntity = new MembershipEntity();
    membershipEntity.setId(id);
    membershipEntity.setUserId(userId);
    membershipEntity.setTeamId(teamId);
    membershipEntity.setRoleId(roleId);
    return membershipEntity;
  }
}
