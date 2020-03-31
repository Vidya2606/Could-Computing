package com.csye6225.fall2018.courseservice.Model;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

public class DynamoDbConnector {
	static AmazonDynamoDB dynamoDb ;
	 
	 public static void init() {
		if (dynamoDb == null) {
		// ProfileCredentialsProvider credentialsProvider = new ProfileCredentialsProvider();
		// credentialsProvider.getCredentials();
		
		dynamoDb = AmazonDynamoDBClientBuilder
					.standard()
					.withCredentials(DefaultAWSCredentialsProviderChain.getInstance())
					.withRegion("us-west-2")
					.build();		
		System.out.println("#### Created DDB client");
		} 

	}
	 
	 public AmazonDynamoDB getClient() {
		 return dynamoDb;
	 }
}
