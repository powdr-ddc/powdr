package edu.cnm.deepdive.powdr.model.dto;

import com.google.gson.annotations.Expose;

/**
 * Data transfer object to communicate with the server side.
 */
public class FavoriteSkiResort {

  @Expose
  private User user;

  @Expose
  private SkiResort skiResort;

  /**
   * Gets the current {@link User}
   */
  public User getUser() {
    return user;
  }

  /**
   * Sets the current {@link User}
   */
  public void setUser(User user) {
    this.user = user;
  }

  /**
   * Gets a {@link SkiResort}
   */
  public SkiResort getSkiResort() {
    return skiResort;
  }

  /**
   * Sets a {@link SkiResort} as a Favorite.
   */
  public void setSkiResort(SkiResort skiResort) {
    this.skiResort = skiResort;
  }
}
