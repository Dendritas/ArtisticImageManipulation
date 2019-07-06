// Compares the brightness between to colors.
color colorComp(color c1, color c2) {
  float r = red(c1);
  float g = green(c1);
  float b = blue(c1);
  float rSel = red(255);
  float gSel = green(255);
  float bSel = blue(255);
  float brightness = c2;
  color newColor = color(0, 0, 0);
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
