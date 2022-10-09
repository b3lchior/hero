import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {
    private Screen screen;
    private Arena arena;


    public Game() {
        arena= new Arena(50, 24);

        try {
            TerminalSize terminalSize = new TerminalSize(arena.getWidth() , arena.getHeight());
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);
            TextGraphics graphics = screen.newTextGraphics();

            screen.setCursorPosition(null);
            screen.doResizeIfNecessary();
            screen.startScreen();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void draw() throws IOException {
        screen.clear();
        arena.draw(screen.newTextGraphics());
        screen.refresh();
    }

    private void processKey(KeyStroke key) throws IOException {
        arena.processKey(key , screen);
    }



    public void run() throws IOException {
        while(true){
            draw();
            KeyStroke key = screen.readInput();
            processKey(key);
            if ( key.getKeyType() == KeyType.Character && key.getCharacter() == 'q'){ screen.close();}


            if ( key.getKeyType() == KeyType.EOF) break ;

            if(arena.verifyMonsterCollisions()){
                screen.close();
                break;
            }

            if (arena.nCoins == arena.countCoins) {
                System.out.println("Winner Winner Chicken Dinner");
                screen.close();
            }


        }
    }
}
