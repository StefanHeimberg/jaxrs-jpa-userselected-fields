-- ****************************************************************************
-- Version : 1.0.0.001 - Initial Schema
-- Author  : Stefan Heimberg <kontakt@stefanheimberg.ch>
-- Created : 12.09.2018
-- ****************************************************************************

-- ============ TODO ============

CREATE TABLE T_TODO (
    ID                  INTEGER NOT NULL,
    SUMMARY             VARCHAR(255) NOT NULL,
    DESCRIPTION         VARCHAR(4000),
    INSERTTIMESTAMP     TIMESTAMP NOT NULL DEFAULT current_timestamp,
    UPDATETIMESTAMP     TIMESTAMP,
    VERSION             INTEGER DEFAULT 1,
    CONSTRAINT todo_pk
        PRIMARY KEY (ID)
);
