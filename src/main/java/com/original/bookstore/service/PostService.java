package com.original.bookstore.service;

import java.util.concurrent.CompletableFuture;

import com.original.bookstore.model.Post;

public interface PostService {
	public CompletableFuture<Post[]> findPosts(String isbn);
}
