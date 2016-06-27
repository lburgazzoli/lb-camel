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

package org.apache.camel.component.chronicle;

import net.openhft.chronicle.engine.api.tree.AssetTree;
import net.openhft.chronicle.engine.tree.VanillaAssetTree;
import org.apache.camel.impl.DefaultEndpoint;
import org.apache.camel.util.ObjectHelper;

public abstract class AbstractChronicleEndpoint<C extends ChronicleConfiguration> extends DefaultEndpoint {
    private final C configuration;

    protected AbstractChronicleEndpoint(String uri, ChronicleComponent component, C configuration) {
        super(uri, component);

        this.configuration = configuration;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    protected C getConfiguration() {
        return configuration;
    }

    // ****************************
    // Helpers
    // ****************************

    public AssetTree remoteAssetTree() {
        return new VanillaAssetTree()
            .forRemoteAccess(
                ObjectHelper.notNull(configuration.getAddresses(), "addresses"),
                ObjectHelper.notNull(configuration.getWireType(), "WireType")
            );
    }
}
