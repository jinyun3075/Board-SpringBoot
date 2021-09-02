package com.board.board.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDto {

    private Long bno;

    private String title;

    private String content;

    private String writerEmail; // 작성자 이메일

    private String writerName;  // 작성자 이름

    private LocalDateTime regDate;

    private LocalDateTime modDate;

    private int replayCount; // 해당 게시글의 댓글 수
}
