package com.gid.admin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.core.env.ResourceIdResolver;
import org.springframework.stereotype.Service;

import com.amazonaws.services.ec2.AmazonEC2;

@Service
public class ApplicationService {
	@Autowired
	private AmazonEC2 amazonEc2;

	@Autowired
	private ResourceIdResolver resourceIdResolver;

	public void handleApplicationLogic() {
		String physicalBucketName = this.resourceIdResolver.resolveToPhysicalResourceId("gid-dn");
		System.out.println(physicalBucketName);
	}

}
