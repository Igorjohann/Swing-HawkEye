/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import appLogs.Logger;
;
import database.*;
import hardware.*;
import menu.*;
import operatingSystem.*;
import slack.Slack;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MonitoramentoLocal extends javax.swing.JFrame {


    private boolean enableDebugUpdateLabels = true;
    private int indexOfCurrentDisk;
    private int indexOfCurrentNetworkInterface;
    private int maxProcesses = 10;

    //Atualiza as variaveis para enviar os dados atuais para o banco de dados
    public void atualizacaoVariaveis() {
        Database db = new Database();
        Logger log = new Logger();
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                }
                catch (Exception e) {
                }
                while (true) {
                    Slack slack = new Slack();







                    Disco disk = new Disco();
                    Cpu cpu = new Cpu();
                    Ram ram = new Ram();
                    Processos processos = new Processos();

                    //RAM

                    porcentoMemoria1.setText(ram.freeRamPercent(Init.hal.getMemory()) + "%");
                    usoMemoria.setText(String.valueOf(ram.usoRam(Init.hal.getMemory())) + "GB");
                    totalMemoria.setText(String.valueOf(ram.totalRam(Init.hal.getMemory())) + "GB");

                    porcentRAM = (int) ram.freeRamPercent(Init.hal.getMemory());
                    porcentRamWarning = getAlertaRAM() * 75 / 100;

                    //JOptionPane.showMessageDialog(null, porcentRamWarning);


                    //224, 210, 8 - AMARELO


                    if(porcentRAM > getAlertaRAM()){
                        porcentoMemoria1.setForeground(new Color(167, 38, 8));

                        try {
                            log.usageAlertLog("ram", String.valueOf(getAlertaRAM()), String.valueOf(ram.usoRam(Init.hal.getMemory())));
                            slack.sendMessage("RAM", Init.os.toString(), "");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                   else if(porcentRAM > porcentRamWarning){
                        porcentoMemoria1.setForeground(new Color(224, 210, 8));
                        try {
                            log.usageAlertLog("ram", String.valueOf(getAlertaRAM()), String.valueOf(ram.usoRam(Init.hal.getMemory())));
                            slack.sendMessage("RAM", Init.os.toString(), "");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                        porcentoMemoria1.setForeground(new Color(8, 165, 55));
                    }


                    //CPU

                    porcentoCPU.setText(cpu.usedCpuPercent(Init.hal.getProcessor()) + "%");
                    usoCPU.setText(cpu.cpuFrequency(Init.hal.getProcessor()));

                    porcentCPU = (int) cpu.usedCpuPercent(Init.hal.getProcessor());
                    porcentCpuWarning = getAlertaCPU() * 75 / 100;


                    if(porcentCPU > getAlertaCPU()){
                        porcentoCPU.setForeground(new Color(167, 38, 8));
                        try {
                            log.usageAlertLog("ram", String.valueOf(getAlertaRAM()), String.valueOf(ram.usoRam(Init.hal.getMemory())));
                            slack.sendMessage("RAM", Init.os.toString(), "");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                    else if(porcentCPU > porcentCpuWarning){
                        porcentoCPU.setForeground(new Color(224, 210, 8));
                        try {
                            log.usageAlertLog("ram", String.valueOf(getAlertaRAM()), String.valueOf(ram.usoRam(Init.hal.getMemory())));
                            slack.sendMessage("RAM", Init.os.toString(), "");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                        porcentoCPU.setForeground(new Color(8, 165, 55));
                    }

                    //DISCO

                    porcentoDISCO.setText(disk.freeDiskPercent(disk.freeDiskPercent(0)) + "%");
                    usoDISCO.setText(String.valueOf(disk.usoDisco(0)) + "GB");//pegando o primeiro disco do PC
                    totalDISCO.setText(String.valueOf(disk.totalDisco(0)) + "GB");

                    porcentDisk = (int) disk.freeDiskPercent(disk.freeDiskPercent(0));
                    porcentDiskWarning = getAlertaDisk() * 75 / 100;


                    if(porcentDisk > getAlertaDisk()){
                        porcentoDISCO.setForeground(new Color(167, 38, 8));
                        try {
                            log.usageAlertLog("disco", String.valueOf(getAlertaDisk()), String.valueOf(disk.usoDisco(0)));
                            slack.sendMessage("CPU", Init.os.toString(), "" );
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else if(porcentDisk > porcentDiskWarning){
                        porcentoDISCO.setForeground(new Color(224, 210, 8));
                        try {
                            log.usageAlertLog("ram", String.valueOf(getAlertaRAM()), String.valueOf(ram.usoRam(Init.hal.getMemory())));
                            slack.sendMessage("RAM", Init.os.toString(), "");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                        porcentoDISCO.setForeground(new Color(8, 165, 55));
                    }

                    //PROCESSOS
                    tempName = tempPid = tempCpuPercent = tempRam = tempRamPercent = "";
                    for (String[] process : Processos.getProcessesAsStringTable(15)) {
                        tempName += (process[0].length() > 13 ? process[0].substring(0, 13) : process[0]) + "\n";
                        tempPid += process[1] + "\n";
                        tempCpuPercent += process[2] + "\n";
                        tempRam += process[3] + "\n";
                        tempRamPercent += process[4] + "\n";
                    }
                    labelProcessName.setText(tempName.substring(0, tempName.length() - 1));
                    labelProcessPid.setText(tempPid.substring(0, tempPid.length() - 1));
                    labelProcessCpuPercent.setText(tempCpuPercent.substring(0, tempCpuPercent.length() - 1));
                    labelProcessRam.setText(tempRam.substring(0, tempRam.length() - 1));
                    labelProcessRamPercent.setText(tempRamPercent.substring(0, tempRamPercent.length() - 1));

                    db.insertComponente(disk.totalDisco(0), disk.freeDiskPercent(0), disk.disponivelDisco(0), cpu.modeloCpu(Init.hal.getProcessor()) ,
                    cpu.usedCpuPercent(Init.hal.getProcessor()), Init.os.getProcessCount(), ram.totalRam(Init.hal.getMemory()), ram.freeRamPercent(Init.hal.getMemory()),
                    ram.avaibleRam(Init.hal.getMemory()), getIdMaquina());

                    //Configuração do tempo de sleep da thread
                    try {Thread.sleep(2000);
                    }
                    catch (Exception e) {}
                }
            }
        }.start();//thread
    }//atualização

    public MonitoramentoLocal() {
        initComponents();
        atualizacaoVariaveis();
        centralizarComponente();
    }

    public void centralizarComponente() {
        Dimension ds = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension dw = getSize();
        setLocation((ds.width - dw.width) / 2, (ds.height - dw.height) / 2);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        MemoriaLabel = new javax.swing.JLabel();
        barraMemoria = new javax.swing.JLabel();
        usoMemoria = new javax.swing.JLabel();
        totalMemoria = new javax.swing.JLabel();
        usoLabel1 = new javax.swing.JLabel();
        porcentoMemoria1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        CPULabel = new javax.swing.JLabel();
        porcentoCPU = new javax.swing.JLabel();
        usoLabel = new javax.swing.JLabel();
        usoCPU = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        porcentoDISCO = new javax.swing.JLabel();
        usoLabel2 = new javax.swing.JLabel();
        usoDISCO = new javax.swing.JLabel();
        DISCOLabel = new javax.swing.JLabel();
        barraDISCO = new javax.swing.JLabel();
        totalDISCO = new javax.swing.JLabel();
        panelProcess = new javax.swing.JPanel();
        imageProcess = new javax.swing.JLabel();
        labelProcessSimboloPorcentagem3 = new javax.swing.JLabel();
        labelProcessSimboloPorcentagem5 = new javax.swing.JLabel();
        labelProcessSimboloPorcentagem6 = new javax.swing.JLabel();
        labelProcessSimboloPorcentagem7 = new javax.swing.JLabel();
        labelProcessSimboloPorcentagem8 = new javax.swing.JLabel();
        labelProcessSimboloPorcentagem9 = new javax.swing.JLabel();
        labelProcessName = new javax.swing.JTextArea();
        labelProcessPid = new javax.swing.JTextArea();
        labelProcessCpuPercent = new javax.swing.JTextArea();
        labelProcessRam = new javax.swing.JTextArea();
        labelProcessRamPercent = new javax.swing.JTextArea();

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 51, 102));
        jPanel1.setToolTipText("");
        jPanel1.setAutoscrolls(true);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("MONITORAMENTO LOCAL");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/LogoHawkEyee.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(99, 99, 99))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2)))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        MemoriaLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/memoria.png"))); // NOI18N

        barraMemoria.setFont(new java.awt.Font("Dialog", 3, 24)); // NOI18N
        barraMemoria.setForeground(new java.awt.Color(58, 65, 84));
        barraMemoria.setText("/");

        usoMemoria.setFont(new java.awt.Font("Dialog", 3, 24)); // NOI18N
        usoMemoria.setText("06,70 GiB");

        totalMemoria.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        totalMemoria.setText("16,00 GiB");

        usoLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        usoLabel1.setText("USO");

        porcentoMemoria1.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        porcentoMemoria1.setText("36%");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(MemoriaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(usoMemoria, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(barraMemoria, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totalMemoria))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(porcentoMemoria1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(usoLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(porcentoMemoria1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(usoLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(usoMemoria, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(barraMemoria, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(totalMemoria, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(MemoriaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        CPULabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cpu-512.png"))); // NOI18N

        porcentoCPU.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        porcentoCPU.setText("30,0%");

        usoLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        usoLabel.setText("USO");

        usoCPU.setFont(new java.awt.Font("Dialog", 3, 24)); // NOI18N
        usoCPU.setText("06,70 ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(CPULabel, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(porcentoCPU, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(usoLabel))
                    .addComponent(usoCPU, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(CPULabel, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(porcentoCPU)
                            .addComponent(usoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(usoCPU, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39))))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        porcentoDISCO.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        porcentoDISCO.setText("100,00%");

        usoLabel2.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        usoLabel2.setText("USO");

        usoDISCO.setFont(new java.awt.Font("Dialog", 3, 24)); // NOI18N
        usoDISCO.setText("06,70 ");

        DISCOLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HD.png"))); // NOI18N

        barraDISCO.setFont(new java.awt.Font("Dialog", 3, 24)); // NOI18N
        barraDISCO.setForeground(new java.awt.Color(58, 65, 84));
        barraDISCO.setText("/");

        totalDISCO.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        totalDISCO.setText("16,00 GiB");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(DISCOLabel)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(porcentoDISCO)
                        .addGap(18, 18, 18)
                        .addComponent(usoLabel2))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(usoDISCO, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(barraDISCO, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(totalDISCO)))
                .addGap(0, 29, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(DISCOLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(porcentoDISCO)
                            .addComponent(usoLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(usoDISCO, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(barraDISCO, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(totalDISCO, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        panelProcess.setBackground(new java.awt.Color(255, 255, 255));
        panelProcess.setPreferredSize(new java.awt.Dimension(356, 130));

        imageProcess.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imageProcess.setIcon(new javax.swing.ImageIcon(getClass().getResource("/processo-icon.png"))); // NOI18N

        labelProcessSimboloPorcentagem3.setBackground(new java.awt.Color(0, 0, 0));
        labelProcessSimboloPorcentagem3.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        labelProcessSimboloPorcentagem3.setForeground(new java.awt.Color(107, 107, 107));
        labelProcessSimboloPorcentagem3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelProcessSimboloPorcentagem3.setText("TOP PROCESSOS");

        labelProcessSimboloPorcentagem5.setBackground(new java.awt.Color(102, 102, 102));
        labelProcessSimboloPorcentagem5.setForeground(new java.awt.Color(107, 107, 107));
        labelProcessSimboloPorcentagem5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelProcessSimboloPorcentagem5.setText("Nome");

        labelProcessSimboloPorcentagem6.setBackground(new java.awt.Color(102, 102, 102));
        labelProcessSimboloPorcentagem6.setForeground(new java.awt.Color(107, 107, 107));
        labelProcessSimboloPorcentagem6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelProcessSimboloPorcentagem6.setText("PID");

        labelProcessSimboloPorcentagem7.setBackground(new java.awt.Color(102, 102, 102));
        labelProcessSimboloPorcentagem7.setForeground(new java.awt.Color(107, 107, 107));
        labelProcessSimboloPorcentagem7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelProcessSimboloPorcentagem7.setText("%CPU");

        labelProcessSimboloPorcentagem8.setBackground(new java.awt.Color(102, 102, 102));
        labelProcessSimboloPorcentagem8.setForeground(new java.awt.Color(107, 107, 107));
        labelProcessSimboloPorcentagem8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelProcessSimboloPorcentagem8.setText("RAM");

        labelProcessSimboloPorcentagem9.setBackground(new java.awt.Color(102, 102, 102));
        labelProcessSimboloPorcentagem9.setForeground(new java.awt.Color(107, 107, 107));
        labelProcessSimboloPorcentagem9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelProcessSimboloPorcentagem9.setText("%RAM");

        labelProcessName.setEditable(false);
        labelProcessName.setColumns(15);
        labelProcessName.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        labelProcessName.setForeground(new java.awt.Color(187, 187, 186));
        labelProcessName.setRows(8);
        labelProcessName.setText("loading...");
        labelProcessName.setDisabledTextColor(new java.awt.Color(153, 153, 154));

        labelProcessPid.setEditable(false);
        labelProcessPid.setColumns(10);
        labelProcessPid.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        labelProcessPid.setForeground(new java.awt.Color(187, 187, 186));
        labelProcessPid.setRows(8);

        labelProcessCpuPercent.setEditable(false);
        labelProcessCpuPercent.setColumns(10);
        labelProcessCpuPercent.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        labelProcessCpuPercent.setForeground(new java.awt.Color(187, 187, 186));
        labelProcessCpuPercent.setRows(8);

        labelProcessRam.setEditable(false);
        labelProcessRam.setColumns(11);
        labelProcessRam.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        labelProcessRam.setForeground(new java.awt.Color(187, 187, 186));
        labelProcessRam.setRows(8);

        labelProcessRamPercent.setEditable(false);
        labelProcessRamPercent.setColumns(9);
        labelProcessRamPercent.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        labelProcessRamPercent.setForeground(new java.awt.Color(187, 187, 186));
        labelProcessRamPercent.setRows(8);

        javax.swing.GroupLayout panelProcessLayout = new javax.swing.GroupLayout(panelProcess);
        panelProcess.setLayout(panelProcessLayout);
        panelProcessLayout.setHorizontalGroup(
            panelProcessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProcessLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(imageProcess, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelProcessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelProcessLayout.createSequentialGroup()
                        .addGroup(panelProcessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelProcessSimboloPorcentagem5, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                            .addComponent(labelProcessName, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelProcessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelProcessLayout.createSequentialGroup()
                                .addComponent(labelProcessSimboloPorcentagem6, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelProcessSimboloPorcentagem7, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(labelProcessSimboloPorcentagem8, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelProcessSimboloPorcentagem9, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                                .addGap(4, 4, 4))
                            .addGroup(panelProcessLayout.createSequentialGroup()
                                .addComponent(labelProcessPid, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(labelProcessCpuPercent, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(labelProcessRam, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelProcessRamPercent, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                .addContainerGap())))
                    .addComponent(labelProcessSimboloPorcentagem3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        panelProcessLayout.setVerticalGroup(
            panelProcessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProcessLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelProcessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imageProcess, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelProcessLayout.createSequentialGroup()
                        .addComponent(labelProcessSimboloPorcentagem3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelProcessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelProcessSimboloPorcentagem5)
                            .addComponent(labelProcessSimboloPorcentagem6)
                            .addComponent(labelProcessSimboloPorcentagem7)
                            .addComponent(labelProcessSimboloPorcentagem8)
                            .addComponent(labelProcessSimboloPorcentagem9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelProcessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                            .addComponent(labelProcessName, javax.swing.GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)
                            .addComponent(labelProcessPid)
                            .addComponent(labelProcessCpuPercent)
                            .addComponent(labelProcessRam)
                            .addComponent(labelProcessRamPercent))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelProcess, javax.swing.GroupLayout.PREFERRED_SIZE, 612, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelProcess, javax.swing.GroupLayout.PREFERRED_SIZE, 489, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {

        } catch (InstantiationException ex) {

        } catch (IllegalAccessException ex) {

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {

        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MonitoramentoLocal().setVisible(true);
            }
        });
    }

    // Variaveis do SWING/COLETA DE DADOS
    private javax.swing.JLabel CPULabel;
    private javax.swing.JLabel DISCOLabel;
    private javax.swing.JLabel MemoriaLabel;
    private javax.swing.JLabel barraDISCO;
    private javax.swing.JLabel barraMemoria;
    private javax.swing.JLabel imageProcess;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextArea labelProcessCpuPercent;
    private javax.swing.JTextArea labelProcessName;
    private javax.swing.JTextArea labelProcessPid;
    private javax.swing.JTextArea labelProcessRam;
    private javax.swing.JTextArea labelProcessRamPercent;
    private javax.swing.JLabel labelProcessSimboloPorcentagem3;
    private javax.swing.JLabel labelProcessSimboloPorcentagem5;
    private javax.swing.JLabel labelProcessSimboloPorcentagem6;
    private javax.swing.JLabel labelProcessSimboloPorcentagem7;
    private javax.swing.JLabel labelProcessSimboloPorcentagem8;
    private javax.swing.JLabel labelProcessSimboloPorcentagem9;
    private javax.swing.JPanel panelProcess;
    private javax.swing.JLabel porcentoCPU;
    private javax.swing.JLabel porcentoDISCO;
    private javax.swing.JLabel porcentoMemoria1;
    private javax.swing.JLabel totalDISCO;
    private javax.swing.JLabel totalMemoria;
    private javax.swing.JLabel usoCPU;
    private javax.swing.JLabel usoDISCO;
    private javax.swing.JLabel usoLabel;
    private javax.swing.JLabel usoLabel1;
    private javax.swing.JLabel usoLabel2;
    private javax.swing.JLabel usoMemoria;
    private String idMaquina;
    private int alertaCPU;
    private int alert = alertaCPU;
    private int alertaRAM;
    private int alertaDisk;
    private int porcentCPU;
    private int porcentRAM;
    private int porcentDisk;
    private int porcentRamWarning;
    private int porcentCpuWarning;
    private int porcentDiskWarning;
    // End of variables declaration//GEN-END:variables
    private int countDebugUpdate = 0;
    private String temp;
    private String tempName;
    private String tempPid;
    private String tempCpuPercent;
    private String tempRam;
    private String tempRamPercent;


    //GETTERS/SETTERS
    public String getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(String idEmpresa) {
        this.idMaquina = idEmpresa;
    }

    public int getAlertaCPU() {
        return alertaCPU;
    }

    public void setAlertaCPU(int alertaCPU) {
        this.alertaCPU = alertaCPU;
    }

    public int getAlertaRAM() {
        return alertaRAM;
    }

    public void setAlertaRAM(int alertaRAM) {
        this.alertaRAM = alertaRAM;
    }

    public int getAlertaDisk() {
        return alertaDisk;
    }

    public void setAlertaDisk(int alertaDisk) {
        this.alertaDisk = alertaDisk;
    }
}
