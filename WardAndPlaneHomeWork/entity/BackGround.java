package com.neuedu.entity;

import java.awt.Graphics;

import com.neuedu.action.ActionAble;
import com.neuedu.util.GetImageUtil;

/**
 * @ClassName: BackGround
 *  @Descrption: 背景类
 * @author Freelancer_Carl
 * @date 2019年8月21日 下午2:12:29
 *
 */
public class BackGround extends GameObj implements ActionAble{

	private Integer speed;

	public BackGround() {
		
	}
	public BackGround(int x,int y,String imgName) {
			this.x = x;
			this.y = y;
			this.img = GetImageUtil.getImg(imgName);
			this.speed = 4;
		}
	@Override
	public void move() {
	x -= speed;
		
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(img, x, y,null);
		move();
	}

}
