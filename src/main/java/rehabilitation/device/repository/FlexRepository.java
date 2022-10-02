package rehabilitation.device.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rehabilitation.device.model.Flex;

import java.time.LocalDateTime;

public interface FlexRepository extends JpaRepository<Flex, Long> {
}
