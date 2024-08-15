package br.com.valkyrie.campus.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "answers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "answerId")
public class Answer {
    private static final int INITIAL_UPVOTE = 0;
    private static final int INITIAL_DOWNVOTE = 0;

    @Id
    private UUID answerId;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Posts post;
    @ManyToOne
    @JoinColumn(name = "answer_by")
    private Users answerBy;
    private String usertag;

    @Column(name = "answer_content")
    private String answerContent;
    private int upvote = INITIAL_UPVOTE;
    private int downvote = INITIAL_DOWNVOTE;

    @Column(name = "is_accepted")
    private boolean isAccepted = false;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "last_updated_date")
    private Date lastUpdatedDate;
}
