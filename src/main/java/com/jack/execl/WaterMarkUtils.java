package com.jack.execl;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

/**
 * @description: pic
 * @author: Jack
 * @create: 2020-02-29 20:37
 */
public class WaterMarkUtils {
    /**
     * @param srcImgPath 源图片路径
     * @param tarImgPath 保存的图片路径
     * @param waterMarkContent 水印内容
     * @param markContentColor 水印颜色
     * @param font 水印字体
     */
    public void addWaterMark(String srcImgPath, String tarImgPath, String ...content) {

        try {
            // 读取原图片信息
            File srcImgFile = new File(srcImgPath);//得到文件
            Image srcImg = ImageIO.read(srcImgFile);//文件转化为图片
            int srcImgWidth = srcImg.getWidth(null);//获取图片的宽
            int srcImgHeight = srcImg.getHeight(null);//获取图片的高
            // 加水印
            BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufImg.createGraphics();
            g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);




            System.err.println(""+srcImgWidth+" h:"+srcImgHeight);




            //设置水印的坐标
          //  int x = srcImgWidth - 2*getWatermarkLength(waterMarkContent, g);
            //int y = srcImgHeight - 2*getWatermarkLength(waterMarkContent, g);
//            System.err.println();
//            x = 266 *2;
//            y = 255 *2;


            Font font1 = new Font("微软雅黑", Font.BOLD, 90);


            //水印字体

            FontMetrics fm = g.getFontMetrics(font1); //类对象,可以获得某个字体的高度,以及字符串的宽度
            int height = fm.getHeight();
            int w = 90;//fm.getWidths()[0];
            int h = 150;


            Color colorName=new Color(0,0,255,255);                               //水印图片色彩以及透明度
            Color color=new Color(255,253,84,255);

g.setFont(font1);
g.setColor(color);
int startX = 280;
int startY = 410;
int numWidth=60;
            g.drawString("恭喜", startX, startY);  //画出水印

            g.setColor(colorName);
            g.drawString(content[0],startX+w*2,startY);

            g.setColor(color);
            g.drawString("精英",startX+w*2+content[0].length()*w,startY);

//            成功收回续期保费应收保费元,掌声祝贺Ta
            String str20 = "成功收回续期保费";
            g.drawString(str20,startX,startY+h);

            g.setColor(colorName);
            int x20 = str20.length()*w+startX;

            g.drawString(content[1],x20,startY+h);
            g.setColor(color);



            int x201 = content[1].length() * numWidth + str20.length() * w + startX;
            if(content[1].contains(".")) x201 -= numWidth/2;
            if(content[1].contains(",")) x201 -= numWidth/2;
            g.drawString("元,掌声祝贺Ta", x201,startY+h);

//            g.drawString("为客户延续保障，给自己增加收入",startX,startY+h*2);
            MyDrawString("为客户延续保障,给自己增加收入",startX,startY+h*2,0.9,g);


//            本次续期收入最高合计续佣元
            String str40 = "本次续期收入最高";
            g.drawString(str40,startX,startY+h*3);
            g.setColor(colorName);

            g.drawString(content[2],str40.length()*w+startX,startY+h*3);
            g.setColor(color);

            g.drawString("元",content[2].length()*numWidth+str40.length()*w+startX,startY+3*h);
//续期财富查询：口袋E-工作台-服务代办-续收
             font1 = new Font("微软雅黑", Font.BOLD, 60);
             color=new Color(0,0,0,255);

            g.setFont(font1);
            g.setColor(color);
            g.drawString("续期财富查询：口袋E-工作台-服务代办-续收",startX+50,startY+4*h);


            g.dispose();
            // 输出图片
            FileOutputStream outImgStream = new FileOutputStream(tarImgPath);
            ImageIO.write(bufImg, "jpg", outImgStream);
            System.out.println("添加水印完成");
            outImgStream.flush();
            outImgStream.close();

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
    }

    public static void MyDrawString(String str,int x,int y,double rate,Graphics g){
        String tempStr=new String();
        int orgStringWight=g.getFontMetrics().stringWidth(str);
        int orgStringLength=str.length();
        int tempx=x;
        int tempy=y;
        while(str.length()>0)
        {
            tempStr=str.substring(0, 1);
            str=str.substring(1, str.length());
            g.drawString(tempStr, tempx, tempy);
            tempx=(int)(tempx+(double)orgStringWight/(double)orgStringLength*rate);
        }
    }

    public int getWatermarkLength(String waterMarkContent, Graphics2D g) {
        return g.getFontMetrics(g.getFont()).charsWidth(waterMarkContent.toCharArray(), 0, waterMarkContent.length());
    }
    public static void main(String[] args) {
        Font font = new Font("微软雅黑", Font.BOLD, 90);                     //水印字体
        String srcImgPath="/Users/zhuxingsheng/Downloads/1.png"; //源图片地址
        String tarImgPath="/Users/zhuxingsheng/Downloads/demo1.png"; //待存储的地址
        String waterMarkContent="刘璇贞";  //水印内容
        Color color=new Color(0,0,255,255);                               //水印图片色彩以及透明度
        Color nameColor=new Color(255,253,84,255);                               //水印图片色彩以及透明度
        new WaterMarkUtils().addWaterMark(srcImgPath, tarImgPath,waterMarkContent,"1,444.55","67");

    }
}
