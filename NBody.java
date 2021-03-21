public class NBody{
    public static double readRadius(String filename){
      In in = new In(filename);
      in.readInt();
      return in.readDouble();
    }


    public static Body[] readBodies(String filename){
      In in = new In(filename);
      int n = in.readInt();
      in.readDouble();
      Body[] bodies = new Body[n];
      for (int i=0; i<n; i++){
          double xxPos=in.readDouble();
          double yyPos=in.readDouble();
          double xxVel=in.readDouble();
          double yyVel=in.readDouble();
          double mass=in.readDouble();
          String imgFileName=in.readString();
          bodies[i]=new Body(xxPos,yyPos,xxVel,yyVel,mass,imgFileName);
      }
      return bodies;
    }

    public static void main(String[] args) {
        double t = 0;
        double T, dt;
        String filename;
        T = Double.parseDouble(args[0]);
        dt = Double.parseDouble(args[1]);
        filename = args[2];
        Body[] bodies = readBodies(filename);
        double radius = readRadius(filename);
        int n = bodies.length;

        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();

        while (t < T) {
            double[] xForces = new double[n];
            double[] yForces = new double[n];

            for (int i = 0; i < n; i++) {
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
            }

            for (int i = 0; i < n; i++) {
                bodies[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");

            for (Body body : bodies) {
                body.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);

            t += dt;
        }


        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < bodies.length; i += 1) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                    bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
        }
    }
}
