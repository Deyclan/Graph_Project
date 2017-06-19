/**
 * Created by Brandon on 17/06/2017.
 */

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import java.util.ArrayList;

/**
 * Created by Brandon on 17/06/2017.
 */
public class CommuneMap extends Application {

    public int widthParam = 2;
    public int heightParam = 2;
    private Color colorParam = Color.BLACK;
    public static int multiplicateur = 20*5;
    private Group root;
    public double toTranslate = 0;
    public ImageView background;


    @Override
    public void start(Stage primaryStage) throws Exception {
        CSVReader csvReader = new CSVReader("CommunesFrance.csv");
        ArrayList list = csvReader.getCommuneList(1000);
        ProachCommuneSearch p = new ProachCommuneSearch(list,0.1);
        p.run();

        drawPage(primaryStage, list);

        primaryStage.show();
    }


    public void drawPage(Stage stage,ArrayList<Commune> list){
        stage.setTitle("Map");
        stage.setResizable(true);

        root = new Group();
        this.background = new ImageView("France.png");
        this.background.setX(12);
        this.background.setFitHeight(970);
        this.background.setFitWidth(1430);
        root.getChildren().add(background);
        for (Commune c:list) {
            for (Arc arc:c.proachCommunesArcs){
                arc.drawArc(root,Color.RED);
            }
            //drawArcs(c);
            drawCommune(c);
        }

        root.setTranslateY(toTranslate);
        this.background.setY(-toTranslate-10);
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    public void drawArcs(Commune commune) {
        for (Commune c : commune.proachCommunes) {
            Line line = new Line();
            line.setStroke(Color.RED);
            line.setStartX(scaleLongitude(commune.longitude));
            line.setStartY(scaleLatitude(commune.latitude));
            line.setEndX(scaleLongitude(c.longitude));
            line.setEndY(scaleLatitude(c.latitude));
            root.getChildren().add(line);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void drawCommune(Commune commune){
        Rectangle rectangle = new Rectangle(widthParam,heightParam);
        rectangle.setFill(colorParam);
        rectangle.setY(-(commune.latitude-41.30)*multiplicateur);
        rectangle.setX((commune.longitude+4.90)*multiplicateur);
        root.getChildren().add(rectangle);
        if ((commune.latitude-41.30)*multiplicateur > toTranslate){
            toTranslate = (commune.latitude-41.30)*multiplicateur;
        }
    }

    public static double scaleLatitude(double Y){
        return -(Y-41.30)*multiplicateur;
    }

    public static double scaleLongitude(double X){
        return (X+4.90)*multiplicateur;
    }
}