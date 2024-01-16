package org.socialnet2.backend.services;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.socialnet2.backend.dtos.ScraperResponseDTO;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ScrapeService {
	// TODO everything (service in development)
	private static final Logger logger = LoggerFactory.getLogger(ScrapeService.class);

	public ScraperResponseDTO getWebpageInfo(String url) throws IOException { // http://localhost:8080/scrap
		logger.info("Received url: " + url);

		/*String url = "www.intel.com";
		String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/105.0.0.0 Safari/537.36";

		try {
			Document doc = Jsoup.connect(url)
					.userAgent(userAgent)
					.get();
		} catch (IOException e) {
			e.printStackTrace();
		}*/

		long start = System.currentTimeMillis();
		Document document;
		try {
			document = Jsoup.connect(url).get();
		} catch (IOException exception) {
			throw new IOException("Unable to parse " + url);
		}
		long stop = System.currentTimeMillis();
		logger.debug("Loading document took : " + (stop - start) + "ms");

		String title = document.title();
		String description = document.select("meta[name=description]").attr("content");
		String image = document.select("meta[property=og:image]").attr("content");

		if (title.isEmpty()) {
			title = document.select("meta[property=og:title]").attr("content");
			if (title.isEmpty()) {
				title = document.select("meta[name=twitter:title]").attr("content");
				if (title.isEmpty())
					title = document.select("meta[property=og:site_name]").attr("content");
			}
		}
		if (description.isEmpty()) {
			description = document.select("meta[property=og:description]").attr("content");
			if (description.isEmpty())
				description = document.select("meta[name=twitter:description]").attr("content");
		}
		// TODO consider improve search for an image
		logger.debug("og:image: " + image);
		if (image.isEmpty()) {
			image = document.select("meta[name=twitter:image]").attr("content");
			logger.debug("twitter:image: " + image);
			if (image.isEmpty()) {
				image = document.select("link[rel=apple-touch-icon]").attr("href");
				logger.debug("link[rel=apple-touch-icon]: " + image);
				if (image.isEmpty()) {
					image = document.select("link[rel=icon]").attr("href");
					logger.debug("link[rel=icon]: " + image);
				}
			}
		}
		if (!image.isEmpty() && !image.startsWith("http"))
			image = url + image;

		// TODO update obtaining url
		url = document.select("meta[property=og:url]").attr("content");

		logger.debug("title: " + title);
		logger.debug("description: " + description);
		logger.debug("image: " + image);
		logger.debug("url: " + url);
		return new ScraperResponseDTO(title, description, image, url);
	}
}
