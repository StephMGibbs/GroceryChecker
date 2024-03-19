package application;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
 
public class HelloWorld extends Application {
    public static void main(String[] args) {
        launch(args); //launch app by passing command line arguments
    }
    
    /**
     * Start. Constructs app, Stage passed in
     *
     * @param primaryStage the primary stage
     */
    @Override
    public void start(Stage primaryStage) { //Stage: Container for all objs - blank window
//        shapes2D (primaryStage);
//        howdyBtn(primaryStage);
//    	formSignIn(primaryStage);
    	scrollPaneEx(primaryStage);
    }
    
    
    public void formSignIn (Stage primaryStage) {
    	//create layout for text, labels, etc to be placed on:
    	GridPane grid = new GridPane(); //GridPane allows grid of rows/cols to place controls
    	grid.setAlignment(Pos.CENTER); //change default position fo grid to center (default top left)
    	grid.setHgap(10); //space between row
    	grid.setVgap(10); //space between col
    	grid.setPadding(new Insets(25, 25, 25, 25)); //space around edges of grid pane (Insets in pixels: top, right, bottom, left)

    	
    	//create text, labels, etc:
    	Text sceneTitle = new Text("Welcome"); //unchangable text
    	sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20)); //font for text, the weight, and size
    	grid.add(sceneTitle, 0, 0, 2, 1); //adds sceneTitle to layout grid @ col 0, row 0, span col to 2 & span row to 1
    	
    	Label userName = new Label("User Name:"); //label text
    	grid.add(userName, 0, 1);
    	
    	TextField userTextField = new TextField(); //text area that can be edited - for username
    	grid.add(userTextField, 1, 1);
    	
    	Label passWord = new Label("Password:");
    	grid.add(passWord, 0, 2);
    	
    	PasswordField pwBox = new PasswordField(); //PasswordField is text area that can be edited but doesn't show what's typed
    	grid.add(pwBox, 1, 2);
    	
//    	grid.setGridLinesVisible(true); //TODO: shows grid layout lines
    	
    	
    	//buttons to submit data & text to display user pressed button:
    	Button btn = new Button("Sign in"); //button with label: Sign in
    	HBox hbBtn = new HBox(10); //HBox layout pane aligns btn independently of grid pane w/ spacing of 10 pixels
    	hbBtn.setAlignment(Pos.BOTTOM_RIGHT); //aligns btn in bottom right of the HBox space
    	hbBtn.getChildren().add(btn); //btn is child HBox pane & HBox pane is added to grid in col 1 & row 4
    	grid.add(hbBtn, 1, 4);
    	
    	
    	//Displays message:
    	final Text actionTarget = new Text();
    	grid.add(actionTarget, 1, 6);
    	
    	
    	//Handle event for button display when user presses it:
    	btn.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent e) {
    			actionTarget.setFill(Color.FIREBRICK); //color of message (red)
    			actionTarget.setText("Sign in button pressed"); //text of message
    		}
    	});
    	
    	
    	Scene scene = new Scene(grid, 300, 275); //keeps grid in center despite window size since root, set start size of 300x275
    	
    	
    	primaryStage.setScene(scene);
    	primaryStage.setTitle("JavaFX Welcome");
    	primaryStage.show();
    }
    
    
    /**
     * Shapes 2D. 2 Dimensional shapes example
     *
     * @param primaryStage the primary stage
     */
    public void shapes2D (Stage primaryStage) {
    	Group root = new Group();// class obj to Group layout
    	
    	Rectangle rect = new Rectangle(); //from javafx.scene.shape package
    	rect.setX(10); //where on stage X-axis
    	rect.setY(20); //where on stage y-axis
    	rect.setWidth(100);
    	rect.setHeight(100);
    	
    	//add objs to group layout
    	root.getChildren().add(rect);
    	
    	Scene scene = new Scene(root, 400, 400); //high level app structure - pass layout obj [can include width/height]
    	
    	//prep stage - set attributes for stage (title, show stage, etc)
    	primaryStage.setScene(scene);
    	primaryStage.setTitle("2 Dimensional Shapes");
    	primaryStage.show();
    }
    
    /**
     * Howdy btn. Button example
     *
     * @param ps the ps
     */
    public void howdyBtn (Stage ps) {
    	Button btn2 = new Button("Say, Howdy Y\'all");
    	
    	btn2.setOnAction(new EventHandler<ActionEvent>() { //define anon class w/ EventHandler param for btn
    		@Override
    		public void handle(ActionEvent arg0) { //code for how event is handled (print text in console)
    			System.out.println("howdy y\'All");
    		}
    	});
    	
    	StackPane root = new StackPane();//scene graph: collection of diff nodes, elements visualized on stage
		//tree structure, 1 root in scene graph at least. Parent for other nodes
    	root.getChildren().add(btn2); //btn node added to root node (parent)
    	
    	Scene scene = new Scene(root); //high level app structure - pass layout obj [can include width/height]
    	
    	//prep stage - set attributes for stage (title, show stage, etc)
    	ps.setScene(scene);
    	ps.setTitle("Alternative Button for Hello World");
    	ps.show();
    }
  
    public void scrollPaneEx (Stage ps) {
    	//create image obj
    	Image img = new Image("/application/funky delirium.png"); //true = located in default package of classpath
    	ImageView imageView = new ImageView();
    	imageView.setImage(img);
    	imageView.setX(5);
    	imageView.setY(0);
    	imageView.setFitWidth(595);
    	imageView.setPreserveRatio(true);
    	
    	ScrollPane scroll = new ScrollPane();
    	scroll.setPrefSize(595, 200);
    	scroll.setContent(imageView);
    	
    	Group root = new Group();
    	root.getChildren().addAll(scroll);
    	Scene scene = new Scene(root, 800, 400, Color.BEIGE);
    	ps.setTitle("Scroll Pane Example");
    	ps.setScene(scene);
    	ps.show();
    }
}
