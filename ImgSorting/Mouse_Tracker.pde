int centerX = 0, centerY = 0, offsetX = 0, offsetY = 0;

void trackMouse() {
  if (mousePressed == true) {
    centerX = mouseX-offsetX;
    centerY = mouseY-offsetY;
    println("center: [" + centerX + ", " + centerY + " ]");
  }
}

void mousePressed(){
  offsetX = mouseX-centerX;
  offsetY = mouseY-centerY;
}
