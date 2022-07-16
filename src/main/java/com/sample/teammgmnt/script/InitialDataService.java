package com.sample.teammgmnt.script;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.teammgmnt.role.RoleEntity;
import com.sample.teammgmnt.role.RoleRepository;
import com.sample.teammgmnt.team.TeamEntity;
import com.sample.teammgmnt.team.TeamEntityBuilder;
import com.sample.teammgmnt.team.TeamURLDTO;
import com.sample.teammgmnt.team.TeamRepository;
import com.sample.teammgmnt.user.UserEntity;
import com.sample.teammgmnt.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.Scanner;

@Service
public class InitialDataService {
  public static final String ALL_USERS_URL = "https://cgjresszgg.execute-api.eu-west-1.amazonaws.com/users";
  public static final String ALL_TEAMS_URL = "https://cgjresszgg.execute-api.eu-west-1.amazonaws.com/teams";
  public static final String USER_DETAIL_URL = "https://cgjresszgg.execute-api.eu-west-1.amazonaws.com/users/";
  public static final String TEAM_DETAIL_URL = "https://cgjresszgg.execute-api.eu-west-1.amazonaws.com/teams/";
  public static final String ROLES_JSON = "roles.json";
  private final TeamRepository teamRepository;
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;

  private final ObjectMapper objectMapper;

  private static final Logger LOGGER = LoggerFactory.getLogger(InitialDataService.class);

  public InitialDataService(TeamRepository teamRepository, UserRepository userRepository, RoleRepository roleRepository) {
    this.teamRepository = teamRepository;
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    objectMapper = new ObjectMapper();
  }

  public String fillInitialData() throws IOException {
//    createAllUser();
//    createAllTeams();
//    createAllRoles(getFileContent(ROLES_JSON));
    return "ok. all data was set";
  }

  private void createAllRoles(String content) throws JsonProcessingException {
    JsonNode rows = objectMapper.readTree(content);
    for (int i = 0; i < rows.size(); i++) {
      RoleEntity entity = objectMapper.readValue(rows.get(i).toString(), RoleEntity.class);
      roleRepository.save(entity);
      LOGGER.info(entity.toString());
    }
    LOGGER.info(rows.size() + " roles were created");
  }

  private void createAllUser() throws IOException {
    JsonNode rows = getContentFromURL(ALL_USERS_URL);
    for (int i = 0; i < rows.size(); i++) {
      UserFileDTO id = getUserFileDTO(rows.get(i));
      UserEntity entity = getUserEntity(id.getId());
      userRepository.save(entity);
      LOGGER.info(entity.toString());
    }
    LOGGER.info(rows.size() + " users were created");
  }

  private UserEntity getUserEntity(String id) throws IOException {
    return objectMapper.readValue(URI.create(USER_DETAIL_URL + id).toURL(), UserEntity.class);
  }

  private UserFileDTO getUserFileDTO(JsonNode row) throws JsonProcessingException {
    return objectMapper.readValue(row.toString(), UserFileDTO.class);
  }

  private JsonNode getContentFromURL(String url) throws IOException {
    return objectMapper.readTree(URI.create(url).toURL());
  }

  private void createAllTeams() throws IOException {
    JsonNode rows = getContentFromURL(ALL_TEAMS_URL);
    for (int i = 0; i < rows.size(); i++) {
      TeamFileDTO id = getTeamFileDTO(rows.get(i));
      TeamEntity entity = getTeamEntity(id.getId());
      teamRepository.save(entity);
      LOGGER.info(entity.toString());
    }
    LOGGER.info(rows.size() + " teams were created");
  }

  private TeamFileDTO getTeamFileDTO(JsonNode row) throws JsonProcessingException {
    return objectMapper.readValue(row.toString(), TeamFileDTO.class);
  }

  private TeamEntity getTeamEntity(String id) throws IOException {
    TeamURLDTO dto = objectMapper.readValue(URI.create(TEAM_DETAIL_URL + id).toURL(), TeamURLDTO.class);
    return TeamEntityBuilder.of()
            .teamLeadId(dto.getTeamLeadId())
            .name(dto.getName())
            .id(dto.getId())
            .teamMemberIds(toInlineContent(dto.getTeamMemberIds()))
            .build();
  }

  private String toInlineContent(String[] teamMemberIds) {
    return Arrays.toString(teamMemberIds);
  }

  private String getFileContent(String filePath) {
    StringBuilder content = new StringBuilder();
    Scanner scan = new Scanner(InitialDataService.class.getClassLoader().getResourceAsStream(filePath));
    while (scan.hasNextLine()) {
      content.append(scan.nextLine());
    }
    return content.toString();

  }

  private String getAllTeamsFile() {
    return "teams.json";
  }

  private String getAllUsersFile() {
    return "users.json";
  }

}
