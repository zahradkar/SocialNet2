package org.socialnet2.ui.containers.components;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.socialnet2.ui.views.posts.Person;

public class MediaObject extends HorizontalLayout {
	public MediaObject(Person person) {
		addClassName("card");
		setSpacing(false);
		getThemeList().add("spacing-s");

		Image image = new Image(person.image(), "profile picture");

		VerticalLayout description = new VerticalLayout();
		description.addClassName("description");
		description.setSpacing(false);
		description.setPadding(false);

		HorizontalLayout header = new HorizontalLayout();
		header.addClassName("header");
		header.setSpacing(false);
		header.getThemeList().add("spacing-s");

		Span name = new Span(person.name());
		name.addClassName("name");

		Span date = new Span(person.date());
		date.addClassName("date");

		header.add(name, date);

		Span post = new Span(person.post());
		post.addClassName("post");

		HorizontalLayout actions = new HorizontalLayout();
		actions.addClassName("actions");
		actions.setSpacing(false);
		actions.getThemeList().add("spacing-s");

		Icon likeIcon = VaadinIcon.HEART.create();
		likeIcon.addClassName("icon");

		Span likes = new Span(person.likes());
		likes.addClassName("likes");

		Icon commentIcon = VaadinIcon.COMMENT.create();
		commentIcon.addClassName("icon");

		Span comments = new Span(person.comments());
		comments.addClassName("comments");

		Icon shareIcon = VaadinIcon.CONNECT.create();
		shareIcon.addClassName("icon");

		Span shares = new Span(person.shares());
		shares.addClassName("shares");

		actions.add(new Vote(), commentIcon, comments, shareIcon, shares);
//		actions.add(likeIcon, likes, commentIcon, comments, shareIcon, shares);

		description.add(header, post, actions);
		add(image, description);
	}
}
