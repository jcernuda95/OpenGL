#version 400 core

in vec2 pass_textureCoordinates;
in vec3 surfaceNormal;
in vec3 toLightVector;
in vec3 toCameraVector;

out vec4 out_Color;

uniform sampler2D textureSampler;
uniform vec3 lightColour;

uniform float shineDamper;
uniform float reflectivity;

void main(void){

    vec3 unitLightVector = normalize(toLightVector);
    vec3 unitNormal = normalize(surfaceNormal);
    float brightness = max(dot(unitNormal, unitLightVector), 0.2);
    vec3 diffuse = brightness * lightColour;

    vec3 reflectedLightDirection = reflect(-unitLightVector, unitNormal);
    vec3 specular = reflectivity * pow(max(dot(normalize(toCameraVector), reflectedLightDirection), 0.0), shineDamper) * lightColour;

    out_Color = vec4(diffuse, 1.0) * texture(textureSampler, pass_textureCoordinates) + vec4(specular, 1.0);

}
