package ru.job4j;

/**
 * Triangle.
 *
 *@author smikhailov
 *@since 24.08.2017
 *@version 1
 */
public class Triangle implements Shape{
	public String pic(){
		StringBuilder sb = new StringBuilder();
	    sb.append("  *  ");
	    sb.append(" *** ");
	    sb.append("*****");
	    return sb.toString();
	}
}