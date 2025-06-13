CREATE TABLE IF NOT EXISTS users
(
  id       SERIAL PRIMARY KEY,
  email    VARCHAR(100) UNIQUE NOT NULL,
  password VARCHAR(100)        NOT NULL
);

-- Insert default admin user
INSERT INTO users (email, password)
VALUES ('admin@example.com', 'admin123')
ON CONFLICT (email) DO NOTHING;

CREATE TABLE IF NOT EXISTS students
(
  id   SERIAL PRIMARY KEY,
  name VARCHAR(100),
  age  INT
);

CREATE TABLE IF NOT EXISTS subjects
(
  id   SERIAL PRIMARY KEY,
  name VARCHAR(100)
);

-- Junction table for many-to-many: student_subject
CREATE TABLE IF NOT EXISTS student_subject
(
  student_id INT,
  subject_id INT,
  mark       DOUBLE PRECISION,
  PRIMARY KEY (student_id, subject_id),
  FOREIGN KEY (student_id) REFERENCES students (id) ON DELETE CASCADE,
  FOREIGN KEY (subject_id) REFERENCES subjects (id) ON DELETE CASCADE
);
