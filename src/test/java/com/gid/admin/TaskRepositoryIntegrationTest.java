package com.gid.admin;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.gid.admin.model.Task;
import com.gid.admin.modules.task.TaskRepository; 

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Gid2017Application.class)
@WebAppConfiguration
@ActiveProfiles("local")
@TestPropertySource(properties = { "amazon.dynamodb.endpoint=http://localhost:8000/",
		"cloud.aws.credentials.accessKey=AKIAIRYUBGUILGEGPLAA", "cloud.aws.credentials.secretKey=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" })
public class TaskRepositoryIntegrationTest {

	private DynamoDBMapper dynamoDBMapper;
	
	@Autowired
	private AmazonDynamoDB amazonDynamoDB;
	
	@Autowired 
	TaskRepository repository;
	
    private static final String EXPECTED_COST = "20";
    private static final String EXPECTED_PRICE = "50";
	
    @Before
    public void setup() throws Exception {
        dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
         
        CreateTableRequest tableRequest = dynamoDBMapper
          .generateCreateTableRequest(Task.class);
        tableRequest.setProvisionedThroughput(
          new ProvisionedThroughput(1L, 1L));
        amazonDynamoDB.createTable(tableRequest);
         
        //...
 
        dynamoDBMapper.batchDelete(
          (List<Task>)repository.findAll());
    }
 
    @Test
    public void sampleTestCase() {
        Task dave = new Task(EXPECTED_COST, EXPECTED_PRICE);
        repository.save(dave);
 
        List<Task> result 
          = (List<Task>) repository.findAll();
         
        assertTrue("Not empty", result.size() > 0);
        assertTrue("Contains item with expected cost", 
          result.get(0).getTaskId().equals(EXPECTED_COST));
    }
}
