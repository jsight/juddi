/*
 * Copyright 2001-2004 The Apache Software Foundation.
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
package org.apache.juddi.function;

import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.juddi.datastore.DataStore;
import org.apache.juddi.datastore.DataStoreFactory;
import org.apache.juddi.datatype.RegistryObject;
import org.apache.juddi.datatype.request.GetServiceDetail;
import org.apache.juddi.datatype.response.ServiceDetail;
import org.apache.juddi.error.InvalidKeyPassedException;
import org.apache.juddi.error.RegistryException;
import org.apache.juddi.registry.RegistryEngine;
import org.apache.juddi.util.Config;
;

/**
 * @author Steve Viens (sviens@apache.org)
 */
public class GetServiceDetailFunction extends AbstractFunction
{
  // private reference to jUDDI Logger
  private static Log log = LogFactory.getLog(GetServiceDetailFunction.class);

  /**
   *
   */
  public GetServiceDetailFunction(RegistryEngine registry)
  {
    super(registry);
  }

  /**
   *
   */
  public RegistryObject execute(RegistryObject regObject)
    throws RegistryException
  {
    GetServiceDetail request = (GetServiceDetail)regObject;
    String generic = request.getGeneric();
    Vector keyVector = request.getServiceKeyVector();

    // aquire a jUDDI datastore instance
    DataStore dataStore = DataStoreFactory.getDataStore();

    try
    {
      dataStore.beginTrans();

      for (int i=0; i<keyVector.size(); i++)
      {
        String serviceKey = (String)keyVector.elementAt(i);

        // If the a BusinessService doesn't exist hrow an InvalidKeyPassedException.
        if ((serviceKey == null) || (serviceKey.length() == 0) ||
            (!dataStore.isValidServiceKey(serviceKey)))
          throw new InvalidKeyPassedException(serviceKey);
      }

      Vector serviceVector = new Vector();

      for (int i=0; i<keyVector.size(); i++)
      {
        String serviceKey = (String)keyVector.elementAt(i);
        serviceVector.add(dataStore.fetchService(serviceKey));
      }

      dataStore.commit();

      // create a new ServiceDetail and stuff the new serviceVector into it.
      ServiceDetail detail = new ServiceDetail();
      detail.setGeneric(generic);
      detail.setOperator(Config.getOperator());
      detail.setBusinessServiceVector(serviceVector);
      return detail;
    }
    catch(InvalidKeyPassedException keyex)
    {
      try { dataStore.rollback(); } catch(Exception e) { }
      log.info(keyex.getMessage());
      throw (RegistryException)keyex;
    }
    catch(RegistryException regex)
    {
      try { dataStore.rollback(); } catch(Exception e) { }
      log.error(regex);
      throw (RegistryException)regex;
    }
    catch(Exception ex)
    {
      try { dataStore.rollback(); } catch(Exception e) { }
      log.error(ex);
      throw new RegistryException(ex);
    }
    finally
    {
      if (dataStore != null)
      	dataStore.release();
    }
  }


  /***************************************************************************/
  /***************************** TEST DRIVER *********************************/
  /***************************************************************************/


  public static void main(String[] args)
  {
    // initialize the registry
    RegistryEngine reg = new RegistryEngine();
    reg.init();

    try
    {
    }
    catch (Exception ex)
    {
      // write execption to the console
      ex.printStackTrace();
    }
    finally
    {
      // destroy the registry
      reg.dispose();
    }
  }
}

