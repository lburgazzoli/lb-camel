/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.component.servicenow;

import org.apache.camel.spi.UriParam;
import org.apache.camel.spi.UriParams;
import org.apache.camel.util.ObjectHelper;


@UriParams
public class ServiceNowConfiguration {
    @UriParam
    private String userName;
    @UriParam
    private String password;

    @UriParam
    private String table;

    @UriParam(defaultValue = "false")
    private Boolean excludeReferenceLink = false;

    @UriParam(defaultValue = "false")
    private Boolean suppressAutoSysField = false;

    @UriParam(defaultValue = "false")
    private String displayValue = "false";

    @UriParam(defaultValue = "false")
    private Boolean inputDisplayValue = false;

    public String getUserName() {
        return userName;
    }

    /**
     * ServiceNow user account name, MUST be provided
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    /**
     * ServiceNow account password, MUST be provided
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean hasBasicAuthentication() {
        return ObjectHelper.isNotEmpty(userName) && ObjectHelper.isNotEmpty(password);
    }

    public String getTable() {
        return table;
    }

    /**
     * The default table, can be overridden by header CamelServiceNowTable
     */
    public void setTable(String table) {
        this.table = table;
    }

    public Boolean getExcludeReferenceLink() {
        return excludeReferenceLink;
    }

    public void setExcludeReferenceLink(Boolean excludeReferenceLink) {
        this.excludeReferenceLink = excludeReferenceLink;
    }

    public Boolean getSuppressAutoSysField() {
        return suppressAutoSysField;
    }

    public void setSuppressAutoSysField(Boolean suppressAutoSysField) {
        this.suppressAutoSysField = suppressAutoSysField;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public void setDisplayValue(String displayValue) {
        this.displayValue = displayValue;
    }

    public Boolean getInputDisplayValue() {
        return inputDisplayValue;
    }

    public void setInputDisplayValue(Boolean inputDisplayValue) {
        this.inputDisplayValue = inputDisplayValue;
    }
}
