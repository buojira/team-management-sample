package com.sample.teammgmnt.user;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<UserEntity> listAll() {
    return userRepository.findAll();
  }

  public UserEntity findById(String id) {
    return userRepository.getById(id);
  }

}
