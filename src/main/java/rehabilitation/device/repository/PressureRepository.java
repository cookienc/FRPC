package rehabilitation.device.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rehabilitation.device.model.Pressure;

import java.time.LocalDateTime;
import java.util.List;

public interface PressureRepository extends JpaRepository<Pressure, Long> {

	List<Pressure> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
}
