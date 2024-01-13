package org.socialnet2.ui.containers.components;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;
import org.socialnet2.backend.dtos.PostRequestDTO;
import org.socialnet2.ui.views.MainView;

import java.time.format.DateTimeFormatter;

public class MediaObject extends HorizontalLayout {
	private Span postIdSpan = new Span();

	public MediaObject(PostRequestDTO postRequestDTO, long postId) {
		initialize(postRequestDTO, postId);
	}

	public MediaObject(PostRequestDTO postRequestDTO) {
		// this constructor is temporal due to loading "static" list in PresentationViewPost class
		initialize(postRequestDTO, null);
	}

	private void initialize(PostRequestDTO postRequestDTO, Long postId) {
		addClassName("card");
		setSpacing(false);
		getThemeList().add("spacing-s");

		Image image = new Image(postRequestDTO.imageURL(), "profile picture");

		VerticalLayout description = new VerticalLayout();
		description.addClassName("description");
		description.setSpacing(false);
		description.setPadding(false);

		HorizontalLayout header = new HorizontalLayout();
		header.addClassName("header");
		header.setSpacing(false);
		header.getThemeList().add("spacing-s");

		Span name = new Span(postRequestDTO.name());
		name.addClassName("name");

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EE d LLL. yyyy");
		Span date = new Span(postRequestDTO.date().format(formatter)); // todo thing about localization formatting
		date.addClassName("date");

		header.add(name, date);

		Span post = new Span(postRequestDTO.content());
		post.addClassName("post");

		HorizontalLayout actions = new HorizontalLayout();
		actions.addClassName("actions");
		actions.setSpacing(false);
		actions.getThemeList().add("spacing-l");

		Icon likeIcon = VaadinIcon.HEART.create();
		likeIcon.addClassName("icon");

		Span likes = new Span(postRequestDTO.likes());
		likes.addClassName("likes");

		Icon commentIcon = VaadinIcon.COMMENT.create();
		commentIcon.addClassName("icon");

		Span comments = new Span(postRequestDTO.comments());
		comments.addClassName("comments");

		Icon shareIcon = VaadinIcon.CONNECT.create();
		shareIcon.addClassName("icon");

		Span shares = new Span(postRequestDTO.shares());
		shares.addClassName("shares");

		//----------------------
		var vote = new VotesComponent(VaadinIcon.THUMBS_UP.create(), VaadinIcon.THUMBS_DOWN.create(), postRequestDTO.likes());
		var comment = new CommentComponent(VaadinIcon.COMMENT.create(), postRequestDTO.comments());
		var share = new SharesComponent(VaadinIcon.CONNECT.create(), postRequestDTO.shares());

		actions.add(vote, comment, share);

		if (postId != null) {
			postIdSpan.setText(postId.toString());
			postIdSpan.setVisible(false);
			actions.add(postIdSpan);
		}

		vote.getBtnPrimary().addClickListener(buttonClickEvent -> {
			// processing of upvote
			// todo more tests
			var user = VaadinSession.getCurrent().getAttribute(UserInfoForm.USER);
			if (user == null)
				new LoginDialog().open();
			else {
				var response = MainView.postService.upvote(Long.parseLong(postIdSpan.getText()), user.toString());
				Notification.show(response.result());
				vote.getSpanCount().setText(response.votes() + "");
				if (vote.getBtnPrimary().getIcon().getClassName().contains("green-icon"))
					vote.getBtnPrimary().getIcon().removeClassName("green-icon");
				else
					vote.getBtnPrimary().getIcon().addClassName("green-icon");
			}
		});

		vote.getBtnSecondary().addClickListener(buttonClickEvent -> {
			// processing of downvote
			//It seems to be working properly. Consider todo more tests
			var user = VaadinSession.getCurrent().getAttribute(UserInfoForm.USER);
			if (user == null)
				new LoginDialog().open();
			else {
				var response = MainView.postService.downvote(Long.parseLong(postIdSpan.getText()), user.toString()); // sends data do DB and receives the response
				Notification.show(response.result()); // displays result
				vote.getSpanCount().setText(response.votes() + ""); // updates count of votes
				if (vote.getBtnSecondary().getIcon().getClassName().contains("red-icon"))
					vote.getBtnSecondary().getIcon().removeClassName("red-icon");
				else
					vote.getBtnSecondary().getIcon().addClassName("red-icon");
			}
		});
		//-----------------------------
		description.add(header, post, actions);
		UserIcon userIcon = new UserIcon();

		if (image.getSrc().isEmpty()) {
			add(userIcon); // todo add style to icon and apply to whole project
		} else {
			add(image);
		}
		add(description);

	}
}
