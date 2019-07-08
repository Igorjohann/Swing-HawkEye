package operatingSystem;


import database.Database;
import hardware.*;
import hardware.Cpu;
import hardware.Disco;
import hardware.Ram;
import menu.*;
import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;
import oshi.software.os.OperatingSystem;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.sql.Timestamp;
import java.util.Date;

import static java.lang.Thread.sleep;

public class Information {

    public void continuousInsert() throws IOException, InterruptedException {

        while(true){
//            Processos.exibir();
//            Ram.print(Init.hal.getMemory());
//            Cpu.print(Init.hal.getProcessor());
//            ComputerSystem.print(Init.hal.getComputerSystem());
//            OperationalSystem.print();




            SystemInfo si = new SystemInfo();

            OperatingSystem os = si.getOperatingSystem();

            Ram ram = new Ram();

            Cpu cpu = new Cpu();

            Disco disco = new Disco();

            Processos processos = new Processos();

            Database db = new Database();

            String totalDisco = "";
            double usoDisco = 0;
            String disponivelDisco = "";
            double totalRam = 0;
            double usoRam = 0;
            String modeloCpu = "";
            double x = (Math.random()*((100-1)+1))+1;



//            db.setTotalDisco(disco.totalDisco(totalDisco));
//
//            db.setUsoDisco(disco.usoDisco(usoDisco));
//
//            db.setDisponivelDisco(disco.disponivelDisco(disponivelDisco));
//
//            db.setModeloCpu(cpu.modeloCpu(modeloCpu));
//
//            db.setUsoCpu(x);
//            db.setProcessosCpu(os.getProcessCount());
//            db.setTotalRam(ram.totalRam(totalRam));
//            db.setUsoRam(ram.usoRam(usoRam));
//            db.setDisponivelRam(db.getTotalRam() - db.getUsoRam());
////            db.getIdEmpresa();
//            processos.processo();
            //String a = db.getIdEmpresa();

            //db.insertComponente();

           sleep(5000);

        }
        }
}
