<%-- 
    Document   : configure jUDDI
    Created on : Feb 23, 2013, 2:05:35 PM
    Author     : Alex O'Ree
--%>


<%@page import="org.apache.commons.configuration.Configuration"%>
<%@page import="org.apache.juddi.v3.client.config.ClientConfig"%>
<%@page import="java.util.Iterator"%>
<%@page import="org.apache.juddi.config.AppConfig"%>
<%@page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header-top.jsp"%>

<div class="container">

    <!-- Main hero unit for a primary marketing message or call to action -->
    <div class="well">
        <h1>Configure jUDDI</h1>
        Just click to edit each field, then click save when you are done. (Not all fields can be modified)
    </div>

    <!-- Example row of columns -->
    <div class="row">


        <div class="span12">
            <%
                URL file = AppConfig.getInstance().getConfigFileURL();
                if (file != null) {
                    out.write("Loaded from: " + StringEscapeUtils.escapeHtml(file.toString()) + "<br>");
                } else {
                    out.write("Loaded from: (location unknown)<br>");
                }
            %>
            <h2>Server Config</h2>
            <table class="table table-hover">
                <tr><th>Field</th><th>Value</th></tr>
                        <%

                            Iterator it = AppConfig.getConfiguration().getKeys();
                            while (it.hasNext()) {
                                String key = (String) it.next();
                                if (key != null && !key.equalsIgnoreCase("nonce")
                                        && key.startsWith("juddi.")) {

                                    out.write("<tr id=\"" + StringEscapeUtils.escapeHtml(key) + "ROW\"><td>");
                                    out.write("<a href=\"javascript:removeKey('" + key + "');\"><i class=\"icon-trash icon-large\"></i></a>");
                                    out.write(StringEscapeUtils.escapeHtml(key));
                                    out.write("</td><td><div ");
                                    out.write(" id=\"" + StringEscapeUtils.escapeHtml(key) + "\" class=\"edit\">");
                                    try {
                                        Object j = AppConfig.getConfiguration().getProperty(key);

                                        if (j != null) {
                                            String str = j.toString();
                                            if (str != null) {
                                                out.write(StringEscapeUtils.escapeHtml(str));
                                            }
                                        }
                                    } catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                    out.write("</div></td></tr>");
                                }
                            }
                        %>
            </table>

            <h2>Admin Console Config (this web site)</h2>
            <%
                UddiAdminHub ahub = UddiAdminHub.getInstance(application, session);
                out.write("Loaded from: " + StringEscapeUtils.escapeHtml(ahub.GetJuddiClientConfig().getConfigurationFile()) + "<br>");
            %>
            <table class="table table-hover" id="configtable">
                <tr><th>Field</th><th>Value</th></tr>
                        <%

                            try {
                                ClientConfig cfg = ahub.GetJuddiClientConfig();
                                Configuration cfg2 = cfg.getConfiguration();
                                Iterator<String> it2 = cfg.getConfiguration().getKeys();

                                String[] nodes = cfg2.getStringArray("client.nodes.node.name");

                                while (it2.hasNext()) {

                                    String key = it2.next();

                                    String value = cfg.getConfiguration().getString(key);
                                    if ((key.startsWith("client") || (key.startsWith("config.props"))) && !key.startsWith("client.nodes.node")) {
                                        out.write("<tr id=\"" + StringEscapeUtils.escapeHtml(key) + "ROW\"><td>");
                                        out.write("<a href=\"javascript:removeKey('" + StringEscapeUtils.escapeJavaScript(key) + "');\"><i class=\"icon-trash icon-large\"></i></a>");
                                        out.write(StringEscapeUtils.escapeHtml(key));
                                        out.write("</td><td><div ");
                                        if (key.startsWith("client") && !key.startsWith("client.nodes")) {
                                            out.write("class=\"edit\" id=\"" + StringEscapeUtils.escapeHtml(key) + "\"");
                                        }
                                        out.write(">");
                                        out.write(StringEscapeUtils.escapeHtml(value));
                                        out.write("</div></td></tr>");
                                    }
                                }

                                for (int i = 0; i < nodes.length; i++) {

                                    String key = "client.nodes.node(" + i + ").name";
                                    out.write("<tr id=\"" + StringEscapeUtils.escapeHtml(key) + "ROW\"><td>");
                                    out.write("<a href=\"javascript:removeKey('" + StringEscapeUtils.escapeJavaScript(key) + "');\"><i class=\"icon-trash icon-large\"></i></a>");
                                    out.write(StringEscapeUtils.escapeHtml(key));
                                    out.write("</td><td><div ");
                                    out.write("class=\"edit\" id=\"" + StringEscapeUtils.escapeHtml(key) + "\">");
                                    out.write(StringEscapeUtils.escapeHtml(cfg2.getString(key)));
                                    out.write("</div></td></tr>");

                                    key = "client.nodes.node(" + i + ").description";
                                    out.write("<tr id=\"" + StringEscapeUtils.escapeHtml(key) + "ROW\"><td>");
                                    out.write("<a href=\"javascript:removeKey('" + StringEscapeUtils.escapeJavaScript(key) + "');\"><i class=\"icon-trash icon-large\"></i></a>");
                                    out.write(StringEscapeUtils.escapeHtml(key));
                                    out.write("</td><td><div ");
                                    out.write("class=\"edit\" id=\"" + StringEscapeUtils.escapeHtml(key) + "\">");
                                    out.write(StringEscapeUtils.escapeHtml(cfg2.getString(key)));
                                    out.write("</div></td></tr>");

                                    key = "client.nodes.node(" + i + ").proxyTransport";
                                    out.write("<tr id=\"" + StringEscapeUtils.escapeHtml(key) + "ROW\"><td>");
                                    out.write("<a href=\"javascript:removeKey('" + StringEscapeUtils.escapeJavaScript(key) + "');\"><i class=\"icon-trash icon-large\"></i></a>");
                                    out.write(StringEscapeUtils.escapeHtml(key));
                                    out.write("</td><td><div ");
                                    out.write("class=\"edit\" id=\"" + StringEscapeUtils.escapeHtml(key) + "\">");
                                    out.write(StringEscapeUtils.escapeHtml(cfg2.getString(key)));
                                    out.write("</div></td></tr>");

                                    key = "client.nodes.node(" + i + ").custodyTransferUrl";
                                    out.write("<tr id=\"" + StringEscapeUtils.escapeHtml(key) + "ROW\"><td>");
                                    out.write("<a href=\"javascript:removeKey('" + StringEscapeUtils.escapeJavaScript(key) + "');\"><i class=\"icon-trash icon-large\"></i></a>");
                                    out.write(StringEscapeUtils.escapeHtml(key));
                                    out.write("</td><td><div ");
                                    out.write("class=\"edit\" id=\"" + StringEscapeUtils.escapeHtml(key) + "\">");
                                    out.write(StringEscapeUtils.escapeHtml(cfg2.getString(key)));
                                    out.write("</div></td></tr>");

                                    key = "client.nodes.node(" + i + ").inquiryUrl";
                                    out.write("<tr id=\"" + StringEscapeUtils.escapeHtml(key) + "ROW\"><td>");
                                    out.write("<a href=\"javascript:removeKey('" + StringEscapeUtils.escapeJavaScript(key) + "');\"><i class=\"icon-trash icon-large\"></i></a>");
                                    out.write(StringEscapeUtils.escapeHtml(key));
                                    out.write("</td><td><div ");
                                    out.write("class=\"edit\" id=\"" + StringEscapeUtils.escapeHtml(key) + "\">");
                                    out.write(StringEscapeUtils.escapeHtml(cfg2.getString(key)));
                                    out.write("</div></td></tr>");

                                    key = "client.nodes.node(" + i + ").publishUrl";
                                    out.write("<tr id=\"" + StringEscapeUtils.escapeHtml(key) + "ROW\"><td>");
                                    out.write("<a href=\"javascript:removeKey('" + StringEscapeUtils.escapeJavaScript(key) + "');\"><i class=\"icon-trash icon-large\"></i></a>");
                                    out.write(StringEscapeUtils.escapeHtml(key));
                                    out.write("</td><td><div ");
                                    out.write("class=\"edit\" id=\"" + StringEscapeUtils.escapeHtml(key) + "\">");
                                    out.write(StringEscapeUtils.escapeHtml(cfg2.getString(key)));
                                    out.write("</div></td></tr>");

                                    key = "client.nodes.node(" + i + ").securityUrl";
                                    out.write("<tr id=\"" + StringEscapeUtils.escapeHtml(key) + "ROW\"><td>");
                                    out.write("<a href=\"javascript:removeKey('" + StringEscapeUtils.escapeJavaScript(key) + "');\"><i class=\"icon-trash icon-large\"></i></a>");
                                    out.write(StringEscapeUtils.escapeHtml(key));
                                    out.write("</td><td><div ");
                                    out.write("class=\"edit\" id=\"" + StringEscapeUtils.escapeHtml(key) + "\">");
                                    out.write(StringEscapeUtils.escapeHtml(cfg2.getString(key)));
                                    out.write("</div></td></tr>");

                                    key = "client.nodes.node(" + i + ").subscriptionUrl";
                                    out.write("<tr id=\"" + StringEscapeUtils.escapeHtml(key) + "ROW\"><td>");
                                    out.write("<a href=\"javascript:removeKey('" + StringEscapeUtils.escapeJavaScript(key) + "');\"><i class=\"icon-trash icon-large\"></i></a>");
                                    out.write(StringEscapeUtils.escapeHtml(key));
                                    out.write("</td><td><div ");
                                    out.write("class=\"edit\" id=\"" + StringEscapeUtils.escapeHtml(key) + "\">");
                                    out.write(StringEscapeUtils.escapeHtml(cfg2.getString(key)));
                                    out.write("</div></td></tr>");

                                }


                            } catch (Exception ex) {
                                ahub.log.error(ex);
                            }
                        %>
            </table>


            <script type="text/javascript">
                function save()
                {
                    //alert("hi");
                    var url = 'ajax/saveconfig.jsp';
                    var postbackdata = new Array();
                    $("div.edit").each(function()
                    {
                        var id = $(this).attr("id");
                        var value = $(this).text();
                        postbackdata.push({
                            name: id,
                            value: value
                        });
                    });
                    postbackdata.push({
                        name: "nonce",
                        value: $("#nonce").val()
                    });

                    var request = $.ajax({
                        url: url,
                        type: "POST",
                        //  data" + i18n_type + ": "html", 
                        cache: false,
                        //  processData: false,f
                        data: postbackdata
                    });


                    request.done(function(msg) {
                        window.console && console.log('postback done ' + url);

                        $("#saveConfigresultBar").html(msg);


                    });

                    request.fail(function(jqXHR, textStatus) {
                        window.console && console.log('postback failed ' + url);
                        $("#saveConfigresultBar").html(textStatus + ' ' + jqXHR.responseText);

                    });
                }
                Reedit();

            </script>
            <a class="btn btn-primary" href="javascript:newItem();"><i class="icon-large icon-plus-sign"></i> Add</a>
            <a class="btn btn-primary" href="javascript:save();">Save</a><br><br>


            <div id="saveConfigresultBar" class="well-small"></div>

            <script type="text/javascript">
                function showDebug() {
                    $("#debugtable").show();
                }
            </script>

            <a class="btn " href="javascript:showDebug();"><i class="icon-large icon-arrow-down"></i> Show Debug Info</a>
            <div id="debugtable" class="hide">
                <table class="table table-hover">
                    <tr><th>Field</th><th>Value</th></tr>
                            <%

                                try {
                                    ClientConfig cfg = ahub.GetJuddiClientConfig();
                                    Iterator<String> it2 = cfg.getConfiguration().getKeys();

                                    while (it2.hasNext()) {

                                        String key = it2.next();

                                        if (!key.startsWith("config.props.") && !key.startsWith("client")) {
                                            String value = cfg.getConfiguration().getString(key);
                                            out.write("<tr><td>");
                                            out.write(StringEscapeUtils.escapeHtml(key));
                                            out.write("</td><td><div ");
                                            out.write(">");
                                            out.write(StringEscapeUtils.escapeHtml(value));
                                            out.write("</div></td></tr>");
                                        }
                                    }
                                } catch (Exception ex) {
                                    ahub.log.error(ex);
                                }

                            %>
                </table>
            </div>
        </div>

    </div>

    <script type="text/javascript">

        function newItem() {
            $("#newItemModal").modal('show');
        }
        function appendKey()
        {
            $("#newItemModal").modal('hide');
            var key = safe_tags_replace($("#newItemKey").val());
            var value = safe_tags_replace($("#newItemValue").val());
            $("<tr id=\"" + key + "ROW\"><td>" +
                    "<a href=\"javascript:removeKey('" + key + "');\"><i class=\"icon-trash icon-large\"></i></a>" +
                    key + "</a></td><td><div id=\"" +
                    key + "\" class=\"edit\">" +
                    value + "</div></td></tr>").appendTo("#configtable");
            Reedit();

        }
        function removeKey(key)
        {
            $('#' + escapeJquerySelector(key) + "ROW").remove();
        }
    </script>

    <div class="modal hide fade container" id="newItemModal">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h3>Add a new setting</h3>
        </div>
        <div class="modal-body">
            Note: the key must start with either client., config.props, or juddi.<br>
            Key: <input type="text" id="newItemKey" placeholder="client."><br>
            Value: <input type="text" id="newItemValue" placeholder="value"><br>
        </div>
        <div class="modal-footer">
            <a href="javascript:appendKey();" class="btn btn-primary">Add</a>
            <a href="javascript:$('#newItemModal').modal('hide');" class="btn">Close</a>
        </div>
    </div>



    <%@include file="header-bottom.jsp"%>