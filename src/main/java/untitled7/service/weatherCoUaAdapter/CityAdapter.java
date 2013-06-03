package untitled7.service.weatherCoUaAdapter;


import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import untitled7.model.City;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CityAdapter {

    public List<City> getCities(Integer country) throws ParserConfigurationException, IOException, SAXException {



        System.out.println("GO !!!!");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder() ;
        Document doc =  builder.parse("http://xml.weather.co.ua/1.2/city/?country=" + country);
        doc.getDocumentElement().normalize();
        NodeList nodes = doc.getElementsByTagName("city");

        List<City> cityList = new ArrayList<City>();

        for (int i = 0; i < nodes.getLength(); i++) {
            Node nNode = nodes.item(i);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                City city = new City();
                city.setId(Long.parseLong(eElement.getAttribute("id")));
                city.setName(eElement.getElementsByTagName("name").item(0).getTextContent());
                city.setName_en(eElement.getElementsByTagName("name_en").item(0).getTextContent());
               // city.setCountry(eElement.getElementsByTagName("country").item(0).getTextContent());
                city.setRegion(eElement.getElementsByTagName("region").item(0).getTextContent());

               cityList.add(city);
                System.out.println("ID : " + eElement.getAttribute("id"));
            }
        }

        return cityList;
    }

}
