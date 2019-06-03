import greenfoot.*;
import java.util.List;
import java.util.ArrayList;

public class Pacman extends Character
{
    private static GreenfootImage Up1 = new GreenfootImage("PacmanUp.png");
    private static GreenfootImage Up2 = new GreenfootImage("PacmanUp1.png");
    private static GreenfootImage Up3 = new GreenfootImage("PacmanUp2.png");
    private static GreenfootImage Down1 = new GreenfootImage("PacmanDown.png");
    private static GreenfootImage Down2 = new GreenfootImage("PacmanDown1.png");
    private static GreenfootImage Down3 = new GreenfootImage("PacmanDown2.png");
    private static GreenfootImage Right1 = new GreenfootImage("PacmanRight.png");
    private static GreenfootImage Right2 = new GreenfootImage("PacmanRight1.png");
    private static GreenfootImage Right3 = new GreenfootImage("PacmanRight2.png");
    private static GreenfootImage Left1 = new GreenfootImage("PacmanLeft.png");
    private static GreenfootImage Left2 = new GreenfootImage("PacmanLeft1.png");
    private static GreenfootImage Left3 = new GreenfootImage("PacmanLeft2.png");
    private static GreenfootImage pacFull = new GreenfootImage("PacmanFull.png");
    private GreenfootImage imgNow = pacFull;
    private boolean openMouth = false;
    public boolean active = true;
   
    public void act() //keep getting the direction through keys
    {
        if(active)
        {
            
        String direction = getDirection();
        
        if(active(direction)) //character, with a new direction, remembers the key
        {
            lastPressed = direction;
            move(direction);
        }
            else if (active(lastPressed))//if there was no new key input
            {
                move(lastPressed);
            }
        
        if(interact(Food.class))
        {
            eat(Food.class);
        }
        
        if(interact(Power.class))
        {
            eat(Power.class);
        }
        //to access world class variables
        Space world = (Space) getWorld();
        
        if(world.ghostTime > world.MAX_GHOST_TIME)
        {
            world.powerUp = false;
            world.ghostTime = 0;
        }
        
        if(world.powerUp) //on power up can eat ghosts
        {
            if(interact(Ghost.class))
            {
                eat(Ghost.class);
            }
            world.ghostTime++;//timer
        }
        
        if(atEdge())
        {
            if(direction == "right")//check if the direction is proper near the tunnel
            {
                setLocation(0, getY());
            }
                else if (direction == "left")
                {
                    setLocation(getWorld().getWidth(), getY());
                }
            }
        }
    }
    
    public void move(String direction)//pacman movement with animation
    {
        
        if(direction == "right")
        {
            //animations
            if(imgNow == pacFull || imgNow == Right1 || (imgNow != Right1 && imgNow != Right2 && imgNow != Right3))//closing mouth
            {
                setImage(Right2);
                imgNow = Right2;
                openMouth = false;
            }
            if(imgNow == Right2 && openMouth) //closing mouth
            {
                setImage(Right1);
                imgNow = Right1;
            }
            if(imgNow == Right2 && !openMouth) //opening mouth
            {
                setImage(Right3);
                imgNow = Right3;
            }
            if(imgNow == Right3) //opening mouth
            {
                setImage(Right2);
                imgNow = Right2;
                openMouth = true;
            }
            setLocation(getX()+1,getY()); // actual movement
        }
        
        if(direction == "left")
        {
            if(imgNow == pacFull || imgNow == Left1 || (imgNow != Left1 && imgNow != Left2 && imgNow != Left3))
            {
                setImage(Left2);
                imgNow = Left2;
                openMouth = false;
            }
            if(imgNow == Left2 && openMouth)
            {
                setImage(Left1);
                imgNow = Left1;
            }
            if(imgNow == Left2 && !openMouth)
            {
                setImage(Left3);
                imgNow = Left3;
            }
            if(imgNow == Left3)
            {
                setImage(Left2);
                imgNow = Left2;
                openMouth = true;
            }
            setLocation(getX()-1,getY());
        }
        
        if(direction == "up")
        {
            if(imgNow == pacFull || imgNow == Up1 || (imgNow != Up1 && imgNow != Up2 && imgNow != Up3))
            {
                setImage(Up2);
                imgNow = Up2;
                openMouth = false;
            }
            if(imgNow == Up2 && openMouth)
            {
                setImage(Up1);
                imgNow = Up1;
            }
            if(imgNow == Up2 && !openMouth)
            {
                setImage(Up3);
                imgNow = Up3;
            }
            if(imgNow == Up3)
            {
                setImage(Up2);
                imgNow = Up2;
                openMouth = true;
            }
            setLocation(getX(),getY()-1);
        }
        
        if(direction == "down")
        {
            if(imgNow == pacFull || imgNow == Down1 || (imgNow != Down1 && imgNow != Down2 && imgNow != Down3))
            {
                setImage(Down2);
                imgNow = Down2;
                openMouth = false;
            }
            if(imgNow == Down2 && openMouth)
            {
                setImage(Down1);
                imgNow = Down1;
            }
            if(imgNow == Down2 && !openMouth)
            {
                setImage(Down3);
                imgNow = Down3;
            }
            if(imgNow == Down3)
            {
                setImage(Down2);
                imgNow = Down2;
                openMouth = true;
            }
            setLocation(getX(),getY()+1);
        }
    }
    
    public String getDirection()
    {
        String direction = Greenfoot.getKey();
        if(Greenfoot.isKeyDown("w")) direction = "up";
        if(Greenfoot.isKeyDown("s")) direction = "down";
        if(Greenfoot.isKeyDown("a")) direction = "left";
        if(Greenfoot.isKeyDown("d")) direction = "right";
        if(direction != null)
        {
            return direction;
        }
            else return lastPressed;
    }
    
    private String lastPressed = null;
}