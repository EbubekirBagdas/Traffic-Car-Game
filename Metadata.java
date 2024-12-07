import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
//Ali Doğan Pekdaş 150123014
//Ebubekir Bağdaş 150122053
//Doğukan Şahin 150122043
public class Metadata extends Pane {
    // Declaration of instance variables
    private double width; // Width of the metadata pane
    private double height; // Height of the metadata pane
    private int numOfGridCellsX; // Number of grid cells in the X direction
    private int numOfGridCellsY; // Number of grid cells in the Y direction
    private int numOfPaths; // Number of paths in the simulation
    private static int numOfCarsToArrive; // Number of cars to arrive at their destination
    private static int numOfCarsAccident; // Number of cars involved in accidents

    // Constructor to initialize Metadata object
    public Metadata(double width, double height, int numOfGridCellsX, int numOfGridCellsY,
                    int numOfPaths, int numOfCarsToArrive, int numOfCarsAccident) {
        this.width = width;
        this.height = height;
        this.numOfGridCellsX = numOfGridCellsX;
        this.numOfGridCellsY = numOfGridCellsY;
        this.numOfPaths = numOfPaths;
        this.numOfCarsToArrive = numOfCarsToArrive;
        this.numOfCarsAccident = numOfCarsAccident;
        createBackGround(); // Invoking the method
    }
    
    // Method to create the background of the metadata pane
    public void createBackGround() {
        setPrefSize(width, height); // Setting the preferred size of the metadata pane
        setStyle("-fx-background-color: rgb(156,199,224);"); // Setting the background color

        double gridCellX = width / numOfGridCellsX; // Calculating the width of each grid cell
        double gridCellY = height / numOfGridCellsY; // Calculating the height of each grid cell

        // Drawing horizontal grid lines
        for (double i = gridCellX; i < width; i += gridCellX) {
            Line line = new Line(0.0, i, width, i); // Creating a horizontal line
            line.setOpacity(0.2); // Setting opacity for the line
            getChildren().add(line); // Adding the line to the metadata pane
        }

        // Drawing vertical grid lines
        for (double i = gridCellY; i < height; i += gridCellY) {
            Line line = new Line(i, 0.0, i, height); // Creating a vertical line
            line.setOpacity(0.2); // Setting opacity for the line
            getChildren().add(line); // Adding the line to the metadata pane
        }
    }

    // Getter method to retrieve the number of paths in the simulation
    public int getNumOfPaths() {
        return numOfPaths;
    }

    // Setter method to set the number of paths in the simulation
    public void setNumOfPaths(int numOfPaths) {
        this.numOfPaths = numOfPaths;
    }

    // Getter method to retrieve the number of cars to arrive at their destination
    public static int getNumOfCarsToArrive() {
        return numOfCarsToArrive;
    }

    // Setter method to set the number of cars to arrive at their destination
    public static void setNumOfCarsToArrive(int numOfCarsToArrive) {
        Metadata.numOfCarsToArrive = numOfCarsToArrive;
    }

    // Getter method to retrieve the number of cars involved in accidents
    public static int getNumOfCarsAccident() {
        return numOfCarsAccident;
    }

    // Setter method to set the number of cars involved in accidents
    public static void setNumOfCarsAccident(int numOfCarsAccident) {
        Metadata.numOfCarsAccident = numOfCarsAccident;
    }
}