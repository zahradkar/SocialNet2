package org.socialnet2.ui.containers.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@Route("comment")
@PageTitle("SocialNet | comment component")
@AnonymousAllowed
public class CommentComponent extends HorizontalLayout {
	public CommentComponent(String komentar) {
		Icon commentIcon = VaadinIcon.COMMENT.create();
		commentIcon.addClassName("vote-icon");

		var commentBtn = new Button(commentIcon);
		commentBtn.addClassName("media-object-button");

		Span comments = new Span(komentar);
		comments.addClassName("comments");

		setAlignItems(Alignment.CENTER);
		addClassName("komentare");
		add(commentBtn, comments);
	}
}
