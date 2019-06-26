#version 400 core

in vec3 position;
in vec2 textureCoordinates;
in vec3 normal;

out vec2 pass_textureCoordinates;
out vec3 surfaceNormal;
out vec3 toLightVector;
out vec3 toCameraVector;

uniform mat4 transformMatrix;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;
uniform vec3 lightPos;

void main(void){

    vec4 WorldPosition = transformMatrix * vec4(position,1.0);

    gl_Position = projectionMatrix * viewMatrix * WorldPosition;
    pass_textureCoordinates = textureCoordinates * 40.0;

    surfaceNormal = (transformMatrix * vec4(normal, 0.0)).xyz;

    toLightVector = lightPos - WorldPosition.xyz;

    toCameraVector = (inverse(viewMatrix) * vec4(0.0, 0.0, 0.0, 1.0)).xyz - WorldPosition.xyz;
}
