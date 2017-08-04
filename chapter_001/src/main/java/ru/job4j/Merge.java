package ru.job4j;

/**
	* Merge.
	*
	*@author smikhailov
	*@since 03.08.2017
	*@version 1
	*/
public class Merge {

	/**
	* MergeArrays.
	*@param 
	*@return
	*@throws
	*/
    int[] mergeArrays(int[] mas1, int[] mas2) {
    	int[] result = new int[mas1.length + mas2.length];
    	int k = 0;
    	int i = 0;
    	int j = 0;

    	for( ;i < mas1.length && j < mas2.length; ) {
    		if (mas1[i] == mas2[j]) {
    			result[k++] = mas1[i++];
    			result[k++] = mas2[j++];
    		} else if (mas1[i] < mas2[j]) {
    			result[k++] = mas1[i++];
    		} else {
    			result[k++] = mas2[j++];
    		}
    	}
    	
    	if (i == mas1.length) {
    		for ( ;j < mas2.length; ){
    			result[k++] = mas2[j++];
    		}
    	}

    	if (j == mas2.length) {
    		for ( ;i < mas1.length; ){
    			result[k++] = mas1[i++];
    		}
    	}

    	return result; 
    }

}