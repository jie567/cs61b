
public class NBody {
    public static double readRadius(String planetsTxtPath){
        In in=new In(planetsTxtPath);
        int firstItemInFile = in.readInt();
        double secondItemInFile = in.readDouble();
        return secondItemInFile;
    }

    public static Planet[] readPlanets(String planetsTxtPath){
        In in=new In(planetsTxtPath);
        int planetNumbers = in.readInt();
        double Radius = in.readDouble();
        Planet[] re=new Planet[5];
        for(int i=0;i<5;i++){
            double xxPos=in.readDouble();
            double yyPos=in.readDouble();
            double xxVel=in.readDouble();
            double yyVel=in.readDouble();
            double mass=in.readDouble();
            String imgFileName=in.readString();
            re[i]=new Planet(xxPos,yyPos,xxVel,yyVel,mass,imgFileName);
        }
        return re;
    }

    public static void main(String[] args){
        double T=Double.valueOf(args[0].toString());
        double dt=Double.valueOf(args[1].toString());
        String filename=args[2];
        double radius=readRadius(filename);
        Planet[] planet=NBody.readPlanets(filename);
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius,radius);
        double time=0;
        int number=planet.length;
        while(time<=T){
            double[] xForces=new double[number];
            double[] yForces=new double[number];
            for (int i=0;i<number;i++){
                xForces[i]=planet[i].calcNetForceExertedByX(planet);
                yForces[i]=planet[i].calcNetForceExertedByY(planet);
            }
            for (int i=0;i<number;i++){
                planet[i].update(dt,xForces[i],yForces[i]);
            }
            StdDraw.clear();
            StdDraw.picture(0,0,"images/starfield.jpg");
            for (Planet b:planet){
                b.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            time+=dt;
        }
        StdOut.printf("%d\n", planet.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planet.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planet[i].xxPos, planet[i].yyPos, planet[i].xxVel,
                    planet[i].yyVel, planet[i].mass, planet[i].imgFileName);
        }
    }
}

