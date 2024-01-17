package org.socialnet2.ui.containers.components;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;
import lombok.Getter;
import org.socialnet2.backend.entities.Post;
import org.socialnet2.ui.views.MainView;

import java.time.format.DateTimeFormatter;

public class MediaObject extends VerticalLayout {
	private final Span postIdSpan = new Span();
	@Getter
	private VotesComponent votesComponent;
	private CommentComponent commentComponent;
	private SharesComponent sharesComponent;

	/*public MediaObject(PostRequestDTO postRequestDTO) {
		initialize(postRequestDTO);
	}*/

	public MediaObject(Post data) {
		setPadding(false);

		var card = new HorizontalLayout();
		card.addClassName("card");
		card.setSpacing(false);
		card.getThemeList().add("spacing-s");

		Image image = new Image(data.getAuthor().getProfilePictureURL(), "profile picture");

		VerticalLayout description = new VerticalLayout();
		description.addClassName("description");
		description.setSpacing(false);
		description.setPadding(false);

		HorizontalLayout header = new HorizontalLayout();
		header.addClassName("header");
		header.setSpacing(false);
		header.getThemeList().add("spacing-s");

		String fullName = "";
		if (!data.getAuthor().getFirstName().isEmpty() || !data.getAuthor().getLastName().isEmpty())
			fullName = data.getAuthor().getFirstName() + " " + data.getAuthor().getLastName();
		Span nameSpn = new Span(fullName);
		nameSpn.addClassName("name");

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EE d LLL. yyyy");
		Span date = new Span("some date"); // todo get date from post and format it
		date.addClassName("date");

		header.add(nameSpn, date);

		Span postText = new Span(data.getContent());
		postText.addClassName("post");

		var post = new VerticalLayout();
		post.setPadding(false);
		post.add(postText);
		var previewImage = new Image(data.getPreviewImageURL(), "presentation picture of scraped web page");
		previewImage.getStyle().setBorderRadius("0").setWidth("auto").setHeight("auto").setMaxWidth("400px").setMaxHeight("300px");
		var previewTitle = new Span(data.getPreviewTitle());
		previewTitle.setMaxWidth("400px");
		previewTitle.getStyle().setFontWeight("bold");
		var previewDescription = new Span(data.getPreviewDescription());
		previewDescription.setMaxWidth("400px");
		if (!data.getPreviewImageURL().isBlank())
			post.add(previewImage);
		if (!data.getPreviewTitle().isBlank())
			post.add(previewTitle);
		if (!data.getPreviewDescription().isBlank())
			post.add(previewDescription);

		HorizontalLayout actions = new HorizontalLayout();
		actions.addClassName("actions");
		actions.setSpacing(false);
		actions.getThemeList().add("spacing-l");

		Icon likeIcon = VaadinIcon.HEART.create();
		likeIcon.addClassName("icon");

		Span likes = new Span(String.valueOf(data.getLikes()));
		likes.addClassName("likes");

		Icon commentIcon = VaadinIcon.COMMENT.create();
		commentIcon.addClassName("icon");

		Span comments = new Span("0"); // todo
		comments.addClassName("comments");

		Icon shareIcon = VaadinIcon.CONNECT.create();
		shareIcon.addClassName("icon");

		Span shares = new Span("0"); // todo
		shares.addClassName("shares");

		//----------------------
		votesComponent = new VotesComponent(VaadinIcon.THUMBS_UP.create(), VaadinIcon.THUMBS_DOWN.create(), String.valueOf(data.getLikes()));
		commentComponent = new CommentComponent(VaadinIcon.COMMENT.create(), "0"); // todo update accordingly
		sharesComponent = new SharesComponent(VaadinIcon.CONNECT.create(), "0"); // todo update accordingly

		actions.add(votesComponent, commentComponent, sharesComponent);

		postIdSpan.setText(data.getId() + "");
//		postIdSpan.setVisible(false);
		actions.add(postIdSpan);

		votesComponent.getBtnPrimary().addClickListener(buttonClickEvent -> {
			// processing of upvote
			// todo more tests
			var user = VaadinSession.getCurrent().getAttribute(UserInfoForm.USER);
			if (user == null)
				new LoginDialog().open();
			else {
				var response = MainView.postService.upvote(Long.parseLong(postIdSpan.getText()), user.toString());
				Notification.show(response.result());
				votesComponent.getSpanCount().setText(response.votes() + "");
				if (votesComponent.getBtnPrimary().getIcon().getClassName().contains("thumbup-green"))
					votesComponent.getBtnPrimary().getIcon().removeClassName("thumbup-green");
				else
					votesComponent.getBtnPrimary().getIcon().addClassName("thumbup-green");
			}
		});

		votesComponent.getBtnSecondary().addClickListener(buttonClickEvent -> {
			// processing of downvote
			//It seems to be working properly. Consider todo more tests
			var user = VaadinSession.getCurrent().getAttribute(UserInfoForm.USER);
			if (user == null)
				new LoginDialog().open();
			else {
				var response = MainView.postService.downvote(Long.parseLong(postIdSpan.getText()), user.toString()); // sends data do DB and receives the response
				Notification.show(response.result()); // displays result
				votesComponent.getSpanCount().setText(response.votes() + ""); // updates count of votes
				if (votesComponent.getBtnSecondary().getIcon().getClassName().contains("thumbdown-red"))
					votesComponent.getBtnSecondary().getIcon().removeClassName("thumbdown-red");
				else
					votesComponent.getBtnSecondary().getIcon().addClassName("thumbdown-red");
			}
		});
		//-----------------------------
//		description.add(header, postText, actions);
		description.add(header, post, actions);
		UserIcon userIcon = new UserIcon();

		if (image.getSrc().isEmpty())
			card.add(userIcon); // todo add style to icon and apply to whole project
		else
			card.add(image);
		card.add(description);
		add(card);
	}

	/*private void initialize(PostRequestDTO postRequestDTO, Long postId) {
		// TODO CONSIDER NOT USING THIS METHOD
		addClassName("card");
		setSpacing(false);
		getThemeList().add("spacing-s");

		Image image = new Image(postRequestDTO.profileImageURL(), "profile picture");

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
		votesComponent = new VotesComponent(VaadinIcon.THUMBS_UP.create(), VaadinIcon.THUMBS_DOWN.create(), postRequestDTO.likes());
		commentComponent = new CommentComponent(VaadinIcon.COMMENT.create(), postRequestDTO.comments());
		sharesComponent = new SharesComponent(VaadinIcon.CONNECT.create(), postRequestDTO.shares());

		actions.add(votesComponent, commentComponent, sharesComponent);

		if (postId != null) {
			postIdSpan.setText(postId.toString());
			postIdSpan.setVisible(false);
			actions.add(postIdSpan);
		}

		votesComponent.getBtnPrimary().addClickListener(buttonClickEvent -> {
			// processing of upvote
			// todo more tests
			var user = VaadinSession.getCurrent().getAttribute(UserInfoForm.USER);
			if (user == null)
				new LoginDialog().open();
			else {
				var response = MainView.postService.upvote(Long.parseLong(postIdSpan.getText()), user.toString());
				Notification.show(response.result());
				votesComponent.getSpanCount().setText(response.votes() + "");
				if (votesComponent.getBtnPrimary().getIcon().getClassName().contains("thumbup-green"))
					votesComponent.getBtnPrimary().getIcon().removeClassName("thumbup-green");
				else
					votesComponent.getBtnPrimary().getIcon().addClassName("thumbup-green");
			}
		});

		votesComponent.getBtnSecondary().addClickListener(buttonClickEvent -> {
			// processing of downvote
			//It seems to be working properly. Consider todo more tests
			var user = VaadinSession.getCurrent().getAttribute(UserInfoForm.USER);
			if (user == null)
				new LoginDialog().open();
			else {
				var response = MainView.postService.downvote(Long.parseLong(postIdSpan.getText()), user.toString()); // sends data do DB and receives the response
				Notification.show(response.result()); // displays result
				votesComponent.getSpanCount().setText(response.votes() + ""); // updates count of votes
				if (votesComponent.getBtnSecondary().getIcon().getClassName().contains("thumbdown-red"))
					votesComponent.getBtnSecondary().getIcon().removeClassName("thumbdown-red");
				else
					votesComponent.getBtnSecondary().getIcon().addClassName("thumbdown-red");
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

	}*/
}
