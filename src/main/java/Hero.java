import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

public class Hero extends Element{

    public Hero(int x , int y){
        super (x,y);
    }


    public Position moveUp() {return new Position(this.getPosition().getX(), this.getPosition().getY() - 1);}                                               // hero movement
    public Position moveDown() {return new Position(this.getPosition().getX(), this.getPosition().getY() + 1);}
    public Position moveRight() {return new Position(this.getPosition().getX() + 1, this.getPosition().getY());}
    public Position moveLeft() {return new Position(this.getPosition().getX() - 1, this.getPosition().getY());}

    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#F0F9FF"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(this.getPosition().getX(), this.getPosition().getY()), "X");
    }


}
