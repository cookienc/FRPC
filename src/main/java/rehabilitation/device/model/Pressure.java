package rehabilitation.device.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import rehabilitation.device.utils.BaseEntity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Pressure extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Embedded
	private Finger finger = new Finger();

	public Pressure(Finger finger) {
		this.finger = finger;
	}

	public int getThumb() {
		return finger.getThumb();
	}

	public int getIndexFinger() {
		return finger.getIndexFinger();
	}

	public int getMiddleFinger() {
		return finger.getMiddleFinger();
	}

	public int getRingFinger() {
		return finger.getRingFinger();
	}

	public int getLittleFinger() {
		return finger.getLittleFinger();
	}
}
