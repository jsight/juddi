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
package org.apache.juddi.datatype.response;

import org.apache.juddi.datatype.RegistryObject;

/**
 * @author Steve Viens (sviens@apache.org)
 * @author Anou Mana (anou_mana@users.sourceforge.net)
 */
public class RegisteredInfo implements RegistryObject
{
  String generic;
  String operator;
  TModelInfos tModelInfos;
  BusinessInfos businessInfos;

  /**
   *
   */
  public RegisteredInfo()
  {
  }

  /**
   *
   * @param genericValue
   */
  public void setGeneric(String genericValue)
  {
    this.generic = genericValue;
  }

  /**
   *
   * @return String UDDI generic value.
   */
  public String getGeneric()
  {
    return this.generic;
  }

  /**
   *
   */
  public void setOperator(String operator)
  {
    this.operator = operator;
  }

  /**
   *
   */
  public void setTModelInfos(TModelInfos infos)
  {
    this.tModelInfos = infos;
  }

  /**
   *
   */
  public TModelInfos getTModelInfos()
  {
    return this.tModelInfos;
  }

  /**
   *
   */
  public void setBusinessInfos(BusinessInfos infos)
  {
    this.businessInfos = infos;
  }

  /**
   *
   */
  public BusinessInfos getBusinessInfos()
  {
    return this.businessInfos;
  }

  /**
   *
   */
  public String getOperator()
  {
     return this.operator;
  }
}