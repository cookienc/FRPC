package rehabilitation.device.controller;

import lombok.Builder;
import lombok.Getter;
import rehabilitation.device.model.Finger;
import rehabilitation.device.model.dto.PressureResponse;

import java.time.LocalDateTime;

@Getter
public class PressureResponseConverter {
	private Long id;
	private Integer thumb;
	private Integer indexFinger;
	private Integer middleFinger;
	private Integer ringFinger;
	private Integer littleFinger;
	private LocalDateTime date;

	@Builder
	public PressureResponseConverter(Long id, Integer thumb, Integer indexFinger, Integer middleFinger, Integer ringFinger, Integer littleFinger, LocalDateTime date) {
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
				.indexFinger(finger.getIndex())
				.middleFinger(finger.getMiddle())
				.ringFinger(finger.getRing())
				.littleFinger(finger.getLittle())
				.date(date)
				.build();
	}
}
