package com.sample.teammgmnt.controller.v1;

import com.sample.teammgmnt.role.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("role")
public class RoleController {

  private final RoleService roleService;

  public RoleController(RoleService roleService) {
    this.roleService = roleService;
  }

  @GetMapping("/test")
  public ResponseEntity<String> test(){
    return ResponseEntity.ok("this test was a success");
  }

  @GetMapping
  public ResponseEntity<List<UserRoleListDTO>> listAll() {
    return ResponseEntity.ok(roleService.findUserRoleList());
  }

}
