import java.awt.Color;

public class ThresholdingOperation implements ImageOperation {

	public Color[][] doOperation(Color[][] imageArray) {
		int numOfRows = imageArray.length;
		int numOfColumns = imageArray[0].length;

		Color[][] result = new Color[numOfRows][numOfColumns];

		for (int i = 0; i < numOfRows; i++)
			for (int j = 0; j < numOfColumns; j++) {

				// If the returned value is greater than 100 it sets the pixel to white, else dark
				if (brightnessScore(imageArray[i][j]) > 100) {
					result[i][j] = new Color(255, 255, 255);
				} else {
					result[i][j] = new Color(0, 0, 0);
				}
			}

		return result;
	}
	/**
	 * 
	 * @param p
	 * @return
	 */
	public double brightnessScore(Color p) {

		return 0.21 * p.getRed() + 0.71 * p.getGreen() + 0.07 * p.getBlue();
	}
}
