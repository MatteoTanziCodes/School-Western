//import java.awt.Color;
//
//public class MagnifyOperation implements ImageOperation {
//
//	// Defines the height and width of the image
//	public Color[][] doOperation(Color[][] imageArray) {
//
//		int numOfRows = imageArray.length;
//		int numOfColumns = imageArray[0].length;
//
//		// Double the height and length 
//		Color[][] array = new Color[numOfRows * 2][numOfColumns * 2];
//
//		// Loops through all of the pixels positions
//		for (int i = 0; i < numOfRows; i++) {
//			for (int j = 0; j < numOfColumns; j++) {
//
//				//Calculates the new positions of the pixels of the enlarged image
//				array[i*2][j*2] = imageArray[i][j];
//				array[i*2][(j*2)+1] = imageArray[i][j];
//				array[(i*2)+1][j*2] = imageArray[i][j];
//				array[(i*2)+1][(j*2)+1] = imageArray[i][j];
//			}
//		}
//		return array;
//
//	}
//
//}


import java.awt.Color;

public class MagnifyOperation implements ImageOperation {

	public Color[][] doOperation(Color[][] imageArray) {
		int numOfRows = imageArray.length;
		int numOfColumns = imageArray[0].length;

		Color[][] result = new Color[numOfRows * 2][numOfColumns * 2];

		for (int i = 0; i < numOfRows; i++) {
			for (int j = 0; j < numOfColumns; j++) {
				
				/*
				 * When an image is magnified, the resulting positions of the pixel are *2, *2+1 row and column each
				 * and together
				 */
				result[i * 2][j * 2] = imageArray[i][j];
				result[i * 2][(j * 2) + 1] = imageArray[i][j];
				result[(i * 2) + 1][j * 2] = imageArray[i][j];
				result[(i * 2) + 1][(j * 2) + 1] = imageArray[i][j];
			}
		}

		return result;
	}
}