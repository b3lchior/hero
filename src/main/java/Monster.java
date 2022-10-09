import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.awt.*;
import java.util.List;
import java.util.Random;

public class Monster extends Element{
    public Monster(int x , int y){
        super (x, y);
    }

    public Position move(Arena arena , List<Coin> coins){
        Random random = new Random();

        while (true){
            Position ret = new Position(this.getPosition().getX() + random.nextInt(3) - 1, this.getPosition().getY() + random.nextInt(3) - 1);
            if(ret.getX() > 0 && ret.getX() < arena.getWidth()-1 && ret.getY() > 1 && ret.getY() < arena.getHeight()-1 && check(coins , ret) == false)
                return ret;
        }


    }


    private boolean check(List<Coin> coins , Position pos){
        boolean f = false;
        for (Coin coin : coins){
            if(pos.equals(coin.getPosition())) f = true;
        }
        return f;
    }
    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#8B0000"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(this.getPosition().getX(), this.getPosition().getY()), "M");
    }
}
