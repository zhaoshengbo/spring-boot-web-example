package org.test.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.test.web.cache.CalculateDetailsCache;
import org.test.web.entity.CalculateDetail;

@Controller
public class IndexController {

	@RequestMapping("/")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("index");
		this.fillPageData(mv, 1);

		return mv;
	}

	private void fillPageData(ModelAndView mv, Integer page) {
		int pageCount = this.getPageCount();
		List<Integer> pageList = this.getPageList(page, pageCount);
		mv.addObject("pageList", pageList);
		mv.addObject("currentPage", page);
		mv.addObject("detailCount", CalculateDetailsCache.getDetailList().size());
		if (page == pageCount) {
			mv.addObject("isLastPage", true);
		}
		if (page == 1) {
			mv.addObject("isFirstPage", true);
		}
		mv.addObject("ok", true);

		this.fillDetailList(mv, page);
	}

	private int getPageCount() {
		int detailCount = CalculateDetailsCache.getDetailList().size();

		return ((detailCount % 5) == 0) ? (detailCount / 5) : (detailCount / 5) + 1;
	}

	private List<Integer> getPageList(Integer currentPage, Integer pageCount) {
		int start = (((currentPage - 1) / 5) * 5) + 1;
		int end = start + 5;
		List<Integer> pageList = new ArrayList<Integer>();
		for (int i = start; (i < end) && (i <= pageCount); i++) {
			pageList.add(Integer.valueOf(i));
		}
		return pageList;
	}

	private void fillDetailList(ModelAndView mv, Integer page) {
		List<CalculateDetail> detailList = new ArrayList<CalculateDetail>();
		int dataCount = CalculateDetailsCache.getDetailList().size();
		int start = (page - 1) * 5;
		int end = start + 5;
		for (int i = start; (i < end) && (i < dataCount); i++) {
			detailList.add(CalculateDetailsCache.getDetailList().get(i));
		}
		mv.addObject("detailList", detailList);
	}
}
