package tum.devs.kmhfldemo.models.location;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "counties")
public class County {

    @Id
    private int id;
    private String county_name;

}
