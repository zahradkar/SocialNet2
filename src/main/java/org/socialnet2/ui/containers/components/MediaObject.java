package org.socialnet2.ui.containers.components;

import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.socialnet2.backend.dtos.PostDTO;

public class MediaObject extends HorizontalLayout {
    public MediaObject(PostDTO postDTO) {
        addClassName("card");
        setSpacing(false);
        getThemeList().add("spacing-s");

        Image image = new Image(postDTO.image(), "profile picture");

        VerticalLayout description = new VerticalLayout();
        description.addClassName("description");
        description.setSpacing(false);
        description.setPadding(false);

        HorizontalLayout header = new HorizontalLayout();
        header.addClassName("header");
        header.setSpacing(false);
        header.getThemeList().add("spacing-s");

        Span name = new Span(postDTO.name());
        name.addClassName("name");

        Span date = new Span(postDTO.date());
        date.addClassName("date");

        header.add(name, date);

        Span post = new Span(postDTO.content());
        post.addClassName("post");

        HorizontalLayout actions = new HorizontalLayout();
        actions.addClassName("actions");
        actions.setSpacing(false);
        actions.getThemeList().add("spacing-l");

        Icon likeIcon = VaadinIcon.HEART.create();
        likeIcon.addClassName("icon");

        Span likes = new Span(postDTO.likes());
        likes.addClassName("likes");

        Icon commentIcon = VaadinIcon.COMMENT.create();
        commentIcon.addClassName("icon");

        Span comments = new Span(postDTO.comments());
        comments.addClassName("comments");

        Icon shareIcon = VaadinIcon.CONNECT.create();
        shareIcon.addClassName("icon");

        Span shares = new Span(postDTO.shares());
        shares.addClassName("shares");

        actions.add(
                new VotesComponent(VaadinIcon.THUMBS_UP.create(), VaadinIcon.THUMBS_DOWN.create(), postDTO.likes()),
                new CommentComponent(VaadinIcon.COMMENT.create(), postDTO.comments()),
                new SharesComponent(VaadinIcon.CONNECT.create(), postDTO.shares())
        );
//		actions.add(likeIcon, likes, commentIcon, comments, shareIcon, shares);

        description.add(header, post, actions);
        add(image, description);
    }
}
