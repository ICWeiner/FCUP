import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Wall {
    private Position position;

    public int getX() {
        return position.getX();
    }

    public void setX(int x) {
        position.setX(x);
    }

    public int getY() {
        return position.getY();
    }

    public void setY(int y) {
        position.setX(y);
    }

    Wall(Position position) {
        this.position = position;
    }

    Wall(int x, int y) {
        this.position = new Position(x,y);
    }

    public void draw(TextGraphics graphics){
        graphics.setForegroundColor(
                TextColor.Factory.fromString("#FFFF33"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new
                        TerminalPosition(position.getX(), position.getY()),
                "X");
        graphics.setCharacter(position.getX(), position.getY(),
                TextCharacter.fromCharacter('#')[0]);
    }

}
