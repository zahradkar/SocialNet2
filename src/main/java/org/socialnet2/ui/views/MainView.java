package org.socialnet2.ui.views;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.socialnet2.ui.components.Footer;
import org.socialnet2.ui.components.Header;
import org.socialnet2.ui.components.MainContainer;

@PageTitle("SocialNet")
@Route(value = "")
@Uses(Icon.class)
@AnonymousAllowed
public class MainView extends Composite<VerticalLayout> {
	public MainView() {
		var header = new Header();
		var main = new MainContainer();
		var footer = new Footer();

		main.getStyle().setMarginTop(header.getHeight());

		getContent().setWidth("100%");
		getContent().setPadding(false);
		getContent().getStyle().set("flex-grow", "1");
		getContent().add(header, main, footer);
	}
}
