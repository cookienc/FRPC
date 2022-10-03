package rehabilitation.device.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rehabilitation.device.model.Finger;
import rehabilitation.device.model.Flex;
import rehabilitation.device.model.Pressure;
import rehabilitation.device.model.dto.PressureResponse;
import rehabilitation.device.model.dto.SensorBarGraphResponse;
import rehabilitation.device.repository.FlexRepository;
import rehabilitation.device.repository.PressureRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static rehabilitation.device.controller.SensorBarGraphConverter.toFlexRes;
import static rehabilitation.device.controller.SensorBarGraphConverter.toPressureRes;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DeviceService {

	private final FlexRepository flexRepository;
	private final PressureRepository pressureRepository;

	public void saveFlex(Flex flex) {
		flexRepository.save(flex);
	}

	public void savePressure(Pressure pressure) {
		pressureRepository.save(pressure);
	}

	public SensorBarGraphResponse getBarGraph(Long pressureId, Long flexId) {
		Pressure pressure = pressureRepository.findById(pressureId).orElseThrow(NoSuchElementException::new);
		Flex flex = flexRepository.findById(flexId).orElseThrow(NoSuchElementException::new);

		return SensorBarGraphResponse.of(toPressureRes(pressure), toFlexRes(flex));
	}

	public List<PressureResponse> getLineGraph(LocalDateTime dateTime) {
		List<PressureResponse> responses = new ArrayList<>();

		for (int i = 0; i < 5; i++) {
			LocalDate date = dateTime.toLocalDate().minusDays(i);
			LocalDateTime start = LocalDateTime.of(date, LocalTime.of(0, 0, 0));
			LocalDateTime end = LocalDateTime.of(date, LocalTime.of(23, 59, 59));
			List<Pressure> pressures = pressureRepository.findByCreatedAtBetween(start, end);

			if (pressures.isEmpty()) {
				return responses;
			}

			Finger finger = Finger.empty();
			for (Pressure pressure : pressures) {
				finger.addValue(pressure.getFinger());
			}

			finger.makeAverage(pressures.size());

			Pressure latestPressure = pressures.get(pressures.size() - 1);
			LocalDateTime pressureDate = LocalDateTime.of(latestPressure.getCreatedAt().toLocalDate(), LocalTime.of(0, 0, 0));
			responses.add(PressureResponse.of(latestPressure.getId(), finger, pressureDate));
		}

		return responses.stream()
				.sorted(comparing(PressureResponse::getDate))
				.collect(toList());
	}
}
