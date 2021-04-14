public interface GraphicalFigureADT {
	/*
	 * Returns the width of this figure
	 */
	public int getWidth();

	/*
	 * Returns the height of this figure
	 */
	public int getHeight();

	/*
	 * Returns the type of this figure
	 */
	public String getType();

	/*
	 * Returns the id of this figure
	 */
	public int getId();

	/*
	 * Returns the offset or position of this figure
	 */
	public Location getOffset();

	/*
	 * Changes the offset of this figure to the specified value.
	 */
	public void setOffset(Location value);

	/*
	 * Change the type of this figure to the specified value.
	 */
	public void setType(String t);

	/*
	 * Adds the given Pixel object into the binary search tree associated with
	 * this figure. A DuplicatedKeyException is thrown if the figure already has
	 * a Pixel with the same key as pix.
	 */
	public void addPixel(Pixel pix) throws DuplicatedKeyException;

	/*
	 * Returns true if this figure intersects the one specified in the
	 * parameter; it returns false otherwise.
	 */
	public boolean intersects(GraphicalFigure fig);
}