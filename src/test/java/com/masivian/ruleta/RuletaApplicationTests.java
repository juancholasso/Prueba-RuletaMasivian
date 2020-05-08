package com.masivian.ruleta;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.masivian.services.RouletteService;

@SpringBootTest
class RuletaApplicationTests {

    @Autowired
    private RouletteService rouletteService;
    
	@Test
	void contextLoads() {
	}
	
//	@Test
//	public void testCreateRoulette() {
//		RestTemplate restTemplate = new RestTemplate();
//	     
//	    final String baseUrl = "http://localhost:8080"/employees";
//	    URI uri = new URI(baseUrl);
//	 
//	    ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
//	     
//	    //Verify request succeed
//	    Assert.assertEquals(200, result.getStatusCodeValue());
//	    Assert.assertEquals(true, result.getBody().contains("employeeList"));
//	}
	
	@Test
    public void testDependency(){       
        assertEquals(rouletteService.isValidColor("red"), false);   
        assertEquals(rouletteService.isValidColor("rojo"), true);     
        assertEquals(rouletteService.isValidColor("negro"), true);     

    }

}
