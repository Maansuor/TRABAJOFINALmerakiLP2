
package com.example.plan.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "factura")
public class Factura implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="idfactura")
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "idcliente")
    private Cliente cliente;
   
    @ManyToOne
    @JoinColumn(name = "iddetalle")
    private Detalle detalle;
    
    
    
    private double total;
    private double igv=0.18;
    private double totalneto=0.18*total;
    
}