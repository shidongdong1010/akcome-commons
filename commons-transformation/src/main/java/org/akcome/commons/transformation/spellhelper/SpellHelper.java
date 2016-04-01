package org.akcome.commons.transformation.spellhelper;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/** 
 *  
 * pinyin4j工具类. <br> 
 *  
 * @author peng_wang
 * @date 2015年11月23日 
 */  
public class SpellHelper {
	/**
	 * 将中文转换为英文
	 * @param name
	 * @return
	 * @throws BadHanyuPinyinOutputFormatCombination
	 */
	public static String getEname(String name)
			throws BadHanyuPinyinOutputFormatCombination {
		HanyuPinyinOutputFormat pyFormat = new HanyuPinyinOutputFormat();
		pyFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		pyFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		pyFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
		return PinyinHelper.toHanyuPinyinString(name, pyFormat, "");
	}
	
	/**
	 * 姓、名的第一个字母需要为大写
	 * @param name
	 * @return
	 * @throws BadHanyuPinyinOutputFormatCombination
	 */
	public static String getUpEname(String name)
			throws BadHanyuPinyinOutputFormatCombination {
		char[] strs = name.toCharArray();
		String newname = null;
		// 名字的长度
		if (strs.length == 2) {
			newname = toUpCase(getEname("" + strs[0])) + " "
					+ toUpCase(getEname("" + strs[1]));
		} else if (strs.length == 3) {
			newname = toUpCase(getEname("" + strs[0])) + " "
					+ toUpCase(getEname("" + strs[1] + strs[2]));
		} else if (strs.length == 4) {
			newname = toUpCase(getEname("" + strs[0] + strs[1])) + " "
					+ toUpCase(getEname("" + strs[2] + strs[3]));
		} else {
			newname = toUpCase(getEname(name));
		}
		return newname;
	}

	/**
	 * 首字母大写
	 * @param str
	 * @return
	 */
	private static String toUpCase(String str) {
		StringBuffer newstr = new StringBuffer();
		newstr.append((str.substring(0, 1)).toUpperCase()).append(
				str.substring(1, str.length()));
		return newstr.toString();
	}

	/**
	 * 将汉字转换为全拼
	 * 
	 * @param src
	 * @return String
	 */
	public static String getPinYin(String src) {
		char[] t1 = null;
		t1 = src.toCharArray();
		String[] t2 = new String[t1.length];
		// 设置汉字拼音输出的格式
		HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
		t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		t3.setVCharType(HanyuPinyinVCharType.WITH_V);
		String t4 = "";
		int t0 = t1.length;
		try {
			for (int i = 0; i < t0; i++) {
				// 判断能否为汉字字符
				if (Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
					t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);// 将汉字的几种全拼都存到t2数组中
					t4 += t2[0];// 取出该汉字全拼的第一种读音并连接到字符串t4后
				} else {
					// 如果不是汉字字符，间接取出字符并连接到字符串t4后
					t4 += Character.toString(t1[i]);
				}
			}
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			e.printStackTrace();
		}
		return t4;
	}

	/**
	 * 将字符串转换成ASCII码
	 * 
	 * @param cnStr
	 * @return String
	 */
	public static String getCnASCII(String cnStr) {
		StringBuffer strBuf = new StringBuffer();
		// 将字符串转换成字节序列
		byte[] bGBK = cnStr.getBytes();
		for (int i = 0; i < bGBK.length; i++) {
			// 将每个字符转换成ASCII码
			strBuf.append(Integer.toHexString(bGBK[i] & 0xff));
		}
		return strBuf.toString();
	}
}
