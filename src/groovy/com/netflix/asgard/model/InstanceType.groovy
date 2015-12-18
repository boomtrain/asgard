/*
 * Copyright 2014 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.netflix.asgard.model

enum InstanceType {

    T1Nano("t1.nano"),
    T1Micro("t1.micro"),
    T2Micro("t2.micro"),
    T2Small("t2.small"),
    T2Medium("t2.medium"),
    T2Large("t2.large"),

    M4Large("m4.large"),
    M4Xlarge("m4.xlarge"),
    M42Xlarge("m4.2xlarge"),
    M44Xlarge("m4.4xlarge"),
    M410Xlarge("m4.10xlarge"),
    M3Medium("m3.medium"),
    M3Large("m3.large"),
    M3Xlarge("m3.xlarge"),
    M32xlarge("m3.2xlarge"),

    C4Large("c4.large"),
    C4Xlarge("c4.xlarge"),
    C42XLarge("c4.2xlarge"),
    C44XLarge("c4.4xlarge"),
    C48XLarge("c4.8xlarge"),
    C3Large("c3.large"),
    C3Xlarge("c3.xlarge"),
    C32xlarge("c3.2xlarge"),
    C34xlarge("c3.4xlarge"),
    C38xlarge("c3.8xlarge"),

    I2Xlarge("i2.xlarge"),
    I22xlarge("i2.2xlarge"),
    I24xlarge("i2.4xlarge"),
    I28xlarge("i2.8xlarge"),

    G22xlarge("g2.2xlarge"),
    R3Large("r3.large"),
    R3Xlarge("r3.xlarge"),
    R32xlarge("r3.2xlarge"),
    R34xlarge("r3.4xlarge"),
    R38xlarge("r3.8xlarge")

    private String value

    InstanceType(String value) {
        this.value = value
    }

    @Override
    String toString() {
        this.value
    }

    /**
     * @param value as String (i.e. t1.micro, etc)
     * @return InstanceType enum
     */
    static InstanceType fromValue(String value) {
        try {
            if (value == null || "" == value) {
                throw new IllegalArgumentException("Value cannot be null or empty!")
            }
            return InstanceType."${value.tokenize('.')*.capitalize().join()}"
        } catch (ex) {
            throw new IllegalArgumentException("Cannot create enum from ${value}")
        }
    }
}
