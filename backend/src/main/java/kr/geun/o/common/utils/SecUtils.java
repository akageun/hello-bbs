package kr.geun.o.common.utils;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 *
 * @author akageun
 */
public class SecUtils {

	public static List<GrantedAuthority> mapToGrantedAuthorities(String authoritiesStr) {
		return mapToGrantedAuthorities(Arrays.asList(authoritiesStr.split(",")));
	}

	/**
	 *
	 *
	 * @param authorities
	 * @return
	 */
	public static List<GrantedAuthority> mapToGrantedAuthorities(List<String> authorities) {
		return authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}

	public static String getAuthorities(Collection<? extends GrantedAuthority> authorities) {
		return authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
	}
}
