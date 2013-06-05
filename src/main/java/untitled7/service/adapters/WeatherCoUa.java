package untitled7.service.adapters;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import untitled7.enumclass.AdapterEnum;
import untitled7.model.City;
import untitled7.model.Country;
import untitled7.model.weather.Weather;
import untitled7.service.Adapter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class WeatherCoUa implements Adapter {

    public AdapterEnum getNameAdapter(){
        return AdapterEnum.weatherCoUa;
    }

    public Weather getWeather(Integer idCity) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<City> getCities(Country country) throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder() ;
        Document doc =  builder.parse("http://xml.weather.co.ua/1.2/city/?country=" + country.getId());
        doc.getDocumentElement().normalize();
        NodeList nodes = doc.getElementsByTagName("city");
        List<City> cityList = new ArrayList<City>();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node nNode = nodes.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                if (eElement.getAttribute("id") == null || eElement.getAttribute("id")=="")continue;
                City city = new City();
                city.setId(Long.parseLong(eElement.getAttribute("id")));
                city.setName(eElement.getElementsByTagName("name").item(0).getTextContent());
                city.setName_en(eElement.getElementsByTagName("name_en").item(0).getTextContent());
                city.setCountry(country);
                city.setRegion(eElement.getElementsByTagName("region").item(0).getTextContent());

                cityList.add(city);
                System.out.println("ID : " + eElement.getAttribute("id"));
            }
        }

        return cityList;
    }

    public List<Country> getCountry() throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder() ;

        Document doc =  builder.parse("http://xml.weather.co.ua/1.2/country/");
        doc.getDocumentElement().normalize();
        NodeList nodes = doc.getElementsByTagName("country");
        List<Country> countryList = new ArrayList<Country>();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node nNode = nodes.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                if (eElement.getAttribute("id") == null || eElement.getAttribute("id")=="")continue;
                Country country = new Country();
                country.setIdCityFromAdapter(Integer.parseInt(eElement.getAttribute("id")));
                country.setIso2(eElement.getElementsByTagName("ISO2").item(0).getTextContent());
                country.setIso3(eElement.getElementsByTagName("ISO3").item(0).getTextContent());
                country.setName(eElement.getElementsByTagName("name").item(0).getTextContent());
                country.setName_en(eElement.getElementsByTagName("name_en").item(0).getTextContent());
                country.setNameAdapter(getNameAdapter());
                countryList.add(country);
            }
        }
        return countryList;
    }
}
