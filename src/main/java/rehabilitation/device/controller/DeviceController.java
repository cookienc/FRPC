package rehabilitation.device.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rehabilitation.device.model.dto.SensorBarGraphResponse;
import rehabilitation.device.service.DeviceService;

@RestController
@RequestMapping("/home")
@RequiredArgsConstructor
@Slf4j
public class DeviceController {

	private final DeviceService deviceService;

	@GetMapping("/bar-graph")
	public ResponseEntity<SensorBarGraphResponse> getBarGraph(@RequestParam Long pressureId, @RequestParam Long flexId) {
		return ResponseEntity.ok().body(deviceService.getBarGraph(pressureId, flexId));
	}

}
