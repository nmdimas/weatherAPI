package untitled7.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.xml.sax.SAXException;
import untitled7.dao.PersonDao;
import untitled7.dao.UserDAO;
import untitled7.model.City;
import untitled7.model.Country;
import untitled7.model.Person;
import untitled7.service.AdapterService;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/person/")
public class PersonController {
	
	private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

	@Autowired
	private PersonDao personDao;

    @Autowired
    AdapterService adapterService;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    MongoTemplate mongoTemplate;

	
	@RequestMapping(method=RequestMethod.GET,value="edit")
	public ModelAndView editPerson(@RequestParam(value="id",required=false) Long id) {		
		logger.debug("Received request to edit person id : "+id);				
		ModelAndView mav = new ModelAndView();		
 		mav.setViewName("edit");
 		Person person = null;
 		if (id == null) {
 			person = new Person();
 		} else {
 			person = personDao.find(id);
 		}
 		
 		mav.addObject("person", person);
		return mav;
		
	}
	
	@RequestMapping(method=RequestMethod.POST,value="edit") 
	public String savePerson(@ModelAttribute Person person) {
		logger.debug("Received postback on person "+person);		
		personDao.save(person);
		return "redirect:list";
		
	}
	
	@RequestMapping(method=RequestMethod.GET,value="list")
	public ModelAndView listPeople() throws IOException, ParserConfigurationException, SAXException {
		logger.debug("Received request to list persons");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("list");

        adapterService.factoryAdapter("WeatherCoUa");

        List<Country> countryList = adapterService.getCounties();



//        List<City> cities = adapterService.getCities(802);
//        for (City city: cities){
//            System.out.println(city.getName());
//        }

//
//        Person person = new Person();
//        person.setFirstName("Dimas");
//
//
//        mongoTemplate.save(person);
//        userDAO.getUsers();
		return mav;
	}

    @RequestMapping(method=RequestMethod.GET,value="writetest")
    public String writetest() throws IOException, ParserConfigurationException, SAXException {


//
//        Person person = new Person();
//        person.setFirstName("Dimas");
//
//
//        mongoTemplate.save(person);
//        userDAO.getUsers();

        return "redirect:list";
    }


    @RequestMapping(method=RequestMethod.GET,value="country")
    public ModelAndView country() throws IOException, ParserConfigurationException, SAXException {

        ModelAndView mav = new ModelAndView();


        Country country = adapterService.getCountry(804);

        List<City> cities = adapterService.getCities(country);

//
//        Person person = new Person();
//        person.setFirstName("Dimas");
//
//
//        mongoTemplate.save(person);
//        userDAO.getUsers();

        return mav;
    }

}
