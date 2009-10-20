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

package org.apache.juddi.validation;

import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.xml.ws.Holder;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.juddi.api_v3.DeletePublisher;
import org.apache.juddi.api_v3.SavePublisher;
import org.apache.juddi.config.AppConfig;
import org.apache.juddi.config.Constants;
import org.apache.juddi.config.Property;
import org.apache.juddi.keygen.KeyGenerator;
import org.apache.juddi.keygen.KeyGeneratorFactory;
import org.apache.juddi.model.Publisher;
import org.apache.juddi.model.UddiEntity;
import org.apache.juddi.model.UddiEntityPublisher;
import org.apache.juddi.v3.error.AssertionNotFoundException;
import org.apache.juddi.v3.error.ErrorMessage;
import org.apache.juddi.v3.error.FatalErrorException;
import org.apache.juddi.v3.error.InvalidKeyPassedException;
import org.apache.juddi.v3.error.InvalidProjectionException;
import org.apache.juddi.v3.error.KeyUnavailableException;
import org.apache.juddi.v3.error.UserMismatchException;
import org.apache.juddi.v3.error.ValueNotAllowedException;
import org.apache.log4j.Logger;
import org.uddi.api_v3.AddPublisherAssertions;
import org.uddi.api_v3.DeleteBinding;
import org.uddi.api_v3.DeleteBusiness;
import org.uddi.api_v3.DeletePublisherAssertions;
import org.uddi.api_v3.DeleteService;
import org.uddi.api_v3.DeleteTModel;
import org.uddi.api_v3.KeyedReference;
import org.uddi.api_v3.KeyedReferenceGroup;
import org.uddi.api_v3.Name;
import org.uddi.api_v3.OverviewDoc;
import org.uddi.api_v3.SaveBinding;
import org.uddi.api_v3.SaveBusiness;
import org.uddi.api_v3.SaveService;
import org.uddi.api_v3.SaveTModel;
import org.uddi.v3_service.DispositionReportFaultMessage;


/**
 * @author <a href="mailto:jfaath@apache.org">Jeff Faath</a>
 * @author <a href="mailto:tcunning@apache.org">Tom Cunningham</a>
 */
public class ValidatePublish extends ValidateUDDIApi {

	Logger log = Logger.getLogger(this.getClass());
	
	public ValidatePublish(UddiEntityPublisher publisher) {
		super(publisher);
	}

	public void validateDeleteBusiness(EntityManager em, DeleteBusiness body) throws DispositionReportFaultMessage {

		// No null input
		if (body == null)
			throw new FatalErrorException(new ErrorMessage("errors.NullInput"));
		
		// No null or empty list
		List<String> entityKeyList = body.getBusinessKey();
		if (entityKeyList == null || entityKeyList.size() == 0)
			throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.NoKeys"));

		HashSet<String> dupCheck = new HashSet<String>();
		for (String entityKey : entityKeyList) {
			boolean inserted = dupCheck.add(entityKey);
			if (!inserted)
				throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.DuplicateKey", entityKey));
			
			Object obj = em.find(org.apache.juddi.model.BusinessEntity.class, entityKey);
			if (obj == null)
				throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.BusinessNotFound", entityKey));
			
			if (!publisher.isOwner((UddiEntity)obj))
				throw new UserMismatchException(new ErrorMessage("errors.usermismatch.InvalidOwner", entityKey));
			
		}
	}

	public void validateDeleteService(EntityManager em, DeleteService body) throws DispositionReportFaultMessage {

		// No null input
		if (body == null)
			throw new FatalErrorException(new ErrorMessage("errors.NullInput"));
		
		// No null or empty list
		List<String> entityKeyList = body.getServiceKey();
		if (entityKeyList == null || entityKeyList.size() == 0)
			throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.NoKeys"));

		HashSet<String> dupCheck = new HashSet<String>();
		for (String entityKey : entityKeyList) {
			boolean inserted = dupCheck.add(entityKey);
			if (!inserted)
				throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.DuplicateKey", entityKey));
			
			Object obj = em.find(org.apache.juddi.model.BusinessService.class, entityKey);
			if (obj == null)
				throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.ServiceNotFound", entityKey));
			
			if (!publisher.isOwner((UddiEntity)obj))
				throw new UserMismatchException(new ErrorMessage("errors.usermismatch.InvalidOwner", entityKey));
			
		}
	}

	public void validateDeleteBinding(EntityManager em, DeleteBinding body) throws DispositionReportFaultMessage {

		// No null input
		if (body == null)
			throw new FatalErrorException(new ErrorMessage("errors.NullInput"));
		
		// No null or empty list
		List<String> entityKeyList = body.getBindingKey();
		if (entityKeyList == null || entityKeyList.size() == 0)
			throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.NoKeys"));

		// Checking for duplicates and existence
		HashSet<String> dupCheck = new HashSet<String>();
		for (String entityKey : entityKeyList) {
			boolean inserted = dupCheck.add(entityKey);
			if (!inserted)
				throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.DuplicateKey", entityKey));
			
			Object obj = em.find(org.apache.juddi.model.BindingTemplate.class, entityKey);
			if (obj == null)
				throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.BindingTemplateNotFound", entityKey));
			
			if (!publisher.isOwner((UddiEntity)obj))
				throw new UserMismatchException(new ErrorMessage("errors.usermismatch.InvalidOwner", entityKey));

		}
	}
	
	public void validateDeleteTModel(EntityManager em, DeleteTModel body) throws DispositionReportFaultMessage {

		// No null input
		if (body == null)
			throw new FatalErrorException(new ErrorMessage("errors.NullInput"));
		
		// No null or empty list
		List<String> entityKeyList = body.getTModelKey();
		if (entityKeyList == null || entityKeyList.size() == 0)
			throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.NoKeys"));

		HashSet<String> dupCheck = new HashSet<String>();
		for (String entityKey : entityKeyList) {
			boolean inserted = dupCheck.add(entityKey);
			if (!inserted)
				throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.DuplicateKey", entityKey));
			
			Object obj = em.find(org.apache.juddi.model.Tmodel.class, entityKey);
			if (obj == null)
				throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.TModelNotFound", entityKey));
			
			if (!publisher.isOwner((UddiEntity)obj))
				throw new UserMismatchException(new ErrorMessage("errors.usermismatch.InvalidOwner", entityKey));
			
		}
	}

	public void validateDeletePublisherAssertions(EntityManager em, DeletePublisherAssertions body) throws DispositionReportFaultMessage {

		// No null input
		if (body == null)
			throw new FatalErrorException(new ErrorMessage("errors.NullInput"));
		
		// No null or empty list
		List<org.uddi.api_v3.PublisherAssertion> entityList = body.getPublisherAssertion();
		if (entityList == null || entityList.size() == 0)
			throw new AssertionNotFoundException(new ErrorMessage("errors.pubassertion.NoPubAssertions"));

		for (org.uddi.api_v3.PublisherAssertion entity : entityList) {
			
			validatePublisherAssertion(em, entity);
			
			org.apache.juddi.model.PublisherAssertionId pubAssertionId = new org.apache.juddi.model.PublisherAssertionId(entity.getFromKey(), entity.getToKey());
			Object obj = em.find(org.apache.juddi.model.PublisherAssertion.class, pubAssertionId);
			if (obj == null)
				throw new AssertionNotFoundException(new ErrorMessage("errors.pubassertion.AssertionNotFound", entity.getFromKey() + ", " + entity.getToKey()));
			else {
				org.apache.juddi.model.PublisherAssertion pubAssertion = (org.apache.juddi.model.PublisherAssertion)obj;
				org.uddi.api_v3.KeyedReference keyedRef = entity.getKeyedReference();
				if (keyedRef == null)
					throw new AssertionNotFoundException(new ErrorMessage("errors.pubassertion.AssertionNotFound", entity.getFromKey() + ", " + entity.getToKey()));

				if (!pubAssertion.getTmodelKey().equalsIgnoreCase(keyedRef.getTModelKey()) || 
					!pubAssertion.getKeyName().equalsIgnoreCase(keyedRef.getKeyName()) || 
					!pubAssertion.getKeyValue().equalsIgnoreCase(keyedRef.getKeyValue()))
					throw new AssertionNotFoundException(new ErrorMessage("errors.pubassertion.AssertionNotFound", entity.getFromKey() + ", " + entity.getToKey()));

			}
			
		}
	}

	
	public void validateSaveBusiness(EntityManager em, SaveBusiness body, Configuration config) throws DispositionReportFaultMessage {

		if (config==null) {
			try {
				config = AppConfig.getConfiguration();
			} catch (ConfigurationException ce){
				log.error("Could not optain config. " + ce.getMessage(), ce);
			}
		}
		// No null input
		if (body == null)
			throw new FatalErrorException(new ErrorMessage("errors.NullInput"));
		
		// No null or empty list
		List<org.uddi.api_v3.BusinessEntity> entityList = body.getBusinessEntity();
		if (entityList == null || entityList.size() == 0)
			throw new ValueNotAllowedException(new ErrorMessage("errors.savebusiness.NoInput"));
		
		for (org.uddi.api_v3.BusinessEntity entity : entityList) {
			validateBusinessEntity(em, entity, config);
		}
	}
	
	public void validateSaveService(EntityManager em, SaveService body, Configuration config) throws DispositionReportFaultMessage {

		if (config==null) {
			try {
				config = AppConfig.getConfiguration();
			} catch (ConfigurationException ce){
				log.error("Could not optain config. " + ce.getMessage(), ce);
			}
		}
		// No null input
		if (body == null)
			throw new FatalErrorException(new ErrorMessage("errors.NullInput"));
		
		// No null or empty list
		List<org.uddi.api_v3.BusinessService> entityList = body.getBusinessService();
		if (entityList == null || entityList.size() == 0)
			throw new ValueNotAllowedException(new ErrorMessage("errors.saveservice.NoInput"));
		
		for (org.uddi.api_v3.BusinessService entity : entityList) {
			// Entity specific data validation
			validateBusinessService(em, entity, null, config);
		}
	}
	
	public void validateSaveBinding(EntityManager em, SaveBinding body, Configuration config) throws DispositionReportFaultMessage {

		if (config==null) {
			try {
				config = AppConfig.getConfiguration();
			} catch (ConfigurationException ce){
				log.error("Could not optain config. " + ce.getMessage(), ce);
			}
		}
		// No null input
		if (body == null)
			throw new FatalErrorException(new ErrorMessage("errors.NullInput"));
		
		// No null or empty list
		List<org.uddi.api_v3.BindingTemplate> entityList = body.getBindingTemplate();
		if (entityList == null || entityList.size() == 0)
			throw new ValueNotAllowedException(new ErrorMessage("errors.savebinding.NoInput"));
		
		for (org.uddi.api_v3.BindingTemplate entity : entityList) {
			validateBindingTemplate(em, entity, null, config);
		}
	}

	public void validateSaveTModel(EntityManager em, SaveTModel body, Configuration config) throws DispositionReportFaultMessage {

		if (config==null) {
			try {
				config = AppConfig.getConfiguration();
				log.info("Configuration:" + config);
			} catch (ConfigurationException ce){
				log.error("Could not optain config. " + ce.getMessage(), ce);
			}
		}
		// No null input
		if (body == null)
			throw new FatalErrorException(new ErrorMessage("errors.NullInput"));
		
		// No null or empty list
		List<org.uddi.api_v3.TModel> entityList = body.getTModel();
		if (entityList == null || entityList.size() == 0)
			throw new ValueNotAllowedException(new ErrorMessage("errors.savetmodel.NoInput"));
		
		for (org.uddi.api_v3.TModel entity : entityList) {
			validateTModel(em, entity, config);
		}
	}

	public void validateAddPublisherAssertions(EntityManager em, AddPublisherAssertions body) throws DispositionReportFaultMessage {

		// No null input
		if (body == null)
			throw new FatalErrorException(new ErrorMessage("errors.NullInput"));
		
		// No null or empty list
		List<org.uddi.api_v3.PublisherAssertion> entityList = body.getPublisherAssertion();
		if (entityList == null || entityList.size() == 0)
			throw new ValueNotAllowedException(new ErrorMessage("errors.addpublisherassertions.NoInput"));
		
		for (org.uddi.api_v3.PublisherAssertion entity : entityList) {
			validatePublisherAssertion(em, entity);
		}
	}
	
	public void validateSetPublisherAssertions(EntityManager em, Holder<List<org.uddi.api_v3.PublisherAssertion>> body) throws DispositionReportFaultMessage {

		// No null input
		if (body == null)
			throw new FatalErrorException(new ErrorMessage("errors.NullInput"));
		
		// Assertion list can be null or empty - it signifies that publisher is deleting all their assertions
		List<org.uddi.api_v3.PublisherAssertion> entityList = body.value;
		if (entityList != null && entityList.size() > 0) {
		
			for (org.uddi.api_v3.PublisherAssertion entity : entityList) {
				validatePublisherAssertion(em, entity);
			}
		}
	}
	
	public void validateBusinessEntity(EntityManager em, org.uddi.api_v3.BusinessEntity businessEntity, Configuration config) throws DispositionReportFaultMessage {
		
		// A supplied businessEntity can't be null
		if (businessEntity == null)
			throw new ValueNotAllowedException(new ErrorMessage("errors.businessentity.NullInput"));
		
		boolean entityExists = false;
		String entityKey = businessEntity.getBusinessKey();
		if (entityKey == null || entityKey.length() == 0) {
			KeyGenerator keyGen = KeyGeneratorFactory.getKeyGenerator();
			entityKey = keyGen.generate();
			businessEntity.setBusinessKey(entityKey);
		}
		else {
			// Per section 4.4: keys must be case-folded
			entityKey = entityKey.toLowerCase();
			businessEntity.setBusinessKey(entityKey);
			
			Object obj = em.find(org.apache.juddi.model.BusinessEntity.class, entityKey);
			if (obj != null) {
				entityExists = true;

				// Make sure publisher owns this entity.
				if (!publisher.isOwner((UddiEntity)obj))
					throw new UserMismatchException(new ErrorMessage("errors.usermismatch.InvalidOwner", entityKey));
			}
			else {
				// Inside this block, we have a key proposed by the publisher on a new entity

				// Validate key and then check to see that the proposed key is valid for this publisher
				ValidateUDDIKey.validateUDDIv3Key(entityKey);
				if (!publisher.isValidPublisherKey(em, entityKey))
					throw new KeyUnavailableException(new ErrorMessage("errors.keyunavailable.BadPartition", entityKey));

			}
		}

		if (!entityExists) {
			// Check to make sure key isn't used by another entity.
			if (!isUniqueKey(em, entityKey))
				throw new KeyUnavailableException(new ErrorMessage("errors.keyunavailable.KeyExists", entityKey));
		}
		
		// TODO: validate "checked" categories or category groups (see section 5.2.3 of spec)? optional to support
		
		validateNames(businessEntity.getName());
		validateDiscoveryUrls(businessEntity.getDiscoveryURLs());
		validateContacts(businessEntity.getContacts());
		validateCategoryBag(businessEntity.getCategoryBag(), config);
		validateIdentifierBag(businessEntity.getIdentifierBag(), config);

		validateBusinessServices(em, businessEntity.getBusinessServices(), businessEntity, config);
		
	}

	public void validateBusinessServices(EntityManager em, org.uddi.api_v3.BusinessServices businessServices, org.uddi.api_v3.BusinessEntity parent, Configuration config)
					throws DispositionReportFaultMessage {
		// Business services is optional
		if (businessServices == null)
			return;
		
		List<org.uddi.api_v3.BusinessService> businessServiceList = businessServices.getBusinessService();
		if (businessServiceList == null || businessServiceList.size() == 0)
			throw new ValueNotAllowedException(new ErrorMessage("errors.businessservices.NoInput"));
		
		for (org.uddi.api_v3.BusinessService businessService : businessServiceList) {
			validateBusinessService(em, businessService, parent, config);
		}
			
	}

	public void validateBusinessService(EntityManager em, org.uddi.api_v3.BusinessService businessService, org.uddi.api_v3.BusinessEntity parent, Configuration config) 
					throws DispositionReportFaultMessage {

		// A supplied businessService can't be null
		if (businessService == null)
			throw new ValueNotAllowedException(new ErrorMessage("errors.businessservice.NullInput"));
		
		// Retrieve the service's passed key
		String entityKey = businessService.getServiceKey();
		if (entityKey != null && entityKey.length() > 0) {
			// Per section 4.4: keys must be case-folded
			entityKey = entityKey.toLowerCase();
			businessService.setServiceKey(entityKey);
		}
		
		// The parent key is either supplied or provided by the higher call to the parent entity save.  If the passed-in parent's business key differs from 
		// the (non-null) business key retrieved from the service, then we have a possible service projection.
		String parentKey = businessService.getBusinessKey();
		if (parentKey != null && parentKey.length() > 0) {
			// Per section 4.4: keys must be case-folded
			parentKey = parentKey.toLowerCase();
			businessService.setBusinessKey(parentKey);
		}
		
		boolean isProjection = false;
		if (parent != null) {
			if (parentKey != null && parentKey.length() > 0) {
				if (!parentKey.equalsIgnoreCase(parent.getBusinessKey())) {
					// Possible projected service - if we have differing parent businesses but a service key was not provided, this is an error as it is not possible 
					// for the business that doesn't "own" the service to generate the key for it.
					if (entityKey == null || entityKey.length() == 0)
						throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.ServiceKeyNotProvidedWithProjection", parentKey + ", " + parent.getBusinessKey()));

					isProjection = true;
				}
			}
			else
				parentKey = parent.getBusinessKey();
		}
		
		// Projections don't require as rigorous testing as only the projected service's business key and service key are examined for validity.
		if (isProjection) {
			Object obj = em.find(org.apache.juddi.model.BusinessService.class, entityKey);
			// Can't project a service that doesn't exist!
			if (obj == null)
				throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.ProjectedServiceNotFound", parentKey + ", " + entityKey));
			else {
				// If the supplied business key doesn't match the existing service's business key, the projection is invalid.
				org.apache.juddi.model.BusinessService bs = (org.apache.juddi.model.BusinessService)obj;
				if (!businessService.getBusinessKey().equalsIgnoreCase(bs.getBusinessEntity().getEntityKey()))
					throw new InvalidProjectionException(new ErrorMessage("errors.invalidprojection.ParentMismatch", businessService.getBusinessKey() + ", " + bs.getBusinessEntity().getEntityKey()));
			}
			obj = null;
		}
		else {
			boolean entityExists = false;
			if (entityKey == null || entityKey.length() == 0) {
				KeyGenerator keyGen = KeyGeneratorFactory.getKeyGenerator();
				entityKey = keyGen.generate();
				businessService.setServiceKey(entityKey);
			}
			else {
				
				Object obj = em.find(org.apache.juddi.model.BusinessService.class, entityKey);
				if (obj != null) {
					entityExists = true;

					org.apache.juddi.model.BusinessService bs = (org.apache.juddi.model.BusinessService)obj;
					
					// If the object exists, and the parentKey was not found to this point, then a save on an existing service with a blank
					// business key has occurred.  It is set here and added to the entity being saved - a necessary step for the object to be
					// persisted properly. (This condition makes some validation tests below unnecessary as the parent is "verified" but it's OK to
					// still run them).
					if (parentKey == null || parentKey.length() == 0) {
						parentKey = bs.getBusinessEntity().getEntityKey();
						businessService.setBusinessKey(parentKey);
					}
					
					// If existing service trying to be saved has a different parent key, then we have a problem
					// TODO: moving services is allowed according to spec?
					if (!parentKey.equalsIgnoreCase(bs.getBusinessEntity().getEntityKey()))
						throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.businessservice.ParentMismatch", parentKey + ", " + bs.getBusinessEntity().getEntityKey()));
					
					// Make sure publisher owns this entity.
					if (!publisher.isOwner((UddiEntity)obj))
						throw new UserMismatchException(new ErrorMessage("errors.usermismatch.InvalidOwner", entityKey));
					
				}
				else {
					// Inside this block, we have a key proposed by the publisher on a new entity

					// Validate key and then check to see that the proposed key is valid for this publisher
					ValidateUDDIKey.validateUDDIv3Key(entityKey);
					if (!publisher.isValidPublisherKey(em, entityKey))
						throw new KeyUnavailableException(new ErrorMessage("errors.keyunavailable.BadPartition", entityKey));

				}
				
			}
			
			// Parent key must be passed if this is a new entity
			if (!entityExists) {
				if (parentKey == null || parentKey.length() == 0)
					throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.ParentBusinessNotFound", parentKey));
			}

			// If parent key IS passed, whether new entity or not, it must be valid.  Additionally, the current publisher must be the owner of the parent.  Note that
			// if a parent ENTITY was passed in, then we don't need to check for any of this since this is part of a higher call.
			if (parentKey != null) {
				if (parent == null) {
					Object parentTemp = em.find(org.apache.juddi.model.BusinessEntity.class, parentKey);
					if (parentTemp == null)
						throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.ParentBusinessNotFound", parentKey));

					// Make sure publisher owns this parent entity.
					if (!publisher.isOwner((UddiEntity)parentTemp))
						throw new UserMismatchException(new ErrorMessage("errors.usermismatch.InvalidOwnerParent", parentKey));
				}
			}

			if (!entityExists) {
				// Check to make sure key isn't used by another entity.
				if (!isUniqueKey(em, entityKey))
					throw new KeyUnavailableException(new ErrorMessage("errors.keyunavailable.KeyExists", entityKey));
			}
			
			// TODO: validate "checked" categories or category groups (see section 5.2.3 of spec)? optional to support
			
			validateNames(businessService.getName());
			validateCategoryBag(businessService.getCategoryBag(), config);
			
			validateBindingTemplates(em, businessService.getBindingTemplates(), businessService, config);
		}
		
	}

	public void validateBindingTemplates(EntityManager em, org.uddi.api_v3.BindingTemplates bindingTemplates, org.uddi.api_v3.BusinessService parent, Configuration config)
					throws DispositionReportFaultMessage {
		// Binding templates is optional
		if (bindingTemplates == null)
			return;
	
		List<org.uddi.api_v3.BindingTemplate> bindingTemplateList = bindingTemplates.getBindingTemplate();
		if (bindingTemplateList == null || bindingTemplateList.size() == 0)
			throw new ValueNotAllowedException(new ErrorMessage("errors.bindingtemplates.NoInput"));
	
		for (org.uddi.api_v3.BindingTemplate bindingTemplate : bindingTemplateList) {
			validateBindingTemplate(em, bindingTemplate, parent, config);
		}
	
	}
	
	public void validateBindingTemplate(EntityManager em, org.uddi.api_v3.BindingTemplate bindingTemplate, 
			org.uddi.api_v3.BusinessService parent, Configuration config) 
					throws DispositionReportFaultMessage {

		// A supplied bindingTemplate can't be null
		if (bindingTemplate == null)
			throw new ValueNotAllowedException(new ErrorMessage("errors.bindingtemplate.NullInput"));

		// Retrieve the binding's passed key
		String entityKey = bindingTemplate.getBindingKey();
		if (entityKey != null && entityKey.length() > 0) {
			// Per section 4.4: keys must be case-folded
			entityKey = entityKey.toLowerCase();
			bindingTemplate.setBindingKey(entityKey);
		}

		// The parent key is either supplied or provided by the higher call to the parent entity save.  If it is provided in both instances, if they differ, an 
		// error occurs.
		String parentKey = bindingTemplate.getServiceKey();
		if (parentKey != null && parentKey.length() > 0) {
			// Per section 4.4: keys must be case-folded
			parentKey = parentKey.toLowerCase();
			bindingTemplate.setServiceKey(parentKey);
		}
		
		if (parent != null) {
			if (parentKey != null && parentKey.length() > 0) {
				if (!parentKey.equalsIgnoreCase(parent.getServiceKey()))
					throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.bindingtemplate.ParentMismatch", parentKey + ", " + parent.getBusinessKey()));
			}
			else
				parentKey = parent.getServiceKey();
		}
		
		boolean entityExists = false;
		if (entityKey == null || entityKey.length() == 0) {
			KeyGenerator keyGen = KeyGeneratorFactory.getKeyGenerator();
			entityKey = keyGen.generate();
			bindingTemplate.setBindingKey(entityKey);
		}
		else {
			
			Object obj = em.find(org.apache.juddi.model.BindingTemplate.class, entityKey);
			if (obj != null) {
				entityExists = true;

				org.apache.juddi.model.BindingTemplate bt = (org.apache.juddi.model.BindingTemplate)obj;

				// If the object exists, and the parentKey was not found to this point, then a save on an existing binding with a blank
				// service key has occurred.  It is set here and added to the entity being saved - a necessary step for the object to be
				// persisted properly. (This condition makes some validation tests below unnecessary as the parent is "verified" but it's OK to
				// still run them).
				if (parentKey == null || parentKey.length() == 0) {
					parentKey = bt.getBusinessService().getEntityKey();
					bindingTemplate.setServiceKey(parentKey);
				}
				
				// If existing binding trying to be saved has a different parent key, then we have a problem
				// TODO: moving bindings is allowed according to spec?
				if (!parentKey.equalsIgnoreCase(bt.getBusinessService().getEntityKey()))
					throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.bindingtemplate.ParentMismatch", parentKey + ", " + bt.getBusinessService().getEntityKey()));
				
				// Make sure publisher owns this entity.
				if (!publisher.isOwner((UddiEntity)obj))
					throw new UserMismatchException(new ErrorMessage("errors.usermismatch.InvalidOwner", entityKey));

			}
			else {
				// Inside this block, we have a key proposed by the publisher on a new entity

				// Validate key and then check to see that the proposed key is valid for this publisher
				ValidateUDDIKey.validateUDDIv3Key(entityKey);
				if (!publisher.isValidPublisherKey(em, entityKey))
					throw new KeyUnavailableException(new ErrorMessage("errors.keyunavailable.BadPartition", entityKey));

			}
			
		}
		
		// Parent key must be passed if this is a new entity
		if (!entityExists) {
			if (parentKey == null || parentKey.length() == 0)
				throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.ParentServiceNotFound", parentKey));
		}

		// If parent key IS passed, whether new entity or not, it must be valid.  Additionally, the current publisher must be the owner of the parent.  Note that
		// if a parent ENTITY was passed in, then we don't need to check for any of this since this is part of a higher call.
		if (parentKey != null) {
			if (parent == null) {
				Object parentTemp = em.find(org.apache.juddi.model.BusinessService.class, parentKey);
				if (parentTemp == null)
					throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.ParentBusinessNotFound", parentKey));

				// Make sure publisher owns this parent entity.
				if (!publisher.isOwner((UddiEntity)parentTemp))
					throw new UserMismatchException(new ErrorMessage("errors.usermismatch.InvalidOwnerParent", parentKey));

			}
		}

		if (!entityExists) {
			// Check to make sure key isn't used by another entity.
			if (!isUniqueKey(em, entityKey))
				throw new KeyUnavailableException(new ErrorMessage("errors.keyunavailable.KeyExists", entityKey));
		}
		
		// TODO: validate "checked" categories or category groups (see section 5.2.3 of spec)? optional to support
		
		
		if (bindingTemplate.getAccessPoint() == null)
			throw new ValueNotAllowedException(new ErrorMessage("errors.bindingtemplate.NoAccessPoint"));
		
		validateCategoryBag(bindingTemplate.getCategoryBag(), config);
		validateTModelInstanceDetails(bindingTemplate.getTModelInstanceDetails());
		
		
	}

	public void validateTModel(EntityManager em, org.uddi.api_v3.TModel tModel, Configuration config) throws DispositionReportFaultMessage {
		// A supplied tModel can't be null
		if (tModel == null)
			throw new ValueNotAllowedException(new ErrorMessage("errors.tmodel.NullInput"));
		
		boolean entityExists = false;
		String entityKey = tModel.getTModelKey();
		if (entityKey == null || entityKey.length() == 0) {
			KeyGenerator keyGen = KeyGeneratorFactory.getKeyGenerator();
			entityKey = keyGen.generate();
			tModel.setTModelKey(entityKey);
		}
		else {
			// Per section 4.4: keys must be case-folded
			entityKey = entityKey.toLowerCase();
			tModel.setTModelKey(entityKey);
			
			Object obj = em.find(org.apache.juddi.model.Tmodel.class, entityKey);
			if (obj != null) {
				entityExists = true;

				// Make sure publisher owns this entity.
				if (!publisher.isOwner((UddiEntity)obj))
					throw new UserMismatchException(new ErrorMessage("errors.usermismatch.InvalidOwner", entityKey));
			}
			else {
				// Inside this block, we have a key proposed by the publisher on a new entity
				
				// First test to see if this is a Key Generator tModel. The keyGenerator suffix appearing in the key is the indicator, since this is not
				// allowed *unless* it's a key generator.
				if (entityKey.toUpperCase().contains(KeyGenerator.KEYGENERATOR_SUFFIX.toUpperCase())) {
					ValidateUDDIKey.validateUDDIv3KeyGeneratorTModel(tModel);
					
					// The root publisher is only allowed one key generator.  This is published in the installation.
					String rootPublisherStr = "root";
					try {
						rootPublisherStr = AppConfig.getConfiguration().getString(Property.JUDDI_ROOT_PUBLISHER);
					} catch (ConfigurationException ce) {
						log.error("Could not read the root publisher setting in the configuration.");
					}
					if (publisher.getAuthorizedName().equals(rootPublisherStr))
						throw new FatalErrorException(new ErrorMessage("errors.tmodel.keygenerator.RootKeyGen"));
					
					// It's a valid Key Generator, but is it available for this publisher?
					if (!publisher.isKeyGeneratorAvailable(em, entityKey))
						throw new KeyUnavailableException(new ErrorMessage("errors.keyunavailable.BadPartition", entityKey));
				
				}
				else {
					// If not a key generator, then simply validate key and then check to see that the proposed key is valid for this publisher
					ValidateUDDIKey.validateUDDIv3Key(entityKey);
					if (!publisher.isValidPublisherKey(em, entityKey))
						throw new KeyUnavailableException(new ErrorMessage("errors.keyunavailable.BadPartition", entityKey));
				}
			}
		}

		if (!entityExists) {
			// Check to make sure key isn't used by another entity.
			if (!isUniqueKey(em, entityKey))
				throw new KeyUnavailableException(new ErrorMessage("errors.keyunavailable.KeyExists", entityKey));
		}
		
		// TODO: validate "checked" categories or category groups (see section 5.2.3 of spec)? optional to support
		
		if (tModel.getName() == null)
			throw new ValueNotAllowedException(new ErrorMessage("errors.tmodel.NoName"));
		
		validateCategoryBag(tModel.getCategoryBag(), config);
		validateIdentifierBag(tModel.getIdentifierBag(), config);

		List<org.uddi.api_v3.OverviewDoc> overviewDocList = tModel.getOverviewDoc();
		if (overviewDocList != null) {
			for (org.uddi.api_v3.OverviewDoc overviewDoc : overviewDocList)
				validateOverviewDoc(overviewDoc);
		}

	}
	
	public void validatePublisherAssertion(EntityManager em, org.uddi.api_v3.PublisherAssertion pubAssertion) throws DispositionReportFaultMessage {
		// A supplied publisher assertion can't be null
		if (pubAssertion == null)
			throw new ValueNotAllowedException(new ErrorMessage("errors.pubassertion.NullInput"));
		
		// The keyedRef must not be blank and every field must contain data.
		org.uddi.api_v3.KeyedReference keyedRef = pubAssertion.getKeyedReference();
		if (keyedRef == null || 
			keyedRef.getTModelKey() == null || keyedRef.getTModelKey().length() == 0 ||
			keyedRef.getKeyName() == null || keyedRef.getKeyName().length() == 0 ||
			keyedRef.getKeyValue() == null || keyedRef.getKeyValue().length() == 0)
			throw new ValueNotAllowedException(new ErrorMessage("errors.pubassertion.BlankKeyedRef"));
		
		String fromKey = pubAssertion.getFromKey();
		if (fromKey == null || fromKey.length() == 0)
			throw new ValueNotAllowedException(new ErrorMessage("errors.pubassertion.BlankFromKey"));

		String toKey = pubAssertion.getToKey();
		if (toKey == null || toKey.length() == 0)
			throw new ValueNotAllowedException(new ErrorMessage("errors.pubassertion.BlankToKey"));
		
		if (fromKey.equalsIgnoreCase(toKey))
			throw new ValueNotAllowedException(new ErrorMessage("errors.pubassertion.SameBusinessKey"));
		
		Object fromObj = em.find(org.apache.juddi.model.BusinessEntity.class, fromKey);
		if (fromObj == null)
			throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.BusinessNotFound", fromKey));

		Object toObj = em.find(org.apache.juddi.model.BusinessEntity.class, pubAssertion.getToKey());
		if (toObj == null)
			throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.BusinessNotFound", toKey));
		
		if (!publisher.isOwner((UddiEntity)fromObj) && !publisher.isOwner((UddiEntity)toObj))
			throw new UserMismatchException(new ErrorMessage("errors.pubassertion.UserMismatch", fromKey + " & " + toKey));
			
	}
	
	public void validateNames(List<org.uddi.api_v3.Name> names) throws DispositionReportFaultMessage {
		// At least one name is required
		if (names == null || names.size() == 0)
			throw new ValueNotAllowedException(new ErrorMessage("errors.names.NoInput"));
		
		for (Name n : names) {
			if (n.getValue() == null || n.getValue().length() == 0)
				throw new ValueNotAllowedException(new ErrorMessage("errors.names.NoValue"));
		}
		
	}
	
	public void validateContacts(org.uddi.api_v3.Contacts contacts) throws DispositionReportFaultMessage {
		// Contacts is optional
		if (contacts == null)
			return;
		
		// If contacts do exist, at least one contact is required
		List<org.uddi.api_v3.Contact> contactList = contacts.getContact();
		if (contactList == null || contactList.size() == 0)
			throw new ValueNotAllowedException(new ErrorMessage("errors.contacts.NoInput"));
		
		for (org.uddi.api_v3.Contact contact : contactList) {
			validateContact(contact);
		}
		
	}

	public void validateContact(org.uddi.api_v3.Contact contact) throws DispositionReportFaultMessage {
		// A supplied contact can't be null
		if (contact == null)
			throw new ValueNotAllowedException(new ErrorMessage("errors.contact.NullInput"));
		
		// At least one personName is required
		List<org.uddi.api_v3.PersonName> pnameList = contact.getPersonName();
		if (pnameList == null || pnameList.size() == 0)
			throw new ValueNotAllowedException(new ErrorMessage("errors.contact.NoPersonName"));
		
		List<org.uddi.api_v3.Address> addressList = contact.getAddress();
		if (addressList != null) {
			for (org.uddi.api_v3.Address address : addressList) {
				if (address != null) {
					if (address.getAddressLine() == null || address.getAddressLine().size() == 0)
						throw new ValueNotAllowedException(new ErrorMessage("errors.contact.NoAddressLine"));
				}
			}
		}
	}
	
	public void validateDiscoveryUrls(org.uddi.api_v3.DiscoveryURLs discUrls) throws DispositionReportFaultMessage {
		// Discovery Urls is optional
		if (discUrls == null)
			return;

		// If discUrls does exist, it must have at least one element
		List<org.uddi.api_v3.DiscoveryURL> discUrlList = discUrls.getDiscoveryURL();
		if (discUrlList == null || discUrlList.size() == 0)
			throw new ValueNotAllowedException(new ErrorMessage("errors.discurls.NoInput"));
	}	
	
	public void validateCategoryBag(org.uddi.api_v3.CategoryBag categories, Configuration config) throws DispositionReportFaultMessage {
		
		// Category bag is optional
		if (categories == null)
			return;
		
		// If category bag does exist, it must have at least one element
		List<KeyedReference> elems = categories.getKeyedReference();
		List<KeyedReferenceGroup> groups = categories.getKeyedReferenceGroup();
		if (groups.size() == 0 && elems.size() == 0)
			throw new ValueNotAllowedException(new ErrorMessage("errors.categorybag.NoInput"));
		
		for (Object elem : elems) {
			if (elem instanceof org.uddi.api_v3.KeyedReference) {
				validateKeyedReference((KeyedReference) elem, config);
			}
		}
	}

	public void validateIdentifierBag(org.uddi.api_v3.IdentifierBag identifiers, Configuration config) throws DispositionReportFaultMessage {
		
		// Identifier bag is optional
		if (identifiers == null)
			return;
		
		// If category bag does exist, it must have at least one element
		List<org.uddi.api_v3.KeyedReference> keyedRefList = identifiers.getKeyedReference();
		if (keyedRefList == null || keyedRefList.size() == 0)
			throw new ValueNotAllowedException(new ErrorMessage("errors.identifierbag.NoInput"));
		
		for (org.uddi.api_v3.KeyedReference keyedRef : keyedRefList) {
			validateKeyedReference(keyedRef, config);
		}
	}
	

	public void validateKeyedReferenceGroup(KeyedReferenceGroup krg) throws DispositionReportFaultMessage {
		// Keyed reference groups must contain a tModelKey
		if (krg.getTModelKey() == null || krg.getTModelKey().length() == 0)
			throw new ValueNotAllowedException(new ErrorMessage("errors.keyedreference.NoTModelKey"));
	}
	
	public void validateKeyedReference(KeyedReference kr, Configuration config) throws DispositionReportFaultMessage {
		if (kr.getTModelKey() == null || kr.getTModelKey().length() == 0)
			throw new ValueNotAllowedException(new ErrorMessage("errors.keyedreference.NoTModelKey"));
			
		if (kr.getKeyValue() == null || kr.getKeyValue().length() == 0)
			throw new ValueNotAllowedException(new ErrorMessage("errors.keyedreference.NoKeyValue"));
			
		String rootPublisherStr = config.getString(Property.JUDDI_ROOT_PUBLISHER);
		// Per section 6.2.2.1 of the specification, no publishers (except the root) are allowed to use the node categorization tmodelKey
		if (Constants.NODE_CATEGORY_TMODEL.equalsIgnoreCase(kr.getTModelKey())) {
			if (!rootPublisherStr.equals(publisher.getAuthorizedName()))
				throw new ValueNotAllowedException(new ErrorMessage("errors.keyedreference.NodeCategoryTModel", Constants.NODE_CATEGORY_TMODEL));
		}
	}

	public void validateTModelInstanceDetails(org.uddi.api_v3.TModelInstanceDetails tmodelInstDetails) throws DispositionReportFaultMessage {

		// tModel Instance Details is optional
		if (tmodelInstDetails == null)
			return;
		
		// If tmodelInstDetails does exist, it must have at least one element
		List<org.uddi.api_v3.TModelInstanceInfo> tmodelInstInfoList = tmodelInstDetails.getTModelInstanceInfo();
		if (tmodelInstInfoList == null || tmodelInstInfoList.size() == 0)
			throw new ValueNotAllowedException(new ErrorMessage("errors.tmodelinstdetails.NoInput"));
		
		for (org.uddi.api_v3.TModelInstanceInfo tmodelInstInfo : tmodelInstInfoList) {
			validateTModelInstanceInfo(tmodelInstInfo);
		}
	}

	public void validateTModelInstanceInfo(org.uddi.api_v3.TModelInstanceInfo tmodelInstInfo) throws DispositionReportFaultMessage {
		// tModel Instance Info can't be null
		if (tmodelInstInfo == null)
			throw new ValueNotAllowedException(new ErrorMessage("errors.tmodelinstinfo.NullInput"));
		
		// TModel key is required
		if (tmodelInstInfo.getTModelKey() == null || tmodelInstInfo.getTModelKey().length() == 0)
			throw new ValueNotAllowedException(new ErrorMessage("errors.tmodelinstinfo.NoTModelKey"));
		
		validateInstanceDetails(tmodelInstInfo.getInstanceDetails());
		
	}

	public void validateInstanceDetails(org.uddi.api_v3.InstanceDetails instDetails) throws DispositionReportFaultMessage {
		// Instance Details is optional
		if (instDetails == null)
			return;
		
		// At least one OverviewDoc or instanceParms must be supplied
		List<OverviewDoc> elems = instDetails.getOverviewDoc();
		if (instDetails.getInstanceParms() == null && elems.size() == 0)
			throw new ValueNotAllowedException(new ErrorMessage("errors.instdetails.NoOverviewOrParms"));
		
	}
	
	public void validateOverviewDoc(org.uddi.api_v3.OverviewDoc overviewDoc) throws DispositionReportFaultMessage {
		// OverviewDoc can't be null
		if (overviewDoc == null)
			throw new ValueNotAllowedException(new ErrorMessage("errors.overviewdoc.NullInput"));
		
		// At least one description or overview URL must be supplied
		List<org.uddi.api_v3.Description> elems = overviewDoc.getDescription();
		if ((elems == null || elems.size() == 0 ) && overviewDoc.getOverviewURL() == null)
			throw new ValueNotAllowedException(new ErrorMessage("errors.overviewdoc.NoDescOrUrl"));
	}

	public void validateRegisteredInfo(org.uddi.api_v3.GetRegisteredInfo body) throws DispositionReportFaultMessage {
		// No null input
		if (body == null)
			throw new FatalErrorException(new ErrorMessage("errors.NullInput"));
		
		// infoSelection is required
		if (body.getInfoSelection() == null)
			throw new ValueNotAllowedException(new ErrorMessage("errors.getregisteredinfo.NoInfoSelection"));
			
	}
	
	/*-------------------------------------------------------------------
	 Publishing API functions are specific to jUDDI.
	 --------------------------------------------------------------------*/
	
	public void validateDeletePublisher(EntityManager em, DeletePublisher body) throws DispositionReportFaultMessage {

		// No null input
		if (body == null)
			throw new FatalErrorException(new ErrorMessage("errors.NullInput"));
		
		// No null or empty list
		List<String> entityKeyList = body.getPublisherId();
		if (entityKeyList == null || entityKeyList.size() == 0)
			throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.NoKeys"));
		
		if (!((Publisher)publisher).isAdmin())
			throw new UserMismatchException(new ErrorMessage("errors.deletepublisher.AdminReqd"));

		HashSet<String> dupCheck = new HashSet<String>();
		for (String entityKey : entityKeyList) {
			boolean inserted = dupCheck.add(entityKey);
			if (!inserted)
				throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.DuplicateKey", entityKey));
			
			Object obj = em.find(org.apache.juddi.model.Publisher.class, entityKey);
			if (obj == null)
				throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.PublisherNotFound", entityKey));
			
		}
	}

	public void validateSavePublisher(EntityManager em, SavePublisher body) throws DispositionReportFaultMessage {

		// No null input
		if (body == null)
			throw new FatalErrorException(new ErrorMessage("errors.NullInput"));
		
		// No null or empty list
		List<org.apache.juddi.api_v3.Publisher> entityList = body.getPublisher();
		if (entityList == null || entityList.size() == 0)
			throw new ValueNotAllowedException(new ErrorMessage("errors.savepublisher.NoInput"));
		
		if (!((Publisher)publisher).isAdmin())
			throw new UserMismatchException(new ErrorMessage("errors.savepublisher.AdminReqd"));
		
		for (org.apache.juddi.api_v3.Publisher entity : entityList) {
			validatePublisher(em, entity);
		}
	}

	public void validatePublisher(EntityManager em, org.apache.juddi.api_v3.Publisher publisher) throws DispositionReportFaultMessage {

		// No null input
		if (publisher == null)
			throw new ValueNotAllowedException(new ErrorMessage("errors.publisher.NullInput"));
		
		String authorizedName = publisher.getAuthorizedName();
		if (authorizedName == null || authorizedName.length() == 0)
			throw new ValueNotAllowedException(new ErrorMessage("errors.publisher.NoAuthorizedName"));
	
		String publisherName = publisher.getPublisherName();
		if (publisherName == null || publisherName.length() == 0)
			throw new ValueNotAllowedException(new ErrorMessage("errors.publisher.NoPublisherName"));

	}
	
	public void validateAdminDeleteTModel(EntityManager em, DeleteTModel body) throws DispositionReportFaultMessage {

		// No null input
		if (body == null)
			throw new FatalErrorException(new ErrorMessage("errors.NullInput"));
		
		// No null or empty list
		List<String> entityKeyList = body.getTModelKey();
		if (entityKeyList == null || entityKeyList.size() == 0)
			throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.NoKeys"));
		
		if (!((Publisher)publisher).isAdmin())
			throw new UserMismatchException(new ErrorMessage("errors.AdminReqd"));

		HashSet<String> dupCheck = new HashSet<String>();
		for (String entityKey : entityKeyList) {
			boolean inserted = dupCheck.add(entityKey);
			if (!inserted)
				throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.DuplicateKey", entityKey));
			
			Object obj = em.find(org.apache.juddi.model.Tmodel.class, entityKey);
			if (obj == null)
				throw new InvalidKeyPassedException(new ErrorMessage("errors.invalidkey.TModelNotFound", entityKey));
			
			if (!publisher.isOwner((UddiEntity)obj))
				throw new UserMismatchException(new ErrorMessage("errors.usermismatch.InvalidOwner", entityKey));
			
		}
	}
	
}
