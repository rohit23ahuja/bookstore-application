package com.original.bookstore.service;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import com.original.bookstore.exception.NotFoundException;
import com.original.bookstore.exception.handler.RestTemplateResponseErrorHandler;
import com.original.bookstore.model.Post;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { NotFoundException.class, Post.class })
@RestClientTest
public class PostServiceFailureTest {

	@Autowired
	private MockRestServiceServer server;
	@Autowired
	private RestTemplateBuilder builder;

	@Test(expected = NotFoundException.class)
	public void when404Error_thenThrowNotFoundException() {
		Assert.assertNotNull(builder);
		Assert.assertNotNull(server);

		RestTemplate restTemplate = builder.errorHandler(new RestTemplateResponseErrorHandler()).build();

		this.server.expect(ExpectedCount.once(), requestTo("https://jsonplaceholder.typicode.com/posts"))
				.andExpect(method(HttpMethod.GET)).andRespond(withStatus(HttpStatus.NOT_FOUND));

		Post[] response = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts", Post[].class);
		this.server.verify();
	}
}
