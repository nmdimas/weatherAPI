package untitled7.model.weather;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class Weather {

    @Id
    private Long id;


    @Indexed
    private Date data_update;










}
