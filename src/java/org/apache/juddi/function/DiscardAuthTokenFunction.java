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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.juddi.datastore.DataStore;
import org.apache.juddi.datastore.DataStoreFactory;
import org.apache.juddi.datatype.RegistryObject;
import org.apache.juddi.datatype.publisher.Publisher;
import org.apache.juddi.datatype.request.AuthInfo;
import org.apache.juddi.datatype.request.DiscardAuthToken;
import org.apache.juddi.datatype.request.GetAuthToken;
import org.apache.juddi.datatype.response.AuthToken;
import org.apache.juddi.datatype.response.DispositionReport;
import org.apache.juddi.datatype.response.Result;
import org.apache.juddi.error.AuthTokenRequiredException;
import org.apache.juddi.error.RegistryException;
import org.apache.juddi.registry.RegistryEngine;
import org.apache.juddi.util.Config;

/**
 * @author Steve Viens (sviens@apache.org)
 */
public class DiscardAuthTokenFunction extends AbstractFunction
{
  // private reference to jUDDI logger
  private static Log log = LogFactory.getLog(DiscardAuthTokenFunction.class);

  /**
   *
   */
  public DiscardAuthTokenFunction(RegistryEngine registry)
  {
    super(registry);
  }

  /**
   *
   */
  public RegistryObject execute(RegistryObject regObject)
    throws RegistryException
  {
    // extract individual parameters
    DiscardAuthToken request = (DiscardAuthToken)regObject;
    String generic = request.getGeneric();
    AuthInfo authInfo = request.getAuthInfo();

    // aquire a jUDDI datastore instance
    DataStore dataStore = DataStoreFactory.getDataStore();

    try
    {
      dataStore.beginTrans();

      // validates authentication parameters
      Publisher publisher = getPublisher(authInfo,dataStore);
      String publisherID = publisher.getPublisherID();

      String authToken = authInfo.getValue();
      if ((authInfo == null) || (authInfo.getValue() == null))
        throw new AuthTokenRequiredException("authInfo="+authInfo);

      dataStore.retireAuthToken(authToken);
      dataStore.commit();

      log.info("Publisher '"+publisherID+"' has discarded AuthToken: "+authToken);
    }
    catch(AuthTokenRequiredException authex)
    {
      try { dataStore.rollback(); } catch(Exception e) { }
      log.info(authex.getMessage());
      throw (RegistryException)authex;
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
    RegistryEngine reg = new RegistryEngine();
    reg.init();

    try
    {
      // generate the request object
      GetAuthToken getRequest = new GetAuthToken("sviens","password");

      // invoke the server
      AuthToken getResponse = (AuthToken)(new GetAuthTokenFunction(reg).execute(getRequest));

      // create a request
      DiscardAuthToken discardRequest1 = new DiscardAuthToken(getResponse.getAuthInfo());
      // invoke the server with a valid AuthToken value
      DispositionReport discardResponse = (DispositionReport)(new DiscardAuthTokenFunction(reg).execute(discardRequest1));
      System.out.println("errno: "+discardResponse.toString());

      // create a request
      DiscardAuthToken discardRequest2 = new DiscardAuthToken();
      discardRequest2.setAuthInfo(new AuthInfo("**-BadAuthToken-**"));
      // invoke the server with an invalid AuthToken value
      DispositionReport discardResponse2 = (DispositionReport)(new DiscardAuthTokenFunction(reg).execute(discardRequest2));
      System.out.println("errno: "+discardResponse2.toString());
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