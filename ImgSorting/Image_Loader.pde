// Loads custom image file.
void imageLoader() {

	selectInput("Select a file to process:", "fileSelected");
}

void fileSelected(File selection) {
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
