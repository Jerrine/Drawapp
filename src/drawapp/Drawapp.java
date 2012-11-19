package drawapp;

import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Drawapp extends Application
{
    public void init(Stage primaryStage){

    final MainWindow main = new MainWindow();
 

    Platform.runLater(new Runnable(){
    @Override
    public void run(){
        ImagePanel imagePanel = main.getImagePanel();
 /*       Reader reader = new StringReader(""
                    //+ "SD 800 800\n"  // set dimension
                    /* Turtle graphic
                    + "TS 200 200 0\n"
                    + "TP 1\n"
                    + "TR 45\n"
                    + "TF 150\n"
                    + "TL 90\n"
                    + "TF 50\n"  */
                    /** Drawing of a house
                    + "DR 50 100 200 150\n"
                    + "DL 50 100 150 25\n"
                    + "DL 150 25 250 100\n"
                    + "DR 130 190 40 60\n"
                    + "FR 130 190 40 60 0\n"
                    + "DI 100 50 50 50 @image.png\n" //display a image
                    + "DR 70 195 40 30 10\n"
                    + "DR 70 195 40 30 10\n"
                    + "FR 190 195 40 30 0\n"
                    + "DR 190 195 40 30\n"); */

       Reader reader = new InputStreamReader(System.in);
       final Parser parser = new Parser(reader,imagePanel,main);
       parser.parse();
            }  
        } );
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        init(primaryStage);
    }

    public static void main(String[] args) {
    launch(args);
    }
}