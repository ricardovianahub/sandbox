CREATE USER docker;
CREATE DATABASE docker;
GRANT ALL PRIVILEGES ON DATABASE docker TO docker;

\c docker

CREATE TABLE IF NOT EXISTS IMPROVEMENT_GRID(
   unique_id varchar(100),
   created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
   team_name VARCHAR(100),
   title VARCHAR(100),
   field1_awesome VARCHAR(255),
   field2_now VARCHAR(255),
   field3_next VARCHAR(255),
   field4_breakdown VARCHAR(255)
);

GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO docker;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO docker;
