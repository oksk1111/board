package com.simpleboard.board.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass   //테이블매핑X, 자식클래스에게 매핑정보 상속
@EntityListeners(AuditingEntityListener.class)  //자동 날짜수정
public abstract class TimeEntity {

    @CreatedDate    //엔티티 생성일 지정
    @Column(updatable = false)  //생성일은 수정 필요없다
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
