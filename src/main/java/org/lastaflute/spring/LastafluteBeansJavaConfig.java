/*
 * Copyright 2014-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.lastaflute.spring;

import org.lastaflute.web.servlet.request.RequestManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * The Java configuration of LastaFlute beans for Spring Framework. <br>
 * You can inject them by importing this class in your auto configuration class.
 * @author FreeGen
 */
@Configuration
public class LastafluteBeansJavaConfig {

    @Bean(name = "requestManager")
    @Lazy
    public RequestManager createRequestManager() {
        return new RequestManager();
    }
}
