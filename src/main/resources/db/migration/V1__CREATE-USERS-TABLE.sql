CREATE TABLE IF NOT EXISTS users (
    user_id UUID PRIMARY KEY UNIQUE NOT NULL,
    full_name VARCHAR(50),
    usertag VARCHAR(15) NOT NULL UNIQUE,
    role TEXT NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    created_date TIMESTAMP NOT NULL,
    last_updated_date TIMESTAMP NOT NULL
);