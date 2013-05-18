<%-- 
/*
 * Copyright 2001-2013 The Apache Software Foundation.
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
    Document   : view subscriptions
    Created on : March 28, 2013, 9:31:19 PM
    Author     : Alex O'Ree
--%>



<%@page import="java.util.List"%>
<%@page import="org.uddi.sub_v3.Subscription"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="org.uddi.api_v3.*"%>
<%@page import="org.apache.juddi.webconsole.PostBackConstants"%>
<%@page import="org.apache.juddi.webconsole.hub.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header-top.jsp" %>
<div class="container">

    <!-- Main hero unit for a primary marketing message or call to action -->
    <div class="well">
        <h1><%=ResourceLoader.GetResource(session, "navbar.subscriptions")%></h1>
    </div>

    <!-- Example row of columns -->
    <div class="row">
        <div class="span12">
            <p>
                <%=ResourceLoader.GetResource(session, "pages.viewsubscriptions.content")%>

            </p>
            <%
                UddiHub x = UddiHub.getInstance(application, session);

                List<Subscription> list = x.GetSubscriptions();
                if (list == null) {
                    out.write(UddiHub.ToErrorAlert(ResourceLoader.GetResource(session, "errors.notsignedin")));
                }
                if (list != null) {
                    if (!list.isEmpty()) {
            %>
            <table class="table table-hover">
                <tr><th><%=ResourceLoader.GetResource(session, "items.key")%></th>
                    <th><%=ResourceLoader.GetResource(session, "items.expires")%></th>
                    <th><%=ResourceLoader.GetResource(session, "items.bindingtemplate.key")%></th>
                    <th><%=ResourceLoader.GetResource(session, "items.actions")%></th></tr>

                <%
                    for (int i = 0; i < list.size(); i++) {
                        out.write("<tr id=\"" + StringEscapeUtils.escapeHtml(list.get(i).getSubscriptionKey()) + "\"><td>");
                        out.write(StringEscapeUtils.escapeHtml(list.get(i).getSubscriptionKey()));
                        out.write("</td><td>");
                        out.write(StringEscapeUtils.escapeHtml(list.get(i).getExpiresAfter().toString()));
                        out.write("</td><td>");
                        out.write(StringEscapeUtils.escapeHtml(list.get(i).getBindingKey()));
                        out.write("</td><td>");


                        out.write("<a href=\"editSubscription.jsp?id=" + URLEncoder.encode(list.get(i).getSubscriptionKey(), "UTF8") + "\"><i class=\"icon-edit icon-2x\"></i></a> ");
                        out.write("<a href=\"javascript:deleteSub('" + StringEscapeUtils.escapeJavaScript(list.get(i).getSubscriptionKey()) + "');\"><i class=\"icon-trash icon-2x\"></i></a> ");
                        out.write("<a href=\"javascript:ViewAsXML('" + StringEscapeUtils.escapeJavaScript(list.get(i).getSubscriptionKey()) + "');\"><i class=\"icon-zoom-in icon-2x\"></i></a> ");

                        out.write(list.get(i).getBindingKey());
                        if (list.get(i).getMaxEntities() != null) {
                            out.write(list.get(i).getMaxEntities().toString());
                        }
                        if (list.get(i).getNotificationInterval() != null) {
                            out.write(list.get(i).getNotificationInterval().toString());
                        }
//out.write(list.get(i).getSubscriptionFilter());
                        out.write("</td></tr>");
                        //  out.write("<tr><td colspan=\"3\"><div id=\"" + StringEscapeUtils.escapeHtml(list.get(i).getSubscriptionKey()) + "\"></div></td></tr>");
                    }
                %>
            </table>
            <%
                    } else
                        out.write(ResourceLoader.GetResource(session, "pages.viewsubscriptions.nosubs"));

                }
            %>

            <script type="text/javascript">
                function deleteSub(key)
                {
                    var postbackdata = new Array();
                    var url='ajax/subscription.jsp';
                        
                              
                    postbackdata.push({
                        name:"nonce", 
                        value: $("#nonce").val()
                    });
    
                        
                    postbackdata.push({
                        name:"DELETE", 
                        value: key
                    });
            
                    var request=   $.ajax({
                        url: url,
                        type:"POST",
                        //  dataType: "html", 
                        cache: false, 
                        //  processData: false,f
                        data: postbackdata
                    });
                
                
                    request.done(function(msg) {
                        window.console && console.log('postback done '  + url);                
        
                        $("#resultBar").html('<a class="close" data-dismiss="alert" href="javascript:hideAlert();">&times;'  + '</a>' + msg);
                        $("#resultBar").show();
                        $("#" + escapeJquerySelector(key)).remove();
        
                    });

                    request.fail(function(jqXHR, textStatus) {
                        window.console && console.log('postback failed ' + url);                                
                        $("#resultBar").html('<a class="close" data-dismiss="alert" href="javascript:hideAlert();">&times;'  + '</a>' +jqXHR.responseText + textStatus);
                        $("#resultBar").show();
        
                    });
                }
            </script>
        </div>
    </div>



    <div class="modal hide fade container" id="viewAsXml">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h3><%=ResourceLoader.GetResource(session, "actions.asxml")%></h3>
        </div>
        <div class="modal-body" id="viewAsXmlContent">


        </div>
        <div class="modal-footer">
            <a href="javascript:closeXmlPop('viewAsXml');" class="btn"><%=ResourceLoader.GetResource(session, "modal.close")%></a>
        </div>
    </div>
    <script type="text/javascript">
         
        function ViewAsXML(id)
        {
            $.get("ajax/toXML.jsp?id=" + id + "&type=subscription", function(data){
                window.console && console.log('asXml success');                
                  
                $("#viewAsXmlContent").html(safe_tags_replace(data));
                $( "#viewAsXml" ).modal('show');
            });
                       
        }
                   
        function closeXmlPop(modaldiv)
        {
            $('#' + modaldiv).modal('hide');
        }
    </script>
    <%@include file="header-bottom.jsp" %>