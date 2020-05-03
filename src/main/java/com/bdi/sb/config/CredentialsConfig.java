package com.bdi.sb.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import lombok.extern.slf4j.Slf4j;


@Configuration
@Slf4j
@Service
public class CredentialsConfig {

	@Value("${access.key}")
	private String ACCESS_KEY;
	@Value("${secret.key}")
	private String SECRET_KEY;
	
	public AmazonS3 getS3Client() {
		AWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY,SECRET_KEY);
		return AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withRegion(Regions.AP_NORTHEAST_2).build();
	}
}
