package untitled7.service;

import org.xml.sax.SAXException;
import untitled7.enumclass.AdapterEnum;
import untitled7.model.City;
import untitled7.model.Country;
import untitled7.model.weather.Weather;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;


public interface Adapter {

    public AdapterEnum getNameAdapter();
    public Weather getWeather(Integer idCity) throws IOException, SAXException, ParserConfigurationException;
    public List<City> getCities(Country country) throws IOException, SAXException, ParserConfigurationException;
    public List<Country> getCountry() throws IOException, SAXException, ParserConfigurationException;

}
