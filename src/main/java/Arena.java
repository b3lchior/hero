import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.*;

import static java.util.Objects.isNull;

public class Arena {
    private int width;
    private int height;
    private Hero hero;
    private List<Wall> walls;
    private List<Coin> coins;
    private List<Monster> monsters;
    private int countCoins = 0;


    public Arena(int w, int h) {
        this.width = w;
        this.height = h;
        this.hero = new Hero(10, 10);
        this.walls = createWalls();
        this.coins = createCoins();
        this.monsters = createMonster();

    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void processKey(KeyStroke key, Screen screen) throws IOException {
        if (key.getKeyType() == KeyType.ArrowUp) {
            moveHero(hero.moveUp());
        }
        else if (key.getKeyType() == KeyType.ArrowDown) {
            moveHero(hero.moveDown());
        }
        else if (key.getKeyType() == KeyType.ArrowRight) {
            moveHero(hero.moveRight());
        }
        else if (key.getKeyType() == KeyType.ArrowLeft) {
            moveHero(hero.moveLeft());
        }
        else if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
            screen.close();
        }
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#4B0082"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        this.hero.draw(graphics);
        graphics.putString(new TerminalPosition(0,0) , "Score:");
        graphics.putString(new TerminalPosition(7,0) , String.valueOf(countCoins));
        for (Wall wall : walls) wall.draw(graphics);

        for (Coin coin : coins){
            if (!coin.getPosition().equals(this.hero.getPosition())) coin.draw(graphics);
        }

        for (Monster monster : monsters) {
            monster.draw(graphics);
            }
        }




    private void moveHero(Position position) {
        if (canHeroMove(position)) hero.setPosition(position);
        coins.remove(retrieveCoins(position));
        moveMonsters();
    }

    private boolean canHeroMove(Position position) {
        for (Wall wall : walls) {
            if (wall.getPosition().equals(position)) return false;
        }
        return true;
    }


    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 1));
            walls.add(new Wall(c, height - 1));
        }
        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }
        return walls;
    }


    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            coins.add(new Coin(random.nextInt(width - 4) + 1, random.nextInt(height - 5) + 1));
        return coins;
    }

    private List<Monster> createMonster() {
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            monsters.add(new Monster(random.nextInt(width - 2) + 1, random.nextInt(height - 5) + 1));
        return monsters;
    }




    private Coin retrieveCoins(Position pos){
        for(Coin coin : coins){
            if (coin.getPosition().equals(pos)){
                countCoins += 1;
                return coin;}
        }
        return null;
    }

    public void moveMonsters(){
        for(Monster monster : monsters){
            monster.setPosition(monster.move(this , this.coins));
        }
    }

    public boolean verifyMonsterCollisions(){
        for(Monster monster : monsters){
            if(monster.getPosition().equals(hero.getPosition())){
                System.out.println("Death.");
                return true;
            }
        }
        return false;

    }
}
