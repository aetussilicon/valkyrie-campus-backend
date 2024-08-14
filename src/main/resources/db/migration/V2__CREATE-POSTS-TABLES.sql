create table if not exists posts (
    post_id BIGINT primary key not null unique,
    title VARCHAR(255) not null,
    content TEXT not null,
    posted_by UUID not null,
    usertag VARCHAR(15) not null,
    upvote BIGINT not null,
    downvote BIGINT not null,
    created_date TIMESTAMP not null,
    last_updated_date TIMESTAMP not null,
    isAnswered BOOLEAN not null,
    isArchived BOOLEAN not null,
    foreign key (posted_by) references users(user_id),
    foreign key (usertag) references users(usertag)
);