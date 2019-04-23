package kr.geun.o.app.bbs.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import kr.geun.o.core.constants.CmnConst;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 게시글 태그 mapping Entity
 *
 * @author akageun
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "bbs_article_tag", indexes = { @Index(name = "IDX_articleId", columnList = "articleId") })
public class BbsArticleTagEntity {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long articleTagId;

	@Column
	private Long articleId;

	@Column
	private Long tagId;

	@Column
	private String createdUserId;

	@Column
	private String updatedUserId;

	/**
	 * 생성일시
	 */
	@JsonFormat(pattern = CmnConst.YMDHMS_READONLY)
	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	/**
	 * 수정일시
	 */
	@JsonFormat(pattern = CmnConst.YMDHMS_READONLY)
	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;

}
