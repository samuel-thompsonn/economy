package visualizer;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import model.market.MarketUser;
import model.market.offer.OfferListener;
import model.market.offer.ViewableOffer;

import java.util.ArrayList;
import java.util.List;

public class OfferDisplay implements OfferListener {
  private String myResourceType;
  private int myPrice;
  private MarketUser myOwner;
  private ViewableOffer myOffer;
  private String myOfferType;
  private Rectangle myBorder;
  private Text myPriceText;
  private Text myResourceTypeText;
  private Text myOfferTypeText;
  private Text myOwnerText;
  private double myX;
  private double myY;
  private double myHeight;
  private Group myRoot;
  private List<OfferDisplayListener> myListeners;

  public OfferDisplay(ViewableOffer offer, double x, double y, double height) {
    myListeners = new ArrayList<>();
    myOffer = offer;
    myResourceType = offer.getResourceType();
    myPrice = offer.getPrice();
    myOwner = offer.getOwner();
    myOfferType = offer.getOfferType();
    myHeight = height;
    myBorder = new Rectangle(0,0,150,myHeight);
    myBorder.setFill(Color.TRANSPARENT);
    myBorder.setStroke(Color.BLACK);
    myPriceText = new Text(""+myPrice);
    myOfferTypeText = new Text(""+myOfferType);
    myResourceTypeText = new Text(""+myResourceType);
    setX(x);
    setY(y);
    myRoot = new Group();
    myRoot.getChildren().addAll(myBorder,myPriceText,myOfferTypeText,myResourceTypeText);
    offer.subscribe(this);
  }

  public void setX(double x) {
    myX = x;
    myBorder.setX(myX);
    myPriceText.setX(myX+10);
    myOfferTypeText.setX(myX+60);
    myResourceTypeText.setX(myX+110);
  }

  public void setY(double y) {
    myY = y;
    myBorder.setY(myY);
    myPriceText.setY(myY+(myHeight/3));
    myOfferTypeText.setY(myY+(myHeight/3));
    myResourceTypeText.setY(myY+(myHeight/3));
  }

  public Group getRoot() {
    return myRoot;
  }

  public void setHeight(double height) {
    myHeight = height;
  }

  @Override
  public void reactToOfferFulfilled() {
    myListeners.forEach(offerDisplayListener -> {offerDisplayListener.reactToOfferFulfilled(this);});
  }

  public void subscribe(OfferDisplayListener listener) {
    myListeners.add(listener);
  }
}
