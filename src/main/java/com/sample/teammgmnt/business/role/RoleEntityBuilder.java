package com.sample.teammgmnt.business.role;

public final class RoleEntityBuilder {
  private String id;
  private String name;

  private RoleEntityBuilder() {
  }

  public static RoleEntityBuilder of() {
    return new RoleEntityBuilder();
  }

  public RoleEntityBuilder id(String id) {
    this.id = id;
    return this;
  }

  public RoleEntityBuilder name(String name) {
    this.name = name;
    return this;
  }

  public RoleEntity build() {
    RoleEntity roleEntity = new RoleEntity();
    roleEntity.setId(id);
    roleEntity.setName(name);
    return roleEntity;
  }
}
