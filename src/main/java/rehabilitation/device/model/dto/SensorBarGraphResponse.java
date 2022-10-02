package rehabilitation.device.model.dto;

import lombok.Builder;
import lombok.Getter;

import static rehabilitation.device.model.dto.PressureCriteria.*;

@Getter
public class SensorBarGraphResponse {
	private PressureResponse pressures;
	private PressureCriteria criteria;
	private FlexResponse flexes;

	@Builder
	public SensorBarGraphResponse(PressureResponse pressures, PressureCriteria criteria, FlexResponse flexes) {
		this.pressures = pressures;
		this.criteria = criteria;
		this.flexes = flexes;
	}

	public static SensorBarGraphResponse of(PressureResponse pressureResponse, FlexResponse flexResponse) {
		return SensorBarGraphResponse.builder()
				.pressures(pressureResponse)
				.flexes(flexResponse)
				.criteria(getPressureCriteria())
				.build();
	}
}
