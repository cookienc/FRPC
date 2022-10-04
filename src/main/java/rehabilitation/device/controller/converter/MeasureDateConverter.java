package rehabilitation.device.controller.converter;

import lombok.Getter;
import rehabilitation.device.model.Flex;
import rehabilitation.device.model.Pressure;
import rehabilitation.device.model.dto.MeasureDate;

@Getter
public class MeasureDateConverter {

	public static MeasureDate of(Pressure pressure, Flex flex) {
		return MeasureDate.builder()
				.pressureId(pressure.getId())
				.flexId(flex.getId())
				.time(pressure.getCreatedAt())
				.build();
	}
}
