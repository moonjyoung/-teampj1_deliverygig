package com.deliverygig.moonjyoung.entity.mycart;

import java.time.LocalDateTime;

import org.hibernate.annotations.DynamicInsert;

import com.deliverygig.moonjyoung.entity.delivery.StoreTimeDetailEntity;
import com.deliverygig.moonjyoung.entity.delivery.UnivTimeInfoEntity;
import com.deliverygig.moonjyoung.entity.food.FoodMenuInfoEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicInsert
@Table(name = "basket_menu_options_combine")
public class BasketMenuOptionsCombineEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bmoc_seq") private Long bmocSeq;
    @Column(name = "bmoc_bi_seq") private Long bmocBiSeq;
    @Column(name = "bmoc_uti_seq") private Long bmocUtiSeq;
    @Column(name = "bmoc_fmi_seq") private Long bmocFmiSeq;
    @Column(name = "bmoc_option_all") private String bmocOptionAll;
    @Column(name = "bmoc_count") private Integer bmocCount;
    @Column(name = "bmoc_price") private Integer bmocPrice;
    @Column(name = "bmoc_reg_dt") private LocalDateTime bmocRegDt;

    @ManyToOne @JoinColumn(name = "bmoc_bi_seq", insertable=false, updatable=false) private BasketInfoEntity basketInfoEntity;
    @ManyToOne @JoinColumn(name = "bmoc_uti_seq", insertable=false, updatable=false) private UnivTimeInfoEntity univTimeInfoEntity;
    @ManyToOne @JoinColumn(name = "fmoc_fmi_seq", insertable=false, updatable=false) private FoodMenuInfoEntity foodMenuInfoEntity;

    public BasketMenuOptionsCombineEntity(Long biSeq, Long utiSeq, Long fmiSeq, String optionAll, Integer price) {
        this.bmocBiSeq = biSeq;
        this.bmocUtiSeq = utiSeq;
        this.bmocFmiSeq = fmiSeq;
        this.bmocOptionAll = optionAll;
        this.bmocPrice = price;
    }
}
