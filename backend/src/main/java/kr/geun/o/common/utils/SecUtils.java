package kr.geun.o.common.utils;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 *
 * @author akageun
 */
public class SecUtils {

	/**
	 *
	 *
	 * @param authorities
	 * @return
	 */
	public static List<GrantedAuthority> mapToGrantedAuthorities(List<String> authorities) {
		return authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}
}
