public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private static double G = 6.67e-11;
    public Planet (double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    public Planet (Planet p){
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }
    public double calcDistance (Planet p) {
        double dx = p.xxPos - this.xxPos;
        double dy = p.yyPos - this.yyPos;
        double dis = dx * dx + dy * dy;
        return Math.sqrt(dis);
    }

    public double calcForceExertedBy (Planet p) {
        double dis = this.calcDistance(p);
        return (G * p.mass * this.mass) / (dis * dis);
    }

    public double calcForceExertedByX (Planet p) {
        double dis = this.calcDistance(p);
        double f = this.calcForceExertedBy(p);
        return f * (p.xxPos - this.xxPos) / dis;
    }

    public double calcForceExertedByY (Planet p) {
        double dis = this.calcDistance(p);
        double f = this.calcForceExertedBy(p);
        return f * (p.yyPos - this.yyPos) / dis;
    }

    public double calcNetForceExertedByX (Planet [] otherPlanets) {
        double totalForce = 0;
        for (Planet other : otherPlanets) {
            if (this.equals(other)) {
                continue;
            }
            totalForce += this.calcForceExertedByX(other);
        }
        return totalForce;
    }
    public double calcNetForceExertedByY (Planet [] otherPlanets) {
        double totalForce = 0;
        for (Planet other : otherPlanets) {
            if (this.equals(other)) {
                continue;
            }
            totalForce += this.calcForceExertedByY(other);
        }
        return totalForce;
    }

    public void update (double dt, double fX, double fY) {
        double aX = fX / this.mass;
        double aY = fY / this.mass;
        double newXV = this.xxVel + dt * aX;
        double newYV = this.yyVel + dt * aY;
        this.xxVel = newXV;
        this.yyVel = newYV;
        this.xxPos = this.xxPos + dt * newXV;
        this.yyPos = this.yyPos + dt * newYV;
    }

    public void draw(){
        StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);
    }
}
