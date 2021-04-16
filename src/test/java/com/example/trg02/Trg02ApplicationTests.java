package com.example.trg02;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootTest
class Trg02ApplicationTests {

	@Test
	void contextLoads() {
	}

	
	 @GetMapping(value = "/")
	    public String sampleHome() {
	        return "user";
	        
	    }
}
