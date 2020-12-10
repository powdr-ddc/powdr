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

  /**
   * Gets an instance of the {@link User}
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
   * Gets the content of a {@link Post}
   */
  public String getContent() {
    return content;
  }

  /**
   * Sets the content of a {@link Post}
   */
  public void setContent(String content) {
    this.content = content;
  }

  /**
   * Gets the ImagePath of a {@link Post}
   */
  public String getImagePath() {
    return imagePath;
  }

  /**
   * Sets the ImagePath of a {@link Post}
   */
  public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
  }

  /**
   * Gets the timestamp of a {@link Post}
   */
  public Date getTimestamp() {
    return timestamp;
  }

  /**
   * Sets the timestamp of a {@link Post}
   */
  public void setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
  }

}
