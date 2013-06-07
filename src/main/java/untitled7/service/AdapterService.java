package untitled7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import untitled7.dao.WeatherDAO;
import untitled7.model.City;
import untitled7.model.Country;
import untitled7.model.weather.Weather;
import untitled7.service.adapters.WeatherCoUa;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.LinkedList;
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


    public Weather getWeather(City city) throws ParserConfigurationException, SAXException, IOException {
        Weather weather = weatherDAO.findWeather(city.getAdpterIdCity());
        if(weather==null){
            weather = adapter.getWeather(city.getAdpterIdCity());
            saveWeather(weather);
        }
        return weather;
    }

    public void saveWeather(Weather weather){
        weatherDAO.saveWeather(weather);
    }

    public List<City> getCities(Country country) throws ParserConfigurationException, SAXException, IOException {
        List<City> cities = weatherDAO.getCities(country);
        if(cities==null || cities.size()<1){
           install();
        }
        return cities;
    }
    public City findCity(Integer idCity, Integer idCountry) throws IOException, SAXException, ParserConfigurationException {
        City city = weatherDAO.findCity(idCity,adapter.getNameAdapter());
        if(city==null){
            install();
            city = weatherDAO.findCity(idCity,adapter.getNameAdapter());
            if(city==null || city.getId() == null){
                return null;
            }
        }
        return city;
    }

    public void saveCities(List<City> cities){
        for (City iteratorCity: cities){
            weatherDAO.saveCity(iteratorCity);
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
        try {
            Country country = weatherDAO.findCountry(idCountry, adapter.getNameAdapter());
            return country;
        }   catch (NoResultException e){
            List<Country> countries =  adapter.getCountry();
            saveCountries(countries);
            Country country = weatherDAO.findCountry(idCountry, adapter.getNameAdapter());
            return country;
        }
    }

    public List<Country> saveCountries(List<Country> countries){
        List<Country> countrySaved = new LinkedList<Country>();
        for(Country country: countries){
            try {
                countrySaved.add(weatherDAO.saveCountry(country));
            }   catch (PersistenceException e){
                countrySaved.add(weatherDAO.findCountry(country.getIdCountryAdapter(),country.getNameAdapter()));
            }

        }
        return countrySaved;
    }

    public void install() throws ParserConfigurationException, SAXException, IOException {
        List<Country> countrySaved = saveCountries(adapter.getCountry());
        for(Country country: countrySaved){
            List<City> cityList = adapter.getCities(country);
            saveCities(cityList);
        }
    }
}
