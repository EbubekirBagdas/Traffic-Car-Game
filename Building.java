import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.layout.GridPane;

//Ali Doğan Pekdaş 150123014
//Ebubekir Bağdaş 150122053
//Doğukan Şahin 150122043
public class Building extends Pane {
    // Declaration of instance variables
	private int buildingOfType;
	private int rotation;
	private int colorIndex;
	private int gridCellX;
	private int gridCellY;
	private double widthOfGrid = 800.0 / 15;
	private double heightOfGrid = 800.0 / 15;
	
	// Constructor to initialize Building object
	public Building(int buildingOfType, int rotation, int colorIndex, int gridCellX, int gridCellY) {
		this.buildingOfType = buildingOfType;
		this.rotation = rotation;
		this.colorIndex = colorIndex;
		this.gridCellX = gridCellX;
		this.gridCellY = gridCellY;
	}

	// Method to create building based on buildingOfType
	public Pane BuildingMaker() {
		Pane pane = new Pane(); // Creating a new Pane to hold the building
		
		// Switching between different building types to create the appropriate building
		switch (this.buildingOfType) {
		case 0:
			pane = createSquareBuilding();
			pane.setLayoutX((gridCellX+0.5) *widthOfGrid);
			pane.setLayoutY((gridCellY-0.5) *heightOfGrid);
			return pane;
		case 1:
			pane = createCircleBuilding();
			pane.setLayoutX(gridCellX * widthOfGrid);
			pane.setLayoutY(gridCellY * heightOfGrid);
			return pane;
		case 2:
			pane = createHouse();
			pane.setLayoutX(gridCellX * widthOfGrid);
			pane.setLayoutY(gridCellY * heightOfGrid);
			return pane;
		default:
			return null;
		}
	}

	// Method to create a square building
	private Pane createSquareBuilding() {
		GridPane pane = new GridPane(); // Using GridPane 
		
		// Creating a large rectangle to represent the main building structure
		Rectangle rect = new Rectangle(widthOfGrid * 2, heightOfGrid * 3);
		rect.setFill(Color.rgb(239, 249, 255)); // Setting fill with color scale
		rect.setStroke(Color.rgb(130, 152, 166)); // Setting stroke color
		rect.setStrokeWidth(3); // Setting stroke width
		rect.setArcWidth(20); // Setting arc width for rounded corners
		rect.setArcHeight(20); // Setting arc height for rounded corners
		
		// Creating smaller rectangles to represent windows
		Rectangle r = new Rectangle(widthOfGrid * 1.7, heightOfGrid * 1.7);
		r.setFill(fillColor(colorIndex)); // Setting fill color dynamically
		r.setStroke(strokeColor(colorIndex)); // Setting stroke color dynamically
		r.setStrokeWidth(4); // Setting stroke width
		r.setArcWidth(20); // Setting arc width for rounded corners
		r.setArcHeight(20); // Setting arc height for rounded corners
		Rectangle r1 = new Rectangle(widthOfGrid * 1.4, heightOfGrid * 1.4);
		r1.setFill(fillColor(colorIndex)); // Setting fill color dynamically
		r1.setStroke(strokeColor(colorIndex)); // Setting stroke color dynamically
		r1.setStrokeWidth(4); // Setting stroke width
		r1.setArcWidth(20); // Setting arc width for rounded corners
		r1.setArcHeight(20); // Setting arc height for rounded corners
		
		// Setting alignment and margin for smaller rectangles within the grid
		GridPane.setHalignment(r, HPos.CENTER);
		GridPane.setValignment(r, VPos.TOP);
		GridPane.setMargin(r, new Insets(8, 0, 0, 0));
		GridPane.setHalignment(r1, HPos.CENTER);
		GridPane.setValignment(r1, VPos.TOP);
		GridPane.setMargin(r1, new Insets(16, 0, 0, 0));
		
		pane.setRotate(-rotation); // Rotating the GridPane
		pane.getChildren().addAll(rect,r,r1); // Adding all elements to the GridPane
		
		return pane; 
	}

	// Method to create a circular building
	private Pane createCircleBuilding() {
		GridPane pane = new GridPane(); // Using GridPane 
		
		// Creating a large rectangle to represent the main building structure
		Rectangle rect1 = new Rectangle(widthOfGrid * 2, heightOfGrid * 3);
		rect1.setFill(Color.rgb(239, 249, 255)); // Setting fill color
		rect1.setStroke(Color.rgb(130, 152, 166)); // Setting stroke color
		rect1.setStrokeWidth(3); // Setting stroke width
		rect1.setArcWidth(20); // Setting arc width for rounded corners
		rect1.setArcHeight(20); // Setting arc height for rounded corners
		
		// Creating circles to represent windows
		Circle circle = new Circle(widthOfGrid * 2 / 2.5);
		circle.setFill(fillColor(colorIndex)); // Setting fill color dynamically
		circle.setStroke(strokeColor(colorIndex)); // Setting stroke color dynamically
		circle.setStrokeWidth(4); // Setting stroke width
		
		Circle circle1 = new Circle(widthOfGrid * 2 / 3.2);
		circle1.setFill(fillColor(colorIndex)); // Setting fill color dynamically
		circle1.setStroke(strokeColor(colorIndex)); // Setting stroke color dynamically
		circle1.setStrokeWidth(4); // Setting stroke width
		
		// Setting alignment and margin for circles within the grid
		GridPane.setHalignment(circle, HPos.CENTER);
		GridPane.setValignment(circle, VPos.TOP);
		GridPane.setMargin(circle, new Insets(8, 0, 0, 0));
		GridPane.setHalignment(circle1, HPos.CENTER);
		GridPane.setValignment(circle1, VPos.TOP);
		GridPane.setMargin(circle1, new Insets(16, 0, 0, 0));
		
		pane.setRotate(-rotation); // Rotating the GridPane
		pane.getChildren().addAll(rect1, circle, circle1); // Adding all elements to the GridPane
		
		return pane; 
	}

	// Method to create a house-like building
	private Pane createHouse() {
		GridPane pane = new GridPane(); // Using GridPane 
		
		// Creating a rectangle to represent the house structure
		Rectangle r2 = new Rectangle(widthOfGrid, heightOfGrid);
		r2.setFill(fillColor(colorIndex)); // Setting fill color dynamically
		r2.setStroke(strokeColor(colorIndex)); // Setting stroke color dynamically
		r2.setStrokeWidth(4); // Setting stroke width
		r2.setArcWidth(20); // Setting arc width for rounded corners
		r2.setArcHeight(20); // Setting arc height for rounded corners
		
		pane.getChildren().add(r2); // Adding the rectangle to the GridPane
		
		return pane; 
	}
	
	// Method to dynamically determine fill color based on colorIndex
	private Paint fillColor(int colorIndex) {
		Color[] color = { Color.rgb(150,150,10), Color.rgb(50,205,30), Color.rgb(135,206,255), Color.rgb(255,10,10) };
		return color[colorIndex];
	}
	
	// Method to dynamically determine stroke color based on colorIndex
	private Paint strokeColor(int colorIndex) {
		Color[] color = { Color.rgb(180,100,50), Color.rgb(10,180,10), Color.rgb(135,190,200), Color.rgb(200,40,40) };
		return color[colorIndex];
	}
}