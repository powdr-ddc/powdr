package edu.cnm.deepdive.powdr.model.dto;

import com.google.gson.annotations.Expose;
import edu.cnm.deepdive.powdr.model.User;

public class FavoriteSkiResort {

  @Expose
  private User user;

  @Expose
  private SkiResort skiResort;

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public SkiResort getSkiResort() {
    return skiResort;
  }

  public void setSkiResort(SkiResort skiResort) {
    this.skiResort = skiResort;
  }
}
