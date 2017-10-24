package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * This class is used to show our Github page
 */
public class WebPagePresenter {

  private Stage webPagePresenter;

  /**
   * Creates a popup and sets the primary stage of this popup
   * @param primaryStage The primary stage
   */
  public WebPagePresenter(Stage primaryStage) {
    webPagePresenter = new Stage();
    webPagePresenter.initOwner(primaryStage);
  }

  public void showGitPage() {
    Scene scene = new Scene(new Group());

    final WebView browser = new WebView();
    final WebEngine webEngine = browser.getEngine();

    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setContent(browser);

    webEngine.load("https://github.com/harald-billstein/Miniprojekt-JDBC-MySQL/blob/master/README.md");

    scene.setRoot(scrollPane);

    webPagePresenter.initModality(Modality.APPLICATION_MODAL);
    webPagePresenter.setScene(scene);
    webPagePresenter.show();
  }

  public void closeWebPagePresenter() {
    webPagePresenter.close();
  }

}
