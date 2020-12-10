package edu.cnm.deepdive.powdr.model.dto;

import com.google.gson.annotations.Expose;

/**
 * Data transfer object to communicate with the server side.
 */
public class Message {

  @Expose
  private String content;

  /**
   * Gets the content of a {@link Message}
   */
  public String getContent() {
    return content;
  }

  /**
   * Sets the content of a {@link Message}
   */
  public void setContent(String content) {
    this.content = content;
  }
}
