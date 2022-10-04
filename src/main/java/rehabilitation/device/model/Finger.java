package rehabilitation.device.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Finger {
	private int thumb;
	private int indexFinger;
	private int middleFinger;
	private int ringFinger;
	private int littleFinger;

	public Finger(int thumb, int indexFinger, int middleFinger, int ringFinger, int littleFinger) {
		this.thumb = thumb;
		this.indexFinger = indexFinger;
		this.middleFinger = middleFinger;
		this.ringFinger = ringFinger;
		this.littleFinger = littleFinger;
	}

	public static Finger empty() {
		return new Finger(0, 0, 0, 0, 0);
	}

	public Finger makeAverage(int size) {
		this.thumb /= size;
		this.indexFinger /= size;
		this.middleFinger /= size;
		this.ringFinger /= size;
		this.littleFinger /= size;

		return this;
	}

	public void addValue(Finger finger) {
		this.thumb += finger.getThumb();
		this.indexFinger += finger.getIndexFinger();
		this.middleFinger += finger.getMiddleFinger();
		this.ringFinger += finger.getRingFinger();
		this.littleFinger += finger.getLittleFinger();
	}
}
