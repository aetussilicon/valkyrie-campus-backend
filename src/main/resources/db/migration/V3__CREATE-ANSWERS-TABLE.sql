create table if not exists answers (
    answer_id VARCHAR(10) primary key unique not null,
    post_id BIGINT not null,
    answer_by UUID not null,
    usertag VARCHAR(15) not null,
    answer_content TEXT not null,
    upvote BIGINT not null,
    downvote BIGINT not null,
    created_date TIMESTAMP not null,
    last_updated_date TIMESTAMP not null,
    is_accepted BOOLEAN not null,
    foreign key (post_id) references posts(post_id),
    foreign key (answer_by) references users(user_id),
    foreign key (usertag) references users(usertag)
)