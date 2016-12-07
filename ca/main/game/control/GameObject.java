package ca.main.game.control;

public  class GameObject {

	private int cor1PosX;
	private int cor1PosY;
	private int cor2PosX;
	private int cor2PosY;
	
	private int centerPosX;
	private int centerPosY;
	
	private int width;
	private int height;
	
	public GameObject(int centerPosX, int centerPosY, int width, int height){
		this.centerPosX = centerPosX;
		this.centerPosY = centerPosY;
		this.width = width;
		this.height = height;
		
		this.cor1PosX = centerPosX - width/2;
		this.cor2PosX = centerPosX + width/2;
		this.cor1PosY = centerPosY - height/2;
		this.cor2PosY = centerPosY + height/2;
		
		System.out.println("center "+ centerPosX+":"+centerPosY);
		System.out.println("obj cor1" + cor1PosX + ":" + cor1PosY);
		System.out.println("obj cor2" + cor2PosX + ":" + cor2PosY);
	}
	
	public boolean isInside(int playerCenterX, int playerCenterY, int playerWidth, int playerHeight){
		int playerCor1PosX = playerCenterX - playerWidth/2;
		int playerCor1PosY = playerCenterY - playerHeight/2;
		int playerCor2PosX = playerCenterX + playerWidth/2;
		int playerCor2PosY = playerCenterY + playerHeight/2;
		
		if((cor1PosX < playerCor1PosX) && (playerCor1PosX < cor2PosX)){
			if ((cor1PosY < playerCor1PosY) && (playerCor1PosY < cor2PosY)){
				return true;
			}
		}
		
		return false;
	}
	
	public int getCor1PosX(){
		return cor1PosX;
	}
	
	public int getCor2PosX(){
		return cor2PosX;
	}
	
	public int getCor1PosY(){
		return cor1PosY;
	}
	
	public int getCor2PosY(){
		return cor2PosY;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
}
