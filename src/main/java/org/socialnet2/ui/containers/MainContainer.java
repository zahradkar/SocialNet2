package org.socialnet2.ui.containers;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.socialnet2.backend.dtos.PostRequestDTO;
import org.socialnet2.backend.entities.Post;
import org.socialnet2.ui.containers.components.*;
import org.socialnet2.ui.views.MainView;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

//@Route("main")
public class MainContainer extends Composite<HorizontalLayout> {
	public static MainContainer instance;
	VerticalLayout leftSidebar;
	VerticalLayout mainColumn;
	VerticalLayout rightSidebar;

	public MainContainer() {
		leftSidebar = new VerticalLayout();
		mainColumn = new VerticalLayout();
		rightSidebar = new VerticalLayout();

		leftSidebar.getStyle().setBackground("tomato");
		leftSidebar.add("Left");
		rightSidebar.getStyle().setBackground("gold");
		rightSidebar.add("Right");

		leftSidebar.setWidth("min-content");
		leftSidebar.getStyle().set("flex-grow", "1");

		mainColumn.setWidth("100%");
//		mainColumn.setHeight("100%");

		rightSidebar.setWidth("min-content");
		rightSidebar.getStyle().set("flex-grow", "1");

		mainColumn.add(new PostCreate());
		postsFromDBtoMainColumn();
		mainColumn.add(new PresentationPostsView());

		addClassName(LumoUtility.Gap.MEDIUM);
		getContent().setWidth("100%");
		getStyle().set("flex-grow", "1");
		getContent().add(leftSidebar, mainColumn, rightSidebar);
		instance = this;
	}

	private void postsFromDBtoMainColumn() {
		// todo refactor whole method!!!
		if (VaadinSession.getCurrent().getAttribute(UserInfoForm.USER) == null) {
			var publishedPosts = MainView.postService.readAll();
			String name;
			Post post;
			int likes;
			LocalDate localDate;
			for (long i = publishedPosts.size() - 1; i >= 0; i--) {
				post = publishedPosts.get((int) i);
				localDate = Instant.ofEpochMilli(post.getCreatedAt()).atZone(ZoneId.systemDefault()).toLocalDate();
				likes = post.getLikedByUsers().size() - post.getDislikedByUsers().size();
				name = post.getAuthor().getFirstName() + " " + post.getAuthor().getLastName();

				mainColumn.add(new MediaObject(new PostRequestDTO(post.getAuthor().getProfilePictureURL(), name, localDate, post.getContent(), likes + "", "0", "0"), post.getId())); // todo update comment and share values
			}
		} else {
			var publishedPosts = MainView.postService.readAll();
			String name;
			Post post;
			int likes;
			LocalDate localDate;
			for (long i = publishedPosts.size() - 1; i >= 0; i--) {
				post = publishedPosts.get((int) i);
				localDate = Instant.ofEpochMilli(post.getCreatedAt()).atZone(ZoneId.systemDefault()).toLocalDate();
				likes = post.getLikedByUsers().size() - post.getDislikedByUsers().size();
				name = post.getAuthor().getFirstName() + " " + post.getAuthor().getLastName();
				var mediaObject = new MediaObject(new PostRequestDTO(post.getAuthor().getProfilePictureURL(), name, localDate, post.getContent(), likes + "", "0", "0"), post.getId());

				//------------------------------------
//				loading colors of vote icons at loading page
				// todo load colors of vote icons even after login
				var description = (VerticalLayout) mediaObject.getComponentAt(mediaObject.getComponentCount() - 1); // i don't like this approach!!!
				var actions = (HorizontalLayout) description.getComponentAt(2);// i don't like this approach!!!
				var voteComponent = (VotesComponent) actions.getComponentAt(0); // i don't like this approach!!!
//				var user = VaadinSession.getCurrent().getAttribute(UserInfoForm.USER).toString();
				var user = MainView.userService.getUserEntity(VaadinSession.getCurrent().getAttribute(UserInfoForm.USER).toString());
				if (post.getDislikedByUsers().contains(user))
					voteComponent.getBtnSecondary().getIcon().addClassName("red-icon");
				if (post.getLikedByUsers().contains(user))
					voteComponent.getBtnPrimary().getIcon().addClassName("green-icon");
				//---------------------

				mainColumn.add(mediaObject); // todo update comment and share values
			}
		}
	}

	public void addToMainColumn(Component component) {
		mainColumn.addComponentAtIndex(1, component);
	}
}
