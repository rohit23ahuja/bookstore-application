package com.nagarro.bookstore.service;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.dummy.bookstore.model.Post;
import com.dummy.bookstore.service.BookService;
import com.dummy.bookstore.service.PostService;
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTest {

	@MockBean
	private PostService postService;
	
	@Autowired
	private BookService bookService;
	

	@Test
	public void whenFindBookInPosts_thenReturnPostsTitle() throws Exception {
		Post[] array = new Post[1];
		Post p =  new Post(1, 1,
				"sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
				"quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto");
		array[0] =p;

		CompletableFuture<Post[]> future = CompletableFuture.completedFuture(array);
		given(this.postService.findPosts("456FGJ")).willReturn(future);
		List<String> result = bookService.findPostsByIsbn("456FGJ");
		assertThat(result.get(0), containsString("sunt"));
	}
	

}




/*	
 * 	@Autowired
private RestTemplate restTemplate;
@MockBean
private RestTemplateResponseErrorHandler errorHandler;
 * 	@Before
public void init() {
	server = MockRestServiceServer.createServer(restTemplate);
}
 * 	@Autowired
	private ObjectMapper mapper;
 * 	private MockRestServiceServer server;
 * 	this.server.expect(requestTo("https://jsonplaceholder.typicode.com/posts"))
.andRespond(withSuccess(postString, MediaType.APPLICATION_JSON));
		String postString = mapper.writeValueAsString(array);
				//given(this.bookService.findByIsbn(book.getIsbn())).willReturn(book);
		Book book = new Book(1l,"456FGJ" , "sunt", "Rohit", 1,5);
		*/