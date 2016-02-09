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
package org.apache.camel.component.etcd;

public interface EtcdConstants {
    String ETCD_ACTION_KEY   = "CamelEtcdActionKey";
    String ETCD_ACTION_PATH  = "CamelEtcdActionPath";
    String ETCD_PREVIOUSNODE = "CamelEtcdPreviousNode";
    String ETCD_TIMEOUT      = "CamelEtcdTimeout";


    String ETCD_PATH_STATS = "/stats";
    String ETCD_PATH_WATCH = "/watch";

    String ETCD_LEADER_STATS_PATH = "/stats/leader";
    String ETCD_SELF_STATS_PATH   = "/stats/self";
    String ETCD_STORE_STATS_PATH  = "/stats/store";
}
