package com.suyang.spider;

import java.util.List;

public interface WebSpiderService {
	int extractTotal(String url, String selector);
	List<String> extractLists(String url, String selector);
	boolean extractArticle(long webSiteId, String url, String titleSelector, String contentSelector);
}
