public class GraphicalFigure implements GraphicalFigureADT {

	//Variable declaration
	private int id, width, height;
	String type;
	Location pos;
	BinarySearchTree BST;
	
	//Constructor 
	public GraphicalFigure (int id, int width, int height, String type, Location pos) {
		this.id = id;
		this.width = width;
		this.height = height;
		this.type = type;
		this.pos = pos;
		
		
		//Creating empty binary search tree
		BST = new BinarySearchTree();
	
	}
	
	// Sets type of figure to specified value
	public void setType(String type) {
		this.type = type;
	}
	
	// Returns width of the rectangle
	 public int getWidth () {
		 return this.width;
	 }
	 
	 // Return height of the rectangle
	 public int getHeight() {
		 return this.height;
	 }
	 
	 // Return type of figure
	 public String getType() {
		 return this.type;
	 }
	 
	 // Return Id of figure
	 public int getId() {
		 return this.id;
	 }
	 
	 // Return offset of figure
	 public Location getOffset() {
		 return this.pos;
	 }
	 
	// Change offset to specified value
	 public void setOffset (Location value) {
		 this.pos = value;
	 }
	 
	 // Inserts pix into the BST associated with the figure
	 public void addPixel(Pixel pix) throws DuplicatedKeyException {
		 
		 try {
			 BST.put(BST.getRoot(), pix);
		 }
		 
		 catch (Exception e){
			 
			 throw new DuplicatedKeyException();
		 }
		 
		 
	 }
	 
	 //Returns true if this figure intersects the one specified in the parameter. It returns false otherwise.

	public boolean intersects (GraphicalFigure gobj) {
		
		//get the smallest pixel
		Pixel smallest = BST.smallest(BST.getRoot());
		
		while (smallest != null) {
			
			// See if there is any overlap
			int xOverlap = smallest.getLocation().xCoord() + pos.xCoord() - gobj.getOffset().xCoord();
			int yOverlap = smallest.getLocation().yCoord() + pos.yCoord() - gobj.getOffset().yCoord();
			
			
			if (gobj.findPixel(new Location(xOverlap, yOverlap)) != true) {
				smallest = BST.successor(BST.getRoot(), smallest.getLocation());
			}
			
			// return true if pixel found
			else {
				return true;
			}
		
		}
		
		// return false if pixel not found
		return false;
	
	 }
	
	//Checks if pixel can be found
	private boolean findPixel(Location p) {
		 
		 if (BST.get(BST.getRoot(), p) == null) return false;
		 
		 else return true;
	 }
	 
	 
	 
}