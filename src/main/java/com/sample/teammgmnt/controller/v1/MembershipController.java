package com.sample.teammgmnt.controller.v1;

import com.sample.teammgmnt.business.teamrole.TeamRoleService;
import com.sample.teammgmnt.controller.v1.dto.MembershipDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("membership")
public class MembershipController {
  private final TeamRoleService teamRoleService;
  private final CustomModelMapper mapper;

  public MembershipController(TeamRoleService teamRoleService, CustomModelMapper mapper) {
    this.teamRoleService = teamRoleService;
    this.mapper = mapper;
  }

  @GetMapping
  public ResponseEntity<List<MembershipDTO>> listAll() {
    List<MembershipDTO> list = teamRoleService.listAll().stream()
            .map(row -> mapper.toMemberShipDTO(row))
            .collect(Collectors.toList());
    return ResponseEntity.ok(list);
  }

  @PostMapping
  public ResponseEntity<String> add(@RequestParam String teamID,
                                    @RequestParam String userID,
                                    @RequestParam(required = false) String roleID) {
    String response = teamRoleService.save(teamID, userID, roleID);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping
  public ResponseEntity<String> remove(@RequestParam String teamID,
                                       @RequestParam String userID,
                                       @RequestParam String roleID) {
    String response = teamRoleService.delete(teamID, userID, roleID);
    return ResponseEntity.ok(response);
  }

}
