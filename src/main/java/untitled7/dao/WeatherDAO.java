package untitled7.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import untitled7.enumclass.AdapterEnum;
import untitled7.model.City;
import untitled7.model.Country;
import untitled7.model.weather.Weather;

import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class WeatherDAO {

    @Autowired
    MongoTemplate mongoTemplate;

    @PersistenceContext
    private EntityManager entityManager;


    public List<City> getCities(Country country){
        return entityManager.createQuery("select p from City p where p.country.idCountryAdapter = :id", City.class)
                .setParameter("id", country.getIdCountryAdapter())
                .getResultList();
    }

    public List<Country> getCountries(){
        return entityManager.createQuery("select p from Country p").getResultList();
    }


    public Weather findWeather(Integer idCity) {
        Query query = new Query(Criteria.where("idCity").is(idCity));
        return mongoTemplate.findOne(query,Weather.class);
    }

    public City findCity(Integer idCity,AdapterEnum nameAdapter){

        return entityManager.createQuery("select p from City p where p.adpterIdCity = :id and p.nameAdapter= :nameAdapter", City.class)
                .setParameter("id", idCity)
                .setParameter("nameAdapter", nameAdapter)
                .getSingleResult();
    }

    public Country findCountry(Integer idCountry, AdapterEnum nameAdapter){
        return entityManager.createQuery("SELECT c FROM Country c WHERE c.idCountryAdapter = :id AND c.nameAdapter = :nameAdapter", Country.class)
                .setParameter("id", idCountry)
                .setParameter("nameAdapter", nameAdapter)
                .getSingleResult();
    }


    @Transactional
    public City saveCity(City city) {
        try {
            if (city.getId() == null) {
                entityManager.persist(city);
                return city;
            } else {
                return entityManager.merge(city);
            }
        }
        catch (NonUniqueResultException e){
           return null;
        }
    }

    @Transactional
    public Country saveCountry(Country country) {

            if (country.getId() == null) {
                entityManager.persist(country);
                return country;
            } else {
                return entityManager.merge(country);
            }

    }

    @Transactional
    public Weather saveWeather(Weather weather) {
        mongoTemplate.save(weather);
        return weather;
//        if (weather.getId() == null) {
//            entityManager.persist(weather);
//            return weather;
//        } else {
//            return entityManager.merge(weather);
//        }
    }




}
