package kg.edu.alatoo.spring_web;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringWebApplicationTests {

	@Autowired
	TestBean test;

	@Test
	void contextLoads() {
		assertNotNull(test);
		System.out.println(test.hello());
	}

}
