import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;
//Ali Doğan Pekdaş 150123014
//Ebubekir Bağdaş 150122053
//Doğukan Şahin 150122043
public class RoadTile extends Pane {
    // Declaration of instance variables
	private int roadType;
	private int rotation;
	private double gridCellX;
	private double gridCellY;
	private double widthOfGrid = 800.0 / 15;
	private double heightOfGrid = 800.0 / 15;

	// Constructor to initialize RoadTile object
	public RoadTile(int roadType, int rotation, double gridCellX, double gridCellY) {
		this.roadType = roadType;
		this.rotation = rotation;
		this.gridCellX = gridCellX;
		this.gridCellY = gridCellY;
	}

	// Method for selecting road types
	public Pane roadMaker() {
		Pane pane = new Pane(); // Creating a new Pane to hold the road
		
		// Switching between different road types to create the appropriate road
		switch (this.roadType) {
		case 0:	
			pane = createStraightRoad();
			break;
		case 1:
			pane = createCurvedRoad();
			break;
		case 2:
			pane = createFourIntersectionRoad();
			break;
		case 3:
			pane = createThreeIntersectionRoad();
			break;
		default:
			return null;
		}
		
		// Setting the position of the road within the grid
		pane.setLayoutX(gridCellX * widthOfGrid);
		pane.setLayoutY(gridCellY * heightOfGrid);
		
		return pane;
	}

	// Method to create a straight road
	private Pane createStraightRoad() {
		StackPane pane = new StackPane(); // Using StackPane
		
		// Creating an invisible rectangle to capture mouse events
		Rectangle rectangleInvisible = new Rectangle(widthOfGrid, heightOfGrid);
		rectangleInvisible.setOpacity(0);
		
		// Creating a visible rectangle representing the road
		Rectangle rectangle = new Rectangle(widthOfGrid, heightOfGrid * 4.0 / 5.0);
		rectangle.setFill(Color.WHITE);
		
		// Setting rotation of the StackPane 
		pane.setRotate(rotation);
		
		// Adding rectangles to the StackPane
		pane.getChildren().addAll(rectangleInvisible,rectangle);

		return pane; 
	}

	// Method to create a curved road
	private Pane createCurvedRoad() {
		StackPane pane = new StackPane(); // Using StackPane 
		
		// Creating an invisible rectangle to capture mouse events
		Rectangle rectangleInvisible = new Rectangle(widthOfGrid, heightOfGrid);
		rectangleInvisible.setOpacity(0);
		
		// Creating an arc representing the curved road
		Arc arc = new Arc(widthOfGrid/2, heightOfGrid, widthOfGrid * 4 / 5, heightOfGrid * 4 / 5, 0, 90);
		arc.setType(ArcType.ROUND);
		arc.setFill(Color.WHITE);
		
		// Creating rectangles to fill in the gaps
		Rectangle rectangle1 = new Rectangle((widthOfGrid * 4 / 5)-1, heightOfGrid * 1 / 5);
	    rectangle1.setFill(Color.WHITE);
	    rectangle1.setStroke(Color.WHITE);
	    rectangle1.setTranslateY(heightOfGrid / 2);
	    Rectangle rectangle2 = new Rectangle(widthOfGrid * 1 / 5, (heightOfGrid * 4 / 5)-1);
	    rectangle2.setFill(Color.WHITE);
	    rectangle2.setStroke(Color.WHITE);
	    rectangle2.setTranslateX(-widthOfGrid / 2);
	    
	    // Setting rotation of the StackPane 
		pane.setRotate(-rotation);
		
		// Adding elements to the StackPane
		pane.getChildren().addAll(rectangleInvisible, arc, rectangle1, rectangle2);
		
		return pane;
	}

	// Method to create a four-way intersection road
	private Pane createFourIntersectionRoad() {
		StackPane pane = new StackPane(); // Using StackPane
		
		// Creating an invisible rectangle to capture mouse events
		Rectangle rectangleInvisible = new Rectangle(widthOfGrid, heightOfGrid);
		rectangleInvisible.setOpacity(0);
		
		// Creating rectangles
		Rectangle rectangle1 = new Rectangle(widthOfGrid, heightOfGrid * 4 / 5);
		rectangle1.setFill(Color.WHITE);
		Rectangle rectangle2 = new Rectangle(widthOfGrid * 4 / 5, heightOfGrid);
		rectangle2.setFill(Color.WHITE);
		
		// Setting rotation of the StackPane
		pane.setRotate(rotation);
		
		// Adding elements to the StackPane
		pane.getChildren().addAll(rectangleInvisible, rectangle1, rectangle2);

		return pane; 
	}

	// Method to create a three-way intersection road
	private Pane createThreeIntersectionRoad() {
		StackPane pane = new StackPane(); // Using StackPane 
		
		// Creating an invisible rectangle to capture mouse events
		Rectangle rectangleInvisible = new Rectangle(widthOfGrid, heightOfGrid);
		rectangleInvisible.setOpacity(0);
		
		// Creating rectangles
		Rectangle rectangle1 = new Rectangle(widthOfGrid, heightOfGrid * 4 / 5);
		rectangle1.setFill(Color.WHITE);
		Rectangle rectangle2 = new Rectangle(widthOfGrid * 4 / 5, heightOfGrid * 4 / 5);
		rectangle2.setFill(Color.WHITE);
		rectangle2.setTranslateY(heightOfGrid * 1 / 10);
		
		// Setting rotation of the StackPane
		pane.setRotate(rotation);
		
		// Adding elements to the StackPane
		pane.getChildren().addAll(rectangleInvisible, rectangle1, rectangle2);
		
		return pane; // Returning the constructed road pane
	}

	// Getter method for rotation
	public int getRotation() {
		return rotation;
	}

	// Setter method for rotation
	public void setRotation(int rotation) {
		this.rotation = rotation;
	}
}