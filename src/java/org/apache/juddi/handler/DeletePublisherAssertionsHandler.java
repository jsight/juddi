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
package org.apache.juddi.handler;

import java.util.Vector;

import org.apache.juddi.datatype.RegistryObject;
import org.apache.juddi.datatype.assertion.PublisherAssertion;
import org.apache.juddi.datatype.request.AuthInfo;
import org.apache.juddi.datatype.request.DeletePublisherAssertions;
import org.apache.juddi.util.xml.XMLUtils;
import org.w3c.dom.Element;

/**
 * @author Steve Viens (sviens@apache.org)
 */
public class DeletePublisherAssertionsHandler extends AbstractHandler
{
  public static final String TAG_NAME = "delete_publisherAssertions";

  private HandlerMaker maker = null;

  protected DeletePublisherAssertionsHandler(HandlerMaker maker)
  {
    this.maker = maker;
  }

  public RegistryObject unmarshal(Element element)
  {
    Vector nodeList = null;
    AbstractHandler handler = null;
    DeletePublisherAssertions obj = new DeletePublisherAssertions();

    // Attributes
    String generic = element.getAttribute("generic");
    if ((generic != null && (generic.trim().length() > 0)))
      obj.setGeneric(generic);

    nodeList = XMLUtils.getChildElementsByTagName(element,AuthInfoHandler.TAG_NAME);
    if (nodeList != null && nodeList.size() == 1)
    {
      handler = maker.lookup(AuthInfoHandler.TAG_NAME);
      obj.setAuthInfo((AuthInfo)handler.unmarshal((Element)nodeList.elementAt(0)));
    }

    nodeList = XMLUtils.getChildElementsByTagName(element,PublisherAssertionHandler.TAG_NAME);
    if (nodeList != null && nodeList.size() > 0)
    {
      handler = maker.lookup(PublisherAssertionHandler.TAG_NAME);
      for (int index = 0; index < nodeList.size(); ++index)
      {

        obj.addPublisherAssertion(
          (PublisherAssertion) handler.unmarshal(
            (Element) nodeList.elementAt(index)));
      }
    }
    return obj;
  }

  public void marshal(RegistryObject object, Element parent)
  {
    DeletePublisherAssertions request = (DeletePublisherAssertions) object;
    Element element = parent.getOwnerDocument().createElementNS(null,TAG_NAME);
    AbstractHandler handler = null;

    String generic = request.getGeneric();
    if (generic != null)
      element.setAttribute("generic", generic);

    AuthInfo authInfo = request.getAuthInfo();
    if (authInfo != null)
    {
      handler = maker.lookup(AuthInfoHandler.TAG_NAME);
      handler.marshal(authInfo, element);
    }

    Vector keyVector = request.getPublisherAssertionVector();
    if ((keyVector != null) && (keyVector.size() > 0))
    {
      handler = maker.lookup(PublisherAssertionHandler.TAG_NAME);
      for (int i = 0; i < keyVector.size(); i++)
        handler.marshal(
          (PublisherAssertion) keyVector.elementAt(i),
          element);
    }

    parent.appendChild(element);
  }


  /***************************************************************************/
  /***************************** TEST DRIVER *********************************/
  /***************************************************************************/


  public static void main(String args[]) throws Exception
  {
    HandlerMaker maker = HandlerMaker.getInstance();
    AbstractHandler handler = maker.lookup(DeletePublisherAssertionsHandler.TAG_NAME);

    Element parent = XMLUtils.newRootElement();
    Element child = null;

    AuthInfo authInfo = new AuthInfo();
    authInfo.setValue("6f157513-844e-4a95-a856-d257e6ba9726");

    PublisherAssertion assertion1 = new PublisherAssertion();
    assertion1.setFromKey("6f157513-844e-4a95-a856-d257e6ba0000");
    assertion1.setToKey("6f157513-844e-4a95-a856-d257e6ba1111");

    PublisherAssertion assertion2 = new PublisherAssertion();
    assertion2.setFromKey("6f157513-844e-4a95-a856-d257e6ba2222");
    assertion2.setToKey("6f157513-844e-4a95-a856-d257e6ba3333");

    DeletePublisherAssertions service = new DeletePublisherAssertions();
    service.setAuthInfo(authInfo);
    service.addPublisherAssertion(assertion1);
    service.addPublisherAssertion(assertion2);

    System.out.println();

    RegistryObject regObject = service;
    handler.marshal(regObject,parent);
    child = (Element)parent.getFirstChild();
    parent.removeChild(child);
    XMLUtils.writeXML(child,System.out);

    System.out.println();

    regObject = handler.unmarshal(child);
    handler.marshal(regObject,parent);
    child = (Element)parent.getFirstChild();
    parent.removeChild(child);
    XMLUtils.writeXML(child,System.out);
  }
}