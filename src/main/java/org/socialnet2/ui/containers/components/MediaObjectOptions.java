package org.socialnet2.ui.containers.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import lombok.Getter;

@Getter
public abstract class MediaObjectOptions extends HorizontalLayout {
	// this class is template of bottom part of Media Object (vote component, comment component, shares component)
	private Button btnPrimary;
	private Button btnSecondary;
	private Span spanCount;

	protected MediaObjectOptions(Icon icon1, Icon icon2, String votes) {
		initialize(icon1, votes);

		icon2.addClassName("media-object__icon");
		btnSecondary = new Button(icon2);
		btnSecondary.addClassName("media-object__button");

		add(btnSecondary);
	}

	protected MediaObjectOptions(Icon icon1, String number) {
		initialize(icon1, number);
	}

	private void initialize(Icon icon1, String number) {
		icon1.addClassName("media-object__icon");
		btnPrimary = new Button(icon1);
		btnPrimary.addClassName("media-object__button");

		spanCount = new Span(number);
		spanCount.addClassName("likes"); // I don't like this

		setAlignItems(Alignment.CENTER);
		addClassName("media-object__options");
		add(btnPrimary, spanCount);
	}

}
