package edu.cnm.deepdive.powdr.model.dto;

import com.google.gson.annotations.Expose;
import java.util.UUID;

public class SkiResort {

  @Expose
  private UUID skiResortId;

  @Expose
  private String name;

  @Expose
  private double latitude;

  @Expose
  private double longitude;

  public UUID getSkiResortId() {
    return skiResortId;
  }

  public void setSkiResortId(UUID skiResortId) {
    this.skiResortId = skiResortId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getLatitude() {
    return latitude;
  }

  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }
}
