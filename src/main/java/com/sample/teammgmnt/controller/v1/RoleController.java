package com.sample.teammgmnt.controller.v1;

import com.sample.teammgmnt.business.role.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("role")
public class RoleController {

  private final RoleService roleService;
  private final ModelMapper modelMapper;

  public RoleController(RoleService roleService, ModelMapper modelMapper) {
    this.roleService = roleService;
    this.modelMapper = modelMapper;
  }

  @GetMapping("/test")
  public ResponseEntity<String> test(){
    return ResponseEntity.ok("this test was a success");
  }

  @GetMapping
  public ResponseEntity<List<RoleListDTO>> listAll() {
    List<RoleListDTO> dtos = roleService.listAll().stream()
            .map((item) -> modelMapper.map(item, RoleListDTO.class))
            .collect(Collectors.toList());
    return ResponseEntity.ok(dtos);
  }

}
