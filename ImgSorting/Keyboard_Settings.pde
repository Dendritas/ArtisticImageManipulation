float zoom = 1.5;
void keyReleased() {
  switch(key) {
    case 'S':
    case 's':
      img.save("Assets/Output/"+imageName+"/"+imageName+extension);
      println("| Image saved as: " + imageName +extension);
      break;
    case 'C':
    case 'c':
      println("zoomIn");
      zoom += 0.05;
      break;
    case 'X':
    case 'x':
      zoom -= 0.05;  
      break;
  }

}
