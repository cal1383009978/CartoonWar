package com.neuedu.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.List;

import com.neuedu.action.ActionAble;
import com.neuedu.client.GameClient;
import com.neuedu.constant.Constant;
import com.neuedu.util.GetImageUtil;

/**
 * @ClassName: Mouse
 *  @Descrption: ����һ������
 * @author Freelancer_Carl
 * @date 2019��8��21�� ����8:07:43
 *
 */
public class Mouse extends GameObj implements ActionAble {

	//�ٶ�
	private int speed;
	//���򲼶�����
	private boolean left,up,right,down;
	//�ͻ����ù���
	public GameClient gc;
	//�ж��ǵл�����
	public boolean isGood;
	
	//��ӷɻ��ӵ��ȼ�����
	public int Bulletlevel = 1;
	
	//���Ѫֵ
	public int blood;
	
	public Mouse() {
		
	}
	public Mouse(int x, int y,String imgName,GameClient gc,boolean isGood) {
			this.x = x;
			this.y = y;
			this.img = GetImageUtil.getImg(imgName);
			this.speed = 20;
			this.gc = gc;
			this.isGood = isGood;
			this.blood = 100;
		}
	//�ƶ��ķ���
	@Override
	public void move() {
		if (left) {  //left==true
			x -= speed;
		}
		if (up) {
			y -= speed;
		}
		if (right) {
			x += speed;
		}
		if (down) {
			y += speed;
		}
		outOfBound();
	}
	//��һ�������
	@Override
	public void draw(Graphics g) {
		g.drawImage(img, x, y, null);
		move();
		g.drawString("��ǰѪ��:"+blood, 10, 200);
	}
	//������ı߽�����
	public void outOfBound() {
		if (y<=30) {
			y = 20;
		}
		if (x<=5) {
			x = 0;
		}
		if (x>=Constant.GAME_WIDTH-img.getWidth(null)) {
			x = Constant.GAME_WIDTH-img.getWidth(null);
		}
		if (y>=Constant.GAME_HEIGHT-img.getHeight(null)) {
			y = Constant.GAME_HEIGHT-img.getHeight(null);
		}
	}
	
	//��������
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			left = true;
			break;
		case KeyEvent.VK_W:
			up = true;
			break;
		case KeyEvent.VK_D:
			right = true;
			break;
		case KeyEvent.VK_S:
			down = true;
			break;
		

		default:
			break;
		}
	}
	//�����ͷ�
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			left = false;
			break;
		case KeyEvent.VK_W:
			up = false;
			break;
		case KeyEvent.VK_D:
			right = false;
			break;
		case KeyEvent.VK_S:
			down = false;
			break;
		case KeyEvent.VK_M:
			fire();
			break;
		default:
			break;
		}
	}
	
	//�ҷ�����Ŀ���
	public void fire() {
		Bullet b = new Bullet(x+this.img.getWidth(null), y+this.img.getHeight(null)/2-20, "bullet/u"+Bulletlevel+".png",gc,true);
		gc.bullets.add(b);
	}
	//��ȡ����ǰ�ľ���
	public Rectangle getRec() {
		return new Rectangle(x, y, this.img.getWidth(null), this.img.getHeight(null));
	}
	//����ҷ���ɫ��ײ������
	public void containItem(Prop prop) {
		if (this.getRec().intersects(prop.getRect())) {
			//�Ƴ�����
			gc.props.remove(prop);
			if (Bulletlevel>6) {
				Bulletlevel = 7;
			}
			//�����ӵ��ȼ�
			else {
				this.Bulletlevel += 1;
			}
		}
	}
	//����ҷ���ɫ��ײ���������
	public void containItems(List<Prop> props) {
		if (props==null) {
			return;
		}else {
			for (int i = 0; i < props.size(); i++) {
				containItem(props.get(i));
					
				
			}
		}
	}
}
