package br.com.valkyrie.campus.model.dtos.answer;

import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpvoteDownvoteDto {
    @Nullable private UUID postId;
    @Nullable private UUID answerId;
    private int upvote;
    private int downvote;
}
