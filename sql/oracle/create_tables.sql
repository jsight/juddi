CREATE TABLE BUSINESS_ENTITY
(
  BUSINESS_KEY VARCHAR2(41) NOT NULL,
  AUTHORIZED_NAME VARCHAR2(255) NOT NULL,
  PUBLISHER_ID VARCHAR2(20) NULL,
  OPERATOR VARCHAR2(255) NOT NULL,
  LAST_UPDATE DATE NOT NULL
);

ALTER TABLE BUSINESS_ENTITY ADD ( PRIMARY KEY (BUSINESS_KEY) );

CREATE TABLE BUSINESS_DESCR
(
  BUSINESS_KEY VARCHAR2(41) NOT NULL,
  BUSINESS_DESCR_ID NUMBER(10) NOT NULL,
  LANG_CODE VARCHAR2(2) NULL,
  DESCR VARCHAR2(255) NOT NULL
);

ALTER TABLE BUSINESS_DESCR ADD ( PRIMARY KEY (BUSINESS_KEY, BUSINESS_DESCR_ID) );

CREATE TABLE BUSINESS_CATEGORY
(
  BUSINESS_KEY VARCHAR2(41) NOT NULL,
  CATEGORY_ID NUMBER(10) NOT NULL,
  TMODEL_KEY_REF VARCHAR2(41) NULL,
  KEY_NAME VARCHAR2(255) NULL,
  KEY_VALUE VARCHAR2(255) NOT NULL
);

ALTER TABLE BUSINESS_CATEGORY ADD ( PRIMARY KEY (BUSINESS_KEY, CATEGORY_ID) );

CREATE TABLE BUSINESS_IDENTIFIER
(
  BUSINESS_KEY VARCHAR2(41) NOT NULL,
  IDENTIFIER_ID NUMBER(10) NOT NULL,
  TMODEL_KEY_REF VARCHAR2(41) NULL,
  KEY_NAME VARCHAR2(255) NULL,
  KEY_VALUE VARCHAR2(255) NOT NULL
);

ALTER TABLE BUSINESS_IDENTIFIER ADD ( PRIMARY KEY (BUSINESS_KEY, IDENTIFIER_ID) );

CREATE TABLE BUSINESS_NAME
(
  BUSINESS_KEY VARCHAR2(41) NOT NULL,
  BUSINESS_NAME_ID NUMBER(10) NOT NULL,
  LANG_CODE VARCHAR2(2) NULL,
  NAME VARCHAR2(255) NOT NULL
);

ALTER TABLE BUSINESS_NAME ADD ( PRIMARY KEY (BUSINESS_KEY, BUSINESS_NAME_ID) );

CREATE TABLE CONTACT
(
  BUSINESS_KEY VARCHAR2(41) NOT NULL,
  CONTACT_ID NUMBER(10) NOT NULL,
  USE_TYPE VARCHAR2(255) NULL,
  PERSON_NAME VARCHAR2(255) NOT NULL
);

ALTER TABLE CONTACT ADD ( PRIMARY KEY (BUSINESS_KEY, CONTACT_ID) );

CREATE TABLE CONTACT_DESCR
(
  BUSINESS_KEY VARCHAR2(41) NOT NULL,
  CONTACT_ID NUMBER(10) NOT NULL,
  CONTACT_DESCR_ID NUMBER(10) NOT NULL,
  LANG_CODE VARCHAR2(2) NULL,
  DESCR VARCHAR2(255) NOT NULL
);

ALTER TABLE CONTACT_DESCR ADD ( PRIMARY KEY (BUSINESS_KEY, CONTACT_ID, CONTACT_DESCR_ID) );

CREATE TABLE ADDRESS
(
  BUSINESS_KEY VARCHAR2(41) NOT NULL,
  CONTACT_ID NUMBER(10) NOT NULL,
  ADDRESS_ID NUMBER(10) NOT NULL,
  USE_TYPE VARCHAR2(255) NULL,
  SORT_CODE VARCHAR2(10) NULL,
  TMODEL_KEY VARCHAR2(41) NULL
);

ALTER TABLE ADDRESS ADD ( PRIMARY KEY (BUSINESS_KEY, CONTACT_ID, ADDRESS_ID) );

CREATE TABLE ADDRESS_LINE
(
  BUSINESS_KEY VARCHAR2(41) NOT NULL,
  CONTACT_ID NUMBER(10) NOT NULL,
  ADDRESS_ID NUMBER(10) NOT NULL,
  ADDRESS_LINE_ID NUMBER(10) NOT NULL,
  LINE VARCHAR2(80) NOT NULL,
  KEY_NAME VARCHAR2(255) NULL,
  KEY_VALUE VARCHAR2(255) NULL
);

ALTER TABLE ADDRESS_LINE ADD ( PRIMARY KEY (BUSINESS_KEY, CONTACT_ID, 
	ADDRESS_ID, ADDRESS_LINE_ID) );

CREATE TABLE EMAIL
(
  BUSINESS_KEY VARCHAR2(41) NOT NULL,
  CONTACT_ID NUMBER(10) NOT NULL,
  EMAIL_ID NUMBER(10) NOT NULL,
  USE_TYPE VARCHAR2(255) NULL,
  EMAIL_ADDRESS VARCHAR2(255) NOT NULL
);

ALTER TABLE EMAIL ADD ( PRIMARY KEY (BUSINESS_KEY, CONTACT_ID, EMAIL_ID) );

CREATE TABLE PHONE
(
  BUSINESS_KEY VARCHAR2(41) NOT NULL,
  CONTACT_ID NUMBER(10) NOT NULL,
  PHONE_ID NUMBER(10) NOT NULL,
  USE_TYPE VARCHAR2(255) NULL,
  PHONE_NUMBER VARCHAR2(50) NOT NULL
);

ALTER TABLE PHONE ADD ( PRIMARY KEY (BUSINESS_KEY, CONTACT_ID, PHONE_ID) );


CREATE TABLE DISCOVERY_URL
(
  BUSINESS_KEY VARCHAR2(41) NOT NULL,
  DISCOVERY_URL_ID NUMBER(10) NOT NULL,
  USE_TYPE VARCHAR2(255) NOT NULL,
  URL VARCHAR2(255) NOT NULL
);

ALTER TABLE DISCOVERY_URL ADD ( PRIMARY KEY (BUSINESS_KEY, DISCOVERY_URL_ID) );

CREATE TABLE BUSINESS_SERVICE
(
  BUSINESS_KEY VARCHAR2(41) NOT NULL,
  SERVICE_KEY VARCHAR2(41) NOT NULL,
  LAST_UPDATE DATE NOT NULL
);

ALTER TABLE BUSINESS_SERVICE ADD ( PRIMARY KEY (SERVICE_KEY) );

CREATE TABLE SERVICE_DESCR
(
  SERVICE_KEY VARCHAR2(41) NOT NULL,
  SERVICE_DESCR_ID NUMBER(10) NOT NULL,
  LANG_CODE VARCHAR2(2) NULL,
  DESCR VARCHAR2(255) NOT NULL
);

ALTER TABLE SERVICE_DESCR ADD ( PRIMARY KEY (SERVICE_KEY, SERVICE_DESCR_ID) );

CREATE TABLE SERVICE_CATEGORY
(
  SERVICE_KEY VARCHAR2(41) NOT NULL,
  CATEGORY_ID NUMBER(10) NOT NULL,
  TMODEL_KEY_REF VARCHAR2(41) NULL,
  KEY_NAME VARCHAR2(255) NULL,
  KEY_VALUE VARCHAR2(255) NOT NULL
);

ALTER TABLE SERVICE_CATEGORY ADD ( PRIMARY KEY (SERVICE_KEY, CATEGORY_ID) );

CREATE TABLE SERVICE_NAME
(
  SERVICE_KEY VARCHAR2(41) NOT NULL,
  SERVICE_NAME_ID NUMBER(10) NOT NULL,
  LANG_CODE VARCHAR2(2) NULL,
  NAME VARCHAR2(255) NOT NULL
);

ALTER TABLE SERVICE_NAME ADD ( PRIMARY KEY (SERVICE_KEY, SERVICE_NAME_ID) );

CREATE TABLE BINDING_TEMPLATE
(
  SERVICE_KEY VARCHAR2(41) NOT NULL,
  BINDING_KEY VARCHAR2(41) NOT NULL,
  ACCESS_POINT_TYPE VARCHAR2(20) NULL,
  ACCESS_POINT_URL VARCHAR2(255) NULL,
  HOSTING_REDIRECTOR VARCHAR2(255) NULL,
  LAST_UPDATE DATE NOT NULL
);

ALTER TABLE BINDING_TEMPLATE ADD ( PRIMARY KEY (BINDING_KEY) );

CREATE TABLE BINDING_DESCR
(
  BINDING_KEY VARCHAR2(41) NOT NULL,
  BINDING_DESCR_ID NUMBER(10) NOT NULL,
  LANG_CODE VARCHAR2(2) NULL,
  DESCR VARCHAR2(255) NOT NULL,
  FOREIGN KEY (BINDING_KEY)
    REFERENCES BINDING_TEMPLATE (BINDING_KEY)
);

ALTER TABLE BINDING_DESCR ADD ( PRIMARY KEY (BINDING_KEY, BINDING_DESCR_ID) );

CREATE TABLE BINDING_CATEGORY
(
  BINDING_KEY VARCHAR(41) NOT NULL,
  CATEGORY_ID INT NOT NULL,
  TMODEL_KEY_REF VARCHAR(41) NULL,
  KEY_NAME VARCHAR(255) NULL,
  KEY_VALUE VARCHAR(255) NOT NULL,
  FOREIGN KEY (BINDING_KEY)
    REFERENCES BINDING_TEMPLATE (BINDING_KEY)
);

ALTER TABLE BINDING_CATEGORY ADD ( PRIMARY KEY (BINDING_KEY,CATEGORY_ID) );

CREATE TABLE TMODEL_INSTANCE_INFO
(
  BINDING_KEY VARCHAR2(41) NOT NULL,
  TMODEL_INSTANCE_INFO_ID NUMBER(10) NOT NULL,
  TMODEL_KEY VARCHAR2(41) NOT NULL,
  OVERVIEW_URL VARCHAR2(255) NULL,
  INSTANCE_PARMS VARCHAR2(255) NULL
);

ALTER TABLE TMODEL_INSTANCE_INFO ADD ( PRIMARY KEY (BINDING_KEY, TMODEL_INSTANCE_INFO_ID) );

CREATE TABLE TMODEL_INSTANCE_INFO_DESCR
(
  BINDING_KEY VARCHAR2(41) NOT NULL,
  TMODEL_INSTANCE_INFO_ID NUMBER(10) NOT NULL,
  TMODEL_INSTANCE_INFO_DESCR_ID NUMBER(10) NOT NULL,
  LANG_CODE VARCHAR2(2) NULL,
  DESCR VARCHAR2(255) NOT NULL,
  FOREIGN KEY (BINDING_KEY,TMODEL_INSTANCE_INFO_ID)
    REFERENCES TMODEL_INSTANCE_INFO (BINDING_KEY, TMODEL_INSTANCE_INFO_ID)
);

ALTER TABLE TMODEL_INSTANCE_INFO_DESCR ADD ( PRIMARY KEY (BINDING_KEY, 
	TMODEL_INSTANCE_INFO_ID, TMODEL_INSTANCE_INFO_DESCR_ID) );

CREATE TABLE INSTANCE_DETAILS_DESCR
(
  BINDING_KEY VARCHAR2(41) NOT NULL,
  TMODEL_INSTANCE_INFO_ID NUMBER(10) NOT NULL,
  INSTANCE_DETAILS_DESCR_ID NUMBER(10) NOT NULL,
  LANG_CODE VARCHAR2(2) NULL,
  DESCR VARCHAR2(255) NOT NULL
);

ALTER TABLE INSTANCE_DETAILS_DESCR ADD ( PRIMARY KEY (BINDING_KEY, 
	TMODEL_INSTANCE_INFO_ID, INSTANCE_DETAILS_DESCR_ID) );

CREATE TABLE INSTANCE_DETAILS_DOC_DESCR
(
  BINDING_KEY VARCHAR2(41) NOT NULL,
  TMODEL_INSTANCE_INFO_ID NUMBER(10) NOT NULL,
  INSTANCE_DETAILS_DOC_DESCR_ID NUMBER(10) NOT NULL,
  LANG_CODE VARCHAR2(2) NULL,
  DESCR VARCHAR2(255) NOT NULL
);

ALTER TABLE INSTANCE_DETAILS_DOC_DESCR ADD ( PRIMARY KEY (BINDING_KEY, 
	TMODEL_INSTANCE_INFO_ID, INSTANCE_DETAILS_DOC_DESCR_ID) );

CREATE TABLE TMODEL
(
  TMODEL_KEY VARCHAR2(41) NOT NULL,
  AUTHORIZED_NAME VARCHAR2(255) NOT NULL,
  PUBLISHER_ID VARCHAR2(20) NULL,
  OPERATOR VARCHAR2(255) NOT NULL,
  NAME VARCHAR2(255) NOT NULL,
  OVERVIEW_URL VARCHAR2(255) NULL,
  DELETED VARCHAR2(5) NULL,
  LAST_UPDATE DATE NOT NULL
);

ALTER TABLE TMODEL ADD ( PRIMARY KEY (TMODEL_KEY) );

CREATE TABLE TMODEL_DESCR
(
  TMODEL_KEY VARCHAR2(41) NOT NULL,
  TMODEL_DESCR_ID NUMBER(10) NOT NULL,
  LANG_CODE VARCHAR2(2) NULL,
  DESCR VARCHAR2(255) NOT NULL
);

ALTER TABLE TMODEL_DESCR ADD ( PRIMARY KEY (TMODEL_KEY, TMODEL_DESCR_ID) );

CREATE TABLE TMODEL_CATEGORY
(
  TMODEL_KEY VARCHAR2(41) NOT NULL,
  CATEGORY_ID NUMBER(10) NOT NULL,
  TMODEL_KEY_REF VARCHAR2(255) NULL,
  KEY_NAME VARCHAR2(255) NULL,
  KEY_VALUE VARCHAR2(255) NOT NULL
);

ALTER TABLE TMODEL_CATEGORY ADD ( PRIMARY KEY (TMODEL_KEY, CATEGORY_ID) );

CREATE TABLE TMODEL_IDENTIFIER
(
  TMODEL_KEY VARCHAR2(41) NOT NULL,
  IDENTIFIER_ID NUMBER(10) NOT NULL,
  TMODEL_KEY_REF VARCHAR2(255) NULL,
  KEY_NAME VARCHAR2(255) NULL,
  KEY_VALUE VARCHAR2(255) NOT NULL
);

ALTER TABLE TMODEL_IDENTIFIER ADD ( PRIMARY KEY (TMODEL_KEY, IDENTIFIER_ID) );

CREATE TABLE TMODEL_DOC_DESCR
(
  TMODEL_KEY VARCHAR2(41) NOT NULL,
  TMODEL_DOC_DESCR_ID NUMBER(10) NOT NULL,
  LANG_CODE VARCHAR2(2) NULL,
  DESCR VARCHAR2(255) NOT NULL
);

ALTER TABLE TMODEL_DOC_DESCR ADD ( PRIMARY KEY (TMODEL_KEY, TMODEL_DOC_DESCR_ID) );

CREATE TABLE PUBLISHER_ASSERTION
(
  FROM_KEY VARCHAR2(41) NOT NULL,
  TO_KEY VARCHAR2(41) NOT NULL,
  TMODEL_KEY VARCHAR2(41) NOT NULL,
  KEY_NAME VARCHAR2(255) NOT NULL,
  KEY_VALUE VARCHAR2(255) NOT NULL,
  FROM_CHECK VARCHAR2(5) NOT NULL,
  TO_CHECK VARCHAR2(5) NOT NULL
);

CREATE TABLE PUBLISHER
(
  PUBLISHER_ID VARCHAR2(20) NOT NULL,
  PUBLISHER_NAME VARCHAR2(255) NOT NULL,
  LAST_NAME VARCHAR2(150) NULL,
  FIRST_NAME VARCHAR2(100) NULL,
  MIDDLE_INIT VARCHAR2(5) NULL,
  WORK_PHONE VARCHAR2(50) NULL,
  MOBILE_PHONE VARCHAR2(50) NULL,
  PAGER VARCHAR2(50) NULL,
  EMAIL_ADDRESS VARCHAR2(255) NULL,
  ADMIN VARCHAR2(5) NULL,
  ENABLED VARCHAR2(5) NULL
);

ALTER TABLE PUBLISHER ADD ( PRIMARY KEY (PUBLISHER_ID) );

CREATE TABLE AUTH_TOKEN
(
  AUTH_TOKEN VARCHAR2(51) NOT NULL,
  PUBLISHER_ID VARCHAR2(20) NOT NULL,
  PUBLISHER_NAME VARCHAR2(255) NOT NULL,
  CREATED DATE NOT NULL,
  LAST_USED DATE NOT NULL,
  NUMBER_OF_USES NUMBER(10) NOT NULL,
  TOKEN_STATE NUMBER(10) NOT NULL
);

ALTER TABLE AUTH_TOKEN ADD ( PRIMARY KEY (AUTH_TOKEN) );

CREATE TABLE MONITOR
(
  REMOTE_HOST VARCHAR2(51) NOT NULL,
  REQUEST_URI VARCHAR2(255) NOT NULL,
  CALLED_FUNCTION VARCHAR2(51) NOT NULL,
  UDDI_VERSION VARCHAR2(51) NOT NULL,
  LOG_TIME TIMESTAMP NOT NULL,
  AUTH_TOKEN VARCHAR2(51) NULL, 
  FAULT VARCHAR2(255) NULL
);

ALTER TABLE BUSINESS_DESCR ADD ( FOREIGN KEY (BUSINESS_KEY) REFERENCES BUSINESS_ENTITY);

ALTER TABLE BUSINESS_CATEGORY ADD ( FOREIGN KEY (BUSINESS_KEY)
    REFERENCES BUSINESS_ENTITY );

ALTER TABLE BUSINESS_IDENTIFIER ADD ( FOREIGN KEY (BUSINESS_KEY)
    REFERENCES BUSINESS_ENTITY );

ALTER TABLE BUSINESS_NAME ADD ( FOREIGN KEY (BUSINESS_KEY)
    REFERENCES BUSINESS_ENTITY );

ALTER TABLE CONTACT ADD ( FOREIGN KEY (BUSINESS_KEY)
    REFERENCES BUSINESS_ENTITY );

ALTER TABLE CONTACT_DESCR ADD ( FOREIGN KEY (BUSINESS_KEY,CONTACT_ID)
    REFERENCES CONTACT );

ALTER TABLE ADDRESS ADD ( FOREIGN KEY (BUSINESS_KEY,CONTACT_ID)
    REFERENCES CONTACT );

ALTER TABLE ADDRESS_LINE ADD ( FOREIGN KEY (BUSINESS_KEY,CONTACT_ID,ADDRESS_ID)
    REFERENCES ADDRESS );

ALTER TABLE EMAIL ADD ( FOREIGN KEY (BUSINESS_KEY,CONTACT_ID)
    REFERENCES CONTACT);

ALTER TABLE PHONE ADD ( FOREIGN KEY (BUSINESS_KEY,CONTACT_ID)
    REFERENCES CONTACT );

ALTER TABLE DISCOVERY_URL ADD ( FOREIGN KEY (BUSINESS_KEY)
    REFERENCES BUSINESS_ENTITY );

ALTER TABLE BUSINESS_SERVICE ADD ( FOREIGN KEY (BUSINESS_KEY)
    REFERENCES BUSINESS_ENTITY );

ALTER TABLE SERVICE_DESCR ADD ( FOREIGN KEY (SERVICE_KEY)
    REFERENCES BUSINESS_SERVICE );

ALTER TABLE SERVICE_CATEGORY ADD ( FOREIGN KEY (SERVICE_KEY)
    REFERENCES BUSINESS_SERVICE );

ALTER TABLE SERVICE_NAME ADD ( FOREIGN KEY (SERVICE_KEY)
    REFERENCES BUSINESS_SERVICE );

ALTER TABLE BINDING_TEMPLATE ADD ( FOREIGN KEY (SERVICE_KEY)
    REFERENCES BUSINESS_SERVICE );

ALTER TABLE TMODEL_INSTANCE_INFO ADD ( FOREIGN KEY (BINDING_KEY)
    REFERENCES BINDING_TEMPLATE );

ALTER TABLE INSTANCE_DETAILS_DESCR ADD ( FOREIGN KEY (BINDING_KEY,TMODEL_INSTANCE_INFO_ID)
    REFERENCES TMODEL_INSTANCE_INFO );

ALTER TABLE INSTANCE_DETAILS_DOC_DESCR ADD ( FOREIGN KEY (BINDING_KEY,TMODEL_INSTANCE_INFO_ID)
    REFERENCES TMODEL_INSTANCE_INFO );

ALTER TABLE TMODEL_DESCR ADD ( FOREIGN KEY (TMODEL_KEY)
    REFERENCES TMODEL );

ALTER TABLE TMODEL_CATEGORY ADD ( FOREIGN KEY (TMODEL_KEY)
    REFERENCES TMODEL );

ALTER TABLE TMODEL_IDENTIFIER ADD ( FOREIGN KEY (TMODEL_KEY)
    REFERENCES TMODEL );

ALTER TABLE TMODEL_DOC_DESCR ADD ( FOREIGN KEY (TMODEL_KEY)
    REFERENCES TMODEL );

ALTER TABLE PUBLISHER_ASSERTION ADD ( FOREIGN KEY (FROM_KEY) 
    REFERENCES BUSINESS_ENTITY );

ALTER TABLE PUBLISHER_ASSERTION ADD ( FOREIGN KEY (TO_KEY) 
    REFERENCES BUSINESS_ENTITY );
