import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
//Ali Doğan Pekdaş 150123014
//Ebubekir Bağdaş 150122053
//Doğukan Şahin 150122043
public class PathObject extends Path {
    // Declaration of instance variables
    private int indexOfPath; // Index of the path object
    private String typeOfPath; // Type of path object ("MoveTo" or "LineTo")
    private double xPosition; // X coordinate of the path object
    private double yPosition; // Y coordinate of the path object

    // Constructor to initialize PathObject object
    public PathObject(int indexOfPath, String typeOfPath, double xPosition, double yPosition) {
        this.indexOfPath = indexOfPath;
        this.typeOfPath = typeOfPath;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        initializing(); // Method to initialize the path object
    }

    // Method to initialize the path object
    public void initializing() {
        if (typeOfPath.equals("MoveTo")) { // If type of path is "MoveTo"
            getElements().add(new MoveTo(xPosition, yPosition)); // Add a MoveTo element to the path
        } else if (typeOfPath.equals("LineTo")) { // If type of path is "LineTo"
            if (getElements().isEmpty()) // If path is empty
                getElements().add(new LineTo(xPosition, yPosition)); // Add a LineTo element to the path
        }
    }

    // Getter method to retrieve the index of the path object
    public int getIndexOfPath() {
        return indexOfPath;
    }

    // Setter method to set the index of the path object
    public void setIndexOfPath(int indexOfPath) {
        this.indexOfPath = indexOfPath;
    }

    // Getter method to retrieve the type of the path object
    public String getTypeOfPath() {
        return typeOfPath;
    }

    // Setter method to set the type of the path object
    public void setTypeOfPath(String typeOfPath) {
        this.typeOfPath = typeOfPath;
    }

    // Getter method to retrieve the X coordinate of the path object
    public double getxPosition() {
        return xPosition;
    }

    // Setter method to set the X coordinate of the path object
    public void setxPosition(double xPosition) {
        this.xPosition = xPosition;
    }

    // Getter method to retrieve the Y coordinate of the path object
    public double getyPosition() {
        return yPosition;
    }

    // Setter method to set the Y coordinate of the path object
    public void setyPosition(double yPosition) {
        this.yPosition = yPosition;
    }
}