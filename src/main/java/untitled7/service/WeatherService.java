package untitled7.service;


import org.springframework.stereotype.Service;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@Service
public class WeatherService {



    public WeatherService getWeather() throws IOException, ParserConfigurationException, SAXException {

        System.out.println("GO !!!!");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder() ;

        Document doc =  builder.parse("http://xml.weather.co.ua/1.2/city/?country=804");

        doc.getDocumentElement().normalize();
        NodeList nodes = doc.getElementsByTagName("city");

        for (int i = 0; i < nodes.getLength(); i++) {
            Node nNode = nodes.item(i);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                System.out.println("ID : " + eElement.getAttribute("id"));
            }
        }




        WeatherService weather = new WeatherService();

        return weather;
    }


}
