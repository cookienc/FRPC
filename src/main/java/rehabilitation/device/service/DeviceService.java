package rehabilitation.device.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rehabilitation.device.model.Flex;
import rehabilitation.device.model.Pressure;
import rehabilitation.device.model.dto.SensorBarGraphResponse;
import rehabilitation.device.repository.FlexRepository;
import rehabilitation.device.repository.PressureRepository;

import java.util.NoSuchElementException;

import static rehabilitation.device.controller.SensorBarGraphConverter.toFlexRes;
import static rehabilitation.device.controller.SensorBarGraphConverter.toPressureRes;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DeviceService {

	private final FlexRepository flexRepository;
	private final PressureRepository pressureRepository;

	public SensorBarGraphResponse getBarGraph(Long pressureId, Long flexId) {
		Pressure pressure = pressureRepository.findById(pressureId).orElseThrow(NoSuchElementException::new);
		Flex flex = flexRepository.findById(flexId).orElseThrow(NoSuchElementException::new);

		return SensorBarGraphResponse.of(toPressureRes(pressure), toFlexRes(flex));
	}

	public void saveFlex(Flex flex) {
		flexRepository.save(flex);
	}

	public void savePressure(Pressure pressure) {
		pressureRepository.save(pressure);
	}
}
