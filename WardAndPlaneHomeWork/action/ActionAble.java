package com.neuedu.action;

import java.awt.Graphics;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

/**
 * @ClassName: ActionAble
 *  @Descrption: ��Ϊ�ӿ�
 * @author Freelancer_Carl
 * @date 2019��8��21�� ����7:59:19
 *
 */
public interface ActionAble {

	//�ƶ�����
	void move();
	//������
	void draw(Graphics g);
}
