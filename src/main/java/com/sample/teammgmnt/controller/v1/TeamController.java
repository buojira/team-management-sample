package com.sample.teammgmnt.controller.v1;

import com.sample.teammgmnt.business.team.TeamService;
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
  private final TeamModelMapper mapper;

  public TeamController(TeamService teamService, TeamModelMapper mapper) {
    this.teamService = teamService;
    this.mapper = mapper;
  }

  @GetMapping
  public ResponseEntity<List<TeamListDTO>> getAllTeams() {
    List<TeamListDTO> dtos = teamService.listAll().stream()
            .map((item) -> mapper.toTeamDTO(item))
            .collect(Collectors.toList());
    return ResponseEntity.ok(dtos);
  }

  @GetMapping("{id}")
  public ResponseEntity<TeamListDTO> getTeamDetail(@PathVariable String id) {
    TeamListDTO dto = mapper.toTeamDTO(teamService.get(id));
    return ResponseEntity.ok(dto);
  }

  @GetMapping("{id}/members")
  public ResponseEntity<List<UserListDTO>> getMembers(@PathVariable String id) {
    List<UserListDTO> members = teamService.getUsers(id).stream()
            .map(row -> mapper.toUserDTO(row))
            .collect(Collectors.toList());
    TeamListDTO dto = mapper.toTeamDTO(teamService.get(id));
    return ResponseEntity.ok(members);
  }

}
