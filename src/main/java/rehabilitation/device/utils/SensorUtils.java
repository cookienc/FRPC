package rehabilitation.device.utils;

public class SensorUtils {
	public static double mapFlex(int x) {
		if (x == 0) {
			return 0;
		}

		if (0 < x && x < 141) {
			return map(x, 0, 141, 10, 8);
		}

		if (141 <= x && x < 583) {
			return map(x, 141, 583, 8, 2);
		}

		return map(x, 554, 750, 2, 1);
	}

	public static double mapPressure(int x) {
		if (x == 0) {
			return 0;
		}

		if (0 < x && x < 23) {
			return map(x, 0, 23, 0, 15);
		}

		if (23 <= x && x < 554) {
			return map(x, 23, 554, 15, 20);
		}

		return map(x, 554, 750, 20, 50);
	}

	private static double map(int x, int inMin, int inMax, int outMin, int outMax) {
		return Math.round(((double) (x - inMin) * (outMax - outMin) / (inMax - inMin) + outMin) * 10) / (double) 10;
	}
}
