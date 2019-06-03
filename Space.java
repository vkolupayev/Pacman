import greenfoot.*;
import java.util.List;

public class Space extends World
{
    private Interface scoreNow; //to store the score
    private Interface start = new Interface("Ready?"); //message for start
    public Pacman pacman = new Pacman();
    public boolean powerUp = false; //condition for power up
    private int frames = 0;
    private boolean gameOver = false;
    private boolean gameWin = false;
    public int ghostTime = 50;
    public int MAX_GHOST_TIME = 150;
    public Space()
    {    
        super(95, 120, 4); // 95x120, 4x1
        Greenfoot.setSpeed(45);
        Interface textScore = new Interface("Score");
        addObject(textScore, 47, 2);
        scoreNow = new Interface();
        addObject(scoreNow, 47, 7);// sets score to 0 and displays it
        addObject(start, 47, 72);
        addObject(pacman, 47, 92);
        
        // 0-walls, 1-food, 2-power up, 3-empty space
       int[] Level =
       {    
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
            0,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,0,
            0,2,0,0,1,0,0,0,1,0,1,0,0,0,1,0,0,2,0,
            0,1,0,0,1,0,0,0,1,0,1,0,0,0,1,0,0,1,0,
            0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,
            0,1,0,0,1,0,1,0,0,0,0,0,1,0,1,0,0,1,0,
            0,1,1,1,1,0,1,1,1,0,1,1,1,0,1,1,1,1,0,
            0,0,0,0,1,0,0,0,3,0,3,0,0,0,1,0,0,0,0,
            3,3,3,0,1,0,3,3,3,3,3,3,3,0,1,0,3,3,3,
            0,0,0,0,1,0,3,0,0,3,0,0,3,0,1,0,0,0,0,
            1,1,1,1,1,3,3,0,3,3,3,0,3,3,1,1,1,1,1,
            0,0,0,0,1,0,3,0,0,0,0,0,3,0,1,0,0,0,0,
            3,3,3,0,1,0,3,3,3,3,3,3,3,0,1,0,3,3,3,
            0,0,0,0,1,0,3,0,0,0,0,0,3,0,1,0,0,0,0,
            0,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,1,1,0,
            0,1,0,0,1,0,0,0,1,0,1,0,0,0,1,0,0,1,0,
            0,2,1,0,1,1,1,1,1,3,1,1,1,1,1,0,1,2,0,
            0,0,1,0,1,0,1,0,0,0,0,0,1,0,1,0,1,0,0,
            0,1,1,1,1,0,1,1,1,0,1,1,1,0,1,1,1,1,0,
            0,1,0,0,0,0,0,0,1,0,1,0,0,0,0,0,0,1,0,
            0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,
            0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0
        };
        
        genLevel(Level);

        Ghost ghostG = new Ghost("green");
        addObject(ghostG, 42, 62);
        Ghost ghostP = new Ghost("orange");
        addObject(ghostP, 47, 62);
        Ghost ghostY = new Ghost("purple");
        addObject(ghostY, 52, 62);
        Ghost ghostR = new Ghost("red");
        addObject(ghostR, 47, 57);
        
    }
    
    public void act() //Keeps checking the state of the game
    {
        begin();
        
        if(gameOver)endGame();
     
        if (!gameWin)
        {  
            
            int amount = getObjects(Food.class).size(); //how much food
            
                if (amount == 0)
                {  
                    gameWin = true;  
                    stopChar();  
                
                    Interface gameOverText = new Interface("You win!");
                    addObject(gameOverText, 47, 72);
                }
         }
    }
    
    public void begin()
    {
        removeObject(start);
    }
    
    //stops pacman and ghosts through a list
    public void stopChar()  
    {  
        pacman.active = false;
        List ghosts = getObjects(Ghost.class);
        
        for(int i = 0;i<ghosts.size();i++)
        {
            Ghost ghost = (Ghost) ghosts.get(i);
            ghost.active = false;
            
        }
    }
    
    
    public Interface getInterface()
    {
        return scoreNow;
    }

    
    public void genLevel(int array[]) //generates the level
    {
        int i = 0;
        
        //Starting from 2x12 to 95x120
        for(int y = 12; y<120; y+=5)
        {
            for(int x = 2; x<95; x+=5)
            {
                int check = array[i];
                
                if(check == 0)
                {
                    addObject(new Wall(), x, y);
                }
                    else if (check == 1)
                    {
                        addObject(new Food(), x, y);
                    } 
                        else if (check == 2)
                        {
                            addObject(new Power(), x, y);
                        }
                i++;
            }
        }
    }

    public void endGame() //Pacman get killed but the ghosts keep roaming
    {
        if(frames == 0)
        {    
            gameOver = true;
            Interface lostText = new Interface("You lose!");
            addObject(lostText, 47, 72);
            pacman.active = false;
            pacman.setImage("PacmanDeath.png");
        } 
            else if(frames == 3)
            {
                pacman.setImage("PacmanDeath1.png");
            }
                else if(frames == 6)
                {
                    pacman.setImage("PacmanDeath2.png");
                }
                    else if(frames == 9)
                    {
                        pacman.setImage("PacmanDeath3.png");
                    }
                        else if(frames == 12)
                        {
                            pacman.setImage("PacmanDeath4.png");
                        }
                        else if(frames == 15)
                        {
                            pacman.setImage("PacmanDeath5.png");
                        }
                        else if(frames == 18)
                        {
                            pacman.setImage("PacmanDeath6.png");
                        }
                        else if(frames == 21)
                        {
                            removeObject(pacman);
                        }
        frames++;
    }
}
