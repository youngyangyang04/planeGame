package plane;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.ArrayList;
import java.util.Date;

import util.Constant;
import util.GameUtil;
import util.MYFrame;

public class PlaneGameFrame extends MYFrame{
	Image bg = GameUtil.getImage("images/bg.jpg");
	Plane p = new Plane("images/plane.png",50,50);
	
	ArrayList bulletList = new ArrayList();//������ʱûѧ����ʱ���ӡ��Ժ�ѧ�˼�
	
	
	
	Date startTime;
	Date endTime;
	Explode baozha = null;
	public void paint(Graphics g){
		g.drawImage(bg, 0, 0, null);
		p.draw(g);
		
		
		for(int i=0;i<bulletList.size();i++){
			Bullet b = (Bullet) bulletList.get(i);
			b.draw(g);
			//�����ɻ�����ײ
			boolean peng = b.getRect().intersects(p.getRect());
			if(peng){
				
				p.setLive(false);//�ɻ�����
				
				if(baozha==null){
					endTime = new Date();
					baozha = new Explode(p.x,p.y);
				}
				baozha.draw(g);
				
				break;
			}
		}
		if(!p.isLive()){
			printInfor(g,"GAME OVER", 50,30 ,Constant.GAME_HEIGHT/2,Color.white);	
			int period = (int)((endTime.getTime()-startTime.getTime())/1000);
			printInfor(g,"����ʱ�䣺" + period +"��", 20,30 ,Constant.GAME_HEIGHT/2+50,Color.white);
			
			switch (period/10) {
			case 0:
				printInfor(g,"���񼶱�", 20,30 ,Constant.GAME_HEIGHT/2+100,Color.white );
				break;
			case 1:
				printInfor(g,"����ˮƽ", 20,30 ,Constant.GAME_HEIGHT/2+100,Color.white );
				break;	
			case 2:
				printInfor(g,"�߼�ˮƽ", 20,30 ,Constant.GAME_HEIGHT/2+100,Color.white );
				break;
			default:
				break;
			}
		}	
		
	}
	/**
	 * ��ӡ��Ϣ
	 * @param g
	 * @param str
	 * @param size
	 * @param x
	 * @param y
	 * @param color
	 */
	public void printInfor(Graphics g,String str,int size,int x,int y,Color color){
		Color c = g.getColor();
		g.setColor(color);
		Font f = new Font("����",Font.BOLD,size);
		g.setFont(f);
		g.drawString(str, x, y);
		g.setColor(c);
		
	}
	
	private Image offScreenImage = null; //����˫���弼��������˸
	public void update(Graphics g){
		if(offScreenImage == null)
			offScreenImage = this.createImage(Constant.GAME_HEIGHT,Constant.GAME_WIDTH);
		
		Graphics gOff = offScreenImage.getGraphics();
		paint(gOff);
		g.drawImage(offScreenImage, 0, 0, null);
	}
	
	
	public static void main(String[] args) {
		new PlaneGameFrame().launchFrame();
	}
	
	public void launchFrame(){
		super.launchFrame();
		
		addKeyListener(new KeyMonitor());
		
		for(int i=0;i<30;i++){//�ӵ�����
			Bullet b = new Bullet();
			bulletList.add(b);
		}
		startTime = new Date();
	}
	
	class KeyMonitor extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			System.out.println("����"+e.getKeyCode());
			p.addDirection(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			System.out.println("�ͷ�"+e.getKeyCode());
			p.minusDirection(e);
		}
		
	}
	
	
}
