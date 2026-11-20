CREATE DATABASE IF NOT EXISTS javafxsample;
USE javafxsample;

CREATE TABLE IF NOT EXISTS employment_application (
    id INT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(120) NOT NULL,
    current_address VARCHAR(255) NOT NULL,
    contact_number VARCHAR(30) NOT NULL,
    email_address VARCHAR(150) NOT NULL,
    highest_education VARCHAR(150) NOT NULL,
    gender VARCHAR(20) NOT NULL,
    date_available VARCHAR(30) NOT NULL,
    desired_position VARCHAR(100) NOT NULL,
    desired_salary VARCHAR(50) NOT NULL,
    legally_authorized VARCHAR(10) NOT NULL,
    has_relatives VARCHAR(10) NOT NULL,
    explanation TEXT,
    applicant_signature VARCHAR(120) NOT NULL
);

SELECT * FROM employment_application;