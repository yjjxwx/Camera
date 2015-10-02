#extension GL_OES_EGL_image_external : require
precision mediump float;
uniform sampler2D texture;
varying vec2 v_TexCoordinate;

void main () {
    vec4 color = texture2D(texture, v_TexCoordinate);
    //gl_FragColor = vec4(1.0, 0.0, 0.0, 1.0);
    gl_FragColor = color;
}