package com.csye6225.fall2018.courseservice.Service;

import java.time.Instant;
import java.util.Random;

public class ServiceUtils {
	public static String generateID(String prefix) {
		Random rand = new Random();
		String now = String.valueOf(Instant.now().getEpochSecond());
		String randomNumber = String.valueOf(rand.nextInt(100));
		StringBuilder sb = new StringBuilder();
		sb.append(prefix).append("-").append(randomNumber).append("-").append(now.substring(now.length() - 3));
		return sb.toString();
	}
}
