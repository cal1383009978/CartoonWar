package com.neuedu.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 * @ClassName: GetImageUtil
 *  @Descrption: ��ȡͼƬ�Ĺ�����
 * @author Freelancer_Carl
 * @date 2019��8��20�� ����6:33:19
 *
 */
public class GetImageUtil {

	//URL
	//��ȡͼƬ�ķ���
	public static Image getImg(String imgName) {
		//����
		URL resource = GetImageUtil.class.getClassLoader().getResource("com/neuedu/img/"+imgName);
		BufferedImage bufferedImage = null;
//		System.out.println(resource);
		try {
			bufferedImage = ImageIO.read(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bufferedImage;
	}
//	public static void main(String[] args) {
//		GetImageUtil.getImg("a1.png");
//	}
}
