package com.sample.teammgmnt.controller.v1;

import com.sample.teammgmnt.business.role.RoleService;
import com.sample.teammgmnt.controller.v1.dto.MembershipResponseDTO;
import com.sample.teammgmnt.controller.v1.dto.RoleResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("role")
public class RoleController {

  private final RoleService roleService;
  private final CustomModelMapper mapper;

  public RoleController(RoleService roleService, CustomModelMapper modelMapper) {
    this.roleService = roleService;
    this.mapper = modelMapper;
  }

  @GetMapping("/test")
  public ResponseEntity<String> test(){
    return ResponseEntity.ok("this test was a success");
  }

  @PostMapping
  public ResponseEntity<String> add(@RequestParam String roleName) {
    String response = roleService.save(roleName);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping
  public ResponseEntity<String> remove(@RequestParam String roleName) {
    String response = roleService.delete(roleName);
    return ResponseEntity.ok(response);
  }

  @GetMapping
  public ResponseEntity<List<RoleResponseDTO>> listAll() {
    List<RoleResponseDTO> dtos = roleService.listAll().stream()
            .map((item) -> mapper.toRoleDTO(item))
            .collect(Collectors.toList());
    return ResponseEntity.ok(dtos);
  }

  @GetMapping("{roleID}")
  public ResponseEntity<List<MembershipResponseDTO>> getMembership(@PathVariable String roleID) {
    List<MembershipResponseDTO> list = roleService.getMemberships(roleID).stream()
            .map(row -> mapper.toMemberShipDTO(row))
            .collect(Collectors.toList());
    return ResponseEntity.ok(list);
  }

}
