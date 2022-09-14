package dislinkt.notificationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Authority implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	Long id;

	String name;

	public String getAuthority() {
		return name;
	}

}
