-- DROP TABLE BUSINESS_DESCR;
-- DROP TABLE BUSINESS_CATEGORY;
-- DROP TABLE BUSINESS_IDENTIFIER;
-- DROP TABLE BUSINESS_NAME;
-- DROP TABLE DISCOVERY_URL;
-- DROP TABLE ADDRESS_LINE;
-- DROP TABLE ADDRESS;
-- DROP TABLE PHONE;
-- DROP TABLE EMAIL;
-- DROP TABLE CONTACT_DESCR;
-- DROP TABLE CONTACT;
-- DROP TABLE SERVICE_DESCR;
-- DROP TABLE SERVICE_CATEGORY;
-- DROP TABLE SERVICE_NAME;
-- DROP TABLE BINDING_DESCR;
-- DROP TABLE BINDING_CATEGORY;
-- DROP TABLE INSTANCE_DETAILS_DESCR;
-- DROP TABLE INSTANCE_DETAILS_DOC_DESCR;
-- DROP TABLE TMODEL_CATEGORY;
-- DROP TABLE TMODEL_DESCR;
-- DROP TABLE TMODEL_DOC_DESCR;
-- DROP TABLE TMODEL_IDENTIFIER;
-- DROP TABLE TMODEL_INSTANCE_INFO_DESCR;
-- DROP TABLE TMODEL_INSTANCE_INFO;
-- DROP TABLE PUBLISHER_ASSERTION;
-- DROP TABLE TMODEL;
-- DROP TABLE BINDING_TEMPLATE;
-- DROP TABLE BUSINESS_SERVICE;
-- DROP TABLE BUSINESS_ENTITY;
-- DROP TABLE PUBLISHER;
-- DROP TABLE AUTH_TOKEN;

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
  LANG_CODE VARCHAR2(5) NULL,
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
  LANG_CODE VARCHAR2(5) NULL,
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
  LANG_CODE VARCHAR2(5) NULL,
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
  LANG_CODE VARCHAR2(5) NULL,
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
  LANG_CODE VARCHAR2(5) NULL,
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
  LANG_CODE VARCHAR2(5) NULL,
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
  LANG_CODE VARCHAR2(5) NULL,
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
  LANG_CODE VARCHAR2(5) NULL,
  DESCR VARCHAR2(255) NOT NULL
);

ALTER TABLE INSTANCE_DETAILS_DESCR ADD ( PRIMARY KEY (BINDING_KEY, 
	TMODEL_INSTANCE_INFO_ID, INSTANCE_DETAILS_DESCR_ID) );

CREATE TABLE INSTANCE_DETAILS_DOC_DESCR
(
  BINDING_KEY VARCHAR2(41) NOT NULL,
  TMODEL_INSTANCE_INFO_ID NUMBER(10) NOT NULL,
  INSTANCE_DETAILS_DOC_DESCR_ID NUMBER(10) NOT NULL,
  LANG_CODE VARCHAR2(5) NULL,
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
  LANG_CODE VARCHAR2(5) NULL,
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
  LANG_CODE VARCHAR2(5) NULL,
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
  EMAIL_ADDRESS VARCHAR2(255) NULL,
  IS_ADMIN VARCHAR2(5) NULL,
  IS_ENABLED VARCHAR2(5) NULL
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

 INSERT INTO TMODEL (TMODEL_KEY,AUTHORIZED_NAME,PUBLISHER_ID,OPERATOR,NAME,OVERVIEW_URL,LAST_UPDATE)
VALUES ('uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4','Administrator','admin','jUDDI.org','uddi-org:types','http://www.uddi.org/taxonomies/UDDI_Taxonomy_tModels.htm#UDDItypes',SYSDATE);

INSERT INTO TMODEL_DESCR (TMODEL_KEY,TMODEL_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4',0,'en','UDDI Type Taxonomy');
 
INSERT INTO TMODEL_DOC_DESCR (TMODEL_KEY,TMODEL_DOC_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4',0,'en','Taxonomy used to categorize Service Descriptions.');
 
INSERT INTO TMODEL_CATEGORY (TMODEL_KEY,CATEGORY_ID,TMODEL_KEY_REF,KEY_NAME,KEY_VALUE)
VALUES ('uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4',0,'uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4','types','categorization');
 
INSERT INTO TMODEL_CATEGORY (TMODEL_KEY,CATEGORY_ID,TMODEL_KEY_REF,KEY_NAME,KEY_VALUE)
VALUES ('uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4',1,'uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4','types','checked');

INSERT INTO TMODEL (TMODEL_KEY,AUTHORIZED_NAME,PUBLISHER_ID,OPERATOR,NAME,OVERVIEW_URL,LAST_UPDATE)
VALUES ('uuid:DB77450D-9FA8-45D4-A7BC-04411D14E384','Administrator','admin','jUDDI.org','unspsc-org:unspsc:3-1','http://www.uddi.org/taxonomies/UDDI_Taxonomy_tModels.htm#UNSPSC31',SYSDATE);

INSERT INTO TMODEL_DESCR (TMODEL_KEY,TMODEL_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:DB77450D-9FA8-45D4-A7BC-04411D14E384',0,'en','Product Taxonomy: UNSPSC (Version 3.1)');

INSERT INTO TMODEL_DOC_DESCR (TMODEL_KEY,TMODEL_DOC_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:DB77450D-9FA8-45D4-A7BC-04411D14E384',0,'en','This tModel defines the UNSPSC product taxonomy.');

INSERT INTO TMODEL_CATEGORY (TMODEL_KEY,CATEGORY_ID,TMODEL_KEY_REF,KEY_NAME,KEY_VALUE)
VALUES ('uuid:DB77450D-9FA8-45D4-A7BC-04411D14E384',0,'uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4','types','categorization');

INSERT INTO TMODEL (TMODEL_KEY,AUTHORIZED_NAME,PUBLISHER_ID,OPERATOR,NAME,OVERVIEW_URL,LAST_UPDATE)
VALUES ('uuid:CD153257-086A-4237-B336-6BDCBDCC6634','Administrator','admin','jUDDI.org','unspsc-org:unspsc','http://www.uddi.org/taxonomies/UDDI_Taxonomy_tModels.htm#UNSPSC',SYSDATE);

INSERT INTO TMODEL_DESCR (TMODEL_KEY,TMODEL_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:CD153257-086A-4237-B336-6BDCBDCC6634',0,'en','Product Taxonomy: UNSPSC (Version 7.3)');

INSERT INTO TMODEL_DOC_DESCR (TMODEL_KEY,TMODEL_DOC_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:CD153257-086A-4237-B336-6BDCBDCC6634',0,'en','This tModel defines Version 7.3 of the UNSPSC product taxonomy.');

INSERT INTO TMODEL_CATEGORY (TMODEL_KEY,CATEGORY_ID,TMODEL_KEY_REF,KEY_NAME,KEY_VALUE)
VALUES ('uuid:CD153257-086A-4237-B336-6BDCBDCC6634',0,'uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4','types','categorization');

INSERT INTO TMODEL_CATEGORY (TMODEL_KEY,CATEGORY_ID,TMODEL_KEY_REF,KEY_NAME,KEY_VALUE)
VALUES ('uuid:CD153257-086A-4237-B336-6BDCBDCC6634',1,'uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4','types','Checked');

INSERT INTO TMODEL (TMODEL_KEY,AUTHORIZED_NAME,PUBLISHER_ID,OPERATOR,NAME,OVERVIEW_URL,LAST_UPDATE)
VALUES ('uuid:C0B9FE13-179F-413D-8A5B-5004DB8E5BB2','Administrator','admin','jUDDI.org','ntis-gov:naics:1997','http://www.uddi.org/taxonomies/UDDI_Taxonomy_tModels.htm#NAICS',SYSDATE);

INSERT INTO TMODEL_DESCR (TMODEL_KEY,TMODEL_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:C0B9FE13-179F-413D-8A5B-5004DB8E5BB2',0,'en','Business Taxonomy: NAICS(1997 Release)');

INSERT INTO TMODEL_DOC_DESCR (TMODEL_KEY,TMODEL_DOC_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:C0B9FE13-179F-413D-8A5B-5004DB8E5BB2',0,'en','This tModel defines the NAICS industry taxonomy.');

INSERT INTO TMODEL_CATEGORY (TMODEL_KEY,CATEGORY_ID,TMODEL_KEY_REF,KEY_NAME,KEY_VALUE)
VALUES ('uuid:C0B9FE13-179F-413D-8A5B-5004DB8E5BB2',0,'uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4','types','categorization');

INSERT INTO TMODEL_CATEGORY (TMODEL_KEY,CATEGORY_ID,TMODEL_KEY_REF,KEY_NAME,KEY_VALUE)
VALUES ('uuid:C0B9FE13-179F-413D-8A5B-5004DB8E5BB2',1,'uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4','types','checked');

INSERT INTO TMODEL (TMODEL_KEY,AUTHORIZED_NAME,PUBLISHER_ID,OPERATOR,NAME,OVERVIEW_URL,LAST_UPDATE)
VALUES ('uuid:4E49A8D6-D5A2-4FC2-93A0-0411D8D19E88','Administrator','admin','jUDDI.org','uddi-org:iso-ch:3166-1999','http://www.uddi.org/taxonomies/UDDI_Taxonomy_tModels.htm#ISO3166',SYSDATE);

INSERT INTO TMODEL_DESCR (TMODEL_KEY,TMODEL_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:4E49A8D6-D5A2-4FC2-93A0-0411D8D19E88',0,'en','ISO 3166-1:1997 and 3166-2:1998. Codes for names of countries and their subdivisions. Part 1: Country codes. Part 2:Country subdivision codes. Update newsletters include ISO 3166-1 V-1 (1998-02-05), V-2 (1999-10-01), ISO 3166-2 I-1 (1998)');

INSERT INTO TMODEL_DOC_DESCR (TMODEL_KEY,TMODEL_DOC_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:4E49A8D6-D5A2-4FC2-93A0-0411D8D19E88',0,'en','Taxonomy used to categorize entries by geographic location.');

INSERT INTO TMODEL_CATEGORY (TMODEL_KEY,CATEGORY_ID,TMODEL_KEY_REF,KEY_NAME,KEY_VALUE)
VALUES ('uuid:4E49A8D6-D5A2-4FC2-93A0-0411D8D19E88',0,'uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4','types','categorization');

INSERT INTO TMODEL_CATEGORY (TMODEL_KEY,CATEGORY_ID,TMODEL_KEY_REF,KEY_NAME,KEY_VALUE)
VALUES ('uuid:4E49A8D6-D5A2-4FC2-93A0-0411D8D19E88',1,'uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4','types','checked');

INSERT INTO TMODEL (TMODEL_KEY,AUTHORIZED_NAME,PUBLISHER_ID,OPERATOR,NAME,OVERVIEW_URL,LAST_UPDATE)
VALUES ('uuid:A035A07C-F362-44DD-8F95-E2B134BF43B4','Administrator','admin','jUDDI.org','uddi-org:general_keywords','http://www.uddi.org/taxonomies/UDDI_Taxonomy_tModels.htm#GenKW',SYSDATE);

INSERT INTO TMODEL_DESCR (TMODEL_KEY,TMODEL_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:A035A07C-F362-44DD-8F95-E2B134BF43B4',0,'en','Special taxonomy consisting of namespace identifiers and the keywords associated with the namespaces');

INSERT INTO TMODEL_DOC_DESCR (TMODEL_KEY,TMODEL_DOC_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:A035A07C-F362-44DD-8F95-E2B134BF43B4',0,'en','This tModel defines an unidentified taxonomy.');

INSERT INTO TMODEL_CATEGORY (TMODEL_KEY,CATEGORY_ID,TMODEL_KEY_REF,KEY_NAME,KEY_VALUE)
VALUES ('uuid:A035A07C-F362-44DD-8F95-E2B134BF43B4',0,'uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4','types','categorization');

INSERT INTO TMODEL (TMODEL_KEY,AUTHORIZED_NAME,PUBLISHER_ID,OPERATOR,NAME,OVERVIEW_URL,LAST_UPDATE)
VALUES ('uuid:4064C064-6D14-4F35-8953-9652106476A9','Administrator','admin','jUDDI.org','uddi-org:owningBusiness','http://www.uddi.org/taxonomies/UDDI_Taxonomy_tModels.htm#owningBusiness',SYSDATE);

INSERT INTO TMODEL_DESCR (TMODEL_KEY,TMODEL_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:4064C064-6D14-4F35-8953-9652106476A9',0,'en','A pointer to a businessEntity that owns the tagged data.');

INSERT INTO TMODEL_DOC_DESCR (TMODEL_KEY,TMODEL_DOC_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:4064C064-6D14-4F35-8953-9652106476A9',0,'en','This tModel indicates the businessEntity that published or owns the tagged tModel. Used with tModels to establish an "owned" relationship with a registered businessEntity.');

INSERT INTO TMODEL_CATEGORY (TMODEL_KEY,CATEGORY_ID,TMODEL_KEY_REF,KEY_NAME,KEY_VALUE)
VALUES ('uuid:4064C064-6D14-4F35-8953-9652106476A9',0,'uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4','types','categorization');

INSERT INTO TMODEL_CATEGORY (TMODEL_KEY,CATEGORY_ID,TMODEL_KEY_REF,KEY_NAME,KEY_VALUE)
VALUES ('uuid:4064C064-6D14-4F35-8953-9652106476A9',1,'uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4','types','checked');

INSERT INTO TMODEL (TMODEL_KEY,AUTHORIZED_NAME,PUBLISHER_ID,OPERATOR,NAME,OVERVIEW_URL,LAST_UPDATE)
VALUES ('uuid:807A2C6A-EE22-470D-ADC7-E0424A337C03','Administrator','admin','jUDDI.org','uddi-org:relationships','http://www.uddi.org/taxonomies/UDDI_Taxonomy_tModels.htm#Relationships',SYSDATE);

INSERT INTO TMODEL_DESCR (TMODEL_KEY,TMODEL_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:807A2C6A-EE22-470D-ADC7-E0424A337C03',0,'en','Starter set classifications of businessEntity relationships');

INSERT INTO TMODEL_DOC_DESCR (TMODEL_KEY,TMODEL_DOC_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:807A2C6A-EE22-470D-ADC7-E0424A337C03',0,'en','This tModel is used to describe business relationships. Used in the publisher assertion messages.');

INSERT INTO TMODEL_CATEGORY (TMODEL_KEY,CATEGORY_ID,TMODEL_KEY_REF,KEY_NAME,KEY_VALUE)
VALUES ('uuid:807A2C6A-EE22-470D-ADC7-E0424A337C03',0,'uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4','types','relationship');

INSERT INTO TMODEL (TMODEL_KEY,AUTHORIZED_NAME,PUBLISHER_ID,OPERATOR,NAME,OVERVIEW_URL,LAST_UPDATE)
VALUES ('uuid:327A56F0-3299-4461-BC23-5CD513E95C55','Administrator','admin','jUDDI.org','uddi-org:operators','http://www.uddi.org/taxonomies/UDDI_Taxonomy_tModels.htm#Operators',SYSDATE);

INSERT INTO TMODEL_DESCR (TMODEL_KEY,TMODEL_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:327A56F0-3299-4461-BC23-5CD513E95C55',0,'en','Taxonomy for categorizing the businessEntity of an operator of a registry.');

INSERT INTO TMODEL_DOC_DESCR (TMODEL_KEY,TMODEL_DOC_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:327A56F0-3299-4461-BC23-5CD513E95C55',0,'en','This checked value set is used to identify UDDI operators.');

INSERT INTO TMODEL_CATEGORY (TMODEL_KEY,CATEGORY_ID,TMODEL_KEY_REF,KEY_NAME,KEY_VALUE)
VALUES ('uuid:327A56F0-3299-4461-BC23-5CD513E95C55',0,'uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4','types','categorization');

INSERT INTO TMODEL_CATEGORY (TMODEL_KEY,CATEGORY_ID,TMODEL_KEY_REF,KEY_NAME,KEY_VALUE)
VALUES ('uuid:327A56F0-3299-4461-BC23-5CD513E95C55',1,'uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4','types','checked');

INSERT INTO TMODEL (TMODEL_KEY,AUTHORIZED_NAME,PUBLISHER_ID,OPERATOR,NAME,OVERVIEW_URL,LAST_UPDATE)
VALUES ('uuid:E59AE320-77A5-11D5-B898-0004AC49CC1E','Administrator','admin','jUDDI.org','uddi-org:isReplacedBy','http://www.uddi.org/taxonomies/UDDI_Taxonomy_tModels.htm#IsReplacedBy',SYSDATE);

INSERT INTO TMODEL_DESCR (TMODEL_KEY,TMODEL_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:E59AE320-77A5-11D5-B898-0004AC49CC1E',0,'en','An identifier system used to point (using UDDI keys) to the tModel (or businessEntity) that is the logical replacement for the one in which isReplacedBy is used');

INSERT INTO TMODEL_DOC_DESCR (TMODEL_KEY,TMODEL_DOC_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:E59AE320-77A5-11D5-B898-0004AC49CC1E',0,'en','This is a checked value set.');

INSERT INTO TMODEL_CATEGORY (TMODEL_KEY,CATEGORY_ID,TMODEL_KEY_REF,KEY_NAME,KEY_VALUE)
VALUES ('uuid:E59AE320-77A5-11D5-B898-0004AC49CC1E',0,'uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4','types','identifier');

INSERT INTO TMODEL_CATEGORY (TMODEL_KEY,CATEGORY_ID,TMODEL_KEY_REF,KEY_NAME,KEY_VALUE)
VALUES ('uuid:E59AE320-77A5-11D5-B898-0004AC49CC1E',1,'uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4','types','checked');

INSERT INTO TMODEL (TMODEL_KEY,AUTHORIZED_NAME,PUBLISHER_ID,OPERATOR,NAME,OVERVIEW_URL,LAST_UPDATE)
VALUES ('uuid:8609C81E-EE1F-4D5A-B202-3EB13AD01823','Administrator','admin','jUDDI.org','dnb-com:D-U-N-S','http://www.uddi.org/taxonomies/UDDI_Taxonomy_tModels.htm#D-U-N-S',SYSDATE);

INSERT INTO TMODEL_DESCR (TMODEL_KEY,TMODEL_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:8609C81E-EE1F-4D5A-B202-3EB13AD01823',0,'en','Dun' || chr(38) || 'Bradstreet D-U-N-S� Number');

INSERT INTO TMODEL_DOC_DESCR (TMODEL_KEY,TMODEL_DOC_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:8609C81E-EE1F-4D5A-B202-3EB13AD01823',0,'en','This tModel is used for the Dun' || chr(38) || 'Bradstreet D-U-N-S� Number identifier.');

INSERT INTO TMODEL_CATEGORY (TMODEL_KEY,CATEGORY_ID,TMODEL_KEY_REF,KEY_NAME,KEY_VALUE)
VALUES ('uuid:8609C81E-EE1F-4D5A-B202-3EB13AD01823',0,'uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4','types','identifier');

INSERT INTO TMODEL (TMODEL_KEY,AUTHORIZED_NAME,PUBLISHER_ID,OPERATOR,NAME,OVERVIEW_URL,LAST_UPDATE)
VALUES ('uuid:B1B1BAF5-2329-43E6-AE13-BA8E97195039','Administrator','admin','jUDDI.org','thomasregister-com:supplierID','http://www.uddi.org/taxonomies/UDDI_Taxonomy_tModels.htm#Thomas',SYSDATE);

INSERT INTO TMODEL_DESCR (TMODEL_KEY,TMODEL_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:B1B1BAF5-2329-43E6-AE13-BA8E97195039',0,'en','Thomas Registry Suppliers');

INSERT INTO TMODEL_DOC_DESCR (TMODEL_KEY,TMODEL_DOC_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:B1B1BAF5-2329-43E6-AE13-BA8E97195039',0,'en','This tModel is used for the Thomas Register supplier identifier codes.');

INSERT INTO TMODEL_CATEGORY (TMODEL_KEY,CATEGORY_ID,TMODEL_KEY_REF,KEY_NAME,KEY_VALUE)
VALUES ('uuid:B1B1BAF5-2329-43E6-AE13-BA8E97195039',0,'uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4','types','identifier');
 
 
 
 

INSERT INTO TMODEL (TMODEL_KEY,AUTHORIZED_NAME,PUBLISHER_ID,OPERATOR,NAME,OVERVIEW_URL,LAST_UPDATE)
VALUES ('uuid:6E090AFA-33E5-36EB-81B7-1CA18373F457','Administrator','admin','jUDDI.org','uddi-org:wsdl:types','http://www.oasis-open.org/committees/uddi-spec/doc/tn/uddi-spec-tc-tn-wsdl-v2.htm#wsdlTypes',SYSDATE);

INSERT INTO TMODEL_DESCR (TMODEL_KEY,TMODEL_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:6E090AFA-33E5-36EB-81B7-1CA18373F457',0,'en','WSDL Type Category System');

INSERT INTO TMODEL_DOC_DESCR (TMODEL_KEY,TMODEL_DOC_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:6E090AFA-33E5-36EB-81B7-1CA18373F457',0,'en','The WSDL Entity Type tModel uses a number of UDDI entities to represent the various entities within a WSDL document.');

INSERT INTO TMODEL_CATEGORY (TMODEL_KEY,CATEGORY_ID,TMODEL_KEY_REF,KEY_NAME,KEY_VALUE)
VALUES ('uuid:6E090AFA-33E5-36EB-81B7-1CA18373F457',0,'uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4','uddi-org:types','unchecked');

INSERT INTO TMODEL_CATEGORY (TMODEL_KEY,CATEGORY_ID,TMODEL_KEY_REF,KEY_NAME,KEY_VALUE)
VALUES ('uuid:6E090AFA-33E5-36EB-81B7-1CA18373F457',1,'uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4','uddi-org:types','categorization');


INSERT INTO TMODEL (TMODEL_KEY,AUTHORIZED_NAME,PUBLISHER_ID,OPERATOR,NAME,OVERVIEW_URL,LAST_UPDATE)
VALUES ('uuid:D01987D1-AB2E-3013-9BE2-2A66EB99D824','Administrator','admin','jUDDI.org','uddi-org:xml:namespace','http://www.oasis-open.org/committees/uddi-spec/doc/tn/uddi-spec-tc-tn-wsdl-v2.htm#xmlNamespace',SYSDATE);

INSERT INTO TMODEL_DESCR (TMODEL_KEY,TMODEL_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:D01987D1-AB2E-3013-9BE2-2A66EB99D824',0,'en','A category system used to indicate namespaces');

INSERT INTO TMODEL_DOC_DESCR (TMODEL_KEY,TMODEL_DOC_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:D01987D1-AB2E-3013-9BE2-2A66EB99D824',0,'en','A namespace provides necessary qualifying information about a technical concept or model. The XML Namespace tModel provides a mechanism to associate a namespace with a UDDI entity.');

INSERT INTO TMODEL_CATEGORY (TMODEL_KEY,CATEGORY_ID,TMODEL_KEY_REF,KEY_NAME,KEY_VALUE)
VALUES ('uuid:D01987D1-AB2E-3013-9BE2-2A66EB99D824',0,'uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4','uddi-org:types','unchecked');

INSERT INTO TMODEL_CATEGORY (TMODEL_KEY,CATEGORY_ID,TMODEL_KEY_REF,KEY_NAME,KEY_VALUE)
VALUES ('uuid:D01987D1-AB2E-3013-9BE2-2A66EB99D824',1,'uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4','uddi-org:types','categorization');


INSERT INTO TMODEL (TMODEL_KEY,AUTHORIZED_NAME,PUBLISHER_ID,OPERATOR,NAME,OVERVIEW_URL,LAST_UPDATE)
VALUES ('uuid:2EC65201-9109-3919-9BEC-C9DBEFCACCF6','Administrator','admin','jUDDI.org','uddi-org:xml:localName','http://www.oasis-open.org/committees/uddi-spec/doc/tn/uddi-spec-tc-tn-wsdl-v2.htm#xmlLocalName',SYSDATE);

INSERT INTO TMODEL_DESCR (TMODEL_KEY,TMODEL_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:2EC65201-9109-3919-9BEC-C9DBEFCACCF6',0,'en','A category system used to indicate XML local names');

INSERT INTO TMODEL_DOC_DESCR (TMODEL_KEY,TMODEL_DOC_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:2EC65201-9109-3919-9BEC-C9DBEFCACCF6',0,'en','The XML Local Name tModel provides a mechanism to indicate the name attribute for the uddi:businessService.');

INSERT INTO TMODEL_CATEGORY (TMODEL_KEY,CATEGORY_ID,TMODEL_KEY_REF,KEY_NAME,KEY_VALUE)
VALUES ('uuid:2EC65201-9109-3919-9BEC-C9DBEFCACCF6',0,'uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4','uddi-org:types','unchecked');

INSERT INTO TMODEL_CATEGORY (TMODEL_KEY,CATEGORY_ID,TMODEL_KEY_REF,KEY_NAME,KEY_VALUE)
VALUES ('uuid:2EC65201-9109-3919-9BEC-C9DBEFCACCF6',1,'uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4','uddi-org:types','categorization');


INSERT INTO TMODEL (TMODEL_KEY,AUTHORIZED_NAME,PUBLISHER_ID,OPERATOR,NAME,OVERVIEW_URL,LAST_UPDATE)
VALUES ('uuid:082B0851-25D8-303C-B332-F24A6D53E38E','Administrator','admin','jUDDI.org','uddi-org:wsdl:portTypeReference','http://www.oasis-open.org/committees/uddi-spec/doc/tn/uddi-spec-tc-tn-wsdl-v2.htm#portTypeReference',SYSDATE);

INSERT INTO TMODEL_DESCR (TMODEL_KEY,TMODEL_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:082B0851-25D8-303C-B332-F24A6D53E38E',0,'en','A category system used to reference a wsdl:portType tModel');

INSERT INTO TMODEL_DOC_DESCR (TMODEL_KEY,TMODEL_DOC_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:082B0851-25D8-303C-B332-F24A6D53E38E',0,'en','The WSDL portType Reference category system provides a mechanism to indicate that a UDDI entity has a relationship with a certain wsdl:portType tModel.');

INSERT INTO TMODEL_CATEGORY (TMODEL_KEY,CATEGORY_ID,TMODEL_KEY_REF,KEY_NAME,KEY_VALUE)
VALUES ('uuid:082B0851-25D8-303C-B332-F24A6D53E38E',0,'uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4','uddi-org:types','checked');

INSERT INTO TMODEL_CATEGORY (TMODEL_KEY,CATEGORY_ID,TMODEL_KEY_REF,KEY_NAME,KEY_VALUE)
VALUES ('uuid:082B0851-25D8-303C-B332-F24A6D53E38E',1,'uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4','uddi-org:types','categorization');


INSERT INTO TMODEL (TMODEL_KEY,AUTHORIZED_NAME,PUBLISHER_ID,OPERATOR,NAME,OVERVIEW_URL,LAST_UPDATE)
VALUES ('uuid:AA254698-93DE-3870-8DF3-A5C075D64A0E','Administrator','admin','jUDDI.org','uddi-org:protocol:soap','http://www.oasis-open.org/committees/uddi-spec/doc/tn/uddi-spec-tc-tn-wsdl-v2.htm#soap',SYSDATE);

INSERT INTO TMODEL_DESCR (TMODEL_KEY,TMODEL_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:AA254698-93DE-3870-8DF3-A5C075D64A0E',0,'en','A tModel that represents the SOAP 1.1 protocol');

INSERT INTO TMODEL_DOC_DESCR (TMODEL_KEY,TMODEL_DOC_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:AA254698-93DE-3870-8DF3-A5C075D64A0E',0,'en','The SOAP Protocol tModel can be used to indicate that a Web service supports the SOAP 1.1 protocol.');

INSERT INTO TMODEL_CATEGORY (TMODEL_KEY,CATEGORY_ID,TMODEL_KEY_REF,KEY_NAME,KEY_VALUE)
VALUES ('uuid:AA254698-93DE-3870-8DF3-A5C075D64A0E',0,'uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4','uddi-org:types','protocol');


INSERT INTO TMODEL (TMODEL_KEY,AUTHORIZED_NAME,PUBLISHER_ID,OPERATOR,NAME,OVERVIEW_URL,LAST_UPDATE)
VALUES ('uuid:6E10B91B-BABC-3442-B8FC-5A3C8FDE0794','Administrator','admin','jUDDI.org','uddi-org:protocol:http','http://www.oasis-open.org/committees/uddi-spec/doc/tn/uddi-spec-tc-tn-wsdl-v2.htm#http',SYSDATE);

INSERT INTO TMODEL_DESCR (TMODEL_KEY,TMODEL_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:6E10B91B-BABC-3442-B8FC-5A3C8FDE0794',0,'en','A tModel that represents the HTTP protocol');

INSERT INTO TMODEL_DOC_DESCR (TMODEL_KEY,TMODEL_DOC_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:6E10B91B-BABC-3442-B8FC-5A3C8FDE0794',0,'en','The HTTP Protocol tModel can be used to indicate that a Web service supports the HTTP protocol.');

INSERT INTO TMODEL_CATEGORY (TMODEL_KEY,CATEGORY_ID,TMODEL_KEY_REF,KEY_NAME,KEY_VALUE)
VALUES ('uuid:6E10B91B-BABC-3442-B8FC-5A3C8FDE0794',0,'uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4','uddi-org:types','protocol');


INSERT INTO TMODEL (TMODEL_KEY,AUTHORIZED_NAME,PUBLISHER_ID,OPERATOR,NAME,OVERVIEW_URL,LAST_UPDATE)
VALUES ('uuid:4DC74177-7806-34D9-AECD-33C57DC3A865','Administrator','admin','jUDDI.org','uddi-org:wsdl:categorization:protocol','http://www.oasis-open.org/committees/uddi-spec/doc/tn/uddi-spec-tc-tn-wsdl-v2.htm#protocol',SYSDATE);

INSERT INTO TMODEL_DESCR (TMODEL_KEY,TMODEL_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:4DC74177-7806-34D9-AECD-33C57DC3A865',0,'en','Category system used to describe the protocol supported by a wsdl:binding.');

INSERT INTO TMODEL_DOC_DESCR (TMODEL_KEY,TMODEL_DOC_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:4DC74177-7806-34D9-AECD-33C57DC3A865',0,'en','The Protocol Categorization tModel provides a mechanism to capture this protocol information in the UDDI binding tModel.');

INSERT INTO TMODEL_CATEGORY (TMODEL_KEY,CATEGORY_ID,TMODEL_KEY_REF,KEY_NAME,KEY_VALUE)
VALUES ('uuid:4DC74177-7806-34D9-AECD-33C57DC3A865',0,'uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4','uddi-org:types','categorization');

INSERT INTO TMODEL_CATEGORY (TMODEL_KEY,CATEGORY_ID,TMODEL_KEY_REF,KEY_NAME,KEY_VALUE)
VALUES ('uuid:4DC74177-7806-34D9-AECD-33C57DC3A865',1,'uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4','uddi-org:types','checked');


INSERT INTO TMODEL (TMODEL_KEY,AUTHORIZED_NAME,PUBLISHER_ID,OPERATOR,NAME,OVERVIEW_URL,LAST_UPDATE)
VALUES ('uuid:E5C43936-86E4-37BF-8196-1D04B35C0099','Administrator','admin','jUDDI.org','uddi-org:wsdl:categorization:transport','http://www.oasis-open.org/committees/uddi-spec/doc/tn/uddi-spec-tc-tn-wsdl-v2.htm#transport',SYSDATE);

INSERT INTO TMODEL_DESCR (TMODEL_KEY,TMODEL_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:E5C43936-86E4-37BF-8196-1D04B35C0099',0,'en','Category system used to describe the transport supported by a wsdl:binding.');

INSERT INTO TMODEL_DOC_DESCR (TMODEL_KEY,TMODEL_DOC_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:E5C43936-86E4-37BF-8196-1D04B35C0099',0,'en','The Transport Categorization tModel provides a mechanism to capture transport information in the UDDI binding tModel which allows a user to search for bindings that implement a specific transport protocol.');

INSERT INTO TMODEL_CATEGORY (TMODEL_KEY,CATEGORY_ID,TMODEL_KEY_REF,KEY_NAME,KEY_VALUE)
VALUES ('uuid:E5C43936-86E4-37BF-8196-1D04B35C0099',0,'uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4','uddi-org:types','categorization');

INSERT INTO TMODEL_CATEGORY (TMODEL_KEY,CATEGORY_ID,TMODEL_KEY_REF,KEY_NAME,KEY_VALUE)
VALUES ('uuid:E5C43936-86E4-37BF-8196-1D04B35C0099',1,'uuid:C1ACF26D-9672-4404-9D70-39B756E62AB4','uddi-org:types','checked');


INSERT INTO TMODEL (TMODEL_KEY,AUTHORIZED_NAME,PUBLISHER_ID,OPERATOR,NAME,OVERVIEW_URL,LAST_UPDATE)
VALUES ('uuid:AD61DE98-4DB8-31B2-A299-A2373DC97212','Administrator','admin','jUDDI.org','uddi-org:wsdl:address','http://www.oasis-open.org/committees/uddi-spec/doc/tn/uddi-spec-tc-tn-wsdl-v2.htm#Address',SYSDATE);

INSERT INTO TMODEL_DESCR (TMODEL_KEY,TMODEL_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:AD61DE98-4DB8-31B2-A299-A2373DC97212',0,'en','A tModel used to indicate the WSDL address option.');

INSERT INTO TMODEL_DOC_DESCR (TMODEL_KEY,TMODEL_DOC_DESCR_ID,LANG_CODE,DESCR)
VALUES ('uuid:AD61DE98-4DB8-31B2-A299-A2373DC97212',0,'en','The WSDL Address tModel provides A mechanism to indicate that the endpoint address should be obtained from a WSDL document.');