package com.recognition;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Set;

public class NewRunTest extends JFrame {

    public static final String nextLine = "\r\n";
    private FileDialog openDia;
    private JButton inputGarage = new JButton("入库");
    private JButton outputGarage = new JButton("出库");
    private JButton garageInfo = new JButton("车库信息");

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NewRunTest().setVisible(true);
            }
        });
    }

    private NewRunTest() {
        initView();
        initComponents();
        actionListener();
    }

    private void actionListener() {
        inputGarage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    openDia.setVisible(true);
                    String dirPath = openDia.getDirectory();//获取文件路径
                    String fileName = openDia.getFile();//获取文件名称

                    //如果打开路径 或 目录为空 则返回空
                    if (dirPath == null || fileName == null) {
                        return;
                    }
                    File file = new File(dirPath, fileName);
                    PostRecognition postRecognition = new PostRecognition();
                    String number = postRecognition.getLicenseNumber(file);
                    GarageManager.getGarageManager().inputGarage(number);
                    JOptionPane.showMessageDialog(null, "入库成功！", "", JOptionPane.INFORMATION_MESSAGE);

                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "入库失败！", "", JOptionPane.INFORMATION_MESSAGE);
                    e1.printStackTrace();
                }
            }
        });

        outputGarage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    openDia.setVisible(true);
                    String dirPath = openDia.getDirectory();//获取文件路径
                    String fileName = openDia.getFile();//获取文件名称

                    //如果打开路径 或 目录为空 则返回空
                    if (dirPath == null || fileName == null) {
                        return;
                    }
                    File file = new File(dirPath, fileName);
                    PostRecognition postRecognition = new PostRecognition();
                    String number = postRecognition.getLicenseNumber(file);
                    GarageManager.getGarageManager().outputGarage(number);
                    JOptionPane.showMessageDialog(null, "出库成功！", "", JOptionPane.INFORMATION_MESSAGE);

                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "出库失败！", "", JOptionPane.INFORMATION_MESSAGE);
                    e1.printStackTrace();
                }
            }
        });

        garageInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Set<String> garage = null;
                try {
                    garage = GarageManager.getGarageManager().listGarage();
                } catch (IOException ex) {
                    System.err.println("listGarage is error" + ex);
                }
                StringBuilder info = new StringBuilder();
                info.append("现存库中车辆信息如下：" + nextLine);
                for (String number : garage) {
                    info.append(number).append(nextLine);
                }
                JOptionPane.showMessageDialog(null, info, "库存信息", JOptionPane.INFORMATION_MESSAGE);
            }
        });

    }

    private void initView() {
        // 关闭程序
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        // 禁止窗口最大化
        setResizable(false);

        // 设置程序窗口居中显示
        Point p = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        // 程序界面高度
        int height = 390;
        // 程序界面宽度
        int width = 530;
        setBounds(p.x, p.y - height / 2, width, height);
        this.setLayout(null);

        setTitle("车库管理系统");
    }

    private void initComponents() {
        openDia = new FileDialog(this, "Open File", FileDialog.LOAD);
        inputGarage.setFocusable(false);
        inputGarage.setBounds(50, 135, 90, 20);
        add(inputGarage);

        outputGarage.setFocusable(false);
        outputGarage.setBounds(200, 135, 90, 20);
        add(outputGarage);

        garageInfo.setFocusable(false);
        garageInfo.setBounds(350, 135, 90, 20);
        add(garageInfo);

    }

}
