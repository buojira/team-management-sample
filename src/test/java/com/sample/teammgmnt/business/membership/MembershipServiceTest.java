package com.sample.teammgmnt.business.membership;

import com.sample.teammgmnt.business.role.RoleEntity;
import com.sample.teammgmnt.business.role.RoleRepository;
import com.sample.teammgmnt.business.team.TeamEntity;
import com.sample.teammgmnt.business.team.TeamService;
import com.sample.teammgmnt.business.user.UserEntity;
import com.sample.teammgmnt.business.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MembershipServiceTest {

  @Spy
  @InjectMocks
  private MembershipService target;
  @Mock
  private MembershipRepository membershipRepository;
  @Mock
  private RoleRepository roleRepository;
  @Mock
  private UserService userService;
  @Mock
  private TeamService teamService;

  @Test
  public void whenDeletingExistentRow_thenSuccess(){
    final String teamID = "1";
    final String userID = "2";
    final String roleID = "3";
    Optional<MembershipEntity> found = Optional.of(MembershipEntityBuilder.of().id("333").build());

    doReturn(found).when(membershipRepository).findOne(any(Example.class));
    doNothing().when(membershipRepository).deleteById("333");

    String result = target.delete(teamID, userID, roleID);

    assertThat(result).isEqualTo("Role unassigned from team member");
    verify(membershipRepository).findOne(any(Example.class));
    verify(membershipRepository).deleteById("333");
  }

  @Test
  public void whenDeletingNonExistentRow_thenDoNothing(){
    final String teamID = "1";
    final String userID = "2";
    final String roleID = "3";

    doReturn(Optional.empty()).when(membershipRepository).findOne(any(Example.class));

    String result = target.delete(teamID, userID, roleID);

    assertThat(result).isEqualTo("Role was not assigned to team member. Nothing was done");
    verify(membershipRepository).findOne(any(Example.class));
    verify(membershipRepository, never()).deleteById(anyString());
  }

  @Test
  public void whenSavingExistentRow_thenSuccess(){
    final String teamID = "1";
    final String userID = "2";
    final String roleID = "3";

    doReturn(false).when(membershipRepository).exists(any(Example.class));
    doReturn(new MembershipEntity()).when(membershipRepository).save(any(MembershipEntity.class));

    String result = target.save(teamID, userID, roleID);

    assertThat(result).isEqualTo("New role assigned to team member");
    verify(membershipRepository).exists(any(Example.class));
    verify(membershipRepository).save(any(MembershipEntity.class));
  }

  @Test
  public void whenSavingNonExistentRow_thenSuccess(){
    final String teamID = "1";
    final String userID = "2";
    final String roleID = "3";

    doReturn(true).when(membershipRepository).exists(any(Example.class));

    String result = target.save(teamID, userID, roleID);

    assertThat(result).isEqualTo("Role was already assigned to team member. Nothing was done");
    verify(membershipRepository).exists(any(Example.class));
    verify(membershipRepository, never()).save(any(MembershipEntity.class));
  }

  @Test
  public void whenFindRoles_thenSuccess() {
    final String teamID = "1";
    final String userID = "2";
    final List<MembershipEntity> found = new ArrayList<>();
    found.add(MembershipEntityBuilder.of().teamId(teamID).userId(userID).roleId("A").build());
    found.add(MembershipEntityBuilder.of().teamId(teamID).userId(userID).roleId("B").build());
    found.add(MembershipEntityBuilder.of().teamId(teamID).userId(userID).roleId("C").build());

    doReturn(found).when(membershipRepository).findAll(any(Example.class));
    doReturn(new RoleEntity()).when(roleRepository).getById(anyString());

    List<RoleEntity> roles = target.findRoles(teamID, userID);

    assertThat(roles).isNotEmpty();
    assertThat(roles.size()).isEqualTo(3);
    verify(membershipRepository).findAll(any(Example.class));
    verify(roleRepository, times(3)).getById(anyString());
  }

  @Test
  public void whenDoNotFindRoles_thenReturnEmpty() {
    final String teamID = "1";
    final String userID = "2";

    doReturn(new ArrayList<>()).when(membershipRepository).findAll(any(Example.class));

    List<RoleEntity> roles = target.findRoles(teamID, userID);

    assertThat(roles).isEmpty();
    verify(membershipRepository).findAll(any(Example.class));
    verify(roleRepository, never()).getById(anyString());
  }

  @Test
  public void whenFindMembership_thenSuccess() {
    final String roleID = "3";
    final List<MembershipEntity> found = new ArrayList<>();
    found.add(MembershipEntityBuilder.of().teamId("1").userId("098").roleId(roleID).build());
    found.add(MembershipEntityBuilder.of().teamId("002").userId("tgdd").roleId(roleID).build());
    found.add(MembershipEntityBuilder.of().teamId("yzr").userId("wsxv").roleId(roleID).build());

    doReturn(new TeamEntity()).when(teamService).get(anyString());
    doReturn(new UserEntity()).when(userService).findById(anyString());
    doReturn(found).when(membershipRepository).findAll(any(Example.class));

    List<MembershipEntity> result = target.findMemberships(roleID);

    assertThat(result).isNotEmpty();
    assertThat(result.size()).isEqualTo(3);
    verify(teamService, times(3)).get(anyString());
    verify(userService, times(3)).findById(anyString());
    verify(membershipRepository).findAll(any(Example.class));
  }

  @Test
  public void whenDoNotFindMembership_returnEmpty() {
    final String roleID = "3";
    doReturn(new ArrayList<>()).when(membershipRepository).findAll(any(Example.class));

    List<MembershipEntity> result = target.findMemberships(roleID);

    assertThat(result).isEmpty();
    verify(teamService, never()).get(anyString());
    verify(userService, never()).findById(anyString());
    verify(membershipRepository).findAll(any(Example.class));
  }


}
