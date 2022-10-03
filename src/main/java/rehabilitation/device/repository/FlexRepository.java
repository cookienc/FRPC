package rehabilitation.device.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rehabilitation.device.model.Flex;

import java.time.LocalDateTime;
import java.util.List;

public interface FlexRepository extends JpaRepository<Flex, Long> {
	List<Flex> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
}
