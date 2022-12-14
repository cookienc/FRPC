package rehabilitation.device.controller.converter;

import lombok.Builder;
import lombok.Getter;
import rehabilitation.device.model.Finger;
import rehabilitation.device.model.dto.FlexResponse;
import rehabilitation.device.model.dto.PressureResponse;

import java.time.LocalDateTime;

import static rehabilitation.device.utils.SensorUtils.mapFlex;
import static rehabilitation.device.utils.SensorUtils.mapPressure;

@Getter
public class ResponseConverter {
	private Long id;
	private Integer thumb;
	private Integer indexFinger;
	private Integer middleFinger;
	private Integer ringFinger;
	private Integer littleFinger;
	private LocalDateTime date;

	@Builder
	public ResponseConverter(Long id, Integer thumb, Integer indexFinger, Integer middleFinger, Integer ringFinger, Integer littleFinger, LocalDateTime date) {
		this.id = id;
		this.thumb = thumb;
		this.indexFinger = indexFinger;
		this.middleFinger = middleFinger;
		this.ringFinger = ringFinger;
		this.littleFinger = littleFinger;
		this.date = date;
	}

	public static PressureResponse ofPressureResponse(Long id, Finger finger, LocalDateTime date) {
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

	public static FlexResponse ofFlexResponse(Long id, Finger finger, LocalDateTime date) {
		return FlexResponse.builder()
				.id(id)
				.thumb(mapFlex(finger.getThumb()))
				.indexFinger(mapFlex(finger.getIndexFinger()))
				.middleFinger(mapFlex(finger.getMiddleFinger()))
				.ringFinger(mapFlex(finger.getRingFinger()))
				.littleFinger(mapFlex(finger.getLittleFinger()))
				.date(date)
				.build();
	}
}
