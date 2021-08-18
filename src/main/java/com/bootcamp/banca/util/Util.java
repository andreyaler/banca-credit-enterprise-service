package com.bootcamp.banca.util;

import java.util.Random;

public class Util {

	public static String generateCreditNumber() {
		final String SAVINGS_ACCOUNT_PREFIX = "500-";
		Random random = new Random();
		return SAVINGS_ACCOUNT_PREFIX + random.nextInt(999999999);
	}
}