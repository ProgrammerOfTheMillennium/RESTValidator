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
import kong.unirest.json.JSONObject;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;



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
    @Test
    public void shouldReturnStatusOkay() throws UnirestException {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("accept", "application/json");
        headers.put("X-Yandex-API-Key", "70f71ecd-e0a6-4876-bde0-7f4368f85e12");

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("lat", "55.755814");
        params.put("lon", "37.617635");
        params.put("lang", "ru_RU");
        params.put("limit", "2");
        params.put("hours", "false");
        params.put("extra", "false");
//        params.put("url", "https://yandex.ru/pogoda/moscow");


        HttpResponse<JsonNode> jsonResponse = Unirest.get("https://api.weather.yandex.ru/v1/forecast?")
                .headers(headers)
                .queryString(params)
                .asJson();

//        1. lat - широта (в градусах) соответствует заданной вами;
//        2. lon - долгота (в градусах) соответствует заданной вами;
//        3. offset - проверьте часовой пояс;
//        4. name - проверьте название часового пояса;
//        5. abbr - проверьте сокращенное название часового пояса;
//        6. dst - проверьте признак летнего времени;
//        7. url - проверьте страницу населенного пункта, убедитесь что ссылка правильная, что
//        широта и долгота внутри ссылки указаны верные;
//        8. Проверьте длину прогноза, убедитесь что прогноз действительно на два дня;
//        9. season - проверьте сезон;
//        10. Проверьте, что код фазы луны на второй день moon_code
//        соответсвует текстовому коду moon_text


        JSONObject jsonObject = jsonResponse.getBody().getObject();
        JSONObject info = jsonObject.getJSONObject("info");
        JSONObject tzinfo = info.getJSONObject("tzinfo");
        JSONArray forecast = jsonObject.getJSONArray("forecasts");
        JSONObject fact = jsonObject.getJSONObject("fact");

        assertNotNull(jsonResponse.getBody());
        assertEquals(200, jsonResponse.getStatus());

        System.out.println(jsonObject);
        System.out.println(info);

        System.out.println("\n");
        System.out.println(info.getString("lat"));
        System.out.println(info.getString("lon"));

        System.out.println(tzinfo.getString("offset"));
        System.out.println(tzinfo.getString("name"));
        System.out.println(tzinfo.getString("abbr"));
        System.out.println(tzinfo.getString("dst"));

        System.out.println(info.getString("url"));
        System.out.println(forecast.length());
        System.out.println(fact.getString("season"));

        System.out.println(forecast.get(1).toString());
    }

}
