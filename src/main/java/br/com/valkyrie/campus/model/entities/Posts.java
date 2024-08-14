package br.com.valkyrie.campus.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "posts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "postId")
public class Posts {

    private static final long INITIAL_UPVOTE = 0;
    private static final long INITIAL_DOWNVOTE = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;
    private String title;
    private String content;
    @ManyToOne
    @JoinColumn(name = "postedBy")
    private Users postedBy;
    //TODO - Adicionar lista das respostas do post
    private long upvote = INITIAL_UPVOTE;
    private long downvote = INITIAL_DOWNVOTE;
    private Date createdDate;
    private Date lastUpdatedDate;
    private boolean isAnswered = false;
    private boolean isArchived = false;
}
