/*
 * Copyright (c) 2015-2020, www.dibo.ltd (service@dibo.ltd).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * https://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.diboot.scheduler.starter;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * scheduler组件自动初始化
 * @author mazc@dibo.ltd
 * @version v2.0
 * @date 2020/11/28
 */
@Slf4j
@Configuration
@EnableConfigurationProperties({SchedulerProperties.class})
@ComponentScan(basePackages = {"com.diboot.scheduler"})
@MapperScan(basePackages = {"com.diboot.scheduler.mapper"})
@Order(12)
public class SchedulerAutoConfig {

    @Autowired
    private SchedulerProperties schedulerProperties;

    /**
     * 初始化starter
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(SchedulerPluginManager.class)
    public SchedulerPluginManager schedulerPluginManager() {
        SchedulerPluginManager pluginManager = new SchedulerPluginManager();
        pluginManager.initPlugin(schedulerProperties);
        return pluginManager;
    }

}