import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Arena {
    private int width ;
    private int height;
    private Hero hero;
    private List<Wall> walls;



    public Arena( int w, int h){
        this.width = w;
        this.height = h;
        hero = new Hero(10,10);

    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    public void processKey(KeyStroke key , Screen screen) throws IOException {
        if (key.getKeyType() == KeyType.ArrowUp){ moveHero(hero.moveUp());}
        if (key.getKeyType() == KeyType.ArrowDown){ moveHero(hero.moveDown());}
        if (key.getKeyType() == KeyType.ArrowRight){ moveHero(hero.moveRight());}
        if (key.getKeyType() == KeyType.ArrowLeft){ moveHero(hero.moveLeft());}

        if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q'){screen.close();
        }
    }

    public void draw(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#4B0082"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        this.hero.draw(graphics);

    }

    private void moveHero(Position position) {
        if (canHeroMove(position)) hero.setPosition(position);
    }

    private boolean canHeroMove(Position position){
        if (position.getX() < width && position.getY() < height && position.getX() >0 && position.getY() > 0) return true;
        return false;
    }

    private void createWalls() {

    }
}
