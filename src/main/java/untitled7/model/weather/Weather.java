package untitled7.model.weather;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Document
public class Weather {

    @Id
    private BigInteger id;


    private Date data_update;


    private Integer idCity;


    private List<OneDay> list;


    public BigInteger getId() {
        return id;
    }


    public Date getData_update() {
        return data_update;
    }

    public void setData_update(Date data_update) {
        this.data_update = data_update;
    }

    public Integer getIdCity() {
        return idCity;
    }

    public void setIdCity(Integer idCity) {
        this.idCity = idCity;
    }

    public List<OneDay> getList() {
        return list;
    }

    public void setList(List<OneDay> list) {
        this.list = list;
    }
}
