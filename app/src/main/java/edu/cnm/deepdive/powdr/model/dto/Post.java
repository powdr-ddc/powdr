package edu.cnm.deepdive.powdr.model.dto;

import com.google.gson.annotations.Expose;
import java.util.Date;

public class Post {

  @Expose
  private User user;

  @Expose
  private String content;

  @Expose
  private String imagePath;

  @Expose
  private Date timestamp;

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getImagePath() {
    return imagePath;
  }

  public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
  }

  public Date getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

}
