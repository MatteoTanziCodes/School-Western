import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 
 * @author matteo
 *
 */
public class ShortestPath {

	Map cityMap; //Instance Variable

	/**
	 * 
	 * @param filename
	 * @throws InvalidMapException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public ShortestPath(String filename) throws InvalidMapException, FileNotFoundException, IOException {

		//Try and catch for filenames
		try {
			this.cityMap = new Map(filename);
		} catch (InvalidMapException e) {
			System.out.println("Wrong map file");
		} catch (FileNotFoundException e) {
			System.out.println("This file does not exist");
		} catch (IOException e) {
			System.out.println("An error has occurred opening the map");
		}

	}

	/**
	 * 
	 * @param input
	 * @return
	 */
	private DLNode<MapCell> checkCustomer(DLList<MapCell> input) {

		DLNode<MapCell> current = input.getfront();

		while (current != null) {
			if (current.getData().isCustomer()) {
				return current;
			} else
				current = current.getNext();
		}
		return null;
	}

	/**
	 * 
	 * @param args
	 * @throws InvalidMapException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void main(String[] args) throws InvalidMapException, FileNotFoundException, IOException {

		if (args.length < 1) {
			System.out.println("Need input filename");
			System.exit(0);
		}

		ShortestPath shortPathMap = new ShortestPath(args[0]);
		DLList<MapCell> list = new DLList<MapCell>();
		int distance = 0;
		int nextDistance = 0;

		try {

			MapCell smallest = shortPathMap.cityMap.getStart();
			MapCell next = shortPathMap.cityMap.getStart();

			list.insert(smallest, distance);
			list.findData(smallest).markInList(); 

			while (list.size() != 0 && shortPathMap.checkCustomer(list) == null) {

				smallest = list.getSmallest();
				smallest.markOutList();
				if (smallest.isCustomer())
					break;
				for (int i = 0; i <= 3; i++) {
					next = shortPathMap.nextCell(smallest);
					if (next == null || next.isMarked())
						continue;
					distance = 1 + smallest.getDistanceToStart();
					if (next.getDistanceToStart() > distance) {
						next.setDistanceToStart(distance);
						next.setPredecessor(smallest);
					}

					nextDistance = next.getDistanceToStart();
					if (list.findData(next) == next && nextDistance < list.getDataValue(next)) {
						list.changeValue(next, nextDistance);
					} else {
						list.insert(next, nextDistance);
						list.findData(next).markInList();

					}

				}

			}
			shortPathMap.checkCustomer(list).getData().markOutList();

			if (list.size() == 0)
				System.out.println("No path found");
			else
				System.out.println("The path has " + (smallest.getDistanceToStart() + 1) + " cells");

		} catch (NullPointerException e) {
			System.out.println("No power station");
		}
	}

	/**
	 * 
	 * @param cell
	 * @return
	 */
	private MapCell nextCell(MapCell cell) {

		for (int i = 0; i <= 3; i++) {
			if (i % 2 == 0 && cell.getNeighbour(i) != null) {
				if (!cell.getNeighbour(i).isMarked()
						&& (cell.isVerticalSwitch() || cell.isOmniSwitch() || cell.isPowerStation())
						&& !cell.getNeighbour(i).isMarkedOutList()) {
					if (cell.getNeighbour(i).isVerticalSwitch() || cell.getNeighbour(i).isOmniSwitch()
							|| cell.getNeighbour(i).isCustomer()) {
						return cell.getNeighbour(i);
					}
				}
			}
			if (!(i % 2 == 0) && cell.getNeighbour(i) != null) {
				if (!cell.getNeighbour(i).isMarked()
						&& (cell.isHorizontalSwitch() || cell.isOmniSwitch() || cell.isPowerStation())
						&& !cell.getNeighbour(i).isMarkedOutList()) {
					if (cell.getNeighbour(i).isHorizontalSwitch() || cell.getNeighbour(i).isOmniSwitch()
							|| cell.getNeighbour(i).isCustomer()) {
						return cell.getNeighbour(i);
					}
				}
			}
		}
		return null;
	}
}
