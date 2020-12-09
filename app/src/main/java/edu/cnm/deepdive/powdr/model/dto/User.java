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

  public UUID getExternalId() {
    return externalId;
  }

  public void setExternalId(UUID externalId) {
    this.externalId = externalId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getBio() {
    return bio;
  }

  public void setBio(String bio) {
    this.bio = bio;
  }

  public String getImagePath() {
    return imagePath;
  }

  public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
  }
}
