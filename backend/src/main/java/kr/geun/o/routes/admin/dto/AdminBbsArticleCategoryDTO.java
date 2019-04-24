package kr.geun.o.routes.admin.dto;

import kr.geun.o.app.bbs.code.CategoryLabelTypeCd;
import kr.geun.o.core.valid.EnumValid;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 게시글 관리 DTO
 * - 카테고리
 *
 * @author akageun
 */
public class AdminBbsArticleCategoryDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.NONE)
    public static class Page {
        @NotNull
        @Min(0)
        private int pageNumber;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.NONE)
    public static class Get {
        @NotNull
        @Min(0)
        private Long categoryId;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.NONE)
    public static class Add {
        @Size(max = 512, min = 1)
        private String name;

        @EnumValid(targetEnum = CategoryLabelTypeCd.class)
        private String type; //bootstrap label color 넣은 곳
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.NONE)
    public static class Modify {
        @NotNull
        @Min(0)
        private Long categoryId;

        @Size(max = 512, min = 1)
        private String name;

        @EnumValid(targetEnum = CategoryLabelTypeCd.class)
        private String type; //bootstrap label color 넣은 곳
    }
}
