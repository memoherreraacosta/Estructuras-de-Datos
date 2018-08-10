import java.awt.Color;

public class Cuadro{
    private Color color;
    private int x, 
    				y,
    				side;
    
    public Cuadro(int x, int y){
        this(x, y, 50);
    }
    
    public Cuadro(int x, int y, int size){
        this.color = Color.decode("#F0DEC8"); //color cuadro
        //Agregar imagen de cuadro
        this.x = x;
        this.y = y;
        this.side = size;
    }
    
    public Color getColor(){
        return this.color;
    }
    
    public void setColor(String color){
        this.color = Color.decode(color);
    }
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
    
    public int getSide(){
        return this.side;
    }

}

class Pared extends Cuadro{

    public Pared(int x, int y) {
        this(x, y, 50);
    }
    
    public Pared(int x, int y, int size){
        super(x, y, size);
        this.setColor("#008000"); //color pared
    }

}
