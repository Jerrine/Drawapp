/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package drawapp;

//import javax.swing.JPanel;
import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.Group;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;

public class ImagePanel extends Group
{ 
  Group root = new Group();
  private int width;
  private int height;
  GraphicsContext graphic;
  Color colour;

  public ImagePanel(int width, int height)
  {
    setImageSize(width, height);
  }

  private void setImageSize(int width, int height)
  {
    this.width = width;
    this.height = height;
    clear(Color.WHITE);
  }

  public void setBackgroundColour(Color colour)
  {
    Rectangle rect = new Rectangle(0, 0, width, height);
    rect.setFill(colour);
    this.getChildren().add(rect);
  }

  public void clear(Color colour)
  {
    setBackgroundColour(colour);
  }

  public void setColour(Color colour)
  {
    //graphics.setColor();
      this.colour = colour;
  }

  public void drawLine(int x1, int y1, int x2, int y2)
  {
    //graphics.drawLine(x1, y1, x2, y2);
      Line line = new Line(x1, y1, x2, y2);
      this.getChildren().add(line);
  }

  public void drawRect(int x1, int y1, int x2, int y2)
  {
    //graphics.drawRect(x1, y1, x2, y2);
      Rectangle rect = new Rectangle(x1, y1, x2, y2);
      this.getChildren().add(rect);
  }

  public void fillRect(int x1, int y1, int x2, int y2)
  {
    //graphics.fillRect(x1, y1, x2, y2);
      Rectangle rect1 = new Rectangle(x1, y1, x2, y2);
      rect1.setFill(Color.WHITE);
      this.getChildren().add(rect1);
      
  }

  public void drawString(int x, int y, String s)
  {
    //graphics.drawString(s,x,y);
      Text text = new Text(x, y, s);
      this.getChildren().add(text);
  }

  public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle)
  {
    //graphics.drawArc(x,y,width,height,startAngle,arcAngle);
      Arc arc = new Arc(x, y, width, height, startAngle, arcAngle);
      this.getChildren().add(arc);
      
  }

  public void drawOval(int x, int y, int width, int height)
  {
    //graphics.drawOval(x,y,width,height);
      Ellipse oval = new Ellipse(x, y, width, height);
      this.getChildren().add(oval);
      
  }
  
  public void drawImage (int x, int y, int width, int height, String filename){
      ImageView image1 = new ImageView(new Image(Drawapp.class.getResourceAsStream(filename), width, height, true, true));
      image1.setLayoutX(x);
      image1.setLayoutY(y);
      this.getChildren().add(image1);
  }
  
  public void setGradient(double startX, double startY, double endX, double endY, 
          boolean proportional, CycleMethod cycleMethod, Color startColour, Color stopColour){
      LinearGradient gradient = new LinearGradient(startX, startY, endX, endY, proportional, 
              cycleMethod, new Stop[]{ new Stop(0,startColour),
                            new Stop(1,stopColour)});
      graphic.setFill(gradient);
  }
  
  // Drawing turtle graphic
    int x = 0;
    int y= 0;
    int angle = 0;
    int penCommand = 0;
         
     Ellipse turtle = new Ellipse(5, 12);
    
     public void turtleStart(int x, int y, int angle){
         turtle.setFill(Color.GREEN);
         turtle.setLayoutX(x);
         turtle.setLayoutY(y);
         this.x = x;
         this.y = y;
         this.angle = angle;
         Rotate rotate = new Rotate(angle, 0, 0);
         turtle.getTransforms().add(rotate);
         this.getChildren().add(turtle);         
     }
     
     public void turtleForward(int steps){
         int oldX = this.x;
         int oldY = this.y;
         this.x += steps * Math.cos(Math.toRadians(angle));
         this.y -= steps * Math.sin(Math.toRadians(angle));
         
         if (penCommand == 1){
             drawLine(oldX, oldY, this.x, this.y);
         }
         turtle.setLayoutX(this.x);
         turtle.setLayoutY(this.y);
     }
     
     public void turtleRight(int angle){
         this.angle = this.angle - angle;
         turtle.getTransforms().add(new Rotate(angle, 0, 0));
     }
     
     public void turtleLeft(int angle){
         this.angle = this.angle + angle;
         turtle.getTransforms().add(new Rotate(180+angle, 0, 0));
     }
     
     public void pen(int penCommand){
         this.penCommand = penCommand;
     }
  
}

