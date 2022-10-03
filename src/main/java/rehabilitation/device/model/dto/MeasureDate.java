package rehabilitation.device.model.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MeasureDate {
	Long pressureId;
	Long flexId;
	LocalDateTime time;

	@Builder
	public MeasureDate(Long pressureId, Long flexId, LocalDateTime time) {
		this.pressureId = pressureId;
		this.flexId = flexId;
		this.time = time;
	}
}
