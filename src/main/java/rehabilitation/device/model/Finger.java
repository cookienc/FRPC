package rehabilitation.device.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Finger {
	private Integer thumb;
	private Integer indexFinger;
	private Integer middleFinger;
	private Integer ringFinger;
	private Integer littleFinger;

	public Finger(Integer thumb, Integer indexFinger, Integer middleFinger, Integer ringFinger, Integer littleFinger) {
		this.thumb = thumb;
		this.indexFinger = indexFinger;
		this.middleFinger = middleFinger;
		this.ringFinger = ringFinger;
		this.littleFinger = littleFinger;
	}

	public static Finger empty() {
		return new Finger(0, 0, 0, 0, 0);
	}

	public void makeAverage(int size) {
		this.thumb /= size;
		this.indexFinger /= size;
		this.middleFinger /= size;
		this.ringFinger /= size;
		this.littleFinger /= size;
	}

	public void addValue(Finger finger) {
		this.thumb += finger.getThumb();
		this.indexFinger += finger.getIndexFinger();
		this.middleFinger += finger.getMiddleFinger();
		this.ringFinger += finger.getRingFinger();
		this.littleFinger += finger.getLittleFinger();
	}
}
