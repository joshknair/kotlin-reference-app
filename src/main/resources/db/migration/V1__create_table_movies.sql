CREATE TABLE public.movies
(
  id text NOT NULL,
  title_name text NOT NULL,
  update_timestamp timestamptz default now(),
  CONSTRAINT movies_pk PRIMARY KEY (id)
);