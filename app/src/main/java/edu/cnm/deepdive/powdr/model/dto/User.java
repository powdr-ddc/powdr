package edu.cnm.deepdive.powdr.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.UUID;

// TODO Make any additions to turn this into an entity class if appropriate.
public class User {

  @Expose
  @SerializedName("userId")
  private UUID externalId;

  @Expose
  @SerializedName("name")
  private String username;

  @Expose
  private String bio;

  @Expose
  private String imagePath;

  /**
   * Gets the external id of a {@link User}
   */
  public UUID getExternalId() {
    return externalId;
  }

  /**
   * Sets the external id of a {@link User}
   */
  public void setExternalId(UUID externalId) {
    this.externalId = externalId;
  }

  /**
   * Gets the username of a {@link User}
   */
  public String getUsername() {
    return username;
  }

  /**
   * Sets the username of a {@link User}
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * Gets the bio of a {@link User}
   */
  public String getBio() {
    return bio;
  }

  /**
   * Sets the bio of a {@link User}
   */
  public void setBio(String bio) {
    this.bio = bio;
  }

  /**
   * Gets the image path of a {@link User}
   */
  public String getImagePath() {
    return imagePath;
  }

  /**
   * Sets the image path of a {@link User}
   */
  public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
  }
}
