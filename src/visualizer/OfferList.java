package visualizer;

import javafx.scene.Group;
import model.market.Market;
import model.market.MarketListener;
import model.market.offer.ViewableOffer;

import java.util.ArrayList;
import java.util.List;

public class OfferList implements MarketListener, OfferDisplayListener {
  public static final int SPACE_PER_OFFER = 50;
  private List<OfferDisplay> myOfferDisplays;
  private Group myRoot;
  private double myX;
  private double myY;

  public OfferList(double x, double y, Market market) {
    myOfferDisplays = new ArrayList<>();
    myRoot = new Group();
    myX = x;
    myY = y;
    market.subscribe(this);
  }

  public void addOffer(ViewableOffer offer) {
    OfferDisplay display = new OfferDisplay(offer,myX,myY+(myOfferDisplays.size() * SPACE_PER_OFFER),SPACE_PER_OFFER);
    myOfferDisplays.add(display);
    myRoot.getChildren().add(display.getRoot());
    display.subscribe(this);
  }

  public Group getRoot() {
    return myRoot;
  }

  @Override
  public void reactToNewOffer(ViewableOffer offer) {
    addOffer(offer);
  }

  @Override
  public void reactToOfferFulfilled(OfferDisplay display) {
    myRoot.getChildren().remove(display.getRoot());
    myOfferDisplays.remove(display);

    List<OfferDisplay> rearrangedDisplay = new ArrayList<>(myOfferDisplays);
    int count = 0;
    for (int i = 0; i < rearrangedDisplay.size(); i++) {
      OfferDisplay offerDisplay = myOfferDisplays.get(i);
      if (offerDisplay == null) {
        continue;
      }
      offerDisplay.setY(myY + (count * SPACE_PER_OFFER));
      count++;
    }
    myOfferDisplays = rearrangedDisplay;
  }
}
