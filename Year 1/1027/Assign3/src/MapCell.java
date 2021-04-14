import java.awt.Color;

/**
 * This class represents a cell used to make up a Map object.
 * Each cell has a type and is drawn in a different colour.
 * Cells know about their neighbors (if they are set using setNeighbor method).
 * The neighbors of a cell are accessed by an index 0, 1, 2, ... 
 * The neighbours of a cell are indexed as follows: the 0 index is for the top
 * neighbour. Indexes for the other neighbours progress incrementally clockwise 
 * from 0 to 3. Eg.
 *        0 
 *       ---
 *    3 |   | 1
 *       ---
 *        2 
 * @author CS1027
 *
 */
public class MapCell extends CellComponent {
	private static final long serialVersionUID = 4865976127980106774L;

	// enum to represent available cell types
	public static enum CellType {
		BLOCK, INITIAL, CUSTOMER, OMNI_SWITCH, INITIAL_PROCESSED, CUSTOMER_PROCESSED, INITAL_OUT_LIST, 
		IN_LIST, OUT_LIST,  ODD_SWITCH, EVEN_SWITCH, PATH
	};

	private CellType type; // Stores the current type of this cell. This value
							// changes as the cells in the map are marked
	private CellType originalType; // Type initially assigned to this cell
	private boolean isStart; // Is this the initial cell with the power station?
	private boolean isEnd; // Is this the customer cell?
	private MapCell[] neighbors; // Stores the cells neighboring THIS one
	private int timeDelay; // Time that the program waits before moving to an adjacent cell
	private int numNeighbours = 4;
	
	private int distanceToStart; // Distance from the cell represented by
									// this MapCell object to the power station cell
	private MapCell predecessor; // Predecessor of this cell in the shortest path to customer cell

	/**
	 * Create a  cell of the specified type
	 * 
	 * @param t
	 *            the CellType to create
	 * @param delay
	 *            time that the program waits before moving to next cell
	 */
	public MapCell(CellType t, int delay) {
		timeDelay = delay;
		this.type = t;
		this.originalType = t;
		this.isStart = (t == CellType.INITIAL);
		this.isEnd = (t == CellType.CUSTOMER);

		// set the initial color based on the initial type
		this.setColor(this.type);
		// allocate space for the neighbor array
		this.neighbors = new MapCell[numNeighbours];
		if (isStart) distanceToStart = 0;
		else distanceToStart = Integer.MAX_VALUE; // Distance to power plant
													// is not known
	}

	/**
	 * Set the neighbor for this cell using the neighbor index.
	 * 
	 * The index for the neighbor indicates which side of the cell this new
	 * neighbor is on: 0-3 inclusive.
	 * 
	 * @param neighbor
	 *            The new cell neighbor
	 * @param i
	 *            The index specifying which side this neighbor is on (0-3
	 *            inclusive)
	 * @throws InvalidNeighbourIndexException
	 *             When an index is specified that is not 0-3.
	 */
	public void setNeighbour(MapCell neighbor, int i) throws InvalidNeighbourIndexException {
		if (0 <= i && i < numNeighbours)
			this.neighbors[i] = neighbor;
		else
			throw new InvalidNeighbourIndexException(i);
	}

	/**
	 * Returns the neighbor for this cell using the neighbor index.
	 * 
	 * The index for the neighbor indicates in which side of the cell the
	 * neighbor is: 0-3.
	 * 
	 * @param i
	 *            The index of the neighbor
	 * @return The cell that is on the i-th side of the current cell, or null if
	 *         no neighbor
	 * @throws InvalidNeighbourIndexException
	 *             When an index is specified that is not 0-3.
	 */
	public MapCell getNeighbour(int i) throws InvalidNeighbourIndexException {
		if (0 <= i && i < numNeighbours)
			return this.neighbors[i];
		else
			throw new InvalidNeighbourIndexException(i);
	}

	/**
	 * This method checks if the current cell is a block of buildings.
	 * 
	 * @return true if this is a cell representing a block of buildings, false otherwise.
	 */
	public boolean isBlock() {
		return type == CellType.BLOCK;
	}

	/**
	 * This method checks if the current cell is an omni switch.
	 * 
	 * @return true if this cell is an omni switch, false otherwise.
	 */
	public boolean isOmniSwitch() {
		return originalType == CellType.OMNI_SWITCH;
	}

	/**
	 * This method checks if the current cell has been marked as in stack or out
	 * of stack.
	 * 
	 * @return true if this cell has been marked as in stack or out of stack,
	 *         false otherwise.
	 */

	public boolean isMarked() {
		return (type == CellType.IN_LIST) || (type == CellType.OUT_LIST);
	}

	/**
	 * This method checks if the current cell has been marked as dequeued
	 * 
	 * @return true if this cell has been marked as dequeued, false
	 *         otherwise.
	 */

	public boolean isMarkedOutList() {
		return (type == CellType.OUT_LIST);
	}
	
	/**
	 * This method checks if the current cell has been marked as enqueued
	 * 
	 * @return true if this cell has been marked as enqueued, false
	 *         otherwise.
	 */

	public boolean isMarkedInList() {
		return (type == CellType.IN_LIST);
	}
	
	/**
	 * This method checks if the current cell is an even switch.
	 * 
	 * @return true if this is an even switch, false otherwise.
	 */
	public boolean isVerticalSwitch() {
		return originalType == CellType.EVEN_SWITCH;
	}
	
		/**
	 * This method checks if the current cell is an odd switch.
	 * 
	 * @return true if this is an odd switch, false otherwise.
	 */
	public boolean isHorizontalSwitch() {
		return originalType == CellType.ODD_SWITCH;
	}

	/**
	 * This method checks if the current cell is the initial cell.
	 * 
	 * @return true if this is the initial cell, false otherwise.
	 */
	public boolean isPowerStation() {
		return this.isStart;
	}

	/**
	 * This method checks if the current cell is the destination.
	 * 
	 * @return true if this is the destination cell, false otherwise.
	 */
	public boolean isCustomer() {
		return this.isEnd;
	}

	/**
	 * This method re-draws the current cell
	 */
	private void reDraw() {
		try {
			Thread.sleep(timeDelay);
		} catch (Exception e) {
			System.err.println("Error while issuing time delay\n" + e.getMessage());
		}
		super.repaint();
	}

	/**
	 * This method marks the destination cell and updates the cell's colour
	 */
	public void markCustomer() {
		this.type = CellType.CUSTOMER_PROCESSED;
		this.setColor(this.type);
		reDraw();
	}

	/**
	 * This method marks the power station cell as the initial cell and updates the cell's
	 * colour
	 */
	public void markInitial() {
		this.type = CellType.INITIAL_PROCESSED;
		this.setColor(this.type);
		reDraw();
	}

		/**
	 * This method marks the cell as in-stack and updates the cell's colour
	 */
	public void markInList() {
		type = CellType.IN_LIST;
		setColor(type);
		reDraw();
	}

	/**
	 * This method marks the cell as popped and updates the cell's colour
	 */
	public void markOutList() {
		type = CellType.OUT_LIST;
		if (this.isCustomer()) {
			MapCell prev = this;

			while (prev != null) {
				prev.setColor(CellType.PATH);
				reDraw();
				prev = prev.getPredecessor();
			}
		} else {
			setColor(this.type);
			reDraw();
		}
	}

	
	/**
	 * Helper method to set the current cell color based on the type of cell.
	 * 
	 * @param t
	 *            The type of the cell; used to set the color
	 */
	private void setColor(CellType t) {
		switch (t) {
		case BLOCK:
			this.setBackground(CellColors.BLOCK);
			break;
		case INITIAL:
			this.setBackground(CellColors.INITIAL);
			break;
		case CUSTOMER:
			this.setBackground(CellColors.CUSTOMER);
			break;
		case OMNI_SWITCH:
			this.setBackground(CellColors.OMNI_SWITCH);
			break;
		case CUSTOMER_PROCESSED:
			this.setBackground(CellColors.CUSTOMER_PROCESSED);
			break;
		case INITIAL_PROCESSED:
			this.setBackground(CellColors.INITIAL_PROCESSED);
			break;
		case INITAL_OUT_LIST:
			this.setBackground(CellColors.INITIAL_OUT_LIST);
			break;
		case IN_LIST:
			if (originalType == CellType.EVEN_SWITCH)
				setBackground(CellColors.EVEN_SWITCH_IN_LIST);
			else if (originalType == CellType.ODD_SWITCH)
				setBackground(CellColors.ODD_SWITCH_IN_LIST);
			else if (originalType == CellType.OMNI_SWITCH)
				setBackground(CellColors.OMNI_SWITCH_IN_LIST);
			else if (originalType == CellType.INITIAL)
				this.setBackground(CellColors.INITIAL_PROCESSED);
			else if (originalType == CellType.CUSTOMER)
				this.setBackground(CellColors.CUSTOMER_PROCESSED);
			else
				setBackground(CellColors.IN_QUEUE);
			break;
		case OUT_LIST:
			if (originalType == CellType.EVEN_SWITCH)
				setBackground(CellColors.EVEN_SWITCH_OUT_LIST);
			else if (originalType == CellType.ODD_SWITCH)
				setBackground(CellColors.ODD_SWITCH_OUT_LIST);
			else if (originalType == CellType.OMNI_SWITCH)
				setBackground(CellColors.OMNI_SWITCH_OUT_LIST);
			else if (originalType == CellType.INITIAL)
				setBackground(CellColors.INITIAL_OUT_LIST);
			else
				setBackground(CellColors.OUT_QUEUE);
			break;
		case PATH:
			if (originalType == CellType.EVEN_SWITCH)
				setBackground(CellColors.EVEN_SWITCH_PATH);
			else if (originalType == CellType.ODD_SWITCH)
				setBackground(CellColors.ODD_SWITCH_PATH);
			else if (originalType == CellType.OMNI_SWITCH)
				setBackground(CellColors.OMNI_SWITCH_PATH);			
			else if (originalType == CellType.INITIAL)
				setBackground(CellColors.INITIAL_PATH);
			else if (originalType == CellType.CUSTOMER)
				setBackground(CellColors.CUSTOMER_PATH);
			else
				setBackground(CellColors.PATH);
			break;
		case EVEN_SWITCH:
			this.setBackground(CellColors.EVEN_SWITCH);
			break;
		case ODD_SWITCH:
			this.setBackground(CellColors.ODD_SWITCH);
			break;
		default:
			this.setBackground(CellColors.BLOCK);
			break;
		}
		this.setForeground(Color.BLACK);
	}

	/**
	 * Gets a String representation of a cell.
	 * 
	 * @return String representation of the cell.
	 */
	public String toString() {
		if (type == CellType.BLOCK)
			return "Block of buildings, ";
		else if (type == CellType.INITIAL || type == CellType.INITIAL_PROCESSED || type == CellType.INITAL_OUT_LIST
				|| originalType == CellType.INITIAL)
			return "Power station, ";
		else if (type == CellType.CUSTOMER || type == CellType.CUSTOMER_PROCESSED || originalType == CellType.CUSTOMER)
			return "Destination, ";
		else if (type == CellType.OMNI_SWITCH || originalType == CellType.OMNI_SWITCH)
			return "Omni switch, ";
		else if (type == CellType.EVEN_SWITCH || originalType == CellType.EVEN_SWITCH)
			return "Even switch, ";
		else if (type == CellType.ODD_SWITCH || originalType == CellType.ODD_SWITCH)
			return "Odd switch, ";
		else
			return "";
	}
	
	
	/**
	 * Returns the current estimation of the distance from the power station to
	 * this cell
	 * 
	 * @return The estimated distance to the power station
	 */
	public int getDistanceToStart() {
		return distanceToStart;
	}

	/**
	 * Changes the current estimation of the distance from the power station to
	 * this cell
	 * 
	 * @param The
	 *            estimation of the distance to the power station
	 */
	public void setDistanceToStart(int dist) {
		distanceToStart = dist;
	}

	/**
	 * Returns true if this cell is being referenced by otherCell
	 * 
	 * @param otherCell Map cell
	 * @return true if the parameter points to this cell
	 */
	public boolean equals(MapCell otherCell) {
		return this == otherCell;
	}

	/**
	 * Get the preceding cell to this one in a shortest path from
	 * power station to customer's place
	 * 
	 * @return preceding cell in the shortest path from power station to customer house
	 */
	public MapCell getPredecessor() {
		return predecessor;
	}

	/**
	 * Set the preceding cell to this one in a shortest path from the
	 * power station to the customer's place
	 * 
	 * @param pred: preceding cell to this one in a shortest path from
	 *            the power station to the customer
	 */
	public void setPredecessor(MapCell pred) {
		predecessor = pred;
	}
}