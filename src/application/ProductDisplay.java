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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ProductDisplay extends Application {
    
	public static int numTotalProducts = 8; //global var for total number of products to display
	
    /**
     * Start. Constructs app, Stage passed in
     *
     * @param primaryStage the primary stage
     */
    @Override
    public void start(Stage primaryStage) { //Stage: Container for all objs - blank window
    	Group root = new Group();
    	
    	GridPane search = searchBar();
    	search.setLayoutX(245);
    	search.setLayoutY(80);
    	
    	if (numTotalProducts > 3) { //window displays 3 at most, if more then make scrollpane
	    	ScrollPane scroll = new ScrollPane(); //scroll pane of multipleProducts() rows
	    	scroll.setPrefSize(1000, 500); //size of scrollpane
	    	scroll.setLayoutX(265); //placement on stage: x-axis
	    	scroll.setLayoutY(250); //placement on stage: y-axis
	    	scroll.setContent(multipleProducts(primaryStage));
	    	
	    	root.getChildren().addAll(search, scroll);
	    	Scene scene = new Scene(root, Color.ALICEBLUE); //Default stage screen size
	    	primaryStage.setScene(scene);
	    	
    	} else {
    		Rectangle rectBorder = new Rectangle(); //Border
    		rectBorder.setLayoutX(264); 
    		rectBorder.setLayoutY(249); 
    		rectBorder.setWidth(1002);
    		rectBorder.setHeight(502);
    		rectBorder.setFill(Color.SILVER);
    		Rectangle rect = new Rectangle(); //space products are placed
    		rect.setLayoutX(265); 
	    	rect.setLayoutY(250); 
        	rect.setWidth(1000);
        	rect.setHeight(500);
        	rect.setFill(Color.WHITESMOKE);
        	
    		GridPane displayGrid = multipleProducts(primaryStage); //arrange where on stage products will be placed
    		displayGrid.setLayoutX(265);
    		displayGrid.setLayoutY(250);
    		
    		root.getChildren().addAll(search, rectBorder, rect, displayGrid);
    		Scene scene = new Scene(root, Color.ALICEBLUE);
	    	primaryStage.setScene(scene);
    	}
    	
    	primaryStage.setTitle("Example Product layout");
    	primaryStage.show();
    }
    
    
    /**
     * Individual product.
     * Contains:
     *  the image of the product, 
     *  name of brand that owns the product, 
     *  name of the product, 
     *  company owner details 
     *  	- Founder-Owned or Family-Owned (FOB, small biz)
     *  	- Private Equity (ownership not publicly listed/traded, could be 1 corpo firm or multiple invested)
     *  	- Corporate (big business, corpo owns multiple corpos to make 1 large biz)
     *  	- Employee-Owned (worker co-op, profits split among employees equally, democratic control of biz)
     *  	- Unknown (can be mix of private equity and founder still involved, going through a change of hands, merger, hard to distinguish)
     *
     * @return the grid pane
     */
    public GridPane individualProduct() {
    	//create layout for text, labels, etc to be placed on:
    	GridPane grid = new GridPane(); //GridPane allows grid of rows/cols to place controls
    	grid.setAlignment(Pos.CENTER); //change default position fo grid to center (default top left)
    	grid.setHgap(10); //space between row
    	grid.setVgap(5); //space between col
    	grid.setPadding(new Insets(25, 25, 25, 25)); //space around edges of grid pane (Insets in pixels: top, right, bottom, left)

    	
    	//create text, labels, etc:
    	Text sceneTitle = new Text("Brand Name:"); //unchangable text
    	sceneTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 15)); //font for text, the weight, and size
    	grid.add(sceneTitle, 1, 0); //adds sceneTitle to layout grid @ col, row, span col, & span row
    	
    	Label productName = new Label("Product Name:"); //label text
    	grid.add(productName, 1, 1);
    	
    	Label companyOwnerStatus = new Label("Company Owner Details:");
    	grid.add(companyOwnerStatus, 1, 2);
    	
    	Label productDetails = new Label("Product Details:");
    	grid.add(productDetails, 1, 3);
    	
    	//Add image to left of all labels in grid:
    	Image img = new Image("/application/funky delirium.png"); //true = located in default package of classpath
    	ImageView imageView = new ImageView();
    	imageView.setImage(img);
    	//set image to specific size
    	imageView.setFitWidth(100);
    	imageView.setFitHeight(100);
    	imageView.setPreserveRatio(true);
    	grid.add(imageView, 0, 0, 1, 4);
    	
    	//grid.setGridLinesVisible(true); //debug - see lines
    	return grid;
    }
    
    /**
     * GridPane to hold Multiple products in rows.
     * Allows the products to be looked at & scrolled through vertically.
     *
     * @param primaryStage the primary stage
     * @return the grid pane
     */
    public GridPane multipleProducts(Stage primaryStage) {
    	GridPane recyclerGrid = new GridPane(); //holds rows of individualProduct() GridPanes
    	recyclerGrid.setAlignment(Pos.CENTER_LEFT); //change default position fo grid to center (default top left)
    	recyclerGrid.setHgap(20); //space between row
    	recyclerGrid.setVgap(5); //space between col
    	recyclerGrid.setPadding(new Insets(25, 25, 25, 25));
    	
    	for (int i = 0; i < numTotalProducts; i++) {
    		recyclerGrid.add(individualProduct(), 0, i); //adds row with new individualProduct GridPane within
    	}
    	
    	//recyclerGrid.setGridLinesVisible(true); //debug - see lines
    	return recyclerGrid;
    }
    
    public GridPane searchBar() {
    	GridPane searchGrid = new GridPane();
    	searchGrid.setAlignment(Pos.TOP_CENTER); //change default position fo grid to center (default top left)
    	searchGrid.setHgap(5); //space between row
    	searchGrid.setVgap(5); //space between col
    	searchGrid.setPadding(new Insets(20, 20, 20, 20));
    	
    	TextField searchBar = new TextField();
    	searchGrid.add(searchBar, 0, 0); //col, row, span col, & span row
    	searchBar.setPrefWidth(920);
    	
    	//buttons to submit data & text to display user pressed button:
    	Button btnSearch = new Button("Search"); //button with label: Sign in
    	btnSearch.setPrefWidth(80);
    	searchGrid.add(btnSearch, 1, 0);
    	
    	//TODO: change from text to update search results of products
    	//Displays message when button pressed:
    	final Text actionTarget = new Text();
    	searchGrid.add(actionTarget, 0, 4);
    	
    	
    	//Handle event for button display when user presses it:
    	btnSearch.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent e) {
    			actionTarget.setFill(Color.FIREBRICK); //color of message (red)
    			actionTarget.setText("Search button pressed - update products"); //text of message
    		}
    	});
    	
    	return searchGrid;
    }
    
    /**
     * Filter area.
     * Filters are:
     * 	+Include
     * 	+Exclude
     * 	+Order
     * 		-A to Z
     * 		-Z to A
     * 	+Price (???)
     */
    public void filterArea() {
    	
    }
    
    /**
     * The main method. 
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
//        launch(args); //launch app by passing command line arguments
    	launch();
    }
}
