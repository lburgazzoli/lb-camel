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
package org.apache.camel.component.consul;


import javax.ws.rs.client.ClientBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.spi.Metadata;
import org.apache.camel.spi.UriParam;
import org.apache.camel.spi.UriParams;
import org.apache.camel.util.jsse.SSLContextParameters;

@UriParams
public class ConsulConfiguration {
    @UriParam
    private String url;

    @UriParam
    @Metadata(label = "security")
    private SSLContextParameters sslContextParameters;

    @UriParam
    @Metadata(label = "advanced")
    private ClientBuilder clientBuilder;

    @UriParam
    @Metadata(label = "advanced")
    private ObjectMapper objectMapper;

    @UriParam(label = "producer")
    private String action;

    @UriParam(label = "producer,kv", defaultValue = "false")
    private boolean valueAsString = false;

    @UriParam
    private String key;

    @UriParam(label = "consumer,watch", defaultValue = "10")
    private Integer blockSeconds = 10;

    @UriParam(label = "consumer,watch", defaultValue = "0")
    private long firstIndex = 0;

    @UriParam(label = "consumer,watch", defaultValue = "false")
    private boolean recursive = false;


    public String getUrl() {
        return url;
    }

    /**
     * The Consul agent URL
     */
    public void setUrl(String url) {
        this.url = url;
    }

    public SSLContextParameters getSslContextParameters() {
        return sslContextParameters;
    }

    /**
     * SSL configuration using an org.apache.camel.util.jsse.SSLContextParameters
     * instance.
     */
    public void setSslContextParameters(SSLContextParameters sslContextParameters) {
        this.sslContextParameters = sslContextParameters;
    }

    public ClientBuilder getClientBuilder() {
        return clientBuilder;
    }

    /**
     * The JAX-RS builder
     */
    public void setClientBuilder(ClientBuilder clientBuilder) {
        this.clientBuilder = clientBuilder;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    /**
     * The {@link ObjectMapper} to use by the client
     */
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String getAction() {
        return action;
    }

    /**
     * The default action. Can be overridden by CamelConsulAction
     */
    public void setAction(String action) {
        this.action = action;
    }

    public boolean isValueAsString() {
        return valueAsString;
    }

    /**
     * Default to transform values retrieved from Consul i.e. on KV endpoint to
     * string.
     */
    public void setValueAsString(boolean valueAsString) {
        this.valueAsString = valueAsString;
    }

    public String getKey() {
        return key;
    }

    /**
     * The default action. Can be overridden by CamelConsulKey
     */
    public void setKey(String key) {
        this.key = key;
    }

    public Integer getBlockSeconds() {
        return blockSeconds;
    }

    /**
     * The second to wait for a watch event, default 10 seconds
     */
    public void setBlockSeconds(Integer blockSeconds) {
        this.blockSeconds = blockSeconds;
    }

    public long getFirstIndex() {
        return firstIndex;
    }

    /**
     * The first index for watch for, default 0
     */
    public void setFirstIndex(long firstIndex) {
        this.firstIndex = firstIndex;
    }

    public boolean isRecursive() {
        return recursive;
    }

    /**
     * Recursively watch, default false
     */
    public void setRecursive(boolean recursive) {
        this.recursive = recursive;
    }
}