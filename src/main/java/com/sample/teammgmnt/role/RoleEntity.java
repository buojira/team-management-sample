package com.sample.teammgmnt.role;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "team_role")
public class RoleEntity {
  @Id
  private String id;
  private String name;

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

  @Override
  public String toString() {
    return "RoleEntity{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            '}';
  }
}