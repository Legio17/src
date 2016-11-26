package ca.main.game.logics;

public class Count {

	public static <T> boolean isThereThree(T[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				// System.out.println(i+","+j);
				if (j == 0)
					if ((arr[i][j] == "X" && arr[i][j + 1] == "X" && arr[i][j + 2] == "X")) {

						return true;
					}
				if (i == 0)
					if (arr[i][j] == ("X") && arr[i + 1][j] == ("X")
							&& arr[i + 2][j] == ("X")) {
						return true;
					}
				if (i == 0 && j == 0) {
					if (arr[i][j] == ("X") && arr[i + 1][j + 1] == ("X")
							&& arr[i + 2][j + 2] == ("X")) {
						return true;
					}
					if (arr[i + 2][j] == ("X") && arr[i + 1][j + 1] == ("X")
							&& arr[i][j + 2] == ("X")) {
						return true;
					}
				}

				if (j == 0)
					if (arr[i][j] == ("O") && arr[i][j + 1] == ("O")
							&& arr[i][j + 2] == ("O")) {
						return true;
					}
				if (i == 0)
					if (arr[i][j] == ("O") && arr[i + 1][j] == ("O")
							&& arr[i + 2][j] == ("O")) {
						return true;
					}
				if (i == 0 && j == 0) {
					if (arr[i][j] == ("O") && arr[i + 1][j + 1] == ("O")
							&& arr[i + 2][j + 2] == ("O")) {
						return true;
					} else if (arr[i + 2][j] == ("O")
							&& arr[i + 1][j + 1] == ("O")
							&& arr[i][j + 2] == ("O")) {
						return true;
					}
				}
				// else
				// return false;
			}
		}
		return false;
	}

	public static <T> boolean isThereNull(T[][] arr) {
		for (int i = 0; i < arr.length; i++)
			for (int j = 0; j < arr[0].length; j++)
				if (arr[i][j] == null) {
					return true;
				}
		return false;
	}

	public static <T> void setToNull(T[][] arr) {
		for (int i = 0; i < arr.length; i++)
			for (int j = 0; j < arr[0].length; j++)
				arr[i][j] = null;
	}

	public static <T> boolean isThereFive(int howManyToWin, T[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {

				try {
					if ((arr[i][j] == "X" && arr[i][j + 1] == "X"
							&& arr[i][j + 2] == "X" && arr[i][j + 3] == "X" && arr[i][j + 4] == "X")) {
						return true;
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("No problem!");
				}
				try {
					if (arr[i][j] == ("X") && arr[i + 1][j] == ("X")
							&& arr[i + 2][j] == ("X") && arr[i + 3][j] == ("X")
							&& arr[i + 4][j] == ("X")) {
						return true;
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("No problem!");
				}
				try {
					if (arr[i][j] == ("X") && arr[i + 1][j + 1] == ("X")
							&& arr[i + 2][j + 2] == ("X")
							&& arr[i + 3][j + 3] == ("X")
							&& arr[i + 4][j + 4] == ("X")) {
						return true;
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("No problem!");
				}
				try {
					if (arr[i][j] == ("X") && arr[i - 1][j + 1] == ("X")
							&& arr[i - 2][j + 2] == ("X")
							&& arr[i - 3][j + 3] == ("X")
							&& arr[i - 4][j + 4] == ("X")) {
						return true;
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("No problem!");
				}

				try {
					if ((arr[i][j] == "O" && arr[i][j + 1] == "O"
							&& arr[i][j + 2] == "O" && arr[i][j + 3] == "O" && arr[i][j + 4] == "O")) {
						return true;
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("No problem!");
				}
				try {
					if (arr[i][j] == ("O") && arr[i + 1][j] == ("O")
							&& arr[i + 2][j] == ("O") && arr[i + 3][j] == ("O")
							&& arr[i + 4][j] == ("O")) {
						return true;
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("No problem!");
				}
				try {
					if (arr[i][j] == ("O") && arr[i + 1][j + 1] == ("O")
							&& arr[i + 2][j + 2] == ("O")
							&& arr[i + 3][j + 3] == ("O")
							&& arr[i + 4][j + 4] == ("O")) {
						return true;
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("No problem!");
				}
				try {
					if (arr[i][j] == ("O") && arr[i - 1][j + 1] == ("O")
							&& arr[i - 2][j + 2] == ("O")
							&& arr[i - 3][j + 3] == ("O")
							&& arr[i - 4][j + 4] == ("O")) {
						return true;
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("No problem!");
				}
			}
			// else
			// return false;
		}

		return false;
	}
}
