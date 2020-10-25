package visualizer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class OfferPrompt extends Parent {
  public static final int OFFER_TYPE_BUY = 0;
  public static final int OFFER_TYPE_SELL = 1;

  private TextField myOfferAmountField;
  private Button mySubmitButton;
  private ToggleGroup myOfferTypeButtons;
  private double myX;
  private double myY;
  private int myOfferType = OFFER_TYPE_BUY;

  public OfferPrompt() {
    myX = 10;
    myY = 10;
    myOfferAmountField = new TextField();
    myOfferAmountField.setAccessibleHelp("Price");
    myOfferAmountField.setTranslateX(myX + 10);
    myOfferAmountField.setMaxWidth(100);
    myOfferAmountField.setTranslateY(myY + 10);
    myOfferAmountField.setMaxHeight(20);
    getChildren().add(myOfferAmountField);

    mySubmitButton = new Button("Submit");
    mySubmitButton.setTranslateX(myOfferAmountField.getTranslateX() + myOfferAmountField.getMaxWidth() + 10);
    mySubmitButton.setTranslateY(myOfferAmountField.getTranslateY());
    mySubmitButton.setMaxWidth(40);
    getChildren().add(mySubmitButton);

    myOfferTypeButtons = new ToggleGroup();
    RadioButton buyButton = new RadioButton("Buy");
    buyButton.setToggleGroup(myOfferTypeButtons);
    buyButton.setSelected(true);
    buyButton.setOnAction(x ->setOfferType(OFFER_TYPE_BUY));
    buyButton.setTranslateX(mySubmitButton.getTranslateX() + + mySubmitButton.getMaxWidth() + 10);
    buyButton.setTranslateY(mySubmitButton.getTranslateY());

    RadioButton sellButton = new RadioButton("Sell");
    sellButton.setToggleGroup(myOfferTypeButtons);
    sellButton.setOnAction(x->setOfferType(OFFER_TYPE_SELL));
    getChildren().add(buyButton);
    getChildren().add(sellButton);
    sellButton.setTranslateX(mySubmitButton.getTranslateX() + mySubmitButton.getMaxWidth() + 10);
    sellButton.setTranslateY(mySubmitButton.getTranslateY() + 30);
  }

  public int getOfferAmount() {
    String amount = myOfferAmountField.getText();
    try {
      return Integer.parseInt(amount);
    } catch (NumberFormatException e) {
      return 1;
    }
  }

  public void setOnSubmit(EventHandler<ActionEvent> action) {
    mySubmitButton.setOnAction(action);
  }

  public String getOfferType() {
    if (myOfferType == OFFER_TYPE_BUY) {
      return "Buy";
    }
    else if (myOfferType == OFFER_TYPE_SELL) {
      return "Sell";
    }
    return "NONE";
  }

  private void setOfferType(int type) {
    myOfferType = type;
  }

}
