package com.deliverygig.moonjyoung.entity.delivery;

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
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pick_up_area")
public class PickUpAreaEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pua_seq") private Long puaSeq;
    // @Column(name = "pua_ui_seq") private Long puaUiSeq;
    @Column(name = "pua_name") private String puaName;
    @ManyToOne @JoinColumn(name = "pua_ui_seq") private UnivInfoEntity univInfoEntity;

    public void setUnivInfoEntity(UnivInfoEntity univInfoEntity) {
        this.univInfoEntity = univInfoEntity;
        if (!univInfoEntity.getPuaEntityList().contains(this)) {
            univInfoEntity.getPuaEntityList().add(this);
        }
    }
}
