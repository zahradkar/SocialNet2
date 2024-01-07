package org.socialnet2.backend.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socialnet2.backend.entities.Post;
import org.socialnet2.backend.records.PostData;
import org.socialnet2.backend.repositories.PostRepository;
import org.socialnet2.backend.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

	public void create(PostData data, String email) {
		logger.debug(data.content());
		var user = userRepository.getReferenceById(email); // TODO test if user never is null
		postRepository.save(new Post(data.content(), user));

//		new PostResponseDTO(post.getId(), post.getTitle(), post.getContent(), getAuthor(user), post.getCreatedAt(), post.getLikes(), user.getProfilePictureURL());
	}

	public List<Post> getAll() {
		return  postRepository.findAll();
	}

	/*public Post update(UpdatedPostDTO post, long userId) {
		// TODO everything
		return null;
	}

	public List<Post> getAllPostOfAUser(long userId) {
		// TODO everything
		return null;
	}

	public VotesResponseDTO getUserVotes(String username) {
		var user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username + " not found!"));
		List<Long> likes = new ArrayList<>();
		for (int i = 0; i < user.getLikedPosts().size(); i++)
			likes.add(user.getLikedPosts().get(i).getId());

		List<Long> dislikes = new ArrayList<>();
		for (int i = 0; i < user.getDislikedPosts().size(); i++)
			dislikes.add(user.getDislikedPosts().get(i).getId());

		logger.info("Returning liked and disliked posts!");
		return new VotesResponseDTO(likes, dislikes);
	}

	public VoteResponseDTO upvote(long postId, String username) throws PostNotFoundException {
		var post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("post " + postId + " was not found!"));
		var user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Error - unable to upvote, voting user not found!"));

		if (post.getLikedByUsers().contains(user)) {
			post.getLikedByUsers().remove(user);
			postRepository.save(post);
			return new VoteResponseDTO(post.getLikes(), "Vote removed!", false);
		} else
			post.getLikedByUsers().add(user);

		postRepository.save(post);
		return new VoteResponseDTO(post.getLikes(), "Voted!", true);
	}

	public VoteResponseDTO downvote(long postId, String username) throws PostNotFoundException {
		var post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("post " + postId + " was not found!"));
		var user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Error - unable to upvote, voting user not found!"));

		if (post.getDislikedByUsers().contains(user)) {
			post.getDislikedByUsers().remove(user);
			postRepository.save(post);
			return new VoteResponseDTO(post.getLikes(), "Down vote removed!", false);
		} else
			post.getDislikedByUsers().add(user);

		postRepository.save(post);
		return new VoteResponseDTO(post.getLikes(), "Down voted!", true);
	}*/
}
