package kr.geun.o.routes.bbs.dto;

import kr.geun.o.app.bbs.code.ArticleStatusCd;
import kr.geun.o.core.valid.EnumValid;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 게시글 관련 DTO
 *
 * @author akageun
 */
public class BbsArticleDTO {

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

        @Min(0)
        @NotNull
        @Setter
        private Long articleId;


    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.NONE)
    public static class Add {

        @EnumValid(targetEnum = ArticleStatusCd.class)
        private String statusCd;

        //TODO : 길이체크
        @NotBlank
        private String title;

        //TODO : 길이체크
        @NotBlank
        private String content;

        @Min(0)
        @NotNull
        private Long categoryId;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.NONE)
    public static class Modify {

        @Min(0)
        @NotNull
        @Setter
        private Long articleId;

        @EnumValid(targetEnum = ArticleStatusCd.class)
        private String statusCd;

        //TODO : 길이체크
        @NotBlank
        private String title;

        //TODO : 길이체크
        @NotBlank
        private String content;

        @NotNull
        @Min(0)
        private Long categoryId;
    }
}
