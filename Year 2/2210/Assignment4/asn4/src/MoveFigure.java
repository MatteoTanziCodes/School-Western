public class MoveFigure {

	private Gui environment; // Graphical user interface

	/* =========================== */
	public MoveFigure(Gui env) {
		/* =========================== */
		environment = env;
	}

	/* ============================================ */
	public int moveLeft(GraphicalFigure[] figs, int numFigures, int fignum, int step)
	/* ============================================ */
	/*
	 * Move the graphical object specified by the second argument to the left. Return true
	 * if the graphical object could move.
	 */
	{
		Location curr, next;
		int newx;

		curr = figs[fignum].getOffset();
		if (curr.xCoord() < step)
			return -1; // Graphical object reached left border
		else
			newx = curr.xCoord() - step;
		next = new Location(newx, curr.yCoord());

		// Verify that graphical obejcts do not overlap
		figs[fignum].setOffset(next);
		for (int i = 0; i < numFigures; ++i)
			if ((i != fignum) && (figs[fignum].intersects(figs[i]))) {
				figs[fignum].setOffset(curr);
				return i;
			}

		// Move graphical object to its new Location
		figs[fignum].setOffset(curr);
		environment.eraseFigure(figs[fignum]);
		figs[fignum].setOffset(next);
		environment.drawFigure(figs[fignum]);
		return -2;
	}

	/* ============================================ */
	public int moveRight(GraphicalFigure[] figs, int numFigures, int fignum, int step)
	/* ============================================ */
	/*
	 * Move the graphical object specified by the second argument to the right. Return true
	 * if the graphical obejct could move.
	 */
	{
		Location curr, next;
		int newx;

		curr = figs[fignum].getOffset();
		if (curr.xCoord() > (environment.displayWidth() - figs[fignum].getWidth() - step))
			return -1; // Graphical object reached right border
		else
			newx = curr.xCoord() + step;
		next = new Location(newx, curr.yCoord());

		// Check that graphical objects do not overlap
		figs[fignum].setOffset(next);
		for (int i = 0; i < numFigures; ++i)
			if ((i != fignum) && (figs[fignum].intersects(figs[i]))) {
				figs[fignum].setOffset(curr);
				return i;
			}

		// Move graphical object to its new Location
		figs[fignum].setOffset(curr);
		environment.eraseFigure(figs[fignum]);
		figs[fignum].setOffset(next);
		environment.drawFigure(figs[fignum]);
		return -2;
	}

	/* ============================================ */
	public int moveDown(GraphicalFigure[] figs, int numFigures, int fignum, int step)
	/* ============================================ */
	/*
	 * Move the graphical object specified by the second argument t the right. Return true
	 * if the graphical object could move.
	 */
	{
		Location curr, next;
		int newy;

		curr = figs[fignum].getOffset();
		if (curr.yCoord() > (environment.displayHeight() - figs[fignum].getHeight() - step))
			return -1; // Graphical object reached bottom border
		else
			newy = curr.yCoord() + step;
		next = new Location(curr.xCoord(), newy);

		// Check that graphical objects do not overlap
		figs[fignum].setOffset(next);
		for (int i = 0; i < numFigures; ++i)
			if ((i != fignum) && (figs[fignum].intersects(figs[i]))) {
				figs[fignum].setOffset(curr);
				return i;
			}

		// Move graphical object to its new Location
		figs[fignum].setOffset(curr);
		environment.eraseFigure(figs[fignum]);
		figs[fignum].setOffset(next);
		environment.drawFigure(figs[fignum]);
		return -2;
	}

	/* ============================================ */
	public int moveUp(GraphicalFigure[] figs, int numFigures, int fignum, int step)
	/* ============================================ */
	/*
	 * Move the graphical object specified by the second argument to the right. Return true
	 * if the graphical object could move
	 */
	{
		Location curr, next;
		int newy;

		curr = figs[fignum].getOffset();
		if (curr.yCoord() < step)
			return -1; // Graphical object reached top border
		else
			newy = curr.yCoord() - step;
		next = new Location(curr.xCoord(), newy);

		// Check that graphical objects do not overlap
		figs[fignum].setOffset(next);
		for (int i = 0; i < numFigures; ++i)
			if ((i != fignum) && (figs[fignum].intersects(figs[i]))) {
				figs[fignum].setOffset(curr);
				return i;
			}

		// Draw graphical object in its new Location
		figs[fignum].setOffset(curr);
		environment.eraseFigure(figs[fignum]);
		figs[fignum].setOffset(next);
		environment.drawFigure(figs[fignum]);
		return -2;
	}
}