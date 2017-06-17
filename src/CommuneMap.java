/**
 * Created by Brandon on 17/06/2017.
 */

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import java.util.ArrayList;

/**
 * Created by Brandon on 17/06/2017.
 */
public class CommuneMap extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        CSVReader csvReader = new CSVReader("CommunesFrance.csv",1000);
        ArrayList list = csvReader.getCommuneList();

        drawPage(primaryStage, list);
        primaryStage.show();
    }


    public void drawPage(Stage stage,ArrayList<Commune> list){
        stage.setTitle("Map");
        stage.setResizable(true);

        int multiplicateur = 20*5;

        Group root = new Group();
        double toTranslate = 0;
        for (Commune c:list) {
            Rectangle rectangle = new Rectangle(2,2);
            rectangle.setFill(Color.RED);
            rectangle.setY(-(c.latitude-41.30)*multiplicateur);
            rectangle.setX((c.longitude+4.90)*multiplicateur);
            root.getChildren().add(rectangle);
            if ((c.latitude-41.30)*multiplicateur > toTranslate){
                toTranslate = (c.latitude-41.30)*multiplicateur;
            }
        }

        root.setTranslateY(toTranslate);
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}