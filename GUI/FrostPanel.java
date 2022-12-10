package GUI;

import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class FrostPanel {

    Rectangle frostPanel;
    GaussianBlur blurEffect;

    // We need to take an input reference
    InformationPanel infoPanelRef;

    FrostPanel(BorderPane scene, InformationPanel inputInfoPanel) {
        this.frostPanel = new Rectangle(0,0,0,0);
        this.infoPanelRef = inputInfoPanel;
        this.frostPanel.heightProperty().bind(scene.heightProperty());
        this.frostPanel.widthProperty().bind(scene.heightProperty());
        stylingComponent();
    }

    public void stylingComponent() {
        this.blurEffect = new GaussianBlur(10);
        this.frostPanel.setFill(Color.DARKBLUE);
        this.frostPanel.setOpacity(0.2);
        this.frostPanel.setEffect(blurEffect);
        this.frostPanel.setVisible(false);
        this.frostPanel.setOnMouseClicked((evt) -> {
            infoPanelRef.removeTargetImage();
            infoPanelRef.removeListView();
            infoPanelRef.setInvisible();
            frostPanel.setVisible(false);
        });
    }

    public void setVisible() {
        this.frostPanel.setVisible(true);
    }

    public void setInvisible() {
        this.frostPanel.setVisible(false);
    }

    public Rectangle getRoot() {
        return this.frostPanel;
    }
}
