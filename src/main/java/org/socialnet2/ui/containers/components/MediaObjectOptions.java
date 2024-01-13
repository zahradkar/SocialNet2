package org.socialnet2.ui.containers.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import lombok.Getter;

@Getter
public abstract class MediaObjectOptions extends HorizontalLayout {
	private Button btn1;
	private Button btn2;
	private Span countSpan;

	protected MediaObjectOptions(Icon icon1, Icon icon2, String votes) {
		initialize(icon1, votes);

		icon2.addClassName("media-object__icon");
		btn2 = new Button(icon2);
		btn2.addClassName("media-object__button");

		add(btn2);
	}

	protected MediaObjectOptions(Icon icon1, String number) {
		initialize(icon1, number);
	}

	private void initialize(Icon icon1, String number) {
		icon1.addClassName("media-object__icon");
		btn1 = new Button(icon1);
		btn1.addClassName("media-object__button");

		countSpan = new Span(number);
		countSpan.addClassName("likes"); // I don't like this

		setAlignItems(Alignment.CENTER);
		addClassName("media-object__options");
		add(btn1, countSpan);
	}

}
