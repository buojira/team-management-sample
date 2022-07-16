package com.sample.teammgmnt.controller.v1;

import com.sample.teammgmnt.business.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("user")
public class UserController {

  private final UserService userService;
  private final TeamModelMapper mapper;

  public UserController(UserService userService, TeamModelMapper mapper) {
    this.userService = userService;
    this.mapper = mapper;
  }

  @GetMapping
  public ResponseEntity<List<UserListDTO>> listAll() {
    List<UserListDTO> dtos = userService.listAll().stream()
            .map((item) -> mapper.toUserDTO(item))
            .collect(Collectors.toList());
    return ResponseEntity.ok(dtos);
  }

}
