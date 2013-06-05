package untitled7.model;


import untitled7.enumclass.AdapterEnum;

import javax.persistence.*;

@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private Integer idCityFromAdapter;

    @Column
    private String iso2;

    @Column
    private String iso3;

    @Column
    private String name;

    @Column
    private String name_en;

    @Enumerated(EnumType.STRING)
    @Column()
    private AdapterEnum nameAdapter;


    public Integer getIdCityFromAdapter() {
        return idCityFromAdapter;
    }

    public void setIdCityFromAdapter(Integer idCityFromAdapter) {
        this.idCityFromAdapter = idCityFromAdapter;
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

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIso2() {
        return iso2;
    }

    public void setIso2(String iso2) {
        this.iso2 = iso2;
    }

    public String getIso3() {
        return iso3;
    }

    public void setIso3(String iso3) {
        this.iso3 = iso3;
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

}
