--liquibase formatted sql logicalFilePath:sql/migrations/oracle/schema-oracle.sql

--changeset INITIAL_SCHEMA:initial
--preconditions onFail:MARK_RAN onError:HALT onUpdateSql:FAIL
create table ROLES (
    ID NUMBER(3) DEFAULT 0 NOT NULL,
    NAME VARCHAR2(100),
    CONSTRAINT ROLES_PK PRIMARY KEY (ID)
);

CREATE TABLE USERS (
  id NUMBER(19, 0) NOT NULL,
  username VARCHAR2(255 CHAR) NOT NULL,
  password VARCHAR2(255 CHAR) NOT NULL,
  PRIMARY KEY (id)
);
create sequence user_name_sequence start with 1 increment by  1;
ALTER TABLE USERS ADD CONSTRAINT username_unic_constr UNIQUE (username);

CREATE TABLE USERS_ROLES (
    user_id NUMBER(19, 0) NOT NULL,
    role_id NUMBER(19, 0) NOT NULL
);

INSERT INTO ROLES (ID, NAME) VALUES (1, 'ROLE_ADMIN');
INSERT INTO ROLES (ID, NAME) VALUES (2, 'ROLE_USER');
INSERT INTO ROLES (ID, NAME) VALUES (3, 'ROLE_COURIER');

/* password of admin is 12345 */
INSERT INTO USERS (ID, USERNAME, PASSWORD) VALUES (user_name_sequence.nextval, 'admin', '$2a$11$6/I2cZkz2pVUUEjjegYFUO0bJILkyrnHxG.JtqTZdQmh/ZhN9FOYW');
INSERT INTO USERS_ROLES (user_id, role_id) VALUES (1, 1);

CREATE TABLE TEST_SPRING_SESSION
(
    PRIMARY_ID              CHAR (36)       NOT NULL,
    SESSION_ID              CHAR (36),
    CREATION_TIME           NUMBER (19,0)   NOT NULL,
    LAST_ACCESS_TIME        NUMBER (19,0)   NOT NULL,
    MAX_INACTIVE_INTERVAL   NUMBER (10,0)   NOT NULL,
    EXPIRY_TIME             NUMBER (19,0)   NOT NULL,
    PRINCIPAL_NAME          VARCHAR2 (100 CHAR),
    CONSTRAINT TEST_SPRING_SESSION_PK PRIMARY KEY (PRIMARY_ID)
);
CREATE INDEX TEST_SPRING_SESSION_IX1 ON TEST_SPRING_SESSION(LAST_ACCESS_TIME);

CREATE TABLE TEST_SPRING_SESSION_ATTRIBUTES
(
    SESSION_PRIMARY_ID  CHAR (36),
    ATTRIBUTE_NAME      VARCHAR2 (200 CHAR),
    ATTRIBUTE_BYTES     BLOB,
    CONSTRAINT TEST_SPRING_SESSION_ATTRIBUTES_PK PRIMARY KEY (SESSION_PRIMARY_ID, ATTRIBUTE_NAME),
    CONSTRAINT TEST_SPRING_SESSION_ATTRIBUTES_FK FOREIGN KEY (SESSION_PRIMARY_ID) REFERENCES TEST_SPRING_SESSION (PRIMARY_ID) ON DELETE CASCADE
);

CREATE INDEX MP3_SPRING_SESSION_ATTRIBUTES_IX1 on TEST_SPRING_SESSION_ATTRIBUTES (SESSION_PRIMARY_ID);
--rollback not required