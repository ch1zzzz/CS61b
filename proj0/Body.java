public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Body(double xP,double yP,double xV,double yV,double m,String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body b){
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    /** calculate the distance between bodies */
    public double calcDistance(Body b){
        double dist;
        double x2 = Math.pow(this.xxPos - b.xxPos,2);
        double y2 = Math.pow(this.yyPos - b.yyPos,2);
        dist = Math.sqrt(x2 + y2);
        return dist;
    }

    /** calculate the force between bodies */
    public double calcForceExertedBy(Body b){
        double force;
        double G = 6.67e-11;
        double dist = this.calcDistance(b);
        force = (G * this.mass * b.mass)/Math.pow(dist,2);
        return force;
    }

    /** calculate the force exerted from b by X */
    public double calcForceExertedByX(Body b){
        double forceX;
        double dist = calcDistance(b);
        double force = this.calcForceExertedBy(b);
        forceX = force * (b.xxPos - this.xxPos) / dist;
        return forceX;
    }

    /** calculate the force exerted from b by Y */
    public double calcForceExertedByY(Body b){
        double forceY;
        double dist = calcDistance(b);
        double force = this.calcForceExertedBy(b);
        forceY = force * (b.yyPos - this.yyPos) / dist;
        return forceY;
    }

    /** calculate the force exerted from a list of bodies by X */
    public double calcNetForceExertedByX(Body[] bl){
        double FxNet = 0;
        for(Body i : bl){
            if(!this.equals(i)){
                FxNet += this.calcForceExertedByX(i);
            }
        }
        return FxNet;
    }

    /** calculate the force exerted from a list of bodies by Y */
    public double calcNetForceExertedByY(Body[] bl){
        double FyNet = 0;
        for(Body i : bl){
            if(!this.equals(i)){
                FyNet += this.calcForceExertedByY(i);
            }
        }
        return FyNet;
    }

    public void update(double dt,double fX,double fY){
        double ax = fX / this.mass;
        double yx = fY / this.mass;
        this.xxVel += ax * dt;
        this.yyVel += yx * dt;
        this.xxPos += this.xxVel * dt;
        this.yyPos += this.yyVel * dt;
    }

    public void draw(){
        StdDraw.picture(this.xxPos,this.yyPos,"images/" + this.imgFileName);
    }
}
