package rehabilitation.device.model.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FlexResponse {
	private Long id;
	private double thumb;
	private double indexFinger;
	private double middleFinger;
	private double ringFinger;
	private double littleFinger;
	private LocalDateTime date;

	@Builder
	public FlexResponse(Long id, double thumb, double indexFinger, double middleFinger, double ringFinger, double littleFinger, LocalDateTime date) {
		this.id = id;
		this.thumb = thumb;
		this.indexFinger = indexFinger;
		this.middleFinger = middleFinger;
		this.ringFinger = ringFinger;
		this.littleFinger = littleFinger;
		this.date = date;
	}
}
