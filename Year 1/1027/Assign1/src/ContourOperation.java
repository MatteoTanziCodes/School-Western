//import java.awt.Color;
//
//public class ContourOperation implements ImageOperation {
//
//	//Defines the number of rows and columns based on position 
//	public Color[][] doOperation(Color[][] imageArray) {
//		int numOfRows = imageArray.length - 1;
//		int numOfColumns = imageArray[0].length - 1;
//		boolean check = false;
//
//		Color[][] result = new Color[numOfRows+1][numOfColumns+1];
//		
//		//Loops through all of the pixels positions
//		for (int i = 0; i < numOfRows+1; i++)
//			for (int j = 0; j < numOfColumns+1; j++) {
//
//				//Check number of neighbors 
//				int neighbours = numOfNeighbours(i, j,numOfRows,numOfColumns);
//				
//				if (neighbours == 3) {
//					check = threePixels(imageArray, i, j,numOfRows,numOfColumns);
//				}
//				else if (neighbours == 5) {
//					check = fivePixels(imageArray, i, j,numOfRows,numOfColumns);
//				}
//				else {
//					check = eightPixels(imageArray, i, j);
//				}
//				
//				if (check){
//					result[i][j] = new Color(0, 0, 0);//Turns the color into black
//				}
//				else {
//					result[i][j] = new Color(255, 255, 255);//Turns the color into white 
//				}
//				
//			}	
//		
//		return result;
//	}
//	
//	//Calculates color distance between pixels
//	public int colourDistance(Color[][] imageArray, int i, int j, int x, int y) {
//
//		return (int) Math.sqrt(Math.pow(imageArray[i][j].getRed() - imageArray[x][y].getRed(), 2) + Math.pow(imageArray[i][j].getGreen() - imageArray[x][y].getGreen(), 2) + Math.pow(imageArray[i][j].getBlue() - imageArray[x][y].getBlue(), 2));
//	}
//	//If pixel has 3 neighbors
//	public boolean threePixels (Color[][] imageArray,int i, int j,int numOfRows, int numOfColumns) {
//		
//		boolean flag = false;
//		int cDistance;
//		//Determining where the pixel is located 
//		if ((i+1>numOfRows)&&(j+1>numOfColumns)){
//			
//			cDistance = colourDistance(imageArray,i,j,i-1,j);
//			
//			if (colourDistance(imageArray,i,j,i-1,j-1) > cDistance) {
//				cDistance = colourDistance(imageArray,i,j,i-1,j-1);
//			}	
//
//			if (colourDistance(imageArray,i,j,i,j-1) > cDistance) {
//				cDistance = colourDistance(imageArray,i,j,i,j-1);
//			}
//		}
//		else if(i+1>numOfRows) {
//			cDistance = colourDistance(imageArray,i,j,i-1,j+1);
//			
//			if (colourDistance(imageArray,i,j,i-1,j) > cDistance) {
//				cDistance = colourDistance(imageArray,i,j,i-1,j);
//			}
//			
//			if (colourDistance(imageArray,i,j,i,j+1) > cDistance) {
//				cDistance = colourDistance(imageArray,i,j,i,j+1);
//			}
//			
//		}
//		else if(j+1>numOfColumns) {
//			cDistance = colourDistance(imageArray,i,j,i+1,j);
//			
//			if (colourDistance(imageArray,i,j,i+1,j-1) > cDistance) {
//				cDistance = colourDistance(imageArray,i,j,i+1,j-1);
//			}
//			
//			if (colourDistance(imageArray,i,j,i,j-1) > cDistance) {
//				cDistance = colourDistance(imageArray,i,j,i,j-1);
//			}
//		}
//		else {
//			
//			cDistance = colourDistance(imageArray,i,j,i+1,j);
//			
//			if (colourDistance(imageArray,i,j,i+1,j+1) > cDistance) {
//				cDistance = colourDistance(imageArray,i,j,i+1,j+1);
//			}	
//
//			if (colourDistance(imageArray,i,j,i,j+1) > cDistance) {
//				cDistance = colourDistance(imageArray,i,j,i,j+1);
//			}
//		}
//		
//		//Seeing if color distance is greater than 65
//		if (cDistance > 65)
//			flag = true;
//		
//		
//		return flag;
//		
//	}
//	//If pixel has 5 neighbors 
//	public boolean fivePixels (Color[][] imageArray,int i, int j,int numOfRows, int numOfColumns) {
//		
//		boolean flag = false;
//		
//		int cDistance;
//		
//		if (i == 0) {
//		
//			//Down
//			cDistance = colourDistance(imageArray,i,j,i+1,j);
//			
//			//Down Right
//			if (colourDistance(imageArray,i,j,i+1,j+1) > cDistance) {
//				cDistance = colourDistance(imageArray,i,j,i+1,j+1);
//			}	
//
//			//Right
//			if (colourDistance(imageArray,i,j,i,j+1) > cDistance) {
//				cDistance = colourDistance(imageArray,i,j,i,j+1);
//			}
//			
//			//Left
//			if (colourDistance(imageArray,i,j,i,j-1) > cDistance) {
//				cDistance = colourDistance(imageArray,i,j,i,j-1);
//			}
//			
//			//Down Left
//			if (colourDistance(imageArray,i,j,i+1,j-1) > cDistance) {
//				cDistance = colourDistance(imageArray,i,j,i+1,j-1);
//			}
//			
//		}
//		else if (j == 0) {
//			
//			//Down
//			cDistance = colourDistance(imageArray,i,j,i+1,j);
//			
//			//Down Right
//			if (colourDistance(imageArray,i,j,i+1,j+1) > cDistance) {
//				cDistance = colourDistance(imageArray,i,j,i+1,j+1);
//			}	
//
//			//Right
//			if (colourDistance(imageArray,i,j,i,j+1) > cDistance) {
//				cDistance = colourDistance(imageArray,i,j,i,j+1);
//			}
//				
//			//Up Right
//			if (colourDistance(imageArray,i,j,i-1,j+1) > cDistance) {
//					cDistance = colourDistance(imageArray,i,j,i+1,j-1);
//			}
//			
//			//Up
//			if (colourDistance(imageArray,i,j,i-1,j) > cDistance) {
//				cDistance = colourDistance(imageArray,i,j,i+1,j-1);
//			}
//			
//			
//		}
//		else if(i == numOfRows) {
//			
//			//Up
//				cDistance = colourDistance(imageArray,i,j,i-1,j);
//			
//			//Right
//			if (colourDistance(imageArray,i,j,i,j+1) > cDistance) {
//				cDistance = colourDistance(imageArray,i,j,i,j+1);
//			}
//			//Up Right
//			if (colourDistance(imageArray,i,j,i-1,j+1) > cDistance) {
//				cDistance = colourDistance(imageArray,i,j,i+1,j-1);
//			}
//			//Up Left
//			if (colourDistance(imageArray,i,j,i-1,j-1) > cDistance) {
//				cDistance = colourDistance(imageArray,i,j,i+1,j-1);
//			}
//			//Left
//			if (colourDistance(imageArray,i,j,i,j-1) > cDistance) {
//				cDistance = colourDistance(imageArray,i,j,i,j-1);
//			}
//			
//		}
//		else{
//			
//			//Down
//			cDistance = colourDistance(imageArray,i,j,i+1,j);
//			//Left
//			if (colourDistance(imageArray,i,j,i,j-1) > cDistance) {
//				cDistance = colourDistance(imageArray,i,j,i,j-1);
//			}
//			
//			//Down Left
//			if (colourDistance(imageArray,i,j,i+1,j-1) > cDistance) {
//				cDistance = colourDistance(imageArray,i,j,i+1,j-1);
//			}
//			//Up
//			if (colourDistance(imageArray,i,j,i-1,j) > cDistance) {
//				cDistance = colourDistance(imageArray,i,j,i+1,j-1);
//			}
//			
//			//Up Left
//			if (colourDistance(imageArray,i,j,i-1,j-1) > cDistance) {
//				cDistance = colourDistance(imageArray,i,j,i+1,j-1);
//			}
//		}
//		//Seeing if color distance is greater than 65
//		if (cDistance > 65)
//			flag = true;
//			
//		return flag;
//		
//	}
//	//If pixel has 8 neighbors 
//	public boolean eightPixels (Color[][] imageArray,int i, int j) {
//		
//		boolean flag = false;
//		
//		//Determining the location of the surrounding pixels 
//		int cDistance;
//		
//		//Down
//		cDistance = colourDistance(imageArray,i,j,i+1,j);
//		
//		//Down Right
//		if (colourDistance(imageArray,i,j,i+1,j+1) > cDistance) {
//			cDistance = colourDistance(imageArray,i,j,i+1,j+1);
//		}	
//
//		//Right
//		if (colourDistance(imageArray,i,j,i,j+1) > cDistance) {
//			cDistance = colourDistance(imageArray,i,j,i,j+1);
//		}
//		
//		//Left
//		if (colourDistance(imageArray,i,j,i,j-1) > cDistance) {
//			cDistance = colourDistance(imageArray,i,j,i,j-1);
//		}
//		
//		//Down Left
//		if (colourDistance(imageArray,i,j,i+1,j-1) > cDistance) {
//			cDistance = colourDistance(imageArray,i,j,i+1,j-1);
//		}
//		
//		//Up Right
//		if (colourDistance(imageArray,i,j,i-1,j+1) > cDistance) {
//			cDistance = colourDistance(imageArray,i,j,i-1,j+1);
//		}
//		
//		//Up
//		if (colourDistance(imageArray,i,j,i-1,j) > cDistance) {
//			cDistance = colourDistance(imageArray,i,j,i-1,j);
//		}
//		
//		//Up Left
//		if (colourDistance(imageArray,i,j,i-1,j-1) > cDistance) {
//			cDistance = colourDistance(imageArray,i,j,i-1,j-1);
//		}
//		//Seeing if color distance is greater than 65
//		if (cDistance > 65)
//			flag = true;
//		
//		return flag;
//		
//	}
//	//Determining the number of neighbors based on its position
//	public int numOfNeighbours (int rows, int columns, int numOfRows, int numOfColumns) {
//		
//		int neighbours = 0;
//		if ((rows == 0||rows == numOfRows) && (columns == 0||columns == numOfColumns))
//		{
//			neighbours = 3;
//		}
//		else if ((rows != 0 && (columns == 0 || columns == numOfColumns))||(columns != 0 && (rows == 0 || rows == numOfRows))) {
//			neighbours = 5;
//		}
//		else {
//			neighbours = 8;
//		}
//		return neighbours;
//	}
//}

import java.awt.Color;

public class ContourOperation implements ImageOperation {

	public Color[][] doOperation(Color[][] imageArray) {
		int numOfRows = imageArray.length - 1;
		int numOfColumns = imageArray[0].length - 1;

		Color[][] result = new Color[numOfRows + 1][numOfColumns + 1];

		for (int i = 0; i < numOfRows + 1; i++) {
			for (int j = 0; j < numOfColumns + 1; j++) {

				int position = position(i, j, numOfRows, numOfColumns);
				int max = 0;

				/*
				 * This set of if statements essentially determines the max color distance by performing all possible
				 * operations based upon the pixels 1-9 type
				 */
				if (position > 3)
					max = determineMax(colourDistance(imageArray, i, j, i - 1, j), max);
				if (position < 6)
					max = determineMax(colourDistance(imageArray, i, j, i + 1, j), max);
				if (position == 2 || position == 3 || position == 5 || position == 6 || position == 8 || position == 9)
					max = determineMax(colourDistance(imageArray, i, j, i, j - 1), max);
				if (position == 1 || position == 2 || position == 4 || position == 5 || position == 7 || position == 8)
					max = determineMax(colourDistance(imageArray, i, j, i, j + 1), max);
				if (position == 4 || position == 5 || position == 7 || position == 8)
					max = determineMax(colourDistance(imageArray, i, j, i - 1, j + 1), max);
				if (position == 9 || position == 8 || position == 6 || position == 5)
					max = determineMax(colourDistance(imageArray, i, j, i - 1, j - 1), max);
				if (position == 1 || position == 2 || position == 4 || position == 5)
					max = determineMax(colourDistance(imageArray, i, j, i + 1, j + 1), max);
				if (position == 2 || position == 3 || position == 5 || position == 6)
					max = determineMax(colourDistance(imageArray, i, j, i + 1, j - 1), max);

				if (max > 65) {
					result[i][j] = new Color(0, 0, 0);
				} else {
					result[i][j] = new Color(255, 255, 255);
				}
			}
		}
		return result;
	}
	/**
	 * 
	 * @param imageArray
	 * @param i
	 * @param j
	 * @param x
	 * @param y
	 * @return
	 */
	public int colourDistance(Color[][] imageArray, int i, int j, int x, int y) {
		return (int) Math.sqrt(Math.pow(imageArray[i][j].getRed() - imageArray[x][y].getRed(), 2)
				+ Math.pow(imageArray[i][j].getGreen() - imageArray[x][y].getGreen(), 2)
				+ Math.pow(imageArray[i][j].getBlue() - imageArray[x][y].getBlue(), 2)); // Returns color distance value
	}
	/**
	 * 
	 * @param i
	 * @param j
	 * @param numOfRows
	 * @param numOfColumns
	 * @return
	 */
	public int position(int i, int j, int numOfRows, int numOfColumns) {

		int position;
		
		// Determines the general 1-9 position type of the pixel
		if (i == 0 && j == 0) {
			position = 1;
		} else if (i == numOfRows && j == numOfColumns) {
			position = 9;
		} else if (i == numOfRows && j == 0) {
			position = 7;
		} else if (i == 0 && j == numOfColumns) {
			position = 3;
		} else if (i == 0 && (j != 0 || j != numOfColumns)) {
			position = 3;
		} else if (j == 0 && (i != 0 || i != numOfRows)) {
			position = 4;
		} else if (j == numOfColumns && (i != 0 || i != numOfRows)) {
			position = 6;
		} else if (i == numOfRows && (j != 0 || j != numOfColumns)) {
			position = 7;
		} else {
			position = 5;
		}

		return position;
	}
	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public int determineMax(int x, int y) {
		if (x > y) {
			return x;
		}
		return y;
	}
}