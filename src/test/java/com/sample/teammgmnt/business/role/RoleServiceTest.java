package com.sample.teammgmnt.business.role;

import com.sample.teammgmnt.business.membership.MembershipService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Example;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RoleServiceTest {

  @Spy
  @InjectMocks
  private RoleService target;
  @Mock
  private RoleRepository roleRepository;
  @Mock
  private MembershipService membershipService;

  @Test
  public void whenGetRoleIDSucceeds_returnID(){
    Optional<RoleEntity> found = Optional.of(RoleEntityBuilder.of().id("567").build());

    doReturn(found).when(roleRepository).findOne(any(Example.class));

    Optional<String> id = target.getRoleID("Scrum Master");

    assertThat(id).isPresent();
    assertThat(id.get()).isEqualTo("567");
    verify(roleRepository).findOne(any(Example.class));
  }

  @Test
  public void whenGetRoleIDFails_returnEmpty(){
    doReturn(Optional.empty()).when(roleRepository).findOne(any(Example.class));

    Optional<String> id = target.getRoleID("Scrum Master");

    assertThat(id).isEmpty();
    verify(roleRepository).findOne(any(Example.class));
  }

  @Test
  public void whenSavingNewItem_thenSuccess() {
    doReturn(Optional.empty()).when(roleRepository).findOne(any(Example.class));
    doReturn(new RoleEntity()).when(roleRepository).save(any(RoleEntity.class));

    String result = target.save("Dev II");

    assertThat(result).isEqualTo("New role registered with success");
    verify(roleRepository).findOne(any(Example.class));
    verify(roleRepository).save(any(RoleEntity.class));
  }

  @Test
  public void whenSavingExistentItem_doNothing() {
    RoleEntity found = RoleEntityBuilder.of().id("1").name("Dev II").build();

    doReturn(Optional.of(found)).when(roleRepository).findOne(any(Example.class));

    String result = target.save("Dev II");

    assertThat(result).isEqualTo("Role name already registered. Nothing was done");
    verify(roleRepository).findOne(any(Example.class));
    verify(roleRepository, never()).save(any(RoleEntity.class));
  }

  @Test
  public void whenDeletingExistentItem_thenSuccess() {
    RoleEntity found = RoleEntityBuilder.of().id("1").name("Dev II").build();

    doReturn(Optional.of(found)).when(roleRepository).findOne(any(Example.class));
    doNothing().when(roleRepository).deleteById(anyString());

    String result = target.delete("Dev II");

    assertThat(result).isEqualTo("Role deleted with success");
    verify(roleRepository).findOne(any(Example.class));
    verify(roleRepository).deleteById(anyString());
  }

  @Test
  public void whenDeletingNonExistentItem_doNothing() {
    doReturn(Optional.empty()).when(roleRepository).findOne(any(Example.class));

    String result = target.delete("Dev II");

    assertThat(result).isEqualTo("Role already deleted. Nothing was done");
    verify(roleRepository).findOne(any(Example.class));
    verify(roleRepository, never()).deleteById(anyString());
  }

}
