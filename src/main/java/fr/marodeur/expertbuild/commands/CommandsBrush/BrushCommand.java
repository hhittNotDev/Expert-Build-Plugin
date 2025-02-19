package fr.marodeur.expertbuild.commands.CommandsBrush;

import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.function.pattern.Pattern;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.Region;

import fr.marodeur.expertbuild.Main;
import fr.marodeur.expertbuild.api.GlueList;
import fr.marodeur.expertbuild.api.fawe.UtilsFAWE;
import fr.marodeur.expertbuild.brush.*;
import fr.marodeur.expertbuild.enums.ExecutorType;
import fr.marodeur.expertbuild.object.*;

import fr.marodeur.expertbuild.object.builderObjects.TerraParameter;
import org.bukkit.Bukkit;
import org.bukkit.block.Biome;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BrushCommand extends AbstractCommand {

    private static final Configuration conf = Main.configuration();

    @Override
    public String getCommand() {
        return "/flower";
    }

    @Override
    public String getSyntax() {
        return "/flower <brush> [pattern - radius]";
    }

    @Override
    public Integer getMinimumArgsLength() {
        return 1;
    }

    @Override
    public String getPermission() {
        return "exp.command.flower";
    }

    @Override
    public List<ExecutorType> getExecutorType() {
        return List.of(ExecutorType.PLAYER);
    }

    @Override
    public void execute(CommandSender executor, Command command, @NotNull String label, @NotNull String[] args) {

        BrushBuilder brushBuilder = this.getBrushBuilder((Player) executor);
        Player p = (Player) executor;

        Pattern pattern;
        Integer radius = 0;
        Biome biome;

        switch (args[0]) {

            case "material" -> {

                if (this.getValidArgument().isPattern(p, args[1])) {
                    pattern = this.getValidArgument().getPattern(p, args[1]);
                } else {
                    this.getValidArgument().sendMessageInvalidPattern(executor, args[1]);
                    break;
                }

                brushBuilder.setPattern(pattern)
                        .sendMessage("expbuild.message.brush.material_set", true);
            }

            case "radius" -> {

                if (this.getValidArgument().isInteger(args[1], 0, conf.getMaxRayonBrush())) {
                    radius = this.getValidArgument().getInteger(args[1]);
                } else {
                    this.getValidArgument().sendMessageInvalidInteger(executor, args[1], 0, conf.getMaxRayonBrush());
                    break;
                }

                brushBuilder.setRadius(radius)
                        .sendMessage("expbuild.message.brush.radius_set", true);
            }

            case "biome" -> {

                if (this.getValidArgument().isBiome(args[1])) {
                    biome = this.getValidArgument().getBiome(args[1]);
                } else {
                    this.getValidArgument().sendMessageInvalidBiome(executor, args[1]);
                    break;
                }

                if (this.getValidArgument().isInteger(args[2], 0, conf.getMaxRayonBrush())) {
                    radius = this.getValidArgument().getInteger(args[2]);
                } else {
                    this.getValidArgument().sendMessageInvalidInteger(executor, args[2], 0, conf.getMaxRayonBrush());
                    break;
                }

                brushBuilder.setBrush(new BiomeBrush())
                        .setEnable(true)
                        .setBiome(biome)
                        .setRadius(radius)
                        .sendMessage("expbuild.message.brush.brush_enable", true, new String[]{"Biome"});
            }

            // TYPE : Brush Pattern Radius
            case "overlay" -> {

                if (this.getValidArgument().isPattern(p, args[1])) {
                    pattern = this.getValidArgument().getPattern(p, args[1]);
                } else {
                    this.getValidArgument().sendMessageInvalidPattern(executor, args[1]);
                    break;
                }

                if (this.getValidArgument().isInteger(args[2], 0, conf.getMaxRayonBrush())) {
                    radius = this.getValidArgument().getInteger(args[2]);
                } else {
                    this.getValidArgument().sendMessageInvalidInteger(executor, args[2], 0, conf.getMaxRayonBrush());
                    break;
                }

                brushBuilder.setBrush(new OverlayBrush())
                        .setEnable(true)
                        .setPattern(pattern)
                        .setRadius(radius)
                        .sendMessage("expbuild.message.brush.brush_enable", true, new String[]{"Overlay"});

            }
            case "spike" -> {

                if (this.getValidArgument().isPattern(p, args[1])) {
                    pattern = this.getValidArgument().getPattern(p, args[1]);
                } else {
                    this.getValidArgument().sendMessageInvalidPattern(executor, args[1]);
                    break;
                }

                if (this.getValidArgument().isInteger(args[2], 0, conf.getMaxRayonBrush())) {
                    radius = this.getValidArgument().getInteger(args[2]);
                } else {
                    this.getValidArgument().sendMessageInvalidInteger(executor, args[2], 0, conf.getMaxRayonBrush());
                    break;
                }

                brushBuilder.setBrush(new SpikeBrush())
                        .setEnable(true)
                        .setPattern(pattern)
                        .setRadius(radius)
                        .sendMessage("expbuild.message.brush.brush_enable", true, new String[]{"Spike"});

            }
            case "cube" -> {

                if (this.getValidArgument().isPattern(p, args[1])) {
                    pattern = this.getValidArgument().getPattern(p, args[1]);
                } else {
                    this.getValidArgument().sendMessageInvalidPattern(executor, args[1]);
                    break;
                }

                if (this.getValidArgument().isInteger(args[2], 0, conf.getMaxRayonBrush())) {
                    radius = this.getValidArgument().getInteger(args[2]);
                } else {
                    this.getValidArgument().sendMessageInvalidInteger(executor, args[2], 0, conf.getMaxRayonBrush());
                    break;
                }

                brushBuilder.setBrush(new CubeBrush())
                        .setEnable(true)
                        .setPattern(pattern)
                        .setRadius(radius)
                        .sendMessage("expbuild.message.brush.brush_enable", true, new String[]{"Cube"});
            }

            case "sphere", "s" -> {

                if (this.getValidArgument().isPattern(p, args[1])) {
                    pattern = this.getValidArgument().getPattern(p, args[1]);
                } else {
                    this.getValidArgument().sendMessageInvalidPattern(executor, args[1]);
                    break;
                }

                if (this.getValidArgument().isInteger(args[2], 0, conf.getMaxRayonBrush())) {
                    radius = this.getValidArgument().getInteger(args[2]);
                } else {
                    this.getValidArgument().sendMessageInvalidInteger(executor, args[2], 0, conf.getMaxRayonBrush());
                    break;
                }

                brushBuilder.setBrush(new SphereBrush())
                        .setEnable(true)
                        .setPattern(pattern)
                        .setRadius(radius)
                        .sendMessage("expbuild.message.brush.brush_enable", true, new String[]{"Sphere"});
            }

            case "rot2Dcube" -> {

                if (this.getValidArgument().isPattern(p, args[1])) {
                    pattern = this.getValidArgument().getPattern(p, args[1]);
                } else {
                    this.getValidArgument().sendMessageInvalidPattern(executor, args[1]);
                    break;
                }

                if (this.getValidArgument().isInteger(args[2], 0, conf.getMaxRayonBrush())) {
                    radius = this.getValidArgument().getInteger(args[2]);
                } else {
                    this.getValidArgument().sendMessageInvalidInteger(executor, args[2], 0, conf.getMaxRayonBrush());
                    break;
                }

                brushBuilder.setBrush(new Rot2DCubeBrush())
                        .setEnable(true)
                        .setPattern(pattern)
                        .setRadius(radius)
                        .sendMessage("expbuild.message.brush.brush_enable", true, new String[]{"Rot2Dcube"});
            }

            // TYPE : Brush pattern
            case "line" -> {

                if (this.getValidArgument().isPattern(p, args[1])) {
                    pattern = this.getValidArgument().getPattern(p, args[1]);
                } else {
                    this.getValidArgument().sendMessageInvalidPattern(executor, args[1]);
                    break;
                }

                brushBuilder.setBrush(new LineBrush())
                        .setEnable(true)
                        .setPattern(pattern)
                        .sendMessage("expbuild.message.brush.brush_enable", true, new String[]{"Line"});
            }

            // TYPE : Brush integer
            case "blendball", "bb" -> {

                if (this.getValidArgument().isInteger(args[1], 0, conf.getMaxRayonBrush())) {
                    radius = this.getValidArgument().getInteger(args[1]);
                } else {
                    this.getValidArgument().sendMessageInvalidInteger(executor, args[1], 0, conf.getMaxRayonBrush());
                    break;
                }

                brushBuilder.setBrush(new BlendBallBrush())
                        .setEnable(true)
                        .setRadius(radius)
                        .sendMessage("expbuild.message.brush.brush_enable", true, new String[]{"blendBall"});

            }

            case "drain" -> {

                if (this.getValidArgument().isInteger(args[1], 0, conf.getMaxRayonBrush())) {
                    radius = this.getValidArgument().getInteger(args[1]);
                } else {
                    this.getValidArgument().sendMessageInvalidInteger(executor, args[1], 0, conf.getMaxRayonBrush());
                    break;
                }

                brushBuilder.setBrush(new DrainBrush())
                        .setEnable(true)
                        .setRadius(radius)
                        .sendMessage("expbuild.message.brush.brush_enable", true, new String[]{"Drain"});

            }

            case "updatechunk" -> {

                if (this.getValidArgument().isInteger(args[1], 0, conf.getMaxRayonBrush())) {
                    radius = this.getValidArgument().getInteger(args[1]);
                } else {
                    this.getValidArgument().sendMessageInvalidInteger(executor, args[1], 0, conf.getMaxRayonBrush());
                    break;
                }

                brushBuilder.setBrush(new UpdateChunkBrush())
                        .setEnable(true)
                        .setRadius(radius)
                        .sendMessage("expbuild.message.brush.brush_enable", true, new String[]{"UpdateChunk"});

            }

            case "eraser" -> {

                if (this.getValidArgument().isInteger(args[1], 0, conf.getMaxRayonBrush())) {
                    radius = this.getValidArgument().getInteger(args[1]);
                } else {
                    this.getValidArgument().sendMessageInvalidInteger(executor, args[1], 0, conf.getMaxRayonBrush());
                    break;
                }

                brushBuilder.setBrush(new EraserBrush())
                        .setEnable(true)
                        .setRadius(radius)
                        .sendMessage("expbuild.message.brush.brush_enable", true, new String[]{"Eraser"});

            }

            case "clipboard3D" -> {

                if (this.getValidArgument().hasSelection(p)) {
                    Region selection = this.getValidArgument().getSelection(p);
                } else {
                    this.getValidArgument().sendMessageInvalidSelection(executor);
                    break;
                }

                Clipboard clip = new UtilsFAWE(p).CopySelection(false);
                GlueList<BlockVec4> list = new GlueList<>();

                clip.iterator().forEachRemaining(blockVector3 -> {

                    BlockVector3 blockVector31 = clip.getOrigin().add(blockVector3);

                    int blockX = blockVector31.getBlockX() - clip.getOrigin().getX();
                    int blockY = blockVector31.getBlockY() - clip.getOrigin().getY();
                    int blockZ = blockVector31.getBlockZ() - clip.getOrigin().getZ();

                    int deltaX = blockX - clip.getOrigin().getX();
                    int deltaY = blockY - clip.getOrigin().getY();
                    int deltaZ = blockZ - clip.getOrigin().getZ();

                    list.add(new BlockVec4(
                            deltaX,
                            deltaY,
                            deltaZ,
                            clip.getFullBlock(blockX, blockY, blockZ)));
                });

                brushBuilder.setBrush(new Clipboard3DBrush())
                        .setEnable(true)
                        .sendMessage("expbuild.message.brush.brush_enable", true, new String[]{"3DClipboard"})
                        .getClipboardBrush().setClipboardsBrush(list);
            }

            case "erode", "e" -> {

                if (this.getValidArgument().isInteger(args[2], 0, conf.getMaxRayonBrush())) {
                    radius = this.getValidArgument().getInteger(args[2]);
                } else {
                    this.getValidArgument().sendMessageInvalidInteger(executor, args[2], 0, conf.getMaxRayonBrush());
                    break;
                }

                switch (args[1]) {

                    case "lift" -> brushBuilder.setBrush(new ErodeBrush())
                            .setRadius(radius)
                            .setEnable(true)
                            .getTerraparameterProfile()

                            .setErosionFaces((byte) 6)
                            .setErosionRecursion((byte)0)
                            .setFillFaces((byte)1)
                            .setFillRecursion((byte)1)

                            .sendMessage("expbuild.message.brush.brush_enable", true, new String[]{"Erode lift"});

                    case "melt" -> brushBuilder.setBrush(new ErodeBrush())
                            .setRadius(radius)
                            .setEnable(true)
                            .getTerraparameterProfile()
                            .setErosionFaces((byte) 2)
                            .setErosionRecursion((byte) 1)
                            .setFillFaces((byte) 5)
                            .setFillRecursion((byte) 1)
                            .sendMessage("expbuild.message.brush.brush_enable", true, new String[]{"Erode melt"});

                    case "fill" -> brushBuilder.setBrush(new ErodeBrush())
                            .setRadius(radius)
                            .setEnable(true)
                            .getTerraparameterProfile()
                            .setErosionFaces((byte) 5)
                            .setErosionRecursion((byte) 1)
                            .setFillFaces((byte) 2)
                            .setFillRecursion((byte) 1)
                            .sendMessage("expbuild.message.brush.brush_enable", true, new String[]{"Erode fill"});

                    case "smooth" -> brushBuilder.setBrush(new ErodeBrush())
                            .setRadius(radius)
                            .setEnable(true)
                            .getTerraparameterProfile()
                            .setErosionFaces((byte) 3)
                            .setErosionRecursion((byte) 1)
                            .setFillFaces((byte) 3)
                            .setFillRecursion((byte) 1)
                            .sendMessage("expbuild.message.brush.brush_enable", true, new String[]{"Erode smooth"});

                    case "floatclean" -> brushBuilder.setBrush(new ErodeBrush())
                            .setRadius(radius)
                            .setEnable(true)
                            .getTerraparameterProfile()
                            .setErosionFaces((byte) 6)
                            .setErosionRecursion((byte) 1)
                            .setFillFaces((byte) 6)
                            .setFillRecursion((byte) 1)
                            .sendMessage("expbuild.message.brush.brush_enable", true, new String[]{"Erode floatClean"});
                }
            }

            case "eb", "erodeblend" -> {

                if (this.getValidArgument().isInteger(args[2], 0, conf.getMaxRayonBrush())) {
                    radius = this.getValidArgument().getInteger(args[2]);
                } else {
                    this.getValidArgument().sendMessageInvalidInteger(executor, args[2], 0, conf.getMaxRayonBrush());
                    break;
                }

                switch (args[1]) {

                    case "lift" -> brushBuilder.setBrush(new ErodeBlendBrush())
                            .setRadius(radius)
                            .setEnable(true)
                            .getTerraparameterProfile()
                            .setErosionFaces((byte) 6)
                            .setErosionRecursion((byte) 0)
                            .setFillFaces((byte) 1)
                            .setFillRecursion((byte) 1)
                            .sendMessage("expbuild.message.brush.brush_enable", true, new String[]{"ErodeBlend lift"});

                    case "melt" -> brushBuilder.setBrush(new ErodeBlendBrush())
                            .setRadius(radius)
                            .setEnable(true)
                            .getTerraparameterProfile()
                            .setErosionFaces((byte) 2)
                            .setErosionRecursion((byte) 1)
                            .setFillFaces((byte) 5)
                            .setFillRecursion((byte) 1)
                            .sendMessage("expbuild.message.brush.brush_enable", true, new String[]{"ErodeBlend melt"});

                    case "fill" -> brushBuilder.setBrush(new ErodeBlendBrush())
                            .setRadius(radius)
                            .setEnable(true)
                            .getTerraparameterProfile()
                            .setErosionFaces((byte) 5)
                            .setErosionRecursion((byte) 1)
                            .setFillFaces((byte) 2)
                            .setFillRecursion((byte) 1)
                            .sendMessage("expbuild.message.brush.brush_enable", true, new String[]{"ErodeBlend fill"});

                    case "smooth" -> brushBuilder.setBrush(new ErodeBlendBrush())
                            .setRadius(radius)
                            .setEnable(true)
                            .getTerraparameterProfile()
                            .setErosionFaces((byte) 3)
                            .setErosionRecursion((byte) 1)
                            .setFillFaces((byte) 3)
                            .setFillRecursion((byte) 1)
                            .sendMessage("expbuild.message.brush.brush_enable", true, new String[]{"ErodeBlend smooth"});

                    case "floatclean" -> brushBuilder.setBrush(new ErodeBlendBrush())
                            .setRadius(radius)
                            .setEnable(true)
                            .getTerraparameterProfile()
                            .setErosionFaces((byte) 6)
                            .setErosionRecursion((byte) 1)
                            .setFillFaces((byte) 6)
                            .setFillRecursion((byte) 1)
                            .sendMessage("expbuild.message.brush.brush_enable", true, new String[]{"ErodeBlend floatClean"});

                }
            }

            case "custom" -> {

                int parameter1;
                int parameter2;
                int parameter3;
                int parameter4;

                if (this.getValidArgument().isInteger(args[1], 0, 6)) {
                    parameter1 = this.getValidArgument().getInteger(args[1]);
                } else {
                    this.getValidArgument().sendMessageInvalidInteger(executor, args[1], 0, 6);
                    break;
                }

                if (this.getValidArgument().isInteger(args[2], 0, 6)) {
                    parameter2 = this.getValidArgument().getInteger(args[2]);
                } else {
                    this.getValidArgument().sendMessageInvalidInteger(executor, args[2], 0, 6);
                    break;
                }

                if (this.getValidArgument().isInteger(args[3], 0, 6)) {
                    parameter3 = this.getValidArgument().getInteger(args[3]);
                } else {
                    this.getValidArgument().sendMessageInvalidInteger(executor, args[3], 0, 6);
                    break;
                }

                if (this.getValidArgument().isInteger(args[4], 0, 6)) {
                    parameter4 = this.getValidArgument().getInteger(args[4]);
                } else {
                    this.getValidArgument().sendMessageInvalidInteger(executor, args[4], 0, 6);
                    break;
                }

                if (this.getValidArgument().isInteger(args[4], 0, conf.getMaxRayonBrush())) {
                    radius = this.getValidArgument().getInteger(args[4]);
                } else {
                    this.getValidArgument().sendMessageInvalidInteger(executor, args[4], 0, 6);
                    break;
                }

                brushBuilder.setBrush(new ErodeBrush())
                        .setRadius(radius)
                        .setEnable(true)
                        .getTerraparameterProfile()
                        .setErosionFaces((byte) parameter1)
                        .setErosionRecursion((byte) parameter2)
                        .setFillFaces((byte) parameter3)
                        .setFillRecursion((byte) parameter4)
                        .sendMessage("expbuild.message.brush.brush_enable", true, new String[]{"Custom"});

            }

            case "none" -> brushBuilder.setBrush(new NoneBrush())
                    .setEnable(false)
                    .sendMessage("expbuild.message.brush.brush_disable", true);

            case "register" -> {

                if (args.length <= 1) {
                    BrushBuilder.registerPlayer(p, false);
                }

                if (args.length == 2) {

                    Bukkit.getOnlinePlayers().stream()
                            .filter(player -> player.getName().equals(args[1]))
                            .forEach(player ->
                                    p.sendMessage(Main.prefix + BrushBuilder.getBrushBuilderPlayer(player, false).toString()));
                }
            }

            case "clipboard" -> clipboardCommand(p, args, this.getValidArgument());
        }
    }


    @Override
    protected OptionalConditionExecution getArgumentLengthList(CommandSender sender) {
        return new OptionalConditionExecution(sender).AddBrushBuilderProfile();
    }

    @Override
    protected ArgumentLengthList getArgumentLengthList() {
        return new ArgumentLengthList(Arrays.asList(

                new ArgumentLength(2, "material", 0, "/flower material <pattern>", 2),
                new ArgumentLength(2, "radius", 0, "/flower radius <integer>", 2),

                //register

                new ArgumentLength(3, "biome", 0, "/flower biome <biome> <radius>", 2),

                new ArgumentLength(3, "line", 0, "/flower line <pettern>", 2),

                new ArgumentLength(3, "overlay", 0, "/flower overlay <pattern> <radius>", 2),
                new ArgumentLength(3, "spike", 0, "/flower spike <pattern> <radius>", 2),
                new ArgumentLength(3, "cube", 0, "/flower cube <pattern> <radius>", 2),
                new ArgumentLength(3, "rot2Dcube", 0, "/flower rot2Dcube <pattern> <radius>", 2),
                new ArgumentLength(3, "sphere", 0, "/flower sphere <pattern> <radius>", 2),

                new ArgumentLength(2, "updatechunk", 0, "/flower updatechunk <radius>", 2),
                new ArgumentLength(2, "drain", 0, "/flower drain  <radius>", 2),
                new ArgumentLength(2, "eraser", 0, "/flower eraser <radius>", 2),
                new ArgumentLength(2, "bb", 0, "/flower bb <radius>", 2),

                new ArgumentLength(3, "e", 0, "/flower e <lift-melt-fill-smooth-floatclean> <radius>", 2),
                new ArgumentLength(3, "eb", 0, "/flower eb <lift-melt-fill-smooth-floatclean> <radius>", 2),

                new ArgumentLength(6, "custom", 0, "/flower bb <erosion face> <erosion recursion> <fill faces> <fill recursion> <radius>", 2),


                new ArgumentLength(3, "remove", 1, "/flower clipboard remove <clipboard name>", 2),
                new ArgumentLength(2, "removeAll", 1, "/flower clipboard removeAll", 1),
                new ArgumentLength(2, "autorotation", 1, "/flower clipboard autorotation", 1),
                new ArgumentLength(2, "add", 1, "/flower clipboard add [clipboard name]", 1),

                new ArgumentLength(2, "clipboard", 0, "/flower clipboard <add-remove-removeAll-autorotation>", 2)

        ));
    }

    @Override
    public SubCommandSender getSubCommand(CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        final List<String> erodeArgs = Arrays
                .asList("lift", "melt", "fill", "smooth", "floatclean");

        final List<String> clipboardBrush = Arrays
                .asList("add", "removeAll", "remove", "autoRotation");

        SubCommandSender subCommandSender = new SubCommandSender();

        if (sender instanceof Player p) {

            // Brush
            // name
            Main.getBrush().getBrushes().iterator().forEachRemaining(registerBrush -> subCommandSender.addSubCommand(new SubCommandSelector().getArgs(0, registerBrush.getBrushName()).toSubCommand(registerBrush.getPermission())));

            // aliases
            Main.getBrush().getBrushes().stream()
                    .filter(abstractBrush -> !abstractBrush.getAliases().equals("unused"))
                    .forEach(registerBrush -> subCommandSender.addSubCommand(new SubCommandSelector().getArgs(0, registerBrush.getAliases()).toSubCommand(registerBrush.getPermission())));

            //Arrays.stream(BrushEnum.values()).toList().forEach(brushEnum -> subCommandSender.addSubCommand(new SubCommandSelector().getArgs(0, brushEnum.getBrush()).toSubCommand(brushEnum.getPermission())));

            // Material
            subCommandSender.addSubCommand(new SubCommandSelector().getArgs(0, "material").toSubCommand("None"));
            // Radius
            subCommandSender.addSubCommand(new SubCommandSelector().getArgs(0, "radius").toSubCommand("None"));
            // Register
            subCommandSender.addSubCommand(new SubCommandSelector().getArgs(0, "register").toSubCommand("exp.register"));
            subCommandSender.addSubCommand(new SubCommandSelector().getPlayerList(args, 1).toSubCommand("None", new ConditionArgumentBefore("register", 0)));


            // Biome
            subCommandSender.addSubCommand(new SubCommandSelector().getBiomeList(args, 1).toSubCommand("exp.brush.biome", new ConditionArgumentBefore("biome", 0)));

            // Brush with pattern <pattern>
            subCommandSender.addSubCommand(new SubCommandSelector().getPatternFactoryList(args, 1).toSubCommand("none", new ConditionArgumentBefore("material", 0)));
            subCommandSender.addSubCommand(new SubCommandSelector().getPatternFactoryList(args, 1).toSubCommand("exp.brush.overlay", new ConditionArgumentBefore("overlay", 0)));
            subCommandSender.addSubCommand(new SubCommandSelector().getPatternFactoryList(args, 1).toSubCommand("exp.brush.spike", new ConditionArgumentBefore("spike", 0)));
            subCommandSender.addSubCommand(new SubCommandSelector().getPatternFactoryList(args, 1).toSubCommand("exp.brush.cube", new ConditionArgumentBefore("cube", 0)));
            subCommandSender.addSubCommand(new SubCommandSelector().getPatternFactoryList(args, 1).toSubCommand("exp.brush.line", new ConditionArgumentBefore("line", 0)));
            subCommandSender.addSubCommand(new SubCommandSelector().getPatternFactoryList(args, 1).toSubCommand("exp.brush.sphere", new ConditionArgumentBefore("sphere", 0)));
            subCommandSender.addSubCommand(new SubCommandSelector().getPatternFactoryList(args, 1).toSubCommand("exp.brush.sphere", new ConditionArgumentBefore("s", 0)));
            subCommandSender.addSubCommand(new SubCommandSelector().getPatternFactoryList(args, 1).toSubCommand("exp.brush.2dcube", new ConditionArgumentBefore("rot2Dcube", 0)));

            // Brush with pattern/biome and integer <integer>
            subCommandSender.addSubCommand(new SubCommandSelector().getPositiveIntegerList(args, 2).toSubCommand("exp.brush.overlay", new ConditionArgumentBefore("overlay", 0)));
            subCommandSender.addSubCommand(new SubCommandSelector().getPositiveIntegerList(args, 2).toSubCommand("exp.brush.spike", new ConditionArgumentBefore("spike", 0)));
            subCommandSender.addSubCommand(new SubCommandSelector().getPositiveIntegerList(args, 2).toSubCommand("exp.brush.cube", new ConditionArgumentBefore("cube", 0)));
            subCommandSender.addSubCommand(new SubCommandSelector().getPositiveIntegerList(args, 2).toSubCommand("exp.brush.2dcube", new ConditionArgumentBefore("rot2Dcube", 0)));
            subCommandSender.addSubCommand(new SubCommandSelector().getPositiveIntegerList(args, 2).toSubCommand("exp.brush.sphere", new ConditionArgumentBefore("sphere", 0)));
            subCommandSender.addSubCommand(new SubCommandSelector().getPositiveIntegerList(args, 2).toSubCommand("exp.brush.sphere", new ConditionArgumentBefore("s", 0)));
            subCommandSender.addSubCommand(new SubCommandSelector().getPositiveIntegerList(args, 2).toSubCommand("exp.brush.biome", new ConditionArgumentBefore("biome", 0)));


            // Brush with <integer>
            subCommandSender.addSubCommand(new SubCommandSelector().getPositiveIntegerList(args, 1).toSubCommand("exp.brush.blendball", new ConditionArgumentBefore("bb", 0)));
            subCommandSender.addSubCommand(new SubCommandSelector().getPositiveIntegerList(args, 1).toSubCommand("exp.brush.blendball", new ConditionArgumentBefore("blendball", 0)));

            subCommandSender.addSubCommand(new SubCommandSelector().getPositiveIntegerList(args, 1).toSubCommand("exp.brush.drain", new ConditionArgumentBefore("drain", 0)));
            subCommandSender.addSubCommand(new SubCommandSelector().getPositiveIntegerList(args, 1).toSubCommand("exp.brush.updatechunk", new ConditionArgumentBefore("update_chunk", 0)));
            subCommandSender.addSubCommand(new SubCommandSelector().getPositiveIntegerList(args, 1).toSubCommand("exp.brush.eraser", new ConditionArgumentBefore("eraser", 0)));
            subCommandSender.addSubCommand(new SubCommandSelector().getPositiveIntegerList(args, 1).toSubCommand("none", new ConditionArgumentBefore("radius", 0)));


            // Erode / ErodeBlend
            subCommandSender.addSubCommand(new SubCommandSelector().getList(1, erodeArgs).toSubCommand("exp.brush.erode", new ConditionArgumentBefore("e", 0)));
            subCommandSender.addSubCommand(new SubCommandSelector().getList(1, erodeArgs).toSubCommand("exp.brush.erode", new ConditionArgumentBefore("erode", 0)));

            subCommandSender.addSubCommand(new SubCommandSelector().getList(1, erodeArgs).toSubCommand("exp.brush.erodeblend", new ConditionArgumentBefore("eb", 0)));
            subCommandSender.addSubCommand(new SubCommandSelector().getList(1, erodeArgs).toSubCommand("exp.brush.erodeblend", new ConditionArgumentBefore("erodeblend", 0)));


            subCommandSender.addSubCommand(new SubCommandSelector().getPositiveIntegerList(args, 2).toSubCommand("exp.brush.custom", new ConditionArgumentBefore("lift", 1)));
            subCommandSender.addSubCommand(new SubCommandSelector().getPositiveIntegerList(args, 2).toSubCommand("exp.brush.custom", new ConditionArgumentBefore("melt", 1)));
            subCommandSender.addSubCommand(new SubCommandSelector().getPositiveIntegerList(args, 2).toSubCommand("exp.brush.custom", new ConditionArgumentBefore("smooth", 1)));
            subCommandSender.addSubCommand(new SubCommandSelector().getPositiveIntegerList(args, 2).toSubCommand("exp.brush.custom", new ConditionArgumentBefore("fill", 1)));
            subCommandSender.addSubCommand(new SubCommandSelector().getPositiveIntegerList(args, 2).toSubCommand("exp.brush.custom", new ConditionArgumentBefore("floatclean", 1)));


            // Custom erode brush
            // Custom
            subCommandSender.addSubCommand(new SubCommandSelector().getArgs(0, "custom").toSubCommand("exp.brush.custom"));

            // Erosion face <integer>
            subCommandSender.addSubCommand(new SubCommandSelector().getPositiveIntegerList(args, 1).toSubCommand("exp.brush.custom", new ConditionArgumentBefore("custom", 0)));
            // Erosion recursion
            subCommandSender.addSubCommand(new SubCommandSelector().getPositiveIntegerList(args, 2).toSubCommand("exp.brush.custom", new ConditionArgumentBefore("custom", 0)));
            // Fill faces
            subCommandSender.addSubCommand(new SubCommandSelector().getPositiveIntegerList(args, 3).toSubCommand("exp.brush.custom", new ConditionArgumentBefore("custom", 0)));
            // Fill recursion
            subCommandSender.addSubCommand(new SubCommandSelector().getPositiveIntegerList(args, 4).toSubCommand("exp.brush.custom", new ConditionArgumentBefore("custom", 0)));
            // Custom erode radius <integer>
            subCommandSender.addSubCommand(new SubCommandSelector().getPositiveIntegerList(args, 5).toSubCommand("exp.brush.custom", new ConditionArgumentBefore("custom", 0)));

            // Clipboard
            subCommandSender.addSubCommand(new SubCommandSelector().getList(1, clipboardBrush).toSubCommand("exp.brush.custom", new ConditionArgumentBefore("clipboard", 0)));
            subCommandSender.addSubCommand(new SubCommandSelector().getList(2, this.getBrushBuilder(p).getClipboardsParameter().getClipboardsName().stream().toList()).toSubCommand("exp.brush.custom", new ConditionArgumentBefore("remove", 1)));

        }
        return subCommandSender;
    }

    private static void clipboardCommand(Player p, String @NotNull [] args, ValidArgument validArgument) {

        BrushBuilder brushBuilder = BrushBuilder.getBrushBuilderPlayer(p, true);

        if (args[1].equalsIgnoreCase("autoRotation")) {

            if (brushBuilder.getClipboardsParameter().isRandomRotation()) {
                brushBuilder.sendMessage("expbuild.message.commands.disable", true, new String[]{"Auto-rotation"})
                        .getClipboardsParameter().setRandomRotation(false);
            } else {
                brushBuilder.sendMessage("expbuild.message.commands.enable", true, new String[]{"Auto-rotation"})
                        .getClipboardsParameter().setRandomRotation(true);
            }
        }

        if (args[1].equalsIgnoreCase("removeAll")) {

            brushBuilder.setBrush(new NoneBrush())
                    .setEnable(false)
                    .sendMessage("expbuild.message.commands.all_clipboards_delete", true)
                    .getClipboardsParameter().clearAll();
            return;
        }
        if (args[1].equalsIgnoreCase("remove")) {

            if (args.length == 2) {
                brushBuilder.sendMessage("expbuild.message.commands.use", true, new String[]{"/fw clipboard remove <clipboard-name>"});
                return;
            }

            if (brushBuilder.getClipboardsParameter().getClipboardsNameExist(args[2])) {
                brushBuilder.sendMessage("expbuild.message.commands.clipboard_remove", true, new String[]{args[2]})
                        .getClipboardsParameter().removeClipboards(args[2]);
            } else {
                brushBuilder.sendMessage("expbuild.message.commands.clipboard_does_not_exist", true, new String[]{args[2]});
            }
            return;

        }

        if (args[1].equalsIgnoreCase("add")) {

            if (validArgument.hasSelection(p)) {
            } else {
                validArgument.sendMessageInvalidSelection(p);
                return;
            }

            String clipboardName;

            if (args.length >= 3) {

                if (brushBuilder.getClipboardsParameter().getClipboardsNameExist(args[2])) {
                    brushBuilder.sendMessage("expbuild.message.commands.clipboard_already_exist", true, new String[]{args[2]});
                    return;
                } else {
                    clipboardName = args[2];
                }

            } else {
                clipboardName = "clipboards_" + brushBuilder.getClipboardsParameter().getClipboardsBlock().size();
            }


            Clipboard clip = new UtilsFAWE(p).CopySelection(false);
            List<BlockVec4> list = new ArrayList<>();

            clip.iterator().forEachRemaining(blockVector3 -> {

                BlockVector3 blockVector31 = clip.getOrigin().add(blockVector3);

                int blockX = blockVector31.getBlockX() - clip.getOrigin().getX();
                int blockY = blockVector31.getBlockY() - clip.getOrigin().getY();
                int blockZ = blockVector31.getBlockZ() - clip.getOrigin().getZ();

                int deltaX = blockX - clip.getOrigin().getX();
                int deltaY = blockY - clip.getOrigin().getY();
                int deltaZ = blockZ - clip.getOrigin().getZ();

                list.add(new BlockVec4(
                        deltaX,
                        deltaY,
                        deltaZ,
                        clip.getFullBlock(blockX, blockY, blockZ)));
            });

            brushBuilder.setBrush(new ClipboardsBrush())
                    .setEnable(true)
                    .sendMessage("expbuild.message.commands.clipboard_add_and_enable", true, new String[]{clipboardName})
                    .getClipboardsParameter().addClipboards(list, clipboardName);

        }
    }
}
