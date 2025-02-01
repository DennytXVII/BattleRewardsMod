package examplemod;

import legend.core.GameEngine;
import legend.game.modding.events.battle.EnemyRewardsEvent;
import legend.game.modding.events.characters.CharacterStatsEvent;
import legend.game.modding.events.input.InputReleasedEvent;
import legend.game.saves.ConfigEntry;
import legend.game.saves.ConfigRegistryEvent;
import legend.game.types.CharacterData2c;
import legend.game.types.LevelStuff08;
import org.legendofdragoon.modloader.Mod;
import org.legendofdragoon.modloader.events.EventListener;
import org.legendofdragoon.modloader.registries.Registrar;
import org.legendofdragoon.modloader.registries.RegistryDelegate;


@Mod(id = EnemyRewardModifier.MOD_ID)
public class EnemyRewardModifier {
  public static final String MOD_ID = "enemy-reward-modifier";

  public EnemyRewardModifier() {
    GameEngine.EVENTS.register(this);
  }

  @EventListener
  public void handleInputReleased(final InputReleasedEvent event) {

  }

  @EventListener
  public void registerConfig(final ConfigRegistryEvent event) {
    CONFIG_REGISTRAR.registryEvent(event);
  }

  private static final Registrar<ConfigEntry<?>, ConfigRegistryEvent> CONFIG_REGISTRAR = new Registrar<>(GameEngine.REGISTRIES.config, MOD_ID);

  public static final RegistryDelegate<GoldModifierConfigEntry> GOLD_MODIFIER_CONFIG = CONFIG_REGISTRAR.register("gold_modifier", GoldModifierConfigEntry::new);
  public static final RegistryDelegate<XPModifierConfigEntry> XP_MODIFIER_CONFIG = CONFIG_REGISTRAR.register("xp_modifier", XPModifierConfigEntry::new);

  @EventListener
  public void BattleRewardModifier(EnemyRewardsEvent event) {
    event.gold = Math.round(GameEngine.CONFIG.getConfig(EnemyRewardModifier.GOLD_MODIFIER_CONFIG.get()) * event.gold);

    event.xp = Math.round(GameEngine.CONFIG.getConfig(EnemyRewardModifier.XP_MODIFIER_CONFIG.get()) * event.xp);

  }


}


