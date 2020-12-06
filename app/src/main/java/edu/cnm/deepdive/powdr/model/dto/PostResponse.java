package edu.cnm.deepdive.powdr.model.dto;

import edu.cnm.deepdive.powdr.model.User;
import java.util.Date;
import java.util.List;

public class PostResponse {


  private List<Post> posts;


  public List<Post> getPosts() {
    return posts;
  }

  public void setPosts(List<Post> posts) {
    this.posts = posts;
  }

  public static class Post {


    private User user;

    private String content;

    private String imagePath;

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

}
