package fr.Marodeur.ExpertBuild.GUI;

import fr.Marodeur.ExpertBuild.Main;
import fr.Marodeur.ExpertBuild.Object.ItemBuilder;
import fr.Marodeur.ExpertBuild.Object.MessageBuilder;
import io.github.rysefoxx.inventory.plugin.content.IntelligentItem;
import io.github.rysefoxx.inventory.plugin.content.InventoryContents;
import io.github.rysefoxx.inventory.plugin.content.InventoryProvider;
import io.github.rysefoxx.inventory.plugin.pagination.RyseInventory;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class MainGUI {

    private static final MessageBuilder msg = Main.getInstance().getMessageConfig();

    public void openMainInventory(Player p) {

        RyseInventory.builder()
                .title(msg.getMainGuiTitle())
                .rows(3)
                .disableUpdateTask()
                .provider(new InventoryProvider() {

                    @Override
                    public void init(Player player, InventoryContents contents) {

                        contents.set(7, IntelligentItem.of(new ItemBuilder(msg.getOrganicGuiTitle(), Material.BONE, 1)
                                        .addLore(msg.getOrganicItem())
                                        .build(),
                                event -> {

                            if (p.hasPermission("exporga.use")) {
                                new OrganicGUI().openOrganicGUI(p);
                            }
                        }));

                        contents.set(13, IntelligentItem.empty(new ItemBuilder("EXP_Build Main", Material.HONEYCOMB, 1)
                                .addLore(msg.getMainItem1())
                                .addLore(msg.getMainItem2())
                                .addLore(msg.getMainItem3())
                                .build()));

                        contents.set(19, IntelligentItem.of(new ItemBuilder("EXP_Build Gmask", Material.SPONGE, 1)
                                .addLore("Easily configure my gmask")
                                .build(),
                                event -> {

                        }));

                        contents.set(25, IntelligentItem.of(new ItemBuilder(msg.getFlowerGuiTitle(), Material.SUNFLOWER, 1)
                                        .addLore(msg.getFlowerItem())
                                        .build(),
                                event -> new FlowerGUI().openFlowerInventory(p)));

                        contents.set(1, IntelligentItem.of(new ItemBuilder(msg.getLeatherGuiTitle(), Material.LEATHER_CHESTPLATE, 1)
                                        .addLore(msg.getLeatherItem())
                                        .build(),
                                event -> new LeatherGUI().openLeatherGUI(p)));
                    }
                })
                .build(Main.getInstance())
                .open(p);
    }
}