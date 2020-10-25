package visualizer;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import model.market.MarketUser;
import model.market.UserListener;

import java.util.HashMap;
import java.util.Map;

public class MarketUserDisplay extends Parent implements UserListener {

  private double myX;
  private double myY;
  private int myMoney;
  private Map<String,Integer> myResources;
  private Text myMoneyDisplay;

  public MarketUserDisplay(double xPos, double yPos, MarketUser user) {
    user.addListener(this);
    myX = xPos;
    myY = yPos;
    myMoney = 0;
    myResources = new HashMap<>();
    myMoneyDisplay = new Text(Integer.toString(myMoney));
    myMoneyDisplay.setTranslateX(myX);
    myMoneyDisplay.setTranslateY(myY);
    getChildren().add(myMoneyDisplay);
  }

  @Override
  public void reactToMoneyChange(int amount) {
    myMoney += amount;
    myMoneyDisplay.setText(Integer.toString(myMoney));
  }

  @Override
  public void reactToResourceChange(String resourceType, int amount) {
    myResources.putIfAbsent(resourceType,0);
    myResources.put(resourceType,myResources.get(resourceType)+amount);
  }
}
