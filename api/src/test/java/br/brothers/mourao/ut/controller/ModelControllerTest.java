package br.brothers.mourao.ut.controller;

import br.brothers.mourao.controller.dto.ModelDTO;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

@WebAppConfiguration
public class ModelControllerTest extends br.brothers.mourao.Test {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MongoClient mongoClient;

    @Autowired
    private Mongo mongo;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        String dbName = "embedded";
        String collectionName = "Foo";
        MongoDatabase database = mongoClient.getDatabase(dbName);
        database.createCollection(collectionName);
        DB db = mongo.getDB(dbName);

        Map<Object, Object> att = new HashMap<>();
        att.put("foo", "int");
        att.put("bar", "String");
        Map<Object, Object> doc = new HashMap<>();
        doc.put("id", "5907b54cf89a674c55a61b5x");
        doc.put("name", "Foo");
        doc.put("attributes", att);

        db.getCollection(collectionName).save(new BasicDBObject(doc));
    }

    @After
    public void release() {
        MongoDatabase database = mongoClient.getDatabase("embedded");
        database.drop();
    }

    @Test
    public void testGetAll()
        throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/model"))
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    @Ignore
    public void testGetById()
        throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/model/5907b54cf89a674c55a61b5x"))
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    @Ignore
    public void testCreate()
        throws Exception {
        ModelDTO content = new ModelDTO();
        Map<String, String> att = new HashMap<>();
        att.put("foo", "int");
        att.put("bar", "String");
        content.setName("Bar");
        content.setAttributes(att);
        mockMvc.perform(MockMvcRequestBuilders.post("/model").content(new Gson().toJson(content)))
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    @Ignore
    public void testDelete()
        throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/model/5907b54cf89a674c55a61b5x"))
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

}
