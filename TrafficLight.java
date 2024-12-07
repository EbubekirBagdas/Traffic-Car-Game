import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

//Ali Doğan Pekdaş 150123014
//Ebubekir Bağdaş 150122053
//Doğukan Şahin 150122043
public class TrafficLight extends Pane {
    // Declaration of instance variables
    private double x1;
    private double x2;
    private double y1;
    private double y2;
    private boolean isGreen = true; // Boolean variable to track if the traffic light is green
    private Circle circle; // Circle representing the traffic light

    // Constructor to initialize TrafficLight object
    public TrafficLight(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        createTrafficLights(); // Invoking the method
    }

    // Method to create the traffic light
    void createTrafficLights() {
        Line line = new Line(x1, y1, x2, y2); // Creating a line to represent the pole of the traffic light
        circle = new Circle((x2 + x1) / 2, (y2 + y1) / 2, 6); // Creating a circle to represent the light
        circle.setFill(Color.GREEN); // Setting initial color of the circle to green
        getChildren().addAll(line, circle); // Adding the line and circle to the pane
        circle.setOnMouseClicked(event -> changeColor()); // Adding event listener to the circle for changing color
    }
    
    // Method to change the color of the traffic light
    public void changeColor() {
        Color current = (Color) circle.getFill(); // Getting the current fill color of the circle
        if (current.equals(Color.RED)) { // If the current color is red, change to green
            circle.setFill(Color.GREEN);
            isGreen = true; // Updating the boolean variable to indicate green light
        } else { // If the current color is green, change to red
            circle.setFill(Color.RED);
            isGreen = false; // Updating the boolean variable to indicate red light
        }
    }

    // Getter method to check if the traffic light is green
    public boolean isGreen() {
        return isGreen;
    }

    // Getter method to access the circle representing the traffic light
    public Circle getCircle() {
        return circle;
    }
}