package untitled7.service;

import org.xml.sax.SAXException;
import untitled7.model.Country;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;


public interface CountryAdapterInterface {

    public Country getCountry(Integer id);
    public List<Country> getListCounties() throws ParserConfigurationException, IOException, SAXException;

}
