/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kylevedder.com.github.physics;

import kylevedder.com.github.main.CenteredRectangle;
import kylevedder.com.github.main.Main;

import org.newdawn.slick.Graphics;

/**
 *
 * @author Kyle
 */
public class PhysicsBox extends PhysicsObject
{

    private float x;
    private float y;
    private float width;
    private float height;

    public PhysicsBox(float x, float y, float width, float height, float rotation, float speed, float angle)
    {
        this.hitBox = new CenteredRectangle(x, y, width, height, rotation);
        this.vector = new Vector(speed, angle);
    }
    
    public PhysicsBox(float x, float y, float width, float height, float rotation, float speed, float angle, float rotationSpeed)
    {
        this.hitBox = new CenteredRectangle(x, y, width, height, rotation);
        this.vector = new Vector(speed, angle, rotationSpeed);
    }

    public void update(int delta)
    {

    }

    public void render(Graphics g)
    {
        this.hitBox.render(g);
    }

}
