package rehabilitation.device;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import rehabilitation.device.model.Finger;
import rehabilitation.device.model.Flex;
import rehabilitation.device.model.Pressure;
import rehabilitation.device.service.DeviceService;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("장치를 테스트 한다.")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DeviceAcceptanceTest {

	@LocalServerPort
	int port;

	@BeforeEach
	void setPort() {
		RestAssured.port = port;
	}

	@Autowired
	DeviceService deviceService;

	/**
	 * given 플렉스와 압력 센서의 값을 저장한다.
	 * when 요청을 시도하면
	 * then 막대 그래프 데이터를 출력한다.
	 */
	@DisplayName("막대 그래프 데이터를 출력한다")
	@Test
	void 막대_그래프_데이터를_출력한다() {
		//given
		Finger flexFinger = new Finger(20, 20, 20, 20, 20);
		Flex flex = new Flex(flexFinger);
		deviceService.saveFlex(flex);

		Finger pressureFinger = new Finger(25, 25, 25, 25, 25);
		Pressure pressure = new Pressure(pressureFinger);
		deviceService.savePressure(pressure);

		//when
		ExtractableResponse<Response> 응답 = given()
				.when()
				.get("/api/bar-graph?pressureId={pressureId}&flexId={flexId}", 1L, 1L)
				.then().log().all()
				.extract();

		//then
		assertAll(
				() -> assertThat(응답.statusCode()).isEqualTo(HttpStatus.OK.value()),
				() -> {
					LinkedHashMap<String, Object> flexes = 응답.jsonPath().get("flexes");
					assertThat(flexes.get("thumb")).isEqualTo(20);
					assertThat(flexes.get("littleFinger")).isEqualTo(20);
				},
				() -> {
					LinkedHashMap<String, Object> pressures = 응답.jsonPath().get("pressures");
					assertThat(pressures.get("thumb")).isEqualTo(25);
					assertThat(pressures.get("littleFinger")).isEqualTo(25);
				}
		);
	}

	/**
	 * given 같은 날짜의 데이터를 5개를 등록한다.
	 * when 날짜를 누르면
	 * then 해당 날짜에 기록한 데이터 목록 5개를 보여준다.
	 */
	@DisplayName("날짜를 누르면 해당 날짜의 데이터 목록들이 조회된다.")
	@Test
	void 데이터_목록_조회() {
	    //given
		for (int i = 0; i < 5; i++) {
			Pressure p = new Pressure(new Finger(25, 25, 25, 25, 25));
			Flex f = new Flex(new Finger(50, 50, 50, 50, 50));
			deviceService.savePressure(p);
			deviceService.saveFlex(f);
		}

	    //when
		ExtractableResponse<Response> 응답 = given()
				.when()
				.get("/api/list?date={date}", LocalDateTime.now().toString().substring(0, 10) + " 00:00:00")
				.then().log().all()
				.extract();

		//then
		assertAll(
				() -> assertThat(응답.statusCode()).isEqualTo(HttpStatus.OK.value()),
				() -> assertThat(응답.jsonPath().getList("time")).hasSize(5)
		);
	}

	private RequestSpecification given() {
		return RestAssured.given().log().all()
				.contentType(MediaType.APPLICATION_JSON_VALUE);
	}
}
