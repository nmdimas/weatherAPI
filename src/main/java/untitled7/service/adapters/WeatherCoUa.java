package untitled7.service.adapters;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import untitled7.enumclass.AdapterEnum;
import untitled7.model.City;
import untitled7.model.Country;
import untitled7.model.weather.OneDay;
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

    public Weather getWeather(Integer idCity) throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc =  builder.parse("http://xml.weather.co.ua/1.2/forecast/"+idCity+"?day=1");
        doc.getDocumentElement().normalize();
        NodeList nodes = doc.getElementsByTagName("day");

        Weather weather = new Weather();
        List<OneDay> dayList = new ArrayList<OneDay>();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node nNode = nodes.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                if (eElement.getAttribute("date") == null || eElement.getAttribute("date")=="")continue;
                OneDay oneDay = new OneDay();

                Element tempElement = (Element)eElement.getElementsByTagName("t").item(0);
                String tempMin = tempElement.getElementsByTagName("min").item(0).getTextContent();
                String tempMax = tempElement.getElementsByTagName("max").item(0).getTextContent();

//                Element maxTemp = (Element)temp.item(0);
//                oneDay.setT(maxTemp.getTextContent());

                oneDay.setP(eElement.getElementsByTagName("p").item(0).getTextContent());
                dayList.add(oneDay);
            }
        }

        weather.setIdCity(idCity);
        weather.setList(dayList);
        return weather;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<City> getCities(Country country) throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder() ;
        Document doc =  builder.parse("http://xml.weather.co.ua/1.2/city/?country=" + country.getIdCountryAdapter());
        doc.getDocumentElement().normalize();
        NodeList nodes = doc.getElementsByTagName("city");
        List<City> cityList = new ArrayList<City>();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node nNode = nodes.item(i);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                if (eElement.getAttribute("id") == null || eElement.getAttribute("id")=="")continue;
                City city = new City();
                city.setAdpterIdCity(Integer.parseInt(eElement.getAttribute("id")));
                city.setName(eElement.getElementsByTagName("name").item(0).getTextContent());
                city.setName_en(eElement.getElementsByTagName("name_en").item(0).getTextContent());
                city.setCountry(country);
                city.setNameAdapter(getNameAdapter());
                city.setRegion(eElement.getElementsByTagName("region").item(0).getTextContent());

                cityList.add(city);
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
                country.setIdCountryAdapter(Integer.parseInt(eElement.getAttribute("id")));
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



