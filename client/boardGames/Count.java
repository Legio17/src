package client.boardGames;

public class Count {

	public static <T> boolean isThereThree(T[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				// System.out.println(i+","+j);
				if (j == 0)
					if ((arr[i][j].equals("X") && arr[i][j + 1].equals("X") && arr[i][j + 2]
							.equals("X"))) {

						return true;
					}
				if (i == 0)
					if (arr[i][j].equals("X") && arr[i + 1][j].equals("X")
							&& arr[i + 2][j].equals("X")) {
						return true;
					}
				if (i == 0 && j == 0) {
					if (arr[i][j].equals("X") && arr[i + 1][j + 1].equals("X")
							&& arr[i + 2][j + 2].equals("X")) {
						return true;
					}
					if (arr[i + 2][j].equals("X")
							&& arr[i + 1][j + 1].equals("X")
							&& arr[i][j + 2].equals("X")) {
						return true;
					}
				}

				if (j == 0)
					if (arr[i][j].equals("O") && arr[i][j + 1].equals("O")
							&& arr[i][j + 2].equals("O")) {
						return true;
					}
				if (i == 0)
					if (arr[i][j].equals("O") && arr[i + 1][j].equals("O")
							&& arr[i + 2][j].equals("O")) {
						return true;
					}
				if (i == 0 && j == 0) {
					if (arr[i][j].equals("O") && arr[i + 1][j + 1].equals("O")
							&& arr[i + 2][j + 2].equals("O")) {
						return true;
					} else if (arr[i + 2][j].equals("O")
							&& arr[i + 1][j + 1].equals("O")
							&& arr[i][j + 2].equals("O")) {
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
		System.out.println("IS THERE FIVE METHOD");
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				try {
					if ((arr[i][j] == null || arr[i][j + 1] == null
							|| arr[i][j + 2] == null || arr[i][j + 3] == null || arr[i][j + 4] == null)) {

					} else {
						if ((arr[i][j].equals("X") && arr[i][j + 1].equals("X")
								&& arr[i][j + 2].equals("X")
								&& arr[i][j + 3].equals("X") && arr[i][j + 4]
								.equals("X"))) {

							return true;
						}
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					 
				}
				try {
					if (arr[i][j] == (null) || arr[i + 1][j] == (null)
							|| arr[i + 2][j] == (null)
							|| arr[i + 3][j] == (null)
							|| arr[i + 4][j] == (null)) {
					} else {
						if (arr[i][j].equals("X") && arr[i + 1][j].equals("X")
								&& arr[i + 2][j].equals("X")
								&& arr[i + 3][j].equals("X")
								&& arr[i + 4][j].equals("X")) {
							return true;
						}
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					 
				}
				try {
					if (arr[i][j] == (null) || arr[i + 1][j + 1] == (null)
							|| arr[i + 2][j + 2] == (null)
							|| arr[i + 3][j + 3] == (null)
							|| arr[i + 4][j + 4] == (null)) {

					} else {
						if (arr[i][j].equals("X")
								&& arr[i + 1][j + 1].equals("X")
								&& arr[i + 2][j + 2].equals("X")
								&& arr[i + 3][j + 3].equals("X")
								&& arr[i + 4][j + 4].equals("X")) {
							return true;
						}
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					 
				}
				try {
					if (arr[i][j] == (null) || arr[i - 1][j + 1] == (null)
							|| arr[i - 2][j + 2] == (null)
							|| arr[i - 3][j + 3] == (null)
							|| arr[i - 4][j + 4] == (null)) {

					} else {
						if (arr[i][j].equals("X")
								&& arr[i - 1][j + 1].equals("X")
								&& arr[i - 2][j + 2].equals("X")
								&& arr[i - 3][j + 3].equals("X")
								&& arr[i - 4][j + 4].equals("X")) {
							return true;
						}
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					 
				}

				try {
					if ((arr[i][j] == null || arr[i][j + 1] == null
							|| arr[i][j + 2] == null || arr[i][j + 3] == null || arr[i][j + 4] == null)) {

					} else {
						if ((arr[i][j].equals("O") && arr[i][j + 1].equals("O")
								&& arr[i][j + 2].equals("O")
								&& arr[i][j + 3].equals("O") && arr[i][j + 4]
								.equals("O"))) {
							return true;
						}
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					 
				}
				try {
					if (arr[i][j] == (null) || arr[i + 1][j] == (null)
							|| arr[i + 2][j] == (null)
							|| arr[i + 3][j] == (null)
							|| arr[i + 4][j] == (null)) {
					} else {
						if (arr[i][j].equals("O") && arr[i + 1][j].equals("O")
								&& arr[i + 2][j].equals("O")
								&& arr[i + 3][j].equals("O")
								&& arr[i + 4][j].equals("O")) {
							return true;
						}
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					 
				}
				try {
					if (arr[i][j] == (null) || arr[i + 1][j + 1] == (null)
							|| arr[i + 2][j + 2] == (null)
							|| arr[i + 3][j + 3] == (null)
							|| arr[i + 4][j + 4] == (null)) {

					} else {
						if (arr[i][j].equals("O")
								&& arr[i + 1][j + 1].equals("O")
								&& arr[i + 2][j + 2].equals("O")
								&& arr[i + 3][j + 3].equals("O")
								&& arr[i + 4][j + 4].equals("O")) {
							return true;
						}
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					 
				}
				try {
					if (arr[i][j] == (null) || arr[i - 1][j + 1] == (null)
							|| arr[i - 2][j + 2] == (null)
							|| arr[i - 3][j + 3] == (null)
							|| arr[i - 4][j + 4] == (null)) {

					} else {
						if (arr[i][j].equals("O")
								&& arr[i - 1][j + 1].equals("O")
								&& arr[i - 2][j + 2].equals("O")
								&& arr[i - 3][j + 3].equals("O")
								&& arr[i - 4][j + 4].equals("O")) {
							return true;
						}
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					 
				}
			}
		}
		return false;
	}
}
