package com.example.Literalura.helpers;

public interface IDataConverter {
    <T> T obtenerDatos(String json, Class<T> clase);
}
