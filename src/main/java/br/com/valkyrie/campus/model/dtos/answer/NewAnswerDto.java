package br.com.valkyrie.campus.model.dtos.answer;

import br.com.valkyrie.campus.model.entities.Posts;
import br.com.valkyrie.campus.model.entities.Users;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class NewAnswerDto {
    private UUID answerId;
    private Posts post;
    private UUID postId;
    private Users answerBy;
    private UUID answerByUserId;
    private String usertag;
    private String answerContent;
    private int upvote;
    private int downvote;
    private Date createdDate;
    private Date lastUpdatedDate;
}
