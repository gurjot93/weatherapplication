/**
 * Created by - GURJOT SINGH(gurjot@dal.ca) B00811724
 * Version - 1.0
 * Created Date - 02/11/2018
 **/
package com.example.gurjo.weatherapplication;
/*WeatherDetails class to store the fetched weather details information*/
public class WeatherDetails {
    String s_city;
    String s_temp;
    String s_minmax;
    String s_weather;
    String s_description;
    String s_humidity;
    String s_clouds;
    String s_humidity_display;
    String s_clouds_display;
    /*Setter Methods to Store the weather details for the city*/
    public void set_City(String s_city)
    {
        this.s_city=s_city;
    }
    public void set_Temperature(String s_temp)
    {
        this.s_temp=s_temp;
    }
    public void set_MinMax_Temperature(String s_minmax)
    {
        this.s_minmax=s_minmax;
    }
    public void set_Weather(String s_weather)
    {
        this.s_weather=s_weather;
    }
    public void set_Description(String s_description)
    {
        this.s_description=s_description;
    }
    public void set_Humidity(String s_humidity)
    {
        this.s_humidity=s_humidity;
    }
    public void set_Clouds(String s_clouds)
    {
        this.s_clouds=s_clouds;
    }
    public void set_HumidityDisplay(String s_humidity_display)
    {
        this.s_humidity_display=s_humidity_display;
    }
    public void set_CloudsDisplay(String s_clouds_display)
    {
        this.s_clouds_display=s_clouds_display;
    }
    /*Getter Methods to print the weather details for the city*/
    public String get_City()
    {
        return this.s_city;
    }
    public String get_Temperature()
    {
        return this.s_temp;
    }
    public String get_MinMax_Temperature()
    {
        return this.s_minmax;
    }
    public String get_Weather()
    {
        return this.s_weather;
    }
    public String get_Description()
    {
        return this.s_description;
    }
    public String get_Humidity()
    {
        return this.s_humidity;
    }
    public String get_Clouds()
    {
        return this.s_clouds;
    }
    public String get_HumidityDisplay()
    {
        return this.s_humidity_display;
    }
    public String get_CloudsDisplay()
    {
        return this.s_clouds_display;
    }
}
