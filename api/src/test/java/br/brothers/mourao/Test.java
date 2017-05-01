package br.brothers.mourao;

import org.junit.runner.RunWith;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@TestPropertySource(properties = { "spring.data.mongodb.port=27667" })
public abstract class Test {

}
