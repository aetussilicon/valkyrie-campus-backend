package br.com.valkyrie.campus.model.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class PostsResponseDto {
    private UUID postId;
    private String title;
    private String content;
    private String postedBy;
    private String usertag;
    private long upvote;
    private long downvote;
    private Date createdDate;
    private Date lastUpdatedDate;
}
