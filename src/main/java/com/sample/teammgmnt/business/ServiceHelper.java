package com.sample.teammgmnt.business;

import org.springframework.data.domain.ExampleMatcher;

import java.util.UUID;

public class ServiceHelper {

  public String generateUUID() {
    return UUID.randomUUID().toString();
  }

  public ExampleMatcher getFullScanExample() {
    return ExampleMatcher.matching()
            .withIgnorePaths(Fields.id.name())
            .withIgnoreNullValues();
  }


}
