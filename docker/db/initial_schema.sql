-- initial_schema.sql

-- Create a table for your Java class
CREATE TABLE users (
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(255),
    working_hours INTEGER
);

-- Insert some initial data
INSERT INTO users (username, password, name, working_hours) VALUES
    ('john_doe', 'hashed_password_1', 'John Doe', 40),
    ('jane_smith', 'hashed_password_2', 'Jane Smith', 35),
    ('bob_johnson', 'hashed_password_3', 'Bob Johnson', 45);
