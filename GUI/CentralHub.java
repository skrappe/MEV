package GUI;

import Info.Content;
import Info.KMP;
import javafx.animation.ScaleTransition;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class CentralHub {
    //Data:
    ArrayList<Content> contentList;
    File[] posterImages;
    ArrayList<Content> favouritesData;  ArrayList<File> favouritesImages;
    ArrayList<Content> searchedData;    ArrayList<File> searchedImages;
    Content potentialTarget;
    Content globalTarget;
    ImageView infoPanelImage;

    //Layers:
    BorderPane homeRoot;
    BorderPane favouritesRoot;

    StackPane homeStackPane;
    StackPane favouritesStackPane;

    FlowPane homeFlowPane;
    FlowPane favouritesFlowPane;

    ScrollPane homeScrollPane;
    ScrollPane favouritesScrollPane;

    HBox homeMenuBar;
    HBox favouritesMenuBar;

    InformationPanel infoPanel;
    FrostPanel frostPanel;

    //Effect:
    DropShadow dropShadow;



    CentralHub(ArrayList<Content> inputContentList, File[] inputPosterImages) {
        dropShadow = new DropShadow();
        dropShadow.setBlurType(BlurType.GAUSSIAN);
        dropShadow.setColor(Color.WHITE);
        dropShadow.setHeight(20);
        dropShadow.setWidth(20);
        allocateData(inputContentList, inputPosterImages);
        homePage(this.contentList, this.posterImages);
        favouritesPage();
    }

    /****************************************

                    HOME PAGE

     *****************************************/
    public void homePage(ArrayList<Content> contentList, File[] posterImages) {
        this.infoPanel = new InformationPanel(contentList, favouritesData); // Content list...

        this.homeRoot = new BorderPane();
        this.frostPanel = new FrostPanel(this.homeRoot, this.infoPanel);
        this.homeStackPane = new StackPane();
        this.homeFlowPane = loadImages(contentList, posterImages, infoPanel);
        this.homeScrollPane = new ScrollPane(homeFlowPane);

        //menuBar instance
        this.homeMenuBar = menuBar();



        styleComponents(this.homeFlowPane, this.homeScrollPane);
        sandwichLayers(this.homeRoot, this.homeStackPane, this.homeScrollPane, this.infoPanel, this.frostPanel, this.homeMenuBar);
    }




    /****************************************

                FAVOURITES PAGE

     *****************************************/
    public void favouritesPage() {
        this.favouritesRoot = new BorderPane();

    }




    /****************************************

                LOAD IMAGES

     *****************************************/
    public FlowPane loadImages(ArrayList<Content> contentList, File[] fileList, InformationPanel infoPanel) {
        FlowPane returnFlowPane = new FlowPane();

        ImageView[] imageViewArray = new ImageView[contentList.size()];
        Image[] imageArray = new Image[contentList.size()];

        for (int i = 0; i < contentList.size(); i++) {
            try{
                //Base Info:
                imageArray[i] = new Image(fileList[i].toURI().toString());
                imageViewArray[i] = new ImageView(imageArray[i]);
                String titleName = fileList[i].getAbsoluteFile().getName();

                //Branding:
                imageViewArray[i].setUserData(titleName.replace(".jpg", ""));
                imageViewArray[i].setId("" + i);


                //Effects:
                var pictureHover = new ScaleTransition(Duration.millis(600), imageViewArray[i]);
                //size of hover effect
                pictureHover.setFromX(1.0);             // start
                pictureHover.setFromY(1.0);             // start
                pictureHover.setToX(1.2);               // to this size
                pictureHover.setToY(1.2);               // to this size
                imageViewArray[i].setEffect(dropShadow);


                /*ON HOVER EVENT - mouse ENTER*/
                imageViewArray[i].setOnMouseEntered((evtEnter) -> {
                    Node targetPicture = (Node) evtEnter.getSource();
                    pictureHover.setRate(1.5);
                    targetPicture.setViewOrder(-1.0);
                    pictureHover.play();
                });
                /*ON HOVER EVENT - mouse EXIT*/
                imageViewArray[i].setOnMouseExited((evtExit) -> {
                    Node targetPicture = (Node) evtExit.getSource();
                    pictureHover.setRate(-1.0);
                    targetPicture.setViewOrder(0.0); // something-something.
                    pictureHover.play();
                });


                /*ON CLICK EVENT*/
                imageViewArray[i].setOnMouseClicked((evt) -> {
                    Node targetPictureSource = (Node) evt.getSource();
                    String targetImageName = imageViewArray[Integer.parseInt(targetPictureSource.getId())].getUserData().toString();
                    this.infoPanel.setTargetImage(retImage(targetImageName, fileList));

                    for(Content info : contentList) {
                        if(info.title.equals(targetImageName)) {
                            // current global!
                            this.infoPanel.setPotentialTarget(info);
                            Field[] fields = info.getClass().getFields();
                            for(Field field : fields) {
                                try {
                                    System.out.println(field.get(info).toString());
                                    infoPanel.setNewItem(field.get(info).toString());
                                } catch(IllegalAccessException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                    }
                    // Make the info panel and frost panel both visible
                    this.infoPanel.setVisible();
                    this.frostPanel.setVisible();

                    // add picture stuff
                });

                returnFlowPane.getChildren().add(imageViewArray[i]);

            } catch (Exception e) {
                System.out.println("No more space");
            }
        }
        return returnFlowPane;
    }

    public void allocateData(ArrayList<Content> inputContentList, File[] inputPosterImages) {
        this.contentList = inputContentList;
        this.posterImages = inputPosterImages;
        this.favouritesData = new ArrayList<>(); this.favouritesImages = new ArrayList<>();
        this.searchedData   = new ArrayList<>(); this.searchedImages   = new ArrayList<>();
    }

    public BorderPane getHomeRoot() {
        return this.homeRoot;
    }

    //we add 'input' to the name of the nodes that we pass in, so we know what it is that we grab from somewhere else.
    public void sandwichLayers(BorderPane inputBorderPane, StackPane inputStackPane, ScrollPane inputScrollPane, InformationPanel inputInfoPanel, FrostPanel inputFrostPanel, HBox inputHomeMenuBar) {
        inputStackPane.getChildren().add(inputScrollPane);
        inputStackPane.getChildren().add(inputFrostPanel.getRoot());
        inputStackPane.getChildren().add(inputInfoPanel.getRoot());
        inputBorderPane.setCenter(inputStackPane);
        //adds menuBar to the top BorderPane;
        inputBorderPane.setTop(inputHomeMenuBar);

    }

    public void styleComponents(FlowPane flowPane, ScrollPane scrollPane) {
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        flowPane.setAlignment(Pos.CENTER);
        flowPane.setHgap(15);
        flowPane.setVgap(20);
        flowPane.setPrefWrapLength(1200);
        flowPane.setStyle("-fx-background-color: #430856");
    }

    public ImageView retImage(String target, File[] filesList) {
        Image targetImage;
        ImageView imageToReturn = null;
        for(int i = 0; i < filesList.length; i++) {
            if(filesList[i].getAbsoluteFile().getName().replace(".jpg", "").equals(target)) {
                // Return image...
                targetImage = new Image(filesList[i].toURI().toString());
                imageToReturn = new ImageView(targetImage);
                imageToReturn.setScaleY(1.2);
                imageToReturn.setScaleX(1.2);
                imageToReturn.setEffect(dropShadow);
            }
        }
        return imageToReturn;
    }


    //this menubar makes it possible for us to search.
    public HBox menuBar() {

        //Buttons and search to interact with.
        Button home = new Button("Home");
        Button favouritesList = new Button("View Favourites");
        TextField searchBar = new TextField("search here...");
        //add remove text-ability
        Button searchButton = new Button("Search");

        HBox menuBar = new HBox();
        menuBar.getChildren().addAll(home, favouritesList, searchBar, searchButton);

        //eventlistener for TextField
        searchBar.setOnMouseClicked((evtClick) -> {
            searchBar.clear();
        });

        searchBar.setOnKeyPressed((keyEvent) -> {
            if(keyEvent.getCode() == KeyCode.ENTER) {
                this.searchedData.clear();
                searchAlgorithm(searchBar);
                printListTest();
            }
        });

        //menuBar styling:
        menuBar.setStyle("-fx-background-color: #560b75");
        menuBar.setEffect(dropShadow);

        //sandwhich layers:
        return menuBar;
    }

    public void searchAlgorithm(TextField searchBar) {
        if(searchBar.getText().length() == 0) {
            System.out.println("NO input detected!");
        } else {
            KMP kmp = new KMP();
            for(Content content : contentList) {
                String filteredGenres = content.genre.replace(",", "");
                String inputText = content.title.concat(filteredGenres).toLowerCase().replace(" ","");
                if(kmp.initKMP(inputText, searchBar.getText().toLowerCase().replace(" ", ""))) {
                    this.searchedData.add(content);
                }
            }
        }
    }

    public void printListTest() {
        this.searchedData.forEach((content) -> {
            System.out.println("Added content is: " + content.title);
        });
    }
}
