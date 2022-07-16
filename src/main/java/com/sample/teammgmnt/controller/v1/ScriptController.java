package com.sample.teammgmnt.controller.v1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sample.teammgmnt.role.RoleService;
import com.sample.teammgmnt.script.InitialDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("script")
public class ScriptController {
  private static final Logger LOGGER = LoggerFactory.getLogger(ScriptController.class);
  private final InitialDataService initialDataService;

  public ScriptController(InitialDataService initialDataService) {
    this.initialDataService = initialDataService;
  }

  @PostMapping("/fill/users")
  public ResponseEntity<String> fillUsers() {
    try {
      return ResponseEntity.ok(initialDataService.createAllUser());
    } catch (IOException e) {
      LOGGER.error(e.getMessage(), e);
      return ResponseEntity.internalServerError().body(e.getMessage());
    }
  }

  @PostMapping("/fill/roles")
  public ResponseEntity<String> fillRoles() {
    try {
      return ResponseEntity.ok(initialDataService.createAllRoles());
    } catch (IOException e) {
      LOGGER.error(e.getMessage(), e);
      return ResponseEntity.internalServerError().body(e.getMessage());
    }
  }

  @PostMapping("/fill/teams")
  public ResponseEntity<String> fillTeams() {
    try {
      return ResponseEntity.ok(initialDataService.createAllTeams());
    } catch (IOException e) {
      LOGGER.error(e.getMessage(), e);
      return ResponseEntity.internalServerError().body(e.getMessage());
    }
  }

}
