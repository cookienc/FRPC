package rehabilitation.device.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rehabilitation.device.model.dto.FlexResponse;
import rehabilitation.device.model.dto.MeasureDate;
import rehabilitation.device.model.dto.PressureResponse;
import rehabilitation.device.model.dto.SensorBarGraphResponse;
import rehabilitation.device.service.DeviceService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DeviceController {

	private final DeviceService deviceService;

	@GetMapping("/bar-graph")
	public ResponseEntity<SensorBarGraphResponse> getBarGraph(@RequestParam Long pressureId, @RequestParam Long flexId) {
		return ResponseEntity.ok().body(deviceService.getBarGraph(pressureId, flexId));
	}

	@GetMapping("/line-graph")
	public ResponseEntity<List<PressureResponse>> getLineGraph(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") String date) {
		LocalDateTime localDateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		return ResponseEntity.ok().body(deviceService.getLineGraph(localDateTime));
	}

	@GetMapping("/line-graph/flex")
	public ResponseEntity<List<FlexResponse>> getLineFlexGraph(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") String date) {
		LocalDateTime localDateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		return ResponseEntity.ok().body(deviceService.getLineFlexGraph(localDateTime));
	}

	@GetMapping("/list")
	public ResponseEntity<List<MeasureDate>> getMeasureDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") String date) {
		LocalDateTime localDateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		return ResponseEntity.ok().body(deviceService.getMeasureDate(localDateTime));
	}
}
