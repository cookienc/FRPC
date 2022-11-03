package rehabilitation.device.model.dto;

import lombok.Builder;
import lombok.Getter;
import rehabilitation.device.model.Finger;
import rehabilitation.device.utils.SensorUtils;

import java.time.LocalDateTime;

import static rehabilitation.device.utils.SensorUtils.*;

@Getter
public class PressureResponse {
	private Long id;
	private double thumb;
	private double indexFinger;
	private double middleFinger;
	private double ringFinger;
	private double littleFinger;
	private LocalDateTime date;

	@Builder
	public PressureResponse(Long id, double thumb, double indexFinger, double middleFinger, double ringFinger, double littleFinger, LocalDateTime date) {
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
				.thumb(mapPressure(finger.getThumb()))
				.indexFinger(mapPressure(finger.getIndexFinger()))
				.middleFinger(mapPressure(finger.getMiddleFinger()))
				.ringFinger(mapPressure(finger.getRingFinger()))
				.littleFinger(mapPressure(finger.getLittleFinger()))
				.date(date)
				.build();
	}
}
