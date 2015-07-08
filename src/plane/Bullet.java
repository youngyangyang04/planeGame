package plane;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import util.Constant;

public class Bullet extends GameObject{
	
	double degree;
	int BulletSpeed = 3;
	
	

	public Bullet(){
		degree = Math.random()*Math.PI*2;
		x = Constant.GAME_HEIGHT/2;
		y = Constant.GAME_WIDTH/2;
		width = 10;
		height = 10;
	}
	
	public void draw(Graphics g){
		Color c = g.getColor();
		g.setColor(Color.yellow);
		g.fillOval((int)x, (int)y, width, height);
		x += BulletSpeed*Math.cos(degree);
		y += BulletSpeed*Math.sin(degree);
		
		if( y > Constant.GAME_HEIGHT - height || y<height){
			degree = -degree;
		}
		if( x < 0 || x> Constant.GAME_WIDTH- width){
			degree = Math.PI- degree;
		}
		
	}
}
