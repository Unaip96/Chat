package com.example.usuario6i.chat_v2;


import java.io.Serializable;

public class Mensaje  implements Serializable {

    private static final long serialVersionUID = 1L;
    String mensaje;
    String nick;
    String ip;


    public Mensaje(String all) {
        split(all);
    }
    public Mensaje(String ip, String nick,String mensaje) {
        this.mensaje = mensaje;
        this.ip = ip;
        this.nick = nick;
    }
    public String getMensaje() {
        return mensaje;
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    public String getNick() {
        return nick;
    }
    public void setNick(String autor) {
        this.nick = nick;
    }
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }

    public String toString(){
        return ip+";"+nick+";"+mensaje+";";
    }
    public void split(String x){
        String[] split = x.split(";");
        this.ip = split[0];
        this.nick = split[1];
        this.mensaje = split[2];
    }


}
