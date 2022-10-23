package rehabilitation.device.model.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SensorBarGraphResponse {
	private PressureResponse pressures;
	private FlexResponse flexes;

	@Builder
	public SensorBarGraphResponse(PressureResponse pressures, FlexResponse flexes) {
		this.pressures = pressures;
		this.flexes = flexes;
	}

	public static SensorBarGraphResponse of(PressureResponse pressureResponse, FlexResponse flexResponse) {
		return SensorBarGraphResponse.builder()
				.pressures(pressureResponse)
				.flexes(flexResponse)
				.build();
	}
}
