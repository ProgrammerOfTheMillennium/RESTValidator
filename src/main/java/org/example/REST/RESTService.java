package org.example.REST;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONException;
import kong.unirest.json.JSONObject;


//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;




/**
 * Hello REST API!
 * lat lon offset name abbr dst url 2days season moon_code.equal(moon_text)
 * https://yandex.ru/dev/weather/doc/dg/concepts/about-docpage/
 *
 * Key #1
 * 70f71ecd-e0a6-4876-bde0-7f4368f85e12
 * https://yandex.ru/pogoda/moscow
 * GET https://api.weather.yandex.ru/v1/informers?lat=55.75396&lon=37.620393
 *
 */
public class RESTService
{
    private JSONObject fullJSON;
    private JSONObject info;
    private JSONObject tzinfo;
    private JSONArray forecast;
    private JSONObject fact;

    private Map<String, String> headers;
    private Map<String, Object> params;

    public JSONObject shouldReturnFullJSONObject() throws UnirestException {
        headers = new HashMap<String, String>();
        headers.put("accept", "application/json");
        headers.put("X-Yandex-API-Key", "70f71ecd-e0a6-4876-bde0-7f4368f85e12");

        params = new HashMap<String, Object>();
        params.put("lat", "55.755814");
        params.put("lon", "37.617635");
        params.put("lang", "ru_RU");
        params.put("limit", "2");
        params.put("hours", "false");
        params.put("extra", "false");

        try {
            HttpResponse<JsonNode> jsonResponse = Unirest.get("https://api.weather.yandex.ru/v1/forecast?")
                    .headers(headers)
                    .queryString(params)
                    .asJson();

            fullJSON = jsonResponse.getBody().getObject();
            info = fullJSON.getJSONObject("info");
            tzinfo = info.getJSONObject("tzinfo");
            forecast = fullJSON.getJSONArray("forecasts");
            fact = fullJSON.getJSONObject("fact");

//            System.out.println("Yandex Pogoda REST API:");
//            System.out.println(fullJSON);

        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        return fullJSON;
    }

    public JSONObject getFullJSON() {
        return fullJSON;
    }
    public JSONObject getInfo() {
        return info;
    }
    public JSONObject getTzInfo() {
        return tzinfo;
    }
    public JSONArray getForecast() {
        return forecast;
    }
    public JSONObject getFact() {
        return fact;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }
    public Map<String, Object> getParams() {
        return params;
    }
}
