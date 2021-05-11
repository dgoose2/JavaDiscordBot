package Bot;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import Commands.Clear;

import javax.security.auth.login.LoginException;

public class Main extends ListenerAdapter {
    public static JDA jda;
    public static String prefix = ".";

    //Bot.Main method
    public static void main(String[] args)throws LoginException{
        String token = "NzYxMDkwMzQ1NTMzNTcxMTAy.X3Ving.UrVRXo0An92EQ9pLrqz7FpSja_I";
        jda = new JDABuilder(AccountType.BOT).setToken(token).buildAsync();
        jda.getPresence().setStatus(OnlineStatus.IDLE);
        jda.getPresence().setGame(Game.watching("boring show"));

        jda.addEventListener(new Clear());


    }
}