package com.deliverygig.moonjyoung.entity.image;

import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@Entity
@Table(name = "pick_up_area_image")
public class PickUpAreaImageEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "puai_seq") private Long puaiSeq; // 수령장소이미지번호
    @Column(name = "puai_image") private String puaiImage; // 수령장소이미지
    @Column(name = "puai_pua_seq") private Long puaiPuaSeq; // 수령장소-세부랑 연결 번호
    @Column(name = "puai_uri") private String puaiUri; // url 추가
    
}
