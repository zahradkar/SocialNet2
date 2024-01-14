package org.socialnet2.ui.containers;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.socialnet2.ui.containers.components.MainColumn;

public class MainContainer extends Composite<HorizontalLayout> {
	public static MainContainer instance;
	private VerticalLayout leftSidebar;
	private VerticalLayout mainColumn; // It is not possible to use pure singleton, because it throws java.lang.IllegalStateException: Can't move a node from one state tree to another. If this is intentional, first remove the node from its current state tree by calling removeFromTree. This usually happens when a component is moved from one UI to another, which is not recommended. This may be caused by assigning components to static members or spring singleton scoped beans and referencing them from multiple UIs. Offending component: created: Component 'org.socialnet2.ui.containers.MainContainer' at 'MainContainer.java' (<init> LINE 51), attached: Component 'org.socialnet2.ui.containers.MainContainer' at 'MainContainer.java' (<init> LINE 51)
	private VerticalLayout rightSidebar;

	public MainContainer() {
		leftSidebar = new VerticalLayout();
		mainColumn = new MainColumn();
		rightSidebar = new VerticalLayout();

		leftSidebar.getStyle().setBackground("tomato");
		leftSidebar.add("Left");
		rightSidebar.getStyle().setBackground("gold");
		rightSidebar.add("Right");

		leftSidebar.setWidth("min-content");
		leftSidebar.getStyle().set("flex-grow", "1");

		rightSidebar.setWidth("min-content");
		rightSidebar.getStyle().set("flex-grow", "1");

		addClassName(LumoUtility.Gap.MEDIUM);
		getContent().setWidth("100%");
		getStyle().set("flex-grow", "1");
		getContent().add(leftSidebar, mainColumn, rightSidebar);
		instance = this;
	}
}
