import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class Arena {
    private int width;
    private int height;
    private Hero hero = new Hero();

    public Arena( int w , int h){
        width = w;
        height = h;

    }


    public void processKey(KeyStroke key , Screen screen) throws IOException {
        if (key.getKeyType() == KeyType.ArrowUp){ moveHero(hero.moveUp());}
        if (key.getKeyType() == KeyType.ArrowDown){ moveHero(hero.moveDown());}
        if (key.getKeyType() == KeyType.ArrowRight){ moveHero(hero.moveRight());}
        if (key.getKeyType() == KeyType.ArrowLeft){ moveHero(hero.moveLeft());}

        if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q'){screen.close();
        }
    }

    public void draw(Screen screen_){
        screen_.setCharacter( hero.getPosition().getX(), hero.getPosition().getY(), TextCharacter.fromCharacter('X')[0]);
    }

    private void moveHero(Position position) {
        if (canHeroMove(position)) hero.setPosition(position);
    }

    private boolean canHeroMove(Position position){
        if (position.getX() < width && position.getY() < height && position.getX() > -width && position.getY() > -height) return true;
        return false;
    }

}
