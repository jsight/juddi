
package org.apache.juddi.v3_service;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This service implements the jUDDI API service
 * 
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "JUDDIApiService", targetNamespace = "urn:juddi-apache-org:v3_service", wsdlLocation = "file:/C:/juddi/trunkcur/uddi-ws/src/main/resources/juddi_api_v1.wsdl")
public class JUDDIApiService
    extends Service
{

    private final static URL JUDDIAPISERVICE_WSDL_LOCATION;
    private final static WebServiceException JUDDIAPISERVICE_EXCEPTION;
    private final static QName JUDDIAPISERVICE_QNAME = new QName("urn:juddi-apache-org:v3_service", "JUDDIApiService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/C:/juddi/trunkcur/uddi-ws/src/main/resources/juddi_api_v1.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        JUDDIAPISERVICE_WSDL_LOCATION = url;
        JUDDIAPISERVICE_EXCEPTION = e;
    }

    public JUDDIApiService() {
        super(__getWsdlLocation(), JUDDIAPISERVICE_QNAME);
    }

    public JUDDIApiService(WebServiceFeature... features) {
        super(__getWsdlLocation(), JUDDIAPISERVICE_QNAME, features);
    }

    public JUDDIApiService(URL wsdlLocation) {
        super(wsdlLocation, JUDDIAPISERVICE_QNAME);
    }

    public JUDDIApiService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, JUDDIAPISERVICE_QNAME, features);
    }

    public JUDDIApiService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public JUDDIApiService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns JUDDIApiPortType
     */
    @WebEndpoint(name = "JUDDIApiImplPort")
    public JUDDIApiPortType getJUDDIApiImplPort() {
        return super.getPort(new QName("urn:juddi-apache-org:v3_service", "JUDDIApiImplPort"), JUDDIApiPortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns JUDDIApiPortType
     */
    @WebEndpoint(name = "JUDDIApiImplPort")
    public JUDDIApiPortType getJUDDIApiImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("urn:juddi-apache-org:v3_service", "JUDDIApiImplPort"), JUDDIApiPortType.class, features);
    }

    private static URL __getWsdlLocation() {
        if (JUDDIAPISERVICE_EXCEPTION!= null) {
            throw JUDDIAPISERVICE_EXCEPTION;
        }
        return JUDDIAPISERVICE_WSDL_LOCATION;
    }

}