package rehabilitation.device.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rehabilitation.device.controller.converter.MeasureDateConverter;
import rehabilitation.device.controller.converter.ResponseConverter;
import rehabilitation.device.model.Finger;
import rehabilitation.device.model.Flex;
import rehabilitation.device.model.Pressure;
import rehabilitation.device.model.dto.FlexResponse;
import rehabilitation.device.model.dto.MeasureDate;
import rehabilitation.device.model.dto.PressureResponse;
import rehabilitation.device.model.dto.SensorBarGraphResponse;
import rehabilitation.device.repository.FlexRepository;
import rehabilitation.device.repository.PressureRepository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static rehabilitation.device.controller.converter.SensorBarGraphConverter.toFlexRes;
import static rehabilitation.device.controller.converter.SensorBarGraphConverter.toPressureRes;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
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

	public List<PressureResponse> getLineGraph(LocalDateTime date) {
		List<PressureResponse> responses = new ArrayList<>();

		for (int i = 0; i < 5; i++) {
			List<Pressure> pressures = getPressures(date, i);
			addData(responses, pressures);
		}

		return responses.stream()
				.sorted(comparing(PressureResponse::getDate))
				.collect(toList());
	}

	public List<MeasureDate> getMeasureDate(LocalDateTime date) {
		List<Flex> flexes = flexRepository.findByCreatedAtBetween(getStartDate(date, 0), getEndDate(date, 0));
		List<Pressure> pressures = pressureRepository.findByCreatedAtBetween(getStartDate(date, 0), getEndDate(date, 0));
		return getMeasureDates(pressures, flexes);
	}

	private void addData(List<PressureResponse> responses, List<Pressure> pressures) {
		if (pressures.isEmpty()) {
			return;
		}

		Finger finger = getAverage(pressures);
		Pressure latestPressure = pressures.get(pressures.size() - 1);
		LocalDateTime pressureDate = LocalDateTime.of(latestPressure.getCreatedAt().toLocalDate(), LocalTime.of(0, 0, 0));

		responses.add(ResponseConverter.ofPressureResponse(latestPressure.getId(), finger, pressureDate));
	}

	private List<Pressure> getPressures(LocalDateTime date, int i) {
		return pressureRepository.findByCreatedAtBetween(getStartDate(date, i), getEndDate(date, i));
	}

	private Finger getAverage(List<Pressure> pressures) {
		Finger finger = Finger.empty();

		for (Pressure pressure : pressures) {
			finger.addValue(pressure.getFinger());
		}

		return finger.makeAverage(pressures.size());
	}

	private LocalDateTime getStartDate(LocalDateTime date, int before) {
		return LocalDateTime.of(date.toLocalDate().minusDays(before), LocalTime.of(0, 0, 0));
	}

	private LocalDateTime getEndDate(LocalDateTime date, int before) {
		return LocalDateTime.of(date.toLocalDate().minusDays(before), LocalTime.of(23, 59, 59));
	}

	private List<MeasureDate> getMeasureDates(List<Pressure> pressures, List<Flex> flexes) {
		List<MeasureDate> dates = new ArrayList<>();

		for (int i = 0; i < pressures.size(); i++) {
			Pressure pressure = pressures.get(i);
			Flex flex = flexes.get(i);
			dates.add(MeasureDateConverter.of(pressure, flex));
		}

		return dates;
	}

	public List<FlexResponse> getLineFlexGraph(LocalDateTime date) {
		List<FlexResponse> responses = new ArrayList<>();

		for (int i = 0; i < 5; i++) {
			List<Flex> pressures = getFlexPressures(date, i);
			addDataFlex(responses, pressures);
		}

		return responses.stream()
				.sorted(comparing(FlexResponse::getDate))
				.collect(toList());
	}

	private List<Flex> getFlexPressures(LocalDateTime date, int i) {
		return flexRepository.findByCreatedAtBetween(getStartDate(date, i), getEndDate(date, i));
	}

	private void addDataFlex(List<FlexResponse> responses, List<Flex> flexes) {
		if (flexes.isEmpty()) {
			return;
		}

		Finger finger = getAverageFlex(flexes);
		Flex latestFlex = flexes.get(flexes.size() - 1);
		LocalDateTime flexDate = LocalDateTime.of(latestFlex.getCreatedAt().toLocalDate(), LocalTime.of(0, 0, 0));

		responses.add(ResponseConverter.ofFlexResponse(latestFlex.getId(), finger, flexDate));
	}

	private Finger getAverageFlex(List<Flex> flexes) {
		Finger finger = Finger.empty();

		for (Flex flex : flexes) {
			finger.addValue(flex.getFinger());
		}

		return finger.makeAverage(flexes.size());
	}
}
