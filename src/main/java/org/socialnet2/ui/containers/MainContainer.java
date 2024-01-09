package org.socialnet2.ui.containers;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.socialnet2.backend.entities.Post;
import org.socialnet2.backend.dtos.PostDTO;
import org.socialnet2.ui.containers.components.MediaObject;
import org.socialnet2.ui.containers.components.PostCreate;
import org.socialnet2.ui.containers.components.PresentationPostsView;
import org.socialnet2.ui.views.MainView;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

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
		var publishedPosts = MainView.postService.readAll();
		String name, image;
		Post post;
		int likes;
		LocalDate localDate;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EE d LLL. yyyy");
		for (long i = publishedPosts.size() - 1; i >= 0; i--) {
			post = publishedPosts.get((int) i);
			localDate = Instant.ofEpochMilli(post.getCreatedAt()).atZone(ZoneId.systemDefault()).toLocalDate();
			likes = post.getLikedByUsers().size() - post.getDislikedByUsers().size();
			name = post.getAuthor().getFirstName() + " " + post.getAuthor().getLastName();
			image = (post.getAuthor().getProfilePictureURL() == null) ? "" : post.getAuthor().getProfilePictureURL();
			mainColumn.add(new MediaObject(new PostDTO(image, name, localDate.format(formatter), post.getContent(), likes + "", "0", "0"))); // todo complete
		}
		mainColumn.add(new PresentationPostsView());

		addClassName(LumoUtility.Gap.MEDIUM);
		getContent().setWidth("100%");
		getStyle().set("flex-grow", "1");
		getContent().add(leftSidebar, mainColumn, rightSidebar);
		instance = this;
	}

	public void addToMainColumn(Component component) {
		mainColumn.addComponentAtIndex(1, component);
	}
}
