package com.gid.admin.config;

import org.springframework.cloud.aws.context.config.annotation.EnableContextInstanceData;
import org.springframework.cloud.aws.context.config.annotation.EnableStackConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableContextInstanceData
@EnableStackConfiguration
public class ApplicationConfiguration {



}
