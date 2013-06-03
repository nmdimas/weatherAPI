package untitled7.service.weatherCoUaAdapter;


import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import untitled7.model.Country;
import untitled7.service.CountryAdapterInterface;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CountryAdapter implements CountryAdapterInterface {


    public Country getCountry(Integer id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<Country> getListCounties() throws ParserConfigurationException, IOException, SAXException {
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
                Country country = new Country();
                country.setId(Integer.parseInt(eElement.getAttribute("id")));
                country.setIso2(eElement.getElementsByTagName("ISO2").item(0).getTextContent());
                country.setIso3(eElement.getElementsByTagName("ISO3").item(0).getTextContent());
                country.setIso3(eElement.getElementsByTagName("name").item(0).getTextContent());
                country.setIso3(eElement.getElementsByTagName("name_en").item(0).getTextContent());

                countryList.add(country);
            }
        }


        return countryList;
    }
}
