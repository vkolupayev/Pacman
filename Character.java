import greenfoot.*;
import java.util.List;
import java.util.ArrayList;


public class Character  extends Actor
{
    public void act() 
    {
        
    }
    //for tunnel
    public boolean atEdge()
    {
        if(getX() > getWorld().getWidth() - 2 || getX() == 0)
            return true;
        else
            return false;
    }
    
    public boolean active(String direction)
    {
        int x;
        int y;
        
        if(direction == "up")
        {
            x = getX();
            y = getY()-1;
        }
            else if(direction == "down")
            {
                x = getX();
                y = getY()+1;
            }
                else if(direction == "right")
                {
                    x = getX()+1;
                    y = getY();
                }
                    else
                    {
                        x = getX()-1;
                        y = getY();
                    }
        
        int prievX = getX();
        int prievY = getY();
        setLocation(x,y);
        Actor theWall = getOneIntersectingObject(Wall.class);
        setLocation(prievX, prievY);
        
        return theWall == null; //No collision
    }
    
    public boolean interact(Class form)
    {
        Actor actor = getOneIntersectingObject(form);
        return actor != null;   //eat method
    }
    
    public void eat(Class form)
    {
        Actor actor = getOneObjectAtOffset(0, 0, form);
        
        if(actor != null && form != Pacman.class && form != Ghost.class) //food, power  up, ghosty
        {
            getWorld().removeObject(actor);
        }
        
        Space world = (Space) getWorld();
        Interface Interface = world.getInterface();
        
        if(form == Food.class)
        {
            Interface.bumpCount(10);
        }
            else if (form == Power.class)
            {
                Interface.bumpCount(50);
                world.powerUp = true;
                world.ghostTime = 0;
            }
                else if (form == Ghost.class)   //with power up for pac
                {
                    Interface.bumpCount(100);
                    List ghosts = world.getObjectsAt(getX(), getY(), Ghost.class);
                    
                    for(int i = 0;i<ghosts.size();i++)
                    {
                        Ghost ghost = (Ghost) ghosts.get(i);
                        ghost.die();
                    }
                    
                }
                    else if (form == Pacman.class)  //for ghost
                    {
                        world.endGame();
                    }
    }
    
    }
