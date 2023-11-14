package com.batch.settlement.domain.entity.order

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToOne
import lombok.NoArgsConstructor
import java.time.ZonedDateTime

@Entity
data class OrderItem(
    @Id @Column(name = "order_item_no") var id: Long,

    var orderCount: Int? = 1, //주문수량
    var itemDeliveryStatus: Int? = 0, //주문 배송 상태

    var createdAt: ZonedDateTime? = ZonedDateTime.now(), //생성시간
    var updatedAt: ZonedDateTime? = ZonedDateTime.now(), //업데이트시간
    var deletedAt: ZonedDateTime? = null, //삭제시간
    var purchaseConfirmedAt: ZonedDateTime? = null, //구매확정일
    var shippedCompleteAt: ZonedDateTime? = null, //배송완료일

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_no", insertable = false, updatable = false)
    var order: Order,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_item_snapshot_no", referencedColumnName = "order_item_snapshot_no", insertable = false, updatable = false)
    var orderItemSnapshot: OrderItemSnapshot
)
