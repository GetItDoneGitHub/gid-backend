package com.gid.admin.config;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
@EnableDynamoDBRepositories(basePackages = "com.baeldung.spring.data.dynamodb.repositories")
public class DynamoConfig {
	@Value("${amazon.dynamodb.endpoint}")
	private String amazonDynamoDBEndpoint;

	@Value("${cloud.aws.credentials.access-key}")
	private String amazonAWSAccessKey;

	@Value("${cloud.aws.credentials.secret-key}")
	private String amazonAWSSecretKey;

	@Bean
	public AmazonS3 amazonS3() {
		AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(amazonAWSCredentials())).build();

		if (!StringUtils.isEmpty(amazonDynamoDBEndpoint)) {
			s3Client.setEndpoint(amazonDynamoDBEndpoint);
		}

		return s3Client;
	}

	@Bean
	public AWSCredentials amazonAWSCredentials() {
		return new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
	}
}
