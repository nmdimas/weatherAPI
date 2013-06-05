package untitled7.dao;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import untitled7.model.City;
import untitled7.model.Country;
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

    public List<City> getCities(Country country){
        return entityManager.createQuery("select p from City p where p.country.id = :id", City.class).setParameter("id",country.getId()).getResultList();
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


    public List<Country> getCountries(){
        return entityManager.createQuery("select p from Country p").getResultList();
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

    public Country getCountry(Integer idCounty){

        return entityManager.createQuery("select p from Country p where p.idCityFromAdapter = :id", Country.class).setParameter("id", idCounty).getSingleResult();
    }


}
