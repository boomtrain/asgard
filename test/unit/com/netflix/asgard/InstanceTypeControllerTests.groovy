/*
 * Copyright 2012 Netflix, Inc.
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
package com.netflix.asgard

import com.netflix.asgard.mock.Mocks
import com.netflix.asgard.model.InstanceTypeData
import grails.test.mixin.TestFor
import org.junit.Before

@TestFor(InstanceTypeController)
class InstanceTypeControllerTests {

    @Before
    void setUp() {
        TestUtils.setUpMockRequest()
        controller.instanceTypeService = Mocks.instanceTypeService()
    }

    void testList() {
        def attrs = controller.list()
        List<InstanceTypeData> types = attrs.instanceTypes.sort { it.name }
        assert 31 <= types.size()
        assert 'c3.2xlarge' == types[0].name
        InstanceTypeData m4large = types.find { it.name == 'm4.large' }
        assert 'Large' == m4large.hardwareProfile.size
        assert '3' == types.find { it.name == 'm3.medium' }.hardwareProfile.mem
        assert '64' == types.find { it.name == 'm4.4xlarge' }.hardwareProfile.mem
        InstanceTypeData c4large = types.find { it.name == 'c4.large' }
        assert '2' == c4large.hardwareProfile.vCpu
        assert '64-bit' == types.find { it.name == 'm4.large' }.hardwareProfile.arch
        assert 'High' == types.find { it.name == 'm4.xlarge' }.hardwareProfile.netPerf
        assert null == m4large.linuxOnDemandPrice
        assert 0.280 == types.find { it.name == 'm3.xlarge' }.linuxOnDemandPrice

        assert 4 <= types.findAll{ it.name =~ /i2./ }.size
    }
}
