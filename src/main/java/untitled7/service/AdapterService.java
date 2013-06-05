package untitled7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import untitled7.dao.WeatherDAO;
import untitled7.model.City;
import untitled7.model.weather.Weather;
import untitled7.service.adapters.WeatherCoUa;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

@Service
public class AdapterService  {

    @Autowired
    WeatherDAO weatherDAO;

    public Adapter adapter;
    AdapterService(){
    }

    AdapterService(String name){
        if (name.equals("WeatherCoUa")) {
            adapter = new WeatherCoUa();
        }
    }


    public Weather getWeather(Integer idCity){
        Weather weather = weatherDAO.find(idCity);
        if (weather == null){
            weather = adapter.getWeather(idCity);
            saveWeather(weather);
        }
        return weather;
    }

    public void saveWeather(Weather weather){

    }

    public List<City> getCities(Integer idCountry) throws ParserConfigurationException, SAXException, IOException {
        List<City> cities = weatherDAO.getCities();
        if(cities==null){
            cities =  adapter.getCities(idCountry);
            saveCities(cities);
        }
        return cities;
    }

    public void saveCities(List<City> cities){
        for (City iteratorCity: cities){
            weatherDAO.save(iteratorCity);
        }
    }

}
