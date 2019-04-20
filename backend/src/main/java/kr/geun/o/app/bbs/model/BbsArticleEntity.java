package kr.geun.o.app.bbs.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import kr.geun.o.core.constants.CmnConst;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 게시글 Entity
 *
 * @author akageun
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "bbs_article")
public class BbsArticleEntity {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long articleId;

	@Column
	private String statusCd;

	@Column
	private Long categoryId;

	@Column
	private String title;

	@Lob
	@Column
	private String content;

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

	@OneToOne
	@JoinColumn(name = "categoryId", insertable = false, updatable = false)
	private BbsCategoryEntity bbsCategoryEntity;

	/**
	 * 무한루프로 인해 override
	 *
	 * @return
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
