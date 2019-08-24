package com.neuedu.client;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.constant.Constant;
import com.neuedu.entity.BackGround;
import com.neuedu.entity.Boom;
import com.neuedu.entity.Bullet;
import com.neuedu.entity.EnemyMouse;
import com.neuedu.entity.Mouse;
import com.neuedu.entity.Prop;
import com.neuedu.util.GetImageUtil;

/**
 * @ClassName: GameClient
 *  @Descrption: ��Ϸ�ͻ���
 * @author Freelancer_Carl
 * @date 2019��8��20�� ����6:18:56
 *
 */
public class GameClient extends Frame {
	
	//����һ��mouse����
	//Mouse mouse = new Mouse(100, 200,"plane/race.png",this,true);
	
	//����һ���ҷ���ɫ�ļ���
	public List<Mouse> mouses = new ArrayList<>();
	
	
	//�������߼���
	public List<Prop> props = new ArrayList<>();
	
	//����һ���ӵ�����
	public List<Bullet> bullets = new ArrayList<>();
	
	//����һ������ͼ
	BackGround backImg = new BackGround(0, 0, "bg01.png");
	
	//����һ����ը����
	public List<Boom> booms = new ArrayList<>();
	
	
	//�����з�����
	public List<Mouse> enemys = new ArrayList<>();

//	private static Image[] imgs = {
//			GetImageUtil.getImg("a1.png"),
//			GetImageUtil.getImg("a2.png"),
//			GetImageUtil.getImg("a3.png"),
//			GetImageUtil.getImg("a4.png"),
//			GetImageUtil.getImg("a5.png"),
//			GetImageUtil.getImg("a6.png"),
//			GetImageUtil.getImg("a7.png"),
//	};
	//���ͼƬ��˸����
	@Override
	public void update(Graphics g) {
		Image backImg = createImage(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		Graphics backg = backImg.getGraphics();
		Color color = backg.getColor();
		backg.setColor(Color.WHITE);
		backg.fillRect(0,0,Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
		backg.setColor(color);
		paint(backg);
		g.drawImage(backImg, 0, 0, null);
	}
	Mouse mouse = null;
	//���ɿͻ��˴��ڵķ���
	public void launchFrame() {
		//���ô��ڴ�С
		this.setSize(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
		//���ñ���
		this.setTitle("��ͨ����ս");
		//�����Ƿ��ܹ���ʾ
		this.setVisible(true);
		//��ֹ���
		this.setResizable(false);
		//���ھ���
		this.setLocationRelativeTo(null);
		Image img = GetImageUtil.getImg("sign.png");
		this.setIconImage(img);
		//�رմ��� ��ӹرռ�����
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		mouse = new Mouse(100, 200,"plane/race.png",this,true);
		//���ҷ�����������Լ�
		mouses.add(mouse);
		//��Ӽ��̼���
		this.addKeyListener(new KeyAdapter() {
			//�������µ������
			@Override
			public void keyPressed(KeyEvent e) {
				mouse.keyPressed(e);
			}
			//�ͷż���
			@Override
			public void keyReleased(KeyEvent e) {
				mouse.keyReleased(e);
			}
		});
	
		//�����߳�
		new paintThread().start();
		
		
		//���з���������ӵ���
		EnemyMouse enemy1 = new EnemyMouse(650, 50,1,this,false);
		EnemyMouse enemy2 = new EnemyMouse(650, 400,1,this,false);
		EnemyMouse enemy3 = new EnemyMouse(950, 60,1,this,false);
		EnemyMouse enemy4 = new EnemyMouse(950, 410,1,this,false);
		EnemyMouse enemy5 = new EnemyMouse(1250, 70,1,this,false);
		EnemyMouse enemy6 = new EnemyMouse(1250, 420,1,this,false);
		enemys.add(enemy1);
		enemys.add(enemy2);
		enemys.add(enemy3);
		enemys.add(enemy4);
		enemys.add(enemy5);
		enemys.add(enemy6);
		
		
		
		//���������
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("������һ�����");
			}
		});
	}
	//��дpaint����
	@Override
	public void paint(Graphics g) {
		backImg.draw(g);
		for (int i = 0; i < mouses.size(); i++) {
			Mouse mouse2 = mouses.get(i);
			mouse2.draw(g);
			mouse2.containItems(props);
			
		}
		//��ǿѭ������ÿ���ӵ�
		//��ǿforѭ���в������κβ���
//		for(Bullet bullet:bullets) {
//			bullet.draw(g);
//		}
		for (int i = 0; i < bullets.size(); i++) {
			Bullet bullet = bullets.get(i);
			bullet.draw(g);
			bullet.hitMonsters(enemys);
			bullet.hitMonsters(mouses);
		}
		g.drawString("��ǰ�ӵ�������:"+bullets.size(), 10, 40);
		g.drawString("��ǰ�л�������:"+enemys.size(), 10, 70);
		g.drawString("��ǰ��ը������:"+booms.size(), 10, 100);
		g.drawString("��ǰ�ҷ�������:"+mouses.size(), 10, 130);
		g.drawString("��ǰ���ߵ�����:"+props.size(), 10, 160);
		//ѭ�����з�
		for (int i = 0; i < enemys.size(); i++) {
			enemys.get(i).draw(g);
		}
		//ѭ����ը
		for (int i = 0; i < booms.size(); i++) {
			if (booms.get(i).isLive()==true) {
				booms.get(i).draw(g);
			}
		}
		
		//ѭ��������
		for (int i = 0; i < props.size(); i++) {
				props.get(i).draw(g);
		}
	}
	
	//�ڲ���
	class paintThread extends Thread{
		@Override
		public void run() {
			while(true) {
				repaint();
				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	
}
