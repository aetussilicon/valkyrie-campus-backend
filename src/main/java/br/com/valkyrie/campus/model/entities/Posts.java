package br.com.valkyrie.campus.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

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
    private UUID postId;
    private String title;
    private String content;
    @ManyToOne
    @JoinColumn(name = "postedBy")
    private Users postedBy;
    private String usertag;
    //TODO - Adicionar lista das respostas do post
    private long upvote = INITIAL_UPVOTE;
    private long downvote = INITIAL_DOWNVOTE;

    @Column(name = "created_date")
    private Date createdDate;
    @Column(name = "last_updated_date")
    private Date lastUpdatedDate;

    @Column(name = "is_answered")
    private boolean isAnswered = false;
    @Column(name = "is_archived")
    private boolean isArchived = false;
}
