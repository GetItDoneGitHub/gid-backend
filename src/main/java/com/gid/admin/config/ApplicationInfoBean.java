package com.gid.admin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationInfoBean {

	@Value("${ami-id:N/A}")
	private String amiId;

	@Value("${hostname:N/A}")
	private String hostname;

	@Value("${instance-type:N/A}")
	private String instanceType;

	@Value("${services/domain:N/A}")
	private String serviceDomain;
}
