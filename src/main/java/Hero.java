import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;

public class Hero {
    private Position position = new Position(10,10);

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position pos){
        this.position = pos ;
    }

    public Position moveUp() {return new Position(position.getX(), position.getY() - 1);}                                               // hero movement
    public Position moveDown() {
        return new Position(position.getX(), position.getY() + 1);
    }
    public Position moveRight() {
        return new Position(position.getX() + 1, position.getY());
    }
    public Position moveLeft() {
        return new Position(position.getX() - 1, position.getY());
    }

    public void draw(Screen screen_){
        screen_.setCharacter( position.getX() , position.getY(), TextCharacter.fromCharacter('X')[0]);

    }


}
