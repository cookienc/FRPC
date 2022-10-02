package rehabilitation.device.model.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PressureCriteria {
	private Integer thumb;
	private Integer indexFinger;
	private Integer middleFinger;
	private Integer ringFinger;
	private Integer littleFinger;

	@Builder
	private PressureCriteria(Integer thumb, Integer indexFinger, Integer middleFinger, Integer ringFinger, Integer littleFinger) {
		this.thumb = thumb;
		this.indexFinger = indexFinger;
		this.middleFinger = middleFinger;
		this.ringFinger = ringFinger;
		this.littleFinger = littleFinger;
	}

	public static PressureCriteria getPressureCriteria() {
		return PressureCriteria.builder()
				.thumb(40)
				.indexFinger(40)
				.middleFinger(40)
				.ringFinger(40)
				.littleFinger(40)
				.build();
	}
}
