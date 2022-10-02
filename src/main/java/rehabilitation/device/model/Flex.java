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
public class Flex extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Embedded
	private Finger finger = new Finger();

	public Flex(Finger finger) {
		this.finger = finger;
	}

	public Integer getThumb() {
		return finger.getThumb();
	}

	public Integer getIndexFinger() {
		return finger.getIndexFinger();
	}

	public Integer getMiddleFinger() {
		return finger.getMiddleFinger();
	}

	public Integer getRingFinger() {
		return finger.getRingFinger();
	}

	public Integer getLittleFinger() {
		return finger.getLittleFinger();
	}
}
