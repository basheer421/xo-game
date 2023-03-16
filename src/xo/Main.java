package xo;

import java.util.Scanner;

class Main {

    public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	Board board = new Board();
	board.draw();
	while (!board.isOver()) {
	    System.out.println("\nChoose a position:");
	    int pos = sc.nextInt();
	    board.update(pos);
	    board.draw();
	}
	System.out.println("Game Over!");
    }
}

class Board {

    private boolean isOver;
    private String[] vals;
    private boolean xTurn;
    private int roundCount;

    Board() {
	roundCount = 0;
	xTurn = true;
	isOver = false;
	vals = new String[9];
	for (int i = 0; i < 9; i++) {
	    vals[i] = "" + (i + 1);
	}
    }

    public void draw() {
	System.out.printf("%s|%s|%s\n", vals[0], vals[1], vals[2]);
	System.out.printf("-|-|-\n");
	System.out.printf("%s|%s|%s\n", vals[3], vals[4], vals[5]);
	System.out.printf("-|-|-\n");
	System.out.printf("%s|%s|%s\n", vals[6], vals[7], vals[8]);
    }

    public void update(int position) {
	if (position > 9 || position < 1
		|| vals[position - 1].compareTo("X") == 0
		|| vals[position - 1].compareTo("O") == 0) {
	    System.out.println("Please choose a valid position!");
	    return;
	}
	if (xTurn) {
	    vals[position - 1] = "X";
	} else {
	    vals[position - 1] = "O";
	}
	xTurn = !xTurn;
	roundCount++;
    }

    private boolean sameThree(int pos1, int pos2, int pos3) {
	if ((vals[pos1].charAt(0) == vals[pos2].charAt(0))
		&& vals[pos1].charAt(0) == vals[pos3].charAt(0)) {
	    return true;
	}
	return false;
    }

    public boolean isOver() {
	if (roundCount == 9) {
	    isOver = true;
	}
	for (int i = 0; i < 9 && !isOver; i++) {
	    if (!isOver && i % 3 == 0) {
		isOver = sameThree(i, i + 1, i + 2);
	    }
	    if (!isOver && i < 3) {
		isOver = sameThree(i, i + 3, i + 6);
	    }
	    if (!isOver && i == 0) {
		isOver = sameThree(i, i + 4, i + 8);
	    }
	    if (!isOver && i == 2) {
		isOver = sameThree(i, i + 2, i + 4);
	    }
	}
	return (isOver);
    }
}
