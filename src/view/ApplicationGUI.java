package view;

import java.sql.Date;
import java.util.ArrayList;

import controller.TheFirmController;
import databasemodel.Employee;
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

public class ApplicationGUI extends Application {

	private BorderPane mainPane;
	private HBox topPane;
	private VBox leftPane;
	private TableView<Employee> centerTable;
	private VBox rightPane;
	private HBox bottomPane;
	private ArrayList<Button> buttons;
	private EventHandler<ActionEvent> eventHandler;

	public ArrayList<Button> getButtons() {
		return buttons;
	}

	private ArrayList tableColumns;
	private MenuBar menuBar;
	private ArrayList<Menu> menus;

	public void kickstartGUIRecources(EventHandler<ActionEvent> eventHandler) {
		System.out.println("GUI kickstartGUIRecources");
		this.eventHandler = eventHandler;
		launch(null);
	}

	public void setupTopPane() {
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

		TableColumn<Employee, String> fname = new TableColumn<Employee, String>("First name");
		fname.setCellValueFactory(new PropertyValueFactory<Employee, String>("fname"));
		fname.setMinWidth(50);

		TableColumn<Employee, String> lname = new TableColumn<Employee, String>("Last name");
		lname.setCellValueFactory(new PropertyValueFactory<Employee, String>("lname"));
		lname.setMinWidth(50);

		TableColumn<Employee, Integer> salary = new TableColumn<Employee, Integer>("Salary");
		salary.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("salary"));
		salary.setMinWidth(50);

		TableColumn<Employee, Date> hireDate = new TableColumn<Employee, Date>("Hire date");
		hireDate.setCellValueFactory(new PropertyValueFactory<Employee, Date>("hire_date"));
		hireDate.setMinWidth(50);

		TableColumn<Employee, Integer> departmentId = new TableColumn<Employee, Integer>("Department ID");
		departmentId.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("department_id"));
		departmentId.setMinWidth(100);

		tableColumns = new ArrayList<>();

		tableColumns.add(fname);
		tableColumns.add(lname);
		tableColumns.add(salary);
		tableColumns.add(hireDate);
		tableColumns.add(departmentId);

		centerTable.getColumns().addAll(tableColumns);
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
			button.setPrefSize(100, 20);
			button.setOnAction(eventHandler);
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		System.out.println("GUI start");
		setupTopPane();
		setUpButtons();
		setupCenterPane();
		setupLeftPane();
		setupMainPane();

		Scene scene = new Scene(mainPane, 600, 300);

		primaryStage.setScene(scene);
		primaryStage.setTitle("The Firm");
		primaryStage.show();
	}
	

}
