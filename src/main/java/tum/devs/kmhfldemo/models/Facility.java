package tum.devs.kmhfldemo.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "facilities")
public class Facility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column
    private String county;

    @Column
    private String constituency;

    @Embedded
    private Location location;

    @Column
    private String phone;

    @Column
    private String email;

}