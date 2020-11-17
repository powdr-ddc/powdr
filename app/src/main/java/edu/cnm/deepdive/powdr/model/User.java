package edu.cnm.deepdive.powdr.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.UUID;

// TODO Make any additions to turn this into an entity class if appropriate.
public class User {

  @Expose
  @SerializedName("user_id")
  private UUID externalId;

  @Expose
  @SerializedName("name")
  private String username;

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
}
