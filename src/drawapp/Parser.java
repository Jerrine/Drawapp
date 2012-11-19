
package drawapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.security.InvalidParameterException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;

public class Parser
{
  private BufferedReader reader;
  private ImagePanel image;
  private MainWindow frame;
  
  
  public Parser(Reader reader, ImagePanel image, MainWindow frame)
  {
    this.reader = new BufferedReader(reader);
    this.image = image;
    this.frame = frame;
  }

  public void parse()
  {
    try
    {  
     frame.nextButton.setOnAction(
            new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent arg0){
                    try {
                        String line = reader.readLine();
                        if (line != null){
                        try {
                            Parser.this.parseLine(line);
                        } catch (ParseException e) {
                            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, e);
                        }} else {
                            frame.postMessage("Drawing completed.");
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });         
    }
   catch (InvalidParameterException e) {
            frame.postMessage("InvalidParameter:" + " ");  
    }
    frame.postMessage("Drawing... "); 
  }


  private void parseLine(String line) throws ParseException
  {
    if (line.length() < 2) {return;}
    String command = line.substring(0, 2);
    if (command.equals("SD")) { setDimension(line.substring(2, line.length())); return;}
    if (command.equals("DL")) { drawLine(line.substring(2,line.length())); return; }
    if (command.equals("DR")) { drawRect(line.substring(2, line.length())); return; }
    if (command.equals("FR")) { fillRect(line.substring(2, line.length())); return; }
    if (command.equals("SC")) { setColour(line.substring(3, line.length())); return; }
    if (command.equals("DS")) { drawString(line.substring(3, line.length())); return; }
    if (command.equals("DA")) { drawArc(line.substring(2, line.length())); return; }
    if (command.equals("DO")) { drawOval(line.substring(2, line.length())); return; }
    if (command.equals("DI")) { drawImage(line.substring(2, line.length())); return;}
    if (command.equals("TS")) { turtleStart(line.substring(2, line.length())); return;}
    if (command.equals("TF")) { turtleForward(line.substring(2, line.length())); return;}
    if (command.equals("TR")) { turtleRight(line.substring(2, line.length())); return;}
    if (command.equals("TL")) { turtleLeft(line.substring(2, line.length())); return;}
    if (command.equals("TP")) { pen(line.substring(2, line.length())); return;}

    throw new ParseException("Unknown drawing command");
  }

  private void turtleStart(String args) throws ParseException{
      int x = 0;
      int y = 0;
      int angle = 0;
      
    StringTokenizer tokenizer = new StringTokenizer(args);
    try{
    x = getInteger(tokenizer);
    y = getInteger(tokenizer);
    angle = getInteger(tokenizer);
    }
    catch(Exception e){
        frame.postMessage("Error in starting the drawing of turtle " + e.getMessage());
    }
    frame.postMessage("TS " + args);
    image.turtleStart(x, y, angle);
  }
  
  private void turtleForward(String args) throws ParseException{
      int steps = 0;
      
    StringTokenizer tokenizer = new StringTokenizer(args);
    try{
    steps = getInteger(tokenizer);
    }
    catch(Exception e){
        frame.postMessage("Error in moving the tutle forward " + e.getMessage());
    }
    frame.postMessage("TF " + args);
    image.turtleForward(steps);
  }
  
  
  private void turtleRight(String args) throws ParseException{
      int angle = 0;
      
    StringTokenizer tokenizer = new StringTokenizer(args);
    try{
    angle = getInteger(tokenizer);
    }
    catch(Exception e){
        frame.postMessage("Error in moving the tutle to the right" + e.getMessage());
    }
    frame.postMessage("TR " + args);
    image.turtleRight(angle);
  }
  
  private void turtleLeft(String args) throws ParseException{
      int angle = 0;
      
    StringTokenizer tokenizer = new StringTokenizer(args);
    try{
    angle = getInteger(tokenizer);
    }
    catch(Exception e){
        frame.postMessage("Error in moving the tutle to the left" + e.getMessage());
    }
    frame.postMessage("TL " + args);
    image.turtleLeft(angle);
  }
  
  private void pen(String args) throws ParseException{
      int penCommand = 0;
      
    StringTokenizer tokenizer = new StringTokenizer(args);
    try{
    penCommand = getInteger(tokenizer);
    }
    catch(Exception e){
        frame.postMessage("Error in commanding the status of pen" + e.getMessage());
    }
    if (penCommand == 1){
        frame.postMessage("Pendown: Set the pen to draw when the turtle moves");
        image.pen(penCommand);
    }
    else if (penCommand == 0){
        frame.postMessage("Penup: Set the pen to not draw when the turtle moves");
        image.pen(penCommand);
    }
    else  {
        frame.postMessage("Error in penup and pendown!");
    }
  }
  
  private void drawLine(String args) throws ParseException
  {
    int x1 = 0;
    int y1 = 0;
    int x2 = 0;
    int y2 = 0;

    StringTokenizer tokenizer = new StringTokenizer(args);
    try{
    x1 = getInteger(tokenizer);
    y1 = getInteger(tokenizer);
    x2 = getInteger(tokenizer);
    y2 = getInteger(tokenizer);
    }
    catch(Exception e){
        frame.postMessage("Error in drawLine" + e.getMessage());
    }
    frame.postMessage("DL " + args);
    image.drawLine(x1,y1,x2,y2);
  }

  private void drawRect(String args) throws ParseException
  {
    int x1 = 0;
    int y1 = 0;
    int x2 = 0;
    int y2 = 0;

    StringTokenizer tokenizer = new StringTokenizer(args);
    try{
    x1 = getInteger(tokenizer);
    y1 = getInteger(tokenizer);
    x2 = getInteger(tokenizer);
    y2 = getInteger(tokenizer);
    }
    catch(Exception e){
        frame.postMessage("Error in drawRect" + e.getMessage());
    }
    frame.postMessage("DR " +args);
    image.drawRect(x1, y1, x2, y2);
  }

  private void fillRect(String args) throws ParseException
  {
    int x1 = 0;
    int y1 = 0;
    int x2 = 0;
    int y2 = 0;

    StringTokenizer tokenizer = new StringTokenizer(args);
    try{
    x1 = getInteger(tokenizer);
    y1 = getInteger(tokenizer);
    x2 = getInteger(tokenizer);
    y2 = getInteger(tokenizer);
    }
    catch(Exception e){
        frame.postMessage("Error in fillRect" + e.getMessage());
    }
    frame.postMessage("FR " +args);
    image.fillRect(x1, y1, x2, y2);
  }

  private void drawArc(String args) throws ParseException
  {
    int x = 0;
    int y = 0;
    int width = 0;
    int height = 0;
    int startAngle = 0;
    int arcAngle = 0;

    StringTokenizer tokenizer = new StringTokenizer(args);
    try{
    x = getInteger(tokenizer);
    y = getInteger(tokenizer);
    width = getInteger(tokenizer);
    height = getInteger(tokenizer);
    startAngle = getInteger(tokenizer);
    arcAngle = getInteger(tokenizer);
    }
    catch(Exception e){
        frame.postMessage("Error in drawArc" + e.getMessage());
    }
    frame.postMessage("DA " +args);
    image.drawArc(x, y, width, height, startAngle, arcAngle);
  }

  private void drawOval(String args) throws ParseException
  {
    int x1 = 0;
    int y1 = 0;
    int width = 0;
    int height = 0;

    StringTokenizer tokenizer = new StringTokenizer(args);
    try{
    x1 = getInteger(tokenizer);
    y1 = getInteger(tokenizer);
    width = getInteger(tokenizer);
    height = getInteger(tokenizer);
    } 
    catch(Exception e){
        frame.postMessage("Error in drawOval" + e.getMessage());
    }    
    frame.postMessage("DO " +args);
    image.drawOval(x1, y1, width, height);
  }

  private void drawString(String args) throws ParseException
  {
    int x = 0;
    int y = 0 ;
    String s = "";
    StringTokenizer tokenizer = new StringTokenizer(args);
    try {
    x = getInteger(tokenizer);
    y = getInteger(tokenizer);
    int position = args.indexOf("@");
    if (position == -1) throw new ParseException("DrawString string is missing");
    s = args.substring(position+1,args.length());
    }
    catch(Exception e){
        frame.postMessage("Error in drawString" + e.getMessage());
    }
    frame.postMessage("DS " +args);
    image.drawString(x,y,s);
  }
  
  private void drawImage(String args) throws ParseException {
      int x = 0;
      int y = 0;
      int width = 0;
      int height = 0;
      String s = "";
      
      StringTokenizer tokenizer = new StringTokenizer(args);
      try {
          x = getInteger(tokenizer);
          y = getInteger(tokenizer);
          width = getInteger(tokenizer);
          height = getInteger(tokenizer);
          int position = args.indexOf("@");
            if (position == -1) {
                throw new ParseException("Filename string is missing");
            }
            s = args.substring(position + 1, args.length());
        } catch (Exception e) {
            frame.postMessage("Error in displaying image " + e.getMessage());
        }
        frame.postMessage("DI " +args);
        image.drawImage(x, y, width, height, s);
      
  }
  
  private void setDimension(String args) throws ParseException{
      int x = 0;
      int y = 0;
      StringTokenizer tokenizer = new StringTokenizer(args);
      try{
          x = getInteger(tokenizer);
          y = getInteger(tokenizer);
      } catch (Exception e){
          frame.postMessage("Error in setting dimension" + e.getMessage());
      }
      frame.postMessage("SD " + args);
      frame.setDimension(x,y);
  }

  private void setColour(String colourName) throws ParseException
  {
    if (colourName.equals("black")) { image.setColour(Color.BLACK); return;}
    if (colourName.equals("blue")) { image.setColour(Color.BLUE); return;}
    if (colourName.equals("cyan")) { image.setColour(Color.CYAN); return;}
    if (colourName.equals("darkgray")) { image.setColour(Color.DARKGRAY); return;}
    if (colourName.equals("gray")) { image.setColour(Color.GRAY); return;}
    if (colourName.equals("green")) { image.setColour(Color.GREEN); return;}
    if (colourName.equals("lightgray")) { image.setColour(Color.LIGHTGRAY); return;}
    if (colourName.equals("magenta")) { image.setColour(Color.MAGENTA); return;}
    if (colourName.equals("orange")) { image.setColour(Color.ORANGE); return;}
    if (colourName.equals("pink")) { image.setColour(Color.PINK); return;}
    if (colourName.equals("red")) { image.setColour(Color.RED); return;}
    if (colourName.equals("white")) { image.setColour(Color.WHITE); return;}
    if (colourName.equals("yellow")) { image.setColour(Color.YELLOW); return;}
    throw new ParseException("Invalid colour name");
  }
  

  private int getInteger(StringTokenizer tokenizer) throws ParseException
  {
    if (tokenizer.hasMoreTokens()){
      return Integer.parseInt(tokenizer.nextToken());}
    else{
      throw new ParseException("Missing Integer value");}
  }
 
}
