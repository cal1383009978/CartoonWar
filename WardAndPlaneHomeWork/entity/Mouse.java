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
 *  @Descrption: 创建一个鼠类
 * @author Freelancer_Carl
 * @date 2019年8月21日 上午8:07:43
 *
 */
public class Mouse extends GameObj implements ActionAble {

	//速度
	private int speed;
	//方向布尔变量
	private boolean left,up,right,down;
	//客户端拿过来
	public GameClient gc;
	//判断是敌还是友
	public boolean isGood;
	
	//添加飞机子弹等级变量
	public int Bulletlevel = 1;
	
	//添加血值
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
	//移动的方法
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
	//画一个鼠出来
	@Override
	public void draw(Graphics g) {
		g.drawImage(img, x, y, null);
		move();
		g.drawString("当前血量:"+blood, 10, 200);
	}
	//处理鼠的边界问题
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
	
	//键盘摁下
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
	//键盘释放
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
	
	//我方飞鼠的开火
	public void fire() {
		Bullet b = new Bullet(x+this.img.getWidth(null), y+this.img.getHeight(null)/2-20, "bullet/u"+Bulletlevel+".png",gc,true);
		gc.bullets.add(b);
	}
	//获取到当前的矩形
	public Rectangle getRec() {
		return new Rectangle(x, y, this.img.getWidth(null), this.img.getHeight(null));
	}
	//检测我方角色碰撞到道具
	public void containItem(Prop prop) {
		if (this.getRec().intersects(prop.getRect())) {
			//移除道具
			gc.props.remove(prop);
			if (Bulletlevel>6) {
				Bulletlevel = 7;
			}
			//更改子弹等级
			else {
				this.Bulletlevel += 1;
			}
		}
	}
	//检测我方角色碰撞到多个道具
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
