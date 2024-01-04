package org.socialnet2.ui.views.posts;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.socialnet2.ui.containers.components.MediaObject;

import java.util.Arrays;
import java.util.List;

@PageTitle("Posts")
@Route("posts")
@AnonymousAllowed
public class PresentationPostsView extends Div {
	public PresentationPostsView() {
		addClassName("posts-view");

		for (Person person : persons) {
			HorizontalLayout card = new MediaObject(person);
//			HorizontalLayout card = createCard(person);
			add(card);
		}
	}

	/*private HorizontalLayout createCard(Person person) {
		HorizontalLayout card = new HorizontalLayout();
		card.addClassName("card");
		card.setSpacing(false);
		card.getThemeList().add("spacing-s");

		Image image = new Image(person.image(), "profile picture");

		VerticalLayout description = new VerticalLayout();
		description.addClassName("description");
		description.setSpacing(false);
		description.setPadding(false);

		HorizontalLayout header = new HorizontalLayout();
		header.addClassName("header");
		header.setSpacing(false);
		header.getThemeList().add("spacing-s");

		Span name = new Span(person.name());
		name.addClassName("name");

		Span date = new Span(person.date());
		date.addClassName("date");

		header.add(name, date);

		Span post = new Span(person.post());
		post.addClassName("post");

		HorizontalLayout actions = new HorizontalLayout();
		actions.addClassName("actions");
		actions.setSpacing(false);
		actions.getThemeList().add("spacing-s");

		Icon likeIcon = VaadinIcon.HEART.create();
		likeIcon.addClassName("icon");

		Span likes = new Span(person.likes());
		likes.addClassName("likes");

		Icon commentIcon = VaadinIcon.COMMENT.create();
		commentIcon.addClassName("icon");

		Span comments = new Span(person.comments());
		comments.addClassName("comments");

		Icon shareIcon = VaadinIcon.CONNECT.create();
		shareIcon.addClassName("icon");

		Span shares = new Span(person.shares());
		shares.addClassName("shares");

		actions.add(likeIcon, likes, commentIcon, comments, shareIcon, shares);

		description.add(header, post, actions);
		card.add(image, description);

		return card;
	}*/

	public static List<Person> persons = Arrays.asList( //
			new Person("https://randomuser.me/api/portraits/men/42.jpg", "John Smith", "May 8",
					"Lorem ipsum dolor sit amet, consectetur adipisicing elit. Blanditiis deserunt fugit mollitia officiis praesentium quibusdam quo reprehenderit sint. A aliquam aperiam at consequatur incidunt nisi possimus provident quaerat vitae voluptatem. Aut, autem dolorem in libero numquam odit pariatur quae tempore?",
					"10K", "22", "11"),
			new Person("https://randomuser.me/api/portraits/women/42.jpg", "Abagail Libbie", "May 3",
					"Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ab adipisci aut, dolor dolorum eveniet ipsa iusto labore, laboriosam pariatur quis repellat sed sequi soluta totam voluptatum. Amet autem consequatur cum deserunt dolore doloremque doloribus, ea, eaque eligendi, explicabo facere harum in ipsam iure nam neque pariatur quam quia quod reiciendis!",
					"1K", "500", "20"),
			new Person("https://randomuser.me/api/portraits/men/24.jpg", "Alberto Raya", "May 3",
					"Lorem ipsum dolor sit amet, consectetur adipisicing elit. A aliquid dolor doloremque ea, ipsam natus nostrum quas quos rem sequi? Accusamus ad animi architecto asperiores assumenda aut autem blanditiis consectetur corporis cumque delectus, earum eius enim explicabo fuga impedit magni maiores molestias mollitia necessitatibus neque nihil non nostrum nulla obcaecati odio pariatur placeat quidem quis quos rem unde ut veritatis. Eius eos incidunt inventore ipsum libero nobis repellat similique unde.",
					"1K", "500", "20"),
			new Person("https://randomuser.me/api/portraits/women/24.jpg", "Emmy Elsner", "Apr 22",
					"Lorem ipsum dolor sit amet, consectetur adipisicing elit. A aperiam consequuntur deserunt doloremque facere in inventore magnam molestias natus necessitatibus, obcaecati optio, quibusdam quod repudiandae saepe sint veritatis! Asperiores dignissimos, eligendi esse placeat similique voluptas?",
					"1K", "500", "20"),
			new Person("https://randomuser.me/api/portraits/men/76.jpg", "Alf Huncoot", "Apr 21",
					"Lorem ipsum dolor sit amet, consectetur adipisicing elit. Provident, qui?",
					"1K", "500", "20"),
			new Person("https://randomuser.me/api/portraits/women/76.jpg", "Lidmila Vilensky", "Apr 17",
					"Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ab, accusantium adipisci aliquid animi aut autem consequuntur cum dicta dolor dolore dolorem earum eius error est eum excepturi illo in maiores molestiae nesciunt nihil non obcaecati officia omnis quas, quidem quod repellat reprehenderit sapiente sed sequi similique temporibus tenetur ullam velit veniam vitae! Accusantium asperiores beatae debitis dolorem, doloribus ducimus et ipsa ipsum laudantium, libero nobis quisquam repellendus. Ab nesciunt pariatur quod recusandae reiciendis ut? Beatae doloremque et fuga incidunt ipsa ipsam quam saepe sequi voluptas? Cum nam quas saepe. Ad aliquid assumenda excepturi exercitationem, harum itaque iure maiores molestiae molestias mollitia, neque officia sequi voluptatum. Assumenda at, aut enim illo molestias nam nihil optio quam rem sint soluta velit, vitae.",
					"1K", "500", "20"),
			new Person("https://randomuser.me/api/portraits/men/94.jpg", "Jarrett Cawsey", "Apr 17",
					"In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document without relying on meaningful content (also called greeking).",
					"1K", "500", "20"),
			new Person("https://randomuser.me/api/portraits/women/94.jpg", "Tania Perfilyeva", "Mar 8",
					"In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document without relying on meaningful content (also called greeking).",
					"1K", "500", "6"),
			new Person("https://randomuser.me/api/portraits/men/16.jpg", "Ivan Polo", "Mar 5",
					"In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document without relying on meaningful content (also called greeking).",
					"1K", "500", "2"),
			new Person("https://randomuser.me/api/portraits/women/16.jpg", "Emelda Scandroot", "Mar 5",
					"Lorem ipsum dolor sit amet, consectetur adipisicing elit. Consequatur debitis excepturi illum, impedit magni quia quidem voluptates! Amet animi aperiam fugit hic laudantium modi nam, optio quibusdam quos, ut vel!",
					"1K", "500", "0"),
			new Person("https://randomuser.me/api/portraits/men/67.jpg", "Marcos Sá", "Mar 4",
					"Lorem ipsum dolor sit amet, consectetur adipisicing elit. Assumenda beatae dolore dolorum illum nihil non perferendis quos tempore ut voluptatum.",
					"1K", "500", "20"),
			new Person("https://randomuser.me/api/portraits/women/67.jpg", "Jacqueline Asong", "Mar 2",
					"In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document without relying on meaningful content (also called greeking).",
					"1K", "500", "20")

	);
}
