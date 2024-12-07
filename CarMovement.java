import javafx.animation.AnimationTimer;
import javafx.animation.PathTransition;
import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

import java.util.ArrayList;
//Ali Doğan Pekdaş 150123014
//Ebubekir Bağdaş 150122053
//Doğukan Şahin 150122043
public class CarMovement extends Pane {
    // Declaration of instance variables
    private AnimationTimer timer;
    private double time = 2; // Timer variable for car spawn rate
    private final ArrayList<TrafficLight> trafficLights; // List of traffic lights
    private ArrayList<PathTransition> pts = new ArrayList<>(); // List of path transitions for cars
    private Path path; // Path for car movement
    private int collisionsTime = 0; // Counter for collisions
    private int numOfArrive = 0; // Counter for cars arriving at their destination
    private GridPane scorePane; // Pane to display score and crash count

    // Constructor to initialize CarMovement object
    public CarMovement(ArrayList<TrafficLight> trafficLights, Path path) {
        this.trafficLights = trafficLights; // Initializing list of traffic lights
        this.path = path; // Initializing path for car movement
        createScorePane(); // Method to create score and crash count pane
        getChildren().add(scorePane); // Adding score and crash count pane to the CarMovement pane
        createTraffic(); // Method to create traffic animation
    }

    // Method to create traffic animation
    void createTraffic() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update(); // Method to update car movement and collisions
            }
        };
        timer.start(); // Starting the animation timer
    }

    // Method to update car movement and collisions
    void update() {
        time += 0.016; // Assuming frame rate is 60 FPS
        // Implement the logic for car collisions
        if (time > 2) {
            if (Math.random() < 0.3) { // Randomly spawn cars with a 30% chance
                spawnCar(); // Method to spawn a car
            }
            time = 0; // Resetting the timer
        }
        checkCarIntersection(); // Method to check for car collisions
        stopCar(); // Method to stop cars at red traffic lights
    }

    // Method to stop cars at red traffic lights
    void stopCar() {
        for (PathTransition pt : pts) {
            Rectangle car = (Rectangle) pt.getNode(); // Getting the car node from path transition
            for (TrafficLight trafficLight : trafficLights) {
                if (!trafficLight.isGreen() && isCarNearLight(car, trafficLight)) { // Checking if traffic light is red and car is near it
                    pt.pause(); // Pausing the path transition
                }
            }
        }
    }

    // Method to check if car is near a traffic light
    boolean isCarNearLight(Rectangle car, TrafficLight trafficlight) {
        double distance = Math.sqrt(Math.pow(car.getLayoutX() - trafficlight.getLayoutX(), 2) + Math.pow(car.getLayoutY() - trafficlight.getLayoutY(), 2));
        return distance < 10; // Threshold distance for car nearness to traffic light
    }

    // Method to spawn a car
    void spawnCar() {
        Rectangle car = new Rectangle(20, 10); // Creating a rectangle to represent a car
        car.setFill(Color.BLUE); // Setting car color to blue

        PathTransition pathTransition = new PathTransition(Duration.seconds(15), path, car); // Creating path transition for car movement
        pathTransition.setRate(1.0); // Setting path transition rate
        pathTransition.setCycleCount(1); // Setting path transition cycle count
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT); // Setting path transition orientation
        pathTransition.play(); // Playing path transition
        pathTransition.setOnFinished(event -> removeCarIncreaseArrive(car)); // Setting event handler for when car reaches its destination
        getChildren().add(car); // Adding car to the CarMovement pane
    }

    // Method to remove car from scene and increase arrival count
    void removeCarIncreaseArrive(Rectangle car) {
        getChildren().remove(car); // Removing car from scene
        numOfArrive++; // Increasing arrival count
        updateScorePane(); // Updating score and crash count pane
    }

    // Method to check for car collisions
    void checkCarIntersection() {
        // Get a list of all cars in the scene
        ArrayList<Node> cars = new ArrayList<>(getChildren().filtered(node -> node instanceof Rectangle && ((Rectangle) node).getFill() == Color.BLUE));
        
        // Check for intersections between cars
        for (int i = 0; i < cars.size(); i++) {
            Rectangle car1 = (Rectangle) cars.get(i);
            for (int j = i + 1; j < cars.size(); j++) {
                Rectangle car2 = (Rectangle) cars.get(j);
                if (car1.getBoundsInParent().intersects(car2.getBoundsInParent())) { // Checking if two cars intersect
                    // Stop the cars' movement
                    car1.setLayoutX(car1.getLayoutX() - car1.getX() / car1.getWidth());
                    car1.setLayoutY(car1.getLayoutY() - car1.getY() / car1.getHeight());
                    car2.setLayoutX(car2.getLayoutX() - car2.getX() / car2.getWidth());
                    car2.setLayoutY(car2.getLayoutY() - car2.getY() / car2.getHeight());
                    
                    // Dimming the color of colliding cars
                    Color originalColor = (Color) car1.getFill();
                    Color dimmedColor = Color.color(originalColor.getRed(), originalColor.getGreen(), originalColor.getBlue(), originalColor.getOpacity() / 2);
                    car1.setFill(dimmedColor);
                    car2.setFill(dimmedColor);
                    
                    // After a delay, remove the cars from the scene
                    PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
                    pause.setOnFinished(event -> getChildren().removeAll(car1, car2));
                    pause.play();
                    collisionsTime++; // Incrementing collision count
                    updateScorePane(); // Updating score and crash count pane
                }
            }
        }
    }

    // Method to create score and crash count pane
    public void createScorePane() {
        scorePane = new GridPane(); // Creating a grid pane for score and crash count
        scorePane.setAlignment(Pos.TOP_LEFT); // Setting alignment of grid pane
        scorePane.setPadding(new Insets(10)); // Setting padding of grid pane
        scorePane.setHgap(10); // Setting horizontal gap of grid pane

        Label scoreLabel = new Label("Score: " + numOfArrive); // Creating label for score count
        scoreLabel.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 15)); // Setting font for score label
        Label crashesLabel = new Label("Crashes: " + collisionsTime); // Creating label for crash count
        crashesLabel.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 15)); // Setting font for crash label

        scorePane.add(scoreLabel, 0, 0); // Adding score label to grid pane
        scorePane.add(crashesLabel, 0, 1); // Adding crash label to grid pane
    }

    // Method to update score and crash count pane
    public void updateScorePane() {
        Label scoreLabel = (Label) scorePane.getChildren().get(0); // Retrieving score label from grid pane
        Label crashesLabel = (Label) scorePane.getChildren().get(1); // Retrieving crash label from grid pane

        scoreLabel.setText("Score: " + numOfArrive); // Updating score label
        crashesLabel.setText("Crashes: " + collisionsTime); // Updating crash label
    }

    // Getter method for collision count
    public int getCollisionsTime() {
        return collisionsTime;
    }

    // Getter method for arrival count
    public int getNumOfArrive() {
        return numOfArrive;
    }
}