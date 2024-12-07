import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;

//Ali Doğan Pekdaş 150123014
//Ebubekir Bağdaş 150122053
//Doğukan Şahin 150122043
public class Test extends Application {
    // ArrayLists to hold data for various map elements
    private static ArrayList<Metadata> metadatas = new ArrayList<>();
    private static ArrayList<RoadTile> roadTiles = new ArrayList<>();
    private static ArrayList<Building> buildings = new ArrayList<>();
    private static ArrayList<TrafficLight> trafficLights = new ArrayList<>();
    private static ArrayList<PathObject> paths = new ArrayList<>();
    private static ArrayList<Line> lines = new ArrayList<>();

    // Method to start the application
    public void start(Stage primaryStage) {
        // Creating a grid pane for the initial menu screen
        GridPane pane = new GridPane();
        // Adding an image view to the grid pane
        ImageView imageView = new ImageView("image.jpg");
        imageView.setFitWidth(800);
        imageView.setFitHeight(600);
        pane.add(imageView, 0, 1);
        pane.setAlignment(Pos.CENTER);

        // Creating a "Start Game" button
        Button btStart = new Button("           Start Game           ");
        // Adding the "Start Game" button to the grid pane
        pane.add(btStart, 0, 0);
        btStart.setTranslateX(322.6);
        btStart.setTranslateY(520);
        // Event handler for the "Start Game" button
        btStart.setOnAction(event -> paneLevel(pane, primaryStage));

        // Creating a scene with the grid pane
        Scene scene = new Scene(pane, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to display level selection menu
    public void paneLevel(GridPane pane, Stage primaryStage) {
        primaryStage.close();
        GridPane temp = new GridPane();
        temp.setStyle("-fx-background-color: aliceblue;");
        temp.setAlignment(Pos.CENTER);
        temp.setVgap(10);
        temp.getChildren().addAll(pane.getChildren());

        // Creating buttons for different levels
        Button btLevel1 = new Button("  Level 1  ");
        Button btLevel2 = new Button("  Level 2  ");
        Button btLevel3 = new Button("  Level 3  ");
        Button btLevel4 = new Button("  Level 4  ");
        Button btLevel5 = new Button("  Level 5  ");
        Button btBack = new Button("    Back    ");
        temp.getChildren().clear();
        // Adding buttons to the grid pane
        temp.add(btLevel1, 0, 1);
        temp.add(btLevel2, 0, 2);
        temp.add(btLevel3, 0, 3);
        temp.add(btLevel4, 0, 4);
        temp.add(btLevel5, 0, 5);
        temp.add(btBack, 0, 6);
        // Event handler for the "Back" button
        btBack.setOnAction(event -> start(primaryStage));

        // Setting up the scene with the grid pane
        primaryStage.setScene(new Scene(temp, 800, 800));
        primaryStage.show();
        // Event handlers for level selection buttons
        btLevel1.setOnAction(event -> {
            try {
                loadLevel("level1.txt");
                displayMap(primaryStage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        btLevel2.setOnAction(event -> {
            try {
                loadLevel("level2.txt");
                displayMap(primaryStage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        btLevel3.setOnAction(event -> {
            try {
                loadLevel("level3.txt");
                displayMap(primaryStage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        btLevel4.setOnAction(event -> {
            try {
                loadLevel("level4.txt");
                displayMap(primaryStage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        btLevel5.setOnAction(event -> {
            try {
                loadLevel("level5.txt");
                displayMap(primaryStage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    // Method to load level data from a file
    public void loadLevel(String filename) throws FileNotFoundException {
        clearArrayLists();
        loadVariables(filename);
    }

    // Method to clear array lists holding level data
    public void clearArrayLists() {
        metadatas.clear();
        roadTiles.clear();
        buildings.clear();
        paths.clear();
        trafficLights.clear();
    }

    // Method to load variables from a file
    public void loadVariables(String filename) throws FileNotFoundException {
        Scanner input = new Scanner(new File(filename));
        while (input.hasNextLine()) {
            String line = input.nextLine();
            String parts[] = line.split(" ");
            switch (parts[0]) {
                case "Metadata":
                    // Parsing metadata from the input line and adding it to the metadata array list
                    double width = Double.parseDouble(parts[1]);
                    double height = Double.parseDouble(parts[2]);
                    int numOfGridCellsInXDirection = Integer.parseInt(parts[3]);
                    int numOfGridCellsInYDirection = Integer.parseInt(parts[4]);
                    int numOfPaths = Integer.parseInt(parts[5]);
                    int numOfCarsToArrive = Integer.parseInt(parts[6]);
                    int numOfCarsAccident = Integer.parseInt(parts[7]);
                    metadatas.add(new Metadata(width, height, numOfGridCellsInXDirection, numOfGridCellsInYDirection,
                            numOfPaths, numOfCarsToArrive, numOfCarsAccident));
                    break;
                case "RoadTile":
                    // Parsing road tile data from the input line and adding it to the roadTiles array list
                    int roadType = Integer.parseInt(parts[1]);
                    int rotation = Integer.parseInt(parts[2]);
                    double gridCellX = Integer.parseInt(parts[3]);
                    double gridCellY = Integer.parseInt(parts[4]);
                    roadTiles.add(new RoadTile(roadType, rotation, gridCellX, gridCellY));
                    break;
                case "Building":
                    // Parsing building data from the input line and adding it to the buildings array list
                    int buildingOfType = Integer.parseInt(parts[1]);
                    int rotationB = Integer.parseInt(parts[2]);
                    int colorIndex = Integer.parseInt(parts[3]);
                    int gridCellXB = Integer.parseInt(parts[4]);
                    int gridCellYB = Integer.parseInt(parts[5]);
                    buildings.add(new Building(buildingOfType, rotationB, colorIndex, gridCellXB, gridCellYB));
                    break;
                case "TrafficLight":
                    // Parsing traffic light data from the input line and adding it to the trafficLights array list
                    double x1 = Double.parseDouble(parts[1]);
                    double y1 = Double.parseDouble(parts[2]);
                    double x2 = Double.parseDouble(parts[3]);
                    double y2 = Double.parseDouble(parts[4]);
                    trafficLights.add(new TrafficLight(x1, y1, x2, y2));
                    break;
                case "Path":
                    // Parsing path data from the input line and adding it to the paths array list
                    int indexOfPath = Integer.parseInt(parts[1]);
                    String typeOfPath = parts[2];
                    double xPosition = Double.parseDouble(parts[3]);
                    double yPosition = Double.parseDouble(parts[4]);
                    paths.add(new PathObject(indexOfPath, typeOfPath, xPosition, yPosition));
                    break;
                default:
                    System.out.println("Invalid input line: " + line);
            }
        }
    }

    // Method to create lines based on path objects
    public void createLines(ArrayList<PathObject> paths) {
        lines.clear();

        for (PathObject path : paths) {
            int index = 0;
            for (int i = 0; i < paths.size(); i++) {
                if (paths.get(i).equals(path)) {
                    index = i;
                }
            }

            if (path.getTypeOfPath().equals("LineTo")) {
                double xStart = paths.get(index - 1).getxPosition();
                double yStart = paths.get(index - 1).getyPosition();
                double xEnd = paths.get(index).getxPosition();
                double yEnd = paths.get(index).getyPosition();
                Line line = new Line(xStart, yStart, xEnd, yEnd);
                line.setOpacity(0);
                lines.add(line);
            }
        }
    }

    // Method to combine lines into a single path
    public Path combiningLines() {
        Path newPath = new Path();
        for (PathObject path : paths) {
            int index = 0;
            for (int i = 0; i < paths.size(); i++) {
                if (paths.get(i).equals(path)) {
                    index = i;
                }
            }

            if (path.getTypeOfPath().equals("LineTo")) {
                double xStart = paths.get(index - 1).getxPosition();
                double yStart = paths.get(index - 1).getyPosition();
                double xEnd = paths.get(index).getxPosition();
                double yEnd = paths.get(index).getyPosition();
                Line line = new Line(xStart, yStart, xEnd, yEnd);
                newPath.getElements().add(new MoveTo(xStart, yStart));
                newPath.getElements().add(new LineTo(xEnd, yEnd));
                line.setOpacity(0);
                lines.add(line);
                newPath.setOpacity(0);
            }
        }

        return newPath;
    }

    // Method to display the map with loaded elements
    public void displayMap(Stage primaryStage) {
        Pane pane = new Pane();
        pane.getChildren().addAll(metadatas);

        for (RoadTile roadTile : roadTiles) {
            pane.getChildren().add(roadTile.roadMaker());
        }

        for (Building building : buildings) {
            pane.getChildren().add(building.BuildingMaker());
        }

        pane.getChildren().addAll(trafficLights);
        pane.getChildren().addAll(combiningLines());
        pane.getChildren().add(addCars(combiningLines()));
        Button btQuit = new Button("Quit for Main Menu");
        StackPane pane1 = new StackPane();
        StackPane.setAlignment(btQuit, Pos.TOP_RIGHT);
        pane1.getChildren().addAll(pane, btQuit);
        btQuit.setOnAction(event -> {
            primaryStage.close();
            start(primaryStage);
        });

        Scene scene = new Scene(pane1, 800, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to add cars to the map
    public CarMovement addCars(Path path) {
        CarMovement car = new CarMovement(trafficLights, path);
        return car;
    }

    // Main method to launch the application
    public static void main(String[] args) {
        launch(args);
    }
}