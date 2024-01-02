package org.socialnet2.ui.components;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class Footer extends Composite<HorizontalLayout> {
	public Footer() {
		getStyle().setBackground("lightgray");
		getContent().setWidth("100%");
		getContent().add("Footer");
	}
}
