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
package org.apache.camel.component.ehcache;

import java.util.Map;
import java.util.Set;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.common.DispatchingProducer;
import org.apache.camel.common.ExchangeProcessor;
import org.ehcache.Cache;

public class EhcacheProducer extends DispatchingProducer {
    private final EhcacheConfiguration configuration;
    private final EhcacheManager manager;
    private final Cache<Object, Object> cache;

    public EhcacheProducer(EhcacheEndpoint endpoint, EhcacheConfiguration configuration) throws Exception {
        super(endpoint, EhcacheConstants.ACTION, configuration.getAction());

        this.configuration = configuration;
        this.manager = endpoint.getManager();
        this.cache = manager.getCache();
    }


    @ExchangeProcessor(EhcacheConstants.ACTION_CLEAR)
    protected void onClear(Exchange exchange) throws Exception {
        cache.clear();

        setResult(exchange, true, null, null);
    }

    @ExchangeProcessor(EhcacheConstants.ACTION_PUT)
    protected void onPut(Exchange exchange) throws Exception {
        cache.put(getKey(exchange), getValue(exchange, Object.class));

        setResult(exchange, true, null, null);
    }

    @ExchangeProcessor(EhcacheConstants.ACTION_PUT_ALL)
    protected void onPutAll(Exchange exchange) throws Exception {
        cache.putAll(getValue(exchange, Map.class));

        setResult(exchange, true, null, null);
    }

    @ExchangeProcessor(EhcacheConstants.ACTION_PUT_IF_ABSENT)
    protected void onPutIfAbsent(Exchange exchange) throws Exception {
        Object oldValue = cache.putIfAbsent(getKey(exchange), getValue(exchange, Object.class));

        setResult(exchange, true, null, oldValue);
    }

    @ExchangeProcessor(EhcacheConstants.ACTION_GET)
    protected void onGet(Exchange exchange) throws Exception {
        Object result = cache.get(getKey(exchange));

        setResult(exchange, true, result, null);
    }

    @ExchangeProcessor(EhcacheConstants.ACTION_GET_ALL)
    protected void onGetAll(Exchange exchange) throws Exception {
        Object result = cache.getAll(getHeader(exchange, EhcacheConstants.KEYS, Set.class));

        setResult(exchange, true, result, null);
    }

    @ExchangeProcessor(EhcacheConstants.ACTION_REMOVE)
    protected void onRemove(Exchange exchange) throws Exception {

        boolean success = true;
        Object valueToReplace = exchange.getIn().getHeader(EhcacheConstants.OLD_VALUE);
        if (valueToReplace == null) {
            cache.remove(getKey(exchange));
        } else {
            success = cache.remove(getKey(exchange), valueToReplace);
        }

        setResult(exchange, success, null, null);
    }

    @ExchangeProcessor(EhcacheConstants.ACTION_REMOVE_ALL)
    protected void onRemoveAll(Exchange exchange) throws Exception {
        cache.removeAll(getHeader(exchange, EhcacheConstants.KEYS, Set.class));

        setResult(exchange, true, null, null);
    }

    @ExchangeProcessor(EhcacheConstants.ACTION_REPLACE)
    protected void onReplace(Exchange exchange) throws Exception {
        boolean success = true;
        Object oldValue = null;
        Object value = getValue(exchange, Object.class);
        Object valueToReplace = exchange.getIn().getHeader(EhcacheConstants.OLD_VALUE);
        if (valueToReplace == null) {
            oldValue = cache.replace(getKey(exchange), value);
        } else {
            success = cache.replace(getKey(exchange), valueToReplace, value);
        }

        setResult(exchange, success, null, oldValue);
    }

    // ****************************
    // Helpers
    // ****************************

    private String getKey(Exchange exchange) throws Exception {
        return getMandatoryHeader(
            exchange,
            EhcacheConstants.KEY,
            configuration.getKey(),
            String.class);
    }

    private <T> T getValue(Exchange exchange, Class<T> type)  throws Exception {
        return getMandatoryHeader(
            exchange,
            EhcacheConstants.VALUE,
            exchange.getIn().getBody(type),
            type);
    }

    private void setResult(Exchange exchange, boolean success, Object result, Object oldValue) {
        Message msg = getResultMessage(exchange);
        msg.setHeader(EhcacheConstants.ACTION_SUCCEEDED, success);
        msg.setHeader(EhcacheConstants.ACTION_HAS_RESULT, oldValue != null || result != null);

        if (oldValue != null) {
            msg.setHeader(EhcacheConstants.OLD_VALUE, oldValue);
        }
        if (result != null) {
            msg.setBody(result);
        }
    }
}
