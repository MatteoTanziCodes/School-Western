import java.awt.Color;

public class AdjustmentOperation implements ImageOperation {

	public Color[][] doOperation(Color[][] imageArray) {
		int numOfRows = imageArray.length;
		int numOfColumns = imageArray[0].length;
		final double max = Math.sqrt(Math.pow(numOfRows, 2) + Math.pow(numOfColumns, 2));

		Color[][] result = new Color[numOfRows][numOfColumns];

		for (int i = 0; i < numOfRows; i++) {
			for (int j = 0; j < numOfColumns; j++) {

				double varible = adjustBrightness(i, j, max);
				
				// Determine a new RGB value based on the brightness multiplier
				int red = (int) (imageArray[i][j].getRed() * varible);
				int green = (int) (imageArray[i][j].getGreen() * varible);
				int blue = (int) (imageArray[i][j].getBlue() * varible);

				result[i][j] = new Color(red, green, blue);
			}
		}
		return result;
	}

	/**
	 * 
	 * @param rows
	 * @param columns
	 * @param max
	 * @return
	 */
	public double adjustBrightness(int rows, int columns,double max) {
		return Math.sqrt(Math.pow(rows, 2) + Math.pow(columns, 2)) / max; // Adjust brightness multiplier
	}
}
