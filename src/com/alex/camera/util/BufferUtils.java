package com.alex.camera.util;

import java.nio.*;

/**
 * Created by alex on 14-11-8.
 */
public class BufferUtils {

    public static FloatBuffer toFloatBuffer(float [] array){
        ByteBuffer bb = ByteBuffer.allocateDirect(array.length << 2);
        bb.order(ByteOrder.nativeOrder());
        FloatBuffer fbb = bb.asFloatBuffer();
        fbb.put(array);
        fbb.position(0);
        return fbb;
    }
    public static IntBuffer toIntBuffer(int [] array){
        ByteBuffer bb = ByteBuffer.allocateDirect(array.length << 2);
        bb.order(ByteOrder.nativeOrder());
        IntBuffer ibb = bb.asIntBuffer();
        ibb.put(array);
        ibb.position(0);
        return ibb;
    }
    public static ShortBuffer toShortBuffer(short [] array){
        ByteBuffer bb = ByteBuffer.allocateDirect(array.length << 1);
        bb.order(ByteOrder.nativeOrder());
        ShortBuffer sbb = bb.asShortBuffer();
        sbb.put(array);
        sbb.position(0);
        return sbb;
    }
}
