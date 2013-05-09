/*
 * Copyright 2001-2010 The Apache Software Foundation.
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
 *
 */
package org.apache.juddi.example.wsdl2uddi;

import java.rmi.RemoteException;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.juddi.api_v3.Publisher;
import org.apache.juddi.api_v3.SavePublisher;
import org.apache.juddi.v3.client.config.UDDIClerk;
import org.apache.juddi.v3.client.config.UDDIClerkManager;
import org.apache.juddi.v3.client.transport.TransportException;
import org.apache.juddi.v3_service.JUDDIApiPortType;
import org.uddi.api_v3.AuthToken;
import org.uddi.api_v3.BusinessEntity;
import org.uddi.api_v3.CategoryBag;
import org.uddi.api_v3.Description;
import org.uddi.api_v3.GetAuthToken;
import org.uddi.api_v3.KeyedReference;
import org.uddi.api_v3.Name;
import org.uddi.api_v3.OverviewDoc;
import org.uddi.api_v3.OverviewURL;
import org.uddi.api_v3.TModel;
import org.uddi.v3_service.DispositionReportFaultMessage;
import org.uddi.v3_service.UDDISecurityPortType;

public class Setup {
	
	static UDDIClerkManager clerkManager;
	
	public void publishBusiness(UDDIClerk clerk) {
		// Creating the parent business entity that will contain our service.
		BusinessEntity myBusEntity = new BusinessEntity();
		Name myBusName = new Name();
		myBusName.setValue("WSDL-Business");
		myBusEntity.getName().add(myBusName);
		myBusEntity.setBusinessKey("uddi:uddi.joepublisher.com:business-for-wsdl");
		clerk.register(myBusEntity);
	}	

	public static void main (String args[]) {
		
		Setup sp = new Setup();
		try {
			clerkManager = new UDDIClerkManager("META-INF/wsdl2uddi-uddi.xml");
			UDDIClerk clerk = clerkManager.getClerk("joe");
			
			//setting up the publisher
			sp.setupJoePublisher(clerk);
			//publish the business
			sp.publishBusiness(clerk);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// This setup needs to be done once, either using the console or using code like this
	private void setupJoePublisher(UDDIClerk clerk) throws DispositionReportFaultMessage, RemoteException, ConfigurationException, TransportException {
		
		UDDISecurityPortType security = clerkManager.getTransport("default").getUDDISecurityService();
		
		//login as root so we can create joe publisher
		GetAuthToken getAuthTokenRoot = new GetAuthToken();
		getAuthTokenRoot.setUserID("root");
		getAuthTokenRoot.setCred("");
		// Making API call that retrieves the authentication token for the 'root' user.
		AuthToken rootAuthToken = security.getAuthToken(getAuthTokenRoot);
		System.out.println ("root AUTHTOKEN = " + rootAuthToken.getAuthInfo());
		//Creating joe publisher
		JUDDIApiPortType juddiApi = clerkManager.getTransport("default").getJUDDIApiService();
		Publisher p = new Publisher();
		p.setAuthorizedName("joepublisher");
		p.setPublisherName("Joe Publisher");
		// Adding the publisher to the "save" structure, using the 'root' user authentication info and saving away. 
		SavePublisher sp = new SavePublisher();
		sp.getPublisher().add(p);
		sp.setAuthInfo(rootAuthToken.getAuthInfo());
		juddiApi.savePublisher(sp);
		
		//Joe should have a keyGenerator
		TModel keyGenerator = new TModel();
		keyGenerator.setTModelKey("uddi:uddi.joepublisher.com:keygenerator");
		Name name = new Name();
		name.setValue("Joe Publisher's Key Generator");
		keyGenerator.setName(name);
		Description description = new Description();
		description.setValue("This is the key generator for Joe Publisher's UDDI entities!");
		keyGenerator.getDescription().add(description);
		OverviewDoc overviewDoc = new OverviewDoc();
		OverviewURL overviewUrl = new OverviewURL();
		overviewUrl.setUseType("text");
		overviewUrl.setValue("http://uddi.org/pubs/uddi_v3.htm#keyGen");
		overviewDoc.setOverviewURL(overviewUrl);
		keyGenerator.getOverviewDoc().add(overviewDoc);
		CategoryBag categoryBag = new CategoryBag();
		KeyedReference keyedReference = new KeyedReference();
		keyedReference.setKeyName("uddi-org:types:keyGenerator");
		keyedReference.setKeyValue("keyGenerator");
		keyedReference.setTModelKey("uddi:uddi.org:categorization:types");
		categoryBag.getKeyedReference().add(keyedReference);
		keyGenerator.setCategoryBag(categoryBag);
		clerk.register(keyGenerator);
	}
}
