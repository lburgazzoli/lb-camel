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

package org.apache.camel.component.consul.enpoint;

import java.util.Map;

import com.orbitz.consul.EventClient;
import com.orbitz.consul.option.EventOptions;
import com.orbitz.consul.option.QueryOptions;
import org.apache.camel.Message;
import org.apache.camel.component.consul.AbstractConsulEndpoint;
import org.apache.camel.component.consul.AbstractConsulProducer;
import org.apache.camel.component.consul.ConsulConfiguration;
import org.apache.camel.component.consul.MessageProcessor;

public class ConsulEventProducer extends AbstractConsulProducer<EventClient> {
    ConsulEventProducer(AbstractConsulEndpoint endpoint, ConsulConfiguration configuration) {
        super(endpoint, configuration, c -> c.eventClient());
    }

    @Override
    protected void bindActionProcessors(Map<String, MessageProcessor> processors) {
        processors.put(ConsulEventActions.FIRE, this::fire);
        processors.put(ConsulEventActions.LIST, this::list);
    }

    // *************************************************************************
    //
    // *************************************************************************

    private void fire(Message message) throws Exception {
        setBodyAndResult(
            message,
            getClient().fireEvent(
                getMandatoryKey(message),
                getOption(message, EventOptions.BLANK, EventOptions.class),
                message.getBody(String.class)
            )
        );
    }
    private void list(Message message) throws Exception {
        setBodyAndResult(
            message,
            getClient().listEvents(
                getKey(message),
                getOption(message, QueryOptions.BLANK, QueryOptions.class)
            )
        );
    }
}
