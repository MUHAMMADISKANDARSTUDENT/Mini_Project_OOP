
CREATE DATABASE IF NOT EXISTS hotel_reservation_db;
USE hotel_reservation_db;

CREATE TABLE users (
    user_id VARCHAR(20) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    phone VARCHAR(20)
);

CREATE TABLE rooms (
    room_id VARCHAR(20) PRIMARY KEY,
    room_number VARCHAR(20) NOT NULL UNIQUE,
    room_type VARCHAR(30) NOT NULL,
    base_price DECIMAL(10,2) NOT NULL,
    bed_type VARCHAR(30),
    max_occupancy INT,
    extra_service VARCHAR(255)
);

CREATE TABLE reservations (
    reservation_id VARCHAR(20) PRIMARY KEY,
    user_id VARCHAR(20) NOT NULL,
    reservation_date DATE NOT NULL,
    check_in_date DATE NOT NULL,
    check_out_date DATE NOT NULL,
    status VARCHAR(30) NOT NULL,
    total_amount DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE payments (
    payment_id VARCHAR(20) PRIMARY KEY,
    reservation_id VARCHAR(20) NOT NULL,
    payment_date DATE NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    method VARCHAR(30) NOT NULL,
    status VARCHAR(30) NOT NULL,
    FOREIGN KEY (reservation_id) REFERENCES reservations(reservation_id)
);

CREATE TABLE reservation_rooms (
    reservation_id VARCHAR(20) NOT NULL,
    room_id VARCHAR(20) NOT NULL,
    PRIMARY KEY (reservation_id, room_id),
    FOREIGN KEY (reservation_id) REFERENCES reservations(reservation_id)
        ON DELETE CASCADE,
    FOREIGN KEY (room_id) REFERENCES rooms(room_id)
);