package untitled7.model;


import untitled7.enumclass.AdapterEnum;

import javax.persistence.*;

@Entity
@Table(name = "country", uniqueConstraints={@UniqueConstraint(name="idCountryAdapter", columnNames={"idCountryAdapter", "name_en"})})
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private Integer idCountryAdapter;

    @Column
    private String iso2;

    @Column
    private String iso3;

    @Column
    private String name;

    public Integer getIdCountryAdapter() {
        return idCountryAdapter;
    }

    public void setIdCountryAdapter(Integer idCountryAdapter) {
        this.idCountryAdapter = idCountryAdapter;
    }

    @Column
    private String name_en;

    @Enumerated(EnumType.STRING)
    @Column()
    private AdapterEnum nameAdapter;



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
