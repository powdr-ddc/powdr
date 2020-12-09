package edu.cnm.deepdive.powdr.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.net.URL;
import java.util.Date;

public class WeatherResponse {

  @Expose
  private Astronomy astronomy;

  @Expose
  private Top top;

  @Expose
  private Mid mid;

  @Expose
  private Bottom bottom;

  @Expose
  private Hourly hourly;

  @Expose
  @SerializedName("chanceofsnow")
  private float chanceOfSnow;

  @Expose
  private Date date;

  @Expose
  @SerializedName("totalSnowfall_cm")
  private float totalSnowfall;

  public Astronomy getAstronomy() {
    return astronomy;
  }

  public void setAstronomy(Astronomy astronomy) {
    this.astronomy = astronomy;
  }

  public Top getTop() {
    return top;
  }

  public void setTop(Top top) {
    this.top = top;
  }

  public Mid getMid() {
    return mid;
  }

  public void setMid(Mid mid) {
    this.mid = mid;
  }

  public Bottom getBottom() {
    return bottom;
  }

  public void setBottom(Bottom bottom) {
    this.bottom = bottom;
  }

  public Hourly getHourly() {
    return hourly;
  }

  public void setHourly(Hourly hourly) {
    this.hourly = hourly;
  }

  public float getChanceOfSnow() {
    return chanceOfSnow;
  }

  public void setChanceOfSnow(float chanceOfSnow) {
    this.chanceOfSnow = chanceOfSnow;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public float getTotalSnowfall() {
    return totalSnowfall;
  }

  public void setTotalSnowfall(float totalSnowfall) {
    this.totalSnowfall = totalSnowfall;
  }

  public static class Astronomy {

    @Expose
    private Date sunrise;

    @Expose
    private Date sunset;

    public Date getSunrise() {
      return sunrise;
    }

    public void setSunrise(Date sunrise) {
      this.sunrise = sunrise;
    }

    public Date getSunset() {
      return sunset;
    }

    public void setSunset(Date sunset) {
      this.sunset = sunset;
    }
  }

  public static class Hourly {

    @Expose
    private Date time;

    @Expose
    private int tempC;

    @Expose
    private int tempF;

    @Expose
    @SerializedName("windspeedMiles")
    private int windSpeedMiles;

    @Expose
    @SerializedName("windspeedKmph")
    private int windSpeedKMph;

    @Expose
    @SerializedName("winddirDegree")
    private int windDirDegree;

    @Expose
    @SerializedName("winddirection")
    private String windDirection;

    @Expose
    private int weatherCode;

    @Expose
    private String weatherDesc;

    @Expose
    private URL weatherIconUrl;

    @Expose
    private float precipMM;

    @Expose
    private float precipInches;

    @Expose
    private int humidity;

    @Expose
    private int visibility;

    @Expose
    @SerializedName("cloudcover")
    private int cloudCover;

    @Expose
    @SerializedName("chanceofrain")
    private int chanceOfRain;

    @Expose
    @SerializedName("chanceofwindy")
    private int chanceOfWindy;

    @Expose
    @SerializedName("chanceofovercast")
    private int chanceOfOvercast;

    @Expose
    @SerializedName("chanceofsunny")
    private int chanceOfSunny;

    @Expose
    @SerializedName("chanceoffog")
    private int chanceOfFog;

    @Expose
    @SerializedName("chanceofsnow")
    private int chanceOfSnow;

    @Expose
    @SerializedName("chanceoflightning")
    private int chanceOfLightning;

    @Expose
    @SerializedName("snowfall_cm")
    private int snowfallCm;

    public Date getTime() {
      return time;
    }

    public void setTime(Date time) {
      this.time = time;
    }

    public int getTempC() {
      return tempC;
    }

    public void setTempC(int tempC) {
      this.tempC = tempC;
    }

    public int getTempF() {
      return tempF;
    }

    public void setTempF(int tempF) {
      this.tempF = tempF;
    }

    public int getWindSpeedMiles() {
      return windSpeedMiles;
    }

    public void setWindSpeedMiles(int windSpeedMiles) {
      this.windSpeedMiles = windSpeedMiles;
    }

    public int getWindSpeedKMph() {
      return windSpeedKMph;
    }

    public void setWindSpeedKMph(int windSpeedKMph) {
      this.windSpeedKMph = windSpeedKMph;
    }

    public int getWindDirDegree() {
      return windDirDegree;
    }

    public void setWindDirDegree(int windDirDegree) {
      this.windDirDegree = windDirDegree;
    }

    public String getWindDirection() {
      return windDirection;
    }

    public void setWindDirection(String windDirection) {
      this.windDirection = windDirection;
    }

    public int getWeatherCode() {
      return weatherCode;
    }

    public void setWeatherCode(int weatherCode) {
      this.weatherCode = weatherCode;
    }

    public String getWeatherDesc() {
      return weatherDesc;
    }

    public void setWeatherDesc(String weatherDesc) {
      this.weatherDesc = weatherDesc;
    }

    public URL getWeatherIconUrl() {
      return weatherIconUrl;
    }

    public void setWeatherIconUrl(URL weatherIconUrl) {
      this.weatherIconUrl = weatherIconUrl;
    }

    public float getPrecipMM() {
      return precipMM;
    }

    public void setPrecipMM(float precipMM) {
      this.precipMM = precipMM;
    }

    public float getPrecipInches() {
      return precipInches;
    }

    public void setPrecipInches(float precipInches) {
      this.precipInches = precipInches;
    }

    public int getHumidity() {
      return humidity;
    }

    public void setHumidity(int humidity) {
      this.humidity = humidity;
    }

    public int getVisibility() {
      return visibility;
    }

    public void setVisibility(int visibility) {
      this.visibility = visibility;
    }

    public int getCloudCover() {
      return cloudCover;
    }

    public void setCloudCover(int cloudCover) {
      this.cloudCover = cloudCover;
    }

    public int getChanceOfRain() {
      return chanceOfRain;
    }

    public void setChanceOfRain(int chanceOfRain) {
      this.chanceOfRain = chanceOfRain;
    }

    public int getChanceOfWindy() {
      return chanceOfWindy;
    }

    public void setChanceOfWindy(int chanceOfWindy) {
      this.chanceOfWindy = chanceOfWindy;
    }

    public int getChanceOfOvercast() {
      return chanceOfOvercast;
    }

    public void setChanceOfOvercast(int chanceOfOvercast) {
      this.chanceOfOvercast = chanceOfOvercast;
    }

    public int getChanceOfSunny() {
      return chanceOfSunny;
    }

    public void setChanceOfSunny(int chanceOfSunny) {
      this.chanceOfSunny = chanceOfSunny;
    }

    public int getChanceOfFog() {
      return chanceOfFog;
    }

    public void setChanceOfFog(int chanceOfFog) {
      this.chanceOfFog = chanceOfFog;
    }

    public int getChanceOfSnow() {
      return chanceOfSnow;
    }

    public void setChanceOfSnow(int chanceOfSnow) {
      this.chanceOfSnow = chanceOfSnow;
    }

    public int getChanceOfLightning() {
      return chanceOfLightning;
    }

    public void setChanceOfLightning(int chanceOfLightning) {
      this.chanceOfLightning = chanceOfLightning;
    }

    public int getSnowfallCm() {
      return snowfallCm;
    }

    public void setSnowfallCm(int snowfallCm) {
      this.snowfallCm = snowfallCm;
    }
  }

  public static class Weather {

    @Expose
    private Date date;

    @Expose
    @SerializedName("chanceofsnow")
    private int chanceOfSnow;

    @Expose
    private float totalSnowfall_cm;

    @Expose
    @SerializedName("maxtempC")
    private int maxTempC;

    @Expose
    @SerializedName("mintempC")
    private int minTempC;

    @Expose
    private Hourly hourly;

    public Date getDate() {
      return date;
    }

    public void setDate(Date date) {
      this.date = date;
    }

    public int getChanceOfSnow() {
      return chanceOfSnow;
    }

    public void setChanceOfSnow(int chanceOfSnow) {
      this.chanceOfSnow = chanceOfSnow;
    }

    public float getTotalSnowfall_cm() {
      return totalSnowfall_cm;
    }

    public void setTotalSnowfall_cm(float totalSnowfall_cm) {
      this.totalSnowfall_cm = totalSnowfall_cm;
    }

    public int getMaxTempC() {
      return maxTempC;
    }

    public void setMaxTempC(int maxTempC) {
      this.maxTempC = maxTempC;
    }

    public int getMinTempC() {
      return minTempC;
    }

    public void setMinTempC(int minTempC) {
      this.minTempC = minTempC;
    }

    public Hourly getHourly() {
      return hourly;
    }

    public void setHourly(Hourly hourly) {
      this.hourly = hourly;
    }
  }

  public static class Top {

  }

  public static class Mid {

  }

  public static class Bottom {

    @Expose
    @SerializedName("maxtempC")
    private int maxTempC;

    @Expose
    @SerializedName("maxtempF")
    private int maxTempF;

    @Expose
    @SerializedName("mintempC")
    private int minTempC;

    @Expose
    @SerializedName("mintempF")
    private int minTempF;

    public int getMaxTempC() {
      return maxTempC;
    }

    public void setMaxTempC(int maxTempC) {
      this.maxTempC = maxTempC;
    }

    public int getMaxTempF() {
      return maxTempF;
    }

    public void setMaxTempF(int maxTempF) {
      this.maxTempF = maxTempF;
    }

    public int getMinTempC() {
      return minTempC;
    }

    public void setMinTempC(int minTempC) {
      this.minTempC = minTempC;
    }

    public int getMinTempF() {
      return minTempF;
    }

    public void setMinTempF(int minTempF) {
      this.minTempF = minTempF;
    }
  }
}
