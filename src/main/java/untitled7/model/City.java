package untitled7.model;


import untitled7.enumclass.AdapterEnum;

import javax.persistence.*;

@Entity
@Table(name = "city", uniqueConstraints={@UniqueConstraint(name="adpterIdCity", columnNames={"adpterIdCity", "name_en"})})
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private Integer adpterIdCity;

    @Column
    private String name;

    @Column
    private String name_en;

    @Column
    private String region;

    @Enumerated(EnumType.STRING)
    @Column()
    private AdapterEnum nameAdapter;

    @OneToOne
    private Country country;

    public Integer getAdpterIdCity() {
        return adpterIdCity;
    }

    public void setAdpterIdCity(Integer adpterIdCity) {
        this.adpterIdCity = adpterIdCity;
    }

    public AdapterEnum getNameAdapter() {
        return nameAdapter;
    }

    public void setNameAdapter(AdapterEnum nameAdapter) {
        this.nameAdapter = nameAdapter;
    }


    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
