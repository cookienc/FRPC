package rehabilitation.device.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rehabilitation.device.model.Pressure;

public interface PressureRepository extends JpaRepository<Pressure, Long> {
}
