package org.socialnet2.ui.containers.components;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;
import org.socialnet2.backend.dtos.PostRequestDTO;
import org.socialnet2.backend.entities.Post;
import org.socialnet2.ui.views.MainView;

import java.time.Instant;
import java.time.ZoneId;
import java.util.List;

public class MainColumn extends VerticalLayout {
	// the only creation of the instance is in MainContainer; use MainColumn.instance elsewhere
	public static MainColumn instance;
	private List<Post> loadedPostsFromDB;

	public MainColumn() {
		setWidth("100%");
		add(new PostCreate());
		getPostsToFrontend();
		add(new PresentationPostsView());
		instance = this;
	}

	private void getPostsToFrontend() {
		// code is repeated due to optimize performance (there is only 1 comparison)
		loadedPostsFromDB = MainView.postService.readAll();
		if (VaadinSession.getCurrent().getAttribute(UserInfoForm.USER) == null)
			for (long i = loadedPostsFromDB.size() - 1; i >= 0; i--)
				add(createPostFrontend(loadedPostsFromDB.get((int) i)));
		else
			for (long i = loadedPostsFromDB.size() - 1; i >= 0; i--) {
				var post = loadedPostsFromDB.get((int) i);
				var mediaObject = createPostFrontend(post);

				// below: loading colors of vote icons at loading page
				// todo load colors of vote icons even after login
				var user = MainView.userService.getUserEntity(VaadinSession.getCurrent().getAttribute(UserInfoForm.USER).toString());
				if (post.getDislikedByUsers().contains(user))
					mediaObject.getVotesComponent().getBtnSecondary().getIcon().addClassName("thumbdown-red");
				if (post.getLikedByUsers().contains(user))
					mediaObject.getVotesComponent().getBtnPrimary().getIcon().addClassName("thumbup-green");

				add(mediaObject);
			}
	}

	private MediaObject createPostFrontend(Post post) {
		var localDate = Instant.ofEpochMilli(post.getCreatedAt()).atZone(ZoneId.systemDefault()).toLocalDate();
		var likes = post.getLikedByUsers().size() - post.getDislikedByUsers().size();
		var name = post.getAuthor().getFirstName() + " " + post.getAuthor().getLastName();
		return new MediaObject(new PostRequestDTO(post.getAuthor().getProfilePictureURL(), name, localDate, post.getContent(), likes + "", "0", "0"), post.getId());// todo update comment and share values
	}
}
