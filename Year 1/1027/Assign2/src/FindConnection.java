import java.io.FileNotFoundException;
import java.io.IOException;
/**
 * 
 * @author matteo
 *
 */
public class FindConnection {

	
	Map cityMap;
	/**
	 * 
	 * @param filename
	 * @throws InvalidMapException
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @variable - cityMap
	 * @variable - s
	 * @variable -cell
	 * 
	 * The Find connection class takes the filename argument and calls separate methods that return the 
	 * series of cells necessary to complete the path to the destination
	 */
	public FindConnection(String filename) throws InvalidMapException, FileNotFoundException, IOException {

		try {
			Map cityMap = new Map(filename);
			Boolean destinationFound = false;

			// Create an empty stack
			ArrayStack s = new ArrayStack();

			// get start cell station
			MapCell cell = cityMap.getStart();

			// push the first sell into the empty stack and mark as inStack
			s.push(cell);
			cell.markInStack();
			int count = 0;

			// While loop stack not empty and destination has not been found
			while ((!s.isEmpty()) && (!destinationFound)) {

				if (((MapCell) s.peek()).isCustomer()) {
					System.out.println("Destination Reached in "+ count + " cells");
					destinationFound = true;
				} else {
					cell = bestCell(cell);
					if (!cell.isMarked()) {
						s.push(cell);
						cell.markInStack();
						count++;

					} else {

						s.pop();
						cell.markOutStack();
						cell = (MapCell) s.peek();
						count--;
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	/**
	 * 
	 * @param args
	 * @throws InvalidMapException
	 * @throws FileNotFoundException
	 * @throws IOException
	 * 
	 * The main takes the filename input and creates the map
	 */
	public static void main(String[] args) throws InvalidMapException, FileNotFoundException, IOException {

		if (args.length < 1) {
			System.out.println("You must provide the name of the input file");
			System.exit(0);
		}
		String mapFileName = args[0];

		FindConnection input = new FindConnection(mapFileName);

	}
	
	/**
	 * 
	 * @param cell
	 * @return
	 * 
	 * Determines what operations need to be ran depending on the cell type
	 */
	private MapCell bestCell(MapCell cell) {

		MapCell nextCell = cell;

		if (cell.isOmniSwitch() || cell.isPowerStation())
			nextCell = checkNeighbours(cell);
		else if (cell.isVerticalSwitch()) {
			nextCell = checkNeighboursVert(cell);
		} else {
			nextCell = checkNeighboursHori(cell);
		}

		return nextCell;

	}

	/**
	 * 
	 * @param cell
	 * @return
	 * 
	 * Checks all neighbouring positions for the correct next cell
	 */
	private MapCell checkNeighbours(MapCell cell) {

		for (int i = 0; i <= 3; i++) {
			try {
				if (cell.getNeighbour(i).isCustomer() && (!cell.getNeighbour(i).isMarked())) {
					return cell.getNeighbour(i);
				}
			} catch (NullPointerException e) {

			}
		}

		for (int i = 0; i <= 3; i++) {
			try {
				if (cell.getNeighbour(i).isOmniSwitch() && (!cell.getNeighbour(i).isMarked())) {
					return cell.getNeighbour(i);
				}
			} catch (NullPointerException e) {

			}
		}

		try {
			if (cell.getNeighbour(0).isVerticalSwitch() && (!cell.getNeighbour(0).isMarked())) {
				return cell.getNeighbour(0);
			}
		} catch (NullPointerException e) {

		}

		try {
			if (cell.getNeighbour(1).isHorizontalSwitch() && (!cell.getNeighbour(1).isMarked())) {
				return cell.getNeighbour(1);
			}
		} catch (NullPointerException e) {

		}
		try {
			if (cell.getNeighbour(2).isVerticalSwitch() && (!cell.getNeighbour(2).isMarked())) {
				return cell.getNeighbour(2);
			}
		} catch (NullPointerException e) {

		}
		try {
			if (cell.getNeighbour(3).isHorizontalSwitch() && (!cell.getNeighbour(3).isMarked())) {
				return cell.getNeighbour(3);
			}
		} catch (NullPointerException e) {

		}

		return cell;

	}

	/**
	 * 
	 * @param cell
	 * @return
	 * 
	 * Checks vertical neighbouring positions for the correct next cell
	 */
	private MapCell checkNeighboursVert(MapCell cell) {

		for (int i = 0; i <= 3; i++) {
			if (i == 0 || i == 2) {
				try {
					if (cell.getNeighbour(i).isCustomer() && (!cell.getNeighbour(i).isMarked())) {
						return cell.getNeighbour(i);
					}
				} catch (NullPointerException e) {

				}
			}
		}

		for (int i = 0; i <= 3; i++) {
			if (i == 0 || i == 2) {
				try {
					if (cell.getNeighbour(i).isOmniSwitch() && (!cell.getNeighbour(i).isMarked())) {
						return cell.getNeighbour(i);
					}
				} catch (NullPointerException e) {

				}
			}
		}
		for (int i = 0; i <= 3; i++) {
			if (i == 0 || i == 2) {
				try {
					if (cell.getNeighbour(i).isVerticalSwitch() && (!cell.getNeighbour(i).isMarked())) {
						return cell.getNeighbour(i);
					}
				} catch (NullPointerException e) {

				}
			}
		}

		return cell;

	}

	/**
	 * 
	 * @param cell
	 * @return
	 * 
	 * Checks horizontal neighbouring positions for the correct next cell
	 */
	private static MapCell checkNeighboursHori(MapCell cell) {

		for (int i = 0; i <= 3; i++) {
			if (i == 1 || i == 3) {
				try {
					if (cell.getNeighbour(i).isCustomer() && (!cell.getNeighbour(i).isMarked())) {
						return cell.getNeighbour(i);
					}
				} catch (NullPointerException e) {

				}
			}
		}

		for (int i = 0; i <= 3; i++) {
			if (i == 1 || i == 3) {
				try {
					if (cell.getNeighbour(i).isOmniSwitch() && (!cell.getNeighbour(i).isMarked())) {
						return cell.getNeighbour(i);
					}
				} catch (NullPointerException e) {

				}
			}
		}

		for (int i = 0; i <= 3; i++) {
			if (i == 1 || i == 3) {
				try {
					if (cell.getNeighbour(i).isHorizontalSwitch() && (!cell.getNeighbour(i).isMarked())) {
						return cell.getNeighbour(i);
					}
				} catch (NullPointerException e) {

				}
			}
		}
		return cell;

	}
}
