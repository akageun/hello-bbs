package kr.geun.o.app.bbs.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 카테고리 Label 타입
 *
 * @author akageun
 */
@Getter
@AllArgsConstructor
public enum CategoryLabelTypeCd {
	//@formatter:off
	BADGE_PRIMARY("badge-primary"),
	BADGE_SECONDARY("badge-secondary"),
	BADGE_SUCCESS("badge-success"),
	BADGE_WARNING("badge-warning"),
	BADGE_DANGER("badge-danger"),
	BADGE_INFO("badge-info"),
	BADGE_LIGHT("badge-light"),
	BADGE_DARK("badge-dark")
	//@formatter:on
	;

	private String classNm;
}
