package senizm.integration.dsl.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Event {

	@Id
	@Column
	private Integer eventId;

	@Column
	private Date insertDate;
	
	@Column
	private String type;

	@Column
	private String description;

	@Column
	private String value;

	@Column
	private Integer status;
}
