CREATE DATABASE IF NOT EXISTS sample_db;
USE sample_db;

CREATE TABLE IF NOT EXISTS date_tb (
                                       "id" SERIAL,
                                       "date_str" VARCHAR(500) NULL,
                                       "date" timestamp NULL,
                                       "local_time" time NULL,
                                       "local_date"  DATE NULL,
                                       "local_datetime_dt" timestamp NULL,
                                       "local_datetime_ts" timestamptz NULL,
                                       "offset_datetime" timestamptz NULL,
                                       "zoned_datetime" timestamptz NULL,

                                       PRIMARY KEY ("id"));



