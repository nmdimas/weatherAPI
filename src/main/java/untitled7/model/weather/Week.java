package untitled7.model.weather;

import javax.persistence.Column;
import javax.persistence.Id;


public class Week {

    @Id
    public Long id;


    @Column
    public String nameDay;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameDay() {
        return nameDay;
    }

    public void setNameDay(String nameDay) {
        this.nameDay = nameDay;
    }
}
