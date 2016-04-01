package org.akcome.commons.transformation.amount;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * AmountUtils
 * 
 * @description 金额元分之间转换工具类
 * @author peng_wang
 * @2015-11-24
 */
public class AmountUtils {
	/** 金额为分的格式 */
	public static final String CURRENCY_FEN_REGEX = "\\-?[0-9]+";
	private static final Pattern AMOUNT_PATTERN = Pattern
			.compile("^(0|[1-9]\\d{0,11})\\.(\\d\\d)$"); // 不考虑分隔符的正确性
	private static final char[] RMB_NUMS = "零壹贰叁肆伍陆柒捌玖".toCharArray();
	private static final String[] UNITS = { "元", "角", "分", "整" };
	private static final String[] U1 = { "", "拾", "佰", "仟" };
	private static final String[] U2 = { "", "万", "亿" };

	/**
	 * 将分为单位的转换为元并返回金额格式的字符串 （除100）
	 * 
	 * @param amount
	 * @return
	 * @throws Exception
	 */
	public static String changeF2Y(Long amount) throws Exception {
		if (!amount.toString().matches(CURRENCY_FEN_REGEX)) {
			throw new Exception("金额格式有误");
		}
		int flag = 0;
		String amString = amount.toString();
		if (amString.charAt(0) == '-') {
			flag = 1;
			amString = amString.substring(1);
		}
		StringBuffer result = new StringBuffer();
		if (amString.length() == 1) {
			result.append("0.0").append(amString);
		} else if (amString.length() == 2) {
			result.append("0.").append(amString);
		} else {
			String intString = amString.substring(0, amString.length() - 2);
			for (int i = 1; i <= intString.length(); i++) {
				if ((i - 1) % 3 == 0 && i != 1) {
					result.append(",");
				}
				result.append(intString.substring(intString.length() - i,
						intString.length() - i + 1));
			}
			result.reverse().append(".")
					.append(amString.substring(amString.length() - 2));
		}
		if (flag == 1) {
			return "-" + result.toString();
		} else {
			return result.toString();
		}
	}

	/**
	 * 将分为单位的转换为元 （除100）
	 * 
	 * @param amount
	 * @return
	 * @throws Exception
	 */
	public static String changeF2Y(String amount) throws Exception {
		if (!amount.matches(CURRENCY_FEN_REGEX)) {
			throw new Exception("金额格式有误");
		}
		return BigDecimal.valueOf(Long.valueOf(amount))
				.divide(new BigDecimal(100)).toString();
	}

	/**
	 * 将元为单位的转换为分 （乘100）
	 * 
	 * @param amount
	 * @return
	 */
	public static String changeY2F(Long amount) {
		return BigDecimal.valueOf(amount).multiply(new BigDecimal(100))
				.toString();
	}

	/**
	 * 将元为单位的转换为分 替换小数点，支持以逗号区分的金额
	 * 
	 * @param amount
	 * @return
	 */
	public static String changeY2F(String amount) {
		String currency = amount.replaceAll("\\$|\\￥|\\,", ""); // 处理包含, ￥//
																// 或者$的金额
		int index = currency.indexOf(".");
		int length = currency.length();
		Long amLong = 0l;
		if (index == -1) {
			amLong = Long.valueOf(currency + "00");
		} else if (length - index >= 3) {
			amLong = Long.valueOf((currency.substring(0, index + 3)).replace(
					".", ""));
		} else if (length - index == 2) {
			amLong = Long.valueOf((currency.substring(0, index + 2)).replace(
					".", "") + 0);
		} else {
			amLong = Long.valueOf((currency.substring(0, index + 1)).replace(
					".", "") + "00");
		}
		return amLong.toString();
	}

	/**
	 * 将金额（整数部分等于或少于12位，小数部分2位）转换为中文大写形式.
	 * 
	 * @param amount
	 *            金额数字
	 * @return 中文大写
	 * @throws IllegalArgumentException
	 */
	public static String convert(String amount) throws IllegalArgumentException {
		// 去掉分隔符
		amount = amount.replace(",", "");
		// 验证金额正确性
		if (amount.equals("0.00")) {
			throw new IllegalArgumentException("金额不能为零.");
		}
		Matcher matcher = AMOUNT_PATTERN.matcher(amount);
		if (!matcher.find()) {
			throw new IllegalArgumentException("输入金额有误.");
		}

		String integer = matcher.group(1); // 整数部分
		String fraction = matcher.group(2); // 小数部分
		String result = "";
		if (!integer.equals("0")) {
			result += integer2rmb(integer) + UNITS[0]; // 整数部分
		}
		if (fraction.equals("00")) {
			result += UNITS[3]; // 添加[整]
		} else if (fraction.startsWith("0") && integer.equals("0")) {
			result += fraction2rmb(fraction).substring(1); // 去掉分前面的[零]
		} else {
			result += fraction2rmb(fraction); // 小数部分
		}
		return result;
	}

	/**
	 * 将金额小数部分转换为中文大写
	 * @param fraction
	 * @return String
	 */
	private static String fraction2rmb(String fraction) {
		char jiao = fraction.charAt(0); // 角
		char fen = fraction.charAt(1); // 分
		return (RMB_NUMS[jiao - '0'] + (jiao > '0' ? UNITS[1] : ""))
				+ (fen > '0' ? RMB_NUMS[fen - '0'] + UNITS[2] : "");
	}
	
	/**
	 * 将金额整数部分转换为中文大写
	 * @param integer
	 * @return String
	 */
	private static String integer2rmb(String integer) {
		StringBuilder buffer = new StringBuilder();
		// 从个位数开始转换
		int i, j;
		for (i = integer.length() - 1, j = 0; i >= 0; i--, j++) {
			char n = integer.charAt(i);
			if (n == '0') {
				// 当n是0且n的右边一位不是0时，插入[零]
				if (i < integer.length() - 1 && integer.charAt(i + 1) != '0') {
					buffer.append(RMB_NUMS[0]);
				}
				// 插入[万]或者[亿]
				if (j % 4 == 0) {
					if (i > 0 && integer.charAt(i - 1) != '0' || i > 1
							&& integer.charAt(i - 2) != '0' || i > 2
							&& integer.charAt(i - 3) != '0') {
						buffer.append(U2[j / 4]);
					}
				}
			} else {
				if (j % 4 == 0) {
					buffer.append(U2[j / 4]); // 插入[万]或者[亿]
				}
				buffer.append(U1[j % 4]); // 插入[拾]、[佰]或[仟]
				buffer.append(RMB_NUMS[n - '0']); // 插入数字
			}
		}
		return buffer.reverse().toString();
	}

	/**
	 * 对金额的格式调整到分
	 * 
	 * @param money
	 * @return
	 */
	public static String moneyFormat(String money) {// 23->23.00
		StringBuffer sb = new StringBuffer();
		if (money == null) {
			return "0.00";
		}
		int index = money.indexOf(".");
		if (index == -1) {
			return money + ".00";
		} else {
			String s0 = money.substring(0, index);// 整数部分
			String s1 = money.substring(index + 1);// 小数部分
			if (s1.length() == 1) {// 小数点后一位
				s1 = s1 + "0";
			} else if (s1.length() > 2) {// 如果超过3位小数，截取2位就可以了
				s1 = s1.substring(0, 2);
			}
			sb.append(s0);
			sb.append(".");
			sb.append(s1);
		}
		return sb.toString();
	}
}
