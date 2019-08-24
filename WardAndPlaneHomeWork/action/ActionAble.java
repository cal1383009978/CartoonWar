package com.neuedu.action;

import java.awt.Graphics;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

/**
 * @ClassName: ActionAble
 *  @Descrption: 行为接口
 * @author Freelancer_Carl
 * @date 2019年8月21日 上午7:59:19
 *
 */
public interface ActionAble {

	//移动方法
	void move();
	//画方法
	void draw(Graphics g);
}
