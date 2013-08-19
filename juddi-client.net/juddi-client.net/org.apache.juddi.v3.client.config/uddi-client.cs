﻿//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by a tool.
//     Runtime Version:4.0.30319.18052
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

// 
// This source code was auto-generated by xsd, Version=4.0.30319.17929.
// 
namespace org.apache.juddi.v3.client.config {
    using System.Xml.Serialization;
    
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("xsd", "4.0.30319.17929")]
    [System.SerializableAttribute()]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    [System.Xml.Serialization.XmlTypeAttribute(AnonymousType=true, Namespace="urn:juddi-apache-org:v3_client")]
    [System.Xml.Serialization.XmlRootAttribute(Namespace="urn:juddi-apache-org:v3_client", IsNullable=false)]
    public partial class uddi {
        
        private short reloadDelayField;
        
        private uddiClient clientField;
        
        /// <remarks/>
        public short reloadDelay {
            get {
                return this.reloadDelayField;
            }
            set {
                this.reloadDelayField = value;
            }
        }
        
        /// <remarks/>
        public uddiClient client {
            get {
                return this.clientField;
            }
            set {
                this.clientField = value;
            }
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("xsd", "4.0.30319.17929")]
    [System.SerializableAttribute()]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    [System.Xml.Serialization.XmlTypeAttribute(AnonymousType=true, Namespace="urn:juddi-apache-org:v3_client")]
    public partial class uddiClient {
        
        private uddiClientNode[] nodesField;
        
        private uddiClientClerks clerksField;
        
        private uddiClientSignature signatureField;
        
        private string nameField;
        
        /// <remarks/>
        [System.Xml.Serialization.XmlArrayItemAttribute("node", IsNullable=false)]
        public uddiClientNode[] nodes {
            get {
                return this.nodesField;
            }
            set {
                this.nodesField = value;
            }
        }
        
        /// <remarks/>
        public uddiClientClerks clerks {
            get {
                return this.clerksField;
            }
            set {
                this.clerksField = value;
            }
        }
        
        /// <remarks/>
        public uddiClientSignature signature {
            get {
                return this.signatureField;
            }
            set {
                this.signatureField = value;
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlAttributeAttribute()]
        public string name {
            get {
                return this.nameField;
            }
            set {
                this.nameField = value;
            }
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("xsd", "4.0.30319.17929")]
    [System.SerializableAttribute()]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    [System.Xml.Serialization.XmlTypeAttribute(AnonymousType=true, Namespace="urn:juddi-apache-org:v3_client")]
    public partial class uddiClientNode {
        
        private string nameField;
        
        private string descriptionField;
        
        private uddiClientNodeProperty[] propertiesField;
        
        private string proxyTransportField;
        
        private string custodyTransferUrlField;
        
        private string inquiryUrlField;
        
        private string publishUrlField;
        
        private string securityUrlField;
        
        private string subscriptionUrlField;
        
        private string subscriptionListenerUrlField;
        
        private string juddiApiUrlField;
        
        private string factoryInitialField;
        
        private string factoryURLPkgsField;
        
        private string factoryNamingProviderField;
        
        private bool isHomeJUDDIField;
        
        private bool isHomeJUDDIFieldSpecified;
        
        /// <remarks/>
        public string name {
            get {
                return this.nameField;
            }
            set {
                this.nameField = value;
            }
        }
        
        /// <remarks/>
        public string description {
            get {
                return this.descriptionField;
            }
            set {
                this.descriptionField = value;
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlArrayItemAttribute("property", IsNullable=false)]
        public uddiClientNodeProperty[] properties {
            get {
                return this.propertiesField;
            }
            set {
                this.propertiesField = value;
            }
        }
        
        /// <remarks/>
        public string proxyTransport {
            get {
                return this.proxyTransportField;
            }
            set {
                this.proxyTransportField = value;
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(DataType="anyURI")]
        public string custodyTransferUrl {
            get {
                return this.custodyTransferUrlField;
            }
            set {
                this.custodyTransferUrlField = value;
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(DataType="anyURI")]
        public string inquiryUrl {
            get {
                return this.inquiryUrlField;
            }
            set {
                this.inquiryUrlField = value;
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(DataType="anyURI")]
        public string publishUrl {
            get {
                return this.publishUrlField;
            }
            set {
                this.publishUrlField = value;
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(DataType="anyURI")]
        public string securityUrl {
            get {
                return this.securityUrlField;
            }
            set {
                this.securityUrlField = value;
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(DataType="anyURI")]
        public string subscriptionUrl {
            get {
                return this.subscriptionUrlField;
            }
            set {
                this.subscriptionUrlField = value;
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(DataType="anyURI")]
        public string subscriptionListenerUrl {
            get {
                return this.subscriptionListenerUrlField;
            }
            set {
                this.subscriptionListenerUrlField = value;
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(DataType="anyURI", IsNullable=true)]
        public string juddiApiUrl {
            get {
                return this.juddiApiUrlField;
            }
            set {
                this.juddiApiUrlField = value;
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(IsNullable=true)]
        public string factoryInitial {
            get {
                return this.factoryInitialField;
            }
            set {
                this.factoryInitialField = value;
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(IsNullable=true)]
        public string factoryURLPkgs {
            get {
                return this.factoryURLPkgsField;
            }
            set {
                this.factoryURLPkgsField = value;
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute(IsNullable=true)]
        public string factoryNamingProvider {
            get {
                return this.factoryNamingProviderField;
            }
            set {
                this.factoryNamingProviderField = value;
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlAttributeAttribute()]
        public bool isHomeJUDDI {
            get {
                return this.isHomeJUDDIField;
            }
            set {
                this.isHomeJUDDIField = value;
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlIgnoreAttribute()]
        public bool isHomeJUDDISpecified {
            get {
                return this.isHomeJUDDIFieldSpecified;
            }
            set {
                this.isHomeJUDDIFieldSpecified = value;
            }
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("xsd", "4.0.30319.17929")]
    [System.SerializableAttribute()]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    [System.Xml.Serialization.XmlTypeAttribute(AnonymousType=true, Namespace="urn:juddi-apache-org:v3_client")]
    public partial class uddiClientNodeProperty {
        
        private string nameField;
        
        private string valueField;
        
        private string valueField1;
        
        /// <remarks/>
        [System.Xml.Serialization.XmlAttributeAttribute()]
        public string name {
            get {
                return this.nameField;
            }
            set {
                this.nameField = value;
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlAttributeAttribute()]
        public string value {
            get {
                return this.valueField;
            }
            set {
                this.valueField = value;
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlTextAttribute()]
        public string Value {
            get {
                return this.valueField1;
            }
            set {
                this.valueField1 = value;
            }
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("xsd", "4.0.30319.17929")]
    [System.SerializableAttribute()]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    [System.Xml.Serialization.XmlTypeAttribute(Namespace="urn:juddi-apache-org:v3_client")]
    public partial class wsdl {
        
        private string businessKeyField;
        
        private string businessNameField;
        
        private string keyDomainField;
        
        private string valueField;
        
        /// <remarks/>
        [System.Xml.Serialization.XmlAttributeAttribute()]
        public string businessKey {
            get {
                return this.businessKeyField;
            }
            set {
                this.businessKeyField = value;
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlAttributeAttribute()]
        public string businessName {
            get {
                return this.businessNameField;
            }
            set {
                this.businessNameField = value;
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlAttributeAttribute()]
        public string keyDomain {
            get {
                return this.keyDomainField;
            }
            set {
                this.keyDomainField = value;
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlTextAttribute()]
        public string Value {
            get {
                return this.valueField;
            }
            set {
                this.valueField = value;
            }
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("xsd", "4.0.30319.17929")]
    [System.SerializableAttribute()]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    [System.Xml.Serialization.XmlTypeAttribute(AnonymousType=true, Namespace="urn:juddi-apache-org:v3_client")]
    public partial class uddiClientClerks {
        
        private uddiClientClerksClerk[] clerkField;
        
        private uddiClientClerksXregister xregisterField;
        
        private bool registerOnStartupField;
        
        private bool registerOnStartupFieldSpecified;
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute("clerk")]
        public uddiClientClerksClerk[] clerk {
            get {
                return this.clerkField;
            }
            set {
                this.clerkField = value;
            }
        }
        
        /// <remarks/>
        public uddiClientClerksXregister xregister {
            get {
                return this.xregisterField;
            }
            set {
                this.xregisterField = value;
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlAttributeAttribute()]
        public bool registerOnStartup {
            get {
                return this.registerOnStartupField;
            }
            set {
                this.registerOnStartupField = value;
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlIgnoreAttribute()]
        public bool registerOnStartupSpecified {
            get {
                return this.registerOnStartupFieldSpecified;
            }
            set {
                this.registerOnStartupFieldSpecified = value;
            }
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("xsd", "4.0.30319.17929")]
    [System.SerializableAttribute()]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    [System.Xml.Serialization.XmlTypeAttribute(AnonymousType=true, Namespace="urn:juddi-apache-org:v3_client")]
    public partial class uddiClientClerksClerk {
        
        private wsdl[] wsdlField;
        
        private string[] classField;
        
        private string nameField;
        
        private string nodeField;
        
        private string publisherField;
        
        private string passwordField;
        
        private bool isPasswordEncryptedField;
        
        private string cryptoProviderField;
        
        private string businessKeyField;
        
        private string businessNameField;
        
        private string keyDomainField;
        
        public uddiClientClerksClerk() {
            this.isPasswordEncryptedField = false;
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute("wsdl", IsNullable=true)]
        public wsdl[] wsdl {
            get {
                return this.wsdlField;
            }
            set {
                this.wsdlField = value;
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute("class", IsNullable=true)]
        public string[] @class {
            get {
                return this.classField;
            }
            set {
                this.classField = value;
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlAttributeAttribute()]
        public string name {
            get {
                return this.nameField;
            }
            set {
                this.nameField = value;
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlAttributeAttribute()]
        public string node {
            get {
                return this.nodeField;
            }
            set {
                this.nodeField = value;
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlAttributeAttribute()]
        public string publisher {
            get {
                return this.publisherField;
            }
            set {
                this.publisherField = value;
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlAttributeAttribute()]
        public string password {
            get {
                return this.passwordField;
            }
            set {
                this.passwordField = value;
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlAttributeAttribute()]
        [System.ComponentModel.DefaultValueAttribute(false)]
        public bool isPasswordEncrypted {
            get {
                return this.isPasswordEncryptedField;
            }
            set {
                this.isPasswordEncryptedField = value;
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlAttributeAttribute()]
        public string cryptoProvider {
            get {
                return this.cryptoProviderField;
            }
            set {
                this.cryptoProviderField = value;
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlAttributeAttribute()]
        public string businessKey {
            get {
                return this.businessKeyField;
            }
            set {
                this.businessKeyField = value;
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlAttributeAttribute()]
        public string businessName {
            get {
                return this.businessNameField;
            }
            set {
                this.businessNameField = value;
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlAttributeAttribute()]
        public string keyDomain {
            get {
                return this.keyDomainField;
            }
            set {
                this.keyDomainField = value;
            }
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("xsd", "4.0.30319.17929")]
    [System.SerializableAttribute()]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    [System.Xml.Serialization.XmlTypeAttribute(AnonymousType=true, Namespace="urn:juddi-apache-org:v3_client")]
    public partial class uddiClientClerksXregister {
        
        private uddiClientClerksXregisterServicebinding[] servicebindingField;
        
        private uddiClientClerksXregisterBusiness[] businessField;
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute("servicebinding", IsNullable=true)]
        public uddiClientClerksXregisterServicebinding[] servicebinding {
            get {
                return this.servicebindingField;
            }
            set {
                this.servicebindingField = value;
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlElementAttribute("business", IsNullable=true)]
        public uddiClientClerksXregisterBusiness[] business {
            get {
                return this.businessField;
            }
            set {
                this.businessField = value;
            }
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("xsd", "4.0.30319.17929")]
    [System.SerializableAttribute()]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    [System.Xml.Serialization.XmlTypeAttribute(AnonymousType=true, Namespace="urn:juddi-apache-org:v3_client")]
    public partial class uddiClientClerksXregisterServicebinding {
        
        private string bindingKeyField;
        
        private string fromClerkField;
        
        private string toClerkField;
        
        private string valueField;
        
        /// <remarks/>
        [System.Xml.Serialization.XmlAttributeAttribute()]
        public string bindingKey {
            get {
                return this.bindingKeyField;
            }
            set {
                this.bindingKeyField = value;
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlAttributeAttribute()]
        public string fromClerk {
            get {
                return this.fromClerkField;
            }
            set {
                this.fromClerkField = value;
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlAttributeAttribute()]
        public string toClerk {
            get {
                return this.toClerkField;
            }
            set {
                this.toClerkField = value;
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlTextAttribute()]
        public string Value {
            get {
                return this.valueField;
            }
            set {
                this.valueField = value;
            }
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("xsd", "4.0.30319.17929")]
    [System.SerializableAttribute()]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    [System.Xml.Serialization.XmlTypeAttribute(AnonymousType=true, Namespace="urn:juddi-apache-org:v3_client")]
    public partial class uddiClientClerksXregisterBusiness {
        
        private string bindingKeyField;
        
        private string fromClerkField;
        
        private string toClerkField;
        
        private string valueField;
        
        /// <remarks/>
        [System.Xml.Serialization.XmlAttributeAttribute()]
        public string bindingKey {
            get {
                return this.bindingKeyField;
            }
            set {
                this.bindingKeyField = value;
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlAttributeAttribute()]
        public string fromClerk {
            get {
                return this.fromClerkField;
            }
            set {
                this.fromClerkField = value;
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlAttributeAttribute()]
        public string toClerk {
            get {
                return this.toClerkField;
            }
            set {
                this.toClerkField = value;
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlTextAttribute()]
        public string Value {
            get {
                return this.valueField;
            }
            set {
                this.valueField = value;
            }
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("xsd", "4.0.30319.17929")]
    [System.SerializableAttribute()]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    [System.Xml.Serialization.XmlTypeAttribute(AnonymousType=true, Namespace="urn:juddi-apache-org:v3_client")]
    public partial class uddiClientSignature {
        
        private string signingKeyStorePathField;
        
        private string signingKeyStoreTypeField;
        
        private uddiClientSignatureSigningKeyStoreFilePassword signingKeyStoreFilePasswordField;
        
        private uddiClientSignatureSigningKeyPassword signingKeyPasswordField;
        
        private string signingKeyAliasField;
        
        private string canonicalizationMethodField;
        
        private string signatureMethodField;
        
        private string xML_DIGSIG_NSField;
        
        private string trustStorePathField;
        
        private string trustStoreTypeField;
        
        private uddiClientSignatureTrustStorePassword trustStorePasswordField;
        
        private bool checkTimestampsField;
        
        private bool checkTrustField;
        
        private bool checkRevocationCRLField;
        
        private bool checkRevocationOCSPField;
        
        public uddiClientSignature() {
            this.xML_DIGSIG_NSField = "http://www.w3.org/2000/09/xmldsig#";
            this.checkTimestampsField = true;
            this.checkTrustField = true;
            this.checkRevocationCRLField = false;
            this.checkRevocationOCSPField = false;
        }
        
        /// <remarks/>
        public string signingKeyStorePath {
            get {
                return this.signingKeyStorePathField;
            }
            set {
                this.signingKeyStorePathField = value;
            }
        }
        
        /// <remarks/>
        public string signingKeyStoreType {
            get {
                return this.signingKeyStoreTypeField;
            }
            set {
                this.signingKeyStoreTypeField = value;
            }
        }
        
        /// <remarks/>
        public uddiClientSignatureSigningKeyStoreFilePassword signingKeyStoreFilePassword {
            get {
                return this.signingKeyStoreFilePasswordField;
            }
            set {
                this.signingKeyStoreFilePasswordField = value;
            }
        }
        
        /// <remarks/>
        public uddiClientSignatureSigningKeyPassword signingKeyPassword {
            get {
                return this.signingKeyPasswordField;
            }
            set {
                this.signingKeyPasswordField = value;
            }
        }
        
        /// <remarks/>
        public string signingKeyAlias {
            get {
                return this.signingKeyAliasField;
            }
            set {
                this.signingKeyAliasField = value;
            }
        }
        
        /// <remarks/>
        public string canonicalizationMethod {
            get {
                return this.canonicalizationMethodField;
            }
            set {
                this.canonicalizationMethodField = value;
            }
        }
        
        /// <remarks/>
        public string signatureMethod {
            get {
                return this.signatureMethodField;
            }
            set {
                this.signatureMethodField = value;
            }
        }
        
        /// <remarks/>
        public string XML_DIGSIG_NS {
            get {
                return this.xML_DIGSIG_NSField;
            }
            set {
                this.xML_DIGSIG_NSField = value;
            }
        }
        
        /// <remarks/>
        public string trustStorePath {
            get {
                return this.trustStorePathField;
            }
            set {
                this.trustStorePathField = value;
            }
        }
        
        /// <remarks/>
        public string trustStoreType {
            get {
                return this.trustStoreTypeField;
            }
            set {
                this.trustStoreTypeField = value;
            }
        }
        
        /// <remarks/>
        public uddiClientSignatureTrustStorePassword trustStorePassword {
            get {
                return this.trustStorePasswordField;
            }
            set {
                this.trustStorePasswordField = value;
            }
        }
        
        /// <remarks/>
        public bool checkTimestamps {
            get {
                return this.checkTimestampsField;
            }
            set {
                this.checkTimestampsField = value;
            }
        }
        
        /// <remarks/>
        public bool checkTrust {
            get {
                return this.checkTrustField;
            }
            set {
                this.checkTrustField = value;
            }
        }
        
        /// <remarks/>
        public bool checkRevocationCRL {
            get {
                return this.checkRevocationCRLField;
            }
            set {
                this.checkRevocationCRLField = value;
            }
        }
        
        /// <remarks/>
        public bool checkRevocationOCSP {
            get {
                return this.checkRevocationOCSPField;
            }
            set {
                this.checkRevocationOCSPField = value;
            }
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("xsd", "4.0.30319.17929")]
    [System.SerializableAttribute()]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    [System.Xml.Serialization.XmlTypeAttribute(AnonymousType=true, Namespace="urn:juddi-apache-org:v3_client")]
    public partial class uddiClientSignatureSigningKeyStoreFilePassword {
        
        private bool isPasswordEncryptedField;
        
        private string cryptoProviderField;
        
        private string valueField;
        
        public uddiClientSignatureSigningKeyStoreFilePassword() {
            this.isPasswordEncryptedField = false;
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlAttributeAttribute()]
        [System.ComponentModel.DefaultValueAttribute(false)]
        public bool isPasswordEncrypted {
            get {
                return this.isPasswordEncryptedField;
            }
            set {
                this.isPasswordEncryptedField = value;
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlAttributeAttribute()]
        public string cryptoProvider {
            get {
                return this.cryptoProviderField;
            }
            set {
                this.cryptoProviderField = value;
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlTextAttribute()]
        public string Value {
            get {
                return this.valueField;
            }
            set {
                this.valueField = value;
            }
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("xsd", "4.0.30319.17929")]
    [System.SerializableAttribute()]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    [System.Xml.Serialization.XmlTypeAttribute(AnonymousType=true, Namespace="urn:juddi-apache-org:v3_client")]
    public partial class uddiClientSignatureSigningKeyPassword {
        
        private bool isPasswordEncryptedField;
        
        private string cryptoProviderField;
        
        private string valueField;
        
        public uddiClientSignatureSigningKeyPassword() {
            this.isPasswordEncryptedField = false;
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlAttributeAttribute()]
        [System.ComponentModel.DefaultValueAttribute(false)]
        public bool isPasswordEncrypted {
            get {
                return this.isPasswordEncryptedField;
            }
            set {
                this.isPasswordEncryptedField = value;
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlAttributeAttribute()]
        public string cryptoProvider {
            get {
                return this.cryptoProviderField;
            }
            set {
                this.cryptoProviderField = value;
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlTextAttribute()]
        public string Value {
            get {
                return this.valueField;
            }
            set {
                this.valueField = value;
            }
        }
    }
    
    /// <remarks/>
    [System.CodeDom.Compiler.GeneratedCodeAttribute("xsd", "4.0.30319.17929")]
    [System.SerializableAttribute()]
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.ComponentModel.DesignerCategoryAttribute("code")]
    [System.Xml.Serialization.XmlTypeAttribute(AnonymousType=true, Namespace="urn:juddi-apache-org:v3_client")]
    public partial class uddiClientSignatureTrustStorePassword {
        
        private bool isPasswordEncryptedField;
        
        private string cryptoProviderField;
        
        private string valueField;
        
        public uddiClientSignatureTrustStorePassword() {
            this.isPasswordEncryptedField = false;
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlAttributeAttribute()]
        [System.ComponentModel.DefaultValueAttribute(false)]
        public bool isPasswordEncrypted {
            get {
                return this.isPasswordEncryptedField;
            }
            set {
                this.isPasswordEncryptedField = value;
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlAttributeAttribute()]
        public string cryptoProvider {
            get {
                return this.cryptoProviderField;
            }
            set {
                this.cryptoProviderField = value;
            }
        }
        
        /// <remarks/>
        [System.Xml.Serialization.XmlTextAttribute()]
        public string Value {
            get {
                return this.valueField;
            }
            set {
                this.valueField = value;
            }
        }
    }
}
