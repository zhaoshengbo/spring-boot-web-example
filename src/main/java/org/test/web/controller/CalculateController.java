package org.test.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;
import org.test.web.cache.CalculateDetailsCache;
import org.test.web.entity.CalculateDetail;
import org.test.web.service.CalculateService;

@Controller
public class CalculateController {

	@Autowired
	private CalculateService calculateService = null;

	@RequestMapping("calculate")
	public ModelAndView calculate(Integer num1, Integer num2, Integer opt) {
		ModelAndView mv = new ModelAndView("index");

		Float result = null;
		if ((num1 != null) && (num2 != null) && (opt != null)) {
			mv.addObject("ok", true);
			result = this.getCalculateService().calculate(num1, num2, opt);
		} else {
			mv.addObject("ok", false);
			mv.addObject("errMsg", this.getErrorMsg(num1, num2));
			result = Float.valueOf(-1);
		}
		mv.addObject("result", result);
		this.fillPageData(mv, 1);

		return mv;
	}

	@RequestMapping("topage")
	public ModelAndView toPage(Integer page) {
		ModelAndView mv = new ModelAndView("index");
		this.fillPageData(mv, page);

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

	@RequestMapping("nextpage")
	public ModelAndView nextPage(Integer oriPage) {
		return null;
	}

	@RequestMapping("lastpage")
	public ModelAndView lastPage(Integer oriPage) {
		return null;
	}

	@ExceptionHandler(value = { MethodArgumentTypeMismatchException.class })
	public ModelAndView exceptionHandler(MethodArgumentTypeMismatchException ex) {
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("detailList", CalculateDetailsCache.getDetailList());
		mv.addObject("ok", false);
		mv.addObject("errMsg", ex.getLocalizedMessage());

		return mv;
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

	private int getPageCount() {
		int detailCount = CalculateDetailsCache.getDetailList().size();

		return ((detailCount % 5) == 0) ? (detailCount / 5) : (detailCount / 5) + 1;
	}

	private String getErrorMsg(Integer num1, Integer num2) {
		StringBuilder errorMsg = new StringBuilder();
		if (num1 == null) {
			errorMsg.append("num1 ");
		}
		if (num2 == null) {
			errorMsg.append("num2 ");
		}
		errorMsg.append("can't be null");

		return errorMsg.toString();
	}

	private CalculateService getCalculateService() {
		return this.calculateService;
	}

}
