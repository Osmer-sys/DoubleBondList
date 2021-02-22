import java.util.Scanner;

public class TestDoubleBondList {
	static class Nodo {
		int data;
		Nodo prev, next; // prev: previous
	}

	public static Nodo root;
	public static Scanner obj;
	static String opcion = "";

	public static void selection() {
		System.out.println(
				"\nPor favor selecione una opción: \n(i)para insertar en la lista.\n(e)para eliminar de la lista.\n(m)para eliminar el mayor.");
		opcion = obj.next();
		if (opcion.equals("i") == true) {
			insert();
		} else {
			if (opcion.equals("e") == true) {
				delete();
			} else {
				if (opcion.equals("m") == true) {
					delHigherDouble();
				} else {
					System.out.println("Opción invalida, por favor ingrese una de las siguientes opciones:\n");
					selection();
				}
			}
		}
	}

	private static void listDoubleBound(int pos, int x) {
		if (pos <= amount() + 1) {
			Nodo newNode = new Nodo();
			newNode.data = x;
			if (pos == 1) {
				newNode.next = root;
				if (root != null) {
					root.prev = newNode;
				}
				root = newNode;
			} else {
				if (pos == amount() + 1) {
					Nodo move = root;
					while (move.next != null)
						move = move.next;
					move.next = newNode;
					newNode.prev = move;
					newNode.next = null;
				} else {
					Nodo move = root;
					for (int f = 1; f <= pos - 2; f++)
						move = move.next;
					Nodo nextNode = move.next;
					move.next = newNode;
					newNode.prev = move;
					newNode.next = nextNode;
					nextNode.prev = newNode;
				}
			}
		}
	}

	public static void insert() {
		int optionInsert;
		int numInsert, dataInsert;
		System.out.println("Por favor indique la posición donde desea insertar: ");
		numInsert = obj.nextInt();
		if (numInsert <= amount() + 1) {
			System.out.println("Ingrese el dato a insertar: ");
			dataInsert = obj.nextInt();
			listDoubleBound(numInsert, dataInsert);
		} else {
			System.out.println("La posición indicada se encuentra por fuera de rango");
			insert();
		}
		System.out.println("Se imprime la lista con la modificación realizada:\n");
		printDoubleBond();
		System.out.println("Para insertar otro dato preseione 1, para terminar 0:\n");
		optionInsert = obj.nextInt();
		if (optionInsert == 1) {
			insert();
		} else {
			if (optionInsert == 0) {
				selection();
			} else {
				if (optionInsert != 1 && optionInsert != 0) {
					System.out.println("La opción seleccionada no es una opción. Por favor ingrese 1 ó 0.");
				}
			}
		}

	}

	public static void delete() {
		int posDelete;
		int optionDelete;
		System.out.println("Por favor ingrese la posición a eliminar: ");
		posDelete = obj.nextInt();
		if (posDelete <= amount()) {
			if (posDelete == 1) {
				root = root.next;
				if (root != null)
					root.prev = null;
			} else {
				Nodo move;
				move = root;
				for (int f = 1; f <= posDelete - 2; f++)
					move = move.next;
				Nodo prox = move.next;
				prox = prox.next;
				move.next = prox;
				if (prox != null)
					prox.prev = move;
			}
		} else {
			System.out.println("La posición indicada se encuentra por fuera de rango");
			delete();
		}
		System.out.println("Se imprime la lista con la modificación realizada:\n");
		printDoubleBond();
		System.out.println("Para eliminar otro dato preseione 1, para terminar 0:\n");
		optionDelete = obj.nextInt();
		if (optionDelete == 1) {
			delete();
		} else {
			if (optionDelete == 0) {
				selection();
			} else {
				if (optionDelete != 1 && optionDelete != 0) {
					System.out.println("La opción seleccionada no es una opción. Por favor ingrese 1 ó 0.");
				}
			}
		}
	}

	private static void delHigherDouble() {
		System.out.println("\nSe eliminará el número mayor de la lista.");
		if (root != null) {
			Nodo reco = root;
			int may = root.data;
			while (reco != null) {
				if (reco.data > may) {
					may = reco.data;
				}
				reco = reco.next;
			}
			reco = root;
			Nodo atras = root;
			while (reco != null) {
				if (reco.data == may) {
					if (reco == root) {
						root = root.next;
						atras = root;
						reco = root;
					} else {
						atras.next = reco.next;
						reco = reco.next;
					}
				} else {
					atras = reco;
					reco = reco.next;
				}
			}
		}
		printDoubleBond();
	}

	private static void printDoubleBond() {
		Nodo move = root;
		while (move != null) {
			System.out.print(move.data + "-");
			move = move.next;
		}
		System.out.println();
	}

	public static boolean isListEmptyDouble() {
		return root == null;
	}

	public static int amount() {
		int amountNode = 0;
		Nodo move = root;
		while (move != null) {
			move = move.next;
			amountNode++;
		}
		return amountNode;
	}

	public static void beginDouble() {
		System.out.println("\n===BIENVENIDO A LISTAS GENERICAS DOBLEMENTE ENCADENADAS===\n");
		obj = new Scanner(System.in);
		if (isListEmptyDouble() == true) {
			System.out.println("\nIngrese la cantidad de nodos a tener \nen lista aleatoria doblemente encadena: ");
			int nNumb = obj.nextInt();
			System.out.println("\nLa lista aleatoria es: \n");
			if (nNumb > 0) {
				int cont = 1;
				for (int f = 1; f <= nNumb; f++) {
					int poz = cont++;
					int n = (int) Math.floor(Math.random() * (10 - 1 + 1) + 1);
					listDoubleBound(poz, n);
				}
			} else {
				System.out.println("Por favor ingrese un número mayor a cero");
				beginDouble();
			}
		}
	}

	public static void main(String[] args) {
		beginDouble();
		printDoubleBond();
		selection();
		delHigherDouble();
	}
}
