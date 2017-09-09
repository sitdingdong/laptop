PShape earth;
PShape moon;
void setup() {
  size(800,800, P3D);
  noStroke();
  earth = createShape(SPHERE, 400);
  moon =createShape(SPHERE, 100);
  PImage img = loadImage("earthmap1k.jpg");
  earth.setTexture(img);
}

float ang = 0;
void draw() {
  background(0);
  translate(width/2,height/2,-400);
  pushMatrix();
  rotateZ(23.5*PI/180);
  rotateY(ang);
  shape(earth);
  ang += 0.01;
  popMatrix();
  translate(width/10, 20, -100);
  shape(moon);
}