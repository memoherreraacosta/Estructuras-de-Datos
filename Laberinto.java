import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.StringTokenizer;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Laberinto extends JPanel {

	private static final long serialVersionUID = 1L;
	private Cuadro[][] cuadros; // Matriz de cuadros
	private QueueLE<Camino> cola;
	private StackLE<Camino> pila;
	private CoordinateConverter converter;
	private int state,
				velocidad,
				aglomeracion,
				tamanoPixel;
	private Camino fin,
				   inicio;
	private ControlPanel cp;
	private Image brick;

	public Laberinto(ControlPanel cp) {
		super();
		this.cp = cp;
		this.tamanoPixel = 40;
		this.setPreferredSize(new Dimension(this.tamanoPixel * 20, this.tamanoPixel * 20 - 10));
		this.cuadros = new Cuadro[20][20];
		this.converter = new CoordinateConverter();
		this.state = 1;
		this.velocidad = 75;
		this.aglomeracion = 4;
		
		try {
			this.brick=ImageIO.read(new File("brick.jpeg"));
		}catch(IOException ex) {}
		
		this.reset();

		this.addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent arg0) {}

			public void mousePressed(MouseEvent arg0) {}

			public void mouseExited(MouseEvent arg0) {}

			public void mouseEntered(MouseEvent arg0) {}

			public void mouseClicked(MouseEvent e) {
				try {
					if (Laberinto.this.cuadros[e.getY() / tamanoPixel][e.getX() / tamanoPixel] instanceof Pared) {
						return;
					}
					if (Laberinto.this.state == 1) {
						Laberinto.this.inicio = (Camino) Laberinto.this.cuadros[e.getY() / tamanoPixel][e.getX() / tamanoPixel];
						Laberinto.this.inicio.changeColor("#AAEFF8"); //Color inicio
						Laberinto.this.repaint();
						Laberinto.this.state = 2;
						Laberinto.this.cp.setNotesText("Selecciona el punto final");
					} else if (Laberinto.this.state == 2) {
						if (Laberinto.this.cuadros[e.getY() / tamanoPixel][e.getX() / tamanoPixel] != Laberinto.this.inicio) {
							Laberinto.this.fin = (Camino) Laberinto.this.cuadros[e.getY() / tamanoPixel][e.getX() / tamanoPixel];
							Laberinto.this.fin.changeColor("#AAEFF8"); //Color fin
							Laberinto.this.repaint();
							Laberinto.this.state = 4;
							Laberinto.this.cp.setNotesText("Buscando ruta");
							Laberinto.this.startSimulation();
						}
					}
				} catch (ArrayIndexOutOfBoundsException ex) {}
			}
		});
	}

	public void startSimulation() {

		Thread hilo = new Thread(new Runnable() {

			public void run() {
				boolean found = false;
				Laberinto.this.cola.enqueue(Laberinto.this.inicio);
				Camino current = null; 
				while (!Laberinto.this.cola.isEmpty()) {
					try {
						current = Laberinto.this.cola.dequeue();

						Laberinto.this.pila.push(current);
						if (current != Laberinto.this.inicio) {
							current.changeColor("#FFFFFF"); //Color cuando esta buscando en el camino
						}
						if (current == Laberinto.this.fin) {
							current.changeColor("#AAEFF8"); //Cuadro final
							Laberinto.this.repaint();
							current.addPath(new int[] { Laberinto.this.converter.matrixToLinear(current.getY() / tamanoPixel,
									current.getX() / tamanoPixel, Laberinto.this.cuadros.length) });
							found = true;

							break;
						} else {
							// Sacar el camino previo de current
							QueueLE<Integer> caminoPrevio = current.getPath();
							int[] path = new int[caminoPrevio.size() + 1];
							int i = 0; // Contador
							while (!caminoPrevio.isEmpty()) {
								path[i] = caminoPrevio.dequeue();
								i++;
							}
							// Aniadir la coordenada del nodo actual
							path[i] = Laberinto.this.converter.matrixToLinear(current.getY() / tamanoPixel,
									current.getX() / tamanoPixel, Laberinto.this.cuadros.length);

							// Agregar vecinos
							if (current.getX() / tamanoPixel + 1 < Laberinto.this.cuadros.length) {
								// Vecino de la derecha
								if (Laberinto.this.cuadros[current.getY() / tamanoPixel][current.getX() / tamanoPixel
										+ 1] instanceof Camino) { // Asegurar que el vecino no es una pared
									if (!Laberinto.this.pila.contains((Camino) Laberinto.this.cuadros[current.getY()
											/ tamanoPixel][current.getX() / tamanoPixel + 1])
											&& !Laberinto.this.cola.contains((Camino) Laberinto.this.cuadros[current.getY()
													/ tamanoPixel][current.getX() / tamanoPixel + 1])) {

										((Camino) Laberinto.this.cuadros[current.getY() / tamanoPixel][current.getX()
												/ tamanoPixel + 1]).addPath(path);
										Laberinto.this.cola.enqueue((Camino) Laberinto.this.cuadros[current.getY()
												/ tamanoPixel][current.getX() / tamanoPixel + 1]);
									}
								}
							}
							if (current.getX() / tamanoPixel - 1 >= 0) {
								// Vecino de la izquierda
								if (Laberinto.this.cuadros[current.getY() / tamanoPixel][current.getX() / tamanoPixel
										- 1] instanceof Camino) { // Asegurar que el vecino no es una pared
									if (!Laberinto.this.pila.contains((Camino) Laberinto.this.cuadros[current.getY()
											/ tamanoPixel][current.getX() / tamanoPixel - 1])
											&& !Laberinto.this.cola.contains((Camino) Laberinto.this.cuadros[current.getY()
													/ tamanoPixel][current.getX() / tamanoPixel - 1])) {

										((Camino) Laberinto.this.cuadros[current.getY() / tamanoPixel][current.getX()
												/ tamanoPixel - 1]).addPath(path);
										Laberinto.this.cola.enqueue((Camino) Laberinto.this.cuadros[current.getY()
												/ tamanoPixel][current.getX() / tamanoPixel - 1]);
									}
								}
							}
							if (current.getY() / tamanoPixel - 1 >= 0) {
								// Vecino de arriba
								if (Laberinto.this.cuadros[current.getY() / tamanoPixel - 1][current.getX()
										/ tamanoPixel] instanceof Camino) { // Asegurar que el vecino no es una pared
									if (!Laberinto.this.pila.contains(
											(Camino) Laberinto.this.cuadros[current.getY() / tamanoPixel - 1][current.getX()
													/ tamanoPixel])
											&& !Laberinto.this.cola
													.contains((Camino) Laberinto.this.cuadros[current.getY() / tamanoPixel
															- 1][current.getX() / tamanoPixel])) {

										((Camino) Laberinto.this.cuadros[current.getY() / tamanoPixel - 1][current.getX()
												/ tamanoPixel]).addPath(path);
										Laberinto.this.cola.enqueue((Camino) Laberinto.this.cuadros[current.getY() / tamanoPixel
												- 1][current.getX() / tamanoPixel]);
									}
								}
							}
							if (current.getY() / tamanoPixel + 1 < Laberinto.this.cuadros.length) {
								// Vecino de abajo
								if (Laberinto.this.cuadros[current.getY() / tamanoPixel + 1][current.getX()
										/ tamanoPixel] instanceof Camino) {
									
									// Asegurar que el vecino no es una pared
									if (!Laberinto.this.pila.contains(
											(Camino) Laberinto.this.cuadros[current.getY() / tamanoPixel + 1][current.getX()
													/ tamanoPixel])
											&& !Laberinto.this.cola
													.contains((Camino) Laberinto.this.cuadros[current.getY() / tamanoPixel
															+ 1][current.getX() / tamanoPixel])) {

										((Camino) Laberinto.this.cuadros[current.getY() / tamanoPixel + 1][current.getX()
												/ tamanoPixel]).addPath(path);
										Laberinto.this.cola.enqueue((Camino) Laberinto.this.cuadros[current.getY() / tamanoPixel
												+ 1][current.getX() / tamanoPixel]);
									}
								}
							}
						}
						Laberinto.this.repaint();
						Thread.sleep(Laberinto.this.velocidad);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				if (found) {
					Laberinto.this.showPath(current.getPath());
					Laberinto.this.cp.setNotesText("Se encontro la solucion");
				} else {
					Laberinto.this.cp.setNotesText("No se encontro una solucion al laberinto");
					Laberinto.this.state = 5;
				}
			}
		});
		hilo.start();
	}

	public void reset() {
		this.cola = new QueueLE<Camino>();
		this.pila = new StackLE<Camino>();
		this.state = 1;
		this.cp.setNotesText("Selecciona el punto de inicio");
		Random ran = new Random();
		
		for (int i = 0; i < cuadros.length; i++) {
			for (int j = 0; j < cuadros[i].length; j++) {
				//Implementar paredes y caminos
				if (ran.nextInt(this.aglomeracion) == 0) {
					cuadros[i][j] = new Pared(j * tamanoPixel, i * tamanoPixel, this.tamanoPixel);
				} else {
					cuadros[i][j] = new Camino(j * tamanoPixel, i * tamanoPixel, this.tamanoPixel);
				}
			}
		}

		this.repaint();
	}
	//Axrchivo importado
	public void reset(String filename){
        // Resetea el laberinto con laberinto prefabricado Debe ser 20 x 20
        this.cola = new QueueLE<Camino>();
        this.pila = new StackLE<Camino>();
        this.state = 1;
        this.cp.setNotesText("Selecciona el punto de inicio");
        try{
            BufferedReader bf = new BufferedReader(new FileReader(filename));
            for(int i = 0; i < cuadros.length; i++){
                String line = bf.readLine();
                StringTokenizer tk = new StringTokenizer(line, ",");
                for(int j = 0; j < cuadros[i].length; j++){
                    if(tk.nextToken().equals("1")){
                        cuadros[i][j] = new Pared(j*tamanoPixel, i*tamanoPixel, this.tamanoPixel);
                    }else{
                        cuadros[i][j] = new Camino(j*tamanoPixel, i*tamanoPixel, this.tamanoPixel);
                    }
                }
            }
            bf.close();
        }catch(Exception ex){
            // En caso de excepcion resetearlo de manera aleatoria
            this.reset();
            System.out.println("Hubo un error");
        }
        this.repaint();
    }
	public void setMatriz(int x, int y) {
		this.cuadros= new Cuadro[x][y];
	}

	public void showPath(QueueLE<Integer> path) {
		Thread hilo = new Thread(new Runnable() {

			public void run() {
				try {
					int num;
					while (!path.isEmpty()) {
						num = path.dequeue();
						Thread.sleep(Laberinto.this.velocidad + 50);
						
						((Camino) Laberinto.this.cuadros[Laberinto.this.converter.linearToMatrix(num,
								Laberinto.this.cuadros.length)[0]][Laberinto.this.converter.linearToMatrix(num,
								Laberinto.this.cuadros.length)[1]]).changeColor("#81A4E2"); //Color trazando camino AAEFF8
						Laberinto.this.repaint();
					}
					Laberinto.this.state = 5;

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		hilo.start();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (int i = 0; i < cuadros.length; i++) {
			for (int j = 0; j < cuadros[i].length; j++) {
				
				g.setColor(cuadros[i][j].getColor());
				g.fillRect(cuadros[i][j].getX(), cuadros[i][j].getY(), cuadros[i][j].getSide(),
						cuadros[i][j].getSide());
			
				g.setColor(Color.BLACK);
				g.drawRect(cuadros[i][j].getX(), cuadros[i][j].getY(), cuadros[i][j].getSide(),
						cuadros[i][j].getSide());
				
				if(cuadros[i][j] instanceof Pared) {
					g.drawImage(this.brick, cuadros[i][j].getX(), cuadros[i][j].getY(), cuadros[i][j].getSide(), cuadros[i][j].getSide(), this);
				}
			}
		}
	}

	public void setVelocidad(int velocidad) {
		if (velocidad >= 5 && velocidad <= 1000) {
			this.velocidad = velocidad;
		}
	}

	public int getState() {
		return this.state;
	}

	public void setWallAglomeration(int num) {
		this.aglomeracion = num;
	}

}