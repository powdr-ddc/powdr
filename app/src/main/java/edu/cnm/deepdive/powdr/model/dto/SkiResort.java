package edu.cnm.deepdive.powdr.model.dto;

import com.google.gson.annotations.Expose;
import java.util.UUID;

/**
 * Data transfer object to communicate with the server side.
 */
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

  /**
   * Gets the name of a {@link SkiResort}
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of a {@link SkiResort}
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the latitude of a {@link SkiResort}
   */
  public double getLatitude() {
    return latitude;
  }

  /**
   * Sets the latitude of a {@link SkiResort}
   */
  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  /**
   * Gets the longitude of a {@link SkiResort}
   */
  public double getLongitude() {
    return longitude;
  }

  /**
   * Sets the longitude of a {@link SkiResort}
   */
  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }
}
