package br.brothers.mourao.ut.controller;

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
public class DynamicRestApiControllerTest extends br.brothers.mourao.Test {

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

        Map<Object, Object> fooDoc = new HashMap<>();
        att.put("_id", "25f62079-eb30-47eb-ba0a-4d3ed0dab367");
        att.put("foo", 123);
        att.put("bar", "123");
        db.getCollection(collectionName).save(new BasicDBObject(fooDoc));
    }

    @After
    public void release() {
        MongoDatabase database = mongoClient.getDatabase("embedded");
        database.drop();
    }

    @Test
    @Ignore
    public void testGetAll()
        throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/Foo"))
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    @Ignore
    public void testGetById()
        throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/Foo/25f62079-eb30-47eb-ba0a-4d3ed0dab367"))
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    @Ignore
    public void testCreate()
            throws Exception {
        Map<Object, Object> content = new HashMap<>();
        content.put("foo", 123);
        content.put("bar", "123");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/Foo").content(new Gson().toJson(content)))
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    @Ignore
    public void testUpdate()
            throws Exception {
        Map<Object, Object> content = new HashMap<>();
        content.put("foo", 321);
        content.put("bar", "321");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/Foo/25f62079-eb30-47eb-ba0a-4d3ed0dab367").content(new Gson().toJson(content)))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    @Ignore
    public void testDelete()
        throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/Foo/25f62079-eb30-47eb-ba0a-4d3ed0dab367"))
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

}
