package service;

import java.util.List;

/**
 * class to convert one type to another.
 * 
 * @author ABULIMITI_Alafate (DI4-SI2), LI_Yuanyuan (DI4-SI2)
 *
 */
public class ConvertService {
	/**
	 * convert a list of string to string[]
	 * 
	 * @param lists
	 *            : list of string
	 * @return string[]
	 */
	public String[] convertTo(List<String> lists) {
		if (lists == null || lists.size() < 0) {
			return null;
		} else {
			String[] result = lists.toArray(new String[lists.size()]);
			return result;
		}
	}
}
