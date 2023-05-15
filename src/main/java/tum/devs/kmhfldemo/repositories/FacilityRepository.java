package tum.devs.kmhfldemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tum.devs.kmhfldemo.models.Facility;

public interface FacilityRepository extends JpaRepository<Facility, Long> {
}
