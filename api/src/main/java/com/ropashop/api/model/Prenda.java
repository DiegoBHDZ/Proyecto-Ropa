package com.ropashop.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "prenda")
public class Prenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank(message = "El SKU es obligatorio")
    @Size(min = 3, max = 20, message = "El SKU debe tener entre 3 y 20 caracteres")
    @Column(nullable = false, unique = true, length = 20)
    private String sku;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede superar 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nombre;

    @NotBlank(message = "La talla es obligatoria")
    @Size(max = 10, message = "La talla no puede superar 10 caracteres")
    @Column(nullable = false, length = 10)
    private String talla;

    @Size(max = 30, message = "El color no puede superar 30 caracteres")
    @Column(length = 30)
    private String color;

    @Size(max = 50, message = "El material no puede superar 50 caracteres")
    @Column(length = 50)
    private String material;

    @PositiveOrZero(message = "El stock no puede ser negativo")
    private Integer stock;

    @Positive(message = "El precio de compra debe ser mayor a 0")
    @Column(precision = 10, scale = 2)
    private BigDecimal precioCompra;

    @NotNull(message = "El estatus es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EstatusPrenda estatus;

    @NotNull(message = "El tipo de precio es obligatorio")
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private TipoPrecio tipoPrecio;

    @NotNull(message = "La categoría es obligatoria")
    @ManyToOne(optional = false)
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    public Prenda() {}

    public Prenda(String sku, String nombre, String talla, String color, String material,
                  Integer stock, BigDecimal precioCompra, EstatusPrenda estatus,
                  TipoPrecio tipoPrecio, Categoria categoria) {
        this.sku = sku;
        this.nombre = nombre;
        this.talla = talla;
        this.color = color;
        this.material = material;
        this.stock = stock;
        this.precioCompra = precioCompra;
        this.estatus = estatus;
        this.tipoPrecio = tipoPrecio;
        this.categoria = categoria;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTalla() { return talla; }
    public void setTalla(String talla) { this.talla = talla; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }

    public BigDecimal getPrecioCompra() { return precioCompra; }
    public void setPrecioCompra(BigDecimal precioCompra) { this.precioCompra = precioCompra; }

    public EstatusPrenda getEstatus() { return estatus; }
    public void setEstatus(EstatusPrenda estatus) { this.estatus = estatus; }

    public TipoPrecio getTipoPrecio() { return tipoPrecio; }
    public void setTipoPrecio(TipoPrecio tipoPrecio) { this.tipoPrecio = tipoPrecio; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
}
