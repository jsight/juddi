/*
 * Copyright 2013 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.juddi.api.impl;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.juddi.Registry;
import org.apache.juddi.v3.client.UDDIConstants;
import org.apache.juddi.v3.tck.TckPublisher;
import org.apache.juddi.v3.tck.TckSecurity;
import org.apache.juddi.v3.tck.TckTModel;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.uddi.api_v3.CategoryBag;
import org.uddi.api_v3.Description;
import org.uddi.api_v3.KeyedReference;
import org.uddi.api_v3.Name;
import org.uddi.api_v3.SaveTModel;
import org.uddi.api_v3.TModel;
import org.uddi.v3_service.UDDIInquiryPortType;
import org.uddi.v3_service.UDDIPublicationPortType;
import org.uddi.v3_service.UDDISecurityPortType;

/**
 *
 * @author Alex O'Ree
 */
public class API_150_ValueSetValidationTest {

        private static Log logger = LogFactory.getLog(API_141_JIRATest.class);
        static UDDISecurityPortType security = new UDDISecurityImpl();
        static UDDIInquiryPortType inquiry = new UDDIInquiryImpl();
        static UDDIPublicationPortType publication = new UDDIPublicationImpl();
        static JUDDIApiImpl juddi = new JUDDIApiImpl();
        static UDDIValueSetValidationImpl vsv = new UDDIValueSetValidationImpl();
        static TckTModel tckTModel = new TckTModel(new UDDIPublicationImpl(), new UDDIInquiryImpl());
        protected static String authInfoJoe = null;
        protected static String authInfoSam = null;
        protected static String authInfoRoot = null;
        protected static String authInfoUDDI = null;

        @AfterClass
        public static void stopManager() throws ConfigurationException {
                Registry.stop();
        }

        @BeforeClass
        public static void startManager() throws ConfigurationException {
                Registry.start();

                logger.debug("Getting auth tokens..");
                try {
                        authInfoJoe = TckSecurity.getAuthToken(security, TckPublisher.getJoePublisherId(), TckPublisher.getJoePassword());
                        authInfoSam = TckSecurity.getAuthToken(security, TckPublisher.getSamPublisherId(), TckPublisher.getSamPassword());
                        authInfoRoot = TckSecurity.getAuthToken(security, TckPublisher.getRootPublisherId(), TckPublisher.getRootPassword());
                        authInfoUDDI = TckSecurity.getAuthToken(security, TckPublisher.getUDDIPublisherId(), TckPublisher.getUDDIPassword());
                        Assert.assertNotNull(authInfoJoe);
                        Assert.assertNotNull(authInfoSam);
                        String authInfoUDDI = TckSecurity.getAuthToken(security, TckPublisher.getUDDIPublisherId(), TckPublisher.getUDDIPassword());
                        tckTModel.saveUDDIPublisherTmodel(authInfoUDDI);
                        tckTModel.saveTModels(authInfoUDDI, TckTModel.TMODELS_XML);
                        tckTModel.saveJoePublisherTmodel(authInfoJoe);
                } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                        Assert.fail("Could not obtain authInfo token.");
                }
        }
        final static String VSV_KEY = "uddi:juddi.apache.org:businesses-asf";
        final static String VSV_BT_KEY = "uddi:juddi.apache.org:servicebindings-valueset-ws";
        final static String TMODEL = "validated.checked.tmodel";

        /**
         * scenario 1
         * <ul>
         * <ol>create checked tmodel x</ol>
         * <ol>register business/service/bt for validatevalues service</ol>
         * <ol>add/alter BT to include instance info for ref to tmodel
         * checked</ol>
         * <ol>alter checked tmodel x, add reference to the BT for the
         * validator</ol>
         * <ol>someone tries to use the checked tmodel as a KR from some other
         * entity</ol>
         * <ol>on publish, the server attempts to validate the KR's value
         * against the VV service via the binding Template</ol>
         * </ul>
         *
         * @throws Exception
         */
        @Test
        public void Scenario1() throws Exception {
                //create checked tmodel x
                //SaveCheckedTModel(TckTModel.JOE_PUBLISHER_KEY_PREFIX + TMODEL);
                //register business/service/bt for validatevalues service
                //this is done by the install process using the built in vsv

                //add/alter BT to include instance info for ref to tmodel checked
                //AlterRootBusiness();
        }

        private void SaveCheckedTModel(String key) throws Exception{
                TModel tm = new TModel();
                tm.setTModelKey(key);
                tm.setCategoryBag(new CategoryBag());
                tm.setName(new Name("My Custom validated key","en"));
                tm.getDescription().add(new Description("valid values include 'one', 'two', 'three'", "en"));
                tm.getCategoryBag().getKeyedReference().add(
                        new KeyedReference(UDDIConstants.IS_VALIDATED_BY, UDDIConstants.IS_VALIDATED_BY_KEY_NAME, VSV_BT_KEY));
                SaveTModel stm = new SaveTModel();
                stm.setAuthInfo(authInfoJoe);
                publication.saveTModel(stm);
        }

        private void AlterRootBusiness() {
                
        }
}
