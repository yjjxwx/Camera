package com.alex.camera.util;

import java.nio.*;

/**
 * Created by alex on 14-11-8.
 */
public class BufferUtils {

    public static FloatBuffer toFloatBuffer(float [] array){
        ByteBuffer bb = ByteBuffer.allocateDirect(array.length << 2);
        bb.order(ByteOrder.nativeOrder());
        bb.position(0);
        FloatBuffer fbb = bb.asFloatBuffer();
        fbb.put(array);
        return fbb;
    }
    public static IntBuffer toIntBuffer(int [] array){
        ByteBuffer bb = ByteBuffer.allocateDirect(array.length << 2);
        bb.order(ByteOrder.nativeOrder());
        bb.position(0);
        IntBuffer ibb = bb.asIntBuffer();
        ibb.put(array);
        return ibb;
    }
    public static ShortBuffer toShortBuffer(short [] array){
        ByteBuffer bb = ByteBuffer.allocateDirect(array.length << 1);
        bb.order(ByteOrder.nativeOrder());
        bb.position(0);
        ShortBuffer sbb = bb.asShortBuffer();
        sbb.put(array);
        return sbb;
    }
}
