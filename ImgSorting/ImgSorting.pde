PImage img, dom, copy, buffer;
String context;
String imageName = "";
String batch = "_a";
String extension = "jpg";
int index = 0;
// Iteration Times
int limit = 5;
int imgIndex = 0;
boolean selected = false;
void setup() {  
  size(1200,700);

  imageLoader();

  cursor(HAND);
  smooth();
  randomSeed(2);
}


void draw() {
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



