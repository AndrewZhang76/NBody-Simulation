public class Body{
  public double xxPos;
  public double yyPos;
  public double xxVel;
  public double yyVel;
  public double mass;
  public String imgFileName;
  private static final double G=6.67e-11;

//Constructors://
  public Body(double xP, double yP, double xV, double yV, double m, String img){
    this.xxPos=xP;
    this.yyPos=yP;
    this.xxVel=xV;
    this.yyVel=yV;
    this.mass=m;
    this.imgFileName=img;
  }

  public Body(Body p){
    this.xxPos=p.xxPos;
    this.yyPos=p.yyPos;
    this.xxVel=p.xxVel;
    this.yyVel=p.yyVel;
    this.mass=p.mass;
    this.imgFileName=p.imgFileName;
  }
  public double calcDistance(Body second){
    return Math.sqrt((this.xxPos-second.xxPos)*(this.xxPos-second.xxPos)+(this.yyPos-second.yyPos)*(this.yyPos-second.yyPos));
  }

  public double calcForceExertedBy(Body second){
    return this.G*this.mass*second.mass/this.calcDistance(second)/this.calcDistance(second);
  }

  public double calcForceExertedByX(Body second){
    return (second.xxPos-this.xxPos)*this.calcForceExertedBy(second)/this.calcDistance(second);
  }
  public double calcForceExertedByY(Body second){
    return (second.yyPos-this.yyPos)*this.calcForceExertedBy(second)/this.calcDistance(second);
  }

  public double calcNetForceExertedByX(Body[] allBodies){
    double netFx=0;
    for(int i=0;i<allBodies.length;i=i+1){
      if (this.equals(allBodies[i])){
      }else{
      netFx=netFx+this.calcForceExertedByX(allBodies[i]);
      }
    }
    return netFx;
  }

  public double calcNetForceExertedByY(Body[] allBodies){
    double netFy=0;
    for(int i=0;i<allBodies.length;i=i+1){
      if (this.equals(allBodies[i])){
      }else{
      netFy=netFy+this.calcForceExertedByY(allBodies[i]);
      }
    }
    return netFy;
  }

  public void update(double dt, double fx, double fy){
    this.xxVel=this.xxVel+dt*(fx/this.mass);
    this.yyVel=this.yyVel+dt*(fy/this.mass);
    this.xxPos=this.xxPos+dt*this.xxVel;
    this.yyPos=this.yyPos+dt*this.yyVel;
  }

  public void draw() {
    StdDraw.picture(xxPos,yyPos,"images/"+imgFileName);
  }
}
