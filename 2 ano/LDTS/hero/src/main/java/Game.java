import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game{
    private Screen screen;
    private Arena arena = new Arena(15,15);
    public Game(){
        try{
            TerminalSize terminalSize = new TerminalSize(30, 30);
            DefaultTerminalFactory terminalFactory = new
                    DefaultTerminalFactory()
                    .setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);

            screen.startScreen();

            screen.doResizeIfNecessary();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void processKey(KeyStroke key) {
        arena.processKey(key);
    }

    private void draw(){
        try{
            screen.clear();
            arena.draw(screen);
            //hero.draw(screen);
            screen.refresh();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void run(){
        KeyStroke key = new KeyStroke('a',false,false);
        while (  key.getKeyType() != KeyType.EOF ||
                (key.getKeyType() == KeyType.Character
                && key.getCharacter() != 'q')){
            try{
                key = screen.readInput();
                processKey(key);
                draw();
            }catch (IOException e){
                e.printStackTrace();
            }

        }
    }
}
