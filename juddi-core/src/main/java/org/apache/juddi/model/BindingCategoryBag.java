package org.apache.juddi.model;
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
 */

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author <a href="mailto:kurt@apache.org">Kurt T Stam</a>
 */
@Entity
@Table(name = "binding_category_bag")
public class BindingCategoryBag extends CategoryBag {

	private static final long serialVersionUID = 1L;
	private BindingTemplate bindingTemplate;

	public BindingCategoryBag() {
		super();
	}
	
	public BindingCategoryBag(BindingTemplate bindingTemplate) {
		super();
		this.bindingTemplate = bindingTemplate;
	} 
	
	@OneToOne(optional=false)
    @JoinColumn(name = "binding_template_id")
	public BindingTemplate getBindingTemplate() {
		return bindingTemplate;
	}

	public void setBindingTemplate(BindingTemplate bindingTemplate) {
		this.bindingTemplate = bindingTemplate;
	}
}
