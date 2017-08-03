package ru.job4j;

/**
	* Iclude.
	*
	*@author smikhailov
	*@since 03.08.2017
	*@version 1
	*/
public class Include {

	/**
	* Contains.
	*@param 
	*@return
	*@throws
	*/
    boolean contains(String origin, String sub) {
    	char[] originArray = stringToArray(origin);
    	char[] subArray = stringToArray(sub);
		int tmp = 0;
		int k = 0;
    	for (int i = 0; i < subArray.length; i++) {
    		for (int j = 0; j < originArray.length; j++) {
    			if (subArray[i] == originArray[j]) {

    				if (tmp == 0) {
    					k = j;
						tmp++;
    				} else if (j == k + 1){
						k = j;
						tmp++;
					} else {tmp = 0;}
    			}
    		}
    	}
    	if (tmp == subArray.length) return true;
    	else return false;
    }

	/**
	* StringToArray.
	*@param 
	*@return
	*@throws
	*/
	char[] stringToArray(String str) {
		
		char[] charArray = str.toCharArray();
		return charArray;
	}

}