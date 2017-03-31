--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.2
-- Dumped by pg_dump version 9.6.2

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: clients; Type: TABLE; Schema: public; Owner: CnC
--

CREATE TABLE clients (
    id integer NOT NULL,
    name character varying,
    appt_date character varying,
    cut_request character varying,
    stylist_id integer
);


ALTER TABLE clients OWNER TO "CnC";

--
-- Name: clients_id_seq; Type: SEQUENCE; Schema: public; Owner: CnC
--

CREATE SEQUENCE clients_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE clients_id_seq OWNER TO "CnC";

--
-- Name: clients_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: CnC
--

ALTER SEQUENCE clients_id_seq OWNED BY clients.id;


--
-- Name: stylists; Type: TABLE; Schema: public; Owner: CnC
--

CREATE TABLE stylists (
    id integer NOT NULL,
    name character varying,
    hire_date character varying,
    base_salary character varying,
    work_schedule character varying
);


ALTER TABLE stylists OWNER TO "CnC";

--
-- Name: stylists_id_seq; Type: SEQUENCE; Schema: public; Owner: CnC
--

CREATE SEQUENCE stylists_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE stylists_id_seq OWNER TO "CnC";

--
-- Name: stylists_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: CnC
--

ALTER SEQUENCE stylists_id_seq OWNED BY stylists.id;


--
-- Name: clients id; Type: DEFAULT; Schema: public; Owner: CnC
--

ALTER TABLE ONLY clients ALTER COLUMN id SET DEFAULT nextval('clients_id_seq'::regclass);


--
-- Name: stylists id; Type: DEFAULT; Schema: public; Owner: CnC
--

ALTER TABLE ONLY stylists ALTER COLUMN id SET DEFAULT nextval('stylists_id_seq'::regclass);


--
-- Data for Name: clients; Type: TABLE DATA; Schema: public; Owner: CnC
--

COPY clients (id, name, appt_date, cut_request, stylist_id) FROM stdin;
\.


--
-- Name: clients_id_seq; Type: SEQUENCE SET; Schema: public; Owner: CnC
--

SELECT pg_catalog.setval('clients_id_seq', 1, false);


--
-- Data for Name: stylists; Type: TABLE DATA; Schema: public; Owner: CnC
--

COPY stylists (id, name, hire_date, base_salary, work_schedule) FROM stdin;
\.


--
-- Name: stylists_id_seq; Type: SEQUENCE SET; Schema: public; Owner: CnC
--

SELECT pg_catalog.setval('stylists_id_seq', 1, false);


--
-- Name: clients clients_pkey; Type: CONSTRAINT; Schema: public; Owner: CnC
--

ALTER TABLE ONLY clients
    ADD CONSTRAINT clients_pkey PRIMARY KEY (id);


--
-- Name: stylists stylists_pkey; Type: CONSTRAINT; Schema: public; Owner: CnC
--

ALTER TABLE ONLY stylists
    ADD CONSTRAINT stylists_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

