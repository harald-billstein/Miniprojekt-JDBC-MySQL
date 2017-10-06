package view;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ApplicationGUI extends Application implements EventHandler<ActionEvent> {

  //private static Stage stage;
  private BorderPane mainPane;
  private HBox topPane;
  private VBox leftPane;
  private TableView<String> centerTable;
  private VBox rightPane;
  private HBox bottomPane;
  private ArrayList buttons;
  private ArrayList tableColumns;
  private MenuBar menuBar;
  private ArrayList menus;


  public void launch() {
	    launch(null);
  }

  public void setupTopPane() {
    Menu menuFile = new Menu("File");
    Menu menuEdit = new Menu("Edit");
    Menu menuView = new Menu("About");

    menus = new ArrayList();
    menus.add(menuFile);
    menus.add(menuEdit);
    menus.add(menuView);

    menuBar = new MenuBar();
    menuBar.setPrefSize(600,12);
    menuBar.getMenus().addAll(menuFile,menuEdit,menuView);

    topPane = new HBox();
    topPane.getChildren().add(menuBar);
  }

  public void setupLeftPane() {
    leftPane = new VBox();
    leftPane.getChildren().addAll(buttons);
  }

  public void setupCenterPane() {
    centerTable = new TableView();
    tableColumns = new ArrayList();

    TableColumn fname = new TableColumn("First name");
    fname.setMinWidth(150);

    TableColumn lname = new TableColumn("Last name");
    lname.setMinWidth(150);
    TableColumn adress = new TableColumn("Adress");
    adress.setMinWidth(150);



    tableColumns.add(fname);
    tableColumns.add(lname);
    tableColumns.add(adress);

    centerTable.getColumns().addAll(fname, lname, adress);
  }

  public void setupMainPane() {
    mainPane = new BorderPane();
    mainPane.setCenter(centerTable);
    mainPane.setTop(topPane);
    mainPane.setLeft(leftPane);
    mainPane.setRight(rightPane);
    mainPane.setBottom(bottomPane);
  }

  public void setUpButtons() {
    buttons = new ArrayList();

    Button search = new Button("Search");
    Button add = new Button("Add");
    Button remove = new Button("Remove");
    Button edit = new Button("Edit");

    search.setPrefSize(100, 20);
    search.setOnAction(this);

    add.setPrefSize(100, 20);
    add.setOnAction(this);

    remove.setPrefSize(100, 20);
    remove.setOnAction(this);

    edit.setPrefSize(100, 20);
    edit.setOnAction(this);

    buttons.add(search);
    buttons.add(add);
    buttons.add(remove);
    buttons.add(edit);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    setUpButtons();
    setupTopPane();
    setupLeftPane();
    setupCenterPane();
    setupMainPane();

    Scene scene = new Scene(mainPane,600,300);

    primaryStage.setScene(scene);
    primaryStage.setTitle("Adress book");
    primaryStage.show();
  }

  @Override
  public void handle(ActionEvent event) {
    Button button = ((Button) event.getSource());

    switch (button.getText()) {
      case "Search":
        Search();
        break;
      case "Add":
        Add();
        break;
      case "Remove":
        Remove();
        break;
      case "Edit":
        Edit();
        break;
      default:
        break;
    }

  }

  private void Edit() {
    System.out.println("Edit");
  }

  private void Remove() {
    System.out.println("Remove");
  }

  private void Add() {
    System.out.println("Add");
  }

  private void Search() {
    System.out.println("Search");
  }
}
