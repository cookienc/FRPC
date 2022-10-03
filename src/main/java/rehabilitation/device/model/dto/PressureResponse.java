package rehabilitation.device.model.dto;

import lombok.Builder;
import lombok.Getter;
import rehabilitation.device.model.Finger;

import java.time.LocalDateTime;

@Getter
public class PressureResponse {
	private Long id;
	private Integer thumb;
	private Integer indexFinger;
	private Integer middleFinger;
	private Integer ringFinger;
	private Integer littleFinger;
	private LocalDateTime date;

	@Builder
	public PressureResponse(Long id, Integer thumb, Integer indexFinger, Integer middleFinger, Integer ringFinger, Integer littleFinger, LocalDateTime date) {
		this.id = id;
		this.thumb = thumb;
		this.indexFinger = indexFinger;
		this.middleFinger = middleFinger;
		this.ringFinger = ringFinger;
		this.littleFinger = littleFinger;
		this.date = date;
	}

	public static PressureResponse of(Long id, Finger finger, LocalDateTime date) {
		return PressureResponse.builder()
				.id(id)
				.thumb(finger.getThumb())
				.indexFinger(finger.getIndexFinger())
				.middleFinger(finger.getMiddleFinger())
				.ringFinger(finger.getRingFinger())
				.littleFinger(finger.getLittleFinger())
				.date(date)
				.build();
	}
}
