package org.socialnet2.ui.containers.components;

import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;

public class UserIcon extends Icon {
	public UserIcon() {
        super(VaadinIcon.USER);
		setSize("44px");
		getStyle().setMarginRight("12px");
		addClassName("user-icon"); // todo use rules above in css file; they are about to be the same as for ".post-view img"
    }
}
