package org.neocities.d3suu.csgo.gravity;

public class Main {

    public static void main(String[] args) throws Exception {
	// write your code here
        Grenade grenade = new Grenade();
        //grenade.absVelocityY = 1000;
        //grenade.absVelocityX = 1000;
        grenade.setAnglePower(80,1000);
        System.out.println("# Debug angle: " + grenade.absVelocityX + " " + grenade.absVelocityY);

        Physics physics = new Physics();
        physics.testOnly();
        //physics.throwGrenade(grenade, 300);
        //physics.throwGrenadeUntilOnGround(grenade);
        physics.throwGrenadeWithBounce(grenade);

    }
}
