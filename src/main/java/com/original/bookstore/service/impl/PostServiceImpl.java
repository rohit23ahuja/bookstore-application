package com.original.bookstore.service.impl;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.original.bookstore.exception.handler.RestTemplateResponseErrorHandler;
import com.original.bookstore.model.Post;
import com.original.bookstore.service.PostService;

@Service
public class PostServiceImpl implements PostService {
	private static final Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);
	private RestTemplate restTemplate;

	@Autowired
	RestTemplateBuilder restTemplateBuilder;

	@Autowired
	RestTemplateResponseErrorHandler errorHandler;

	@Async
	public CompletableFuture<Post[]> findPosts(String isbn) {
		logger.info("Requesting post api for data");
		restTemplate = restTemplateBuilder.errorHandler(errorHandler).build();
		Post[] response = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts", Post[].class);
		logger.info("Response received from post api");
		return CompletableFuture.completedFuture(response);
	}

}
