/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kylevedder.com.github.physics;

import kylevedder.com.github.main.CenteredRectangle;
import kylevedder.com.github.main.Main;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

/**
 *
 * @author Kyle
 */
public class PhysicsBoxControlled extends PhysicsBox
{

    public PhysicsBoxControlled(float x, float y, float width, float height, float rotation, float speed, float angle)
    {
        super(x, y, width, height, rotation, speed, angle);
    }

    public PhysicsBoxControlled(float x, float y, float width, float height, float rotation, float speed, float angle, float rotationSpeed)
    {
        super(x, y, width, height, rotation, speed, angle, rotationSpeed);
    }

    @Override
    public void update(int delta)
    {
        this.update(null, delta);
    }

    
    
    public void update(GameContainer container, int delta)
    {
        System.out.println("00000000000000000");
        System.out.println(this.vector);
        this.vector = updateVector(container, vector, 20);                
        this.hitBox.updateDelta(this.vector.getXComp()/(1000/delta), this.vector.getYComp()/(1000/delta), 0);
        System.out.println(this.vector);
        System.out.println("11111111111111111");
    }

    private Vector updateVector(GameContainer container, Vector vector, float maxSpeed)
    {
        Input input = container.getInput();        
        vector.setSpeed(0);
        if (input != null)
        {           
            //drive forward
            if (input.isKeyDown(Input.KEY_UP) || input.isKeyDown(Input.KEY_W))
            {
                vector.setSpeed(maxSpeed);
                vector.setAngle(-90);
//                vector.setA
            }
            //drive forward
            if (input.isKeyDown(Input.KEY_DOWN) || input.isKeyDown(Input.KEY_S))
            {
                vector.setSpeed(maxSpeed);
            }
            //turn left
            if (input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_A))
            {
                vector.setSpeed(maxSpeed);
            }
            //turn right
            if (input.isKeyDown(Input.KEY_RIGHT) || input.isKeyDown(Input.KEY_D))
            {
                vector.setSpeed(maxSpeed);
            }            
        }
        return vector;
    }

    @Override
    public void render(Graphics g)
    {
        super.render(g); //To change body of generated methods, choose Tools | Templates.
        g.setColor(Color.red);
        g.drawLine(this.hitBox.getCenterX(), this.hitBox.getCenterY(), this.hitBox.getCenterX() + vector.getXComp(), this.hitBox.getCenterY() + vector.getYComp());
        g.setColor(Color.black);
        g.drawLine(this.hitBox.getCenterX(), this.hitBox.getCenterY(), this.hitBox.getCenterX() + 20*(float)Math.cos(Math.toRadians(vector.getAngle())), this.hitBox.getCenterY() + 20*(float)Math.sin(Math.toRadians(vector.getAngle())));
    }
    
    

}
