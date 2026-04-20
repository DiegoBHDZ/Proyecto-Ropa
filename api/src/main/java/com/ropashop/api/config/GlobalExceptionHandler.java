package com.ropashop.api.config;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidacion(MethodArgumentNotValidException ex) {
        List<String> errores = ex.getBindingResult().getFieldErrors().stream()
                .map(fe -> "• " + fe.getField() + ": " + fe.getDefaultMessage())
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                "error", "Corrige los siguientes campos:", "campos", errores));
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Map<String, Object>> handleDataAccess(DataAccessException ex) {
        String causa = ex.getMessage() != null ? ex.getMessage() : "";
        String mensaje;
        if (causa.contains("Categoria") || causa.contains("categoria")) {
            mensaje = "La categoría seleccionada no es válida. Selecciona una de la lista.";
        } else if (causa.contains("sku") || causa.contains("SKU")) {
            mensaje = "El SKU ingresado ya existe en el sistema.";
        } else {
            mensaje = "Error de base de datos. Verifica los datos e intenta de nuevo.";
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                "error", mensaje, "campos", List.of()));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicado(DataIntegrityViolationException ex) {
        String mensaje = "Error de datos duplicados.";
        if (ex.getMessage() != null && ex.getMessage().toLowerCase().contains("sku")) {
            mensaje = "El SKU ingresado ya existe en el sistema. Usa un código diferente.";
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
                "error", mensaje, "campos", List.of()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntime(RuntimeException ex) {
        String mensaje;
        if (ex.getMessage() != null && ex.getMessage().contains("Categoría no encontrada")) {
            mensaje = "La categoría seleccionada no existe. Recarga la página e intenta de nuevo.";
        } else if (ex.getMessage() != null && ex.getMessage().contains("Prenda no encontrada")) {
            mensaje = "La prenda no fue encontrada.";
        } else {
            mensaje = "Error al procesar la solicitud. Verifica los datos e intenta de nuevo.";
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                "error", mensaje, "campos", List.of()));
    }
}
