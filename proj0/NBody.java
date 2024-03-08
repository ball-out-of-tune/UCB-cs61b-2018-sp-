public class NBody {
    public static double readRadius(String filename){
        In in = new In(filename);
        int num = in.readInt();
        double radius = in.readDouble();
        return radius;
    }


    public static Planet[] readPlanets(String filename){
        In in = new In(filename);
        int num = in.readInt();
        double radius = in.readDouble();
        Planet[] planets = new Planet[num];
        for(int i = 0; i < num; i++){
            double xp = in.readDouble();
            double yp = in.readDouble();
            double vx = in.readDouble();
            double vy = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            planets[i] = new Planet(xp, yp, vx, vy, m, img);
        }
        return planets;
    }

    public static void main(String args[]){

        double T = Double.parseDouble(args[0]);
        double dT = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet planets[] = readPlanets(filename);
        int len = planets.length;
        String imageToDraw = "images/starfield.jpg";
        StdDraw.setXscale(-radius, radius);
        StdDraw.setYscale(-radius,radius);
        StdDraw.enableDoubleBuffering();

        double time = 0;
        for (time = 0; time < T; time += dT){
            double[] xForces = new double[len];
            double[] yForces = new double[len];
            StdDraw.picture(0,0,imageToDraw);
            for (int i = 0; i < len; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
                planets[i].update(dT,xForces[i],yForces[i]);
                planets[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }

//        In in = new In(filename);
//        int num = in.readInt();
//        System.out.println(num);
//        double radius = in.readDouble();
//        System.out.print(radius);
//        for (int i = 0; i < len; i++){
//            double m1 = in.readDouble();
//            double m2 = in.readDouble();
//            double m3 = in.readDouble();
//            double m4 = in.readDouble();
//            double m5 = in.readDouble();
//            System.out.print(m1 + "" + m2 + " " + m3 + " " + m4 + " " + m5 + " " + planets[i]) ;
//        }
          StdOut.printf("%d\n", planets.length);
          StdOut.printf("%.2e\n", radius);
          for (int i = 0; i < planets.length; i++) {
              StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                      planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                      planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}

