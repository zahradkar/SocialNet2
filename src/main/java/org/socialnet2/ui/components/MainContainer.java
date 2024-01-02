package org.socialnet2.ui.components;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.socialnet2.ui.views.posts.PresentationPosts;

@Route("main")
public class MainContainer extends Composite<HorizontalLayout> {
	//  TODO class in development
	public MainContainer() {
		var leftSidebar = new VerticalLayout();
		var mainColumn = new VerticalLayout();
		var rightSidebar = new VerticalLayout();
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
		mainColumn.add(new AddPostComponent(), new PresentationPosts());

		addClassName(LumoUtility.Gap.MEDIUM);
		getContent().setWidth("100%");
		getStyle().set("flex-grow", "1");
		getContent().add(leftSidebar, mainColumn, rightSidebar);
	}
}
