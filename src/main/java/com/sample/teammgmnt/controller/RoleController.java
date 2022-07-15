package com.sample.teammgmnt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("role")
public class RoleController {

  @GetMapping("/test")
  public ResponseEntity<String> test(){
    return ResponseEntity.ok("this test was a success");
  }

}
