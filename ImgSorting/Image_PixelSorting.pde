// Pixel Sorting by Emilio Parra. 
// Pixel Sorting Algorithm
void pixelSorting() {
  buffer = img;
  buffer.loadPixels();
  println("  imgIndex: " + imgIndex);
  int neighbors = 2;
    // Begin our loop for every pixel
  for (int x = 0; x < img.width; x++ ) {
    color com;
    for (int y = 0; y < img.height; y++) {
      // Each pixel location (x,y) gets passed into a function called convolution()
      // The convolution() function returns a new color to be displayed.
      int loc = x + y*img.width;
      img.updatePixels();
      dom.updatePixels();
      color c = convolution(x,y, kernelC, matrixsize, img);
      color d = convolution(x,y, kernelL, matrixsize, dom);
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
