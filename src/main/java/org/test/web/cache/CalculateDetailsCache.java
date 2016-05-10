package org.test.web.cache;

import java.util.LinkedList;
import java.util.List;

import org.test.web.entity.CalculateDetail;

public class CalculateDetailsCache {

	private static LinkedList<CalculateDetail> detailList = new LinkedList<CalculateDetail>();

	static {
		for (int i = 0; i < 100; i++) {
			CalculateDetail detail = new CalculateDetail();
			detail.setNum1(i);
			detail.setNum2(i);
			detail.setOpt(i);
			detail.setResult(Float.valueOf(i));
			CalculateDetailsCache.detailList.addFirst(detail);
		}
	}

	public static void addCalculateDetail(Integer num1, Integer num2, Integer opt, Float result) {
		CalculateDetail detail = new CalculateDetail();
		detail.setNum1(num1);
		detail.setNum2(num2);
		detail.setOpt(opt);
		detail.setResult(result);

		CalculateDetailsCache.detailList.addFirst(detail);
	}

	public static List<CalculateDetail> getDetailList() {
		return CalculateDetailsCache.detailList;
	}

}
