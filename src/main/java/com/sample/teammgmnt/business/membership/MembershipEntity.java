package com.sample.teammgmnt.business.membership;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "role_search")
public class MembershipEntity {

  @Id
  private String id;
  private String userId;
  private String teamId;
  private String roleId;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getTeamId() {
    return teamId;
  }

  public void setTeamId(String teamId) {
    this.teamId = teamId;
  }

  public String getRoleId() {
    return roleId;
  }

  public void setRoleId(String roleId) {
    this.roleId = roleId;
  }
}
