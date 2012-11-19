package drawapp;

import java.io.File;
import java.io.IOException;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

public class MainWindow extends Group 
{
  public static final int DEFAULT_WIDTH = 500;
  public static final int DEFAULT_HEIGHT = 300;

  private int width;
  private int height ;
  private int x;
  private int y;

  private ImagePanel imagePanel;
  private TextArea messageView;
  
  Parser parser;
  
  Button nextButton, saveButton, closeButton;

  Group root = new Group();
  final Stage stage = new Stage();
  final Scene scene = new Scene(root, 600, 600);
  
  
  public MainWindow(){
      this(DEFAULT_WIDTH, DEFAULT_HEIGHT);
      
  }

  public MainWindow(int width, int height)
  { 
    this.width = width;
    this.height = height;
    this.setVisible(true);
    buildGUI(); 
  }
 
  public void setDimension(int x, int y){
      this.x = x;
      this.y = y;
  }

  private void buildGUI()
  {
      
    stage.setScene(scene);
    stage.setResizable(true);
    stage.show();
      
    closeButton = new Button("Close Window");
    closeButton.setTranslateX(300);
    closeButton.setTranslateY(470);
    closeButton.setOnMouseClicked(new EventHandler<MouseEvent>(){
          @Override
          public void handle(MouseEvent arg0) {  
             System.exit(0);
          }
      }); 
  
    nextButton = new Button("Next");
    nextButton.setTranslateX(80);
    nextButton.setTranslateY(470); 
    
    saveButton = new Button("Save");
    saveButton.setTranslateX(200);
    saveButton.setTranslateY(470);
    saveButton.setOnAction(new EventHandler<ActionEvent>(){
        @Override
      public void handle(ActionEvent event){
          WritableImage image = new WritableImage(width, height);
          scene.snapshot(image); 
          try {
                    File file = new File("imageSaved.png");
                    postMessage("The image had been saved as 'imageSaved.png.'");
                    ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
                } catch (IOException e) {
                    postMessage("Save image error." + e.getMessage());
                }
      }
  });
    
    messageView = new TextArea();
    messageView.setTranslateY(300);
    
    imagePanel = new ImagePanel(width, height);
    root.getChildren().addAll(closeButton, nextButton, saveButton, messageView, imagePanel);

  }


  public ImagePanel getImagePanel()
  {
    return imagePanel;
  }

  public void postMessage(final String s)
  {
     Platform.runLater(new Runnable()
        {
          @Override
          public void run()
          {
             String textBox = messageView.getText();
             messageView.setText( textBox + "\n" + s); 
             
          }
        });
  }

  public void actionPerformed(ActionEvent actionEvent)
  {
    System.exit(0);
  }
}