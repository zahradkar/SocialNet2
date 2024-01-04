package org.socialnet2.ui.containers;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.socialnet2.ui.containers.components.PostCreate;
import org.socialnet2.ui.views.posts.PresentationPostsView;

@Route("main")
public class MainContainer extends Composite<HorizontalLayout> {
	//  TODO class in development
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
		mainColumn.add(new PostCreate(), new PresentationPostsView());

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
