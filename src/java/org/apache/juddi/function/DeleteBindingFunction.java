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
import org.apache.juddi.datatype.publisher.Publisher;
import org.apache.juddi.datatype.request.AuthInfo;
import org.apache.juddi.datatype.request.DeleteBinding;
import org.apache.juddi.datatype.request.GetAuthToken;
import org.apache.juddi.datatype.response.AuthToken;
import org.apache.juddi.datatype.response.DispositionReport;
import org.apache.juddi.datatype.response.Result;
import org.apache.juddi.error.InvalidKeyPassedException;
import org.apache.juddi.error.RegistryException;
import org.apache.juddi.error.UserMismatchException;
import org.apache.juddi.util.Config;

/**
 * @author Steve Viens (sviens@apache.org)
 */
public class DeleteBindingFunction extends AbstractFunction
{
  // private reference to jUDDI Logger
  private static Log log = LogFactory.getLog(DeleteBindingFunction.class);

  /**
   *
   */
  public DeleteBindingFunction()
  {
    super();
  }

  /**
   *
   */
  public RegistryObject execute(RegistryObject regObject)
    throws RegistryException
  {
    // extract individual parameters
    DeleteBinding request = (DeleteBinding)regObject;
    String generic = request.getGeneric();
    AuthInfo authInfo = request.getAuthInfo();
    Vector bindingKeyVector = request.getBindingKeyVector();

    // aquire a jUDDI datastore instance
    DataStoreFactory factory = DataStoreFactory.getFactory();
    DataStore dataStore = factory.acquireDataStore();

    try
    {
      dataStore.beginTrans();

      // validate authentication parameters
      Publisher publisher = getPublisher(authInfo,dataStore);
      String publisherID = publisher.getPublisherID();

      // validate request parameters
      for (int i=0; i<bindingKeyVector.size(); i++)
      {
        // grab the next key from the vector
        String bindingKey = (String)bindingKeyVector.elementAt(i);

        // check that this binding template really exists.
        // If not then throw an InvalidKeyPassedException.
        if ((bindingKey == null) || (bindingKey.length() == 0) ||
            (!dataStore.isValidBindingKey(bindingKey)))
          throw new InvalidKeyPassedException("BindingKey: "+bindingKey);

        // check to make sure that 'authorizedName' controls the
        // business entity that this binding belongs to. If not
        // then throw a UserMismatchException.
        if (!dataStore.isBindingPublisher(bindingKey,publisherID))
          throw new UserMismatchException("BindingKey: "+bindingKey);
      }

      // delete the BindingTemplates
      for (int i=0; i<bindingKeyVector.size(); i++)
      {
        String bindingKey = (String)bindingKeyVector.elementAt(i);
        dataStore.deleteBinding(bindingKey);

        log.info("Publisher '"+publisherID+"' deleted BindingTemplate with key: "+bindingKey);
      }

      dataStore.commit();
    }
    catch(Exception ex)
    {
      // we must rollback for *any* exception
      try { dataStore.rollback(); }
      catch(Exception e) { }

      // write to the log
      log.error(ex);

      // prep RegistryFault to throw
      if (ex instanceof RegistryException)
        throw (RegistryException)ex;
      else
        throw new RegistryException(ex);
    }
    finally
    {
      factory.releaseDataStore(dataStore);
    }

    // didn't encounter an exception so let's create
    // and return a successfull DispositionReport
    DispositionReport dispRpt = new DispositionReport();
    dispRpt.setGeneric(generic);
    dispRpt.setOperator(Config.getOperator());
    dispRpt.addResult(new Result(Result.E_SUCCESS));
    return dispRpt;
  }


  /***************************************************************************/
  /***************************** TEST DRIVER *********************************/
  /***************************************************************************/


  public static void main(String[] args)
  {
    // initialize the registry
    org.apache.juddi.registry.RegistryEngine reg = org.apache.juddi.registry.RegistryEngine.getInstance();
    reg.init();

    try
    {
      // create & execute the GetAuthToken request
      GetAuthToken authTokenRequest = new GetAuthToken("sviens","password");
      AuthToken authToken = (AuthToken)reg.execute(authTokenRequest);
      AuthInfo authInfo = authToken.getAuthInfo();

      // create & execute the DeleteBinding request
      DeleteBinding request = new DeleteBinding();
      request.setAuthInfo(authInfo);
      DispositionReport response = (DispositionReport)reg.execute(request);
      System.out.println("errno: "+response.toString());
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