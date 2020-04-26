public class NBody {

    /** read the radius from file */
    public static double readRadius(String filepath){
        double Radius;
        In in = new In(filepath);
        int n = in.readInt();
        Radius = in.readDouble();
        return Radius;
    }

    public static Body[] readBodies(String filepath){
        In in = new In(filepath);
        int n = in.readInt();
        double Radius = in.readDouble();
        Body[] b = new Body[n];
        for(int i=0;i < n;i++){
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();
            b[i] = new Body(xxPos,yyPos,xxVel,yyVel,mass,img);
        }
        return b;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        double t = 0;
        String filename = args[2];
        double r = readRadius(filename);
        Body[] B = readBodies(filename);
        int n = B.length;

        StdDraw.setScale(-r,r);
        StdDraw.clear();
        StdDraw.picture(0,0,"images/starfield.jpg" );
        for(Body i : B){
            i.draw();
        }
        StdDraw.disableDoubleBuffering();

        while(t <= T){
            double[] xForces = new double[n];
            double[] yForces = new double[n];
            StdDraw.clear();
            StdDraw.picture(0,0,"images/starfield.jpg" );
            for(int i = 0;i < n;i++){
                xForces[i] = B[i].calcNetForceExertedByX(B);
                yForces[i] = B[i].calcNetForceExertedByX(B);
            }
            for(int i = 0;i < n;i++){
                B[i].update(dt,xForces[i],yForces[i]);
            }
            for (Body b: B) {
                b.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            t += dt;
        }

        StdOut.printf("%d\n", B.length);
        StdOut.printf("%.2e\n", r);
        for (int i = 0; i < B.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    B[i].xxPos, B[i].yyPos, B[i].xxVel,
                    B[i].yyVel, B[i].mass, B[i].imgFileName);
        }

    }


}
