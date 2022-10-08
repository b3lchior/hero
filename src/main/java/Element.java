import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class Element {
    private Position position;

    public Element(int x , int y){
        this.position = new Position(x,y);

    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position pos){
        this.position = pos ;
    }

    abstract public void draw(TextGraphics graphics);


}
