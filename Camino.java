
public class Camino extends Cuadro{
    
    QueueLE<Integer> camino;
    
    public Camino(int x, int y){
        this(x, y, 50);
    }
    
    public Camino(int x, int y, int size) {
        super(x, y, size);
        this.camino = new QueueLE<Integer>();
    }
    
    public QueueLE<Integer> getPath(){
        return this.camino;
    }
    
    public void addPath(int[] newPath){
        for(int coordinate: newPath){
            camino.enqueue(coordinate);
        }
    }
    
    public void changeColor(String color){
        this.setColor(color);
    }
    
}
