-- Users table
CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    age INT,
    weight DOUBLE PRECISION,
    gender VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Trainer-Client relationship table
CREATE TABLE IF NOT EXISTS trainer_client (
    trainer_id INT NOT NULL,
    client_id INT NOT NULL,
    PRIMARY KEY (trainer_id, client_id),
    FOREIGN KEY (trainer_id) REFERENCES users(id),
    FOREIGN KEY (client_id) REFERENCES users(id)
);

-- Workouts table
CREATE TABLE IF NOT EXISTS workouts (
    workout_id SERIAL PRIMARY KEY,
    client_id INT NOT NULL,
    exercise_name VARCHAR(255) NOT NULL,
    target_sets INT NOT NULL,
    target_reps INT NOT NULL,
    is_completed BOOLEAN DEFAULT FALSE,
    client_comment TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (client_id) REFERENCES users(id)
);