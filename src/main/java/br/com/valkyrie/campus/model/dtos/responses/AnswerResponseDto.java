package br.com.valkyrie.campus.model.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class AnswerResponseDto {
    private UUID answerId;
    private UUID postId;
    private String answerByUser;
    private String usertag;
    private String answerContent;
    private int upvote;
    private int downvote;
    private Date createdDate;
    private Date lastUpdatedDate;
}
