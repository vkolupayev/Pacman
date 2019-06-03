import greenfoot.*;

public class Ghost  extends Character
{
    private boolean isOut = false;
    public boolean active = true;
    private String direction = null;
    private String color = null;
    private int timer = -1;
    private boolean ghostDead = false;
    private static int MAX_DEAD_TIME = 150;
    private GreenfootImage ghostBlue = new GreenfootImage("Ghosty.png");
    
    public Ghost(String ghostColor)
    {
        color = ghostColor;
        setColor();
    }
    
    public void act() 
    {
        if(isOut && active)
        {
            go();
            
            Space world = (Space) getWorld();
            
            if(!ghostDead)
            {
                if(world.powerUp)
                {
                    setImage(ghostBlue);
                }
                    else
                    {
                        setColor();
                    }
            }
            
            if(ghostDead)
            {
                if(timer >= MAX_DEAD_TIME)
                {
                    ghostDead = false;
                    setColor();
                    timer = -1;
                }
                timer++;
            }
            
            if(interact(Pacman.class) && !world.powerUp && !ghostDead)
            {
                eat(Pacman.class);
            }
        
            if(atEdge())
            {
                if(direction == "right")
                {
                   setLocation(0, getY());
                }   
                    else if (direction == "left")
                    {
                        setLocation(getWorld().getWidth(), getY());
                    }
            }
            
        } 
            else//get those ghosts into spacexdxxdxdx!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            {
                int x = getX();
                int y = getY();
                if(x == 47 && y == 57)
                {
                // red
                setLocation(47, 56);
                }
                else if(x == 47 && y == 56)
                {
                    setLocation(47, 55);
                }
                else if(x == 47 && y == 55)
                {
                    setLocation(47, 54);
                }
                else if(x == 47 && y == 54)
                {
                    setLocation(47, 53);
                }
                else if(x == 47 && y == 53)
                {
                    setLocation(47, 52);
                    isOut = true;
                    //timer = -1;
                }
                else if(x == 47 && y == 62)
                {
                    // orange
                    setLocation(47, 61);
                }
                else if(x == 47 && y == 61)
                {
                    setLocation(47, 60);
                }
                else if(x == 47 && y == 60)
                {
                    setLocation(47, 59);
                }
                else if(x == 47 && y == 59)
                {
                    setLocation(47, 58);
                }
                else if(x == 47 && y == 58)
                {
                    setLocation(47, 57);
                }
                else if(x == 42 && y == 62)
                {
                // green
                setLocation(43, 62);
            }
            else if(x == 43 && y == 62)
              {       
                setLocation(44, 62);
            }
            else if(x == 44 && y == 62)
            {
                setLocation(45, 62);
            }
            else if(x == 45 && y == 62)
            {
                setLocation(46, 62);
            }
            else if(x == 46 && y == 62)
            {
                setLocation(47, 62);
            }
            else if(x == 52 && y == 62 /*&& timer == 5*/)
            {
                // Purple
                setLocation(51, 62);
            }
            else if(x == 51 && y == 62)
            {
                setLocation(50, 62);
            }
            else if(x == 50 && y == 62)
            {
                setLocation(49, 62);
            }
            else if(x == 49 && y == 62)
            {
                setLocation(48, 62);
            }
            else if(x == 48 && y == 62)
            {
                setLocation(47, 62);
            }
            //timer++;
        }
    }
    
    public void go()
    {
        if(direction != null && active(direction))
        {
            move(direction);
        }
            else
            {
                int randNum = Greenfoot.getRandomNumber(4);
                direction = setDir(randNum);
                go(); //loopy
            }
    }
    
    public String setDir(int randNum)
    {
        String direction = "";
        if(randNum == 0)
        {
            direction = "left";
        }
            else if(randNum == 1)
            {
                direction = "right";
            }
            else if(randNum == 2)
            {
            direction = "up";
            }
                else
                {
                        direction = "down";
                }
        return direction;
    }
    
    public void setColor()
    {
        if(color == "green")
        {
            setImage("GhostG.png");
        }
            else if(color == "orange")
            {
                setImage("GhostO.png");
            }
            else if(color == "red")
            {
                setImage("GhostR.png");
            }
            else if(color == "purple")
            {
                setImage("GhostP.png");
            }
    }
    
    public void move(String direction)
    {
        if(direction == "right")
        {
            setLocation(getX()+1,getY());
        }
        if(direction == "left")
        {
            setLocation(getX()-1,getY());
        }
        if(direction == "up")
        {
            setLocation(getX(),getY()-1);
        }
        if(direction == "down")
        {
            setLocation(getX(),getY()+1);
        }
    }
    
    public void die()
    {
        setImage("GhostDead.png");
        ghostDead = true;
    }
}