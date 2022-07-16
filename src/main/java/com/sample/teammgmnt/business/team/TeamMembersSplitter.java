package com.sample.teammgmnt.business.team;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TeamMembersSplitter {

  private final Pattern SPLIT_PATTERN = Pattern.compile("([^\\[\\],\\s]+)");

  public List<String> extractMembers(String inlineListOfMembers) {
    List<String> members = new ArrayList<>();
    Matcher matcher = SPLIT_PATTERN.matcher(inlineListOfMembers);
    while (matcher.find()) {
      members.add(matcher.group(1));
    }
    return members;
  }

}
