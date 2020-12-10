package edu.cnm.deepdive.powdr.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Data transfer class to transfer the data between the weather API and the application.
 */
public class WeatherResponse {

  @Expose
  @SerializedName("main")
  private Temperature temperature;

  @Expose
  private List<Weather> weather;

  @Expose
  private int visibility;

  @Expose
  private Clouds clouds;

  @Expose
  private Precipitation rain;

  @Expose
  private Precipitation snow;

  private Wind wind;
  /**
   * Returns the temperature of a location.
   */
  public Temperature getTemperature() {
    return temperature;
  }

  /**
   * Sets the temperature of a location
   * @param temperature Temperature to set.
   */
  public void setTemperature(Temperature temperature) {
    this.temperature = temperature;
  }

  /**
   * Returns the weather attribute of a location.
   */
  public List<Weather> getWeather() {
    return weather;
  }

  /**
   * Sets the weather attribute of a location.
   * @param weather List of weather attributes
   */
  public void setWeather(List<Weather> weather) {
    this.weather = weather;
  }

  /**
   * Returns the visibility of a location.
   */
  public int getVisibility() {
    return visibility;
  }

  /**
   * Sets the visibility of a location.
   * @param visibility visibility percentage
   */
  public void setVisibility(int visibility) {
    this.visibility = visibility;
  }

  /**
   * Returns the cloud coverage of a location.
   */
  public Clouds getClouds() {
    return clouds;
  }

  /**
   * Sets the cloud coverage of a location.
   * @param clouds cloud attributes
   */
  public void setClouds(Clouds clouds) {
    this.clouds = clouds;
  }

  /**
   * Returns the rain stats of a location.
   */
  public Precipitation getRain() {
    return rain;
  }

  /**
   * Sets the rain stats of a location
   * @param rain Rain stats
   */
  public void setRain(Precipitation rain) {
    this.rain = rain;
  }

  /**
   * Returns the snow stats of a location.
   */
  public Precipitation getSnow() {
    return snow;
  }

  /**
   * Sets the snow stats of a location.
   * @param snow Snow stats
   */
  public void setSnow(Precipitation snow) {
    this.snow = snow;
  }

  public Wind getWind() {
    return wind;
  }

  public void setWind(Wind wind) {
    this.wind = wind;
  }

  /**
   * Class that contains the attributes of temperature from the API.
   */
  public static class Temperature {
    @Expose
    @SerializedName("temp")
    private float current;

    @Expose
    @SerializedName("feels_like")
    private float feelsLike;

    @Expose
    @SerializedName("temp_min")
    private float min;

    @Expose
    @SerializedName("temp_max")
    private float max;

    @Expose
    private float pressure;

    @Expose
    private float humidity;

    /**
     * Returns the current temperature in Kelvin.
     */
    public float getCurrent() {
      return current;
    }

    /**
     * Sets the current temperature.
     * @param current Current temperature
     */
    public void setCurrent(float current) {
      this.current = current;
    }

    /**
     * Returns the temperature that it feels like due to other attributes such as wind.
     */
    public float getFeelsLike() {
      return feelsLike;
    }

    /**
     * Sets the temperature it feels like according to other attributes.
     * @param feelsLike Temperature it feels like.
     */
    public void setFeelsLike(float feelsLike) {
      this.feelsLike = feelsLike;
    }

    /**
     * Returns the minimum temperature forecast.
     */
    public float getMin() {
      return min;
    }

    /**
     * Sets the minimum temperature forecast.
     * @param min Minimum temperature
     */
    public void setMin(float min) {
      this.min = min;
    }

    /**
     * Returns the maximum temperature forecast.
     */
    public float getMax() {
      return max;
    }

    /**
     * Sets the maximum temperature forecast.
     * @param max Maximum temperature
     */
    public void setMax(float max) {
      this.max = max;
    }

    /**
     * Returns the current pressure in the atmosphere for a location.
     */
    public float getPressure() {
      return pressure;
    }

    /**
     * Sets the current pressure in the atmosphere for a location.
     * @param pressure Atmospheric pressure
     */
    public void setPressure(float pressure) {
      this.pressure = pressure;
    }

    /**
     * Returns the humidity of a specified location.
     */
    public float getHumidity() {
      return humidity;
    }

    /**
     * Sets the humidity of a location.
     * @param humidity Humidity
     */
    public void setHumidity(float humidity) {
      this.humidity = humidity;
    }
  }

  /**
   * Class that contains the attributes of weather from the API.
   */
  public static class Weather {
    @Expose
    private int id;

    @Expose
    private String main;

    @Expose
    private String description;

    @Expose
    private String icon;

    /**
     * Returns the id of the weather condition for a location.
     */
    public int getId() {
      return id;
    }

    /**
     * Sets the id of the weather condition for a location.
     * @param id Weather id
     */
    public void setId(int id) {
      this.id = id;
    }

    /**
     * Returns the group of weather parameters (Rain, Snow, Extreme, ect.).
     */
    public String getMain() {
      return main;
    }

    /**
     * Returns the group of weather parameters (Rain, Snow, Extreme, ect.).
     * @param main Weather parameters
     */
    public void setMain(String main) {
      this.main = main;
    }

    /**
     * Returns the weather condition within the group.
     */
    public String getDescription() {
      return description;
    }

    /**
     * Sets the weather condition within the group.
     * @param description Description of weather
     */
    public void setDescription(String description) {
      this.description = description;
    }

    /**
     * Returns the weather icon id
     */
    public String getIcon() {
      return icon;
    }

    /**
     * Sets the weather icon id.
     * @param icon Weather icon id
     */
    public void setIcon(String icon) {
      this.icon = icon;
    }
  }

  /**
   * Class that contains the attributes of wind from the API.
   */
  public static class Wind {
    @Expose
    private float speed;

    @Expose
    @SerializedName("deg")
    private int degrees;

    /**
     * Returns the wind speed.
     */
    public float getSpeed() {
      return speed;
    }

    /**
     * Sets the wind speed.
     * @param speed Wind speed
     */
    public void setSpeed(float speed) {
      this.speed = speed;
    }

    /**
     * Returns the wind direction in degrees (meteorological).
     */
    public int getDegrees() {
      return degrees;
    }

    /**
     * Sets the wind direction in degrees (meteorological).
     * @param degrees Wind direction degrees
     */
    public void setDegrees(int degrees) {
      this.degrees = degrees;
    }
  }

  /**
   * Class that contains the attributes of clouds from the API.
   */
  public static class Clouds {
    @Expose
    @SerializedName("all")
    private float coverage;

    /**
     * Returns the cloudiness percentage.
     */
    public float getCoverage() {
      return coverage;
    }

    /**
     * Sets the cloudiness percentage.
     * @param coverage Cloudiness percentage
     */
    public void setCoverage(float coverage) {
      this.coverage = coverage;
    }
  }

  /**
   * Class that contains the attributes of precipitation from the API.
   */
  public static class Precipitation {
    @Expose
    @SerializedName("1h")
    private float oneHour;

    @Expose
    @SerializedName("3h")
    private float threeHour;

    /**
     * Returns the rain/snow volume for the last 1 hour (mm)
     */
    public float getOneHour() {
      return oneHour;
    }

    /**
     * Sets the rain/snow volume for the last 1 hour (mm)
     * @param oneHour 1 hour
     */
    public void setOneHour(float oneHour) {
      this.oneHour = oneHour;
    }

    /**
     * Returns the rain/snow volume for the last 3 hours (mm)
     */
    public float getThreeHour() {
      return threeHour;
    }

    /**
     * Sets the rain/snow volume for the last 1 hour (mm)
     * @param threeHour 3 hours
     */
    public void setThreeHour(float threeHour) {
      this.threeHour = threeHour;
    }
  }
}
