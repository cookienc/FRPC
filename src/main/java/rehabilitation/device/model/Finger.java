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
	private Integer index;
	private Integer middle;
	private Integer ring;
	private Integer little;

	public Finger(Integer thumb, Integer index, Integer middle, Integer ring, Integer little) {
		this.thumb = thumb;
		this.index = index;
		this.middle = middle;
		this.ring = ring;
		this.little = little;
	}

	public static Finger empty() {
		return new Finger(0, 0, 0, 0, 0);
	}

	public Finger makeAverage(int size) {
		this.thumb /= size;
		this.index /= size;
		this.middle /= size;
		this.ring /= size;
		this.little /= size;

		return this;
	}

	public void addValue(Finger finger) {
		this.thumb += finger.getThumb();
		this.index += finger.getIndex();
		this.middle += finger.getMiddle();
		this.ring += finger.getRing();
		this.little += finger.getLittle();
	}
}
