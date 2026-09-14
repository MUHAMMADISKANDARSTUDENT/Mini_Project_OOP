PRAGMA foreign_keys = ON;

CREATE TABLE IF NOT EXISTS users (
    user_id TEXT PRIMARY KEY,
    name TEXT NOT NULL,
    email TEXT,
    phone TEXT
);

CREATE TABLE IF NOT EXISTS rooms (
    room_id TEXT PRIMARY KEY,
    room_number TEXT NOT NULL UNIQUE,
    room_type TEXT NOT NULL,
    base_price REAL NOT NULL,
    bed_type TEXT,
    max_occupancy INTEGER,
    extra_service TEXT
);

CREATE TABLE IF NOT EXISTS reservations (
    reservation_id TEXT PRIMARY KEY,
    user_id TEXT NOT NULL,
    reservation_date TEXT NOT NULL,
    check_in_date TEXT NOT NULL,
    check_out_date TEXT NOT NULL,
    status TEXT NOT NULL,
    total_amount REAL NOT NULL,
    FOREIGN KEY (user_id)
        REFERENCES users(user_id)
);

CREATE TABLE IF NOT EXISTS payments (
    payment_id TEXT PRIMARY KEY,
    reservation_id TEXT NOT NULL,
    payment_date TEXT NOT NULL,
    amount REAL NOT NULL,
    method TEXT NOT NULL,
    status TEXT NOT NULL,
    FOREIGN KEY (reservation_id)
        REFERENCES reservations(reservation_id)
);

CREATE TABLE IF NOT EXISTS reservation_rooms (
    reservation_id TEXT NOT NULL,
    room_id TEXT NOT NULL,
    PRIMARY KEY (reservation_id, room_id),
    FOREIGN KEY (reservation_id)
        REFERENCES reservations(reservation_id)
        ON DELETE CASCADE,
    FOREIGN KEY (room_id)
        REFERENCES rooms(room_id)
);