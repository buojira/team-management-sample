package com.sample.teammgmnt.team;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

  private final TeamRepository teamRepository;

  public TeamService(TeamRepository teamRepository) {
    this.teamRepository = teamRepository;
  }

  public List<TeamEntity> listAll() {
    return teamRepository.findAll();
  }
}
