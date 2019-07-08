package database;

import crypto.*;
import telas.*;

import javax.swing.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Database{

    Encrypt crypto = new Encrypt();
    private DateTimeFormatter dt = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    private DateTimeFormatter tm = DateTimeFormatter.ofPattern("HH:mm:ss");
    private LocalDateTime now = LocalDateTime.now();
    private String sistemaOperacional;
    private String nomeMaquina = "Teste";
    private String setor = "Infra";
    private String alertaCPU;
    private String alertaRAM;
    private String alertaDisco;
    private String usuario;
    private String[] dataLeitura = getDt().format(getNow()).split(" ");
    private String SQL;


    //String para setar dados que 'localizam' nosso banco de dados e seta as credenciais necessárias para acessar o banco.
    private String connectionUrl = "jdbc:sqlserver://hawkeyedb.database.windows.net:1433;" + "database=HawkEye;" + "user=HawkEye-Admin;" + "password=@hawk123;" + "encrypt=true;" + "trustServerCertificate=false;" + "LoginUsuarioTimeout=30;";

    //Verifica se o usuário e senha batem com o que está salvo na base
    public boolean selectUserAndPasswordIsCorrect(String user, String password){
        boolean correct = false;
        try (Connection con = DriverManager.getConnection(getConnectionUrl()); Statement stmt = con.createStatement()){
            SQL = "SELECT usuario, senha FROM empresa WHERE usuario = '"+user+"' AND senha = '"+password+"'";
            ResultSet rs = stmt.executeQuery(SQL);

            if(!rs.next()){
                JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos.");

            }
            else{
                correct = true;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return correct;
    }
    //Retorna o ID da empresa que está logada
    public String selectIdEmpresa(String usuario) {
        String id = "";
        String id1 = "";
        try (Connection con = DriverManager.getConnection(getConnectionUrl()); Statement stmt = con.createStatement();) {
            String SQL = "SELECT idEmpresa FROM empresa WHERE usuario = '"+ usuario +"';";
            ResultSet rs = stmt.executeQuery(SQL);

            while(rs.next()){
                id = rs.getString("idEmpresa");
                id1 = rs.getString("idEmpresa");
            }

            if(selectMaquina(id, System.getProperty("user.name"))){

                selectMaquina1(id1, System.getProperty("user.name"));
            }
            else {

                TelaCadastroMaquina cadastroMaquina = new TelaCadastroMaquina();
                cadastroMaquina.setIdEmpresa(id);
                cadastroMaquina.setVisible(true);
//                cadastroMaquina.finalizarCadastro;
                //insertMaquina(id, System.getProperty("user.name"));
            }
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    //Verifica se aquela empresa já possui uma máquina(com o SingleId desse 'servidor')
    public boolean selectMaquina(String idEmpresa, String singleId){
        boolean maquinaExiste = false;
        try (Connection con = DriverManager.getConnection(getConnectionUrl()); Statement stmt = con.createStatement();) {
            String SQL = "SELECT * FROM maquina WHERE idEmpresa = '"+ idEmpresa +"' AND singleId = '"+ crypto.singleID(singleId) +"';";

            ResultSet rs = stmt.executeQuery(SQL);

            if(!rs.next()){

                idEmpresa = rs.getString("idMaquina");
                TelaCadastroMaquina cadastroMaquina = new TelaCadastroMaquina();
                cadastroMaquina.setVisible(true);
                cadastroMaquina.setIdEmpresa(idEmpresa);
                return maquinaExiste;
            }
            else{
                maquinaExiste = true;
                selectMaquina1(idEmpresa, System.getProperty("user.name"));
                return maquinaExiste;
            }

        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            //e.printStackTrace();
        }
        return maquinaExiste;
    }

    //Retorna o ID da máquina atual e os valores de alerta definido pelo usuário
    public String selectMaquina1(String id, String singleId){
        String idMaquina = "";
        int alertaCPU = 0;
        int alertaRAM = 0;
        int alertaDisk = 0;
        try (Connection con = DriverManager.getConnection(getConnectionUrl()); Statement stmt = con.createStatement();) {
            String SQL = "SELECT * FROM maquina WHERE idEmpresa = '"+ id +"' AND singleId = '"+ crypto.singleID(singleId) +"';";

            ResultSet rs = stmt.executeQuery(SQL);

            if(rs.next()){
                idMaquina = rs.getString("idMaquina");
                alertaCPU = Integer.parseInt(rs.getString("alertaCPU"));
                alertaRAM = Integer.parseInt(rs.getString("alertaRAM"));
                alertaDisk = Integer.parseInt(rs.getString("alertaDisco"));
                //id2 = rs.getString("idMaquina");
                MonitoramentoLocal monitoramentoLocal = new MonitoramentoLocal();
                //selectAlertaCPU(id1, 0);
                monitoramentoLocal.setIdMaquina(idMaquina);
                monitoramentoLocal.setAlertaCPU(alertaCPU);
                monitoramentoLocal.setAlertaRAM(alertaRAM);
                monitoramentoLocal.setAlertaDisk(alertaDisk);
                monitoramentoLocal.setVisible(true);
                return idMaquina;
            }
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
        return idMaquina;
    }

    //Realiza o insert no banco de dados do novo 'servidor'.
    public void insertMaquina(String idEmpresa, String singleId) {
        String insertSql = "INSERT INTO maquina(sistemaOperacional, nomeMaquina, setor, idEmpresa, alertaCPU, alertaRAM, alertaDisco, singleId) VALUES "
                + "('" + getSistemaOperacional() +"' , '" + getNomeMaquina() +"', '" + getSetor() + "', '" + idEmpresa +
                "', '" + getAlertaCpu() + "', '" + getAlertaRam() + "', '" + getAlertaDisco() + "', '" + crypto.singleID(singleId) + "');";

        ResultSet resultSet = null;

        try (Connection connection = DriverManager.getConnection(getConnectionUrl());
             PreparedStatement prepsInsertProduct = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);) {

            prepsInsertProduct.execute();
            // Retrieve the generated key from the insert.
            resultSet = prepsInsertProduct.getGeneratedKeys();

            // Print the ID of the inserted row.
            while (resultSet.next()) {

            }

        }
        // Handle any errors that may have occurred.
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Realiza o insert dos dados que estão sendo colhidos pela Oshi
    public void insertComponente(double totalDisco, double usoDisco, double disponivelDisco, String modeloCpu, double usoCpu, int processosCpu, double totalRam, double usoRam, double disponivelRam, String idMaquina){

        String insertSql = "INSERT INTO componente(totalDisco, usoDisco, disponivelDisco, modeloCpu, " +
                "usoCpu, processosCpu, totalRam, usoRam, disponivelRam, idMaquina, dataLeitura, horaLeitura) VALUES "
                + "('" + totalDisco +"' , '" + usoDisco +"', '" + disponivelDisco +
                "', '" + modeloCpu + "', '" + usoCpu + "'" +
                ", '" + processosCpu + "', '" + totalRam + "'" +
                ", '" + usoRam + "', '" + disponivelRam + "'" +
                ", '" + idMaquina +"', '" + dataLeitura[0].replaceAll("/", "-") +"', '" + dataLeitura[1] +"');";

        ResultSet resultSet = null;

        try (Connection connection = DriverManager.getConnection(getConnectionUrl());
             PreparedStatement prepsInsertProduct = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);) {

            prepsInsertProduct.execute();
            // Retrieve the generated key from the insert.
            resultSet = prepsInsertProduct.getGeneratedKeys();

            // Print the ID of the inserted row.
            while (resultSet.next()) {

            }
        }
        // Handle any errors that may have occurred.
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Getters && Setters
    public String getConnectionUrl() {
        return connectionUrl;
    }

    public DateTimeFormatter getDt() {
        return dt;
    }

    public void setDt(DateTimeFormatter dt) {
        this.dt = dt;
    }

    public DateTimeFormatter getTm() {
        return tm;
    }

    public void setTm(DateTimeFormatter tm) {
        this.tm = tm;
    }

    public LocalDateTime getNow() {
        return now;
    }

    public void setNow(LocalDateTime now) {
        this.now = now;
    }

    public String getAlertaDisco() {
        return alertaDisco;
    }

    public void setAlertaDisco(String alertaDisco) {
        this.alertaDisco = alertaDisco;
    }

    public String getAlertaCpu() {
        return alertaCPU;
    }

    public void setAlertaCpu(String alertaCPU) {
        this.alertaCPU = alertaCPU;
    }

    public String getAlertaRam() {
        return alertaRAM;
    }

    public void setAlertaRam(String alertaRAM) {
        this.alertaRAM = alertaRAM;
    }

    public String getSistemaOperacional() {
        return sistemaOperacional;
    }

    public void setSistemaOperacional(String sistemaOperacional) {
        this.sistemaOperacional = sistemaOperacional;
    }

    public String getNomeMaquina() {
        return nomeMaquina;
    }

    public void setNomeMaquina(String nomeMaquina) {
        this.nomeMaquina = nomeMaquina;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }
}