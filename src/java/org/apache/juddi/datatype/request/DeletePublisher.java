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
package org.apache.juddi.datatype.request;

import java.util.Vector;

import org.apache.juddi.datatype.RegistryObject;
import org.apache.juddi.datatype.publisher.PublisherID;

/**
 * "Used to delete registered Publisher information from the registry."
 *
 * @author Steve Viens (sviens@apache.org)
 */
public class DeletePublisher implements RegistryObject,Admin
{
  String generic;
  AuthInfo authInfo;
  Vector publisherIDVector;

  /**
   *
   */
  public DeletePublisher()
  {
  }

  /**
   *
   */
  public DeletePublisher(AuthInfo authInfo, String publisherID)
  {
    this.authInfo = authInfo;
    addPublisherID(publisherID);
  }

  /**
   *
   */
  public DeletePublisher(AuthInfo authInfo,Vector publisherIDs)
  {
    this.authInfo = authInfo;
    this.publisherIDVector = publisherIDs;
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
   * @return String UDDI request's generic value.
   */
  public String getGeneric()
  {
    return this.generic;
  }

  /**
   *
   */
  public void addPublisherID(PublisherID publisherID)
  {
    if ((publisherID != null) && (publisherID.getValue() != null))
      addPublisherID(publisherID.getValue());
  }

  /**
   *
   */
  public void addPublisherID(String publisherID)
  {
    if (this.publisherIDVector == null)
      this.publisherIDVector = new Vector();
    this.publisherIDVector.add(publisherID);
  }

  /**
   *
   */
  public void setPublisherIDVector(Vector publisherIDs)
  {
    this.publisherIDVector = publisherIDs;
  }

  /**
   *
   */
  public Vector getPublisherIDVector()
  {
    return this.publisherIDVector;
  }

  /**
   *
   */
  public void setAuthInfo(AuthInfo authInfo)
  {
    this.authInfo = authInfo;
  }

  /**
   *
   */
  public AuthInfo getAuthInfo()
  {
    return this.authInfo;
  }
}