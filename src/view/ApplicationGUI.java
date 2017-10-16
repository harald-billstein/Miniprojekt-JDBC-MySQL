package view;

import java.sql.Date;
import java.util.ArrayList;

import controller.TheFirmController;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Department;
import model.Employee;

public class ApplicationGUI {

	private BorderPane mainPane;
	private HBox topPane;
	private VBox leftPane;
	private TableView<Employee> centerTable;
	private VBox rightPane;
	private HBox bottomPane;
	private ArrayList<Button> buttons;
	private EventHandler<ActionEvent> event;
	private Stage primaryStage;

	public ArrayList<Button> getButtons() {
		return buttons;
	}

	private ArrayList tableColumns;
	private MenuBar menuBar;
	private ArrayList<Menu> menus;

	public void setupTopPane() {
		System.out.println("setupTopPane");
		Menu menuFile = new Menu("File");
		Menu menuEdit = new Menu("Edit");
		Menu menuView = new Menu("About");

		menus = new ArrayList<Menu>();
		menus.add(menuFile);
		menus.add(menuEdit);
		menus.add(menuView);

		menuBar = new MenuBar();
		menuBar.setPrefSize(600, 12);
		menuBar.getMenus().addAll(menuFile, menuEdit, menuView);

		topPane = new HBox();
		topPane.getChildren().add(menuBar);
	}

	public void setupLeftPane() {
		System.out.println("setupleftpane");
		leftPane = new VBox();
		leftPane.getChildren().addAll(buttons);
	}

	public void setupCenterPane() {
		System.out.println("setupCenterPane");
		centerTable = new TableView<Employee>();
		centerTable.setEditable(true);

		TableColumn<Employee, String> fname = new TableColumn<>("First name");
		fname.setCellValueFactory(new PropertyValueFactory<>("fname"));
		fname.setMinWidth(50);

		TableColumn<Employee, String> lname = new TableColumn<>("Last name");
		lname.setCellValueFactory(new PropertyValueFactory<>("lname"));
		lname.setMinWidth(50);

		TableColumn<Employee, Integer> salary = new TableColumn<>("Salary");
		salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
		salary.setMinWidth(50);


		TableColumn<Employee, Date> hireDate = new TableColumn<>("Hire date");
		hireDate.setCellValueFactory(new PropertyValueFactory<>("hire_date"));
		hireDate.setMinWidth(50);
		
		TableColumn<Employee, String> departmentName = new TableColumn<>("Department Name");
		departmentName.setCellValueFactory(new PropertyValueFactory<>("departmentName"));
		departmentName.setMinWidth(50);
		
		TableColumn<Employee, Integer> departmentRent = new TableColumn<>("Dep. Rent");
		departmentRent.setCellValueFactory(new PropertyValueFactory<>("departmentRent"));
		departmentRent.setMinWidth(100);
		
		TableColumn<Employee, String> departmentPhonenumber = new TableColumn<>("Dep. Phonenumber");
		departmentPhonenumber.setCellValueFactory(new PropertyValueFactory<>("departmentPhoneNumber"));
		departmentPhonenumber.setMinWidth(50);
		
		
		
		
		
		TableColumn<Employee, String> companyCarRegNr = new TableColumn<>("Car regnr.");
		companyCarRegNr.setCellValueFactory(new PropertyValueFactory<>("reg_nr"));
		companyCarRegNr.setMinWidth(50);
		
		
		TableColumn<Employee, String> companyCarBrand = new TableColumn<>("car brand");
		companyCarBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
		companyCarBrand.setMinWidth(50);
		
		TableColumn<Employee, String> companyCarModel = new TableColumn<>("car model");
		companyCarModel.setCellValueFactory(new PropertyValueFactory<>("model"));
		companyCarModel.setMinWidth(50);
		
		TableColumn<Employee, Integer> companyCarPurchasePrice = new TableColumn<>("Purchase price");
		companyCarPurchasePrice.setCellValueFactory(new PropertyValueFactory<>("purchase_price"));
		companyCarPurchasePrice.setMinWidth(50);
		
		TableColumn<Employee, Date> companyCarPurchaseDate = new TableColumn<>("Purchase date");
		companyCarPurchaseDate.setCellValueFactory(new PropertyValueFactory<>("purchase_date"));
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

	public TableView<Employee> getCenterTable() {
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
		Button add = new Button("Add");
		Button remove = new Button("Remove");
		Button edit = new Button("Edit");

		buttons = new ArrayList<>();
		buttons.add(search);
		buttons.add(add);
		buttons.add(remove);
		buttons.add(edit);

		for (Button button : buttons) {
			button.setMinWidth(100);
			button.setOnAction(event);
		}
	}

	public void start() {
		System.out.println("GUI start");
		setupTopPane();
		setupCenterPane();
		setUpButtons();
		setupLeftPane();
		setupMainPane();

		Scene scene = new Scene(mainPane, 600, 300);

		primaryStage.setScene(scene);
		primaryStage.setTitle("The Firm");
		primaryStage.show();
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public void setObserver(EventHandler<ActionEvent> event) {
		this.event = event;
	}
}
