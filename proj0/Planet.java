public class Planet{
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private static final double G = 6.67e-11;
    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img)
    {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p){
        return Math.sqrt( (p.xxPos - xxPos)*(p.xxPos - xxPos) + (p.yyPos - yyPos)*(p.yyPos - yyPos) );
    }

    public double calcForceExertedBy(Planet p){
        return G*p.mass*mass/calcDistance(p)/calcDistance(p);
    }

    public double calcForceExertedByX(Planet p){
        return calcForceExertedBy(p)*(p.xxPos - xxPos)/calcDistance(p);
    }

    public double calcForceExertedByY(Planet p){
        return calcForceExertedBy(p)*(p.yyPos - yyPos)/calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] planets){
        double netForceExertedByX = 0;
        for (int i = 0; i < planets.length; i++){
            if (!this.equals(planets[i]))
                netForceExertedByX += calcForceExertedByX(planets[i]);
        }
        return  netForceExertedByX;
    }

    public double calcNetForceExertedByY(Planet[] planets){
        double netForceExertedByY = 0;
        for(Planet p : planets){
            if(!this.equals(p))
                netForceExertedByY += calcForceExertedByY(p);
        }
        return netForceExertedByY;
    }

    public void update(double dt,double fX,double fY){
        double aX = fX/mass;
        double aY = fY/mass;
        xxVel += aX*dt;
        yyVel += aY*dt;
        xxPos += xxVel*dt;
        yyPos += yyVel*dt;
    }


    public void draw(){
        StdDraw.picture(xxPos,yyPos,"images/" + imgFileName);
    }

}
