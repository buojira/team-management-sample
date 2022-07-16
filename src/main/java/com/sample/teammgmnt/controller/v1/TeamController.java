package com.sample.teammgmnt.controller.v1;

import com.sample.teammgmnt.business.membership.MembershipService;
import com.sample.teammgmnt.business.team.TeamService;
import com.sample.teammgmnt.controller.v1.dto.RoleResponseDTO;
import com.sample.teammgmnt.controller.v1.dto.TeamResponseDTO;
import com.sample.teammgmnt.controller.v1.dto.UserResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("team")
public class TeamController {

  private final TeamService teamService;
  private final MembershipService membershipService;
  private final CustomModelMapper mapper;

  public TeamController(TeamService teamService, MembershipService membershipService, CustomModelMapper mapper) {
    this.teamService = teamService;
    this.membershipService = membershipService;
    this.mapper = mapper;
  }

  @GetMapping
  public ResponseEntity<List<TeamResponseDTO>> getAllTeams() {
    List<TeamResponseDTO> dtos = teamService.listAll().stream()
            .map((item) -> mapper.toTeamDTO(item))
            .collect(Collectors.toList());
    return ResponseEntity.ok(dtos);
  }

  @GetMapping("{id}")
  public ResponseEntity<TeamResponseDTO> getTeamDetail(@PathVariable String id) {
    TeamResponseDTO dto = mapper.toTeamDTO(teamService.get(id));
    return ResponseEntity.ok(dto);
  }

  @GetMapping("{id}/members")
  public ResponseEntity<List<UserResponseDTO>> getMembers(@PathVariable String id) {
    List<UserResponseDTO> members = teamService.getUsers(id).stream()
            .map(row -> mapper.toUserDTO(row))
            .collect(Collectors.toList());
    return ResponseEntity.ok(members);
  }

  @GetMapping("{teamID}/{userID}")
  public ResponseEntity<List<RoleResponseDTO>> getMembership(@PathVariable String teamID,
                                                             @PathVariable String userID) {
    List<RoleResponseDTO> roles = membershipService.findRoles(teamID, userID).stream()
            .map(row -> mapper.toRoleDTO(row))
            .collect(Collectors.toList());
    return ResponseEntity.ok(roles);
  }
}
