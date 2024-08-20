package br.com.valkyrie.campus.model.dtos;

import br.com.valkyrie.campus.model.entities.Users;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class PostsDto {
    private UUID postId;
    private String title;
    private String content;
    private Users postedBy;
    private String usertag;
    private long upvote;
    private long downvote;
    private Date createdDate;
    private String formatedDate;
}
