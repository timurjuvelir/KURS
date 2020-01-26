import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.uci.ics.jung.algorithms.layout.KKLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;

/** 
 * @author Vladimir Chernyavskiy
 * @version 1.0
*/
public class Main {

	/**  Поле количества вершин. */
	private int n;
	
	/**  Поле вероятности. */
	private double p;

	/**
	 * this - указатель на объект.

	 */
	public Main(int n, double p) {
		this.n = n; // кол-во вершин
		this.p = p; // вероятность создания ребра
	}

	/**
	 * Цикл генерации рандомного графа.
	 * addVertex - метод добавляющий вершину.
	 * Math.random - возвращение псевдослучайного числа с плавающей запятой.
	 * addEdge - добавление ребра.
	 */
	public Graph<Integer, String> generateRandomGraph() {
		Graph<Integer, String> g = new SparseMultigraph<Integer, String>();
	
		for (int i = 0; i < n; i++) {
			g.addVertex(i);
		}

		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (Math.random() < p) {
					g.addEdge(i + "-to-" + j, i, j);
				}
			}
		}
		return g;
	}

	/**
	 * JPanel - универсальный контейнер.
	 * JButton - создание кнопки.
	 * ActionListener - механизм обратного вызова.
	 * JFrame(String title) - создание пустого окна приложения с заголовком.
	 * setSize(int width, int height) - определение размеров окна.
	 * setVisible(boolean visible) - делает окно видимым.
	 * setDefaultCloseOperation (WindowConstants.EXIT_ON_CLOSE) - используется для закрытия вашего JFrame.
	 */
	public static void main(String[] args) {

		JPanel myPanel = new JPanel();
		//final Main rgg = new Main(25, 0.5); // вершины; вероятность 
		JButton go = new JButton("Сгенерировать");
		go.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Main rgg = new Main(25, 0.5); // вершины; вероятность 
				Graph<Integer, String> g = rgg.generateRandomGraph();
				Layout<Integer, String> layout = new KKLayout<Integer, String>(g);
				layout.setSize(new Dimension(700, 500)); 
				BasicVisualizationServer<Integer, String> vv = new BasicVisualizationServer<Integer, String>(layout);
				vv.setPreferredSize(new Dimension(700, 500)); 
				JFrame frame = new JFrame("Генератор случайных графов");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.getContentPane().add(vv);
				frame.pack();
				frame.setVisible(true);

	}	});
		myPanel.add(go);
		
		JFrame myFrame = new JFrame("Генератор случайных графов");
		myFrame.setContentPane(myPanel);
		go.setPreferredSize(new Dimension(150,40));
		myFrame.setSize(400, 100);
		myFrame.setVisible(true);

}
}