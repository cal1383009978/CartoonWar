package com.neuedu.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import com.neuedu.action.ActionAble;
import com.neuedu.client.GameClient;
import com.neuedu.util.GetImageUtil;

/**
 * @ClassName: EnemyMouse
 *  @Descrption: 敌鼠类
 * @author Freelancer_Carl
 * @date 2019年8月21日 下午2:55:55
 *
 */
public class EnemyMouse extends Mouse implements ActionAble{

	private Integer enemyType;
	
	private Integer speed;
	
	private GameClient gc;
	
	public static Image[] imgs1 = {
			GetImageUtil.getImg("boss/BOSS_01.png"),
			GetImageUtil.getImg("boss/BOSS_02.png"),
			GetImageUtil.getImg("boss/BOSS_03.png"),
			GetImageUtil.getImg("boss/BOSS_04.png"),
			GetImageUtil.getImg("boss/BOSS_05.png"),
	};
	
	public EnemyMouse() {
		
	}
	public EnemyMouse(int x,int y,int enemyType,GameClient gc,boolean isGood) {
			this.x = x;
			this.y = y; 
			this.enemyType = enemyType;
			this.speed =0;
			this.gc = gc;
			this.isGood = isGood;
		}
	@Override
		public void move() {
			x -= speed;
		}
	int count = 5;
	@Override
	public void draw(Graphics g) {
		if (count>4) {
			count = 0;
		}
		g.drawImage(imgs1[count++], x, y, null);
		move();
		if (random.nextInt(500)>490) {
			fire();
		}
	}
		
	//随机数
	Random random = new Random();
	//敌军发火
	public void fire() {
		Bullet bullet = new Bullet(x,y+imgs1[0].getHeight(null)/2+20,"bullet/bullets1.png", gc,false);
		gc.bullets.add(bullet);
	}
	//获取到子弹的矩形
		public Rectangle getRec() {
			return new Rectangle(x, y,this.imgs1[0].getWidth(null),this.imgs1[0].getHeight(null));
		}
}
