package kg.edu.alatoo.springWeb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringWebApplicationTests {

	@Autowired
	TestBean test;

	@Test
	void contextLoads() {
		System.out.println(test.hello());
	}

}
