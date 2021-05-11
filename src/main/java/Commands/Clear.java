package Commands;
import Bot.Main;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
//import Bot.Main;

import java.awt.*;
import java.util.List;


public class Clear extends ListenerAdapter {
    public void onGuildMessageReceived(GuildMessageReceivedEvent event){
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if (args[0].equalsIgnoreCase( Main.prefix + "clear")){
            if(args.length < 2){
                //Usage
                EmbedBuilder usage = new EmbedBuilder();
                usage.setColor(Color.red);
                usage.setTitle("Specify amount to delete");
                usage.setDescription("Usage: `" + Main.prefix + "clear [# of messages]`");
                event.getChannel().sendMessage(usage.build()).queue();

            }else{
                try{
                    List<Message> messages = event.getChannel().getHistory().retrievePast(Integer.parseInt(args[1])).complete();
                    event.getChannel().deleteMessages(messages).queue();

                    //Success
                    EmbedBuilder success = new EmbedBuilder();
                    success.setColor(Color.green);
                    success.setTitle("Successfully deleted " + args[1] + " messages.");
                    event.getChannel().sendMessage(success.build()).queue();
                }
                catch (IllegalArgumentException e){
                    if (e.toString().startsWith("java.lang.IllegalArgumentException: Message retrieval")){
                        //Too many messages
                        EmbedBuilder error = new EmbedBuilder();
                        error.setColor(Color.red);
                        error.setTitle("Too many messages selected");
                        error.setDescription("Between 1-100 messages can be deleted at one time.");
                        event.getChannel().sendMessage(error.build()).queue();
                    }else{
                        //Messages too old
                        EmbedBuilder error = new EmbedBuilder();
                        error.setColor(Color.red);
                        error.setTitle("Selected messages are older than 2 weeks");
                        error.setDescription("Messages older than 2 weeks cannot be deleted.");
                        event.getChannel().sendMessage(error.build()).queue();
                    }
                }

            }

        }

    }
}
