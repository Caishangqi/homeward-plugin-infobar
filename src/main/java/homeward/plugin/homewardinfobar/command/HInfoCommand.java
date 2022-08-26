package homeward.plugin.homewardinfobar.command;


import com.caizi.mf.annotations.Command;
import com.caizi.mf.annotations.Default;
import com.caizi.mf.annotations.SubCommand;
import com.caizi.mf.base.CommandBase;
import homeward.plugin.homewardinfobar.HomewardInfoBar;
import homeward.plugin.homewardinfobar.bossbar.InfoHUD;
import net.kyori.adventure.audience.Audience;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

@Command("hinfo")
public class HInfoCommand extends CommandBase {

    @Default
    public void defaultInfoCommand(final CommandSender commandSender) {
        HomewardInfoBar.customLogger.send("Welcome to use Hinfo", (Player) commandSender);
    }

    @SubCommand("testbossbar")
    public void testBossBar(final CommandSender commandSender) {
        HashMap<Player, InfoHUD> hudPool = HomewardInfoBar.hudManager.getHUDPool();
        if (hudPool.containsKey((Player) commandSender)) {
            hudPool.get((Player) commandSender).hideActiveBossBar((Audience) commandSender);
            hudPool.remove((Player) commandSender);
            hudPool.put((Player) commandSender, new InfoHUD());
            hudPool.get((Player) commandSender).showMyBossBar((Audience) commandSender);
        } else {
            hudPool.put((Player) commandSender, new InfoHUD());
            hudPool.get((Player) commandSender).showMyBossBar((Audience) commandSender);
        }

    }

    @SubCommand("showsequence")
    public void showsequence(final CommandSender commandSender) {
        HomewardInfoBar.customLogger.send(HomewardInfoBar.hudManager.getDisplayPriority().toString(), (Player) commandSender);
    }

    @SubCommand("rawtest")
    public void rawTest(final CommandSender commandSender) {


        TextComponent textComponent = new TextComponent();
        textComponent.setFont("boss_bar_1");
        textComponent.setText("ꈈ");


        commandSender.spigot().sendMessage(textComponent);
    }


}
