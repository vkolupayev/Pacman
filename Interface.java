import greenfoot.*;


public class Interface extends Actor
{
    private int totalCount = 0;
    
    public Interface(String text)
    {
        setImage(new GreenfootImage(text, 25, Color.WHITE, Color.BLACK));
    }
    
    public Interface()
    {
        setImage(new GreenfootImage("0", 25, Color.WHITE, Color.BLACK));
    }

    public void bumpCount(int amount)
    {
        totalCount += amount;
        setImage(new GreenfootImage("" + totalCount, 20, Color.WHITE, Color.BLACK));
    }
}