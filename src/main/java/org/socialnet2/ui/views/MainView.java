package org.socialnet2.ui.views;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.socialnet2.backend.services.PostService;
import org.socialnet2.backend.services.ScrapeService;
import org.socialnet2.backend.services.UserService;
import org.socialnet2.ui.containers.Footer;
import org.socialnet2.ui.containers.Header;
import org.socialnet2.ui.containers.MainContainer;

@PageTitle("SocialNet")
@Route(value = "")
@Uses(Icon.class)
@AnonymousAllowed
public class MainView extends Composite<VerticalLayout> {
	public static PostService postService;
	public static UserService userService;
	public static ScrapeService scrapeService;
	public MainView(UserService userService1, PostService postService1, ScrapeService scrapeService1) {
		postService = postService1;
		userService = userService1;
		scrapeService = scrapeService1;

		var header = new Header();
		var main = new MainContainer();
		var footer = new Footer();

		main.getStyle().setMarginTop(header.getHeight());
		header.getStyle().setTop("0");

		addClassName("posts-view");
		getContent().setWidth("100%");
		getContent().setPadding(false);
		getContent().getStyle().set("flex-grow", "1");
		getContent().add(header, main, footer);
	}
}
