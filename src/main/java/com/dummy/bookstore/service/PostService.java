package com.dummy.bookstore.service;

import java.util.concurrent.CompletableFuture;

import com.dummy.bookstore.model.Post;

public interface PostService {
	public CompletableFuture<Post[]> findPosts(String isbn);
}
