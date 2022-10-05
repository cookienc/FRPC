package rehabilitation.device.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rehabilitation.device.model.dto.SensorBarGraphResponse;
import rehabilitation.device.service.DeviceService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping
@RequiredArgsConstructor
@Slf4j
public class RenderController {

	private final DeviceService deviceService;

	@GetMapping("/")
	public String home() {
		return "index";
	}

	@GetMapping("/list")
	public String getMeasureDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") String date, Model model) {
		log.info("date = {}", date);

		LocalDateTime localDateTime = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		model.addAttribute("measures", deviceService.getMeasureDate(localDateTime));
		return "list";
	}

	@GetMapping("/line-graph")
	public String getLineGraph(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime date, Model model) {
		model.addAttribute("pressures", deviceService.getLineGraph(date));
		return "line-graph";
	}

	@GetMapping("/bar-graph")
	public String getBarGraph(@RequestParam Long pressureId, @RequestParam Long flexId, Model model) {
		SensorBarGraphResponse barGraph = deviceService.getBarGraph(pressureId, flexId);
		model.addAttribute("pressures", barGraph.getPressures());
		model.addAttribute("criteria", barGraph.getCriteria());
		model.addAttribute("flexes", barGraph.getFlexes());
		return "bar-graph";
	}
}
