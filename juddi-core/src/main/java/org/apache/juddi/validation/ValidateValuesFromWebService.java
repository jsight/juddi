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
package org.apache.juddi.validation;

import javax.xml.ws.BindingProvider;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.juddi.api.impl.UDDIValueSetValidationImpl;
import org.apache.juddi.config.Property;
import org.apache.juddi.v3.client.UDDIConstants;
import org.apache.juddi.v3.client.UDDIService;
import org.apache.juddi.v3.error.ErrorMessage;
import org.apache.juddi.v3.error.ValueNotAllowedException;
import org.uddi.api_v3.BindingTemplate;
import org.uddi.api_v3.BusinessEntity;
import org.uddi.api_v3.BusinessService;
import org.uddi.api_v3.DispositionReport;
import org.uddi.api_v3.PublisherAssertion;
import org.uddi.api_v3.TModel;
import org.uddi.v3_service.UDDIValueSetValidationPortType;
import org.uddi.vs_v3.ValidateValues;

/**
 * This class handles when this UDDI server receives some kind of SaveXXX
 * Publish API request and the request uses a tModel KeyedReference that is both
 * "checked" and has a UDDIConstants.IS_VALIDATED_BY property. The value of the
 * IS_VALIDATED_BY reference points to a BindingTemplate which should in turn
 * point to a Web Service that handles validation requests for the tModel
 * KeyedReferences.
 *
 * Short Story long, this class handles the external callout. It also handles
 * classpath:/ URLs (which is currently just jUDDI's implementation of VSV
 *
 * @author Alex O'Ree
 * @see UDDIConstants.IS_VALIDATED_BY
 * @since 3.3
 */
public class ValidateValuesFromWebService {

        static final Log log = LogFactory.getLog(ValidateValuesFromWebService.class);

        private static UDDIValueSetValidationPortType getPort(String url) throws ValueNotAllowedException {
                UDDIService svc = new UDDIService();
                UDDIValueSetValidationPortType vsv = svc.getUDDIValueSetValidationPort();

                if (url == null || url.trim().length() == 0) {
                        log.error("VSV Validation Failed: Cannot locate class from url null");
                        ValueNotAllowedException x = new ValueNotAllowedException(new ErrorMessage("errors.valuesetvalidation.invalidurl"));
                        throw x;
                }
                if (url.startsWith(Property.DEFAULT_BASE_URL)
                        || url.startsWith(Property.DEFAULT_BASE_URL_SECURE)) {
                        vsv = new UDDIValueSetValidationImpl();

                }
                if (url.startsWith("classpath:")) {
                        try {
                                String clz = url.substring(11);
                                Class<UDDIValueSetValidationPortType> forName = (Class<UDDIValueSetValidationPortType>) Class.forName(clz);
                                vsv = forName.newInstance();
                        } catch (Exception ex) {
                                log.error("VSV Validation Failed: Cannot locate class from url " + url, ex);
                        }
                } else {
                        //external service, stick with 
                        log.info("Calling External VSV Service");
                        ((BindingProvider) vsv).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, url);
                }
                return vsv;
        }

        public static void Validate(String url, BusinessEntity obj) throws ValueNotAllowedException {

                UDDIValueSetValidationPortType vsv=getPort(url);
                ValidateValues req = new ValidateValues();
                req.getBusinessEntity().add(obj);

                try {
                        DispositionReport validateValues = vsv.validateValues(req);
                        //TODO parse
                } catch (Exception ex) {
                        log.error("VSV Validation Failed: Cannot locate class from url " + url, ex);
                        ValueNotAllowedException x = new ValueNotAllowedException(new ErrorMessage("errors.valuesetvalidation.invalidcontent", ex.getMessage()));
                        throw x;
                }
        }

        public static void Validate(String url, TModel obj) throws ValueNotAllowedException {
                UDDIValueSetValidationPortType vsv=getPort(url);
                ValidateValues req = new ValidateValues();
                req.getTModel().add(obj);

                try {
                        DispositionReport validateValues = vsv.validateValues(req);
                } catch (Exception ex) {
                        log.warn(ex);
                        ValueNotAllowedException x = new ValueNotAllowedException(new ErrorMessage("errors.valuesetvalidation.invalidcontent", ex.getMessage()));
                }
        }

        public static void Validate(String url, BusinessService obj) throws ValueNotAllowedException {
                UDDIValueSetValidationPortType vsv=getPort(url);
                ValidateValues req = new ValidateValues();
                req.getBusinessService().add(obj);

                try {
                        DispositionReport validateValues = vsv.validateValues(req);
                } catch (Exception ex) {
                        log.warn(ex);
                        ValueNotAllowedException x = new ValueNotAllowedException(new ErrorMessage("errors.valuesetvalidation.invalidcontent", ex.getMessage()));
                }
        }

        public static void Validate(String url, BindingTemplate obj) throws ValueNotAllowedException {
                UDDIValueSetValidationPortType vsv=getPort(url);
                ValidateValues req = new ValidateValues();
                req.getBindingTemplate().add(obj);

                try {
                        DispositionReport validateValues = vsv.validateValues(req);
                } catch (Exception ex) {
                        log.warn(ex);
                        ValueNotAllowedException x = new ValueNotAllowedException(new ErrorMessage("errors.valuesetvalidation.invalidcontent", ex.getMessage()));
                }
        }

        public static void Validate(String url, PublisherAssertion obj) throws ValueNotAllowedException {
                UDDIValueSetValidationPortType vsv=getPort(url);
                ValidateValues req = new ValidateValues();
                req.getPublisherAssertion().add(obj);
                try {
                        DispositionReport validateValues = vsv.validateValues(req);
                } catch (Exception ex) {
                        log.warn(ex);
                        ValueNotAllowedException x = new ValueNotAllowedException(new ErrorMessage("errors.valuesetvalidation.invalidcontent", ex.getMessage()));
                }
        }
}
