BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "usuarios" (
	"login"	TEXT NOT NULL UNIQUE,
	"contra"	TEXT NOT NULL,
	PRIMARY KEY("login")
);
COMMIT;
