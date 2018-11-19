package kr.geun.o.common.constants;

/**
 *
 *
 * @author 김형근
 */
public enum AuthorityCd {
	ADMIN, USER;

	public String roleAuthority() {
		return "ROLE_" + this.name();
	}
}
