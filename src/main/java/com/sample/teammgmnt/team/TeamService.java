package com.sample.teammgmnt.team;

import com.sample.teammgmnt.user.UserEntity;
import com.sample.teammgmnt.user.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

  private final UserService userService;
  private final TeamRepository teamRepository;
  private final TeamMembersSplitter membersSplitter;

  public TeamService(UserService userService, TeamRepository teamRepository) {
    this.userService = userService;
    this.teamRepository = teamRepository;
    membersSplitter = new TeamMembersSplitter();
  }

  public List<TeamEntity> listAll() {
    return teamRepository.findAll();
  }

  public TeamEntity get(String id) {
    return teamRepository.getById(id);
  }

  public List<UserEntity> getUsers(String teamID) {
    List<UserEntity> users = new ArrayList<>();
    Optional<TeamEntity> row = teamRepository.findById(teamID);
    if (row.isPresent()) {
      List<String> members = membersSplitter.extractMembers(row.get().getTeamMemberIds());
      members.forEach(member -> users.add(userService.findById(member)));
    }
    return users;
  }

}
