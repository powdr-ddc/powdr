package edu.cnm.deepdive.powdr.model.dto;

import com.google.gson.annotations.Expose;

public class Message {

  @Expose
  private String content;

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
