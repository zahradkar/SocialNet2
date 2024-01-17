package org.socialnet2.backend.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socialnet2.backend.dtos.PostRequestDTO;
import org.socialnet2.backend.dtos.VoteResponseDTO;
import org.socialnet2.backend.entities.Post;
import org.socialnet2.backend.repositories.PostRepository;
import org.socialnet2.backend.repositories.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
	// TODO review everything
	private static final Logger logger = LoggerFactory.getLogger(PostService.class);
	private final PostRepository postRepository;
	private final UserRepository userRepository;

	public PostService(PostRepository postRepository, UserRepository userRepository) {
		this.postRepository = postRepository;
		this.userRepository = userRepository;
	}

	/*private static String getAuthor(User user) {
		String result = user.getFirstName() + " " + user.getLastName();
		return result.isBlank() ? user.getUsername() : result;
	}

	public void delete(Long id, String username) throws Exception {
		// TODO update exception accordingly (return code)
		if (id == null)
			throw new Exception("Id must not be null!");
		var post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("Post not found!"));
		if (!post.getAuthor().getUsername().equals(username))
			throw new AuthenticationException("You can delete only your posts!");
		postRepository.deleteById(id);
	}*/

	public Post create(PostRequestDTO data, String email) {
		// todo test
		var user = userRepository.findById(email).orElseThrow(() -> new UsernameNotFoundException("user with " + email + " not found!")); // must be findById()
		return postRepository.save(new Post(user, data.content(), data.previewImageURL(), data.previewTitle(), data.previewDescription()));
//		return new PostResponseDTO(post.getCreatedAt(), post.getId() + "", post.getContent(), post.getAuthor(), post.getLikes() + "", "0", "0", post.getUpdatedAt(), post.getPreviewImageURL(), post.getPreviewTitle(), post.getPreviewDescription());// todo update comments and shares
	}

	public List<Post> readAll() {
		return postRepository.findAll();
//		List<Post> posts = postRepository.findAll();
//		List<PostResponseDTO> responses = new ArrayList<>();
//		for (int i = 0; i < posts.size(); i++)
//			responses.add(new PostResponseDTO(posts.get(i).getCreatedAt(), posts.get(i).getId() + "", posts.get(i).getContent(), posts.get(i).getAuthor(), posts.get(i).getLikes() + "", "0","0",posts.get(i).getUpdatedAt(),posts.get(i).getPreviewImageURL(),posts.get(i).getPreviewTitle(),posts.get(i).getPreviewDescription()));
//		return responses;
	}

	/*public Post update(UpdatedPostDTO post, long userId) {
		// TODO everything
		return null;
	}

	public List<Post> getAllPostOfAUser(long userId) {
		// TODO everything
		return null;
	}*/

	/*public UserVotesRespDTO getUsersVotes(String userId) { // > get votes of a user
		var user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException(userId + " not found!"));
		List<Long> likes = new ArrayList<>();
		for (int i = 0; i < user.getLikedPosts().size(); i++)
			likes.add(user.getLikedPosts().get(i).getId());

		List<Long> dislikes = new ArrayList<>();
		for (int i = 0; i < user.getDislikedPosts().size(); i++)
			dislikes.add(user.getDislikedPosts().get(i).getId());

		logger.info("Returning IDs of liked and disliked posts!");
		return new UserVotesRespDTO(likes, dislikes);
	}

	public UserVotesRespDTO2 getUsersVotes2(String userId) { // > get votes of a user
		var user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException(userId + " not found!"));
		logger.info("Returning liked and disliked posts!");
		return new UserVotesRespDTO2(user.getLikedPosts(), user.getDislikedPosts());
	}*/

	public VoteResponseDTO upvote(long postId, String userId) {
		var post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("post " + postId + " was not found!"));
		var user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("Error - unable to upvote, voting user not found!"));

		if (post.getLikedByUsers().contains(user)) {
			post.getLikedByUsers().remove(user);
			postRepository.save(post);
			return new VoteResponseDTO(post.getLikes(), "Vote removed!", false);
		} else
			post.getLikedByUsers().add(user);

		postRepository.save(post);
		return new VoteResponseDTO(post.getLikes(), "Voted!", true);
	}

	public VoteResponseDTO downvote(long postId, String userId) {
		var post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("post " + postId + " was not found!"));
		var user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("Error - unable to upvote, voting user not found!"));

		if (post.getDislikedByUsers().contains(user)) {
			post.getDislikedByUsers().remove(user);
			postRepository.save(post);
			return new VoteResponseDTO(post.getLikes(), "Down vote removed!", false);
		} else
			post.getDislikedByUsers().add(user);

		postRepository.save(post);
		return new VoteResponseDTO(post.getLikes(), "Down voted!", true);
	}
}
