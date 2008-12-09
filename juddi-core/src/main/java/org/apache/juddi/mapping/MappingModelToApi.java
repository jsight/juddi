/*
 * Copyright 2001-2008 The Apache Software Foundation.
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

package org.apache.juddi.mapping;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBElement;

import org.uddi.api_v3.CompletionStatus;
import org.uddi.api_v3.ObjectFactory;

import org.uddi.v3_service.DispositionReportFaultMessage;

/**
 * @author <a href="mailto:jfaath@apache.org">Jeff Faath</a>
 */
public class MappingModelToApi {
	
	public static void mapPublisher(org.apache.juddi.model.Publisher modelPublisher, 
									org.apache.juddi.api.datatype.Publisher apiPublisher) 
				   throws DispositionReportFaultMessage {

		apiPublisher.setAuthorizedName(modelPublisher.getAuthorizedName());
		apiPublisher.setPublisherName(modelPublisher.getPublisherName());
		apiPublisher.setEmailAddress(modelPublisher.getEmailAddress());
		apiPublisher.setIsAdmin(modelPublisher.getIsAdmin());
		apiPublisher.setIsEnabled(modelPublisher.getIsEnabled());
		apiPublisher.setMaxBindingsPerService(modelPublisher.getMaxBindingsPerService());
		apiPublisher.setMaxBusinesses(modelPublisher.getMaxBusinesses());
		apiPublisher.setMaxServicePerBusiness(modelPublisher.getMaxServicesPerBusiness());
		apiPublisher.setMaxTModels(modelPublisher.getMaxTmodels());
	}
	
	public static void mapBusinessEntity(org.apache.juddi.model.BusinessEntity modelBusinessEntity, 
										 org.uddi.api_v3.BusinessEntity apiBusinessEntity) 
				   throws DispositionReportFaultMessage {

		apiBusinessEntity.setBusinessKey(modelBusinessEntity.getEntityKey());
		
		mapBusinessNames(modelBusinessEntity.getBusinessNames(), apiBusinessEntity.getName());
		mapBusinessDescriptions(modelBusinessEntity.getBusinessDescrs(), apiBusinessEntity.getDescription());

		mapDiscoveryUrls(modelBusinessEntity.getDiscoveryUrls(), apiBusinessEntity.getDiscoveryURLs(), apiBusinessEntity);
		mapContacts(modelBusinessEntity.getContacts(), apiBusinessEntity.getContacts(), apiBusinessEntity);
		mapBusinessIdentifiers(modelBusinessEntity.getBusinessIdentifiers(), apiBusinessEntity.getIdentifierBag(), apiBusinessEntity);
		mapBusinessCategories(modelBusinessEntity.getBusinessCategories(), apiBusinessEntity.getCategoryBag(), apiBusinessEntity);
		
		mapBusinessServices(modelBusinessEntity.getBusinessServices(), apiBusinessEntity.getBusinessServices(), apiBusinessEntity);
	
	}

	public static void mapBusinessNames(Set<org.apache.juddi.model.BusinessName> modelNameList, 
										List<org.uddi.api_v3.Name> apiNameList) 
				   throws DispositionReportFaultMessage {
		apiNameList.clear();

		for (org.apache.juddi.model.BusinessName modelName : modelNameList) {
			org.uddi.api_v3.Name apiName = new org.uddi.api_v3.Name();
			apiName.setLang(modelName.getLangCode());
			apiName.setValue(modelName.getName());
			apiNameList.add(apiName);
		}
	}

	public static void mapBusinessDescriptions(Set<org.apache.juddi.model.BusinessDescr> modelDescList, 
											   List<org.uddi.api_v3.Description> apiDescList) 
				   throws DispositionReportFaultMessage {
		apiDescList.clear();

		for (org.apache.juddi.model.BusinessDescr modelDesc : modelDescList) {
			org.uddi.api_v3.Description apiDesc = new org.uddi.api_v3.Description();
			apiDesc.setLang(modelDesc.getLangCode());
			apiDesc.setValue(modelDesc.getDescr());
			apiDescList.add(apiDesc);
		}
	}

	public static void mapDiscoveryUrls(Set<org.apache.juddi.model.DiscoveryUrl> modelDiscUrlList, 
										org.uddi.api_v3.DiscoveryURLs apiDiscUrls,
										org.uddi.api_v3.BusinessEntity apiBusinessEntity) 
				   throws DispositionReportFaultMessage {
		if (apiDiscUrls == null)
			apiDiscUrls = new org.uddi.api_v3.DiscoveryURLs();

		List<org.uddi.api_v3.DiscoveryURL> apiDiscUrlList = apiDiscUrls.getDiscoveryURL();
		apiDiscUrlList.clear();
		
		for (org.apache.juddi.model.DiscoveryUrl modelDiscUrl : modelDiscUrlList) {
			org.uddi.api_v3.DiscoveryURL apiDiscUrl = new org.uddi.api_v3.DiscoveryURL();
			apiDiscUrl.setUseType(modelDiscUrl.getUseType());
			apiDiscUrl.setValue(modelDiscUrl.getUrl());
			apiDiscUrlList.add(apiDiscUrl);
		}
		apiBusinessEntity.setDiscoveryURLs(apiDiscUrls);
	}
	
	public static void mapContacts(Set<org.apache.juddi.model.Contact> modelContactList, 
								   org.uddi.api_v3.Contacts apiContacts,
								   org.uddi.api_v3.BusinessEntity apiBusinessEntity) 
				   throws DispositionReportFaultMessage {
		if (apiContacts == null)
			apiContacts = new org.uddi.api_v3.Contacts();

		List<org.uddi.api_v3.Contact> apiContactList = apiContacts.getContact();
		apiContactList.clear();

		for (org.apache.juddi.model.Contact modelContact : modelContactList) {
			org.uddi.api_v3.Contact apiContact = new org.uddi.api_v3.Contact();
			apiContact.setUseType(modelContact.getUseType());
			// TODO: The model only supports one person name, needs to support collection.
			org.uddi.api_v3.PersonName apiPersonName = new org.uddi.api_v3.PersonName();
			apiPersonName.setValue(modelContact.getPersonName());
			apiContact.getPersonName().add(apiPersonName);
			
			mapContactDescriptions(modelContact.getContactDescrs(), apiContact.getDescription());
			mapContactEmails(modelContact.getEmails(), apiContact.getEmail());
			mapContactPhones(modelContact.getPhones(), apiContact.getPhone());
			mapContactAddresses(modelContact.getAddresses(), apiContact.getAddress());
			
			apiContactList.add(apiContact);
		}
		apiBusinessEntity.setContacts(apiContacts);
	}

	public static void mapContactDescriptions(Set<org.apache.juddi.model.ContactDescr> modelDescList, 
											  List<org.uddi.api_v3.Description> apiDescList) 
	throws DispositionReportFaultMessage {
		apiDescList.clear();

		for (org.apache.juddi.model.ContactDescr modelDesc : modelDescList) {
			org.uddi.api_v3.Description apiDesc = new org.uddi.api_v3.Description();
			apiDesc.setLang(modelDesc.getLangCode());
			apiDesc.setValue(modelDesc.getDescr());
			apiDescList.add(apiDesc);
		}
	}

	public static void mapContactEmails(Set<org.apache.juddi.model.Email> modelEmailList, 
										List<org.uddi.api_v3.Email> apiEmailList) 
				   throws DispositionReportFaultMessage {
		apiEmailList.clear();

		for (org.apache.juddi.model.Email modelEmail : modelEmailList) {
			org.uddi.api_v3.Email apiEmail = new org.uddi.api_v3.Email();
			apiEmail.setUseType(modelEmail.getUseType());
			apiEmail.setValue(modelEmail.getEmailAddress());
			apiEmailList.add(apiEmail);
		}
	}
	
	public static void mapContactPhones(Set<org.apache.juddi.model.Phone> modelPhoneList, 
										List<org.uddi.api_v3.Phone> apiPhoneList) 
				   throws DispositionReportFaultMessage {
		apiPhoneList.clear();

		for (org.apache.juddi.model.Phone modelPhone : modelPhoneList) {
			org.uddi.api_v3.Phone apiPhone = new org.uddi.api_v3.Phone();
			apiPhone.setUseType(modelPhone.getUseType());
			apiPhone.setValue(modelPhone.getPhoneNumber());
			apiPhoneList.add(apiPhone);
		}
	}

	public static void mapContactAddresses(Set<org.apache.juddi.model.Address> modelAddressList, 
										   List<org.uddi.api_v3.Address> apiAddressList) 
				   throws DispositionReportFaultMessage {
		apiAddressList.clear();

		for (org.apache.juddi.model.Address modelAddress : modelAddressList) {
			org.uddi.api_v3.Address apiAddress = new org.uddi.api_v3.Address();
			apiAddress.setUseType(modelAddress.getUseType());
			apiAddress.setLang("");
			apiAddress.setSortCode(modelAddress.getSortCode());
			apiAddress.setTModelKey(modelAddress.getTmodelKey());
			
			mapAddressLines(modelAddress.getAddressLines(), apiAddress.getAddressLine());
			
			apiAddressList.add(apiAddress);
		}
	}

	public static void mapAddressLines(Set<org.apache.juddi.model.AddressLine> modelAddressLineList, 
									   List<org.uddi.api_v3.AddressLine> apiAddressLineList) 
				   throws DispositionReportFaultMessage {
		apiAddressLineList.clear();

		for (org.apache.juddi.model.AddressLine modelAddressLine : modelAddressLineList) {
			org.uddi.api_v3.AddressLine apiAddressLine = new org.uddi.api_v3.AddressLine();
			apiAddressLine.setKeyName(modelAddressLine.getKeyName());
			apiAddressLine.setKeyName(modelAddressLine.getKeyValue());
			apiAddressLine.setValue(modelAddressLine.getLine());
			apiAddressLineList.add(apiAddressLine);
		}
	}

	public static void mapBusinessIdentifiers(Set<org.apache.juddi.model.BusinessIdentifier> modelIdentifierList, 
											  org.uddi.api_v3.IdentifierBag apiIdentifierBag,
											  org.uddi.api_v3.BusinessEntity apiBusinessEntity) 
				   throws DispositionReportFaultMessage {
		if (apiIdentifierBag == null)
			apiIdentifierBag = new org.uddi.api_v3.IdentifierBag();

		List<org.uddi.api_v3.KeyedReference> apiKeyedRefList = apiIdentifierBag.getKeyedReference();
		apiKeyedRefList.clear();

		for (org.apache.juddi.model.BusinessIdentifier modelIdentifier : modelIdentifierList) {
			org.uddi.api_v3.KeyedReference apiKeyedRef = new org.uddi.api_v3.KeyedReference();
			apiKeyedRef.setTModelKey(modelIdentifier.getTmodelKeyRef());
			apiKeyedRef.setKeyName(modelIdentifier.getKeyName());
			apiKeyedRef.setKeyValue(modelIdentifier.getKeyValue());
			apiKeyedRefList.add(apiKeyedRef);
		}
		apiBusinessEntity.setIdentifierBag(apiIdentifierBag);
	}
	
	public static void mapBusinessCategories(Set<org.apache.juddi.model.BusinessCategory> modelCategoryList, 
											 org.uddi.api_v3.CategoryBag apiCategoryBag,
											 org.uddi.api_v3.BusinessEntity apiBusinessEntity) 
				   throws DispositionReportFaultMessage {
		if (apiCategoryBag == null)
			apiCategoryBag = new org.uddi.api_v3.CategoryBag();

		List<JAXBElement<?>> apiCategoryList = apiCategoryBag.getContent();
		apiCategoryList.clear();

		for (org.apache.juddi.model.BusinessCategory modelCategory : modelCategoryList) {
			// TODO:  Currently, the model doesn't allow for the persistence of keyedReference groups.  This must be incorporated into the model.  For now
			// the KeyedReferenceGroups are ignored.

			org.uddi.api_v3.KeyedReference apiKeyedRef = new org.uddi.api_v3.KeyedReference();
			apiKeyedRef.setTModelKey(modelCategory.getTmodelKeyRef());
			apiKeyedRef.setKeyName(modelCategory.getKeyName());
			apiKeyedRef.setKeyValue(modelCategory.getKeyValue());
			apiCategoryList.add(new ObjectFactory().createKeyedReference(apiKeyedRef));
		}
		apiBusinessEntity.setCategoryBag(apiCategoryBag);
	}

	public static void mapBusinessServices(Set<org.apache.juddi.model.BusinessService> modelBusinessServiceList, 
										   org.uddi.api_v3.BusinessServices apiBusinessServices,
										   org.uddi.api_v3.BusinessEntity apiBusinessEntity) 
				   throws DispositionReportFaultMessage {

		if (apiBusinessServices == null)
			apiBusinessServices = new org.uddi.api_v3.BusinessServices();

		List<org.uddi.api_v3.BusinessService> apiBusinessServiceList = apiBusinessServices.getBusinessService();
		apiBusinessServiceList.clear();
		
		for (org.apache.juddi.model.BusinessService modelBusinessService : modelBusinessServiceList) {
			org.uddi.api_v3.BusinessService apiBusinessService = new org.uddi.api_v3.BusinessService();
			mapBusinessService(modelBusinessService, apiBusinessService);
			apiBusinessServiceList.add(apiBusinessService);
		}
		apiBusinessEntity.setBusinessServices(apiBusinessServices);
	}

	public static void mapBusinessService(org.apache.juddi.model.BusinessService modelBusinessService, 
										  org.uddi.api_v3.BusinessService apiBusinessService) 
				   throws DispositionReportFaultMessage {

		apiBusinessService.setBusinessKey(modelBusinessService.getBusinessEntity().getEntityKey());
		apiBusinessService.setServiceKey(modelBusinessService.getEntityKey());

		mapServiceNames(modelBusinessService.getServiceNames(), apiBusinessService.getName());
		mapServiceDescriptions(modelBusinessService.getServiceDescrs(), apiBusinessService.getDescription());

		mapServiceCategories(modelBusinessService.getServiceCategories(), apiBusinessService.getCategoryBag(), apiBusinessService);

	}

	public static void mapServiceNames(Set<org.apache.juddi.model.ServiceName> modelNameList, 
									   List<org.uddi.api_v3.Name> apiNameList) 
				   throws DispositionReportFaultMessage {
		apiNameList.clear();

		for (org.apache.juddi.model.ServiceName modelName : modelNameList) {
			org.uddi.api_v3.Name apiName = new org.uddi.api_v3.Name();
			apiName.setLang(modelName.getLangCode());
			apiName.setValue(modelName.getName());
			apiNameList.add(apiName);
		}
	}

	public static void mapServiceDescriptions(Set<org.apache.juddi.model.ServiceDescr> modelDescList, 
											  List<org.uddi.api_v3.Description> apiDescList) 
				   throws DispositionReportFaultMessage {
		apiDescList.clear();

		for (org.apache.juddi.model.ServiceDescr modelDesc : modelDescList) {
			org.uddi.api_v3.Description apiDesc = new org.uddi.api_v3.Description();
			apiDesc.setLang(modelDesc.getLangCode());
			apiDesc.setValue(modelDesc.getDescr());
			apiDescList.add(apiDesc);
		}
	}
	
	public static void mapServiceCategories(Set<org.apache.juddi.model.ServiceCategory> modelCategoryList, 
											org.uddi.api_v3.CategoryBag apiCategoryBag,
											org.uddi.api_v3.BusinessService apiBusinessService) 
				   throws DispositionReportFaultMessage {
		if (apiCategoryBag == null)
			apiCategoryBag = new org.uddi.api_v3.CategoryBag();

		List<JAXBElement<?>> apiCategoryList = apiCategoryBag.getContent();
		apiCategoryList.clear();

		for (org.apache.juddi.model.ServiceCategory modelCategory : modelCategoryList) {
			// TODO:  Currently, the model doesn't allow for the persistence of keyedReference groups.  This must be incorporated into the model.  For now
			// the KeyedReferenceGroups are ignored.

			org.uddi.api_v3.KeyedReference apiKeyedRef = new org.uddi.api_v3.KeyedReference();
			apiKeyedRef.setTModelKey(modelCategory.getTmodelKeyRef());
			apiKeyedRef.setKeyName(modelCategory.getKeyName());
			apiKeyedRef.setKeyValue(modelCategory.getKeyValue());
			apiCategoryList.add(new ObjectFactory().createKeyedReference(apiKeyedRef));
		}
		apiBusinessService.setCategoryBag(apiCategoryBag);
	}

	public static void mapBindingTemplates(Set<org.apache.juddi.model.BindingTemplate> modelBindingTemplateList, 
										   org.uddi.api_v3.BindingTemplates apiBindingTemplates,
										   org.uddi.api_v3.BusinessService apiBusinessService) 
				   throws DispositionReportFaultMessage {

		if (apiBindingTemplates == null)
			apiBindingTemplates = new org.uddi.api_v3.BindingTemplates();

		List<org.uddi.api_v3.BindingTemplate> apiBindingTemplateList = apiBindingTemplates.getBindingTemplate();
		apiBindingTemplateList.clear();

		for (org.apache.juddi.model.BindingTemplate modelBindingTemplate : modelBindingTemplateList) {
			org.uddi.api_v3.BindingTemplate apiBindingTemplate = new org.uddi.api_v3.BindingTemplate();
			mapBindingTemplate(modelBindingTemplate, apiBindingTemplate);
			apiBindingTemplateList.add(apiBindingTemplate);
		}
		apiBusinessService.setBindingTemplates(apiBindingTemplates);
	}

	public static void mapBindingTemplate(org.apache.juddi.model.BindingTemplate modelBindingTemplate, 
										  org.uddi.api_v3.BindingTemplate apiBindingTemplate) 
				   throws DispositionReportFaultMessage {

		apiBindingTemplate.setServiceKey(modelBindingTemplate.getBusinessService().getEntityKey());
		apiBindingTemplate.setBindingKey(modelBindingTemplate.getEntityKey());
		org.uddi.api_v3.AccessPoint apiAccessPoint = new org.uddi.api_v3.AccessPoint();
		apiAccessPoint.setUseType(modelBindingTemplate.getAccessPointType());
		apiAccessPoint.setValue(modelBindingTemplate.getAccessPointUrl());
		apiBindingTemplate.setAccessPoint(apiAccessPoint);
		org.uddi.api_v3.HostingRedirector apiHost = new org.uddi.api_v3.HostingRedirector();
		apiHost.setBindingKey(modelBindingTemplate.getHostingRedirector());
		apiBindingTemplate.setHostingRedirector(apiHost);

		mapBindingDescriptions(modelBindingTemplate.getBindingDescrs(), apiBindingTemplate.getDescription());

		mapBindingCategories(modelBindingTemplate.getBindingCategories(), apiBindingTemplate.getCategoryBag(), apiBindingTemplate);

	}

	public static void mapBindingDescriptions(Set<org.apache.juddi.model.BindingDescr> modelDescList, 
											  List<org.uddi.api_v3.Description> apiDescList) 
				   throws DispositionReportFaultMessage {
		apiDescList.clear();

		for (org.apache.juddi.model.BindingDescr modelDesc : modelDescList) {
			org.uddi.api_v3.Description apiDesc = new org.uddi.api_v3.Description();
			apiDesc.setLang(modelDesc.getLangCode());
			apiDesc.setValue(modelDesc.getDescr());
			apiDescList.add(apiDesc);
		}
	}

	public static void mapBindingCategories(Set<org.apache.juddi.model.BindingCategory> modelCategoryList, 
											org.uddi.api_v3.CategoryBag apiCategoryBag,
											org.uddi.api_v3.BindingTemplate apiBindingTemplate) 
				   throws DispositionReportFaultMessage {
		if (apiCategoryBag == null)
			apiCategoryBag = new org.uddi.api_v3.CategoryBag();

		List<JAXBElement<?>> apiCategoryList = apiCategoryBag.getContent();
		apiCategoryList.clear();

		for (org.apache.juddi.model.BindingCategory modelCategory : modelCategoryList) {
			// TODO:  Currently, the model doesn't allow for the persistence of keyedReference groups.  This must be incorporated into the model.  For now
			// the KeyedReferenceGroups are ignored.

			org.uddi.api_v3.KeyedReference apiKeyedRef = new org.uddi.api_v3.KeyedReference();
			apiKeyedRef.setTModelKey(modelCategory.getTmodelKeyRef());
			apiKeyedRef.setKeyName(modelCategory.getKeyName());
			apiKeyedRef.setKeyValue(modelCategory.getKeyValue());
			apiCategoryList.add(new ObjectFactory().createKeyedReference(apiKeyedRef));
		}
		apiBindingTemplate.setCategoryBag(apiCategoryBag);
	}

	public static void mapTModelInstanceDetails(Set<org.apache.juddi.model.TmodelInstanceInfo> modelTModelInstInfoList, 
												org.uddi.api_v3.TModelInstanceDetails apiTModelInstDetails,
												org.uddi.api_v3.BindingTemplate apiBindingTemplate) 
				   throws DispositionReportFaultMessage {
		if (apiTModelInstDetails == null)
			apiTModelInstDetails = new org.uddi.api_v3.TModelInstanceDetails();

		List<org.uddi.api_v3.TModelInstanceInfo> apiTModelInstInfoList = apiTModelInstDetails.getTModelInstanceInfo();
		apiTModelInstInfoList.clear();

		for (org.apache.juddi.model.TmodelInstanceInfo modelTModelInstInfo : modelTModelInstInfoList) {
			org.uddi.api_v3.TModelInstanceInfo apiTModelInstInfo = new org.uddi.api_v3.TModelInstanceInfo();
			apiTModelInstInfo.setTModelKey(modelTModelInstInfo.getTmodelKey());
			mapTModelInstanceInfoDescriptions(modelTModelInstInfo.getTmodelInstanceInfoDescrs(), apiTModelInstInfo.getDescription());
			mapInstanceDetails(modelTModelInstInfo, apiTModelInstInfo.getInstanceDetails(), apiTModelInstInfo);

			apiTModelInstInfoList.add(apiTModelInstInfo);
		}
		apiBindingTemplate.setTModelInstanceDetails(apiTModelInstDetails);
	}
	
	public static void mapTModelInstanceInfoDescriptions(Set<org.apache.juddi.model.TmodelInstanceInfoDescr> modelDescList, 
														 List<org.uddi.api_v3.Description> apiDescList) 
				   throws DispositionReportFaultMessage {
		apiDescList.clear();

		for (org.apache.juddi.model.TmodelInstanceInfoDescr modelDesc : modelDescList) {
			org.uddi.api_v3.Description apiDesc = new org.uddi.api_v3.Description();
			apiDesc.setLang(modelDesc.getLangCode());
			apiDesc.setValue(modelDesc.getDescr());
			apiDescList.add(apiDesc);
		}
	}

	public static void mapInstanceDetails(org.apache.juddi.model.TmodelInstanceInfo modelTModelInstInfo, 
										  org.uddi.api_v3.InstanceDetails apiInstanceDetails,
										  org.uddi.api_v3.TModelInstanceInfo apiTModelInstInfo) 
				   throws DispositionReportFaultMessage {
		if (apiInstanceDetails == null)
			apiInstanceDetails = new org.uddi.api_v3.InstanceDetails();

		List<JAXBElement<?>> apiInstanceDetailsContent = apiInstanceDetails.getContent();
		apiInstanceDetailsContent.clear();

		apiInstanceDetailsContent.add(new ObjectFactory().createInstanceParms(modelTModelInstInfo.getInstanceParms()));
		
		Set<org.apache.juddi.model.InstanceDetailsDescr> modelInstDetailsDescrList = modelTModelInstInfo.getInstanceDetailsDescrs();
		for (org.apache.juddi.model.InstanceDetailsDescr modelInstDetailDescr : modelInstDetailsDescrList) {
			org.uddi.api_v3.Description apiDesc = new org.uddi.api_v3.Description();
			apiDesc.setLang(modelInstDetailDescr.getLangCode());
			apiDesc.setValue(modelInstDetailDescr.getDescr());
			apiInstanceDetailsContent.add(new ObjectFactory().createDescription(apiDesc));
			
		}
		
		// TODO: OverviewDoc is not mapped properly in the model.
		apiInstanceDetailsContent.add(new ObjectFactory().createOverviewDoc(null));
		
		apiTModelInstInfo.setInstanceDetails(apiInstanceDetails);
	}
	
	public static void mapTModel(org.apache.juddi.model.Tmodel modelTModel, 
								 org.uddi.api_v3.TModel apiTModel) 
				   throws DispositionReportFaultMessage {

		apiTModel.setTModelKey(modelTModel.getEntityKey());
		org.uddi.api_v3.Name apiName = new org.uddi.api_v3.Name();
		apiName.setValue(modelTModel.getName());
		apiTModel.setName(apiName);
		apiTModel.setDeleted(modelTModel.isDeleted());
		
		mapTModelDescriptions(modelTModel.getTmodelDescrs(), apiTModel.getDescription());

		mapTModelIdentifiers(modelTModel.getTmodelIdentifiers(), apiTModel.getIdentifierBag(), apiTModel);
		mapTModelCategories(modelTModel.getTmodelCategories(), apiTModel.getCategoryBag(), apiTModel);
		//TODO: OverviewDoc - model doesn't have logical mapping
		apiTModel.getOverviewDoc();

	}

	public static void mapTModelDescriptions(Set<org.apache.juddi.model.TmodelDescr> modelDescList, 
											 List<org.uddi.api_v3.Description> apiDescList) 
			    throws DispositionReportFaultMessage {
		apiDescList.clear();

		for (org.apache.juddi.model.TmodelDescr modelDesc : modelDescList) {
			org.uddi.api_v3.Description apiDesc = new org.uddi.api_v3.Description();
			apiDesc.setLang(modelDesc.getLangCode());
			apiDesc.setValue(modelDesc.getDescr());
			apiDescList.add(apiDesc);
		}
	}

	public static void mapTModelIdentifiers(Set<org.apache.juddi.model.TmodelIdentifier> modelIdentifierList, 
											org.uddi.api_v3.IdentifierBag apiIdentifierBag,
											org.uddi.api_v3.TModel apiTModel) 
				   throws DispositionReportFaultMessage {
		if (apiIdentifierBag == null)
			apiIdentifierBag = new org.uddi.api_v3.IdentifierBag();

		List<org.uddi.api_v3.KeyedReference> apiKeyedRefList = apiIdentifierBag.getKeyedReference();
		apiKeyedRefList.clear();

		for (org.apache.juddi.model.TmodelIdentifier modelIdentifier : modelIdentifierList) {
			org.uddi.api_v3.KeyedReference apiKeyedRef = new org.uddi.api_v3.KeyedReference();
			apiKeyedRef.setTModelKey(modelIdentifier.getTmodelKeyRef());
			apiKeyedRef.setKeyName(modelIdentifier.getKeyName());
			apiKeyedRef.setKeyValue(modelIdentifier.getKeyValue());
			apiKeyedRefList.add(apiKeyedRef);
		}
		apiTModel.setIdentifierBag(apiIdentifierBag);
	}
	
	public static void mapTModelCategories(Set<org.apache.juddi.model.TmodelCategory> modelCategoryList, 
										   org.uddi.api_v3.CategoryBag apiCategoryBag,
										   org.uddi.api_v3.TModel apiTModel) 
				   throws DispositionReportFaultMessage {
		if (apiCategoryBag == null)
			apiCategoryBag = new org.uddi.api_v3.CategoryBag();

		List<JAXBElement<?>> apiCategoryList = apiCategoryBag.getContent();
		apiCategoryList.clear();

		for (org.apache.juddi.model.TmodelCategory modelCategory : modelCategoryList) {
			// TODO:  Currently, the model doesn't allow for the persistence of keyedReference groups.  This must be incorporated into the model.  For now
			// the KeyedReferenceGroups are ignored.

			org.uddi.api_v3.KeyedReference apiKeyedRef = new org.uddi.api_v3.KeyedReference();
			apiKeyedRef.setTModelKey(modelCategory.getTmodelKeyRef());
			apiKeyedRef.setKeyName(modelCategory.getKeyName());
			apiKeyedRef.setKeyValue(modelCategory.getKeyValue());
			apiCategoryList.add(new ObjectFactory().createKeyedReference(apiKeyedRef));
		}
		apiTModel.setCategoryBag(apiCategoryBag);
	}

	public static void mapBusinessInfo(org.apache.juddi.model.BusinessEntity modelBusinessEntity, 
									   org.uddi.api_v3.BusinessInfo apiBusinessInfo) 
				   throws DispositionReportFaultMessage {
		
		apiBusinessInfo.setBusinessKey(modelBusinessEntity.getEntityKey());
		
		mapBusinessNames(modelBusinessEntity.getBusinessNames(), apiBusinessInfo.getName());
		mapBusinessDescriptions(modelBusinessEntity.getBusinessDescrs(), apiBusinessInfo.getDescription());
		
		mapServiceInfos(modelBusinessEntity.getBusinessServices(), apiBusinessInfo.getServiceInfos(), apiBusinessInfo);
		
	}

	public static void mapServiceInfos(Set<org.apache.juddi.model.BusinessService> modelBusinessServiceList, 
									   org.uddi.api_v3.ServiceInfos apiServiceInfos,
									   org.uddi.api_v3.BusinessInfo apiBusinessInfo) 
				   throws DispositionReportFaultMessage {
		if (apiServiceInfos == null)
			apiServiceInfos = new org.uddi.api_v3.ServiceInfos();
		
		List<org.uddi.api_v3.ServiceInfo> apiServiceInfoList = apiServiceInfos.getServiceInfo();
		apiServiceInfoList.clear();
		
		for (org.apache.juddi.model.BusinessService modelBusinessService : modelBusinessServiceList) {
			org.uddi.api_v3.ServiceInfo apiServiceInfo = new org.uddi.api_v3.ServiceInfo();

			mapServiceInfo(modelBusinessService, apiServiceInfo);

			apiServiceInfos.getServiceInfo().add(apiServiceInfo);
		}
		apiBusinessInfo.setServiceInfos(apiServiceInfos);
	}
	
	public static void mapServiceInfo(org.apache.juddi.model.BusinessService modelBusinessService, 
									  org.uddi.api_v3.ServiceInfo apiServiceInfo) 
				   throws DispositionReportFaultMessage {

		apiServiceInfo.setBusinessKey(modelBusinessService.getBusinessEntity().getEntityKey());
		apiServiceInfo.setServiceKey(modelBusinessService.getEntityKey());

		mapServiceNames(modelBusinessService.getServiceNames(), apiServiceInfo.getName());
	}
	
	public static void mapTModelInfo(org.apache.juddi.model.Tmodel modelTModel, 
									 org.uddi.api_v3.TModelInfo apiTModelInfo) 
				   throws DispositionReportFaultMessage {

		apiTModelInfo.setTModelKey(modelTModel.getEntityKey());
		org.uddi.api_v3.Name apiName = new org.uddi.api_v3.Name();
		apiName.setValue(modelTModel.getName());
		apiTModelInfo.setName(apiName);

		mapTModelDescriptions(modelTModel.getTmodelDescrs(), apiTModelInfo.getDescription());

	}
	
	public static void mapAuthToken(org.apache.juddi.model.AuthToken modelAuthToken, 
									org.uddi.api_v3.AuthToken apiAuthToken) 
				   throws DispositionReportFaultMessage {
		apiAuthToken.setAuthInfo(modelAuthToken.getAuthToken());
	
	}

	public static void mapPublisherAssertion(org.apache.juddi.model.PublisherAssertion modelPublisherAssertion, 
											 org.uddi.api_v3.PublisherAssertion apiPublisherAssertion) 
				   throws DispositionReportFaultMessage {

		apiPublisherAssertion.setFromKey(modelPublisherAssertion.getId().getFromKey());
		apiPublisherAssertion.setToKey(modelPublisherAssertion.getId().getToKey());
		
		org.uddi.api_v3.KeyedReference keyedRef = new org.uddi.api_v3.KeyedReference();
		keyedRef.setTModelKey(modelPublisherAssertion.getTmodelKey());
		keyedRef.setKeyName(modelPublisherAssertion.getKeyName());
		keyedRef.setKeyValue(modelPublisherAssertion.getKeyValue());
		
		apiPublisherAssertion.setKeyedReference(keyedRef);
		
	}
	
	public static void mapAssertionStatusItem(org.apache.juddi.model.PublisherAssertion modelPublisherAssertion, 
											  org.uddi.api_v3.AssertionStatusItem apiAssertionStatusItem,
											  List<?> businessKeys) 
				   throws DispositionReportFaultMessage {

		apiAssertionStatusItem.setFromKey(modelPublisherAssertion.getId().getFromKey());
		apiAssertionStatusItem.setToKey(modelPublisherAssertion.getId().getToKey());
		
		org.uddi.api_v3.KeyedReference keyedRef = new org.uddi.api_v3.KeyedReference();
		keyedRef.setTModelKey(modelPublisherAssertion.getTmodelKey());
		keyedRef.setKeyName(modelPublisherAssertion.getKeyName());
		keyedRef.setKeyValue(modelPublisherAssertion.getKeyValue());
		
		apiAssertionStatusItem.setKeyedReference(keyedRef);
		
		if ("true".equalsIgnoreCase(modelPublisherAssertion.getFromCheck()) && 
			"true".equalsIgnoreCase(modelPublisherAssertion.getToCheck()))
			apiAssertionStatusItem.setCompletionStatus(CompletionStatus.STATUS_COMPLETE);
		else if(!"true".equalsIgnoreCase(modelPublisherAssertion.getFromCheck()) && 
				"true".equalsIgnoreCase(modelPublisherAssertion.getToCheck()))
			apiAssertionStatusItem.setCompletionStatus(CompletionStatus.STATUS_FROM_KEY_INCOMPLETE);
		else if("true".equalsIgnoreCase(modelPublisherAssertion.getFromCheck()) && 
				!"true".equalsIgnoreCase(modelPublisherAssertion.getToCheck()))
			apiAssertionStatusItem.setCompletionStatus(CompletionStatus.STATUS_TO_KEY_INCOMPLETE);
		else if(!"true".equalsIgnoreCase(modelPublisherAssertion.getFromCheck()) && 
				!"true".equalsIgnoreCase(modelPublisherAssertion.getToCheck()))
			apiAssertionStatusItem.setCompletionStatus(CompletionStatus.STATUS_BOTH_INCOMPLETE);
		
		org.uddi.api_v3.KeysOwned keysOwned = new org.uddi.api_v3.KeysOwned();
		
		Collections.sort((List<String>)businessKeys);
		if (Collections.binarySearch((List<String>)businessKeys, modelPublisherAssertion.getBusinessEntityByFromKey().getEntityKey()) >= 0)
			keysOwned.getContent().add(new ObjectFactory().createFromKey(modelPublisherAssertion.getBusinessEntityByFromKey().getEntityKey()));
		
		if (Collections.binarySearch((List<String>)businessKeys, modelPublisherAssertion.getBusinessEntityByToKey().getEntityKey()) >= 0)
			keysOwned.getContent().add(new ObjectFactory().createToKey(modelPublisherAssertion.getBusinessEntityByToKey().getEntityKey()));
		
	}

	public static void mapRelatedBusinessInfo(org.apache.juddi.model.PublisherAssertion modelPublisherAssertion,
											  org.apache.juddi.model.BusinessEntity modelRelatedBusiness,
											  org.uddi.api_v3.Direction direction,
											  org.uddi.api_v3.RelatedBusinessInfo apiRelatedBusinessInfo) 
				   throws DispositionReportFaultMessage {

		apiRelatedBusinessInfo.setBusinessKey(modelRelatedBusiness.getEntityKey());
		
		mapBusinessNames(modelRelatedBusiness.getBusinessNames(), apiRelatedBusinessInfo.getName());
		mapBusinessDescriptions(modelRelatedBusiness.getBusinessDescrs(), apiRelatedBusinessInfo.getDescription());
		
		org.uddi.api_v3.SharedRelationships sharedRelationships = new org.uddi.api_v3.SharedRelationships();
		sharedRelationships.setDirection(direction);
		
		org.uddi.api_v3.KeyedReference keyedRef = new org.uddi.api_v3.KeyedReference();
		keyedRef.setTModelKey(modelPublisherAssertion.getTmodelKey());
		keyedRef.setKeyName(modelPublisherAssertion.getKeyName());
		keyedRef.setKeyValue(modelPublisherAssertion.getKeyValue());
		sharedRelationships.getKeyedReference().add(keyedRef);
		
		apiRelatedBusinessInfo.getSharedRelationships().add(sharedRelationships);
	}

}
