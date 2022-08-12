package homeward.plugin.homewardinfobar;

import com.caizi.mf.base.CommandManager;
import com.caizi.utils.logs.ConsoleLogger;
import com.caizi.utils.logs.CustomLogger;
import com.caizi.utils.logs.LoggerManipulationType;
import homeward.plugin.homewardinfobar.command.HInfoCommand;
import homeward.plugin.homewardinfobar.compatibilities.CompatibilityManager;
import homeward.plugin.homewardinfobar.compatibilities.provided.MinecraftCompatibility;
import homeward.plugin.homewardinfobar.hud.HUDManager;
import homeward.plugin.homewardinfobar.scheduler.OnTickScheduler;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class HomewardInfoBar extends JavaPlugin {

    private static CommandManager commandManager;

    public static CompatibilityManager compatibilityManager;
    public static FileConfiguration config;
    public static ConsoleLogger consoleLogger;
    public static CustomLogger customLogger;
    private static final String pluginPrefix = "&5HInfo &f| ";

    public static HUDManager hudManager;

    public static HomewardInfoBar plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        initializedComponents();
        loadCompatibility();
        registerCommands();
        loadConfigurations();
        initializeHUDManager();
        loadingScheduler();
    }

    private void initializeHUDManager() {
        consoleLogger.send(LoggerManipulationType.LOAD, "&7加载初始化的HUD管理器");
        hudManager = HUDManager.INSTANCE;
    }

    private void registerCommands() {
        commandManager = new CommandManager(this);
        commandManager.register(new HInfoCommand());
    }

    private void loadCompatibility() {
        compatibilityManager = new homeward.plugin.homewardinfobar.compatibilities.CompatibilityManager(this, consoleLogger, MinecraftCompatibility.class);
    }

    private void loadConfigurations() {
        //注册默认Config,没有的话创建一个
        saveDefaultConfig();
        config = getConfig();
    }


    private void initializedComponents() {
        plugin = this;
        consoleLogger = new ConsoleLogger();
        customLogger = new CustomLogger();
        customLogger.setLoggerPrefix(pluginPrefix);
        consoleLogger.setLoggerPrefix(pluginPrefix);
        consoleLogger.send(LoggerManipulationType.LOAD, "正在初始化 " + this.getName() + " 的必要组件");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        consoleLogger.send(LoggerManipulationType.UNLOAD, "关闭 " + this.getName() + " 中...");
    }

    public static HomewardInfoBar getInstance() {
        return plugin;
    }

    private void loadingScheduler() {
        OnTickScheduler.INSTANCE.runScheduler();
    }
}