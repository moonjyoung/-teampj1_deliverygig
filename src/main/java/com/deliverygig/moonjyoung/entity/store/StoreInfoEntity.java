package com.deliverygig.moonjyoung.entity.store;

import java.time.LocalDate;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@Entity
@Table(name = "store_info")
public class StoreInfoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "si_seq")    private Long   siSeq;
    @Column(name = "si_name")   private String siName;
    @Column(name = "si_discount") @ColumnDefault("0")     private Double    siDiscount;
    @Column(name = "si_regdt")    @ColumnDefault("now()") private LocalDate siRegdt;
}
