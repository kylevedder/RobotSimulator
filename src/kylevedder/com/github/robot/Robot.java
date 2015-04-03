/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kylevedder.com.github.robot;

import kylevedder.com.github.main.CenteredRectangle;
import kylevedder.com.github.physics.Vector;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

/**
 *
 * @author kyle
 */
public class Robot
{

    private final int JOYSTICK_NUM = 0;
    private final int AXIS_X = 0;
    private final int AXIS_Y = 1;
    private final int AXIS_TWIST = 2;
    private final int AXIS_SLIDER = 3;

    private final float MAX_SPEED = 80;

    private final float WIDTH = 80;
    private final float HEIGHT = 100;

    private CenteredRectangle hitBox = null;
    private Vector vectorFR = null;
    private Vector vectorFL = null;
    private Vector vectorBR = null;
    private Vector vectorBL = null;

    private Vector vectorTurnFR = null;
    private Vector vectorTurnFL = null;
    private Vector vectorTurnBR = null;
    private Vector vectorTurnBL = null;

    private Vector vectorResultantFR = null;
    private Vector vectorResultantFL = null;
    private Vector vectorResultantBR = null;
    private Vector vectorResultantBL = null;

    public Robot(float x, float y)
    {
        this.hitBox = new CenteredRectangle(x, y, WIDTH, HEIGHT, 0);
        this.vectorBL = new Vector(0, 0);
        this.vectorBR = new Vector(0, 0);
        this.vectorFL = new Vector(0, 0);
        this.vectorFR = new Vector(0, 0);

        this.vectorTurnBL = new Vector(0, 0);
        this.vectorTurnBR = new Vector(0, 0);
        this.vectorTurnFL = new Vector(0, 0);
        this.vectorTurnFR = new Vector(0, 0);

        this.vectorResultantBL = new Vector(0, 0);
        this.vectorResultantBR = new Vector(0, 0);
        this.vectorResultantFL = new Vector(0, 0);
        this.vectorResultantFR = new Vector(0, 0);
    }

    public void update(GameContainer container, int delta)
    {
        double angleRad = Math.toRadians(this.hitBox.getAngle());

        vectorBL = updateDriveVector(container, vectorBL);
        vectorBR = updateDriveVector(container, vectorBR);
        vectorFL = updateDriveVector(container, vectorFL);
        vectorFR = updateDriveVector(container, vectorFR);

        float twist = container.getInput().getAxisValue(JOYSTICK_NUM, AXIS_TWIST);

        System.out.println("Twist: " + twist);

        float baseAngle = (float) Math.toDegrees(Math.atan2(WIDTH / 2, HEIGHT / 2));
        vectorTurnFR = new Vector(twist * 20, (float) this.hitBox.getAngle() + baseAngle + ((twist < 0)? 180 :0));
        vectorTurnFL = new Vector(twist * 20, (float) this.hitBox.getAngle() + baseAngle - 90 + ((twist < 0)? 180 :0));
        vectorTurnBR = new Vector(twist * 20, (float) this.hitBox.getAngle() + baseAngle + 90 + ((twist < 0)? 180 :0));
        vectorTurnBL = new Vector(twist * 20, (float) this.hitBox.getAngle() + baseAngle + 180 + ((twist < 0)? 180 :0));

        this.vectorResultantFR = Vector.add(vectorFR, vectorTurnFR);
        this.vectorResultantFL = Vector.add(vectorFL, vectorTurnFL);
        this.vectorResultantBR = Vector.add(vectorBR, vectorTurnBR);
        this.vectorResultantBL = Vector.add(vectorBL, vectorTurnBL);

        this.hitBox.updateDelta(0, 0, container.getInput().getAxisValue(JOYSTICK_NUM, AXIS_TWIST) * 40 / (1000 / delta));
    }

    public void render(Graphics g)
    {
        this.hitBox.render(g);
        g.setColor(Color.black);
        double angleRad = Math.toRadians(this.hitBox.getAngle());
        System.out.println(Math.toDegrees(angleRad));
        float frX = hitBox.getCenterX();
        float frY = hitBox.getCenterY();
        frX += (float) Math.sin(angleRad) * HEIGHT / 2;
        frY -= (float) Math.cos(angleRad) * HEIGHT / 2;

        frX += (float) Math.cos(angleRad) * WIDTH / 2;
        frY += (float) Math.sin(angleRad) * WIDTH / 2;

        float flX = hitBox.getCenterX();
        float flY = hitBox.getCenterY();
        flX += (float) Math.sin(angleRad) * HEIGHT / 2;
        flY -= (float) Math.cos(angleRad) * HEIGHT / 2;

        flX -= (float) Math.cos(angleRad) * WIDTH / 2;
        flY -= (float) Math.sin(angleRad) * WIDTH / 2;

        float brX = hitBox.getCenterX();
        float brY = hitBox.getCenterY();
        brX -= (float) Math.sin(angleRad) * HEIGHT / 2;
        brY += (float) Math.cos(angleRad) * HEIGHT / 2;

        brX += (float) Math.cos(angleRad) * WIDTH / 2;
        brY += (float) Math.sin(angleRad) * WIDTH / 2;

        float blX = hitBox.getCenterX();
        float blY = hitBox.getCenterY();
        blX -= (float) Math.sin(angleRad) * HEIGHT / 2;
        blY += (float) Math.cos(angleRad) * HEIGHT / 2;

        blX -= (float) Math.cos(angleRad) * WIDTH / 2;
        blY -= (float) Math.sin(angleRad) * WIDTH / 2;

        System.out.println((float) Math.cos(angleRad) * HEIGHT / 2);
        g.drawLine(frX, frY, frX + this.vectorFR.getXComp(), frY + this.vectorFR.getYComp());
        g.drawLine(flX, flY, flX + this.vectorFL.getXComp(), flY + this.vectorFL.getYComp());
        g.drawLine(brX, brY, brX + this.vectorBR.getXComp(), brY + this.vectorBR.getYComp());
        g.drawLine(blX, blY, blX + this.vectorBL.getXComp(), blY + this.vectorBL.getYComp());

        g.setColor(Color.blue);

        g.drawLine(frX, frY, frX + this.vectorTurnFR.getXComp(), frY + this.vectorTurnFR.getYComp());
        g.drawLine(flX, flY, flX + this.vectorTurnFL.getXComp(), flY + this.vectorTurnFL.getYComp());
        g.drawLine(brX, brY, brX + this.vectorTurnBR.getXComp(), brY + this.vectorTurnBR.getYComp());
        g.drawLine(blX, blY, blX + this.vectorTurnBL.getXComp(), blY + this.vectorTurnBL.getYComp());
        
        g.setColor(Color.red);
        g.drawLine(frX, frY, frX + this.vectorResultantFR.getXComp(), frY + this.vectorResultantFR.getYComp());
        g.drawLine(flX, flY, flX + this.vectorResultantFL.getXComp(), flY + this.vectorResultantFL.getYComp());
        g.drawLine(brX, brY, brX + this.vectorResultantBR.getXComp(), brY + this.vectorResultantBR.getYComp());
        g.drawLine(blX, blY, blX + this.vectorResultantBL.getXComp(), blY + this.vectorResultantBL.getYComp());

    }

    /**
     * Update vector based upon user input.
     *
     * @param container
     * @param vector
     * @param maxSpeed
     * @return
     */
    private Vector updateDriveVector(GameContainer container, Vector vector)
    {
        Input input = container.getInput();
        vector.setSpeed(0);
        if (input != null)
        {
            float x = input.getAxisValue(JOYSTICK_NUM, AXIS_X) * MAX_SPEED;
            float y = input.getAxisValue(JOYSTICK_NUM, AXIS_Y) * MAX_SPEED;
            float twist = input.getAxisValue(JOYSTICK_NUM, AXIS_TWIST);

            vector = new Vector(x, y, 0);//new vector
            if (vector.getSpeed() > MAX_SPEED)
            {
                vector.setSpeed(MAX_SPEED);
            }
        }
        return vector;
    }

    private Vector updateTurnVector(GameContainer container, Vector vector)
    {
        Input input = container.getInput();
        vector.setSpeed(0);
        if (input != null)
        {
            float x = input.getAxisValue(JOYSTICK_NUM, AXIS_X) * MAX_SPEED;
            float y = input.getAxisValue(JOYSTICK_NUM, AXIS_Y) * MAX_SPEED;
            float twist = input.getAxisValue(JOYSTICK_NUM, AXIS_TWIST);

            vector = new Vector(x, y, 0);//new vector
            if (vector.getSpeed() > MAX_SPEED)
            {
                vector.setSpeed(MAX_SPEED);
            }
        }
        return vector;
    }
}
