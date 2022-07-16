package com.sample.teammgmnt.controller.v1;

import com.sample.teammgmnt.user.UserService;
import org.modelmapper.ModelMapper;
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
  private final ModelMapper modelMapper;

  public UserController(UserService userService, ModelMapper modelMapper) {
    this.userService = userService;
    this.modelMapper = modelMapper;
  }

  @GetMapping
  public ResponseEntity<List<UserListDTO>> listAll() {
    List<UserListDTO> dtos = userService.listAll().stream()
            .map((item) -> modelMapper.map(item, UserListDTO.class))
            .collect(Collectors.toList());
    return ResponseEntity.ok(dtos);
  }

}
