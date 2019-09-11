package me.vapor.botforgod.commands.commandpack;

import me.vapor.botforgod.NewMain;
import me.vapor.botforgod.commands.Command;
import org.javacord.api.entity.message.Message;

import java.awt.*;

public final class Mute extends Command {
    private NewMain instance;
    public Mute(NewMain instance){
        super(instance, "mute", "mute <id>", new String[]{"mut"});
        this.instance = instance;
    }

    @Override
    public void onCommand(Message message, NewMain instance, String[] args) {
        if(!message.getAuthor().canMuteMembersOnServer()){
            new me.vapor.botforgod.utils.Message(message.getAuthor(), "Error", instance.getConfig().getMessage("messageGenericNoPerms", null, this), Color.RED, message.getChannel());
            return;
        }
        message.delete();
        if(args.length == 0){
            new me.vapor.botforgod.utils.Message(message.getAuthor(), "Error", instance.getConfig().getMessage("messageGenericArgsErr", null, this), Color.RED, message.getChannel());
            return;
        }
        instance.getMuted().add(args[0]);
        new me.vapor.botforgod.utils.Message("Mute",
                instance.getConfig().getMessage("messageMuteSuccess", new String[][]{
                        {"%user%", instance.getApi().getUserById(args[0]).join().getDiscriminatedName()},
                        {"%id%", args[0]}
                }, this), Color.BLACK, message.getServer().get().getChannelsByName("public-mute").get(0).asTextChannel().get());
    }
}
