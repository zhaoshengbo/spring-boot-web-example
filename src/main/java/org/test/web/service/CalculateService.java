package org.test.web.service;

import org.springframework.stereotype.Service;
import org.test.web.cache.CalculateDetailsCache;

@Service
public class CalculateService {

	public Float calculate(Integer num1, Integer num2, Integer opt) {
		Float result = null;
		switch (opt) {
		case 1: {
			result = (float) (num1 + num2);
			break;
		}
		case 2: {
			result = (float) (num1 - num2);
			break;
		}
		case 3: {
			result = (float) (num1 * num2);
			break;
		}
		case 4: {
			result = (float) num1 / num2;
			break;
		}
		default: {
			result = (float) -1;
		}
		}
		this.cacheCalculateDetail(num1, num2, opt, result);

		return result;
	}

	private void cacheCalculateDetail(Integer num1, Integer num2, Integer opt, Float result) {
		CalculateDetailsCache.addCalculateDetail(num1, num2, opt, result);
	}

}
