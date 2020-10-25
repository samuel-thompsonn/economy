package visualizer;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.market.EconMarket;
import model.market.ManualUser;
import model.market.Market;
import model.market.MarketUser;

public class EconVisualizer extends Application {

  public static final int SCENE_WIDTH = 800;
  public static final int SCENE_HEIGHT = 800;
  public static final String WINDOW_TITLE = "Econonmy";

  private Market mainMarket;
  private ManualUser manualUser;

  @Override
  public void start(Stage primaryStage) throws Exception {
    mainMarket = new EconMarket();
    manualUser = new ManualUser();

    Group myGroup = new Group();
    OfferPrompt prompt = new OfferPrompt();
    prompt.setOnSubmit(a -> dummyOffer(prompt.getOfferType(),"Wood",prompt.getOfferAmount()));
    myGroup.getChildren().add(prompt);
    MarketUserDisplay display = new MarketUserDisplay(10,100, manualUser);
    myGroup.getChildren().add(display);
    OfferList offerList = new OfferList(10,200,mainMarket);
    myGroup.getChildren().add(offerList.getRoot());
    Scene myScene = new Scene(myGroup, SCENE_WIDTH, SCENE_HEIGHT);
    primaryStage.setScene(myScene);
    primaryStage.setTitle(WINDOW_TITLE);
    primaryStage.show();
  }

  private void dummyOffer(String type, String resourceType, int price) {
    mainMarket.makeOffer(type,resourceType,price,manualUser);
  }
}
