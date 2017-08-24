package ru.job4j;

/**
 * Square.
 *
 *@author smikhailov
 *@since 24.08.2017
 *@version 1
 */
public class Square implements Shape{
	public String pic(){
		StringBuilder sb = new StringBuilder();
	    sb.append("****");
	    sb.append("****");
	    sb.append("****");
	    sb.append("****");
	    return sb.toString();
	}
}