/**
 *
 * @author matteo
 */
public class nk_TicTacToe {

	char[][] gameBoard;
	int board_size;
	int inline;
	int max_levels;
	

	public nk_TicTacToe(int board_size, int inline, int max_levels) {
		
		this.board_size = board_size;
		this.inline = inline;
		this.max_levels = max_levels;
		this.gameBoard = new char[board_size][board_size];
		
		for (int i = 0; i < board_size; i++) {	
			for (int j = 0; j < board_size; j++) {
				gameBoard[i][j] = ' ';
			}			
		}		
	}
	
	public Dictionary createDictionary() {
		Dictionary dictionary = new Dictionary(7499);
		return dictionary;	
	}
	
	public int repeatedConfig(Dictionary configurations) {
		
                String config = "";

		for (int i = 0; i < board_size; i++) {	
			for (int j = 0; j < board_size; j++) {	
				config = config + gameBoard[i][j];
			}		
		}
		
		return configurations.get(config);
		
	}

	public void insertConfig(Dictionary configurations, int score) {
		
		String config = "";
		
		for (int i = 0; i < board_size; i++) {
			for (int j = 0; j < board_size; j++) {
				config = config + gameBoard[i][j];	
			}	
		}
		Record temp = new Record(config, score);
		configurations.insert(temp);
		
	}
 
	public void storePlay(int r, int c, char symbol) {
		gameBoard[r][c] = symbol;
	}

	public boolean squareIsEmpty(int r, int c) {
            Character space = ' ';
            Character currentPlay = gameBoard[r][c];
            return currentPlay.equals(space);
        }
        
	public boolean wins(char symbol) {
		
		int count = 0;
		int row = 0;
		int col = 0;
                int k;

		for (int i = 0; i < board_size; i++) {	
			for (int j = 0; j < board_size; j++) {
				if (i != row) {
					count = 0;
					row++;
				} 
				if (symbol == gameBoard[i][j]) {
					count++;
				} else{
					count = 0;
				}
				if (count == inline) {
					return true;	
				}
			}
		}
                count = 0;		
		for (int j = 0; j < board_size; j++) {
			for (int i = 0; i < board_size; i++) {
				if (j != col) {
					count = 0;
					col++;
				}
				if (symbol == gameBoard[i][j]) {
					count++;
				} else {
					count = 0;
				}
				if (count == inline) {
					return true;
				}
			}
		}
		for (int diag = 0; diag < board_size * 2 - 1; ++diag) {
			count = 0;
			if (diag < board_size) {
				k = 0;
			} else {
				k = diag - board_size + 1;
			}
			for (int i = k; i <= diag - k; ++i) {
				int val = (board_size - 1) - (diag - i);
				if (symbol == gameBoard[i][val]) {
					count = count + 1;
				} else {
					count = 0;
				}
				if (count == inline) {
					return true;
				}
			}
		}

		for (int diag = 0; diag < board_size * 2 - 1; ++diag) {
			count = 0;
			if (diag < board_size) {
				k = 0;
			} else {
				k = diag - board_size + 1;
			}
			for (int i = k; i <= diag - k; ++i) {
				int val = diag - i;
				if (symbol == gameBoard[i][val]) {
					count = count + 1;
				} else {
					count = 0;
				}
				if (count == inline) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean isDraw() {
		
		boolean win1 = wins('X');
		boolean win2 = wins('O');
		boolean full = true;
		
		for (int i = 0; i < board_size; i++) {
			for (int j = 0; j < board_size; j++) {
				if (gameBoard[i][j] == ' ') {
					full = false;
				}	
			}
		}
		
            return win1 == false && win2 == false && full;
		
	}
	
	public int evalBoard() {
		
		//computer wins
		if (wins('O')) {
			return 3;		
		//human wins
		} else if (wins('X')) {
			return 0;
		//draw
		} else if (isDraw()) {
			return 2;			
		//undecided
		} else {
			return 1;		
		}
		
	}
	
}
