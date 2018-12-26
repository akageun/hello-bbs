package kr.geun.o.app.bbs.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import kr.geun.o.common.constants.CmnConst;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * 게시판 카테고리
 *
 * @author akageun
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "bbs_category")
public class BbsCategoryEntity {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long categoryId;

	@Column(length = 512)
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
