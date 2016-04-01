package org.akcome.commons.transformation.math;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.akcome.commons.transformation.collection.CollectionUtil;
import org.akcome.commons.transformation.string.StringUtil;

/** 
 * 数学运算辅助类。 
 *  
 * @author peng_wang 
 * @date 2015年11月24日 
 */  
public class MathUtil {
	/** 
     * 功能：将字符串转换为BigDecimal，一般用于数字运算时。 
     *  
     * @author peng_wang 
     * @date 2015年11月24日 
     * @param str 
     *            字符串 
     * @return BigDecimal,str为empty时返回null。 
     */  
    public static BigDecimal toBigDecimal(String str) {  
        if (StringUtil.isEmpty(str)) {  
            return null;  
        }  
        return new BigDecimal(str);  
    }  
  
    /** 
     * 功能：将字符串抓换为double，如果失败返回默认值。 
     *  
     * @author peng_wang 
     * @date 2015年11月24日 
     * @param str 
     *            字符串 
     * @param defaultValue 
     *            失败时返回的默认值 
     * @return double 
     */  
    public static double toDouble(String str, double defaultValue) {  
        if (str == null) {  
            return defaultValue;  
        }  
        try {  
            return Double.parseDouble(str);  
        } catch (NumberFormatException nfe) {  
            return defaultValue;  
        }  
    }  
  
    /** 
     * 功能：将字符串抓换为float，如果失败返回默认值。 
     *  
     * @author peng_wang 
     * @date 2015年11月24日 
     * @param str 
     *            字符串 
     * @param defaultValue 
     *            失败时返回的默认值 
     * @return float 
     */  
    public static float toFloat(String str, float defaultValue) {  
        if (str == null) {  
            return defaultValue;  
        }  
        try {  
            return Float.parseFloat(str);  
        } catch (NumberFormatException nfe) {  
            return defaultValue;  
        }  
    }  
  
    /** 
     * 功能：将字符串抓换为long，如果失败返回默认值。 
     *  
     * @author peng_wang 
     * @date 2015年11月24日 
     * @param str 
     *            字符串 
     * @param defaultValue 
     *            失败时返回的默认值 
     * @return long 
     */  
    public static long toLong(String str, long defaultValue) {  
        if (str == null) {  
            return defaultValue;  
        }  
        try {  
            return Long.parseLong(str);  
        } catch (NumberFormatException nfe) {  
            return defaultValue;  
        }  
    }  
  
    /** 
     * 功能：将字符串抓换为int，如果失败返回默认值。 
     *  
     * @author peng_wang 
     * @date 2015年11月24日 
     * @param str 
     *            字符串 
     * @param defaultValue 
     *            失败时返回的默认值 
     * @return int 
     */  
    public static int toInt(String str, int defaultValue) {  
        if (str == null) {  
            return defaultValue;  
        }  
        try {  
            return Integer.parseInt(str);  
        } catch (NumberFormatException nfe) {  
            return defaultValue;  
        }  
    }  
  
    /** 
     * <p> 
     * 得到两个 <code>double</code>值中最大的一个. 
     * </p> 
     *  
     * @param a 
     *            值 1 
     * @param b 
     *            值 2 
     * @return 最大的值 
     * @author peng_wang 
     * @date 2015年11月24日 
     */  
    public static float getMax(float a, float b) {  
        if (Float.isNaN(a)) {  
            return b;  
        } else if (Float.isNaN(b)) {  
            return a;  
        } else {  
            return Math.max(a, b);  
        }  
    }  
  
    /** 
     * <p> 
     * 得到数组中最大的一个. 
     * </p> 
     *  
     * @param array 
     *            数组不能为null，也不能为空。 
     * @return 得到数组中最大的一个. 
     * @throws IllegalArgumentException 
     *             如果 <code>数组</code> 是 <code>null</code> 
     * @throws IllegalArgumentException 
     *             如果 <code>数组</code>是空 
     * @author peng_wang 
     * @date 2015年11月24日 
     */  
    public static float getMax(float[] array) {  
        if (array == null) {  
            throw new IllegalArgumentException("The Array must not be null");  
        } else if (array.length == 0) {  
            throw new IllegalArgumentException("Array cannot be empty.");  
        }  
        float max = array[0];  
        for (int j = 1; j < array.length; j++) {  
            max = getMax(array[j], max);  
        }  
        return max;  
    }  
  
    /** 
     * <p> 
     * 得到数组中最大的一个. 
     * </p> 
     *  
     * @param array 
     *            数组不能为null，也不能为空。 
     * @return 得到数组中最大的一个. 
     * @throws IllegalArgumentException 
     *             如果 <code>数组</code> 是 <code>null</code> 
     * @throws IllegalArgumentException 
     *             如果 <code>数组</code>是空 
     * @author peng_wang 
     * @date 2015年11月24日 
     */  
    public static double getMax(double[] array) {  
        if (array == null) {  
            throw new IllegalArgumentException("The Array must not be null");  
        } else if (array.length == 0) {  
            throw new IllegalArgumentException("Array cannot be empty.");  
        }  
        double max = array[0];  
        for (int j = 1; j < array.length; j++) {  
            max = getMax(array[j], max);  
        }  
  
        return max;  
    }  
  
    /** 
     * <p> 
     * 得到两个 <code>double</code>值中最大的一个. 
     * </p> 
     *  
     * @param a 
     *            值 1 
     * @param b 
     *            值 2 
     * @return 最大的值 
     * @author peng_wang 
     * @date 2015年11月24日 
     * */  
    public static double getMax(double a, double b) {  
        if (Double.isNaN(a)) {  
            return b;  
        } else if (Double.isNaN(b)) {  
            return a;  
        } else {  
            return Math.max(a, b);  
        }  
    }  
  
    /** 
     * <p> 
     * 得到两个float中最小的一个。 
     * </p> 
     *  
     * @param a 
     *            值 1 
     * @param b 
     *            值 2 
     * @return double值最小的 
     * @author peng_wang 
     * @date 2015年11月24日 
     */  
    public static float getMin(float a, float b) {  
        if (Float.isNaN(a)) {  
            return b;  
        } else if (Float.isNaN(b)) {  
            return a;  
        } else {  
            return Math.min(a, b);  
        }  
    }  
  
    /** 
     * <p> 
     * 返回数组中最小的数值。 
     * </p> 
     *  
     * @param array 
     *            数组不能为null，也不能为空。 
     * @return 数组里面最小的float 
     * @throws IllegalArgumentException 
     *             如果<code>数组</code>是<code>null</code> 
     * @throws IllegalArgumentException 
     *             如果<code>数组</code>是空 
     * @author peng_wang 
     * @date 2015年11月24日 
     */  
    public static float getMin(float[] array) {  
        if (array == null) {  
            throw new IllegalArgumentException("数组不能为null。");  
        } else if (array.length == 0) {  
            throw new IllegalArgumentException("数组不能为空。");  
        }  
  
        float min = array[0];  
        for (int i = 1; i < array.length; i++) {  
            min = getMin(array[i], min);  
        }  
  
        return min;  
    }  
  
    /** 
     * <p> 
     * 返回数组中最小的double。 
     * </p> 
     *  
     * @param array 
     *            数组不能为null，也不能为空。 
     * @return 数组里面最小的double 
     * @throws IllegalArgumentException 
     *             如果<code>数组</code>是<code>null</code> 
     * @throws IllegalArgumentException 
     *             如果<code>数组</code>是空 
     * @author peng_wang 
     * @date 2015年11月24日 
     */  
    public static double getMin(double[] array) {  
        if (array == null) {  
            throw new IllegalArgumentException("数组不能为null。");  
        } else if (array.length == 0) {  
            throw new IllegalArgumentException("数组不能为空。");  
        }  
        double min = array[0];  
        for (int i = 1; i < array.length; i++) {  
            min = getMin(array[i], min);  
        }  
        return min;  
    }  
  
    /** 
     * <p> 
     * 得到两个double中最小的一个。 
     * </p> 
     *  
     * @param a 
     *            值 1 
     * @param b 
     *            值 2 
     * @return double值最小的 
     * @author peng_wang 
     * @date 2015年11月24日 
     */  
    public static double getMin(double a, double b) {  
        if (Double.isNaN(a)) {  
            return b;  
        } else if (Double.isNaN(b)) {  
            return a;  
        } else {  
            return Math.min(a, b);  
        }  
    }  
  
    /** 
     * 返回两个double的商 first除以second。 
     *  
     * @param first 
     *            第一个double 
     * @param second 
     *            第二个double 
     * @return double 
     * @author peng_wang 
     * @date 2015年11月24日 
     */  
    public static double divideDouble(double first, double second) {  
        BigDecimal b1 = new BigDecimal(first);  
        BigDecimal b2 = new BigDecimal(second);  
        return b1.divide(b2).doubleValue();  
    }  
  
    /** 
     * 返回两个double的乘积 first*second。 
     *  
     * @param first 
     *            第一个double 
     * @param second 
     *            第二个double 
     * @return double 
     * @author peng_wang 
     * @date 2015年11月24日 
     */  
    public static double multiplyDouble(double first, double second) {  
        BigDecimal b1 = new BigDecimal(first);  
        BigDecimal b2 = new BigDecimal(second);  
        return b1.multiply(b2).doubleValue();  
    }  
  
    /** 
     * 返回两个double的差值 first-second。 
     *  
     * @param first 
     *            第一个double 
     * @param second 
     *            第二个double 
     * @return double 
     * @author peng_wang 
     * @date 2015年11月24日 
     */  
    public static double subtractDouble(double first, double second) {  
        BigDecimal b1 = new BigDecimal(first);  
        BigDecimal b2 = new BigDecimal(second);  
        return b1.subtract(b2).doubleValue();  
    }  
  
    /** 
     * 返回两个double的和值 first+second。 
     *  
     * @param first 
     *            第一个double 
     * @param second 
     *            第二个double 
     * @return double 
     * @author peng_wang 
     * @date 2015年11月24日 
     */  
    public static double sumDouble(double first, double second) {  
        BigDecimal b1 = new BigDecimal(first);  
        BigDecimal b2 = new BigDecimal(second);  
        return b1.add(b2).doubleValue();  
    }  
  
    /** 
     * 格式化double指定位数小数。例如将11.123格式化为11.1。 
     *  
     * @param value 
     *            原double数字。 
     * @param decimals 
     *            小数位数。 
     * @return 格式化后的double，注意为硬格式化不存在四舍五入。 
     * @author peng_wang 
     * @date 2015年11月24日 
     */  
    public static String formatDouble(double value, int decimals) {  
        String doubleStr = "" + value;  
        int index = doubleStr.indexOf(".") != -1 ? doubleStr.indexOf(".")  
                : doubleStr.indexOf(",");  
        if (index == -1)  
            return doubleStr;  
        if (decimals == 0) {  
            return doubleStr.substring(0, index);  
        }  
        int len = index + decimals + 1;  
        if (len >= doubleStr.length())  
            len = doubleStr.length();  
        double d = Double.parseDouble(doubleStr.substring(0, len));  
        return String.valueOf(d);  
    }  
  
    /** 
     * 生成一个指定位数的随机数，并将其转换为字符串作为函数的返回值。 
     *  
     * @param numberLength 
     *            随机数的位数。 
     * @return String 注意随机数可能以0开头。 
     * @author peng_wang 
     * @date 2015年11月24日 
     */  
    public static String randomNumber(int numberLength) {  
        // 记录生成的每一位随机数  
        StringBuffer sb = new StringBuffer();  
        for (int i = 0; i < numberLength; i++) {  
            // 每次生成一位,随机生成一个0-10之间的随机数,不含10。  
            Double ranDouble = Math.floor(Math.random() * 10);  
            sb.append(ranDouble.intValue());  
        }  
        return sb.toString();  
    }  
  
    /** 
     * 功能：生成一个在最大数和最小数之间的随机数。会出现最小数，但不会出现最大数。 
     *  
     * @author peng_wang 
     * @date 2015年11月24日 
     * @param minNum 
     *            最小数 
     * @param maxNum 
     *            最大数 
     * @return int 
     */  
    public static int randomNumber(int minNum, int maxNum) {  
        if (maxNum <= minNum) {  
            throw new RuntimeException("maxNum必须大于minNum!");  
        }  
        // 计算出来差值  
        int subtract = maxNum - minNum;  
        Double ranDouble = Math.floor(Math.random() * subtract);  
        return ranDouble.intValue() + minNum;  
    }  
  
    /** 
     * 功能：生成一个在最大数和最小数之间的随机数。会出现最小数，但不会出现最大数。<br/> 
     * 但不随机notin数组中指定的数字， 如果可随机的范围较小，可能会一直随机不到，或者随机的很慢。 
     *  
     * @author peng_wang 
     * @date 2015年11月24日 
     * @param minNum 
     *            最小数 
     * @param maxNum 
     *            最大数 
     * @param notin 
     *            不随机数组这些数字 
     * @return int 
     */  
    public static int randomNumber(int minNum, int maxNum, Integer[] notin) {  
        if (notin.length >= (maxNum - minNum)) {  
            throw new RuntimeException("notin数组的元素已经把可以随机的都排除了，无法得到随机数!");  
        }  
        while (true) {  
            int num = randomNumber(minNum, maxNum);  
            if (!CollectionUtil.arrayContain(notin, num)) {
                return num;  
            }  
        }  
    }  
    
	/**
	 * 功能：加法
	 * @param d1 （double）
	 * @param d2（double）
	 * @return double（两数之和）
	 */
	public static double add(double d1, double d2) {
		BigDecimal b1 = new BigDecimal(Double.toString(d1));
		BigDecimal b2 = new BigDecimal(Double.toString(d2));
		return b1.add(b2).doubleValue();
	}
	
	/**
	 *功能： 减法
	 * @param d1 （double）
	 * @param d2  （double）
	 * @return double（两数之差）
	 */
	public static double sub(double d1, double d2) {
		BigDecimal b1 = new BigDecimal(Double.toString(d1));
		BigDecimal b2 = new BigDecimal(Double.toString(d2));
		return b1.subtract(b2).doubleValue();
	}
	
	/**
	 * 功能：乘法
	 * @param d1 （double）
	 * @param d2 （double）
	 * @return  double （两数之积）
	 */
	public static double mul(double d1, double d2) {
		BigDecimal b1 = new BigDecimal(Double.toString(d1));
		BigDecimal b2 = new BigDecimal(Double.toString(d2));
		return b1.multiply(b2).doubleValue();
	}
	
	/**
	 * 功能：除法
	 * @param d1 （double）
	 * @param d2 （double）
	 * @param scale  保留小数位个数
	 * @return double  (两数之商)
	 */
	public static double div(double d1, double d2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
		BigDecimal b1 = new BigDecimal(Double.toString(d1));
		BigDecimal b2 = new BigDecimal(Double.toString(d2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	/**
	 * 功能：两个浮点数对比
	 * @param d1 （double）
	 * @param d2 （double）
	 * @return  boolean
	 */
	public static boolean equal(double d1, double d2) {
		BigDecimal b1 = new BigDecimal(Double.toString(d1));
		BigDecimal b2 = new BigDecimal(Double.toString(d2));
		return b1.equals(b2);
	}
	
	/**
	 * 功能：小数位四舍五入处理
	 * @param d  （double）
	 * @param scale (int)  保留小数位个数
	 * @return  double
	 */
	public static double round(double d, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(d));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	/**
	 * 功能： 比较是否相同
	 * @param a  （double）
	 * @param b   （double）
	 * @return  boolean
	 */
	public static boolean equals(double a, double b){
		BigDecimal data1 = new BigDecimal(a); 
		BigDecimal data2 = new BigDecimal(b); 
		return data1.compareTo(data2) == 0 ? true : false;
	}
	
	/**
	 * 功能： 判读字符串是否是中文
	 * @param str  （String）
	 * @return  boolean
	 */
	public static boolean isChinese(String str){
		Pattern p = Pattern.compile("[\u4e00-\u9fa5]+$");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
	}
}
