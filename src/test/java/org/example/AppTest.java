package org.example;

import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import kong.unirest.json.JSONString;
import org.example.REST.RESTService;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


import java.util.HashMap;
import java.util.Map;


/**
 * Unit test for simple REST App:
 *         Сформируйте проект с тестами, который будет вызывать API Яндекс.Погоды для города
 *         Москва. Прогноз погоды запрашивайте всего на два дня. Получив ответ от API сделайте
 *         следующие проверки:
 *         1. lat - широта (в градусах) соответствует заданной вами;
 *         2. lon - долгота (в градусах) соответствует заданной вами;
 *         3. offset - проверьте часовой пояс;
 *         4. name - проверьте название часового пояса;
 *         5. abbr - проверьте сокращенное название часового пояса;
 *         6. dst - проверьте признак летнего времени;
 *         7. url - проверьте страницу населенного пункта, убедитесь что ссылка правильная, что
 *         широта и долгота внутри ссылки указаны верные;
 *         8. Проверьте длину прогноза, убедитесь что прогноз действительно на два дня;
 *         9. season - проверьте сезон;
 *         10. Напишите логику и проверьте, что код фазы луны на второй день moon_code
 *         соответсвует текстовому коду moon_text.
 */
public class AppTest 
{
    @BeforeMethod
    public void setup() {
    }

    @Test
    public void shouldHasCorrectLatitude()
    {
        RESTService rest = new RESTService();
        rest.shouldReturnFullJSONObject();

//        System.out.println(rest.getParams().get("lat"));
        Assert.assertEquals(rest.getParams().get("lat"), rest.getInfo().getString("lat"));
    }

    @Test
    public void shouldHasCorrectLongitude()
    {
        RESTService rest = new RESTService();
        rest.shouldReturnFullJSONObject();

//        System.out.println(rest.getParams().get("lon"));
        Assert.assertEquals(rest.getParams().get("lon"), rest.getInfo().getString("lon"));
    }

    @Test
    public void shouldHasTimeZoneOffset()
    {
        RESTService rest = new RESTService();
        rest.shouldReturnFullJSONObject();

        Assert.assertNotNull(rest.getTzInfo().getInt("offset"));
    }

    @Test
    public void shouldHasTimeZoneName()
    {
        RESTService rest = new RESTService();
        rest.shouldReturnFullJSONObject();

        Assert.assertNotNull(rest.getTzInfo().getString("name"));
    }

    @Test
    public void shouldHasTimeZoneShortcut()
    {
        RESTService rest = new RESTService();
        rest.shouldReturnFullJSONObject();

        Assert.assertNotNull(rest.getTzInfo().getString("abbr"));
    }

    @Test
    public void shouldHasDaySavingTime()
    {
        RESTService rest = new RESTService();
        rest.shouldReturnFullJSONObject();

        Assert.assertNotNull(rest.getTzInfo().getString("dst"));
    }

    @Test
    public void shouldHasCityUrl()
    {
        RESTService rest = new RESTService();
        rest.shouldReturnFullJSONObject();

        Assert.assertNotNull(rest.getInfo().getString("url"));
    }

    @Test
    public void shouldHasForecastForTwoDays()
    {
        RESTService rest = new RESTService();
        rest.shouldReturnFullJSONObject();

        Assert.assertEquals(Integer.parseInt(rest.getParams().get("limit").toString()), rest.getForecast().length());
    }

    @Test
    public void shouldHasSeason()
    {
        RESTService rest = new RESTService();
        rest.shouldReturnFullJSONObject();

        Assert.assertNotNull(rest.getFact().getString("season"));
    }

    @Test
    public void shouldHasMoonPhase()
    {
        RESTService rest = new RESTService();
        rest.shouldReturnFullJSONObject();

        Map<Integer, String> moonPhase = new HashMap<Integer, String>();

        moonPhase.put(0, "full-moon");
        moonPhase.put(1, "decreasing-moon");
        moonPhase.put(2, "decreasing-moon");
        moonPhase.put(3, "decreasing-moon");
        moonPhase.put(4, "last-quarter");
        moonPhase.put(5, "decreasing-moon");
        moonPhase.put(6, "decreasing-moon");
        moonPhase.put(7, "decreasing-moon");
        moonPhase.put(8, "new-moon");
        moonPhase.put(9, "growing-moon");
        moonPhase.put(10, "growing-moon");
        moonPhase.put(11, "growing-moon");
        moonPhase.put(12, "first-quarter");
        moonPhase.put(13, "growing-moon");
        moonPhase.put(14, "growing-moon");
        moonPhase.put(15, "growing-moon");

        JSONObject forecast = new JSONObject(rest.getForecast().get(1).toString());
        Assert.assertEquals(forecast.getString("moon_text"), moonPhase.get(Integer.parseInt(forecast.getString("moon_code"))));
    }






//        System.out.println(forecast.get(1).toString());

}
