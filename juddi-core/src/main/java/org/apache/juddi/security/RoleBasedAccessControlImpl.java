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

package org.apache.juddi.security;

import java.util.List;
import org.uddi.api_v3.BindingTemplate;
import org.uddi.api_v3.BusinessEntity;
import org.uddi.api_v3.BusinessInfo;
import org.uddi.api_v3.BusinessService;
import org.uddi.api_v3.TModel;

/**
 * RBAC functionality, to be pulled from J2EE container, such as Tomcat or Jboss
 * Need to map roles to permissions via tables
 * @author Alex O'Ree
 */
public class RoleBasedAccessControlImpl implements IAccessControl{

    
    public List<BusinessService> FilterServices(String username, List<BusinessService> services) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    public List<BusinessEntity> FilterBusinesses(String username, List<BusinessEntity> business) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    public List<TModel> FilterTModels(String username, List<TModel> tmodels) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    
    public boolean HasAccess(AccessLevel level, String username, String key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    public boolean IsAdmin(String username) throws IllegalArgumentException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    public List<BindingTemplate> FilterBindingTemplates(String username, List<BindingTemplate> bindings) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean HasAccess(AccessLevel level, String username, List<String> keys) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<BusinessInfo> FilterBusinessInfo(String username, List<BusinessInfo> business) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void AssertAccess(AccessLevel level, String username, List<String> keys) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
