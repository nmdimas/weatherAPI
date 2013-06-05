package untitled7.dao;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import untitled7.model.City;
import untitled7.model.Person;
import untitled7.model.weather.Weather;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class WeatherDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public Weather find(Integer idCity) {
        return entityManager.find(Weather.class, idCity);
    }

    public List<City> getCities(){
        return entityManager.createQuery("select p from City p").getResultList();
    }


    @Transactional
    public City save(City city) {
        if (city.getId() == null) {
            entityManager.persist(city);
            return city;
        } else {
            return entityManager.merge(city);
        }
    }





}
