package org.neocities.d3suu.csgo.gravity;

public class Grenade {
    public float X;
    public float Y;

    // Absolute velocity is object velocity
    public float absVelocityX;
    public float absVelocityY;
    public Exception GrenadeAngleOvershoot;

    Grenade(){
        this.X = 0;
        this.Y = 0;
        this.absVelocityX = 0f;
        this.absVelocityY = 0f;
    }

    public void setX(float X){
        this.X = X;
    }

    public void setY(float Y){
        this.Y = Y;
    }

    // -------------------------------
    // Purpose: set starting velocity by power and angle
    // -------------------------------
    public void setAnglePower(float angle, float power) throws Exception {
        if(angle >= 0 && angle <= 90) {
            this.absVelocityY = (float) (Math.sin( Math.toRadians(angle) ) * power);
            this.absVelocityX = (float) (Math.cos( Math.toRadians(angle) ) * power);
        }else{
            throw GrenadeAngleOvershoot;
        }
    }
}
