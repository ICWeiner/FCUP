import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

public class Arena {
    Hero hero = new Hero(10,10);
    private int width;
    private int height;

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void draw(Screen screen){
        hero.draw(screen);
    }

    public void draw(TextGraphics graphics){
        hero.draw(graphics);
    }

    private boolean canHeroMove(Position position){
        if(position.getX() >= width || position.getY() >= height
        || position.getX() <= 0     || position.getY() <= 0){
            return false;
        }
        return true;
    }

    public void moveHero(Position position) {
        if (canHeroMove(position))
            hero.setPosition(position);
    }

    public void processKey(KeyStroke key) {
        switch (key.getKeyType()) {
            case ArrowLeft:
                moveHero(hero.moveLeft());
                break;
            case ArrowRight:
                moveHero(hero.moveRight());
                break;
            case ArrowUp:
                moveHero(hero.moveUp());
                break;
            case ArrowDown:
                moveHero(hero.moveDown());
                break;
        }
    }

    public void draw(Position p){
        hero.setPosition(p);
    }

}
