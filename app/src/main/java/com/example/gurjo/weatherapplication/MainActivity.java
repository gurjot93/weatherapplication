/**
 * The WeatherApplication displays the weather details of a particular city
 * The user can enter any city and the result will be displayed in the application
 * Created by - GURJOT SINGH(gurjot@dal.ca) B00811724
 * Version - 1.0
 * Created Date - 01/11/2018
**/
package com.example.gurjo.weatherapplication;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONObject;
/*Main Class for Weather Application*/
public class MainActivity extends AppCompatActivity {
    /*e_city EditText field to enter the city name */
    EditText e_city;
    /*Button to retrieve the weather */
    Button b_weather;
    /*String to store the city name fetched from the EditText*/
    String s_city_name;
    /* Textviews to display different types of weather details */
    TextView t_city;
    TextView t_temp;
    TextView t_minmax;
    TextView t_weather;
    TextView t_description;
    TextView t_humidity;
    TextView t_humidity_display;
    TextView t_clouds;
    TextView t_clouds_display;
    /* ImageView to display the type of weather in image form*/
    ImageView i_weather;
    /* Relative Layout for Weather application*/
    RelativeLayout r_background;
    /* s_error to store any errors in the program*/
    String s_error=" ";
    /* Object Creation for WeatherDetails Class*/
    WeatherDetails details= new WeatherDetails();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        r_background= (RelativeLayout) findViewById(R.id.background);
        b_weather=(Button) findViewById(R.id.b_getweather);
        e_city = (EditText) findViewById(R.id.e_city);
        t_city = (TextView) findViewById(R.id.t_city);
        t_temp = (TextView) findViewById(R.id.t_temp);
        t_minmax = (TextView) findViewById(R.id.t_minmax);;
        t_weather = (TextView) findViewById(R.id.t_weather);;
        t_description = (TextView) findViewById(R.id.t_description);;
        t_humidity = (TextView) findViewById(R.id.t_humidity);;
        t_humidity_display = (TextView) findViewById(R.id.t_humidity_display);;
        t_clouds = (TextView) findViewById(R.id.t_clouds);;
        t_clouds_display = (TextView) findViewById(R.id.t_clouds_display);;
        i_weather = (ImageView) findViewById(R.id.i_weather);
        r_background.setBackgroundResource(R.color.grey);
        i_weather.setImageIcon(null);
        /*intializing the Weather application by displaying the weather of a default city =Halifax City */
        findweather("Halifax");
        /* Listener for button b_weather to fetch the weather details*/
        b_weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* Fetching the city name from the EditText*/
                s_city_name = e_city.getText().toString();
                /* Calling the function to retrieve the results*/
                findweather(s_city_name);
            }
        });
    }
    /* function to fetch and display weather
    * JSON Referred from - https://mobilesiri.com/json-parsing-in-android-using-android-studio/
    *  Weather API Referred from - https://openweathermap.org/current
    */
    public void findweather(String city_entered)
    {
        /* Openeweathermap api is used to fetch the details */
        String url = "http://api.openweathermap.org/data/2.5/weather?q="+city_entered+"&units=metric&appid=6e195c5fd0a49edc53f31af6e6252ac5";
        /*Creating a JsonObjectRequest to fetch the details from the api */
        JsonObjectRequest req = new JsonObjectRequest(
                Request.Method.GET, url,null,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response){
                        try{
                            /*Creating JSONObject to fetch the weather details */
                            JSONObject main_object = response.getJSONObject("main");
                            /*Creating JSONArray to fetch weather details from an array in JSON file*/
                            JSONArray array = response.getJSONArray("weather");
                            JSONObject object = array.getJSONObject(0);
                            /*Fetching all details from the JSON and storing them as Strings */
                            String temp = String.valueOf(main_object.getDouble("temp"));
                            String description = object.getString("description");
                            String description_breif = object.getString("main");
                            String city_details = object.getString("icon");
                            String city = response.getString("name");
                            String temp_min = String.valueOf(main_object.getDouble("temp_min"));
                            String temp_max = String.valueOf(main_object.getDouble("temp_max"));
                            String humidity = String.valueOf(main_object.getDouble("humidity"));
                            JSONObject country_object = response.getJSONObject("sys");
                            String country = String.valueOf(country_object.getString("country"));
                            JSONObject cloud_object = response.getJSONObject("clouds");
                            String clouds = String.valueOf(cloud_object.getDouble("all"));
                            /*formatting the values fetched by calling remove_decimal() function*/
                            int temperature = remove_decimals(temp);
                            int temperature_min = remove_decimals(temp_min);
                            int temperature_max = remove_decimals(temp_max);
                            int v_humidity = remove_decimals(humidity);
                            int v_clouds = remove_decimals(clouds);
                            /* Storing the fetched results in WeatherDetails Class*/
                            details.set_City(city+", "+country);
                            details.set_Temperature(String.valueOf(temperature)+"°C");
                            details.set_MinMax_Temperature("Min. "+String.valueOf(temperature_min)+"°C  Max. "+String.valueOf(temperature_max)+"°C");
                            details.set_Description(description);
                            details.set_Weather(description_breif);
                            details.set_Humidity(String.valueOf(v_humidity)+"%");
                            details.set_Clouds(String.valueOf(v_clouds)+"%");
                            details.set_HumidityDisplay("Humidity");
                            details.set_CloudsDisplay("Clouds");
                            /* This will display day and night images for different cities */
                            if(city_details.contains("d"))
                            {
                                r_background.setBackgroundResource(R.drawable.day);
                            }
                            else if(city_details.contains("n"))
                            {
                                r_background.setBackgroundResource(R.drawable.night);
                            }
                            else
                            {
                                r_background.setBackgroundResource(R.color.white);
                            }
                            /*Picasso will fetch the weather image from the api and display in the application
                            * Referred from - https://github.com/codepath/android_guides/wiki/Displaying-Images-with-the-Picasso-Library
                            * */
                            Picasso.with(getApplicationContext()).load("http://openweathermap.org/img/w/"+city_details+".png").into(i_weather);
                            /*This will call display_results() function that will display the results in the application*/
                            display_results();
                        }
                        /*Exceptions */
                        catch (Exception e){
                            e.printStackTrace();
                            s_error="Error Retriving Data!";
                            /*This will set previously fetched values to null*/
                            setNull();
                            }
                    }
                }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError e){
                /* main exception function of the application */
                e.printStackTrace();
                /* This will handle exception if there is issue in network connectivity*/
                if(e.toString().contains("Unable to resolve host"))
                {
                    s_error="Network Connection Not Available!";

                }
                /* This will handle exception if the city provided is invalid*/
                else if(e.toString().equals("com.android.volley.ClientError"))
                {
                    s_error="Please provide a Correct City Name!";
                }
                else
                {
                    s_error="Error Retriving Data!";
                }
                /*This will set previously fetched values to null*/
                setNull();
            }
        }
        );
        /* creating volley request*/
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(req);
    }
    /*This function will fetch the weather details from WeatherDetails Class and display the result in the application*/
    public void display_results()
    {
        t_city.setText(details.get_City());
        t_temp.setText(details.get_Temperature());
        t_minmax.setText(details.get_MinMax_Temperature());
        t_description.setText(details.get_Description());
        t_weather.setText(details.get_Weather());
        t_humidity.setText(details.get_Humidity());
        t_clouds.setText(details.get_Clouds());
        t_humidity_display.setText(details.get_HumidityDisplay());
        t_clouds_display.setText(details.get_CloudsDisplay());
    }
    /* function to set previously fetched values to null*/
    public void setNull()
    {
        details.set_City(s_error);
        details.set_Temperature(" ");
        details.set_MinMax_Temperature(" ");
        details.set_Description(" ");
        details.set_Weather(" ");
        details.set_Humidity(" ");
        details.set_Clouds(" ");
        details.set_HumidityDisplay(" ");
        details.set_CloudsDisplay(" ");
        s_error=" ";
        i_weather.setImageIcon(null);
        r_background.setBackgroundResource(R.color.grey);
        display_results();
    }
    /*function to remove decimals from the input fetched from weather api */
    public int remove_decimals(String input)
    {
        double temp = Double.parseDouble(input);
        temp=Math.round(temp);
        int final_result = (int)temp;
        return final_result;
    }
}
