package GUI;

import javafx.application.Application;
import Info.Info;
import Info.Content;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {
    ArrayList<Content> contentList;
    File[] posterFiles;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        initData();

        CentralHub centralHub = new CentralHub(contentList, posterFiles);

        Scene mainScene = new Scene(centralHub.getHomeRoot());
        stage.setScene(mainScene);
        stage.show();
    }

    public void initData() {
        Info info = new Info();
        this.contentList = info.getContentList();
        this.posterFiles = info.getFileList();
    }
}
