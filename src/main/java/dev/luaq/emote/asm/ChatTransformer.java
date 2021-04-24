package dev.luaq.emote.asm;

import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraftforge.fml.common.asm.transformers.deobf.FMLDeobfuscatingRemapper;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodNode;

public class ChatTransformer implements IClassTransformer {
    @Override
    public byte[] transform(String name, String transformedName, byte[] data) {
        // if the class name isn't GuiNewChat then we do not touch its data
        if (!transformedName.equalsIgnoreCase("net.minecraft.client.gui.GuiNewChat")) {
            return data;
        }

        // read the class bytecode
        ClassReader reader = new ClassReader(data);
        ClassNode node = new ClassNode();

        // accept the node for parsing
        reader.accept(node, 0);

        // find the method where things are rendered
        for (MethodNode method : node.methods) {
            // get an interpretable method name
            String methodName = FMLDeobfuscatingRemapper.INSTANCE.mapMethodName(node.name, method.name, method.desc);

            // check if it's the drawChat method
            if (!methodName.equalsIgnoreCase("func_146230_a") && !methodName.equalsIgnoreCase("drawChat")) {
                continue; // skip this method
            }

            // instruction list
            InsnList inst = new InsnList();

            // TODO: create instructions and design a method to inject a call to

            // place the instructions
            method.instructions.insertBefore(method.instructions.getLast(), inst);

            // we can break, we found it
            break;
        }

        // create a proper class writer object
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);

        // transform and return the new bytecode
        node.accept(writer);
        return writer.toByteArray();
    }
}
