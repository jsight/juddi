<jsp:include page="header.html" />

<%
   String requestName = "delete_publisherAssertions";
   String requestType = "publish";
   String requestKey = requestName+":request";
   String responseKey = requestName+":response";
   String requestTimeKey = requestName+":time";
%>

<h3><%= requestName%></h3>
<div class="link">
The delete_publisherAssertions API call causes one or more publisherAssertion 
elements to be removed from a publisher's assertion collection.  
If any error occurs in processing this API call, a dispositionReport element 
will be returned to the caller within a SOAP Fault containing an error number 
indicating an
<a href="/uddi_errors#E_assertionNotFound">E_assertionNotFound</a>, 
<a href="/uddi_errors#E_authTokenExpired">E_authTokenExpired</a> or 
<a href="/uddi_errors#E_authTokenRequired">E_authTokenRequired</a> 
error was encountered.
</div>

<form method="post" action="controller.jsp">
<textarea class=msgs id=soap_request name=soap_request rows=15 cols=75 wrap=off><%
String requestMessage = (String)session.getAttribute(requestKey);
if (requestMessage != null) {
  out.print(requestMessage);
} else { %>
<?xml version="1.0" encoding="utf-8"?>
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/">
  <soapenv:Body>
    <delete_publisherAssertions generic="2.0" xmlns="urn:uddi-org:api_v2">
      <authInfo>*****</authInfo>
      <publisherAssertion>
        <fromKey>*****</fromKey>
        <toKey>*****</toKey>
        <keyedReference tModelKey="*****" keyName="*****" keyValue="*****" />
      </publisherAssertion>
    </delete_publisherAssertions>
  </soapenv:Body>
</soapenv:Envelope>
<% } %>
</textarea>

<%
String requestTime = (String)session.getAttribute(requestTimeKey);
if (requestTime == null) {
  requestTime = "0";
} %>
<table cellpadding="4" width="100%">
<tr>
<td>
<input type="hidden" name="request_name" value=<%=requestName%>>
<input type="hidden" name="request_type" value=<%=requestType%>>
<input type="submit" name="submit_button" value="Submit">
<input type="submit" name="reset_button" value="Reset">
</td>
<td align="right">
Time: <strong><%= requestTime%></strong> milliseconds
</td>
</tr>
</table>

<textarea class=msgs id=soap_response name=soap_response rows=20 cols=75 wrap=off><%
String responseMessage = (String)session.getAttribute(responseKey);
if (responseMessage != null) {
  out.print(responseMessage);
} %>
</textarea>
</form>

<jsp:include page="footer.html" />