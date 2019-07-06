// Convolution by Daniel Shiffman. 
color convolution(int x, int y, float[][] matrix, int matrixsize, PImage inputImg) {
  float rtotal = 0.0;
  float gtotal = 0.0;
  float btotal = 0.0;
  float rgbTotal = 0.0;
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
