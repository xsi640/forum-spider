package com.suyang.spider;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.suyang.domain.WebSite;
import com.suyang.repository.WebSiteRepository;

public class WebSpider {

	private static Logger logger = LoggerFactory.getLogger(WebSpider.class);
	private boolean isStarted = false;

	private int start = 1;
	private int end = 1;
	private WebSite webSite;
	private WebSpiderService webSpiderService;
	private WebSiteRepository webSiteRepository;

	public WebSpider(WebSite webSite, WebSpiderService webSpiderService, WebSiteRepository webSiteRepository) {
		this.webSite = webSite;
		this.webSpiderService = webSpiderService;
		this.webSiteRepository = webSiteRepository;
	}
	
	public void start() throws Exception {
		if (isStarted) {
			logger.error("webSpider is busy!!");
			throw new Exception("webSpider is busy!!");
		}
		isStarted = true;

		try {
			if(start == end) {
				start = 1;
				end = 1;
			}
			String url = MessageFormat.format(webSite.getUrlTemplate(), 1);
			logger.info("get total url:"+url);
			end = webSpiderService.extractTotal(url, webSite.getTotalSelector());
			logger.info("total:"+end);
			for(int i = start; i <= end; i++) {
				url = MessageFormat.format(webSite.getUrlTemplate(), i);
				logger.info("get list url:"+url);
				List<String> lists = webSpiderService.extractLists(url, webSite.getListSelector());
				boolean isBreak = false;
				for(String s : lists) {
					if(webSpiderService.extractArticle(webSite.getId(), s, webSite.getTitleSelector(), webSite.getContentSelector())) {
						isBreak = true;
						break;
					}
				}
				if(isBreak) {
					break;
				}
			}
			webSite.setLastExtractTime(new Date());
			webSiteRepository.save(webSite);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		} finally {
			isStarted = false;
		}
	}
}
