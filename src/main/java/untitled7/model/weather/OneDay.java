package untitled7.model.weather;


import javax.persistence.Column;
import javax.persistence.Id;


public class OneDay {

    @Id
    private Long id;

    @Column
    private Integer tempMin;
    @Column
    private Integer tempMax;

    @Column
    private String humidity;

    @Column
    private String wind;





    @Column
    private String p;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }
}
