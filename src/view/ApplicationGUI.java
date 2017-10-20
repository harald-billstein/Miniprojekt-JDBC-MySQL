package view;

import controller.TheFirmController.Observers;
import java.sql.Date;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.EmployeeObservable;

public class ApplicationGUI {

  private BorderPane mainPane;
  private HBox topPane;
  private VBox leftPane;
  private TableView<EmployeeObservable> centerTable;
  private VBox rightPane;
  private HBox bottomPane;
  private ArrayList<Button> buttons;
  private Observers observers;
  private Stage primaryStage;
  private ArrayList tableColumns;
  private final static int APPLICATION_WINDOW_WIDTH = 1000;
  private final static int APPLICATION_WINDOW_HEIGHT = 300;

  public void setupTopPane() {
    System.out.println("setupTopPane");
    Menu menuFile = new Menu("About");
    MenuItem menuItem = new MenuItem("Git");
    menuItem.setOnAction(observers.getActionEvent());
    menuFile.getItems().add(menuItem);

    MenuBar menuBar = new MenuBar();
    menuBar.autosize();
    menuBar.getMenus().addAll(menuFile);

    topPane = new HBox();
    topPane.setAlignment(Pos.BASELINE_RIGHT);
    topPane.getChildren().add(menuBar);
  }

  public void setupLeftPane() {
    System.out.println("setupleftpane");
    leftPane = new VBox();
    leftPane.getChildren().addAll(buttons);
  }

  public void setupCenterPane() {
    System.out.println("setupCenterPane");
    centerTable = new TableView<EmployeeObservable>();
    centerTable.setEditable(true);
    centerTable.setOnMouseClicked(observers.getMouseEvent());

    TableColumn<EmployeeObservable, String> fname = new TableColumn<>("First name");
    fname.setCellValueFactory(new PropertyValueFactory<>("firstName"));
    fname.setMinWidth(50);

    TableColumn<EmployeeObservable, String> lname = new TableColumn<>("Last name");
    lname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
    lname.setMinWidth(50);

    TableColumn<EmployeeObservable, Integer> salary = new TableColumn<>("Salary");
    salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
    salary.setMinWidth(50);

    TableColumn<EmployeeObservable, Date> hireDate = new TableColumn<>("Hire date");
    hireDate.setCellValueFactory(new PropertyValueFactory<>("hireDate"));
    hireDate.setMinWidth(50);

    TableColumn<EmployeeObservable, String> departmentName = new TableColumn<>("Department name");
    departmentName.setCellValueFactory(new PropertyValueFactory<>("departmentName"));
    departmentName.setMinWidth(50);

    TableColumn<EmployeeObservable, Integer> departmentRent = new TableColumn<>("Department rent");
    departmentRent.setCellValueFactory(new PropertyValueFactory<>("departmentRent"));
    departmentRent.setMinWidth(100);

    TableColumn<EmployeeObservable, String> departmentPhonenumber =
        new TableColumn<>("Department phonenumber");
    departmentPhonenumber.setCellValueFactory(new PropertyValueFactory<>("departmentPhoneNumber"));
    departmentPhonenumber.setMinWidth(50);

    TableColumn<EmployeeObservable, String> companyCarRegNr = new TableColumn<>("Car regnr.");
    companyCarRegNr.setCellValueFactory(new PropertyValueFactory<>("companyCarRegNr"));
    companyCarRegNr.setMinWidth(50);

    TableColumn<EmployeeObservable, String> companyCarBrand = new TableColumn<>("car brand");
    companyCarBrand.setCellValueFactory(new PropertyValueFactory<>("companyCarBrand"));
    companyCarBrand.setMinWidth(50);

    TableColumn<EmployeeObservable, String> companyCarModel = new TableColumn<>("car model");
    companyCarModel.setCellValueFactory(new PropertyValueFactory<>("companyCarModel"));
    companyCarModel.setMinWidth(50);

    TableColumn<EmployeeObservable, Integer> companyCarPurchasePrice =
        new TableColumn<>("Purchase price");
    companyCarPurchasePrice
        .setCellValueFactory(new PropertyValueFactory<>("companyCarPurchasePrise"));
    companyCarPurchasePrice.setMinWidth(50);

    TableColumn<EmployeeObservable, Date> companyCarPurchaseDate =
        new TableColumn<>("Purchase date");
    companyCarPurchaseDate
        .setCellValueFactory(new PropertyValueFactory<>("companyCarPurchaseDate"));
    companyCarPurchaseDate.setMinWidth(50);

    tableColumns = new ArrayList<>();

    tableColumns.add(fname);
    tableColumns.add(lname);
    tableColumns.add(salary);
    tableColumns.add(hireDate);

    tableColumns.add(departmentName);
    tableColumns.add(departmentRent);
    tableColumns.add(departmentPhonenumber);

    tableColumns.add(companyCarRegNr);
    tableColumns.add(companyCarBrand);
    tableColumns.add(companyCarModel);
    tableColumns.add(companyCarPurchasePrice);
    tableColumns.add(companyCarPurchaseDate);

    centerTable.getColumns().addAll(tableColumns);
  }

  public TableView<EmployeeObservable> getCenterTable() {
    return centerTable;
  }

  public void setupMainPane() {
    System.out.println("setup Mainpane");
    mainPane = new BorderPane();
    mainPane.setCenter(centerTable);
    mainPane.setTop(topPane);
    mainPane.setLeft(leftPane);
    mainPane.setRight(rightPane);
    mainPane.setBottom(bottomPane);
  }

  public void setUpButtons() {
    System.out.println("setup buttons");

    Button search = new Button("Search");
    search.setId("MainMenuSearchButton");
    Button add = new Button("Add");
    add.setId("MainMenuAddButton");
    Button remove = new Button("Remove");
    remove.setId("MainMenuRemoveButton");
    Button edit = new Button("Edit");
    edit.setId("MainMenuEditButton");
    Button resetTableView = new Button("Reset table view");
    resetTableView.setId("MainMenuResetButton");

    buttons = new ArrayList<>();
    buttons.add(search);
    buttons.add(add);
    buttons.add(remove);
    buttons.add(edit);
    buttons.add(resetTableView);

    for (Button button : buttons) {
      button.setMinWidth(100);
      button.setOnAction(observers.getActionEvent());
    }
  }

  public void start() {
    System.out.println("GUI start");
    setupTopPane();
    setupCenterPane();
    setUpButtons();
    setupLeftPane();
    setupMainPane();

    Scene scene = new Scene(mainPane, APPLICATION_WINDOW_WIDTH, APPLICATION_WINDOW_HEIGHT);

    primaryStage.setScene(scene);
    primaryStage.setOnCloseRequest(observers.getWindowEvent());
    primaryStage.setTitle("The Firm");
    primaryStage.show();
  }

  public void setPrimaryStage(Stage primaryStage) {
    this.primaryStage = primaryStage;
  }

  public Stage getPrimaryStage() {
    return this.primaryStage;
  }

  public ArrayList<Button> getButtons() {
    return buttons;
  }

  public void setObservers(Observers observers) {
    this.observers = observers;

  }


}
