package com.suyang.spider.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.poreader.common.HttpUtils;
import com.poreader.common.TypeParse;
import com.suyang.domain.Article;
import com.suyang.domain.Attchement;
import com.suyang.domain.enums.AttchementType;
import com.suyang.repository.ArticleRepository;
import com.suyang.repository.AttchementRepository;
import com.suyang.spider.WebSpiderService;

public class WebSpiderServiceImpl implements WebSpiderService {

	private static Logger logger = LoggerFactory.getLogger(WebSpiderServiceImpl.class);

	@Autowired
	private ArticleRepository articleRepository;
	@Autowired
	private AttchementRepository attchementRepository;

	@Override
	public int extractTotal(String url, String selector) {
		String content = HttpUtils.getContent(url, null, "GET");
		Document doc = Jsoup.parse(content);
		Elements elePage = doc.select(selector);
		String s = elePage.html();
		return TypeParse.toInt(s);
	}

	public List<String> extractLists(String url, String selector) {
		List<String> result = new ArrayList<>();
		String content = HttpUtils.getContent(url, null, "GET");
		Document doc = Jsoup.parse(content);
		Elements elePosts = doc.select(selector);
		for (Element ele : elePosts) {
			String href = ele.attr("href");
			// String title = ele.attr("title");
			result.add(href);
		}
		return result;
	}

	@Override
	public boolean extractArticle(long webSiteId, String url, String titleSelector, String contentSelector) {
		String path = getPath(url);
		if (articleRepository.countByUrl(path) > 0)
			return true;

		String html = HttpUtils.getContent(url, null, "POST");
		Document doc = Jsoup.parse(html);
		Elements eleTitle = doc.select(titleSelector);
		String title = eleTitle.text();
		logger.info("title:" + title);
		Elements eleContent = doc.select(contentSelector);
		String content = eleContent.html();
		logger.info("content:" + content);
		Elements eleImages = eleContent.select("img");

		Article article = new Article();
		article.setTitle(title);
		article.setUrl(path);
		article.setContent(content);
		article.setWebSiteId(webSiteId);
		articleRepository.save(article);

		for (Element ele : eleImages) {
			Attchement attchment = new Attchement();
			attchment.setAttchementType(AttchementType.Photo);
			String imgTitle = ele.attr("title");
			logger.info("img title:" + imgTitle);
			String imgSrc = ele.attr("src");
			logger.info("img src:" + imgSrc);
			attchment.setName(imgTitle);
			attchment.setData(HttpUtils.getData(imgSrc, null, "GET"));
			attchementRepository.save(attchment);
		}
		return false;
	}

	private String getPath(String url) {
		try {
			URI uri = new URI(url);
			return uri.getPath();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return url;
	}

}
