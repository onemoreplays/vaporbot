package me.inao.botforgod.server;

import me.inao.botforgod.NewMain;
import me.inao.botforgod.classes.ExceptionCatcher;

import java.net.ServerSocket;

public class Server extends Thread{
    private NewMain instance;
    public Server(NewMain instance){
        this.instance = instance;
    }

    @Override
    public void run() {
        try{
            try(ServerSocket socket = new ServerSocket(Integer.parseInt(instance.getConfig().getOption("externalPort")))){
                while(true){
                    new Connection(instance, socket.accept()).start();
                }
            }
        }catch (Exception e){
            new ExceptionCatcher(e);
        }
    }
}
