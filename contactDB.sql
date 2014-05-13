--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.4
-- Dumped by pg_dump version 9.3.4
-- Started on 2014-05-13 18:19:25

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 175 (class 3079 OID 11750)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 1963 (class 0 OID 0)
-- Dependencies: 175
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 174 (class 1259 OID 16468)
-- Name: auth_user; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE auth_user (
    userid integer NOT NULL,
    username character varying(50) NOT NULL,
    password character varying(50) NOT NULL,
    enabled boolean DEFAULT true,
    user_role_id integer NOT NULL
);


ALTER TABLE public.auth_user OWNER TO postgres;

--
-- TOC entry 173 (class 1259 OID 16466)
-- Name: auth_user_userid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE auth_user_userid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.auth_user_userid_seq OWNER TO postgres;

--
-- TOC entry 1964 (class 0 OID 0)
-- Dependencies: 173
-- Name: auth_user_userid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE auth_user_userid_seq OWNED BY auth_user.userid;


--
-- TOC entry 171 (class 1259 OID 16406)
-- Name: contact; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE contact (
    contactid integer NOT NULL,
    name character varying(50) NOT NULL,
    age integer NOT NULL,
    address character varying(100)
);


ALTER TABLE public.contact OWNER TO postgres;

--
-- TOC entry 170 (class 1259 OID 16404)
-- Name: contact_contactid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE contact_contactid_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.contact_contactid_seq OWNER TO postgres;

--
-- TOC entry 1965 (class 0 OID 0)
-- Dependencies: 170
-- Name: contact_contactid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE contact_contactid_seq OWNED BY contact.contactid;


--
-- TOC entry 172 (class 1259 OID 16444)
-- Name: user_role; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE user_role (
    user_role_id integer NOT NULL,
    role character varying(50) NOT NULL,
    description text
);


ALTER TABLE public.user_role OWNER TO postgres;

--
-- TOC entry 1835 (class 2604 OID 16471)
-- Name: userid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY auth_user ALTER COLUMN userid SET DEFAULT nextval('auth_user_userid_seq'::regclass);


--
-- TOC entry 1834 (class 2604 OID 16409)
-- Name: contactid; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY contact ALTER COLUMN contactid SET DEFAULT nextval('contact_contactid_seq'::regclass);


--
-- TOC entry 1955 (class 0 OID 16468)
-- Dependencies: 174
-- Data for Name: auth_user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY auth_user (userid, username, password, enabled, user_role_id) FROM stdin;
1	admin	admin	t	1
2	user	user	t	2
3	thuan	thuan	t	2
\.


--
-- TOC entry 1966 (class 0 OID 0)
-- Dependencies: 173
-- Name: auth_user_userid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('auth_user_userid_seq', 3, true);


--
-- TOC entry 1952 (class 0 OID 16406)
-- Dependencies: 171
-- Data for Name: contact; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY contact (contactid, name, age, address) FROM stdin;
1	Tom	25	New York
2	Peter	22	Paris
3	Mary	20	London
4	Daisy	23	Tokyo
\.


--
-- TOC entry 1967 (class 0 OID 0)
-- Dependencies: 170
-- Name: contact_contactid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('contact_contactid_seq', 4, true);


--
-- TOC entry 1953 (class 0 OID 16444)
-- Dependencies: 172
-- Data for Name: user_role; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY user_role (user_role_id, role, description) FROM stdin;
1	ROLE_ADMIN	\N
2	ROLE_USER	\N
\.


--
-- TOC entry 1838 (class 2606 OID 16411)
-- Name: contact_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY contact
    ADD CONSTRAINT contact_pkey PRIMARY KEY (contactid);


--
-- TOC entry 1842 (class 2606 OID 16474)
-- Name: user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY auth_user
    ADD CONSTRAINT user_pkey PRIMARY KEY (userid);


--
-- TOC entry 1840 (class 2606 OID 16451)
-- Name: user_role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY user_role
    ADD CONSTRAINT user_role_pkey PRIMARY KEY (user_role_id);


--
-- TOC entry 1843 (class 2606 OID 16475)
-- Name: user_user_role_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY auth_user
    ADD CONSTRAINT user_user_role_id_fkey FOREIGN KEY (user_role_id) REFERENCES user_role(user_role_id);


--
-- TOC entry 1962 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2014-05-13 18:19:26

--
-- PostgreSQL database dump complete
--

