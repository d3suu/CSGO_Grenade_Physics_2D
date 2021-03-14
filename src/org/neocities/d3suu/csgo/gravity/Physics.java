package org.neocities.d3suu.csgo.gravity;

public class Physics {
    public float gravity;

    // base velocity is world velocity (parent)
    public float baseVelocityX;
    public float baseVelocityY;

    public float frameTime;
    public int currentFrame;

    public float groundY;

    public boolean serialFrames;

    public float surfaceElasticity; // In leaked CS:GO code, surface Elasticity is between 0 and 1, where x>0.9 -> x=0.9
    public float maxBounces; // Not actually used in CS:GO code
    public float currentBounces;

    Physics(){
        this.gravity = 800f;
        this.baseVelocityX = 0f;
        this.baseVelocityY = 0f;
        this.frameTime = 0.01f;
        this.currentFrame = 0;
        this.groundY = 0f;
        this.serialFrames = true;
        this.surfaceElasticity = 0.5f;
        this.maxBounces = 5;
        this.currentBounces = 0;
    }

    public void testOnly(){
        this.serialFrames = false;
    }

    // ----------------------
    // Purpose: build gnuplot data for grenade until it touches ground
    // Defined in: void CBaseEntity::PhysicsAddGravityMove
    // ----------------------
    public void throwGrenadeUntilOnGround(Grenade grenade){
        System.out.println("# Use gnuplot for plot\n# Frame grenadeX grenadeY grenadeVelocityX grenadeVelocityY");
        int i = 0;
        while(true){
            // Next frame
            if(this.serialFrames) {
                this.currentFrame++;
            }

            // Calculate next X value
            // (vecAbsVelocity.x + GetBaseVelocity().x ) * gpGlobals->frametime;
            float moveX = (grenade.absVelocityX + this.baseVelocityX) * this.frameTime;
            grenade.X += moveX;

            // Calculate next Y value
            float newYVelocity = grenade.absVelocityY - this.gravity * this.frameTime; // vecAbsVelocity.z - GetActualGravity( this ) * gpGlobals->frametime;
            // move.z = ((vecAbsVelocity.z + newZVelocity) / 2.0 + GetBaseVelocity().z ) * gpGlobals->frametime;
            float moveY = ((grenade.absVelocityY + newYVelocity) / 2f + this.baseVelocityY) * this.frameTime;
            grenade.Y += moveY;
            grenade.absVelocityY = newYVelocity;

            // If on ground, return
            if(grenade.Y <= this.groundY){
                return;
            }else{
                // Print out values
                System.out.println(i + " " + grenade.X + " " + grenade.Y + " " + grenade.absVelocityX + " " + grenade.absVelocityY);
            }
            i++;
        }
    }

    // ----------------------
    // Purpose: build gnuplot data for grenade for n frames (ticks)
    // Defined in: void CBaseEntity::PhysicsAddGravityMove
    // ----------------------
    public void throwGrenade(Grenade grenade, int frames){
        System.out.println("# Use gnuplot for plot\n# Frame grenadeX grenadeY grenadeVelocityX grenadeVelocityY");
        for(int i = 0; i<frames; i++){
            // Next frame
            if(this.serialFrames) {
                this.currentFrame++;
            }

            // Calculate next X value
            // (vecAbsVelocity.x + GetBaseVelocity().x ) * gpGlobals->frametime;
            float moveX = (grenade.absVelocityX + this.baseVelocityX) * this.frameTime;
            grenade.X += moveX;

            // Calculate next Y value
            float newYVelocity = grenade.absVelocityY - this.gravity * this.frameTime; // vecAbsVelocity.z - GetActualGravity( this ) * gpGlobals->frametime;
            // move.z = ((vecAbsVelocity.z + newZVelocity) / 2.0 + GetBaseVelocity().z ) * gpGlobals->frametime;
            float moveY = ((grenade.absVelocityY + newYVelocity) / 2f + this.baseVelocityY) * this.frameTime;
            grenade.Y += moveY;
            grenade.absVelocityY = newYVelocity;

            // If on ground, return
            if(grenade.Y <= this.groundY){
                return;
            }else{
                // Print out values
                System.out.println(i + " " + grenade.X + " " + grenade.Y + " " + grenade.absVelocityX + " " + grenade.absVelocityY);
            }
        }
    }

    // ----------------------
    // Purpose: build gnuplot data for grenade with bounces
    // Defined in: void CBaseEntity::ResolveFlyCollisionBounce
    // ----------------------
    public void throwGrenadeWithBounce(Grenade grenade){
        System.out.println("# Use gnuplot for plot\n# Frame grenadeX grenadeY grenadeVelocityX grenadeVelocityY");
        int i = 0;
        while(true){
            // Next frame
            if(this.serialFrames) {
                this.currentFrame++;
            }

            // Calculate next X value
            // (vecAbsVelocity.x + GetBaseVelocity().x ) * gpGlobals->frametime;
            float moveX = (grenade.absVelocityX + this.baseVelocityX) * this.frameTime;
            grenade.X += moveX;

            // Calculate next Y value
            float newYVelocity = grenade.absVelocityY - this.gravity * this.frameTime; // vecAbsVelocity.z - GetActualGravity( this ) * gpGlobals->frametime;
            // move.z = ((vecAbsVelocity.z + newZVelocity) / 2.0 + GetBaseVelocity().z ) * gpGlobals->frametime;
            float moveY = ((grenade.absVelocityY + newYVelocity) / 2f + this.baseVelocityY) * this.frameTime;
            grenade.Y += moveY;
            grenade.absVelocityY = newYVelocity;

            // If on ground, bounce
            if(grenade.Y <= this.groundY){
                if(grenade.absVelocityY != 0) {
                    grenade.absVelocityY *= this.surfaceElasticity;
                    grenade.absVelocityY = -grenade.absVelocityY;
                    grenade.Y = -grenade.Y;
                    this.currentBounces++;
                }
                if(this.currentBounces >= this.maxBounces){
                    return;
                }
            }else{
                // Print out values
                System.out.println(i + " " + grenade.X + " " + grenade.Y + " " + grenade.absVelocityX + " " + grenade.absVelocityY);
            }
            i++;
        }
    }
}
