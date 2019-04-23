package kr.geun.o.app.bbs.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import kr.geun.o.core.constants.CmnConst;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * 게시글 태그 Entity
 *
 * @author akageun
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "bbs_tag")
public class BbsTagEntity {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long tagId;

	@Column(length = 512)
	@Size(max = 512, min = 1)
	private String name;

	@Column
	private String type; //bootstrap label color 넣은 곳

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
