package com.amit.common.utils;

import java.util.UUID;


public class RandomUIDGenerator {
	
  public static String generateId() {
	  UUID uid = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");     
      return uid.randomUUID().toString(); 
  }
  
}
