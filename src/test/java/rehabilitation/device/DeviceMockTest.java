package rehabilitation.device;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import rehabilitation.device.model.Finger;
import rehabilitation.device.model.Pressure;
import rehabilitation.device.model.dto.PressureResponse;
import rehabilitation.device.repository.PressureRepository;
import rehabilitation.device.service.DeviceService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("꺽은선 그래프 기능을 테스트 한다")
public class DeviceMockTest {

	@InjectMocks
	DeviceService deviceService;

	@Mock
	PressureRepository pressureRepository;

	/**
	 * given 그래프 값이 5개 이상 추가한다.
	 * when 꺽은선 그래프 정보를 조회하면
	 * then 반환된다.
	 */
	@DisplayName("꺽은선 그래프 정보 목록 최근 5개를 출력한다.")
	@Test
	void 꺽은선_그래프_정보를_최근_5개만_출력한다() {
		//given
		LocalDateTime now = LocalDateTime.now();

		for (int i = 0; i < 5; i++) {
			LocalDate date = now.toLocalDate().minusDays(i);
			LocalDateTime start = LocalDateTime.of(date, LocalTime.of(0, 0, 0));
			LocalDateTime end = LocalDateTime.of(date, LocalTime.of(23, 59, 59));
			Pressure p1 = new Pressure(new Finger(25, 25, 25, 25, 25));
			ReflectionTestUtils.setField(p1, "createdAt", start);
			when(pressureRepository.findByCreatedAtBetween(start, end)).thenReturn(List.of(p1));
		}

		//when
		List<PressureResponse> 응답 = deviceService.getLineGraph(now);

		//then
		assertAll(
				() -> assertThat(응답).hasSize(5),
				() -> assertThat(응답.get(0).getThumb()).isEqualTo(25)
		);
	}

	/**
	 * given 날짜가 같은 그래프 값을 5개 이상 추가한다.
	 * when 꺽은선 그래프 정보를 조회하면
	 * then 1개만 반환된다.
	 */
	@DisplayName("날짜가 같은 5개의 그래프를 추가하여 조회하면 1개만 출력된다")
	@Test
	void 날짜가_같은_5개의_그래프를_추가하여_조회하면_1개만_출력된다() {
		//given
		LocalDateTime now = LocalDateTime.now();
		LocalDate date = now.toLocalDate();
		LocalDateTime start = LocalDateTime.of(date, LocalTime.of(0, 0, 0));
		LocalDateTime end = LocalDateTime.of(date, LocalTime.of(23, 59, 59));

		Pressure p1 = new Pressure(new Finger(25, 25, 25, 25, 25));
		ReflectionTestUtils.setField(p1, "createdAt", start);
		Pressure p2 = new Pressure(new Finger(26, 26, 26, 26, 26));
		ReflectionTestUtils.setField(p2, "createdAt", start);
		Pressure p3 = new Pressure(new Finger(27, 27, 27, 27, 27));
		ReflectionTestUtils.setField(p3, "createdAt", start);
		Pressure p4 = new Pressure(new Finger(30, 30, 30, 30, 30));
		ReflectionTestUtils.setField(p4, "createdAt", start);
		Pressure p5 = new Pressure(new Finger(35, 35, 35, 35, 35));
		ReflectionTestUtils.setField(p5, "createdAt", start);

		when(pressureRepository.findByCreatedAtBetween(start, end)).thenReturn(List.of(p1, p2, p3, p4, p5));

		//when
		List<PressureResponse> 응답 = deviceService.getLineGraph(now);

		//then
		assertAll(
				() -> assertThat(응답).hasSize(1),
				() -> assertThat(응답.get(0).getThumb()).isEqualTo((25 + 26 + 27 + 30 + 35) / 5)
		);
	}
}

