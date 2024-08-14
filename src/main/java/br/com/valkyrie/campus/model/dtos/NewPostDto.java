package br.com.valkyrie.campus.model.dtos;

import br.com.valkyrie.campus.model.entities.Users;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class NewPostDto {
    private Long postId;
    @NotNull private String title;
    @NotNull private String content;
    private Users postedBy;
    @NotNull private String usertag;
    private long upvote;
    private long downvote;
    private Date createdDate;
    private Date lastUpdatedDate;
    private boolean isAnswered;
    private boolean isArchived;
}
