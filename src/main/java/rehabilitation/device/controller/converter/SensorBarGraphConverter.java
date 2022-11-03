package rehabilitation.device.controller.converter;

import rehabilitation.device.model.Flex;
import rehabilitation.device.model.Pressure;
import rehabilitation.device.model.dto.FlexResponse;
import rehabilitation.device.model.dto.PressureResponse;

import static rehabilitation.device.utils.SensorUtils.mapFlex;
import static rehabilitation.device.utils.SensorUtils.mapPressure;

public class SensorBarGraphConverter {

	public static FlexResponse toFlexRes(Flex flex) {
		return FlexResponse.builder()
				.id(flex.getId())
				.thumb(mapFlex(flex.getThumb()))
				.indexFinger(mapFlex(flex.getIndexFinger()))
				.middleFinger(mapFlex(flex.getMiddleFinger()))
				.ringFinger(mapFlex(flex.getRingFinger()))
				.littleFinger(mapFlex(flex.getLittleFinger()))
				.date(flex.getCreatedAt())
				.build();
	}

	public static PressureResponse toPressureRes(Pressure pressure) {
		return PressureResponse.builder()
				.id(pressure.getId())
				.thumb(mapPressure(pressure.getThumb()))
				.indexFinger(mapPressure(pressure.getIndexFinger()))
				.middleFinger(mapPressure(pressure.getMiddleFinger()))
				.ringFinger(mapPressure(pressure.getRingFinger()))
				.littleFinger(mapPressure(pressure.getLittleFinger()))
				.date(pressure.getCreatedAt())
				.build();
	}
}
