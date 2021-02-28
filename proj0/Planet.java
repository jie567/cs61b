public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    static final double G=6.67*10e-11;
    public Planet(double xP, double yP, double xV, double yV, double m, String img){
        xxPos=xP;
        yyPos=yP;
        xxVel=xV;
        yyVel=yV;
        mass=m;
        imgFileName=img;
    }
    public Planet(Planet p){
        xxPos=p.xxPos;
        yyPos=p.yyPos;
        xxVel=p.xxVel;
        yyVel=p.yyVel;
        mass=p.mass;
        imgFileName=p.imgFileName;
    }

    public double calcDistance(Planet a){
        double x=a.xxPos-this.xxPos;
        double y=a.yyPos-this.yyPos;
        return Math.sqrt(x*x+y*y);
    }
    public  double calcForceExertedBy(Planet a){
        double r=this.calcDistance(a);
        double F=(G*this.mass*a.mass)/(r*r);
        return F/10;
    }
    public double calcForceExertedByX(Planet a){
        double r=this.calcDistance(a);
        double x=a.xxPos-this.yyPos;
        double F=this.calcForceExertedBy(a);
        double Fx=F*x/r;
        return Fx;
    }
    public double calcForceExertedByY(Planet a){
        double r=this.calcDistance(a);
        double y=a.yyPos-this.yyPos;
        double F=this.calcForceExertedBy(a);
        double Fy=F*y/r;
        return Fy;
    }

    public double calcNetForceExertedByX(Planet[] allPlanets){
        double[] Fx=new double[allPlanets.length];
        double Fxx=0;
        for(int i=0;i<allPlanets.length;i++){
            if(this.equals(allPlanets[i])){
                continue;
            }
            Fx[i]=this.calcForceExertedByX(allPlanets[i]);
            Fxx+=Fx[i];
        }
        return Fxx;
    }
    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double[] Fy = new double[allPlanets.length];
        double Fyy = 0;
        for (int i = 0; i < allPlanets.length; i++) {
            if (this.equals(allPlanets[i])) {
                continue;
            }
            Fy[i] = this.calcForceExertedByY(allPlanets[i]);
            Fyy += Fy[i];
        }
        return Fyy;
    }

    public void update(double t,double Fx,double Fy){
        double ax=Fx/this.mass;
        double ay=Fy/this.mass;
        this.xxVel+=ax*t;
        this.yyVel+=ay*t;
        this.xxPos+=this.xxVel*t;
        this.yyPos+=this.yyVel*t;
    }

    public void draw(){
        StdDraw.enableDoubleBuffering();
        StdDraw.picture(this.xxPos,this.yyPos,"images/"+this.imgFileName);
        StdDraw.show();
        StdDraw.pause(1);
    }
}