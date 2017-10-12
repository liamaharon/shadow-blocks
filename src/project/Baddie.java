package project;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public abstract class Baddie extends SmartSprite
{

    public Baddie(String src, TileCoord pos) throws SlickException
    {
        super(src, pos);
    }

    // baddies are also blocked by cracked walls
    @Override
    public boolean canMoveTo(TileCoord pos, LevelManager levelManager)
    {
        return super.canMoveTo(pos, levelManager) ||
               levelManager.getCrackedWallFromCoord(pos) != null;
    }
    @Override
    public boolean canMoveTo(TileCoord pos, Direction dir, LevelManager levelManager)
    {
        return super.canMoveTo(pos, levelManager) ||
                levelManager.getCrackedWallFromCoord(pos) != null;
    }

    // baddies try to attack the player every update
    @Override
    public void update(LevelManager levelManager, Input input, int delta) throws SlickException
    {
        attack(getPos(), levelManager);
    }

    // baddies are constantly trying to 'attack' the Player. this means if the
    // baddie is at the same position as the Player, the level should restart
    public void attack(TileCoord pos, LevelManager levelManager)
    {
        if (levelManager.getCurGameState().getPlayerCoord().equals(pos))
        {
            levelManager.restartLevel();
        }
    }
}
