
public class NBody {
    public static double readRadius (String address) {
        In in = new In(address);
        int numberOfPlanets = in.readInt();
        double universeRadius = in.readDouble();
        return universeRadius;
    }
    public static Planet [] readPlanets (String address) {
        In in = new In(address);
        int num = in.readInt();
        in.readDouble();
        Planet[] planets = new Planet[num];
        for (int i = 0; i < num; i++) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();
            Planet planet = new Planet(xxPos, yyPos, xxVel, yyVel, mass, img);
            planets[i] = planet;
        }
        return planets;
    }
    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Planet[] planets = readPlanets(filename);
        double radius = readRadius(filename);
        String starfield = "images/starfield.jpg";
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius, radius);
        StdDraw.picture(0, 0, starfield);
        for (Planet planet: planets) {
            planet.draw();
        }
        int n = planets.length;
        for (double time = 0; time < T; time+=dt) {
            double [] xForces = new double [n];
            double [] yForces = new double [n];
            for (int i = 0; i < n; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for (int i = 0; i < n; i++) {
                planets[i].update(dt,xForces[i],yForces[i]);
            }
            StdDraw.picture(0, 0, starfield);
            for (Planet planet: planets) {
                planet.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}
