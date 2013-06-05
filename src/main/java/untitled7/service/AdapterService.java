package untitled7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import untitled7.dao.WeatherDAO;
import untitled7.model.City;
import untitled7.model.Country;
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

    public void factoryAdapter(String name){
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

    public List<City> getCities(Country country) throws ParserConfigurationException, SAXException, IOException {
        List<City> cities = weatherDAO.getCities(country);
        if(cities==null || cities.size()<1){
            cities =  adapter.getCities(weatherDAO.getCountry(country.getIdCityFromAdapter()));
            saveCities(cities);
        }
        return cities;
    }

    public void saveCities(List<City> cities){
        for (City iteratorCity: cities){
            weatherDAO.save(iteratorCity);
        }
    }


    public List<Country> getCounties() throws ParserConfigurationException, SAXException, IOException {
        List<Country> countries = weatherDAO.getCountries();
        if(countries==null || countries.size() < 1){
            countries = adapter.getCountry();
            saveCountries(countries);
        }
        return countries;
    }
    public Country getCountry(Integer idCountry) throws ParserConfigurationException, SAXException, IOException {
        Country country = weatherDAO.getCountry(idCountry);
        if(country==null || country.getId()==null){
            List<Country> countries =  adapter.getCountry();
            saveCountries(countries);
            country = weatherDAO.getCountry(idCountry);
        }
        return country;
    }

    public void saveCountries(List<Country> countries){
        for(Country country: countries){
            weatherDAO.saveCountry(country);
        }
    }

}
