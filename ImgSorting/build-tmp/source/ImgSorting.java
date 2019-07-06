import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class ImgSorting extends PApplet {

PImage img, dom, copy, buffer;
String context;
String imageName = "";
String batch = "_a";
String extension = "jpg";
int index = 0;
int limit = 5;
int imgIndex = 0;
boolean selected = false;
public void setup() {  
  

  imageLoader();

  cursor(HAND);
  
  randomSeed(2);
}


public void draw() {
  background(2);
  if (selected == true) {
    if (index < limit) {
      pixelSorting(); 
      img.updatePixels();
      dom.updatePixels();
      image(img, 0,0, width, height);
      img.save("Assets/Out/"+imageName+batch+"/"+imageName+batch+index+"."+extension);
      println("| Image saved as: " + imageName+batch+index+"."+extension);
    } else if (index == limit) {
      println("| End Conv_Test...");
    }
    index++;
    
    trackMouse();
    translate(centerX,centerY);
    image(img,0,0, width, height);
  }
  
}



// Compares the brightness between to colors.
public int colorComp(int c1, int c2) {
  float r = red(c1);
  float g = green(c1);
  float b = blue(c1);
  float rSel = red(255);
  float gSel = green(255);
  float bSel = blue(255);
  float brightness = c2;
  int newColor = color(0, 0, 0);
  if (r > brightness) {
    rSel = r + 10;
  } 
  if (g > brightness) {
    gSel = g + 10;
  } 
  if (b < brightness) {
    bSel = b + 10;
  } 
  float cTotal = rSel + gSel + bSel;
  newColor = color(r,g,b);
  return newColor;
}
// Convolution by Daniel Shiffman. 
public int convolution(int x, int y, float[][] matrix, int matrixsize, PImage inputImg) {
  float rtotal = 0.0f;
  float gtotal = 0.0f;
  float btotal = 0.0f;
  float rgbTotal = 0.0f;
  int offset = matrixsize/2;

  // Loop through convolution matrix
  for (int i = 0; i < matrixsize; i++ ) {
    for (int j = 0; j < matrixsize; j++ ) {

      // What pixel are we testing
      int xloc = x + i-offset; //-offset
      int yloc = y + j-offset;
      int convLoc = xloc + inputImg.width*yloc;

      // Make sure we haven't walked off the edge of the pixel array
      // It is often good when looking at neighboring pixels to make sure we have not gone off the edge of the pixel array by accident.
      convLoc = constrain(convLoc, 0, inputImg.pixels.length-1);
      
      // Calculate the convolution
      // We sum all the neighboring pixels multiplied by the values in the convolution matrix.
      rtotal += (red(inputImg.pixels[convLoc]) * matrix[i][j]);
      gtotal += (green(inputImg.pixels[convLoc]) * matrix[i][j]);
      btotal += (blue(inputImg.pixels[convLoc]) * matrix[i][j]);
      rgbTotal = rtotal + gtotal + btotal;
      
    }
  }

  // Make sure RGB is within range
  rtotal = constrain(rtotal, 0, 255);
  gtotal = constrain(gtotal, 0, 255);
  btotal = constrain(btotal, 0, 255);
  
  
  // Return the resulting color
  return color(rtotal, gtotal, btotal);
}
// Loads custom image file.
public void imageLoader() {

	selectInput("Select a file to process:", "fileSelected");
}

public void fileSelected(File selection) {
  if (selection == null) {
    selected = false;
    println("Window was closed or the user hit cancel.");
  } else {
    String fileName[] = split(selection.getAbsolutePath(), '/');
    String fileTemp[] = split(fileName[fileName.length - 1], '.');
    imageName = fileTemp[0];
    extension = fileTemp[1];
    println("User selected " + imageName + "." + extension);
    img = loadImage(selection.getAbsolutePath());
    dom = loadImage(selection.getAbsolutePath());
    
    int w = dom.width;
    int h = dom.height;
    buffer = createImage(w, h, RGB);
    copy = createImage(w, h, RGB);
    img.resize(w,h);
    //copy.resize(720,1280);
    dom.resize(w,h);
    buffer.resize(w,h);
    copy.resize(w,h);
    loadPixels();
    img.loadPixels();
    dom.loadPixels();
    copy.loadPixels();
    selected = true;
      println("| Img Loaded | imgWidth : " + img.width + " imgHeight : " + img.height);
  }
}
// Pixel Sorting by Emilio Parra. 
// Pixel Sorting Algorithm
public void pixelSorting() {
  buffer = img;
  buffer.loadPixels();
  println("  imgIndex: " + imgIndex);
  int neighbors = 2;
    // Begin our loop for every pixel
  for (int x = 0; x < img.width; x++ ) {
    int com;
    for (int y = 0; y < img.height; y++) {
      // Each pixel location (x,y) gets passed into a function called convolution()
      // The convolution() function returns a new color to be displayed.
      int loc = x + y*img.width;
      img.updatePixels();
      dom.updatePixels();
      int c = convolution(x,y, kernelC, matrixsize, img);
      int d = convolution(x,y, kernelL, matrixsize, dom);
      com = colorComp(c,d);

      if (brightness(c) > 120) {
        img.pixels[loc] = d + dom.pixels[loc]/com;
        dom.pixels[loc] = c ;
      } else {
        img.pixels[loc] = d + (buffer.pixels[loc]/com);
        dom.pixels[loc] = d - buffer.pixels[loc]/com ;
      }
      
      
    }
  }
}
int matrixsize = 3;
// The convolution matrix kernels (filters).
float[] kernel;
      // Random
float[][] kernelA1 = { { 0, -1, 0 } , 
                     { -1, 9, -1 } ,
                     { 0, -1, -1 } } ;

      // Structures       
float[][] kernelA2 = { { -1, -1, 0 } , 
                     { -1, 9, -1 } ,
                     { 0, -1, 0 } } ;

      // Cubism        
float[][] kernelB = { { -1, -1, -1 } , 
                     { -1, 9, -1 } ,
                     { -1, -1, -1 } } ;

      // Dimention
float[][] kernelC = { { 0, -1, 0 } , 
                     { -1, 5, -1 } ,
                     { 0, -1, 0 } } ;  

      // Psy
float[][] kernelD = { { 0, 1, 0 } , 
                     { 1, 1, 1 } ,
                     { 0, 1, 0 } } ; 

      // FingerPrint
float[][] kernelE = { { -1, 1, 0 } , 
                     { 1, 1, 1 } ,
                     { 0, 1, 0 } } ; 
                     
      // Psy II
float[][] kernelF = { { 0, 1, 0 } , 
                     { 1, -1, 1 } ,
                     { 0, 1, 0 } } ; 
                     
      // Waves
float[][] kernelG = { { 0, 0, 0 } , 
                     { 1, -1, 1 } ,
                     { 0, 0, 0 } } ;   

      // Random Flowers
float[][] kernelH = { {0, 1, 0 } , 
                     { 1, 9, 1 } ,
                     { 0, 1, 0 } } ;  
                     
      // Random Flowers
float[][] kernelI = { {-2, -1, 0 } , 
                     { -1, 1, 1 } ,
                     { 0, 1, 2 } } ;  
                     
      // Diagonal Lines 
float[][] kernelJ = { {-2, 0, 3 } , 
                     { 0, 0, 0 } ,
                     { -3, 0, 2 } } ;

      // Lines I
float[][] kernelK = { {1, 1, 1 } , 
                      {1, 0, 1 } ,
                      {1, 1, 1 } } ;  


      // Lines II
float[][] kernelL = { {-2, -1, 3 } , 
                     { 1, 0, -1 } ,
                     { -3, 1, 2 } } ;  
                     
                                
float[][] matrix = kernelL;

                  
float zoom = 1.5f;
public void keyReleased() {
  switch(key) {
    case 'S':
    case 's':
      img.save("Assets/Output/"+imageName+"/"+imageName+extension);
      println("| Image saved as: " + imageName +extension);
      break;
    case 'C':
    case 'c':
      println("zoomIn");
      zoom += 0.05f;
      break;
    case 'X':
    case 'x':
      zoom -= 0.05f;  
      break;
  }

}
int centerX = 0, centerY = 0, offsetX = 0, offsetY = 0;

public void trackMouse() {
  if (mousePressed == true) {
    centerX = mouseX-offsetX;
    centerY = mouseY-offsetY;
    println("center: [" + centerX + ", " + centerY + " ]");
  }
}

public void mousePressed(){
  offsetX = mouseX-centerX;
  offsetY = mouseY-centerY;
}
  public void settings() {  size(1200,700);  smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "ImgSorting" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
