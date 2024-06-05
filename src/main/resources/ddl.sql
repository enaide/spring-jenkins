DROP TABLE IF EXISTS employees;

CREATE TABLE "public"."employees"
(
    "employee_id" int4 NOT NULL,
    "first_name"  varchar(255) COLLATE "pg_catalog"."default",
    "last_name"   varchar(255) COLLATE "pg_catalog"."default",
    "title"       varchar(255) COLLATE "pg_catalog"."default",
    "birth_date"  date,
    "hire_date"   date,
    "address"     varchar(255) COLLATE "pg_catalog"."default",
    "city"        varchar(255) COLLATE "pg_catalog"."default",
    "region"      varchar(255) COLLATE "pg_catalog"."default",
    "country"     varchar(255) COLLATE "pg_catalog"."default",
    "postal_code" varchar(255) COLLATE "pg_catalog"."default",
    "home_phone"  varchar(255) COLLATE "pg_catalog"."default",
    "extension"   varchar(255) COLLATE "pg_catalog"."default",
    "notes"       varchar(255) COLLATE "pg_catalog"."default",
    "reports_to"  int4,

    CONSTRAINT "employees_pkey" PRIMARY KEY ("employee_id")
)
;