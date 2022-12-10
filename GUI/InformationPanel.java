package GUI;

import Info.Content;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;

import java.util.ArrayList;

public class InformationPanel {
    // Grid containers to categorize the content into specific containers:
    GridPane root;
    GridPane imageAndButton;

    // List all of our movie data:
    ListView contentInfo;

    // The image we display:
    ImageView targetImage;

    // Each image we click on is declared as a "potential target", whereby we
    // are able to "add" or "remove" this potential target to our favourites
    Content potentialTarget;

    // Buttons:
    HBox buttons;
    Button add;
    Button remove;

    // Referenced:
    ArrayList<Content> contentListRef;
    ArrayList<Content> favouriteListRef;

    InformationPanel(ArrayList<Content> inputContentList, ArrayList<Content> inputFavouriteList) {
        // Init components:
        this.root = new GridPane();
        this.imageAndButton = new GridPane();
        this.contentInfo = new ListView();
        this.buttons = new HBox();
        this.add = new Button("Add to Favourites");
        this.remove = new Button("Remove from Favourites");
        this.potentialTarget = null;

        // References:
        this.contentListRef = inputContentList;
        this.favouriteListRef = inputFavouriteList;

        //
        stylingAndConstraints();
        sandwichLayers();
    }

    public void stylingAndConstraints() {
        // Constraints - Grid #1:
        ColumnConstraints rootContentCol = new ColumnConstraints();
        rootContentCol.setPercentWidth(50);
        ColumnConstraints rootPictureCol = new ColumnConstraints();
        rootPictureCol.setPercentWidth(50);
        this.root.getColumnConstraints().addAll(rootContentCol, rootPictureCol);
        this.root.setStyle("-fx-background-color: black; ");
        this.root.setMaxWidth(900);
        this.root.setMaxHeight(300);
        this.root.setAlignment(Pos.CENTER);
        this.root.setVisible(false);

        // Constraints  - Grid #2:
        RowConstraints pictureRow = new RowConstraints();
        pictureRow.setPercentHeight(80);
        RowConstraints buttonRow = new RowConstraints();
        buttonRow.setPercentHeight(20);
        this.imageAndButton.getRowConstraints().addAll(pictureRow, buttonRow);
        this.imageAndButton.setAlignment(Pos.CENTER);

        this.add.setStyle("-fx-background-color: white");
        this.add.setOnMouseClicked((evt) -> {
            favouriteListRef.add(this.potentialTarget);
            System.out.println("Add new title...");
        });
        this.remove.setStyle("-fx-background-color: white");
        this.remove.setOnMouseClicked((evt) -> {
            System.out.println("Remove new title...");
        });
    }

    public void sandwichLayers() {
        // Mash together:
        this.buttons.getChildren().addAll(add, remove);
        this.imageAndButton.add(buttons, 0, 1);
        this.root.add(contentInfo, 0, 0);
        this.root.add(imageAndButton, 1, 0);
    }

    /*ADD & REMOVE IMAGES*/
    public void setTargetImage(ImageView inputImage) {
        this.targetImage = inputImage;
        this.imageAndButton.add(targetImage, 0, 0);
    }

    public void setPotentialTarget(Content inputInfo) {
        this.potentialTarget = inputInfo;
    }

    public void removeTargetImage() {
        this.imageAndButton.getChildren().remove(targetImage);
    }

    /*ADD & REMOVE LIST CONTENT*/
    public void setNewItem(String newItem) {
        this.contentInfo.getItems().add(newItem);
    }

    public void removeListView() {
        this.contentInfo.getItems().clear();
    }

    /*VISIBILITY*/
    public void setVisible() {
        this.root.setVisible(true);
    }

    public void setInvisible() {
        this.root.setVisible(false);
    }

    // Get root node:
    public GridPane getRoot() {
        return this.root;
    }
}
