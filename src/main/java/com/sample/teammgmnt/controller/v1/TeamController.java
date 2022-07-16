package com.sample.teammgmnt.controller.v1;

import com.sample.teammgmnt.team.TeamService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("team")
public class TeamController {

  private final TeamService teamService;
  private final ModelMapper modelMapper;

  public TeamController(TeamService teamService, ModelMapper modelMapper) {
    this.teamService = teamService;
    this.modelMapper = modelMapper;
  }

  @GetMapping
  public ResponseEntity<List<TeamListDTO>> getAllTeams() {
    List<TeamListDTO> dtos = teamService.listAll().stream()
            .map((item) -> modelMapper.map(item, TeamListDTO.class))
            .collect(Collectors.toList());
    return ResponseEntity.ok(dtos);
  }

}
