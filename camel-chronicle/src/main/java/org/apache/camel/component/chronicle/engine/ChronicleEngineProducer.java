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
package org.apache.camel.component.chronicle.engine;

import net.openhft.chronicle.engine.api.tree.AssetTree;
import org.apache.camel.component.chronicle.AbstractChronicleProducer;


public class ChronicleEngineProducer extends AbstractChronicleProducer<ChronicleEngineConfiguration, ChronicleEngineEnpoint> {
    private AssetTree tree;

    public ChronicleEngineProducer(ChronicleEngineEnpoint endpoint) {
        super(endpoint, ChronicleEngineConstants.ACTION);
    }

    @Override
    protected void doStart() throws Exception {
        super.doStart();

        if (tree != null) {
            throw new IllegalStateException("AssetTree already configured");
        }

        ChronicleEngineConfiguration conf = getConfiguration();
        tree = getChronicleEnpoint().remoteAssetTree();
    }

    @Override
    protected void doStop() throws Exception {
        if (tree != null) {
            tree.close();
            tree = null;
        }

        super.doStop();
    }
}