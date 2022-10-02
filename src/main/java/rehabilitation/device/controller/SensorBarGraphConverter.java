package rehabilitation.device.controller;

import rehabilitation.device.model.Flex;
import rehabilitation.device.model.Pressure;
import rehabilitation.device.model.dto.FlexResponse;
import rehabilitation.device.model.dto.PressureResponse;

public class SensorBarGraphConverter {
	public static FlexResponse toFlexRes(Flex flex) {
		return FlexResponse.builder()
				.thumb(flex.getThumb())
				.indexFinger(flex.getIndexFinger())
				.middleFinger(flex.getMiddleFinger())
				.ringFinger(flex.getRingFinger())
				.littleFinger(flex.getLittleFinger())
				.date(flex.getCreatedAt())
				.build();
	}

	public static PressureResponse toPressureRes(Pressure pressure) {
		return PressureResponse.builder()
				.thumb(pressure.getThumb())
				.indexFinger(pressure.getIndexFinger())
				.middleFinger(pressure.getMiddleFinger())
				.ringFinger(pressure.getRingFinger())
				.littleFinger(pressure.getLittleFinger())
				.date(pressure.getCreatedAt())
				.build();
	}
}
