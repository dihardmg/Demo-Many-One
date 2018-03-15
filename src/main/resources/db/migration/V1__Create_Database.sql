
CREATE TABLE c_security_permission (
    id character varying(255) NOT NULL,
    permission_label character varying(255) NOT NULL,
    permission_value character varying(255) NOT NULL
);

CREATE TABLE c_security_role (
    id character varying(255) NOT NULL,
    description character varying(255),
    name character varying(255) NOT NULL
);

CREATE TABLE c_security_role_permission (
    id_role character varying(255) NOT NULL,
    id_permission character varying(255) NOT NULL
);

CREATE TABLE c_security_user (
    id character varying(255) NOT NULL,
    active boolean NOT NULL,
    username character varying(255) NOT NULL,
    id_role character varying(255) NOT NULL
);

CREATE TABLE c_security_user_password (
    id_user character varying(36) NOT NULL,
    password character varying(255) NOT NULL
);


CREATE TABLE pulsa(
id character varying(255) NOT NULL PRIMARY KEY,
  paket character varying(255) NOT NULL,
  keterangan character varying(255)
);


CREATE TABLE person(
  id character varying(255) NOT NULL PRIMARY key,
  nama character varying(255) NOT NULL,
  nohp character varying(12) NOT  NULL ,
  id_pulsa character varying(255) NOT NULL
);



ALTER TABLE person
ADD CONSTRAINT fk_pulsa_person FOREIGN KEY (id_pulsa) REFERENCES pulsa(id);
