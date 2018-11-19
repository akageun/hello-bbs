package kr.geun.o.common.constants;

/**
 * 권한 코드
 *
 * @author 김형근
 */
public enum AuthorityCd {
	ADMIN, USER;

	/**
	 * 롤코드
	 *
	 * @return
	 */
	public String roleCd() {
		return "ROLE_" + this.name();
	}
}
